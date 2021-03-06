package com.nearme.statistics.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 表DM_KV_EVENT_INDEX实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class KVEventIndexEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int statdate;
	private String statdateStr;
	private String systemID;
	private String eventID;
	private String model;
	private String appVersion;
	private int startImeis;
	private int startCounts;
	private int eventImeis;
	private int eventCounts;
	private Date updateTime;
	
	public int getStatdate() {
		return statdate;
	}
	public void setStatdate(int statdate) {
		this.statdate = statdate;
	}
	public String getSystemID() {
		return systemID;
	}
	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
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
	public int getStartImeis() {
		return startImeis;
	}
	public void setStartImeis(int startImeis) {
		this.startImeis = startImeis;
	}
	public int getStartCounts() {
		return startCounts;
	}
	public void setStartCounts(int startCounts) {
		this.startCounts = startCounts;
	}
	public int getEventImeis() {
		return eventImeis;
	}
	public void setEventImeis(int eventImeis) {
		this.eventImeis = eventImeis;
	}
	public int getEventCounts() {
		return eventCounts;
	}
	public void setEventCounts(int eventCounts) {
		this.eventCounts = eventCounts;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getStatdateStr() {
		return statdateStr;
	}
	public void setStatdateStr(String statdateStr) {
		this.statdateStr = statdateStr;
	}

}
