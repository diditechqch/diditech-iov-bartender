package com.diditech.iov.gps.app.trace.srv.impl;

import cn.hutool.core.collection.CollUtil;
import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TripAcc;
import com.diditech.iov.gps.app.trace.srv.TripServiceBase;
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
public class TripAccServiceImpl extends TripServiceBase<TripAcc> {

    private static class Holder {
        private static final TripAccServiceImpl INSTANCE = new TripAccServiceImpl();
    }

    public static TripAccServiceImpl getInstance() {
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

    private List<List<GpsInfoTripMin>> splitTrips(List<GpsInfoTripMin> gpsList) {
        List<List<GpsInfoTripMin>> tripList = new ArrayList<>();
        GpsInfoTripMin before;
        GpsInfoTripMin after = null;
        int startIdx = 0;
        for (int i = 0; (startIdx < gpsList.size() - 1 && i < gpsList.size() - 1); i++) {
            before = gpsList.get(i);
            after = gpsList.get(i + 1);
            if (before.getAcc().intValue() != after.getAcc().intValue()) {
                if (after.getAcc().intValue() > 0) {
                    startIdx = i + 1;
                    continue;
                }
                tripList.add(gpsList.subList(startIdx, i + 1));
                startIdx = i + 1;
            }
        }
        if (startIdx < gpsList.size() - 1 && after != null && after.getAcc().intValue() > 0) {
            tripList.add(gpsList.subList(startIdx, gpsList.size()));
        }
        return tripList;
    }
}
