package com.nearme.statistics.model.sys.systemTable;

import java.io.Serializable;

/**
 * 游戏中心产品信息实体类
 *
 * @author 刘超
 * @version 1.0
 * @since 1.0,2012-8-16
 */
public class SystemGCProductInfoEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productID;
	private String productName;
	private int systemID;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

}
