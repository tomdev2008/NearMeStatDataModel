package com.nearme.statistics.dto.sys;

import java.util.Date;

import com.nearme.statistics.form.sys.AppVersionForm;
import com.nearme.statistics.model.sys.systemTable.BaseSystemEntity;

public class AppVersionDTO extends BaseSystemEntity {
	private int systemID;//系统编号
	private String appVersion;//版本
	private String versionName;//版本别名
	private Date detectDate;//发现/添加日期
	private String detectType;//统计系统自动发现:A,管理员手动添加:M.(auto,manual)
	private String hide;//是否在相关下拉菜单中隐藏:Y/N
	
	public boolean fillFromForm(AppVersionForm form){
		this.systemID = form.getSystemID();
		this.appVersion = form.getAppVersion();
		this.detectDate = form.getDetectDate();
		this.detectType = form.getDetectType();
		this.hide = form.getHide();
		
		return true;
	}
	
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

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
}
