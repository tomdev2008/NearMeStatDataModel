package com.nearme.statistics.model.sys.systemTable;


public class SystemResourceTypeEntity {
	
	//包含有手机型号,来源代码，资源分类系统表参数
	private int systemID;
	private String systemName;
	
	//渠道
	private int channelID;
	private String channelName;
	
	//来源代码
	private String sourceCode;//来源code
	private String sourceDesc;//来源描述
	private String sourceName;//来源名称
	//手机
	private String serialName;//产品系列名称
	private String isOppo;//是否OPPO手机(Y/N)
	private String model;//机型
	private String onMarketTime;//上市时间
	private int platformID;
	private String platformName;//产品平台
	//资源分类表
	private String categoryID;//分类ID
	private String categoryName;//分类名称
	private int categoryLevel;//分类级别
	private String categoryParentID;//分类父类ID
	
	
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
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
	public String getOnMarketTime() {
		return onMarketTime;
	}
	public void setOnMarketTime(String onMarketTime) {
		this.onMarketTime = onMarketTime;
	}
	
	public String getSerialName() {
		return serialName;
	}
	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}
	
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
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
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	public int getChannelID() {
		return channelID;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setIsOppo(String isOppo) {
		this.isOppo = isOppo;
	}
	public String getIsOppo() {
		return isOppo;
	}
	public void setPlatformID(int platformID) {
		this.platformID = platformID;
	}
	public int getPlatformID() {
		return platformID;
	}
}
