package com.nearme.statistics.model.coloros;

import java.util.Date;

/**
 * @author 朱峰
 * @function 安装排行
 * @version 1.0
 * @since 1.0, 2013-10-21
 */
public class COSInstallrankEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private String position;// 排名
	private String appname;// 应用
	private long installcnt;// 安装次数
	private long upgradecnt;// 升级次数

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

	public String getStatDatestr() {
		return statDatestr;
	}

	public void setStatDatestr(String statDatestr) {
		this.statDatestr = statDatestr;
	}

	public String getStatEndDatestr() {
		return statEndDatestr;
	}

	public void setStatEndDatestr(String statEndDatestr) {
		this.statEndDatestr = statEndDatestr;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public long getInstallcnt() {
		return installcnt;
	}

	public void setInstallcnt(long installcnt) {
		this.installcnt = installcnt;
	}

	public long getUpgradecnt() {
		return upgradecnt;
	}

	public void setUpgradecnt(long upgradecnt) {
		this.upgradecnt = upgradecnt;
	}
}
