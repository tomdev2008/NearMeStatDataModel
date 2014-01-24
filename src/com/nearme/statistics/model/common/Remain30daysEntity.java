package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 下载留存
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-11
 */
public class Remain30daysEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long remain;// 留存量
	private long remain1day;// 1天后留存量
	private long remain2day;// 2天后留存量
	private long remain3day;// 3天后留存量
	private long remain4day;// 4天后留存量
	private long remain5day;// 5天后留存量
	private long remain6day;// 6天后留存量
	private long remain7day;// 7天后留存量
	private long remain14day;// 14天后留存量
	private long remain30day;// 30天后留存量
	private float remainratio1day;// 1天后留存率
	private float remainratio2day;// 2天后留存率
	private float remainratio3day;// 3天后留存率
	private float remainratio4day;// 4天后留存率
	private float remainratio5day;// 5天后留存率
	private float remainratio6day;// 6天后留存率
	private float remainratio7day;// 7天后留存率
	private float remainratio14day;// 14天后留存率
	private float remainratio30day;// 30天后留存率

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

	public long getRemain() {
		return remain;
	}

	public void setRemain(long remain) {
		this.remain = remain;
	}

	public long getRemain1day() {
		return remain1day;
	}

	public void setRemain1day(long remain1day) {
		this.remain1day = remain1day;
	}

	public long getRemain2day() {
		return remain2day;
	}

	public void setRemain2day(long remain2day) {
		this.remain2day = remain2day;
	}

	public long getRemain3day() {
		return remain3day;
	}

	public void setRemain3day(long remain3day) {
		this.remain3day = remain3day;
	}

	public long getRemain4day() {
		return remain4day;
	}

	public void setRemain4day(long remain4day) {
		this.remain4day = remain4day;
	}

	public long getRemain5day() {
		return remain5day;
	}

	public void setRemain5day(long remain5day) {
		this.remain5day = remain5day;
	}

	public long getRemain6day() {
		return remain6day;
	}

	public void setRemain6day(long remain6day) {
		this.remain6day = remain6day;
	}

	public long getRemain7day() {
		return remain7day;
	}

	public void setRemain7day(long remain7day) {
		this.remain7day = remain7day;
	}

	public long getRemain14day() {
		return remain14day;
	}

	public void setRemain14day(long remain14day) {
		this.remain14day = remain14day;
	}

	public long getRemain30day() {
		return remain30day;
	}

	public void setRemain30day(long remain30day) {
		this.remain30day = remain30day;
	}

	public float getRemainratio1day() {
		return remainratio1day;
	}

	public void setRemainratio1day(float remainratio1day) {
		this.remainratio1day = remainratio1day;
	}

	public float getRemainratio2day() {
		return remainratio2day;
	}

	public void setRemainratio2day(float remainratio2day) {
		this.remainratio2day = remainratio2day;
	}

	public float getRemainratio3day() {
		return remainratio3day;
	}

	public void setRemainratio3day(float remainratio3day) {
		this.remainratio3day = remainratio3day;
	}

	public float getRemainratio4day() {
		return remainratio4day;
	}

	public void setRemainratio4day(float remainratio4day) {
		this.remainratio4day = remainratio4day;
	}

	public float getRemainratio5day() {
		return remainratio5day;
	}

	public void setRemainratio5day(float remainratio5day) {
		this.remainratio5day = remainratio5day;
	}

	public float getRemainratio6day() {
		return remainratio6day;
	}

	public void setRemainratio6day(float remainratio6day) {
		this.remainratio6day = remainratio6day;
	}

	public float getRemainratio7day() {
		return remainratio7day;
	}

	public void setRemainratio7day(float remainratio7day) {
		this.remainratio7day = remainratio7day;
	}

	public float getRemainratio14day() {
		return remainratio14day;
	}

	public void setRemainratio14day(float remainratio14day) {
		this.remainratio14day = remainratio14day;
	}

	public float getRemainratio30day() {
		return remainratio30day;
	}

	public void setRemainratio30day(float remainratio30day) {
		this.remainratio30day = remainratio30day;
	}
}
