package com.nearme.statistics.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 基本分析结果实体
 * @author 80053813 罗勇
 * @version 1.0
 * @since 1.0 2012-5-14
 */
public class BaseFxModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 数据日期
	 */
	private Date statDate;
	private int systemID;
	private String appVersion;
	private String model;
	private int networkID;
	private int channelID;
	private String sourceCode;
	private String eventTag;
	private int cntImei;
	private int cntUser;
	private int cntTimes;
	
	private String systemName;
	private String networkName;
	private String channelName;
	
	private String rateCntImeiStr;
	private String rateCntUserStr;
	private String rateCntTimesStr;
	
	public Date getStatDate() {
		return statDate;
	}
	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
	public int getSystemID() {
		return systemID;
	}
	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getNetworkID() {
		return networkID;
	}
	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getEventTag() {
		return eventTag;
	}
	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
	}
	public int getCntImei() {
		return cntImei;
	}
	public void setCntImei(int cntImei) {
		this.cntImei = cntImei;
	}
	public int getCntUser() {
		return cntUser;
	}
	public void setCntUser(int cntUser) {
		this.cntUser = cntUser;
	}
	public int getCntTimes() {
		return cntTimes;
	}
	public void setCntTimes(int cntTimes) {
		this.cntTimes = cntTimes;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setRateCntImeiStr(String rateCntImeiStr) {
		this.rateCntImeiStr = rateCntImeiStr;
	}
	public String getRateCntImeiStr() {
		return rateCntImeiStr;
	}
	public void setRateCntUserStr(String rateCntUserStr) {
		this.rateCntUserStr = rateCntUserStr;
	}
	public String getRateCntUserStr() {
		return rateCntUserStr;
	}
	public void setRateCntTimesStr(String rateCntTimesStr) {
		this.rateCntTimesStr = rateCntTimesStr;
	}
	public String getRateCntTimesStr() {
		return rateCntTimesStr;
	}
}
