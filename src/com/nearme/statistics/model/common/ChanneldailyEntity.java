package com.nearme.statistics.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道日明细Entity
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-9
 */
public class ChanneldailyEntity implements Serializable {
	private static final long serialVersionUID = -4836723705987434245L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long newImei;// 新增imei
	private long newSsoid;// 新增ssoid
	private long startImei;// 下载次数
	private long startSsoid;// 启动次数

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
	public long getNewImei() {
		return newImei;
	}
	public void setNewImei(long newImei) {
		this.newImei = newImei;
	}
	public long getNewSsoid() {
		return newSsoid;
	}
	public void setNewSsoid(long newSsoid) {
		this.newSsoid = newSsoid;
	}
	public long getStartImei() {
		return startImei;
	}
	public void setStartImei(long startImei) {
		this.startImei = startImei;
	}
	public long getStartSsoid() {
		return startSsoid;
	}
	public void setStartSsoid(long startSsoid) {
		this.startSsoid = startSsoid;
	}
}
