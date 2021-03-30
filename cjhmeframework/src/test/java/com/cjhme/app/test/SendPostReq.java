package com.cjhme.app.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <p>
 * Title: SendPostReq.java
 * </p>
 * Description:
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Jun 20, 2015 9:59:41 AM
 */
public class SendPostReq {


	/**
	 * ��ָ��URL����POST����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ��������������Ӧ����name1=value1&name2=value2����ʽ��
	 * @return URL����Զ����Դ����Ӧ
	 */
	public static String sendPost(String url, String param) {
		//PrintWriter out = null;
		OutputStream out=null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;
			// MSIE 5.0; Windows NT; DigExt)");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "html/json,charset=utf-8");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			//out = new PrintWriter(conn.getOutputStream());
			out = new DataOutputStream(conn.getOutputStream());
			// �����������
			out.write(param.getBytes("utf-8"));
			//out.print(param);
			
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����POST��������쳣��" + e.getMessage());
			return e.getMessage();
			// e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public String sendPost(String url, String content, Map<String, String> head) {

		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
			httpURLConnection.setRequestProperty("accept", "*/*");
			httpURLConnection.setRequestProperty("connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Content-Length", String.valueOf(content.length()));
			if (head != null) {
				for (String key : head.keySet()) {
					httpURLConnection.setRequestProperty(key, head.get(key));
				}
			}
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(content.getBytes("UTF-8"));
			out.flush();

			if (httpURLConnection.getResponseCode() == 200) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
				StringBuilder returnStr = new StringBuilder();
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					returnStr.append(line);
				}
				bufferedReader.close();
				return returnStr.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
