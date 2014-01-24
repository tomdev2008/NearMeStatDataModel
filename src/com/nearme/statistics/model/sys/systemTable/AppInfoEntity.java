package com.nearme.statistics.model.sys.systemTable;

/**
 * 应用
 * @author 80053813
 *
 */
public class AppInfoEntity extends BaseSystemEntity{

	private int systemID;//系统编号
	private String systemGroup;//内容平台、社交平台
	private String systemCode;//SSO分配的应用编码
	private String systemName;//系统名称
	private String systemDesc;//应用描述
	
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
