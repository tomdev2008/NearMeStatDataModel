package com.nearme.statistics.dto.app.store;

import com.nearme.statistics.dto.BaseDTO;

/**
 * 商店DTO
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class StoreDTO extends BaseDTO {
	private String userType;//用户类型
	private int statDateInt;
	private int startDateInt;
	private int endDateInt;
	private String channel;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
