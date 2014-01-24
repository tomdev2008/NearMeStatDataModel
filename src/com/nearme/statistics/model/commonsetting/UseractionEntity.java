package com.nearme.statistics.model.commonsetting;

/**
 * 用户行为编码
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class UseractionEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8823925869433302323L;

	private int systemID;// 应用标识

	private String groupcode;// 分组编码
	private String groupname;// 分组名称

	private String actioncode;// 行为编码
	private String actionname;// 行为名称

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
