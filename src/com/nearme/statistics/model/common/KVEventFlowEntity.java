package com.nearme.statistics.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 表BT_KV_EVENT_FLOW实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class KVEventFlowEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String eventFlowName;
	private String eventFlowDesc;
	private int eventFlowStep;
	private String eventFlowSteps;
	private String systemID;
	private String finalEventName;
	private String finalEventID;
	private int eventCounts;
	private String eventCR;
	private String eventTCR;
	private int eventImeis;
	private String eventICR;
	private String eventITCR;
	private Date updateTime;
	private int statDate;
	private String prevEventName;
	
	public String getEventFlowName() {
		return eventFlowName;
	}
	public void setEventFlowName(String eventFlowName) {
		this.eventFlowName = eventFlowName;
	}
	public String getEventFlowDesc() {
		return eventFlowDesc;
	}
	public void setEventFlowDesc(String eventFlowDesc) {
		this.eventFlowDesc = eventFlowDesc;
	}
	public int getEventFlowStep() {
		return eventFlowStep;
	}
	public void setEventFlowStep(int eventFlowStep) {
		this.eventFlowStep = eventFlowStep;
	}
	public String getSystemID() {
		return systemID;
	}
	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
	public String getEventFlowSteps() {
		return eventFlowSteps;
	}
	public void setEventFlowSteps(String eventFlowSteps) {
		this.eventFlowSteps = eventFlowSteps;
	}
	public String getFinalEventName() {
		return finalEventName;
	}
	public void setFinalEventName(String finalEventName) {
		this.finalEventName = finalEventName;
	}
	public String getFinalEventID() {
		return finalEventID;
	}
	public void setFinalEventID(String finalEventID) {
		this.finalEventID = finalEventID;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getEventCounts() {
		return eventCounts;
	}
	public void setEventCounts(int eventCounts) {
		this.eventCounts = eventCounts;
	}
	public String getEventCR() {
		return eventCR;
	}
	public void setEventCR(String eventCR) {
		this.eventCR = eventCR;
	}
	public String getEventTCR() {
		return eventTCR;
	}
	public void setEventTCR(String eventTCR) {
		this.eventTCR = eventTCR;
	}
	public int getEventImeis() {
		return eventImeis;
	}
	public void setEventImeis(int eventImeis) {
		this.eventImeis = eventImeis;
	}
	public String getEventICR() {
		return eventICR;
	}
	public void setEventICR(String eventICR) {
		this.eventICR = eventICR;
	}
	public String getEventITCR() {
		return eventITCR;
	}
	public void setEventITCR(String eventITCR) {
		this.eventITCR = eventITCR;
	}
	public int getStatDate() {
		return statDate;
	}
	public void setStatDate(int statDate) {
		this.statDate = statDate;
	}
	public String getPrevEventName() {
		return prevEventName;
	}
	public void setPrevEventName(String prevEventName) {
		this.prevEventName = prevEventName;
	}

}
