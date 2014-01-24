package com.nearme.statistics.dto.sys;

public class SystemTableDTO {
	
	private int id;//公共id，包括联网方式，应用名称，平台等需要用到ID的地方
	//联网方式
	private String networkName;//联网方式名称
	//应用名称
	private String appKey;//应用key
	private String appName;//应用名称
	private String appDesc;//应用描述
	//平台
	private String platformName;//平台名称
	//渠道
	private String channelName;//渠道名称
	//分享目标
	private String shareTargetName;//分享目标名称
	//支付方式
	private String payTypeName;//支付方式名称
	//支付来源
	private String paySourceName;//支付来源名称
	//手机
	private String mobileTableName;//手机系列表
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getShareTargetName() {
		return shareTargetName;
	}
	public void setShareTargetName(String shareTargetName) {
		this.shareTargetName = shareTargetName;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getPaySourceName() {
		return paySourceName;
	}
	public void setPaySourceName(String paySourceName) {
		this.paySourceName = paySourceName;
	}
	public String getMobileTableName() {
		return mobileTableName;
	}
	public void setMobileTableName(String mobileTableName) {
		this.mobileTableName = mobileTableName;
	}
}
