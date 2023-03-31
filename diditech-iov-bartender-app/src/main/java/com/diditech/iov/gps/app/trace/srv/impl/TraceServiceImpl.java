package com.diditech.iov.gps.app.trace.srv.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.trace.entity.*;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.core.util.GisUtils;
import com.diditech.iov.gps.app.geo.address.service.GeoServiceI;
import com.diditech.iov.gps.app.obd.service.ObdService;
import com.diditech.iov.gps.app.trace.po.GpsAreaCalculationInfo;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.srv.TraceDbService;
import com.diditech.iov.gps.app.trace.srv.TraceRequest;
import com.diditech.iov.gps.app.trace.srv.TraceService;
import com.diditech.utils.GPSUtil;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 轨迹服务实体类
 * @author zhjd
 * @date 2023/3/8
 */
@Slf4j
@Service
public class TraceServiceImpl implements TraceService {

    @Autowired
    private TraceDbService gpsService;

    @Autowired
    private GeoServiceI geoService;

    @Autowired
    private ObdService obdService;

    @Override
    public List<TraceInfo> getTrace(String deviceNum,
                                    Date beginTime,
                                    Date endTime) {
        return gpsService.getTrace(deviceNum, beginTime, endTime);
    }

    @Override
    public List<TripCalculate> getTraceTrip(String deviceNum,
                                            Date beginTime,
                                            Date endTime,
                                            String coorType,
                                            Integer minNoDataDuration,
                                            Double minTripDistance,
                                            Integer includeAddress,
                                            Integer order) {
        List<GpsInfoTripMin> traceList = getTraceTripMin(deviceNum, beginTime, endTime, coorType);
        if (CollUtil.isEmpty(traceList)) {
            return new ArrayList<>();
        }
        log.debug("轨迹分段基于轨迹数量={}", traceList.size());
        List<TripCalculate> tripCalculates = calculateTrips(deviceNum, traceList, minNoDataDuration);
        handleTrips(tripCalculates, minTripDistance);

        if (Util.asInt(includeAddress) == 1) {
            String coorBatch = tripCalculates.stream()
                    .map(t -> t.getStartLat() + StrUtil.COMMA + t.getStartLng() + Const.SEP_LINE
                            + t.getEndLat() + StrUtil.COMMA + t.getEndLng() + Const.SEP_LINE)
                    .reduce(Const.EMPTY, String::concat);

            List<String> address = batchGeoDecode(coorBatch, coorType);
            if (address != null && address.size() == tripCalculates.size() * 2) {
                int addrIdx = 0;
                for (TripCalculate trip : tripCalculates) {
                    trip.setStartAddress(address.get(addrIdx).split(Const.REGEX_LINE)[0]);
                    trip.setEndAddress(address.get(addrIdx + 1).split(Const.REGEX_LINE)[0]);
                    addrIdx = addrIdx + 2;
                }
            }
        }

        getObdFuelMulti(tripCalculates, deviceNum);

        if (Util.asInt(order) == 1) {
            return tripCalculates.stream()
                    .sorted(((o1, o2) -> o2.getStartTime().compareTo(o1.getStartTime())))
                    .collect(Collectors.toList());
        }
        return tripCalculates;
    }

    @Override
    public List<TripCalculate> getTraceTrip(TraceRequest request) {
        return getTraceTrip(request.getDeviceNum(),
                request.getBeginTime(),
                request.getEndTime(),
                request.getCoorType(),
                request.getMinNoDataDuration(),
                request.getMinTripDistance(),
                request.getIncludeAddress(),
                request.getOrder());
    }

    private List<GpsInfoTripMin> getTraceTripMin(String deviceNum,
                                                 Date beginTime,
                                                 Date endTime,
                                                 String coorType) {
        return gpsService.getTraceTripMin(deviceNum, beginTime, endTime, coorType);
    }

    /**
     * 根据阈值对轨迹数据做切割
     * @date 2021/2/25
     * @author zhjd
     */
    private List<TripCalculate> calculateTrips(String deviceNum, List<GpsInfoTripMin> traceList, Integer minNoDataDuration) {
        // 过滤停车数据
        List<GpsInfoTripMin> drivingList = traceList.stream()
                .filter(item -> (item.getObdSpeed() != null && item.getObdSpeed() > 5) || item.getSpeed() > 5)
                .collect(Collectors.toList());

        List<List<GpsInfoTripMin>> tripList = new ArrayList<>();
        int startIdx = 0;
        for (int i = 0; (startIdx < drivingList.size() - 1 && i < drivingList.size() - 1); i++) {
            GpsInfoTripMin before = drivingList.get(i);
            GpsInfoTripMin after = drivingList.get(i + 1);
            if (getDateBetween(before, after) >= minNoDataDuration) {
                // 无数据超过一段时间则分隔轨迹，此处由于长时间停车的数据也会一并处理
                tripList.add(drivingList.subList(startIdx, i + 1));
                startIdx = i + 1;
            }
        }
        if (startIdx < drivingList.size() - 1) {
            tripList.add(drivingList.subList(startIdx, drivingList.size()));
        }
        return tripList.stream()
                .map(item -> buildTrip(item, deviceNum))
                .collect(Collectors.toList());
    }

    /**
     * 轨迹分段处理策略：过滤
     * @date 2021/2/20
     * @author zhjd
     */
    private void handleTrips(List<TripCalculate> list, Double minTripDistance) {
        Iterator<TripCalculate> it = list.iterator();
        TripCalculate s;
        int index;
        while (it.hasNext()) {
            s = it.next();
            if (s.getMileage().doubleValue() < minTripDistance) {
                index = list.indexOf(s);
                if (index > 0) {
                    list.get(index - 1).setForceBreak(true);
                }
                it.remove();
            }
        }
    }

    @Override
    public List<String> batchGeoDecode(String batchCoordinates, String coorType) {
        if (Util.isEmpty(batchCoordinates)) {
            return null;
        }
        String addressResult = geoService.geoBatch(batchCoordinates, coorType);
        return Arrays.asList(addressResult.split(StrUtil.COMMA));
    }

    /**
     * @Description 获取相邻两个轨迹点的GpsTime时间间隔
     * @author zhaist
     * @date 2021年01月12日 上午10:58:02
     */
    private long getDateBetween(GpsInfoTripMin currentGps, GpsInfoTripMin nextGps) {
        Date d1 = Util.asDate(currentGps.getGpsTime(), Util.STR_SHORT_DT_FORMAT);
        Date d2 = Util.asDate(nextGps.getGpsTime(), Util.STR_SHORT_DT_FORMAT);
        if (d1 != null && d2 != null) {
            return DateUtil.between(d1, d2, DateUnit.SECOND);
        } else {
            log.error("不合规的GPS坐标点->currentGps:{},nextGps:{}", currentGps, nextGps);
            return 0;
        }
    }

    /**
     * 基于一组分段轨迹生成分段统计数据
     * @date 2021/2/19
     * @author zhjd
     */
    private TripCalculate buildTrip(List<GpsInfoTripMin> oneTrip, String deviceNum) {
        if (CollUtil.isEmpty(oneTrip)) {
            return null;
        }
        int maxSpeed = oneTrip.stream()
                .mapToInt(GpsInfoTripMin::getSpeed)
                .max()
                .getAsInt();

        double tripDistance = 0D;
        Double lat1;
        Double lng1;
        Double lat2;
        Double lng2;
        for (int i = 1; i < oneTrip.size(); i++) {
            lat1 = oneTrip.get(i - 1).getLat();
            lng1 = oneTrip.get(i - 1).getLng();
            lat2 = oneTrip.get(i).getLat();
            lng2 = oneTrip.get(i).getLng();
            tripDistance += GPSUtil.getDistance(lat1, lng1, lat2, lng2);
        }

        GpsInfoTripMin startPoint = oneTrip.get(0);
        GpsInfoTripMin endPoint = oneTrip.get(oneTrip.size() - 1);

        Date start = Util.asDate(startPoint.getGpsTime(), Util.STR_SHORT_DT_FORMAT);
        Date end = Util.asDate(endPoint.getGpsTime(), Util.STR_SHORT_DT_FORMAT);
        long duration = DateUtil.between(start, end, DateUnit.MINUTE);

        TripCalculate trip = new TripCalculate();
        trip.setGpsNumber(oneTrip.size());
        trip.setStartLat(startPoint.getLat());
        trip.setStartLng(startPoint.getLng());
        trip.setStartTime(start);
        trip.setEndLat(endPoint.getLat());
        trip.setEndLng(endPoint.getLng());
        trip.setEndTime(end);
        trip.setMaxSpeed(BigDecimal.valueOf(maxSpeed));
        trip.setMileage(
                BigDecimal.valueOf(tripDistance).setScale(1, BigDecimal.ROUND_HALF_UP));
        if (trip.getMileage() != null && duration > 0L) {
            BigDecimal aveSpeed = trip.getMileage()
                    .divide(BigDecimal.valueOf(duration / 60D), 1, BigDecimal.ROUND_HALF_UP);
            trip.setAveSpeed(aveSpeed);
        } else {
            trip.setAveSpeed(BigDecimal.ZERO);
        }
        trip.setDeviceNum(deviceNum);
        return trip;
    }


    @Override
    public Map<String, Date> calculateGpsAreaInfo(GpsAreaQuery[] queries) {

        List<GpsAreaCalculationInfo> calList = Arrays.stream(queries)
                .map(item -> {
                    GpsAreaCalculationInfo info = new GpsAreaCalculationInfo();
                    BeanUtil.copyProperties(item, info);
                    return info;
                })
                .collect(Collectors.toList());

        calList.forEach(item -> {
            List<TraceInfo> traceInfos = null;
            try {
                traceInfos = getTrace(item.getDeviceNum(),
                        Util.asDate(item.getBeginTime(), Util.STR_SHORT_DT_FORMAT),
                        Util.asDate(item.getEndTime(), Util.STR_SHORT_DT_FORMAT));
            } catch (Exception ex) {
                log.error(JSON.toJSONString(item), ex);
            }
            if (CollUtil.isNotEmpty(traceInfos)) {
                item.setTraceInfos(traceInfos);
            }
        });

        calList.parallelStream()
                .forEach(info -> info.setArea(GisUtils.getInstance().getCircularAreaPoints(
                        info.getCentreLng(), info.getCentreLat(), info.getRadius() / 1000D)));

        Map<String, Date> result = new HashMap<>();

        calList.forEach(item -> {
            Date date = getGpsAreaInfo(item);
            if (date != null) {
                result.put(item.getId(), date);
            }
        });

        return result;
    }

    @Override
    public void getObdFuelMulti(List<TripCalculate> trips, String deviceNum) {
        List<Integer> fuelValues = trips.stream()
                .map(item -> obdService.getObdFuel(deviceNum, item.getStartTime(), item.getEndTime()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(fuelValues)) {
            return;
        }
        for (int i = 0; i < trips.size(); i++) {
            // modify by zhjd 20220512 start
            if (i < fuelValues.size()) {
                trips.get(i).setFuelConsumption(fuelValues.get(i));
            }
            // modify by zhjd 20220512 end
        }
    }

    @Override
    public double getTraceDistance(String deviceNum, Date beginTime, Date endTime) {
        List<GpsInfoTripMin> traceList = getTraceTripMin(deviceNum, beginTime, endTime, CoordinateType.BD09.name());
        if (traceList == null || traceList.size() < 2) {
            return 0;
        }
        double s = 0.0D;
        for (int i = 1; i < traceList.size(); i++) {
            double lat2 = traceList.get(i).getLat();
            double lng2 = traceList.get(i).getLng();
            double lat1 = traceList.get(i - 1).getLat();
            double lng1 = traceList.get(i - 1).getLng();
            s += GPSUtil.getDistance(lat1, lng1, lat2, lng2);
        }
        BigDecimal b = BigDecimal.valueOf(s);
        double result = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }

    @Override
    public GpsEntity getGpsEntity(String deviceNum, Date gpsTime) {
        return gpsService.getGpsEntity(deviceNum, gpsTime);
    }

    @Override
    public GpsEntity getGpsEntity(TraceRequest request) {
        return getGpsEntity(request.getDeviceNum(), request.getBeginTime());
    }

    private Date getGpsAreaInfo(GpsAreaCalculationInfo info) {
        if (CollUtil.isEmpty(info.getTraceInfos())) {
            return null;
        }
        Integer lastStatus = null;
        for (TraceInfo t : info.getTraceInfos()) {
            int curStatus = inArea(t, info.getArea(), info.getCoorType());
            if (lastStatus == null) {
                lastStatus = curStatus;
                continue;
            }
            if (lastStatus == curStatus) {
                continue;
            }
            if (curStatus == info.getInFlag()) {
                return Util.asDate(t.getGpsTime(), Util.STR_SHORT_DT_FORMAT);
            }
            lastStatus = curStatus;
        }
        return null;
    }

    private int inArea(TraceInfo t, List<double[]> area, String coorType) {
        double lat;
        double lng;
        if (CoordinateType.BD09 == CoordinateType.get(coorType)) {
            lat = Util.asDouble(t.getLatBd());
            lng = Util.asDouble(t.getLngBd());
        } else {
            lat = Util.asDouble(t.getLatGc());
            lng = Util.asDouble(t.getLngGc());
        }
        return GPSUtil.isPoineInArea(lng, lat, area) ? 1 : 0;
    }
}
