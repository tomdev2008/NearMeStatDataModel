package com.nearme.statistics.form.app.common;

import com.nearme.statistics.util.NumericUtil;

/**
 * 用户行为编码
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class UseractionForm {
	private String systemID;

	private String groupcode;// 分组编码
	private String groupname;// 分组名称

	private String actioncode;// 行为编码
	private String actionname;// 行为名称

	private String operation;// 执行的操作(db操作类型"add","update")

	public String getSystemID() {
		return systemID;
	}

	/**
	 * 获取SystemID的int值
	 * 
	 * @return
	 */
	public int getSystemIDValue() {
		return NumericUtil.tryParse(systemID, 0);
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
