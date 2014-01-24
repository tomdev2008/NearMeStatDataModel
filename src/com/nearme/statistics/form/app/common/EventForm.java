package com.nearme.statistics.form.app.common;

import com.nearme.statistics.form.BaseForm;

/**
 * 自定义事件
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-10-23
 */
public class EventForm extends BaseForm{
	private int id;
	private String eventID;	//事件ID
	private String eventName;	// 事件Key
	private String eventDesc;	// 事件说明
	private String eventKey;	// 事件Key
	private String eventValue;	// 事件Key
	
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	public String getEventValue() {
		return eventValue;
	}
	public void setEventValue(String eventValue) {
		this.eventValue = eventValue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
}
