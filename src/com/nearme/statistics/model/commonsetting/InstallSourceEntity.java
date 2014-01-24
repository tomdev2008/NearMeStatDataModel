package com.nearme.statistics.model.commonsetting;

/**
 * 安装来源
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-30
 */
public class InstallSourceEntity implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String systemID;// 应用标识
	private String packageName;// 包名
	private String appName;// 应用名
	private String appType;// 应用类型
	private String lastUpdateTime;
	
	public String getSystemID() {
		return systemID;
	}
	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
