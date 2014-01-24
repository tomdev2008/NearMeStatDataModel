package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 网络日明细
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-12
 */
public class NetdayEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private Date statDate;// 时间
	private Date statEndDate;

	private long allstartImei;// 全网络启动IMEI数
	private long allnewImei;// 全网络新增IMEI数
	private long startImei;// 该网络启动IMEI数
	private float startRatio;// 该网络启动IMEI占比
	private long newImei;// 该网络新增IMEI数
	private float newRatio;// 该网络新增IMEI占比

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

	public long getAllstartImei() {
		return allstartImei;
	}

	public void setAllstartImei(long allstartImei) {
		this.allstartImei = allstartImei;
	}

	public long getAllnewImei() {
		return allnewImei;
	}

	public void setAllnewImei(long allnewImei) {
		this.allnewImei = allnewImei;
	}

	public long getStartImei() {
		return startImei;
	}

	public void setStartImei(long startImei) {
		this.startImei = startImei;
	}

	public float getStartRatio() {
		return startRatio;
	}

	public void setStartRatio(float startRatio) {
		this.startRatio = startRatio;
	}

	public long getNewImei() {
		return newImei;
	}

	public void setNewImei(long newImei) {
		this.newImei = newImei;
	}

	public float getNewRatio() {
		return newRatio;
	}

	public void setNewRatio(float newRatio) {
		this.newRatio = newRatio;
	}
}
