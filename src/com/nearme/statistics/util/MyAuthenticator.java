package com.nearme.statistics.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

	private static List<String> buildToAddresses() {
		List<String> address = new ArrayList<String>();
		address.add("330169742@qq.com");
		address.add("278477155@qq.com");
		address.add("17441413@qq.com");
		address.add("309726300@qq.com");
		address.add("1031820815@qq.com");
		address.add("982523212@qq.com");
		address.add("275463822@qq.com");
		address.add("375211301@qq.com");
		address.add("530401092@qq.com");
		address.add("421798595@qq.com");
		return address;
	}

	public static void sendMail(String subject, String content) {
		// 这个类主要是设置邮件
		MailSenderInfoBean mailInfo = new MailSenderInfoBean();
		mailInfo.setMailServerHost("mail9988.oppo.com");
		mailInfo.setMailServerPort("9988");
		mailInfo.setValidate(true);
		mailInfo.setUserName("keke_alert");
		mailInfo.setPassword("oppo*SZ999@");// 您的邮箱密码
		mailInfo.setFromAddress("keke_alert@oppo.com");
		List<String> address = buildToAddresses();
		mailInfo.setToAddress(address);
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		// List<String> attachFileNames = new ArrayList<String>();
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		try {
			// 发送文体格式
			// sms.sendTextMail(mailInfo);
			// 发送HTML格式
			sms.sendHtmlMail(mailInfo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void sendMail(String subject, String feedback, String content) {
		// 这个类主要是设置邮件
		MailSenderInfoBean mailInfo = new MailSenderInfoBean();
		mailInfo.setMailServerHost("mail9988.oppo.com");
		mailInfo.setMailServerPort("9988");
		mailInfo.setValidate(true);
		mailInfo.setUserName("keke_alert");
		mailInfo.setPassword("oppo*SZ999@");// 您的邮箱密码
		mailInfo.setFromAddress("keke_alert@oppo.com");
		List<String> address = buildToAddresses();
		mailInfo.setToAddress(address);
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		// List<String> attachFileNames = new ArrayList<String>();
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		try {
			// 发送文体格式
			// sms.sendTextMail(mailInfo);
			// 发送HTML格式
			sms.sendHtmlMail(mailInfo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String buildMail(String username, String url, String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("<table border='1' cellpadding='2' cellspacing='0'>");
		sb.append("<th colspan='2' bgcolor='yellow'>"
				+ DateUtil.formatDate(new Date(), DateUtil.FMT_FULL_DATETIME)
				+ "</th>");
		sb.append("<tr><td>反馈人</td><td>" + username + "</td></tr>");
		sb.append("<tr><td>反馈URL</td><td>" + url + "</td></tr>");
		sb.append("<tr><td>反馈内容</td><td>" + content + "</td></tr>");
		sb.append("</table>");

		return sb.toString();
	}

	public static String buildMail(String feedback, String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("<table border='1' cellpadding='2' cellspacing='0'>");
		sb.append("<th colspan='2' bgcolor='yellow'>"
				+ DateUtil.formatDate(new Date(), DateUtil.FMT_FULL_DATETIME)
				+ "</th>");
		sb.append("<tr><td>反馈人</td><td>" + feedback + "</td></tr>");
		sb.append("<tr><td>反馈内容</td><td>" + content + "</td></tr>");
		sb.append("</table>");

		return sb.toString();
	}

	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSenderInfoBean mailInfo = new MailSenderInfoBean();
		mailInfo.setMailServerHost("mail9988.oppo.com");
		mailInfo.setMailServerPort("9988");
		mailInfo.setValidate(true);
		mailInfo.setUserName("keke_alert");
		mailInfo.setPassword("oppo*SZ999@");// 您的邮箱密码
		mailInfo.setFromAddress("keke_alert@oppo.com");
		List<String> address = new ArrayList<String>();
		address.add("330169742@qq.com");
		address.add("278477155@qq.com");
		mailInfo.setToAddress(address);
		mailInfo.setSubject("异常反馈");
		mailInfo.setContent(buildMail("刘超", "日明细数据没有计算出来"));
		// List<String> attachFileNames = new ArrayList<String>();
		// 此处是你要得到的上传附件的文件路径
		// attachFileNames.add("C:\\Users\\Administrator\\Desktop\\javaMail.rar");
		// mailInfo.setAttachFileNames(attachFileNames);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);//发送文体格式
		try {
			// 采取html格式发送
			sms.sendHtmlMail(mailInfo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} // 发送html格式
	}
}
