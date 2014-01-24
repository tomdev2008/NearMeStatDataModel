package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 新用户活跃
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-25
 */
public class NewuseractiveEntity implements java.io.Serializable {
	private static final long serialVersionUID = -4019318499206342153L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String activedays;// 活跃天数
	private long startimei;// 启动imei
	private long downcnt;// 下载次数
	private long startcnt;// 启动次数

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

	public String getActivedays() {
		return activedays;
	}

	public void setActivedays(String activedays) {
		this.activedays = activedays;
	}

	public long getStartimei() {
		return startimei;
	}

	public void setStartimei(long startimei) {
		this.startimei = startimei;
	}

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}
}
