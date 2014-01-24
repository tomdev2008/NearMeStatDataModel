package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 月活天数和分布
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBActivedaysdistributeEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String activedays;// 活跃天数
	private long usercnt;// 用户数
	private long newusercnt;// 新增用户数
	private long loginuser;// 登陆用户
	private long newloginuser;// 新增登陆用户
	private long startcnt;// 启动次数
	private long installapp;// 安装量
	private float installratio;// 安装量占比
	private long servemobile;// 服务手机数
	private long avginstaluser;// 人均安装量
	private long avginstallmobel;// 台均安装量
	private float avgactdays;//月平均活跃天数

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

	public String getActivedays() {
		return activedays;
	}

	public void setActivedays(String activedays) {
		this.activedays = activedays;
	}

	public long getUsercnt() {
		return usercnt;
	}

	public void setUsercnt(long usercnt) {
		this.usercnt = usercnt;
	}

	public long getNewusercnt() {
		return newusercnt;
	}

	public void setNewusercnt(long newusercnt) {
		this.newusercnt = newusercnt;
	}

	public long getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(long loginuser) {
		this.loginuser = loginuser;
	}

	public long getNewloginuser() {
		return newloginuser;
	}

	public void setNewloginuser(long newloginuser) {
		this.newloginuser = newloginuser;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public long getInstallapp() {
		return installapp;
	}

	public void setInstallapp(long installapp) {
		this.installapp = installapp;
	}

	public long getServemobile() {
		return servemobile;
	}

	public void setServemobile(long servemobile) {
		this.servemobile = servemobile;
	}

	public long getAvginstaluser() {
		return avginstaluser;
	}

	public void setAvginstaluser(long avginstaluser) {
		this.avginstaluser = avginstaluser;
	}

	public long getAvginstallmobel() {
		return avginstallmobel;
	}

	public void setAvginstallmobel(long avginstallmobel) {
		this.avginstallmobel = avginstallmobel;
	}

	public float getInstallratio() {
		return installratio;
	}

	public void setInstallratio(float installratio) {
		this.installratio = installratio;
	}

	public float getAvgactdays() {
		return avgactdays;
	}

	public void setAvgactdays(float avgactdays) {
		this.avgactdays = avgactdays;
	}
}
