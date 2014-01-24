package com.nearme.statistics.form.sys;

public class NetworkTypeForm {
	private int networkID;
	private String networkGroup;
	private String networkName;
	public int getNetworkID() {
		return networkID;
	}
	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	public void setNetworkGroup(String networkGroup) {
		this.networkGroup = networkGroup;
	}
	public String getNetworkGroup() {
		return networkGroup;
	}
}
