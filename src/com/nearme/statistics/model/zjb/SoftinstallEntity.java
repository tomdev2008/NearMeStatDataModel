package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 软件安装排行
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class SoftinstallEntity implements java.io.Serializable {
	private static final long serialVersionUID = -205351673683985453L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String softID;// 软件ID
	private String softName;// 软件名称
	private long softInstall;// 软件安装数量

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

	public String getSoftID() {
		return softID;
	}

	public void setSoftID(String softID) {
		this.softID = softID;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public long getSoftInstall() {
		return softInstall;
	}

	public void setSoftInstall(long softInstall) {
		this.softInstall = softInstall;
	}
}
