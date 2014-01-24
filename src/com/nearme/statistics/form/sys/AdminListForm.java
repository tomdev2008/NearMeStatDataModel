package com.nearme.statistics.form.sys;

import java.util.Date;

public class AdminListForm {

	private int id;// 管理员ID
	private String userName;// 登录名
	private String adminNewPasswd;
	private String adminNewPasswd2;
	private String userPasswd;
	private String realName;
	private String email;
	private String mobileNumber;
	private Date updateTime;// 最近更新时间
	private String roleIDs;// 角色IDs
	private String groupIDs;// 分组IDs
	private String[] roleIDlist;// 角色ID数组
	private String[] groupIDlist;// 分组ID数组
	private int ipLimit;// IP限定规则：0只允许白名单;1只禁止黑名单;2全部允许

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getIpLimit() {
		return ipLimit;
	}

	public void setIpLimit(int ipLimit) {
		this.ipLimit = ipLimit;
	}

	public String getAdminNewPasswd() {
		return adminNewPasswd;
	}

	public void setAdminNewPasswd(String adminNewPasswd) {
		this.adminNewPasswd = adminNewPasswd;
	}

	public String getAdminNewPasswd2() {
		return adminNewPasswd2;
	}

	public void setAdminNewPasswd2(String adminNewPasswd2) {
		this.adminNewPasswd2 = adminNewPasswd2;
	}

	public String getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(String roleIDs) {
		this.roleIDs = roleIDs;
	}

	public String getGroupIDs() {
		return groupIDs;
	}

	public void setGroupIDs(String groupIDs) {
		this.groupIDs = groupIDs;
	}

	public String[] getRoleIDlist() {
		return roleIDlist;
	}

	public void setRoleIDlist(String[] roleIDlist) {
		this.roleIDlist = roleIDlist;
	}

	public String[] getGroupIDlist() {
		return groupIDlist;
	}

	public void setGroupIDlist(String[] groupIDlist) {
		this.groupIDlist = groupIDlist;
	}
}
