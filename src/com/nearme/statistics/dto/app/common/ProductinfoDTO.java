package com.nearme.statistics.dto.app.common;

import com.nearme.statistics.dto.BaseDTO;

/**
 * 游戏DTO
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class ProductinfoDTO extends BaseDTO {
	private String productname;// 游戏名
	private String productIDs;// 游戏产品IDs

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductIDs() {
		return productIDs;
	}

	public void setProductIDs(String productIDs) {
		this.productIDs = productIDs;
	}
}
