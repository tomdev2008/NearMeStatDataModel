package com.nearme.statistics.dto.app.common;

import com.nearme.statistics.dto.BaseDTO;

/**
 * 自定义事件查询参数实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class EventDTO extends BaseDTO{

	private int id;
	private String eventID;
	private String eventName;
	private String eventDesc;
	private String eventKey;
	private String eventValue;
	private int startDateInt;
	private int endDateInt;

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
