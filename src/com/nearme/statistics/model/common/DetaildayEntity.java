package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 日明细数据 Entity
 * @version 1.0
 * @since 1.0, 2013-4-24
 */
public class DetaildayEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private Date statDate;// 时间
	private long startTimes;// 启动次数
	private long startImei;// 启动IMEI
	private long startUser;// 启动账号
	private long newImei;// 新增IMEI
	private long newUser;// 新增账号

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
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
}
