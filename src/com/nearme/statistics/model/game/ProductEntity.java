package com.nearme.statistics.model.game;

import java.io.Serializable;

/**
 * 产品列表Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-4
 */
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	private String productid;// 产品id
	private String productname;// 产品名

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
}
