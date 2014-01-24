package com.nearme.statistics.dto.sys;

import com.nearme.statistics.form.sys.ChannelForm;
import com.nearme.statistics.model.sys.systemTable.BaseSystemEntity;

public class ChannelDTO extends BaseSystemEntity {
	private int channelID;
	private String channelName;
	private String channelDesc;
	
	public boolean fillFromForm(ChannelForm form){
		this.channelID = form.getChannelID();
		this.channelName = form.getChannelName();
		this.channelDesc = form.getChannelDesc();
		
		return true;
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
