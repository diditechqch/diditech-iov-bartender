package com.diditech.iov.gps.app.report.task;

import java.util.concurrent.ExecutorService;

/**
 * 报表定时任务服务-接口类
 * @author zhjd <br>
 * @date 2023/3/24 <br>
 */
public interface ReportTripJobService {
    void doCronJob(ExecutorService executor);
}
