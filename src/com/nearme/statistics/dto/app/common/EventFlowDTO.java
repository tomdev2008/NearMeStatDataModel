package com.nearme.statistics.dto.app.common;

import com.nearme.statistics.dto.BaseDTO;

/**
 * 自定义事件流查询参数实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-10-28
 */
public class EventFlowDTO extends BaseDTO{
	private String eventFlowName;
	private String eventFlowDesc;
	private int eventFlowStep;
	private String finalEventID;
	private int statFromDate;
	private int statDate;
	private int statEndDate;
	
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
	public String getFinalEventID() {
		return finalEventID;
	}
	public void setFinalEventID(String finalEventID) {
		this.finalEventID = finalEventID;
	}
	public int getStatDate() {
		return statDate;
	}
	public void setStatDate(int statDate) {
		this.statDate = statDate;
	}
	public int getStatEndDate() {
		return statEndDate;
	}
	public void setStatEndDate(int statEndDate) {
		this.statEndDate = statEndDate;
	}
	public int getStatFromDate() {
		return statFromDate;
	}
	public void setStatFromDate(int statFromDate) {
		this.statFromDate = statFromDate;
	}

}
