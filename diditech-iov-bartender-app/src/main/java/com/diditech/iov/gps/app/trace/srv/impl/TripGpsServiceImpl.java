package com.diditech.iov.gps.app.trace.srv.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TripGps;
import com.diditech.iov.gps.app.trace.srv.TripServiceBase;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
@Slf4j
@Component
public class TripGpsServiceImpl extends TripServiceBase<TripGps> {

    private static class Holder {
        private static final TripGpsServiceImpl INSTANCE = new TripGpsServiceImpl();
    }

    public static TripGpsServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected List<TripGps> getTripInfo(String deviceNum,
                                        List<GpsInfoTripMin> traceList,
                                        Integer minNoDataDuration,
                                        Double minTripDistance) {
        log.debug("轨迹分段基于轨迹数量={}", traceList.size());
        List<TripGps> tripCalculates = calculateTrips(deviceNum, traceList, minNoDataDuration);
        handleTrips(tripCalculates, minTripDistance);
        return tripCalculates;
    }

    /**
     * 根据阈值对轨迹数据做切割
     * @date 2021/2/25
     * @author zhjd
     */
    private List<TripGps> calculateTrips(String deviceNum, List<GpsInfoTripMin> traceList, Integer minNoDataDuration) {
        // 过滤停车数据
        List<GpsInfoTripMin> drivingList = traceList.stream()
                .filter(item -> (item.getObdSpeed() != null && item.getObdSpeed() > 5) || item.getSpeed() > 5)
                .collect(Collectors.toList());
        List<List<GpsInfoTripMin>> tripList = splitTrips(drivingList, minNoDataDuration);
        return tripList.stream()
                .map(item -> buildTrip(item, deviceNum, TripGps.class))
                .collect(Collectors.toList());
    }

    /**
     * 根据阈值对轨迹数据做切割
     * @date 2021/2/25
     * @author zhjd
     */
    private List<List<GpsInfoTripMin>> splitTrips(List<GpsInfoTripMin> gpsList, Object... thresholds) {
        List<List<GpsInfoTripMin>> tripList = new ArrayList<>();
        int startIdx = 0;
        GpsInfoTripMin before;
        GpsInfoTripMin after;
        for (int i = 0; (startIdx < gpsList.size() - 1 && i < gpsList.size() - 1); i++) {
            before = gpsList.get(i);
            after = gpsList.get(i + 1);
            if (getDateBetween(before, after) >= Util.asInt(thresholds[0])) {
                // 无数据超过一段时间则分隔轨迹，此处由于长时间停车的数据也会一并处理
                tripList.add(gpsList.subList(startIdx, i + 1));
                startIdx = i + 1;
            }
        }
        if (startIdx < gpsList.size() - 1) {
            tripList.add(gpsList.subList(startIdx, gpsList.size()));
        }
        return tripList;
    }

    /**
     * 轨迹分段处理策略：过滤
     * @date 2021/2/20
     * @author zhjd
     */
    private void handleTrips(List<TripGps> list, Double minTripDistance) {
        Iterator<TripGps> it = list.iterator();
        TripGps s;
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

}
