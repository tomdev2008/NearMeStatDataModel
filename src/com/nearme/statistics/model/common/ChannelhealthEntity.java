package com.nearme.statistics.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道健康度
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-9
 */
public class ChannelhealthEntity implements Serializable {
	private static final long serialVersionUID = -4836723705987434245L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private float imei1x;// imei1X留存
	private float imei2x;// imei2X留存
	private float imei3x;// imei3X留存
	private float ssoid1x;// ssoid1X留存
	private float ssoid2x;// ssoid2X留存
	private float ssoid3x;// ssoid3X留存

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

	public float getImei1x() {
		return imei1x;
	}

	public void setImei1x(float imei1x) {
		this.imei1x = imei1x;
	}

	public float getImei2x() {
		return imei2x;
	}

	public void setImei2x(float imei2x) {
		this.imei2x = imei2x;
	}

	public float getImei3x() {
		return imei3x;
	}

	public void setImei3x(float imei3x) {
		this.imei3x = imei3x;
	}

	public float getSsoid1x() {
		return ssoid1x;
	}

	public void setSsoid1x(float ssoid1x) {
		this.ssoid1x = ssoid1x;
	}

	public float getSsoid2x() {
		return ssoid2x;
	}

	public void setSsoid2x(float ssoid2x) {
		this.ssoid2x = ssoid2x;
	}

	public float getSsoid3x() {
		return ssoid3x;
	}

	public void setSsoid3x(float ssoid3x) {
		this.ssoid3x = ssoid3x;
	}
}