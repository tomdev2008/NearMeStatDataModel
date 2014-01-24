package com.nearme.statistics.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 短信工具类
 * 
 * @author 刘超
 * @version 1.0
 * @since 1.0,2012-12-7
 */
public class SMSUtil {

	public static final String SMS_SERVICE_URL = "http://60.12.231.66:91/smscenter/GiveAnAlarm";

	// 发送短信通知
	public static final String contactList = new StringBuilder("").append(
			"13410898177,")// 刘超
			.append("15920013045,")// 丰光贤
			.append("13823378597,")// 范海强
			.append("15014038713,")// 林逸聪
			.append("15914182515,")// 吴俊杰
			.append("18617104512,")// 丁骥
			.append("18823382465,")// 朱峰
			.append("15815568160,")// 黄科
			.append("13430955516")// 罗海江
			.toString();
	
	public static void sendSMS(Map<String, String> contacts, String content) {
		try {
			Iterator<String> it = contacts.keySet().iterator();
			StringBuilder sb = new StringBuilder(256);
			while (it.hasNext()) {
				String key = it.next();
				sb.append(contacts.get(key));
				sb.append(",");
			}

			PostMethod post = new PostMethod(SMS_SERVICE_URL);
			post.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

			NameValuePair[] params = new NameValuePair[2];
			params[0] = new NameValuePair("mobiles", sb.toString());
			params[1] = new NameValuePair("content", content);
			post.setRequestBody(params);

			HttpClient httpclient = new HttpClient();

			int result = httpclient.executeMethod(post);

			if (result == 200) {
				System.out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendSMS(String contacts, String content) {
		try {
			PostMethod post = new PostMethod(SMS_SERVICE_URL);
			post.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

			NameValuePair[] params = new NameValuePair[2];
			params[0] = new NameValuePair("mobiles", contacts);
			params[1] = new NameValuePair("content", content);
			post.setRequestBody(params);

			HttpClient httpclient = new HttpClient();

			int result = httpclient.executeMethod(post);

			if (result == 200) {
				System.out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readResponse(InputStream in) throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void main(String[] args) {
		sendSMS(contactList, "Hello World!你好！");
	}
}
