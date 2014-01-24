package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 下载留存
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-11
 */
public class DownremainEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long downimei;// 下载imei数
	private long dr1;// 1天后下载留存imei
	private long dr2;// 2天后下载留存imei
	private long dr3;// 3天后下载留存imei
	private float drr1;// 1天后下载留存率
	private float drr2;// 2天后下载留存率
	private float drr3;// 3天后下载留存率
	private long newdownimei;// 新增下载imei数
	private long ndr1;// 1天后新增下载留存imei数
	private long ndr2;// 2天后新增下载留存imei数
	private long ndr3;// 3天后新增下载留存imei数
	private float ndrr1;// 1天后新增下载留存imei率
	private float ndrr2;// 2天后新增下载留存imei率
	private float ndrr3;// 3天后新增下载留存imei率

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

	public long getDownimei() {
		return downimei;
	}

	public void setDownimei(long downimei) {
		this.downimei = downimei;
	}

	public long getDr1() {
		return dr1;
	}

	public void setDr1(long dr1) {
		this.dr1 = dr1;
	}

	public long getDr2() {
		return dr2;
	}

	public void setDr2(long dr2) {
		this.dr2 = dr2;
	}

	public long getDr3() {
		return dr3;
	}

	public void setDr3(long dr3) {
		this.dr3 = dr3;
	}

	public float getDrr1() {
		return drr1;
	}

	public void setDrr1(float drr1) {
		this.drr1 = drr1;
	}

	public float getDrr2() {
		return drr2;
	}

	public void setDrr2(float drr2) {
		this.drr2 = drr2;
	}

	public float getDrr3() {
		return drr3;
	}

	public void setDrr3(float drr3) {
		this.drr3 = drr3;
	}

	public long getNewdownimei() {
		return newdownimei;
	}

	public void setNewdownimei(long newdownimei) {
		this.newdownimei = newdownimei;
	}

	public long getNdr1() {
		return ndr1;
	}

	public void setNdr1(long ndr1) {
		this.ndr1 = ndr1;
	}

	public long getNdr2() {
		return ndr2;
	}

	public void setNdr2(long ndr2) {
		this.ndr2 = ndr2;
	}

	public long getNdr3() {
		return ndr3;
	}

	public void setNdr3(long ndr3) {
		this.ndr3 = ndr3;
	}

	public float getNdrr1() {
		return ndrr1;
	}

	public void setNdrr1(float ndrr1) {
		this.ndrr1 = ndrr1;
	}

	public float getNdrr2() {
		return ndrr2;
	}

	public void setNdrr2(float ndrr2) {
		this.ndrr2 = ndrr2;
	}

	public float getNdrr3() {
		return ndrr3;
	}

	public void setNdrr3(float ndrr3) {
		this.ndrr3 = ndrr3;
	}
}
