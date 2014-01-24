package com.nearme.statistics.dto;

import java.util.Date;

public class LogDTO {

	private String username;// 访问者
	private String visiturl;// 访问位置

	private int systemID;// 产品id
	private Date statDate;
	private Date endDate;
	private String lidu;// 粒度（日、周、月）
	private String model; // 机型
	private String appVersion;// 版本
	private String networkType;// 网络环境
	private String channel;// 渠道
	private String imeitype;// 类型（启动IMEI、新增IMEI）
	private String groupcode;// 用户分组code
	private String actioncode;// 用户行为code
	private String condition1;// 特殊条件1
	private String condition2;// 特殊条件2
	private String condition3;// 特殊条件3
	private String condition4;// 特殊条件4
	private String condition5;// 特殊条件5

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVisiturl() {
		return visiturl;
	}

	public void setVisiturl(String visiturl) {
		this.visiturl = visiturl;
	}

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLidu() {
		return lidu;
	}

	public void setLidu(String lidu) {
		this.lidu = lidu;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getImeitype() {
		return imeitype;
	}

	public void setImeitype(String imeitype) {
		this.imeitype = imeitype;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}

	public String getCondition3() {
		return condition3;
	}

	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}

	public String getCondition4() {
		return condition4;
	}

	public void setCondition4(String condition4) {
		this.condition4 = condition4;
	}

	public String getCondition5() {
		return condition5;
	}

	public void setCondition5(String condition5) {
		this.condition5 = condition5;
	}

}
