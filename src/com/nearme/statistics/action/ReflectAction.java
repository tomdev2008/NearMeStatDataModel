package com.nearme.statistics.action;

import java.net.MalformedURLException;
import java.net.URL;

import com.nearme.statistics.dto.ReflectDTO;
import com.nearme.statistics.service.sys.ReflectService;
import com.nearme.statistics.util.MyAuthenticator;
import com.nearme.statistics.util.SMSUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户反馈Action
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-29
 */
public class ReflectAction extends ActionSupport {
	private static final long serialVersionUID = -6800646749635228290L;

	private String url;// 路径
	private String detail;// 反馈描述
	private String username;// 反馈人

	private ReflectService service;

	// 提交反馈信息到DB中
	public String reflect() {
		if (url == null) {
			return Action.SUCCESS;
		}

		if (detail != null && detail.length() > 500) {
			detail = detail.substring(0, 500);
		}

		if (username != null && username.length() > 16) {
			username = username.substring(0, 16);
		}

		// 提交到DB
		ReflectDTO dto = new ReflectDTO();
		dto.setUrl(url);
		if (detail != null) {
			dto.setDetail(detail);
		}
		if (username != null) {
			dto.setUsername(username);
		}
		service.setReflectInfo(dto);

//		sendSMS();
		sendMail();

		return Action.SUCCESS;
	}
	
	/**
	 * 发送短信通知统计组
	 */
	@SuppressWarnings("unused")
	private void sendSMS(){
		String shorturl = "";
		try {
			shorturl = new URL(url).getPath();
		} catch (MalformedURLException e) {
			shorturl = url;
			e.printStackTrace();
		}
		SMSUtil.sendSMS(SMSUtil.contactList, "统计错误报告：url=" + shorturl + "  \n详细说明=" + detail
				+ "  \n反馈人=" + username + "\n请及时处理！");
	}
	
	/**
	 * 发送邮件通知统计组
	 */
	private void sendMail(){
		String shorturl = "";
		try {
			shorturl = new URL(url).getPath();
		} catch (MalformedURLException e) {
			shorturl = url;
			e.printStackTrace();
		}
		MyAuthenticator.sendMail("统计异常反馈", MyAuthenticator.buildMail(username,
				shorturl, detail));
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ReflectService getService() {
		return service;
	}

	public void setService(ReflectService service) {
		this.service = service;
	}
}
