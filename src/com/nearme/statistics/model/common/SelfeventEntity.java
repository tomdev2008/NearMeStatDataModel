package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 自定义事件 Entity
 * @version 1.0
 * @since 1.0, 2013-5-24
 */
public class SelfeventEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private Date statDate;// 时间
	private long startImei;// 启动IMEI数
	private long startCnt;// 启动次数
	private long eventActiveImei; // 事件激活IMEI数
	private long eventActiveCnt;// 事件激活次数

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public long getStartImei() {
		return startImei;
	}

	public void setStartImei(long startImei) {
		this.startImei = startImei;
	}

	public long getStartCnt() {
		return startCnt;
	}

	public void setStartCnt(long startCnt) {
		this.startCnt = startCnt;
	}

	public long getEventActiveImei() {
		return eventActiveImei;
	}

	public void setEventActiveImei(long eventActiveImei) {
		this.eventActiveImei = eventActiveImei;
	}

	public long getEventActiveCnt() {
		return eventActiveCnt;
	}

	public void setEventActiveCnt(long eventActiveCnt) {
		this.eventActiveCnt = eventActiveCnt;
	}
}
