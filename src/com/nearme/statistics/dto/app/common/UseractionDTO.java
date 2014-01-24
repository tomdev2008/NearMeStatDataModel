package com.nearme.statistics.dto.app.common;

/**
 * 用户行为编码
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class UseractionDTO {
	private int systemID;

	private String groupcode;
	private String groupname;

	private String actioncode;
	private String actionname;

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
}
