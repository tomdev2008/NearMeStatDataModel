package com.nearme.statistics.dto.sys;

import com.nearme.statistics.form.sys.NetworkTypeForm;
import com.nearme.statistics.model.sys.systemTable.BaseSystemEntity;

public class NetworkTypeDTO extends BaseSystemEntity {
	private int networkID;
	private String networkGroup;
	private String networkName;
	
	public boolean fillFromForm(NetworkTypeForm form){
		this.networkID = form.getNetworkID();
		this.networkGroup = form.getNetworkGroup();
		this.networkName = form.getNetworkName();
		
		return true;
	}
	
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
