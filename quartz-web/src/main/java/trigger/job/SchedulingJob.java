package trigger.job;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.DateUtil;


public class SchedulingJob extends JobSupport {

    private static final Logger log = LoggerFactory.getLogger(SchedulingJob.class);

    @Override
    protected void taskMethod(JobExecutionContext arg0) {
        Trigger cronTrigger = arg0.getTrigger();
        JobDataMap jobDataMap = cronTrigger.getJobDataMap();
        Scheduler scheduler = arg0.getScheduler();
        SchedulerContext skedCtx = null;
        try {
            skedCtx = scheduler.getContext();
            log.info(DateUtil.getDateYYYYMMDDHHMMSS(new Date()) + ":::" + jobDataMap.get("key") + ":" + cronTrigger.getName() + ":" + cronTrigger.getGroup());
        } catch (SchedulerException e) {
            log.error("调度触发异常！", e);
        }
    }
}
