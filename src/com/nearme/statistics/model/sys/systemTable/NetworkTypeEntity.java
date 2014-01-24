package com.nearme.statistics.model.sys.systemTable;

/**
 * 网络类型
 * @author 80053813
 *
 */
public class NetworkTypeEntity extends BaseSystemEntity {

	private int networkID;
	private String networkGroup;//2G/3G/WIFI
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
