package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 用户生命周期-衰减数据 Entity
 * @version 1.0
 * @since 1.0, 2013-5-10
 */
public class UserlifecycleDecayEntity implements java.io.Serializable {
	private static final long serialVersionUID = 3017731761424354986L;

	private Date statDate;// 时间
	private long startImei;// 启动Imei
	private long startUser;// 启动用户数

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

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
}
