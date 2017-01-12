package trigger.simple;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import trigger.job.SimpleTriggerQuartzJob;

/**
 * Trigger(触发器)的责任就是触发一个 Job 去执行。<br/>
 * 当用 Scheduler 注册一个 Job 的时候要创建一个 Trigger 与这个 Job 相关联。<br/>
 * Quartz 提供了四种类型的 Trigger，但其中两种是最为常用的，分别是下面的两种： SimpleTrigger 和
 * CronTrigger.
 * 
 */
public class SimpleTriggerQuartzScheduling {
	public static void main(String[] args) throws Exception {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		JobDetail jobDetail = new JobDetail("SimpleTriggerQuartzJob",
				Scheduler.DEFAULT_GROUP, SimpleTriggerQuartzJob.class);

		SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger",
				Scheduler.DEFAULT_GROUP);

		/**
		 *  SimpleTrigger 是两个之中简单的那个，它主要用来激发单事件的 Job，
		 *  Trigger 在指定时间激发，并重复 n 次--两次激发时间之间的延时为 m，然后结束作业。
		 */
		simpleTrigger.setStartTime(new Date(System.currentTimeMillis()));
		simpleTrigger.setRepeatInterval(2000);//延时,相对于初始的次数
		simpleTrigger.setRepeatCount(3);//重复次数,本来执行一次，然后重复N次

		scheduler.scheduleJob(jobDetail, simpleTrigger);

		scheduler.start();
	}
}
