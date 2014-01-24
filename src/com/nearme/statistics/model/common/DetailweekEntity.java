package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 周明细数据 Entity
 * @version 1.0
 * @since 1.0, 2013-4-24
 */
public class DetailweekEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4623036455199098825L;

	private String tableName;
	// 时间
	private Date statDate;
	private Date statEndDate;
	private String weekStr;//界面用于显示的时间周期
	private int systemID;// 应用标识

	private long startTimes;// 启动次数
	private long startImei;// 启动IMEI
	private long startUser;// 启动账号
	private long newImei;// 新增IMEI
	private long newUser;// 新增账号

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

	public long getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(long startTimes) {
		this.startTimes = startTimes;
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

	public long getNewUser() {
		return newUser;
	}

	public void setNewUser(long newUser) {
		this.newUser = newUser;
	}

	public String getWeekStr() {
		return weekStr;
	}

	public void setWeekStr(String weekStr) {
		this.weekStr = weekStr;
	}
}
