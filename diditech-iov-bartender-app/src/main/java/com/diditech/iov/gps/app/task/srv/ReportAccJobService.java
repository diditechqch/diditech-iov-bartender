package com.diditech.iov.gps.app.task.srv;

import java.util.concurrent.ExecutorService;

/**
 * ACC分段报表定时任务服务-接口类
 * @author zhjd <br>
 * @date 2023/4/14 <br>
 */
public interface ReportAccJobService {
    void doCronJob(ExecutorService executor);
}
