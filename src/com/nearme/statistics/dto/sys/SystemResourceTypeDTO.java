package com.nearme.statistics.dto.sys;

import java.util.Date;

public class SystemResourceTypeDTO {
	
	//包含有手机型号,来源代码，资源分类系统表参数
	private int systemID;
	//来源代码
	private String sourceCode;//来源code
	private String sourceDesc;//来源描述
	
	//手机
	private String model;//机型
	private Date onMarketTime;//上市时间
	private int serialID;//产品系列名称
	private int platformID;//产品平台
	//资源分类表
	private String categoryID;//分类ID
	private String categoryName;//分类名称
	private int categoryLevel;//分类级别
	private String categoryParentID;//分类父类ID
	public int getSystemID() {
		return systemID;
	}
	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getSourceDesc() {
		return sourceDesc;
	}
	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date getOnMarketTime() {
		return onMarketTime;
	}
	public void setOnMarketTime(Date onMarketTime) {
		this.onMarketTime = onMarketTime;
	}
	public int getSerialID() {
		return serialID;
	}
	public void setSerialID(int serialID) {
		this.serialID = serialID;
	}
	public int getPlatformID() {
		return platformID;
	}
	public void setPlatformID(int platformID) {
		this.platformID = platformID;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryLevel() {
		return categoryLevel;
	}
	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	public String getCategoryParentID() {
		return categoryParentID;
	}
	public void setCategoryParentID(String categoryParentID) {
		this.categoryParentID = categoryParentID;
	}
}
