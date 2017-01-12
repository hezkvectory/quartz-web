package trigger.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import util.DateUtil;
import util.ThreadSleep;

public class CronTriggerQuartzJob implements Job{

	private static final Logger log = Logger.getLogger(CronTriggerQuartzJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("============start================" + DateUtil.getDateYYYYMMDDHHMMSS(new Date()));
		ThreadSleep.sleep(1);
		log.info("=============end==================" + DateUtil.getDateYYYYMMDDHHMMSS(new Date()));
	}

}
