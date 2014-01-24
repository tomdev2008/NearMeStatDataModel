package com.nearme.statistics.form.sys;

import java.util.Date;

/**
 * 
 * @author 80053813
 *
 */
public class AppVersionForm {
	private int systemID;//系统编号
	private String appVersion;//版本
	private Date detectDate;//发现/添加日期
	private String detectType;//统计系统自动发现:A,管理员手动添加:M.(auto,manual)
	private String hide;//是否在相关下拉菜单中隐藏:Y/N
	
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
	public Date getDetectDate() {
		return detectDate;
	}
	public void setDetectDate(Date detectDate) {
		this.detectDate = detectDate;
	}
	public String getDetectType() {
		return detectType;
	}
	public void setDetectType(String detectType) {
		this.detectType = detectType;
	}
	public String getHide() {
		return hide;
	}
	public void setHide(String hide) {
		this.hide = hide;
	}
}
