package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 月健康度数据 Entity
 * @version 1.0
 * @since 1.0, 2013-5-13
 */
public class MonthhealthEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private String tableName;//要查询的表名
	private Date statDate;// 时间
	private Date statEndDate;
	private int systemID;// 应用标识
	
	private long startImei;// 启动IMEI
	private long startUser;// 启动账号
	private long newImei;// 新增IMEI
	private long remainImei;// 留存IMEI
	private long remainUser;// 留存账号数
	private long newUser;// 新增账号
	private long silentUser;// 沉默用户数
	private long silentImei;// 沉默IMEI数
	private long lostUser;// 流失用户
	private long lostImei;// 流失IMEI数
	private long backImei;// 回流IMEI数
	private long backUser;// 回流用户

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public Date getStatEndDate() {
		return statEndDate;
	}

	public void setStatEndDate(Date statEndDate) {
		this.statEndDate = statEndDate;
	}

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public long getStartImei() {
		return startImei;
	}

	public void setStartImei(long startImei) {
		this.startImei = startImei;
	}

	public long getStartUser() {
		return startUser;
	}

	public void setStartUser(long startUser) {
		this.startUser = startUser;
	}

	public long getNewImei() {
		return newImei;
	}

	public void setNewImei(long newImei) {
		this.newImei = newImei;
	}

	public long getRemainImei() {
		return remainImei;
	}

	public void setRemainImei(long remainImei) {
		this.remainImei = remainImei;
	}

	public long getRemainUser() {
		return remainUser;
	}

	public void setRemainUser(long remainUser) {
		this.remainUser = remainUser;
	}

	public long getNewUser() {
		return newUser;
	}

	public void setNewUser(long newUser) {
		this.newUser = newUser;
	}

	public long getSilentUser() {
		return silentUser;
	}

	public void setSilentUser(long silentUser) {
		this.silentUser = silentUser;
	}

	public long getSilentImei() {
		return silentImei;
	}

	public void setSilentImei(long silentImei) {
		this.silentImei = silentImei;
	}

	public long getLostUser() {
		return lostUser;
	}

	public void setLostUser(long lostUser) {
		this.lostUser = lostUser;
	}

	public long getLostImei() {
		return lostImei;
	}

	public void setLostImei(long lostImei) {
		this.lostImei = lostImei;
	}

	public long getBackImei() {
		return backImei;
	}

	public void setBackImei(long backImei) {
		this.backImei = backImei;
	}

	public long getBackUser() {
		return backUser;
	}

	public void setBackUser(long backUser) {
		this.backUser = backUser;
	}
}
