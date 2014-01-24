package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 用户行为分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBUseractionEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long loginuser;// 登陆用户
	private long usercnt;// 用户数
	private long startcnt;// 启动次数
	private long eventtotal;// 事件消息总次数
	private long logineventcnt;// 登陆用户事件消息次数
	private float logineventratio;// 登陆用户事件消息占比
	private long nologineventcnt;// 未登录用户事件消息次数
	private float nologineventratio;// 未登录用户事件消息占比

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

	public long getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(long loginuser) {
		this.loginuser = loginuser;
	}

	public long getUsercnt() {
		return usercnt;
	}

	public void setUsercnt(long usercnt) {
		this.usercnt = usercnt;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public long getEventtotal() {
		return eventtotal;
	}

	public void setEventtotal(long eventtotal) {
		this.eventtotal = eventtotal;
	}

	public long getLogineventcnt() {
		return logineventcnt;
	}

	public void setLogineventcnt(long logineventcnt) {
		this.logineventcnt = logineventcnt;
	}

	public float getLogineventratio() {
		return logineventratio;
	}

	public void setLogineventratio(float logineventratio) {
		this.logineventratio = logineventratio;
	}

	public long getNologineventcnt() {
		return nologineventcnt;
	}

	public void setNologineventcnt(long nologineventcnt) {
		this.nologineventcnt = nologineventcnt;
	}

	public float getNologineventratio() {
		return nologineventratio;
	}

	public void setNologineventratio(float nologineventratio) {
		this.nologineventratio = nologineventratio;
	}
}
