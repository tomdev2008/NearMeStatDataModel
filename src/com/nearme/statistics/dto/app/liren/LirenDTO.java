package com.nearme.statistics.dto.app.liren;

import com.nearme.statistics.dto.BaseDTO;

/**
 * 丽人DTO
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class LirenDTO extends BaseDTO {
	/**
	 * 商品质量查询类型参数<br>
	 * browse_total   浏览总查询<br>
	 * browse_detail  浏览详情查询<br>
	 * like           喜欢数查询<br>
	 * quality        商品质量查询<br>
	 */
	private String type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
