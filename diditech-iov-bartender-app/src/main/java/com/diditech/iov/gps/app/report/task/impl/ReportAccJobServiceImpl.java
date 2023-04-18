package com.diditech.iov.gps.app.report.task.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.app.report.po.RptAcc;
import com.diditech.iov.gps.app.report.repository.RptMapper;
import com.diditech.iov.gps.app.report.srv.ReportAccService;
import com.diditech.iov.gps.app.report.srv.impl.ReportTripServiceBase;
import com.diditech.iov.gps.app.report.task.ReportAccJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * ACC分段报表定时任务服务-实体类
 * @author zhjd <br>
 * @date 2023/4/14 <br>
 */
@Slf4j
@Service
public class ReportAccJobServiceImpl extends ReportTripJobServiceImpl implements ReportAccJobService {

    @Autowired
    private RptMapper rptMapper;

    @Autowired
    @Qualifier("accExecutor")
    private ExecutorService executor;

    @Autowired
    private ReportAccService accService;

    @Override
    protected Executor getExecutor() {
        return executor;
    }

    /**
     * 根据分段轨迹生成 行驶、停车报表，按设备管理事务
     * @date 2023/3/17
     * @author zhjd
     */
    @Transactional(transactionManager = "defaultSqlTransactionManager")
    public void doSave(String device, Date start, Date end) {
        try {
            List<RptAcc> acc = accService.buildRptTripListByDynamicTrip(device, start, end);
            if (CollUtil.isEmpty(acc)) {
                return;
            }
            RptAcc lastAcc = rptMapper.selectRptAccBefore(device, start);
            // 支持重复执行
            if (lastAcc != null && !acc.get(0).getEndTime().after(lastAcc.getEndTime())) {
                List<RptAcc> duplicated = acc.stream()
                        .filter(item -> !item.getEndTime().after(lastAcc.getEndTime()))
                        .collect(Collectors.toList());
                acc.removeAll(duplicated);
            }
            boolean mergeLastTrip = false;
            if (lastAcc != null) {
                // 判断是否需要与历史分段合并
                mergeLastTrip = DateUtil.between(lastAcc.getEndTime(),
                        acc.get(0).getStartTime(), DateUnit.SECOND) <= ReportTripServiceBase.minNoDataDuration;
            }

            accService.saveReport(acc, mergeLastTrip, lastAcc);
        } catch (Exception ex) {
            log.error(JSON.toJSONString(Arrays.asList(device, start, end)), ex);
        }
    }
}
