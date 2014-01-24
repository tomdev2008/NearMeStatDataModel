package com.nearme.statistics.model.zjb;

/**
 * 装机宝版本信息
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class ZjbVersionEntity {
	private String appVersion;//版本
	private String hide;//是否在相关下拉菜单中隐藏:Y/N
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getHide() {
		return hide;
	}
	public void setHide(String hide) {
		this.hide = hide;
	}
}
