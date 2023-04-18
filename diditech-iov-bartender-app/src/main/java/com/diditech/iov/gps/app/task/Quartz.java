package com.diditech.iov.gps.app.task;

import com.diditech.iov.gps.app.task.job.JobDetailI;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author zhjd <br>
 * @date 2023/3/9 <br>
 */
@Configuration
public class Quartz {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Autowired
    private List<JobDetailI> jobDetails;

    @Profile({"prod", "test"})
    @Bean
    public Scheduler configScheduler() {
        Scheduler scheduler = schedulerFactory.getScheduler();

        jobDetails.forEach(item -> {
            try {
                scheduler.scheduleJob(item.buildJobDetail(), item.buildTrigger());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        });

        return scheduler;
    }

    @Profile("dev")
    @Bean
    public Scheduler configScheduler_() {
        Scheduler scheduler = schedulerFactory.getScheduler();

        jobDetails.forEach(item -> {
            try {
                scheduler.scheduleJob(item.buildJobDetail(), newTrigger().startNow().build());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        });

        return scheduler;
    }
}
