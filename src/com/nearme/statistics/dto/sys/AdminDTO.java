package com.nearme.statistics.dto.sys;

/**
 * 用户管理DTO
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-26
 */
public class AdminDTO {
	private int id;// 管理员ID
	private String username;// 登录名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
