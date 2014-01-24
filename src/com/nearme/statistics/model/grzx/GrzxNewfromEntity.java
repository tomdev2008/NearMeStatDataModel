package com.nearme.statistics.model.grzx;

import java.io.Serializable;
import java.util.Date;

/**
 * 新增来源
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class GrzxNewfromEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long comefrom;//来源
	private long totalnewuser;//当天总共新增
	private long newuser;//该来源新增用户
	private float newratio;//新增占比

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

	public long getComefrom() {
		return comefrom;
	}

	public void setComefrom(long comefrom) {
		this.comefrom = comefrom;
	}

	public long getTotalnewuser() {
		return totalnewuser;
	}

	public void setTotalnewuser(long totalnewuser) {
		this.totalnewuser = totalnewuser;
	}

	public long getNewuser() {
		return newuser;
	}

	public void setNewuser(long newuser) {
		this.newuser = newuser;
	}

	public float getNewratio() {
		return newratio;
	}

	public void setNewratio(float newratio) {
		this.newratio = newratio;
	}
}
