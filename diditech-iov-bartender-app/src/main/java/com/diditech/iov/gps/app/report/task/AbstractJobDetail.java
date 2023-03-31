package com.diditech.iov.gps.app.report.task;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author zhjd <br>
 * @date 2023/3/9 <br>
 */
public abstract class AbstractJobDetail implements JobDetailI {

    protected abstract Class<? extends Job> getJobClass();

    protected abstract String getName();

    protected abstract String getCron();

    private String getGroup() {
        return "group";
    }

    @Override
    public JobDetail buildJobDetail() {
        return newJob(getJobClass())
                .withIdentity(getName(), getGroup())
                .build();
    }

    @Override
    public Trigger buildTrigger() {
        return newTrigger()
                .withIdentity(getName(), getGroup())
                .withSchedule(cronSchedule(getCron()))
                .build();
    }


}
