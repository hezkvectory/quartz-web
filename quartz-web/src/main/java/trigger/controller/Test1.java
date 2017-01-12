package trigger.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test1 {

	public static void main(String[] args) {
		try {
			String spec = "http://localhost:8080/quartz-web/test.do";
			URL url = new URL(spec);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("content-type", "text/html");

			conn.connect();// 握手
			OutputStream os = conn.getOutputStream();// 拿到输出流
			PrintWriter out = new PrintWriter(os);
			out.print("============这是测试输入流================================================");

			out.flush();
			os.flush();
			out.close();
			os.close();

			InputStream is = conn.getInputStream();// 拿到输入流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			System.out.println(s);

			br.close();
			isr.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
