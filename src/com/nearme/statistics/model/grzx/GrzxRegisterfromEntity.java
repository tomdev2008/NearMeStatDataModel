package com.nearme.statistics.model.grzx;

import java.io.Serializable;
import java.util.Date;

/**
 * 注册来源
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class GrzxRegisterfromEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long allreg;// 全平台注册帐号数
	private long mobilereg;// 手机注册帐号数
	private long emailreg;// 邮箱注册帐号数
	private long nobandreg;// 无绑定信息注册帐号数
	private long visitreg;// 游客帐号注册数
	private long thirdreg;// 第三方帐号注册数

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

	public long getAllreg() {
		return allreg;
	}

	public void setAllreg(long allreg) {
		this.allreg = allreg;
	}

	public long getMobilereg() {
		return mobilereg;
	}

	public void setMobilereg(long mobilereg) {
		this.mobilereg = mobilereg;
	}

	public long getEmailreg() {
		return emailreg;
	}

	public void setEmailreg(long emailreg) {
		this.emailreg = emailreg;
	}

	public long getNobandreg() {
		return nobandreg;
	}

	public void setNobandreg(long nobandreg) {
		this.nobandreg = nobandreg;
	}

	public long getVisitreg() {
		return visitreg;
	}

	public void setVisitreg(long visitreg) {
		this.visitreg = visitreg;
	}

	public long getThirdreg() {
		return thirdreg;
	}

	public void setThirdreg(long thirdreg) {
		this.thirdreg = thirdreg;
	}
}
