package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品日报Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-4
 */
public class ProductreportdailyEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long totaluser;// 总用户
	private long totaldownload;// 总下载
	private long loginuser;// 登陆用户
	private long newimei;// 新增手机
	private long newuser;// 新增用户
	private long downloadtimes;// 下载次数
	private long startimei;// 启动IMEI
	private long starttimes;// 启动次数
	private long silentuser;// 沉默用户
	private long lostuser;// 流失用户
	private float nrr1;
	private float nrr2;
	private float nrr3;
	private float lrr1;
	private float lrr2;
	private float lrr3;

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

	public long getTotaluser() {
		return totaluser;
	}

	public void setTotaluser(long totaluser) {
		this.totaluser = totaluser;
	}

	public long getTotaldownload() {
		return totaldownload;
	}

	public void setTotaldownload(long totaldownload) {
		this.totaldownload = totaldownload;
	}

	public long getNewimei() {
		return newimei;
	}

	public void setNewimei(long newimei) {
		this.newimei = newimei;
	}

	public long getNewuser() {
		return newuser;
	}

	public void setNewuser(long newuser) {
		this.newuser = newuser;
	}

	public long getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(long loginuser) {
		this.loginuser = loginuser;
	}

	public long getDownloadtimes() {
		return downloadtimes;
	}

	public void setDownloadtimes(long downloadtimes) {
		this.downloadtimes = downloadtimes;
	}

	public long getStartimei() {
		return startimei;
	}

	public void setStartimei(long startimei) {
		this.startimei = startimei;
	}

	public long getStarttimes() {
		return starttimes;
	}

	public void setStarttimes(long starttimes) {
		this.starttimes = starttimes;
	}

	public long getSilentuser() {
		return silentuser;
	}

	public void setSilentuser(long silentuser) {
		this.silentuser = silentuser;
	}

	public long getLostuser() {
		return lostuser;
	}

	public void setLostuser(long lostuser) {
		this.lostuser = lostuser;
	}

	public float getNrr1() {
		return nrr1;
	}

	public void setNrr1(float nrr1) {
		this.nrr1 = nrr1;
	}

	public float getNrr2() {
		return nrr2;
	}

	public void setNrr2(float nrr2) {
		this.nrr2 = nrr2;
	}

	public float getNrr3() {
		return nrr3;
	}

	public void setNrr3(float nrr3) {
		this.nrr3 = nrr3;
	}

	public float getLrr1() {
		return lrr1;
	}

	public void setLrr1(float lrr1) {
		this.lrr1 = lrr1;
	}

	public float getLrr2() {
		return lrr2;
	}

	public void setLrr2(float lrr2) {
		this.lrr2 = lrr2;
	}

	public float getLrr3() {
		return lrr3;
	}

	public void setLrr3(float lrr3) {
		this.lrr3 = lrr3;
	}

}
