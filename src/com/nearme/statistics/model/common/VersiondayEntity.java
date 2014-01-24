package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 版本日明细
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-12
 */
public class VersiondayEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private Date statDate;// 时间
	private Date statEndDate;

	private long allstartImei;// 全版本启动IMEI数
	private long allnewImei;// 全版本新增IMEI数
	private long startImei;// 该版本启动IMEI数
	private float startRatio;// 该版本启动IMEI占比
	private long newImei;// 该版本新增IMEI数
	private float newRatio;// 该版本新增IMEI占比
	private long updateImei;// 升级至该版本IMEI数

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

	public long getUpdateImei() {
		return updateImei;
	}

	public void setUpdateImei(long updateImei) {
		this.updateImei = updateImei;
	}
}
