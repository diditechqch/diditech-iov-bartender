package com.diditech.iov.gps.app.trip.srv.impl;

import cn.hutool.core.collection.CollUtil;
import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TripAcc;
import com.diditech.iov.gps.app.trip.srv.TripServiceBase;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
@Slf4j
@Component
public class AccServiceImpl extends TripServiceBase<TripAcc> {

    private static class Holder {
        private static final AccServiceImpl INSTANCE = new AccServiceImpl();
    }

    public static AccServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected List<TripAcc> getTripInfo(String deviceNum,
                                        List<GpsInfoTripMin> traceList,
                                        Integer minNoDataDuration,
                                        Double minTripDistance) {
        return getTripInfo(deviceNum, traceList, minTripDistance);
    }

    private List<TripAcc> getTripInfo(String deviceNum, List<GpsInfoTripMin> traceList, Double minTripDistance) {
        if (CollUtil.isEmpty(traceList)) {
            return new ArrayList<>();
        }
        List<TripAcc> tripAccList = calculateTrips(deviceNum, traceList);
        return tripAccList.stream()
                .filter(item -> item.getMileage().doubleValue() >= minTripDistance)
                .collect(Collectors.toList());
    }

    private List<TripAcc> calculateTrips(String deviceNum, List<GpsInfoTripMin> traceList) {
        List<List<GpsInfoTripMin>> tripList = splitTrips(traceList);
        return tripList.stream()
                .map(item -> buildTrip(item, deviceNum, TripAcc.class))
                .collect(Collectors.toList());
    }

    /**
     * 以点火为分段开始，以熄火为结束，若全程acc不变，则将所有查询所得轨迹视为一段
     * @date 2023/4/14
     * @author zhjd
     */
    private List<List<GpsInfoTripMin>> splitTrips(List<GpsInfoTripMin> gpsList) {
        List<GpsInfoTripMin> accGpsList = gpsList.stream()
                .filter(item -> item.getAcc() != null)
                .collect(Collectors.toList());

        List<List<GpsInfoTripMin>> tripList = new ArrayList<>();

        if (CollUtil.isEmpty(accGpsList)) {
            tripList.add(gpsList);
            return tripList;
        }

        GpsInfoTripMin before;
        GpsInfoTripMin after = null;
        int startIdx = 0;
        for (int i = 0; (startIdx < accGpsList.size() - 1 && i < accGpsList.size() - 1); i++) {
            before = accGpsList.get(i);
            after = accGpsList.get(i + 1);
            if (Util.asInt(before.getAcc()) != Util.asInt(after.getAcc())) {
                if (Util.asInt(after.getAcc()) > 0) {
                    startIdx = i + 1;
                    continue;
                }
                tripList.add(accGpsList.subList(startIdx, i + 1));
                startIdx = i + 1;
            }
        }
        if (startIdx < accGpsList.size() - 1 && after != null && Util.asInt(after.getAcc()) > 0) {
            tripList.add(accGpsList.subList(startIdx, accGpsList.size()));
        }

        if (CollUtil.isEmpty(tripList)) {
            tripList.add(gpsList);
        }
        return tripList;
    }
}
