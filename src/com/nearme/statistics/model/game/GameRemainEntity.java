package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 留存分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class GameRemainEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private Date remainDate;// 留存日
	private long remain;// 账号留存
	private float remainratio;// 账号留存率
	private long imeiremain;// imei留存
	private float imeiremainratio;// imei留存率

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

	public Date getRemainDate() {
		return remainDate;
	}

	public void setRemainDate(Date remainDate) {
		this.remainDate = remainDate;
	}

	public long getRemain() {
		return remain;
	}

	public void setRemain(long remain) {
		this.remain = remain;
	}

	public float getRemainratio() {
		return remainratio;
	}

	public void setRemainratio(float remainratio) {
		this.remainratio = remainratio;
	}

	public long getImeiremain() {
		return imeiremain;
	}

	public void setImeiremain(long imeiremain) {
		this.imeiremain = imeiremain;
	}

	public float getImeiremainratio() {
		return imeiremainratio;
	}

	public void setImeiremainratio(float imeiremainratio) {
		this.imeiremainratio = imeiremainratio;
	}
}
