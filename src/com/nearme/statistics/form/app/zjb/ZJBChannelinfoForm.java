package com.nearme.statistics.form.app.zjb;

import com.nearme.statistics.form.BaseForm;


/**
 * 渠道管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-24
 */
public class ZJBChannelinfoForm extends BaseForm{
	private String channelID;// 渠道号
	private String channelName;// 渠道名
	private String channelDesc;// 渠道描述

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
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
