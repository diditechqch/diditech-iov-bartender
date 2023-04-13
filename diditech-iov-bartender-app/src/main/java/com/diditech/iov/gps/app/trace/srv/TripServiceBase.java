package com.diditech.iov.gps.app.trace.srv;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TripBase;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.address.service.GeoServiceI;
import com.diditech.iov.gps.app.obd.service.ObdService;
import com.diditech.utils.GPSUtil;
import dd.utils.Util;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
public abstract class TripServiceBase<T extends TripBase> implements TripService {

    private static TraceService traceService;

    private static GeoServiceI geoService;

    private static ObdService obdService;

    @Autowired
    public final void setTraceService(TraceService traceService) {
        this.traceService = traceService;
    }

    @Autowired
    public final void setGeoService(GeoServiceI geoService) {
        this.geoService = geoService;
    }

    @Autowired
    public final void setObdService(ObdService obdService) {
        this.obdService = obdService;
    }

    @Override
    public List<T> getTrip(String deviceNum,
                           Date beginTime,
                           Date endTime,
                           String coorType,
                           Integer minNoDataDuration,
                           Double minTripDistance,
                           Integer includeAddress,
                           Integer order) {
        List<GpsInfoTripMin> traceList = traceService.getTraceTripMin(deviceNum, beginTime, endTime, coorType);
        if (CollUtil.isEmpty(traceList)) {
            return new ArrayList<>();
        }

        List<T> trip = getTripInfo(deviceNum, traceList, minNoDataDuration, minTripDistance);

        if (Util.asInt(includeAddress) == 1) {
            String coorBatch = trip.stream()
                    .map(t -> t.getStartLat() + StrUtil.COMMA + t.getStartLng() + Const.SEP_LINE
                            + t.getEndLat() + StrUtil.COMMA + t.getEndLng() + Const.SEP_LINE)
                    .reduce(Const.EMPTY, String::concat);

            List<String> address = geoService.geoBatchReturnList(coorBatch, coorType);
            if (address != null && address.size() == trip.size() * 2) {
                int addrIdx = 0;
                for (T t : trip) {
                    t.setStartAddress(address.get(addrIdx).split(Const.REGEX_LINE)[0]);
                    t.setEndAddress(address.get(addrIdx + 1).split(Const.REGEX_LINE)[0]);
                    addrIdx = addrIdx + 2;
                }
            }
        }

        getObdFuelMulti(trip, deviceNum);

        if (Util.asInt(order) == 1) {
            return trip.stream()
                    .sorted(((o1, o2) -> o2.getStartTime().compareTo(o1.getStartTime())))
                    .collect(Collectors.toList());
        }
        return trip;
    }

    protected abstract List<T> getTripInfo(String deviceNum, List<GpsInfoTripMin> traceList,
                                           Integer minNoDataDuration,
                                           Double minTripDistance);

    /**
     * 基于一组分段轨迹生成分段统计数据
     * @date 2021/2/19
     * @author zhjd
     */
    @SneakyThrows
    protected T buildTrip(List<GpsInfoTripMin> oneTrip, String deviceNum, Class<T> clazz) {
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

        T trip = clazz.newInstance();
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

    /**
     * 批量获取油耗信息
     * @return void
     * @date 2022/1/12
     * @author zhjd
     */
    protected void getObdFuelMulti(List<T> trips, String deviceNum) {
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
}
