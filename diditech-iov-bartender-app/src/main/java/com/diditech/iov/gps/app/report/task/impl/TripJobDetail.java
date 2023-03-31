package com.diditech.iov.gps.app.report.task.impl;

import com.diditech.iov.gps.app.report.srv.ReportJobService;
import com.diditech.iov.gps.app.report.task.AbstractJobDetail;
import lombok.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
@ConfigurationProperties("quartz.trip")
public class TripJobDetail extends AbstractJobDetail {
    private final String name = "trip";
    private String cron;
    private int batchThreadNum;

    @Bean("tripExecutor")
    public ExecutorService getExecutor() {
        return Executors.newFixedThreadPool(batchThreadNum);
    }

    @Autowired
    private ReportJobService jobService;

    @Override
    public Class getJobClass() {
        return TripJob.class;
    }

    private class TripJob implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            jobService.doCronJob();
        }
    }
}
