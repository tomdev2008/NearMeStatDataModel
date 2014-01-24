package com.nearme.statistics.model.sys.systemTable;

/**
 * 渠道
 * @author 80053813
 *
 */
public class ChannelEntity extends BaseSystemEntity {

	private int channelID;
	private String channelName;
	private String channelDesc;
	
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
