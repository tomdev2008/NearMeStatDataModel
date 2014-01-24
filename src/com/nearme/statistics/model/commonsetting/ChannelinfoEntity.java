package com.nearme.statistics.model.commonsetting;

/**
 * 渠道管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class ChannelinfoEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5652634774199196661L;
	
	private int systemID;// 应用标识
	private int channelID;// 渠道号
	private String channelName;// 渠道名
	private String channelDesc;// 渠道描述

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public int getChannelID() {
		return channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelDesc() {
		return channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}
}
