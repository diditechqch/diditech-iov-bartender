package com.diditech.iov.gps.app.report.srv.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.diditech.iov.gps.api.report.domain.ReportAccData;
import com.diditech.iov.gps.api.report.domain.ReportTripsData;
import com.diditech.iov.gps.api.trace.entity.TripAcc;
import com.diditech.iov.gps.api.trace.entity.TripGps;
import com.diditech.iov.gps.app.report.po.RptAcc;
import com.diditech.iov.gps.app.report.po.RptAccExample;
import com.diditech.iov.gps.app.report.po.RptTrips;
import com.diditech.iov.gps.app.report.repository.RptAccMapper;
import com.diditech.iov.gps.app.report.repository.RptMapper;
import com.diditech.iov.gps.app.report.srv.ReportAccService;
import com.diditech.iov.gps.app.report.srv.ReportTripsService;
import com.diditech.iov.gps.app.trace.srv.TraceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 点火报表服务-实体类
 * @author zhjd <br>
 * @date 2023/4/14 <br>
 */
@Slf4j
@Service
public class ReportAccServiceImpl
        extends ReportTripServiceBase<ReportAccData, RptAcc>
        implements ReportAccService {

    @Autowired
    private ReportTripsService tripsService;

    @Autowired
    private RptAccMapper accMapper;

    @Autowired
    private RptMapper rptMapper;

    @Override
    public List<RptAcc> buildRptTripListByDynamicTrip(String device, Date start, Date end) {
        List<TripAcc> trips = TraceRequest.Builder
                .create(device, start, end)
                .getAcc();
        if (CollUtil.isEmpty(trips)) {
            return new ArrayList<>();
        }
        return trips.stream()
                .map(this::buildRptTripByDynamicTrip)
                .collect(Collectors.toList());
    }

    @Override
    public List<RptAcc> setBeforeData(List<RptAcc> list, boolean mergeLastData, RptAcc before) {
        List<RptTrips> trips = tripsService.setBeforeData(list.stream()
                .map(this::convertRptTrip)
                .collect(Collectors.toList()), mergeLastData, before);
        return trips.stream()
                .map(this::convertRptAcc)
                .collect(Collectors.toList());
    }

    @Override
    public int saveReportData(List<RptAcc> list) {
        RptAccExample example = new RptAccExample();
        example.createCriteria()
                .andDeviceNumEqualTo(list.get(0).getDeviceNum())
                .andStartTimeBetween(list.get(0).getStartTime(), list.get(list.size() - 1).getStartTime());
        accMapper.deleteByExample(example);
        return rptMapper.batchInsertAcc(list);
    }

    @Override
    protected Map<String, List<ReportAccData>> loadDynamicReport(List<String> deviceNumList,
                                                                 Date beginTime,
                                                                 Date endTime,
                                                                 String coorType,
                                                                 Integer minDuration,
                                                                 Double minDistance,
                                                                 Map<String, List<ReportAccData>> historyMap) {
        return deviceNumList.parallelStream()
                .map(deviceNum -> loadDynamicAccByDevice(deviceNum,
                        beginTime, endTime, coorType, historyMap.get(deviceNum)))
                .flatMap(Collection::stream)
                .filter(item -> item.getDuration() >= minDuration
                        && item.getDistance() >= minDistance)
                .collect(Collectors.groupingBy(ReportAccData::getDeviceNum));
    }

    private List<ReportAccData> loadDynamicAccByDevice(String deviceNum,
                                                       Date beginTime,
                                                       Date endTime,
                                                       String coorType,
                                                       List<ReportAccData> historyList) {
        List<ReportAccData> current =
                buildReportDtoByDynamicAcc(deviceNum, beginTime, endTime, coorType);
        if (CollUtil.isEmpty(historyList)) {
            return current;
        }
        if (mergeReportDto(historyList.get(historyList.size() - 1), current.get(0))) {
            return current.subList(1, current.size());
        }
        return current;
    }

    private List<ReportAccData> buildReportDtoByDynamicAcc(String deviceNum,
                                                           Date start,
                                                           Date end,
                                                           String coorType) {
        List<TripAcc> accList = TraceRequest.Builder
                .create(deviceNum, start, end)
                .coorType(coorType)
                .getAcc();
        if (CollUtil.isEmpty(accList)) {
            return new ArrayList<>();
        }

        return accList.stream()
                .map(item -> {
                    ReportAccData data = new ReportAccData();
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
    private boolean mergeReportDto(ReportAccData before, ReportAccData after) {
        ReportTripsData beforeTrip = convertDtoTrip(before);
        ReportTripsData afterTrip = convertDtoTrip(after);
        boolean result = tripsService.mergeReportDto(beforeTrip, afterTrip);
        // TODO test
        BeanUtil.copyProperties(beforeTrip, before);
        return result;
    }

    @Override
    protected List<RptAcc> selectEntity(List<String> deviceNumList,
                                        Date fromTime,
                                        Date toTime,
                                        Integer minDuration,
                                        Double minDistance) {
        RptAccExample example = new RptAccExample();
        example.createCriteria()
                .andDeviceNumIn(deviceNumList)
                .andStartTimeBetween(fromTime, toTime)
                .andDurationGreaterThanOrEqualTo(minDuration)
                .andDistanceGreaterThanOrEqualTo(BigDecimal.valueOf(minDistance));
        return accMapper.selectByExample(example);
    }

    @Override
    protected ReportAccData buildReportDto(RptAcc acc, String coorType) {
        RptTrips trips = convertRptTrip(acc);
        ReportTripsData tripsData = tripsService.buildReportDto(trips, coorType);
        return convertDtoAcc(tripsData);
    }

    /**
     * 根据轨迹分段动态数据生成行程报表数据（数据库对象）
     * @date 2023/4/13
     * @author zhjd
     */
    protected RptAcc buildRptTripByDynamicTrip(TripAcc entity) {
        TripGps gps = new TripGps();
        BeanUtil.copyProperties(entity, gps);
        RptTrips rptTrips = tripsService.buildRptTripByDynamicTrip(gps);
        RptAcc rptAcc = new RptAcc();
        BeanUtil.copyProperties(rptTrips, rptAcc);
        return rptAcc;
    }

    private RptTrips convertRptTrip(RptAcc acc) {
        if (acc == null) {
            return null;
        }
        RptTrips trips = new RptTrips();
        BeanUtil.copyProperties(acc, trips);
        return trips;
    }

    private RptAcc convertRptAcc(RptTrips trip) {
        if (trip == null) {
            return null;
        }
        RptAcc acc = new RptAcc();
        BeanUtil.copyProperties(trip, acc);
        return acc;
    }

    private ReportTripsData convertDtoTrip(ReportAccData acc) {
        if (acc == null) {
            return null;
        }
        ReportTripsData trips = new ReportTripsData();
        BeanUtil.copyProperties(acc, trips);
        return trips;
    }

    private ReportAccData convertDtoAcc(ReportTripsData trip) {
        if (trip == null) {
            return null;
        }
        ReportAccData acc = new ReportAccData();
        BeanUtil.copyProperties(trip, acc);
        return acc;
    }
}
