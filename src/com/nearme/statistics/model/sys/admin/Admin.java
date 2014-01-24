package com.nearme.statistics.model.sys.admin;

import java.util.Date;

/**
 * 用户信息Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-26
 */
public class Admin {
	private int id;// 管理员ID
	private String userName;// 登录名
	private String userPasswd;
	private String realName;
	private String email;
	private String mobileNumber;
	private int state;// 管理员状态：0 OK; 1 DELETED
	private int updateBy;
	private Date updateTime;// 最近更新时间
	private int ipLimit;// IP限定规则：0只允许白名单;1只禁止黑名单;2全部允许

	private String roleIDs;// 角色IDs
	private String systemIDs;// 角色对应的systemIDs
	private String roleNames;// 角色名称

	private String groupIDs;// 分组IDs
	private String groupNames;// 分组名称

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public String getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(String roleIDs) {
		this.roleIDs = roleIDs;
	}

	public String getSystemIDs() {
		return systemIDs;
	}

	public void setSystemIDs(String systemIDs) {
		this.systemIDs = systemIDs;
	}

	public String getGroupIDs() {
		return groupIDs;
	}

	public void setGroupIDs(String groupIDs) {
		this.groupIDs = groupIDs;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}
}
