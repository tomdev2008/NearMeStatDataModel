package com.nearme.statistics.dto.app.coloros;

import com.nearme.statistics.dto.BaseDTO;

/**
 * coloros DTO
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-18
 */
public class ColorosDTO extends BaseDTO {
	// ‘yyyyMMdd’格式的时间
	private String startDateStr;// 起始时间
	private String endDateStr;// 结束时间
	
	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
}
