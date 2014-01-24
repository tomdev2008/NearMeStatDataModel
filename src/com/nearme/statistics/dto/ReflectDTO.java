package com.nearme.statistics.dto;

/**
 * 反馈信息
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-29
 */
public class ReflectDTO {
	private String url;// 路径
	private String detail;// 反馈描述
	private String username;// 反馈人

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
}
