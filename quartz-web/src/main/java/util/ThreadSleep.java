package util;

import org.apache.log4j.Logger;

public class ThreadSleep {
	private static final Logger log = Logger.getLogger(ThreadSleep.class);
	
	public static void sleep(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error("sleep error", e);
		}
	}

}
