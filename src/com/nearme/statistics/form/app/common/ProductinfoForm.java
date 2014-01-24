package com.nearme.statistics.form.app.common;

import com.nearme.statistics.form.BaseForm;

/**
 * 产品名Form
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class ProductinfoForm extends BaseForm {
	private String productname;// 产品名
	private String productIDs;// 游戏产品IDs

	public String getProductIDs() {
		return productIDs;
	}

	public void setProductIDs(String productIDs) {
		this.productIDs = productIDs;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
}
