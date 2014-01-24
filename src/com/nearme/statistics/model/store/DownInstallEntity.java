package com.nearme.statistics.model.store;

import java.util.Date;

/**
 * 下载安装成功率
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-16
 */
public class DownInstallEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 时间
	private Date statdate;
	private String appVersion;
	private String model;
	private long downRequest;
	private long downSuccess;
	private long downFail;
	private long install;
	private long installSuccess;
	private float downSuccessRate;
	private float downFailRate;
	private float installSuccessRate;
	
	public Date getStatdate() {
		return statdate;
	}
	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public long getDownRequest() {
		return downRequest;
	}
	public void setDownRequest(long downRequest) {
		this.downRequest = downRequest;
	}
	public long getDownSuccess() {
		return downSuccess;
	}
	public void setDownSuccess(long downSuccess) {
		this.downSuccess = downSuccess;
	}
	public long getDownFail() {
		return downFail;
	}
	public void setDownFail(long downFail) {
		this.downFail = downFail;
	}
	public long getInstall() {
		return install;
	}
	public void setInstall(long install) {
		this.install = install;
	}
	public long getInstallSuccess() {
		return installSuccess;
	}
	public void setInstallSuccess(long installSuccess) {
		this.installSuccess = installSuccess;
	}
	public float getDownSuccessRate() {
		return downSuccessRate;
	}
	public void setDownSuccessRate(float downSuccessRate) {
		this.downSuccessRate = downSuccessRate;
	}
	public float getDownFailRate() {
		return downFailRate;
	}
	public void setDownFailRate(float downFailRate) {
		this.downFailRate = downFailRate;
	}
	public float getInstallSuccessRate() {
		return installSuccessRate;
	}
	public void setInstallSuccessRate(float installSuccessRate) {
		this.installSuccessRate = installSuccessRate;
	}

}
