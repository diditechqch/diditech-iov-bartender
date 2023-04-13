package com.diditech.iov.gps.app.report.srv.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.app.device.repository.DeviceMapper;
import com.diditech.iov.gps.app.report.po.RptTrips;
import com.diditech.iov.gps.app.report.repository.RptMapper;
import com.diditech.iov.gps.app.report.srv.ReportGpsService;
import com.diditech.iov.gps.app.report.srv.ReportJobService;
import com.diditech.iov.gps.app.report.srv.ReportStopsService;
import com.diditech.iov.gps.app.report.srv.ReportTripsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * 报表定时任务服务-实体类
 * @author zhjd <br>
 * @date 2023/3/24 <br>
 */
@Slf4j
@Service
public class ReportJobServiceImpl implements ReportJobService {

    private final int cronJobDayRange = 2;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private RptMapper rptMapper;

    @Autowired
    @Qualifier("tripExecutor")
    private ExecutorService executor;

    @Autowired
    private ReportTripsService tripsService;

    @Autowired
    private ReportStopsService stopsService;

    @Autowired
    private ReportGpsService gpsService;

    @Override
    public void doCronJob() {
        List<String> devices =
//                Arrays.asList(
//                "14162036970", "868120236768377",
//                "868120293154065", "868120291579651", "18940712702","18940712702"
//        );
                deviceMapper.getDevicesByGpsTime(cronJobDayRange);
        if (CollUtil.isEmpty(devices)) {
            return;
        }
        Date end = DateUtil.beginOfDay(new Date());
        Date start = DateUtil.beginOfDay(DateUtil.offsetDay(end, -cronJobDayRange));

        // 使用线程池处理
        // 注：此处调用的批量反地理编码服务也存在线程池
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        devices.parallelStream()
                .forEach(device -> tasks.add(
                        CompletableFuture.runAsync(() -> doSave(device, start, end), executor)));
        tasks.parallelStream().map(CompletableFuture::join).count();
    }

    /**
     * 根据分段轨迹生成 行驶、停车报表，按设备管理事务
     * @date 2023/3/17
     * @author zhjd
     */
    @Transactional(transactionManager = "defaultSqlTransactionManager")
    public void doSave(String device, Date start, Date end) {
        try {
            List<RptTrips> trips = tripsService.buildRptTripListByDynamicTrip(device, start, end);
            if (CollUtil.isEmpty(trips)) {
                return;
            }
            RptTrips lastTrip = rptMapper.selectRptTripsBefore(device, start);
            // 支持重复执行
            if (lastTrip != null && !trips.get(0).getEndTime().after(lastTrip.getEndTime())) {
                List<RptTrips> duplicated = trips.stream()
                        .filter(item -> !item.getEndTime().after(lastTrip.getEndTime()))
                        .collect(Collectors.toList());
                trips.removeAll(duplicated);
            }
            boolean mergeLastTrip = false;
            if (lastTrip != null) {
                // 判断是否需要与历史分段合并
                mergeLastTrip = DateUtil.between(lastTrip.getEndTime(),
                        trips.get(0).getStartTime(), DateUnit.SECOND) <= ReportTripServiceBase.minNoDataDuration;
            }

            tripsService.saveTrip(trips, mergeLastTrip, lastTrip);
            stopsService.saveStops(trips, mergeLastTrip, lastTrip);
            gpsService.saveDayGps(trips, mergeLastTrip);
        } catch (Exception ex) {
            log.error(JSON.toJSONString(Arrays.asList(device, start, end)), ex);
        }
    }
}
