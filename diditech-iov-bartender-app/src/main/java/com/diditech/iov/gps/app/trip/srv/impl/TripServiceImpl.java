package com.diditech.iov.gps.app.trip.srv.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TripGps;
import com.diditech.iov.gps.app.trip.srv.TripServiceBase;
import com.diditech.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
@Slf4j
@Component
public class TripServiceImpl extends TripServiceBase<TripGps> {

    private static class Holder {
        private static final TripServiceImpl INSTANCE = new TripServiceImpl();
    }

    public static TripServiceImpl getInstance() {
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
    private List<TripGps> calculateTrips(String deviceNum,
                                         List<GpsInfoTripMin> traceList,
                                         Integer minNoDataDuration) {
        // 1. 停车点去重
        List<GpsInfoTripMin> deduplicated = deduplicatedStopPoints(traceList);

        // 2. 分段：无数据超过一段时间则分隔轨迹
        List<List<GpsInfoTripMin>> tripList = splitTrips(deduplicated, minNoDataDuration);

        // 3. 处理分隔点之间距离问题
        List<List<GpsInfoTripMin>> gapFixed = fixTripGap(tripList);

        // 4. 构建分段对象
        return gapFixed.stream()
                .map(item -> buildTrip(item, deviceNum, TripGps.class))
                .collect(Collectors.toList());
    }

    /**
     * 处理连续停车且重复坐标的定位点，每组仅保留第一个和最后一个
     * @date 2023/5/12
     * @author zhjd
     */
    private List<GpsInfoTripMin> deduplicatedStopPoints(List<GpsInfoTripMin> traceList) {
        // 1. 查找连续停车且重复坐标的定位点，保留第一个和最后一个
        GpsInfoTripMin gpsFrom;
        GpsInfoTripMin gpsMid;
        GpsInfoTripMin gpsTo;
        int cursorFrom = 0;
        int cursorMid = cursorFrom + 1;
        int cursorTo = cursorMid + 1;
        List<GpsInfoTripMin> removeList = new ArrayList<>();
        while (cursorTo < traceList.size()) {
            gpsFrom = traceList.get(cursorFrom);
            gpsMid = traceList.get(cursorMid);
            gpsTo = traceList.get(cursorTo);
            if (isSameLocation(gpsFrom, gpsMid, gpsTo) && isNotMoving(gpsFrom, gpsMid, gpsTo)) {
                // 以第1个点为cursorFrom，找出其后2个连续的点，若3个点坐标相同，则删除中间的点
                removeList.add(gpsMid);
                cursorMid = cursorTo;
                cursorTo = cursorMid + 1;
                continue;
            }
            if (isSameLocation(gpsMid, gpsTo) && isNotMoving(gpsMid, gpsTo)) { // && isSameLocation(gpsFrom, gpsMid) != 0
                // 若相邻2个点坐标相同，则需继续与下一个点比较
                cursorFrom = cursorMid;
                cursorMid = cursorTo;
                cursorTo = cursorTo + 1;
                continue;
            }
            cursorFrom = cursorTo + 1;
            cursorMid = cursorFrom + 1;
            cursorTo = cursorMid + 1;
        }

        gpsFrom = traceList.get(0);
        gpsMid = traceList.get(1);
        if (isSameLocation(gpsFrom, gpsMid) && isNotMoving(gpsFrom, gpsMid)) { // && isSameLocation(gpsMid, cursorTo) != 0
            // 若第1和第2个点重复，仅保留一个
            removeList.add(gpsFrom);
        }

        // 2. 过滤重复坐标
        return traceList.stream()
                .filter(item -> !removeList.contains(item))
                .collect(Collectors.toList());
    }

    /**
     * 处理分段之间距离差问题
     * @date 2023/5/12
     * @author zhjd
     */
    private List<List<GpsInfoTripMin>> fixTripGap(List<List<GpsInfoTripMin>> tripLists) {
        List<GpsInfoTripMin> tripBf;
        List<GpsInfoTripMin> tripAf;
        GpsInfoTripMin gapLastPoint;
        GpsInfoTripMin gapNextPoint;
        GpsInfoTripMin newPoint;
        List<List<GpsInfoTripMin>> newTripLists = new ArrayList<>(tripLists.size());
        newTripLists.add(tripLists.get(0));
        List<GpsInfoTripMin> newTrip;
        for (int i = 0; i < tripLists.size() - 1; i++) {
            tripBf = tripLists.get(i);
            tripAf = tripLists.get(i + 1);
            gapLastPoint = tripBf.get(tripBf.size() - 1);
            gapNextPoint = tripAf.get(0);
            if (isSameLocation(gapLastPoint, gapNextPoint)) {
                newTripLists.add(tripAf);
                continue;
            }
            // 添加新定位点重用gapLastPoint的坐标，其他数据使用gapNextPoint，添加到tripAf首位
            newPoint = new GpsInfoTripMin();
            BeanUtil.copyProperties(gapNextPoint, newPoint);
            newPoint.setLng(gapLastPoint.getLng());
            newPoint.setLat(gapLastPoint.getLat());
            newPoint.setPrimaryLat(gapLastPoint.getPrimaryLat());
            newPoint.setPrimaryLng(gapLastPoint.getPrimaryLng());
            newTrip = new ArrayList<>();
            newTrip.add(newPoint);
            newTrip.addAll(tripAf);
            newTripLists.add(newTrip);
        }
        return newTripLists;
    }

    private boolean isSameLocation(GpsInfoTripMin... gpsInfoArray) {
        GpsInfoTripMin before;
        GpsInfoTripMin next;
        for (int i = 0; i < gpsInfoArray.length - 1; i++) {
            before = gpsInfoArray[i];
            next = gpsInfoArray[i + 1];
            if (before.getLat().doubleValue() != next.getLat().doubleValue()
                    || before.getLng().doubleValue() != next.getLng().doubleValue()) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotMoving(GpsInfoTripMin... gpsInfoArray) {
        return Arrays.stream(gpsInfoArray)
                .noneMatch(item -> item.getSpeed() >= 5);
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
                // 无数据超过一段时间则分隔轨迹，长时间停车的数据也属于该情况
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
