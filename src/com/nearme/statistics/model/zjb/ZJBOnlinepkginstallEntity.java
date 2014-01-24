package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 在线资源包安装
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBOnlinepkginstallEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long installtotal;// 软件安装总量
	private String softname;// 资源包名
	private long installcnt;// 软件安装量
	private float installratio;// 软件安装占比
	private long avginstall;// 平均单个软件安装量
	private long clickinstall;// 一键安装量
	private long pkginnerinstall;// 包内安装量
	
	private long innerpkgcnt;//包内软件数

	public long getInnerpkgcnt() {
		return innerpkgcnt;
	}

	public void setInnerpkgcnt(long innerpkgcnt) {
		this.innerpkgcnt = innerpkgcnt;
	}

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

	public long getInstalltotal() {
		return installtotal;
	}

	public void setInstalltotal(long installtotal) {
		this.installtotal = installtotal;
	}

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public long getInstallcnt() {
		return installcnt;
	}

	public void setInstallcnt(long installcnt) {
		this.installcnt = installcnt;
	}

	public float getInstallratio() {
		return installratio;
	}

	public void setInstallratio(float installratio) {
		this.installratio = installratio;
	}

	public long getAvginstall() {
		return avginstall;
	}

	public void setAvginstall(long avginstall) {
		this.avginstall = avginstall;
	}

	public long getClickinstall() {
		return clickinstall;
	}

	public void setClickinstall(long clickinstall) {
		this.clickinstall = clickinstall;
	}

	public long getPkginnerinstall() {
		return pkginnerinstall;
	}

	public void setPkginnerinstall(long pkginnerinstall) {
		this.pkginnerinstall = pkginnerinstall;
	}
}
