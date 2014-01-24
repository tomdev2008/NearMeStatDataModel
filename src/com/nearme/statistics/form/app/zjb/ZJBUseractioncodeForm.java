package com.nearme.statistics.form.app.zjb;

/**
 * 行为编码管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-24
 */
public class ZJBUseractioncodeForm{
	private String actionID;// 行为id
	private String actioncode;// 行为编码
	private String actionname;// 行为名

	public String getActionID() {
		return actionID;
	}

	public void setActionID(String actionID) {
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
