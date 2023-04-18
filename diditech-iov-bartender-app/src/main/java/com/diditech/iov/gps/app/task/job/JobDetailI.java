package com.diditech.iov.gps.app.task.job;

import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * @author zhjd <br>
 * @date 2023/3/9 <br>
 */
public interface JobDetailI {

    JobDetail buildJobDetail();

    Trigger buildTrigger();
}
