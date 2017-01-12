package trigger.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import trigger.manager.QuartzTriggerManage;

@Controller
@RequestMapping("/")
public class IndexController {
	@Resource(name = "quartzTriggerManage")
	private QuartzTriggerManage quartzTriggerManage;
	private static final Logger log = LoggerFactory
			.getLogger(IndexController.class);

	@RequestMapping("/index")
	public String toIndex() {
		return "index";
	}

	@RequestMapping("/delete")
	public String delete(String name, String group) throws SchedulerException {
		log.info(name + ":" + group);
		quartzTriggerManage.removeTrigger(name, group);
		return "redirect:index.do";
	}

	@RequestMapping("/add")
	public String add(String trigger, String name, String group, String param)
			throws SchedulerException, ParseException {
		log.info(trigger);
		CronTrigger cronTrigger = new CronTrigger(name, group);
		cronTrigger.setCronExpression(trigger);
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("key", param);
		cronTrigger.setJobDataMap(jobDataMap);
		quartzTriggerManage.addCronTrigger(cronTrigger);
		quartzTriggerManage.resumeTrigger(name, group);
		return "redirect:index.do";
	}

	@ResponseBody
	@RequestMapping(value = "/test")
	public Map<String, Object> test(HttpServletRequest request)
			throws SchedulerException, IOException {

		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("k1", "v1");
		return map;
	}
}
