package trigger.manager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

@Service("quartzTriggerManage")
public class QuartzTriggerManage {

    @Resource(name = "quartzScheduler")
    private Scheduler    scheduler;
    private final String quartzJobName = "schedulingJob";

    @SuppressWarnings("unused")
    @PostConstruct
    private void init() {

    }

    public void addTrigger(Trigger trigger) throws SchedulerException {
        JobDetail jobDetail = scheduler.getJobDetail(quartzJobName, Scheduler.DEFAULT_GROUP);
        if (null != jobDetail) {
            scheduler.addJob(jobDetail, true);
            trigger.setJobName(quartzJobName);
            scheduler.scheduleJob(trigger);
        }
    }

   
    public void addCronTrigger(CronTrigger cronTrigger) throws SchedulerException {
        addCronTrigger(Scheduler.DEFAULT_GROUP, cronTrigger);
    }

   
    public void addCronTrigger(String jobGrourpName, CronTrigger cronTrigger) throws SchedulerException {
        JobDetail jobDetail = scheduler.getJobDetail(quartzJobName, jobGrourpName);
        if (null != jobDetail) {
            scheduler.addJob(jobDetail, true);
            cronTrigger.setJobName(quartzJobName);
            scheduler.scheduleJob(cronTrigger);
        } else {
        }
    }

    
    public void addSimpleTrigger(SimpleTrigger simpleTrigger) throws SchedulerException {
        JobDetail jobDetail = scheduler.getJobDetail(quartzJobName, Scheduler.DEFAULT_GROUP);
        if (null != jobDetail) {
            scheduler.addJob(jobDetail, true);
            simpleTrigger.setJobName(quartzJobName);
            scheduler.scheduleJob(simpleTrigger);
        } else {
        }
    }

    public void deleteJob(String jobName, String groupName) throws SchedulerException {
        this.scheduler.deleteJob(jobName, groupName);
    }

    public String[] getJobNames(String groupName) throws SchedulerException {
        return null;
    }

    public String[] getJobNames() throws SchedulerException {
        return null;
    }

    public void pauseTrigger(String triggerName, String groupName) throws SchedulerException {
        scheduler.pauseTrigger(triggerName, groupName);
    }

    public void pauseTrigger(String triggerName) throws SchedulerException {
        pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);
    }

    public boolean removeTrigger(String triggerName, String groupName) throws SchedulerException {
        pauseTrigger(triggerName, groupName);
        return scheduler.unscheduleJob(triggerName, groupName);
    }

    public boolean removeTrigger(String triggerName) throws SchedulerException {
        return removeTrigger(triggerName, Scheduler.DEFAULT_GROUP);
    }

    public void resumeTrigger(String triggerName, String groupName) throws SchedulerException {
        this.scheduler.resumeTrigger(triggerName, groupName);
    }

    public void resumeTrigger(String triggerName) throws SchedulerException {
        resumeTrigger(triggerName, Scheduler.DEFAULT_GROUP);
    }

    public Trigger getTrigger(String triggerName, String group) throws SchedulerException {
        Trigger trigger = scheduler.getTrigger(triggerName, group);
        return trigger;
    }

    /**
     * @Description: 获取上次触发时间
     */
    public long getPrevFireTime(String triggerName) throws SchedulerException {
        try {
            Trigger trigger = getTrigger(triggerName, Scheduler.DEFAULT_GROUP);
            return trigger.getPreviousFireTime().getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * @Description: 获取下次触发时间
     */
    public long getNextFireTime(String triggerName) throws SchedulerException {
        try {
            Trigger trigger = getTrigger(triggerName, Scheduler.DEFAULT_GROUP);
            return trigger.getNextFireTime().getTime();
        } catch (Exception e) {
            return 0;
        }
    }

}
