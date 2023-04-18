package com.diditech.iov.gps.app.task.job.impl;

import com.diditech.iov.gps.app.task.srv.ReportTripJobService;
import lombok.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 轨迹统计定时任务
 * @author zhjd <br>
 * @date 2023/3/8 <br>
 */
@Data
@Component
@ConfigurationProperties(prefix = "quartz.trip")
public class TripJobDetail extends AbstractJobDetail {
    private final String name = "trip";
    private String cron;
    private int batchThreadNum;

    public ExecutorService getExecutor() {
        return Executors.newFixedThreadPool(batchThreadNum);
    }

    @Autowired
    @Qualifier("tripJobService")
    private ReportTripJobService jobService;

    @Override
    public Class getJobClass() {
        return TripJob.class;
    }

    private class TripJob implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            jobService.doCronJob(getExecutor());
        }
    }
}
