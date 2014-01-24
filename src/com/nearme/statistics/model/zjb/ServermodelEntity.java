package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 服务机型
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class ServermodelEntity implements java.io.Serializable {
	private static final long serialVersionUID = -205351673683985453L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String pinpai;// 品牌
	private String model;// 机型
	private long connectCnt;// 连接次数
	private float zhanbi;// 占比
	private long installcnt;//安装量
	private float installratio;//安装占比
	private long avginstall;//平均安装量

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

	public String getPinpai() {
		return pinpai;
	}

	public void setPinpai(String pinpai) {
		this.pinpai = pinpai;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getConnectCnt() {
		return connectCnt;
	}

	public void setConnectCnt(long connectCnt) {
		this.connectCnt = connectCnt;
	}

	public float getZhanbi() {
		return zhanbi;
	}

	public void setZhanbi(float zhanbi) {
		this.zhanbi = zhanbi;
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
}
