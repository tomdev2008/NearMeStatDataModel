package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 单个资源运营
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBRunpointEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String softname;// 软件名
	private long installtotal;// 安装总量
	private long selfcnt;// 自定义资源包安装量
	private long onlinerescnt;// 在线资源包安装量
	private long searchcnt;// 搜索安装量
	private long onlinesoftcnt;// 在线软件安装量
	private long pushinstall;// 强推安装量
	private long installercnt;// 安装器安装量
	
	private String lidu;//日周月

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

	public long getInstalltotal() {
		return installtotal;
	}

	public void setInstalltotal(long installtotal) {
		this.installtotal = installtotal;
	}

	public long getSelfcnt() {
		return selfcnt;
	}

	public void setSelfcnt(long selfcnt) {
		this.selfcnt = selfcnt;
	}

	public long getOnlinerescnt() {
		return onlinerescnt;
	}

	public void setOnlinerescnt(long onlinerescnt) {
		this.onlinerescnt = onlinerescnt;
	}

	public long getSearchcnt() {
		return searchcnt;
	}

	public void setSearchcnt(long searchcnt) {
		this.searchcnt = searchcnt;
	}

	public long getOnlinesoftcnt() {
		return onlinesoftcnt;
	}

	public void setOnlinesoftcnt(long onlinesoftcnt) {
		this.onlinesoftcnt = onlinesoftcnt;
	}

	public long getPushinstall() {
		return pushinstall;
	}

	public void setPushinstall(long pushinstall) {
		this.pushinstall = pushinstall;
	}

	public long getInstallercnt() {
		return installercnt;
	}

	public void setInstallercnt(long installercnt) {
		this.installercnt = installercnt;
	}

	public String getLidu() {
		return lidu;
	}

	public void setLidu(String lidu) {
		this.lidu = lidu;
	}
}
