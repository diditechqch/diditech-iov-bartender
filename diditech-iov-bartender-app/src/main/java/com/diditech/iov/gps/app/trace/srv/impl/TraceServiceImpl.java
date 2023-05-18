package com.diditech.iov.gps.app.trace.srv.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.trace.entity.*;
import com.diditech.iov.gps.app.core.util.GisUtils;
import com.diditech.iov.gps.app.trace.po.GpsAreaCalculationInfo;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.srv.TraceDbService;
import com.diditech.iov.gps.app.trace.srv.TraceRequest;
import com.diditech.iov.gps.app.trace.srv.TraceService;
import com.diditech.iov.gps.app.trip.TripType;
import com.diditech.iov.gps.app.trip.srv.TripService;
import com.diditech.utils.GPSUtil;
import com.diditech.utils.Util;
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

    @Override
    public List<TraceInfo> getTrace(String deviceNum,
                                    Date beginTime,
                                    Date endTime) {
        return gpsService.getTrace(deviceNum, beginTime, endTime);
    }

    @Override
    public List<GpsInfoTripMin> getTraceTripMin(String deviceNum,
                                                Date beginTime,
                                                Date endTime,
                                                String coorType) {
        return gpsService.getTraceTripMin(deviceNum, beginTime, endTime, coorType);
    }

    @Override
    public List getTrip(String deviceNum,
                        Date beginTime,
                        Date endTime,
                        String coorType,
                        Integer minNoDataDuration,
                        Double minTripDistance,
                        Integer includeAddress,
                        Integer order) {
        TripService service = TripType.GPS.getService();
        return service.getTrip(deviceNum, beginTime, endTime, coorType,
                minNoDataDuration, minTripDistance, includeAddress, order);
    }

    @Override
    public List<TripGps> getTrip(TraceRequest request) {
        return TripType.GPS.getService()
                .getTrip(
                        request.getDeviceNum(),
                        request.getBeginTime(),
                        request.getEndTime(),
                        request.getCoorType(),
                        request.getMinNoDataDuration(),
                        request.getMinTripDistance(),
                        request.getIncludeAddress(),
                        request.getOrder());
    }

    @Override
    public List<TripAcc> getAcc(TraceRequest request) {
        return TripType.ACC.getService()
                .getTrip(
                        request.getDeviceNum(),
                        request.getBeginTime(),
                        request.getEndTime(),
                        request.getCoorType(),
                        request.getMinNoDataDuration(),
                        request.getMinTripDistance(),
                        request.getIncludeAddress(),
                        request.getOrder());
    }

    /**
     * 统一里程计算逻辑
     * @date 2023/5/12
     * @author zhjd
     */
    @Override
    public BigDecimal getTripDistance(List<GpsInfoTripMin> oneTrip) {
        double tripDistance = 0D;
        Double lat1;
        Double lng1;
        Double lat2;
        Double lng2;
        for (int i = 1; i < oneTrip.size(); i++) {
            lat1 = oneTrip.get(i - 1).getPrimaryLat();
            lng1 = oneTrip.get(i - 1).getPrimaryLng();
            lat2 = oneTrip.get(i).getPrimaryLat();
            lng2 = oneTrip.get(i).getPrimaryLng();
            tripDistance += GPSUtil.getDistance(lat1, lng1, lat2, lng2);
        }
        return BigDecimal.valueOf(tripDistance).setScale(2, BigDecimal.ROUND_HALF_UP);
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
    public BigDecimal getTraceDistance(String deviceNum, Date beginTime, Date endTime) {
        List<GpsInfoTripMin> traceList
                = getTraceTripMin(deviceNum, beginTime, endTime, CoordinateType.BD09.name());
        if (traceList == null || traceList.size() < 2) {
            return BigDecimal.ZERO;
        }
        return getTripDistance(traceList);
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
