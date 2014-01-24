package com.nearme.statistics.form.app.common;

import com.nearme.statistics.form.BaseForm;

/**
 * 事件流
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-10-23
 */
public class EventFlowForm extends BaseForm{
	private String eventFlowName;	//事件流名称
	private String eventFlowDesc;	//事件流描述
	private String[] eventID;	//事件流中的各个事件ID
	private int eventFlowStep;	//事件流描述
	
	private String eventName;	//事件名称
	private String prevEventName;	//上一步事件名称
	
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
	public String[] getEventID() {
		return eventID;
	}
	public void setEventID(String[] eventID) {
		this.eventID = eventID;
	}
	public int getEventFlowStep() {
		return eventFlowStep;
	}
	public void setEventFlowStep(int eventFlowStep) {
		this.eventFlowStep = eventFlowStep;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getPrevEventName() {
		return prevEventName;
	}
	public void setPrevEventName(String prevEventName) {
		this.prevEventName = prevEventName;
	}
}
