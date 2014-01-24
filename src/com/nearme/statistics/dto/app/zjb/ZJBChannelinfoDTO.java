package com.nearme.statistics.dto.app.zjb;

/**
 * 渠道管理
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-24
 */
public class ZJBChannelinfoDTO {
	private int channelID;// 渠道号
	private String channelName;// 渠道名
	private String channelDesc;// 渠道描述


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
