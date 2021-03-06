package com.nearme.statistics.model.zjbsetting;

/**
 * 装机宝用户行为编码管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-28
 */
public class ZJBUseractioncodeEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5652634774199196661L;

	private int actionID;// 行为id
	private String actioncode;// 行为编码
	private String actionname;// 行为名

	public int getActionID() {
		return actionID;
	}

	public void setActionID(int actionID) {
		this.actionID = actionID;
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
