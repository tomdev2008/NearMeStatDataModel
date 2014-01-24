package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 基础数据
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class BasedateEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long users;// 登陆用户数
	private long totoalusers;// 总登陆用户数
	private long newusers;// 新增登陆用户数
	private long computers;// 用户数
	private long totalpcs;// 总用户数
	private long newpcs;// 新增用户数
	private long conns;// 服务手机数
	private long apks;// 软件安装量
	private float avguser;// 人均安装量(apks / computers)
	private float avgphone;// 台均安装量(apks / conns)

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

	public long getUsers() {
		return users;
	}

	public void setUsers(long users) {
		this.users = users;
	}

	public long getTotoalusers() {
		return totoalusers;
	}

	public void setTotoalusers(long totoalusers) {
		this.totoalusers = totoalusers;
	}

	public long getNewusers() {
		return newusers;
	}

	public void setNewusers(long newusers) {
		this.newusers = newusers;
	}

	public long getComputers() {
		return computers;
	}

	public void setComputers(long computers) {
		this.computers = computers;
	}

	public long getTotalpcs() {
		return totalpcs;
	}

	public void setTotalpcs(long totalpcs) {
		this.totalpcs = totalpcs;
	}

	public long getNewpcs() {
		return newpcs;
	}

	public void setNewpcs(long newpcs) {
		this.newpcs = newpcs;
	}

	public long getConns() {
		return conns;
	}

	public void setConns(long conns) {
		this.conns = conns;
	}

	public long getApks() {
		return apks;
	}

	public void setApks(long apks) {
		this.apks = apks;
	}

	public float getAvguser() {
		return avguser;
	}

	public void setAvguser(float avguser) {
		this.avguser = avguser;
	}

	public float getAvgphone() {
		return avgphone;
	}

	public void setAvgphone(float avgphone) {
		this.avgphone = avgphone;
	}
}
