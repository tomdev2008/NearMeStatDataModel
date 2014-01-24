package com.nearme.statistics.dto.app.common;

/**
 * 安装来源管理
 * @author gxfeng
 * @version 1.0
 * @since 1.0, 2013-12-30
 */
public class InstallSourceDTO {
	private int systemID;// 应用标识
	private int packageName;// 包名
	private String appName;// 应用名
	private String appType;// 应用类型
	private String lastUpdateTime;
	
	public int getSystemID() {
		return systemID;
	}
	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}
	public int getPackageName() {
		return packageName;
	}
	public void setPackageName(int packageName) {
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
