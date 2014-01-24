package com.nearme.statistics.model.coloros;

import java.util.Date;

/**
 * 月启动留存
 *
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-25
 */
public class COSMonthsrEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1152163763934526975L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private long launch_imei;// 启动用户数
	private float lrr1_imei;// 启动留存率x1
	private float lrr2_imei;// 启动留存率x2
	private float lrr3_imei;// 启动留存率x3

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

	public long getLaunch_imei() {
		return launch_imei;
	}

	public void setLaunch_imei(long launch_imei) {
		this.launch_imei = launch_imei;
	}

	public float getLrr1_imei() {
		return lrr1_imei;
	}

	public void setLrr1_imei(float lrr1_imei) {
		this.lrr1_imei = lrr1_imei;
	}

	public float getLrr2_imei() {
		return lrr2_imei;
	}

	public void setLrr2_imei(float lrr2_imei) {
		this.lrr2_imei = lrr2_imei;
	}

	public float getLrr3_imei() {
		return lrr3_imei;
	}

	public void setLrr3_imei(float lrr3_imei) {
		this.lrr3_imei = lrr3_imei;
	}
}
