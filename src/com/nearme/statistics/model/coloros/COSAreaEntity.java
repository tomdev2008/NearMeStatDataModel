package com.nearme.statistics.model.coloros;

import java.io.Serializable;

/**
 * 活跃用户地域分布Entity
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-11-22
 */
public class COSAreaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1966370627727505840L;
	
	private String statDate;
	private int systemID;
	private String model;
	private String appVersion;
	private String country;
	private String province;
	private String city;
	private int startImeis;
	private String percent;
	
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	public int getSystemID() {
		return systemID;
	}
	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getStartImeis() {
		return startImeis;
	}
	public void setStartImeis(int startImeis) {
		this.startImeis = startImeis;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}

}
