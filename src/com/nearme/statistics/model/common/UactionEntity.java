package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 用户行为分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-15
 */
public class UactionEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8823925869433302323L;

	private Date statDate;// 时间
	private Date statEndDate;

	private long startuser;// 启动账号数
	private long startimei;// 启动手机数
	private long startcnt;// 启动次数
	private long eventuser;// 事件消息账号数
	private long eventimei;// 事件消息手机数
	private long eventcnt;// 事件消息次数

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

	public long getStartuser() {
		return startuser;
	}

	public void setStartuser(long startuser) {
		this.startuser = startuser;
	}

	public long getStartimei() {
		return startimei;
	}

	public void setStartimei(long startimei) {
		this.startimei = startimei;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public long getEventuser() {
		return eventuser;
	}

	public void setEventuser(long eventuser) {
		this.eventuser = eventuser;
	}

	public long getEventimei() {
		return eventimei;
	}

	public void setEventimei(long eventimei) {
		this.eventimei = eventimei;
	}

	public long getEventcnt() {
		return eventcnt;
	}

	public void setEventcnt(long eventcnt) {
		this.eventcnt = eventcnt;
	}
}
