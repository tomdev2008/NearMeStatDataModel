package com.nearme.statistics.model.grzx;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号覆盖率
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class GrzxUsercoverEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long startImei;// 全平台启动IMEI数
	private long startUser;// 全平台启动帐号数
	private float coverratio;// 帐号覆盖率

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

	public float getCoverratio() {
		return coverratio;
	}

	public void setCoverratio(float coverratio) {
		this.coverratio = coverratio;
	}
}
