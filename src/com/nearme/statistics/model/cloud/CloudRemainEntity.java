package com.nearme.statistics.model.cloud;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储留存
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-13
 */
public class CloudRemainEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long storeuser;// 存储帐号数
	private float remain1x;// 1X存储留存率
	private float remain2x;// 2X存储留存率
	private float remain3x;// 3X存储留存率

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

	public long getStoreuser() {
		return storeuser;
	}

	public void setStoreuser(long storeuser) {
		this.storeuser = storeuser;
	}

	public float getRemain1x() {
		return remain1x;
	}

	public void setRemain1x(float remain1x) {
		this.remain1x = remain1x;
	}

	public float getRemain2x() {
		return remain2x;
	}

	public void setRemain2x(float remain2x) {
		this.remain2x = remain2x;
	}

	public float getRemain3x() {
		return remain3x;
	}

	public void setRemain3x(float remain3x) {
		this.remain3x = remain3x;
	}
}
