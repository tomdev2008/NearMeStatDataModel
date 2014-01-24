package com.nearme.statistics.model.liren;

import java.io.Serializable;
import java.util.Date;

/**
 * push Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-19
 */
public class LRPushEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String theme;// 推送主题
	private long usercnt;// 推送用户数
	private long sendcnt;// 发送push数
	private long showcnt;// push状态栏显示数
	private float arriveratio;// 到达率
	private long clickcnt;// 点击push数
	private long arrivepagecnt;// 到达push页面数

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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public long getUsercnt() {
		return usercnt;
	}

	public void setUsercnt(long usercnt) {
		this.usercnt = usercnt;
	}

	public long getSendcnt() {
		return sendcnt;
	}

	public void setSendcnt(long sendcnt) {
		this.sendcnt = sendcnt;
	}

	public long getShowcnt() {
		return showcnt;
	}

	public void setShowcnt(long showcnt) {
		this.showcnt = showcnt;
	}

	public float getArriveratio() {
		return arriveratio;
	}

	public void setArriveratio(float arriveratio) {
		this.arriveratio = arriveratio;
	}

	public long getClickcnt() {
		return clickcnt;
	}

	public void setClickcnt(long clickcnt) {
		this.clickcnt = clickcnt;
	}

	public long getArrivepagecnt() {
		return arrivepagecnt;
	}

	public void setArrivepagecnt(long arrivepagecnt) {
		this.arrivepagecnt = arrivepagecnt;
	}
}
