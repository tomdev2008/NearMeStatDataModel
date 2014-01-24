package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 在线资源包内安装
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBOnlinepkginnerinstallEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String softname;// 软件名
	private String pkgname;// 资源包名
	private long innerinstallcnt;// 包内软件安装量
	private long installtotal;// 软件安装总量
	private float innerinstallratio;// 包内软件安装占比

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

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public String getPkgname() {
		return pkgname;
	}

	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}

	public long getInnerinstallcnt() {
		return innerinstallcnt;
	}

	public void setInnerinstallcnt(long innerinstallcnt) {
		this.innerinstallcnt = innerinstallcnt;
	}

	public long getInstalltotal() {
		return installtotal;
	}

	public void setInstalltotal(long installtotal) {
		this.installtotal = installtotal;
	}

	public float getInnerinstallratio() {
		return innerinstallratio;
	}

	public void setInnerinstallratio(float innerinstallratio) {
		this.innerinstallratio = innerinstallratio;
	}
}
