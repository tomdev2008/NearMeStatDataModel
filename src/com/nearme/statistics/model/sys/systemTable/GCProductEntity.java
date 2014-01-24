package com.nearme.statistics.model.sys.systemTable;

/**
 * 游戏中心产品
 * @author 80053813
 *
 */
public class GCProductEntity extends BaseSystemEntity {

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
