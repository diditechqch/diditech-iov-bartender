package com.diditech.iov.gps.app.report.srv.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.report.domain.ReportTripsData;
import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.api.trace.entity.TripCalculate;
import com.diditech.iov.gps.app.report.po.RptTrips;
import com.diditech.iov.gps.app.report.po.RptTripsExample;
import com.diditech.iov.gps.app.report.repository.RptMapper;
import com.diditech.iov.gps.app.report.repository.RptTripsMapper;
import com.diditech.iov.gps.app.report.srv.ReportTripsService;
import com.diditech.iov.gps.app.trace.srv.TraceRequest;
import com.diditech.utils.Coordinate;
import com.diditech.utils.GISFixUtil;
import com.diditech.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 行程服务-实体类
 * @author zhjd <br>
 * @date 2023/3/14 <br>
 */
@Slf4j
@Service
public class ReportTripsServiceImpl
        extends TripServiceBase<ReportTripsData, RptTrips>
        implements ReportTripsService {

    @Autowired
    private RptTripsMapper tripMapper;

    @Autowired
    private RptMapper rptMapper;

    @Override
    public int saveTrip(List<RptTrips> trips, boolean mergeLastTrip, RptTrips before) {
        RptTrips mergedTrip = trips.get(0);
        if (mergeLastTrip && before != null) {
            log.debug("device num={}, merge data before={}, trip={}",
                    mergedTrip.getDeviceNum(), JSON.toJSONString(before), JSON.toJSONString(mergedTrip));
            before.setEndTime(mergedTrip.getEndTime());
            before.setEndLatGc(mergedTrip.getEndLatGc());
            before.setEndLngGc(mergedTrip.getEndLngGc());
            before.setEndLat(mergedTrip.getEndLat());
            before.setEndLng(mergedTrip.getEndLng());
            before.setEndLatBd(mergedTrip.getEndLatBd());
            before.setEndLngBd(mergedTrip.getEndLngBd());
            before.setEndAddress(mergedTrip.getEndAddress());
            before.setDistance(before.getDistance().add(mergedTrip.getDistance()));
            before.setFuelConsumption(before.getFuelConsumption() + mergedTrip.getFuelConsumption());
            long duration = DateUtil.between(before.getStartTime(), mergedTrip.getEndTime(), DateUnit.MINUTE);
            BigDecimal aveSpeed = before.getDistance()
                    .divide(BigDecimal.valueOf(duration / 60D), 0, BigDecimal.ROUND_HALF_UP);
            before.setDuration(Util.asInt(duration));
            before.setSpeedAve(aveSpeed.shortValue());
            before.setSpeedMax(NumberUtil.max(before.getSpeedMax(), mergedTrip.getSpeedMax()));
            trips.remove(0);
            // 不需要移除该条数据，不影响停车数据，且里程计算需要使用
            trips.add(before);
        }
        trips.sort(Comparator.comparing(RptTrips::getStartTime));
        RptTripsExample example = new RptTripsExample();
        example.createCriteria()
                .andDeviceNumEqualTo(mergedTrip.getDeviceNum())
                .andStartTimeBetween(trips.get(0).getStartTime(), trips.get(trips.size() - 1).getStartTime());
        tripMapper.deleteByExample(example);
        return rptMapper.batchInsertTrips(trips);
    }

    @Override
    public List<RptTrips> buildRptTripListByDynamicTrip(String deviceNum, Date start, Date end) {
        List<TripCalculate> tripCalculates = TraceRequest.Builder
                .create(deviceNum, start, end)
                .getTrips();
        if (CollUtil.isEmpty(tripCalculates)) {
            return new ArrayList<>();
        }
        return tripCalculates.stream()
                .map(this::buildRptTripByDynamicTrip)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportTripsData> loadDynamicTripByDevice(String deviceNum,
                                                         Date beginTime,
                                                         Date endTime,
                                                         String coorType,
                                                         List<ReportTripsData> historyList) {
        List<ReportTripsData> current =
                buildReportDtoByDynamicTrip(deviceNum, beginTime, endTime, coorType);
        if (CollUtil.isEmpty(historyList)) {
            return current;
        }
        if (mergeReportDto(historyList.get(historyList.size() - 1), current.get(0))) {
            return current.subList(1, current.size());
        }
        return current;
    }

    @Override
    public RptTrips rebuildRptTripByTime(RptTrips rptTrip) {
        List<TripCalculate> tripCalculates = TraceRequest.Builder
                .create(rptTrip.getDeviceNum(), rptTrip.getStartTime(), rptTrip.getEndTime())
                .minTripDistance(0D)
                .getTrips();
        if (CollUtil.isEmpty(tripCalculates)) {
            log.error("ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        }
        List<RptTrips> trips = tripCalculates.stream()
                .map(this::buildRptTripByDynamicTrip)
                .collect(Collectors.toList());
        if (trips.size() > 1) {
            log.error("ERRORRRRRRRRRRRRRRRRRRRRRRRRRR");
        }
        return trips.get(0);
    }

    @Override
    public Map<String, List<ReportTripsData>> loadDynamicTrip(List<String> deviceNumList,
                                                              Date beginTime,
                                                              Date endTime,
                                                              String coorType,
                                                              Integer minDuration,
                                                              Double minDistance,
                                                              Map<String, List<ReportTripsData>> historyMap) {
        return deviceNumList.parallelStream()
                .map(deviceNum -> loadDynamicTripByDevice(deviceNum,
                        beginTime, endTime, coorType, historyMap.get(deviceNum)))
                .flatMap(Collection::stream)
                .filter(item -> item.getDuration() >= minDuration
                        && item.getDistance() >= minDistance)
                .collect(Collectors.groupingBy(ReportTripsData::getDeviceNum));
    }

    @Override
    public List<RptTrips> selectEntity(List<String> deviceNumList,
                                       Date fromTime,
                                       Date toTime,
                                       Integer minDuration,
                                       Double minDistance) {
        RptTripsExample example = new RptTripsExample();
        example.createCriteria()
                .andDeviceNumIn(deviceNumList)
                .andStartTimeBetween(fromTime, toTime)
                .andDurationGreaterThanOrEqualTo(minDuration)
                .andDistanceGreaterThanOrEqualTo(BigDecimal.valueOf(minDistance));
        return tripMapper.selectByExample(example);
    }

    @Override
    protected ReportTripsData buildReportDto(RptTrips rptTrip, String coorType) {
        ReportTripsData data = new ReportTripsData();
        BeanUtil.copyProperties(rptTrip, data);
        if (StrUtil.equalsIgnoreCase(CoordinateType.BD09.name(), coorType)) {
            data.setStartLat(rptTrip.getStartLatBd().doubleValue());
            data.setStartLng(rptTrip.getStartLngBd().doubleValue());
            data.setEndLat(rptTrip.getEndLatBd().doubleValue());
            data.setEndLng(rptTrip.getEndLngBd().doubleValue());
        }
        if (StrUtil.equalsIgnoreCase(CoordinateType.GCJ02.name(), coorType)) {
            data.setStartLat(rptTrip.getStartLatGc().doubleValue());
            data.setStartLng(rptTrip.getStartLngGc().doubleValue());
            data.setEndLat(rptTrip.getEndLatGc().doubleValue());
            data.setEndLng(rptTrip.getEndLngGc().doubleValue());
        }
        data.setDuration(DateUtil.between(rptTrip.getStartTime(), rptTrip.getEndTime(), DateUnit.MINUTE));
        return data;
    }

    /**
     * 根据轨迹分段动态数据生成行程报表数据（数据库对象）
     * @date 2023/3/23
     * @author zhjd
     */
    private RptTrips buildRptTripByDynamicTrip(TripCalculate entity) {
        RptTrips rpt = new RptTrips();
        BeanUtil.copyProperties(entity, rpt);
        rpt.setDayTag(DateUtil.format(entity.getStartTime(), Util.STR_DATE_FORMAT));
        rpt.setDeviceNum(entity.getDeviceNum());
        rpt.setDistance(entity.getMileage());
        rpt.setDuration(Util.asInt(DateUtil.between(entity.getStartTime(), entity.getEndTime(), DateUnit.MINUTE)));
        rpt.setEndLatBd(BigDecimal.valueOf(entity.getEndLat()));
        rpt.setEndLngBd(BigDecimal.valueOf(entity.getEndLng()));
        Coordinate endBd = new Coordinate(entity.getEndLat(), entity.getEndLng());
        Coordinate endGc = GISFixUtil.convertBD09ToGCJ02(endBd);
        Coordinate endO = GISFixUtil.unfixCoordinate(endGc);
        rpt.setEndLatGc(BigDecimal.valueOf(endGc.lat));
        rpt.setEndLngGc(BigDecimal.valueOf(endGc.lng));
        rpt.setEndLat(BigDecimal.valueOf(endO.lat));
        rpt.setEndLng(BigDecimal.valueOf(endO.lng));
        rpt.setSpeedAve(entity.getAveSpeed().shortValue());
        rpt.setSpeedMax(entity.getMaxSpeed().shortValue());
        rpt.setStartLatBd(BigDecimal.valueOf(entity.getStartLat()));
        rpt.setStartLngBd(BigDecimal.valueOf(entity.getStartLng()));
        Coordinate startBd = new Coordinate(entity.getStartLat(), entity.getStartLng());
        Coordinate startGc = GISFixUtil.convertBD09ToGCJ02(startBd);
        Coordinate startO = GISFixUtil.unfixCoordinate(startGc);
        rpt.setStartLatGc(BigDecimal.valueOf(startGc.lat));
        rpt.setStartLngGc(BigDecimal.valueOf(startGc.lng));
        rpt.setStartLat(BigDecimal.valueOf(startO.lat));
        rpt.setStartLng(BigDecimal.valueOf(startO.lng));
        return rpt;
    }

    /**
     * 根据动态轨迹分段生成查询对象
     * @date 2023/3/24
     * @author zhjd
     */
    private List<ReportTripsData> buildReportDtoByDynamicTrip(String deviceNum,
                                                              Date start,
                                                              Date end,
                                                              String coorType) {
        List<TripCalculate> tripCalculates = TraceRequest.Builder
                .create(deviceNum, start, end)
                .coorType(coorType)
                .getTrips();
        if (CollUtil.isEmpty(tripCalculates)) {
            return new ArrayList<>();
        }

        return tripCalculates.stream()
                .map(item -> {
                    ReportTripsData data = new ReportTripsData();
                    BeanUtil.copyProperties(item, data);
                    data.setDeviceNum(deviceNum);
                    data.setDistance(item.getMileage().doubleValue());
                    data.setDuration(DateUtil.between(item.getStartTime(), item.getEndTime(), DateUnit.MINUTE));
                    data.setSpeedMax(item.getMaxSpeed().intValue());
                    data.setSpeedAve(item.getAveSpeed().intValue());
                    return data;
                })
                .collect(Collectors.toList());
    }

    /**
     * 两个分段合并一个
     * @return boolean true合并成功 false未合并
     * @date 2023/3/24
     * @author zhjd
     */
    private boolean mergeReportDto(ReportTripsData before, ReportTripsData after) {
        boolean needMergeBefore = DateUtil.between(before.getEndTime(),
                after.getStartTime(), DateUnit.SECOND) <= minNoDataDuration;
        if (needMergeBefore) {
            before.setEndTime(after.getEndTime());
            before.setEndLat(after.getEndLat());
            before.setEndLng(after.getEndLng());
            before.setEndAddress(after.getEndAddress());
            before.setDistance(before.getDistance() + after.getDistance());
            before.setFuelConsumption(before.getFuelConsumption() + after.getFuelConsumption());
            long duration = DateUtil.between(before.getStartTime(), after.getEndTime(), DateUnit.MINUTE);
            BigDecimal aveSpeed = BigDecimal.valueOf(before.getDistance())
                    .divide(BigDecimal.valueOf(duration / 60D), 0, BigDecimal.ROUND_HALF_UP);
            before.setSpeedAve(aveSpeed.shortValue());
            before.setSpeedMax(NumberUtil.max(before.getSpeedMax(), after.getSpeedMax()));
            before.setDuration(duration);
            return true;
        }
        return false;
    }

}
