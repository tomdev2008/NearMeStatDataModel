package com.nearme.statistics.dto.sys;

import com.nearme.statistics.form.sys.AppInfoForm;
import com.nearme.statistics.model.sys.systemTable.BaseSystemEntity;

public class AppInfoDTO extends BaseSystemEntity {
	private int systemID;//系统编号
	private String systemGroup;//内容平台、社交平台
	private String systemCode;//SSO分配的应用编码
	private String systemName;//系统名称
	private String systemDesc;//系统描述
	
	public boolean fillFromForm(AppInfoForm form){
		this.systemID = form.getSystemID();
		this.systemGroup = form.getSystemGroup();
		this.systemCode = form.getSystemCode();
		this.systemName = form.getSystemName();
		this.setSystemDesc(form.getSystemDesc());
		
		return true;
	}
	
	public int getSystemID() {
		return systemID;
	}
	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}

	public String getSystemDesc() {
		return systemDesc;
	}

	public void setSystemGroup(String systemGroup) {
		this.systemGroup = systemGroup;
	}

	public String getSystemGroup() {
		return systemGroup;
	}
}
