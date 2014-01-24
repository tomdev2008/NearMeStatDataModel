package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 月明细数据 Entity
 * @version 1.0
 * @since 1.0, 2013-4-24
 */
public class DetailmonthEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4647112826553580282L;

	// 时间
	private Date statDate;
	private Date statEndDate;
	private int systemID;// 应用标识

	private long startImei;// 启动IMEI
	private long startUser;// 启动账号
	private long newImei;// 新增IMEI
	private long newUser;// 新增账号
	private long monthstartimei; // 日启动1+日启动2+...+日启动30
	private long monthstartuser; // 日启动1+日启动2+...+日启动30
	private long monthnewimei; // 日启动1+日启动2+...+日启动30
	private long monthnewuser; // 日启动1+日启动2+...+日启动30
	private long avgStartImei;// 日均启动IMEI
	private long avgStartUser;// 日均启动账号数
	private long avgNewImei;// 日均新增Imei数
	private long avgNewUser;// 日均新增账号数
	private float avgActiveDay;// 平均活跃天数

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

	public long getNewUser() {
		return newUser;
	}

	public void setNewUser(long newUser) {
		this.newUser = newUser;
	}

	public long getMonthstartimei() {
		return monthstartimei;
	}

	public void setMonthstartimei(long monthstartimei) {
		this.monthstartimei = monthstartimei;
	}

	public long getAvgStartImei() {
		return avgStartImei;
	}

	public void setAvgStartImei(long avgStartImei) {
		this.avgStartImei = avgStartImei;
	}

	public long getAvgStartUser() {
		return avgStartUser;
	}

	public void setAvgStartUser(long avgStartUser) {
		this.avgStartUser = avgStartUser;
	}

	public long getAvgNewImei() {
		return avgNewImei;
	}

	public void setAvgNewImei(long avgNewImei) {
		this.avgNewImei = avgNewImei;
	}

	public long getAvgNewUser() {
		return avgNewUser;
	}

	public void setAvgNewUser(long avgNewUser) {
		this.avgNewUser = avgNewUser;
	}

	public float getAvgActiveDay() {
		return avgActiveDay;
	}

	public void setAvgActiveDay(float avgActiveDay) {
		this.avgActiveDay = avgActiveDay;
	}

	public long getMonthstartuser() {
		return monthstartuser;
	}

	public void setMonthstartuser(long monthstartuser) {
		this.monthstartuser = monthstartuser;
	}

	public long getMonthnewimei() {
		return monthnewimei;
	}

	public void setMonthnewimei(long monthnewimei) {
		this.monthnewimei = monthnewimei;
	}

	public long getMonthnewuser() {
		return monthnewuser;
	}

	public void setMonthnewuser(long monthnewuser) {
		this.monthnewuser = monthnewuser;
	}
}
