package com.nearme.statistics.dto.sys;

import com.nearme.statistics.form.sys.GCProductForm;
import com.nearme.statistics.model.sys.systemTable.BaseSystemEntity;

public class GCProductDTO extends BaseSystemEntity{
	private String productID;
	private String productName;
	private int systemID;
	
	public boolean fillFromForm(GCProductForm form){
		this.systemID = form.getSystemID();
		this.productID = form.getProductID();
		this.productName = form.getProductName();
		
		return true;
	}
	
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
