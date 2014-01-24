package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 渠道概述
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-6
 */
public class ChannelgeneralEntity implements java.io.Serializable {
	private static final long serialVersionUID = 520457614267274255L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private String channelname;// 渠道
	private long newimei;// 日新增imei
	private float newimeiratio;// 日新增imei占比
	private long startimei;// 日启动imei
	private float startimeiratio;// 日启动imei占比
	private long totalimei;// 累计imei
	private long newssoid;// 日新增账号
	private float newssoidratio;// 日新增账号占比
	private long startssoid;// 日启动账号
	private float startssoidratio;// 日启动账号占比
	private long totalssoid;// 累计账号

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

	public float getNewimeiratio() {
		return newimeiratio;
	}

	public void setNewimeiratio(float newimeiratio) {
		this.newimeiratio = newimeiratio;
	}

	public long getStartimei() {
		return startimei;
	}

	public void setStartimei(long startimei) {
		this.startimei = startimei;
	}

	public float getStartimeiratio() {
		return startimeiratio;
	}

	public void setStartimeiratio(float startimeiratio) {
		this.startimeiratio = startimeiratio;
	}

	public long getTotalimei() {
		return totalimei;
	}

	public void setTotalimei(long totalimei) {
		this.totalimei = totalimei;
	}

	public long getNewssoid() {
		return newssoid;
	}

	public void setNewssoid(long newssoid) {
		this.newssoid = newssoid;
	}

	public float getNewssoidratio() {
		return newssoidratio;
	}

	public void setNewssoidratio(float newssoidratio) {
		this.newssoidratio = newssoidratio;
	}

	public long getStartssoid() {
		return startssoid;
	}

	public void setStartssoid(long startssoid) {
		this.startssoid = startssoid;
	}

	public float getStartssoidratio() {
		return startssoidratio;
	}

	public void setStartssoidratio(float startssoidratio) {
		this.startssoidratio = startssoidratio;
	}

	public long getTotalssoid() {
		return totalssoid;
	}

	public void setTotalssoid(long totalssoid) {
		this.totalssoid = totalssoid;
	}

}
