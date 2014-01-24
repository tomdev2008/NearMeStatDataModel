package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 渠道质量
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-6
 */
public class ChannelqualityEntity implements java.io.Serializable {
	private static final long serialVersionUID = 520457614267274255L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private String channelname;
	private long newimei;
	private float nirday1;
	private float nirday7;
	private float nirday30;
	private long startcnt;
	private float avgusetime;

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

	public String getStatDatestr() {
		return statDatestr;
	}

	public void setStatDatestr(String statDatestr) {
		this.statDatestr = statDatestr;
	}

	public String getStatEndDatestr() {
		return statEndDatestr;
	}

	public void setStatEndDatestr(String statEndDatestr) {
		this.statEndDatestr = statEndDatestr;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public long getNewimei() {
		return newimei;
	}

	public void setNewimei(long newimei) {
		this.newimei = newimei;
	}

	public float getNirday1() {
		return nirday1;
	}

	public void setNirday1(float nirday1) {
		this.nirday1 = nirday1;
	}

	public float getNirday7() {
		return nirday7;
	}

	public void setNirday7(float nirday7) {
		this.nirday7 = nirday7;
	}

	public float getNirday30() {
		return nirday30;
	}

	public void setNirday30(float nirday30) {
		this.nirday30 = nirday30;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public float getAvgusetime() {
		return avgusetime;
	}

	public void setAvgusetime(float avgusetime) {
		this.avgusetime = avgusetime;
	}
}
