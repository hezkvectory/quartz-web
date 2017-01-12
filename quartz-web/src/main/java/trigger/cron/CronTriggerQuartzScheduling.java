package trigger.cron;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import trigger.job.CronTriggerQuartzJob;

public class CronTriggerQuartzScheduling {
	public static void main(String[] args) throws SchedulerException, ParseException {

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		JobDetail jobDetail = new JobDetail("CronTriggerQuartzJob",
				Scheduler.DEFAULT_GROUP, CronTriggerQuartzJob.class);

		CronTrigger cronTrigger = new CronTrigger("CronTrigger", Scheduler.DEFAULT_GROUP);
		//每3秒执行一次
		cronTrigger.setCronExpression("*/3 * * * * ?");
		scheduler.scheduleJob(jobDetail, cronTrigger);

		scheduler.start();
	
	}
}
