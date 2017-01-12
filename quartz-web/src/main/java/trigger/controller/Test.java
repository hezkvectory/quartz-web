package trigger.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws ParseException, IOException {
		 CloseableHttpClient httpclient =   HttpClients.custom().build();  
		HttpPost method = new HttpPost("http://localhost:8080/quartz-web/test.do");

//		method.addHeader("Content-type", "application/json; charset=utf-8");
//		method.setHeader("Accept", "application/json");
		method.setEntity(new StringEntity("fdsfsdfsdfsdffffffffffffffffffffffffffffffffff", Charset.forName("UTF-8")));
		long startTime = System.currentTimeMillis();

		HttpResponse response = httpclient.execute(method);

		long endTime = System.currentTimeMillis();
		int statusCode = response.getStatusLine().getStatusCode();

		logger.info("statusCode:" + statusCode);
		logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
		if (statusCode != HttpStatus.SC_OK) {
			logger.error("Method failed:" + response.getStatusLine());
		}

		String body = EntityUtils.toString(response.getEntity());
		System.out.println(body);
	}
}
