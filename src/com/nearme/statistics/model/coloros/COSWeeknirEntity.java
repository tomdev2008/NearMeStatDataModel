package com.nearme.statistics.model.coloros;

import java.util.Date;

/**
 * 周新增留存
 *
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-25
 */
public class COSWeeknirEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1152163763934526975L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private long new_imei;// 新增用户数
	private float nrr1_imei;// 用户留存率x1
	private float nrr2_imei;// 用户留存率x2
	private float nrr3_imei;// 用户留存率x3

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

	public long getNew_imei() {
		return new_imei;
	}

	public void setNew_imei(long new_imei) {
		this.new_imei = new_imei;
	}

	public float getNrr1_imei() {
		return nrr1_imei;
	}

	public void setNrr1_imei(float nrr1_imei) {
		this.nrr1_imei = nrr1_imei;
	}

	public float getNrr2_imei() {
		return nrr2_imei;
	}

	public void setNrr2_imei(float nrr2_imei) {
		this.nrr2_imei = nrr2_imei;
	}

	public float getNrr3_imei() {
		return nrr3_imei;
	}

	public void setNrr3_imei(float nrr3_imei) {
		this.nrr3_imei = nrr3_imei;
	}
}
