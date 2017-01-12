package trigger.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class JobSupport extends QuartzJobBean {
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		taskMethod(arg0);
	}

	protected abstract void taskMethod(JobExecutionContext arg0);
}
