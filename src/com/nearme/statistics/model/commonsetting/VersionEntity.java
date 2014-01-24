package com.nearme.statistics.model.commonsetting;

/**
 * 各应用版本信息
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-6
 */
public class VersionEntity implements java.io.Serializable{
	private static final long serialVersionUID = 7006779353267613074L;
	
	private int systemID;// 系统编号
	private String appVersion;// 版本
	private String versionName;// 版本别名
	private String hide;// 是否在相关下拉菜单中隐藏:Y/N

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}
}
