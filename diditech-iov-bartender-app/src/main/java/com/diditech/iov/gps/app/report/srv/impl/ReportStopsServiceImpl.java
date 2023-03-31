package com.diditech.iov.gps.app.report.srv.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.report.domain.ReportStopData;
import com.diditech.iov.gps.api.report.domain.ReportTripsData;
import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.app.core.util.MapUtil;
import com.diditech.iov.gps.app.report.po.RptStops;
import com.diditech.iov.gps.app.report.po.RptStopsExample;
import com.diditech.iov.gps.app.report.po.RptTrips;
import com.diditech.iov.gps.app.report.repository.RptMapper;
import com.diditech.iov.gps.app.report.repository.RptStopsMapper;
import com.diditech.iov.gps.app.report.srv.ReportStopsService;
import com.diditech.iov.gps.app.report.srv.ReportTripsService;
import com.diditech.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 停留点服务-实体类
 * @author zhjd <br>
 * @date 2023/3/14 <br>
 */
@Slf4j
@Service
public class ReportStopsServiceImpl
        extends TripServiceBase<ReportStopData, RptStops>
        implements ReportStopsService {

    @Autowired
    private ReportTripsService tripsService;

    @Autowired
    private RptStopsMapper stopsMapper;

    @Autowired
    private RptMapper rptMapper;

    @Override
    public int saveStops(List<RptTrips> trips, boolean mergeLastTrip, RptTrips before) {
        if (!mergeLastTrip && before != null) {
            trips.add(before);
            trips.sort(Comparator.comparing(RptTrips::getStartTime));
        }
        if (CollUtil.isNotEmpty(trips) && trips.size() < 2) {
            return 0;
        }
        List<RptStops> stops = buildRptStops(trips);
        RptStopsExample example = new RptStopsExample();
        example.createCriteria()
                .andDeviceNumEqualTo(trips.get(0).getDeviceNum())
                .andStartTimeBetween(trips.get(0).getStartTime(), trips.get(trips.size() - 1).getStartTime());
        stopsMapper.deleteByExample(example);
        int result = rptMapper.batchInsertStops(stops);
        if (!mergeLastTrip && before != null) {
            // 恢复原数据，否则会影响里程统计
            trips.remove(before);
        }
        return result;
    }

    @Override
    protected Map<String, List<ReportStopData>> loadDynamicTrip(List<String> deviceNumList,
                                                                Date beginTime,
                                                                Date endTime,
                                                                String coorType,
                                                                Integer minDuration,
                                                                Double minDistance,
                                                                Map<String, List<ReportStopData>> historyMap) {

        // 查询最新分段动态数据
        Map<String, List<ReportTripsData>> tripMap
                = historyMap.keySet().stream()
                .map(historyMap::get)
                .map(list -> list.get(list.size() - 1))
                .map(data -> tripsService.loadDynamicTripByDevice(
                        data.getDeviceNum(), data.getEndTime(), endTime, coorType, null))
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(ReportTripsData::getDeviceNum));

        if (tripMap.isEmpty()) {
            // 处理当前为停车状态的设备数据
            return historyMap.keySet().stream()
                    .map(historyMap::get)
                    .map(list -> {
                        ReportStopData latest = list.get(list.size() - 1);
                        latest.setEndTime(new Date());
                        int duration = Util.asInt(DateUtil.between(latest.getStartTime(),
                                latest.getEndTime(), DateUnit.MINUTE));
                        latest.setDuration(duration);
                        return latest;
                    })
                    .collect(Collectors.groupingBy(ReportStopData::getDeviceNum));
        }

        Map<String, List<ReportStopData>> dtoMap = tripMap.keySet()
                .parallelStream()
                .map(tripMap::get)
                .map(tripDto -> buildReportDtoByTripDto(tripDto, minDuration))
                .filter(list -> !list.isEmpty())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(ReportStopData::getDeviceNum));

        MapUtil.mergeMap(dtoMap, historyMap);
        return historyMap;
    }

    @Override
    protected List<RptStops> selectEntity(List<String> deviceNumList,
                                          Date fromTime,
                                          Date toTime,
                                          Integer minDuration,
                                          Double minDistance) {
        RptStopsExample example = new RptStopsExample();
        example.createCriteria()
                .andDeviceNumIn(deviceNumList)
                .andStartTimeBetween(fromTime, toTime)
                .andDurationGreaterThanOrEqualTo(minDuration);
        return stopsMapper.selectByExample(example);
    }

    @Override
    protected ReportStopData buildReportDto(RptStops stopsData, String coorType) {
        ReportStopData data = new ReportStopData();
        BeanUtil.copyProperties(stopsData, data);
        if (StrUtil.equalsIgnoreCase(CoordinateType.BD09.name(), coorType)) {
            data.setLat(stopsData.getLatBd().doubleValue());
            data.setLng(stopsData.getLngBd().doubleValue());
        }
        if (StrUtil.equalsIgnoreCase(CoordinateType.GCJ02.name(), coorType)) {
            data.setLat(stopsData.getLatGc().doubleValue());
            data.setLng(stopsData.getLngGc().doubleValue());
        }
        return data;
    }

    /**
     * 构建停留点存储对象
     * @date 2023/3/24
     * @author zhjd
     */
    private List<RptStops> buildRptStops(List<RptTrips> trips) {
        trips.sort(Comparator.comparing(RptTrips::getStartTime));
        RptTrips before;
        RptTrips after;
        RptStops stops;
        List<RptStops> stopsList = new ArrayList<>();
        for (int i = 0; i < trips.size() - 1; i++) {
            before = trips.get(i);
            after = trips.get(i + 1);
            stops = new RptStops();
            BeanUtil.copyProperties(before, stops);
            stops.setAddress(before.getEndAddress());
            stops.setLat(before.getEndLat());
            stops.setLng(before.getEndLng());
            stops.setLatBd(before.getEndLatBd());
            stops.setLngBd(before.getEndLngBd());
            stops.setLatGc(before.getEndLatGc());
            stops.setLngGc(before.getEndLngGc());
            stops.setStartTime(before.getEndTime());
            stops.setEndTime(after.getStartTime());
            stops.setDuration(Util.asInt(DateUtil.between(stops.getStartTime(), stops.getEndTime(), DateUnit.MINUTE)));
            stopsList.add(stops);
        }
        if (stopsList.isEmpty()) {
            log.error("{}", JSON.toJSONString(trips));
        }
        return stopsList;
    }

    /**
     * 构建停留点查询对象
     * @date 2023/3/24
     * @author zhjd
     */
    private List<ReportStopData> buildReportDtoByTripDto(List<ReportTripsData> tripDto, Integer minDuration) {
        if (CollUtil.isEmpty(tripDto)) {
            return new ArrayList<>();
        }
        ReportTripsData before;
        Date parkEndTime;
        ReportStopData stop;
        List<ReportStopData> stopList = new ArrayList<>();
        for (int i = 0; i < tripDto.size(); i++) {
            before = tripDto.get(i);
            if (i == tripDto.size() - 1) {
                // 处理当前为停车状态的设备数据
                parkEndTime = new Date();
            } else {
                parkEndTime = tripDto.get(i + 1).getStartTime();
            }
            stop = new ReportStopData();
            BeanUtil.copyProperties(before, stop);
            stop.setAddress(before.getEndAddress());
            stop.setLat(before.getEndLat());
            stop.setLng(before.getEndLng());
            stop.setStartTime(before.getEndTime());
            stop.setEndTime(parkEndTime);
            stop.setDuration(Util.asInt(DateUtil.between(stop.getStartTime(), stop.getEndTime(), DateUnit.MINUTE)));
            stopList.add(stop);
        }
        List<ReportStopData> stopsAfterFilter = stopList.stream()
                .filter(item -> item.getDuration() >= minDuration)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(stopsAfterFilter)) {
            return new ArrayList<>();
        }
        stopsAfterFilter.sort(Comparator.comparing(ReportStopData::getStartTime));
        return stopsAfterFilter;
    }

}
