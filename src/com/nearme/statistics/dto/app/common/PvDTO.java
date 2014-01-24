package com.nearme.statistics.dto.app.common;

/**
 * 页面访问参数实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-18
 */
public class PvDTO {
	private int statDateInt;
	private int startDateInt;
	private int endDateInt;
	private String systemID;
	private String appVersion;
	private String channelID;
	private String lidu;// 粒度（日、周、月）
	private String statDates;
	private String flag;
	private String curPage;
	
	public int getStatDateInt() {
		return statDateInt;
	}
	public void setStatDateInt(int statDateInt) {
		this.statDateInt = statDateInt;
	}
	public int getStartDateInt() {
		return startDateInt;
	}
	public void setStartDateInt(int startDateInt) {
		this.startDateInt = startDateInt;
	}
	public int getEndDateInt() {
		return endDateInt;
	}
	public void setEndDateInt(int endDateInt) {
		this.endDateInt = endDateInt;
	}
	public String getSystemID() {
		return systemID;
	}
	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getChannelID() {
		return channelID;
	}
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}
	public String getLidu() {
		return lidu;
	}
	public void setLidu(String lidu) {
		this.lidu = lidu;
	}
	public String getStatDates() {
		return statDates;
	}
	public void setStatDates(String statDates) {
		this.statDates = statDates;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
}
