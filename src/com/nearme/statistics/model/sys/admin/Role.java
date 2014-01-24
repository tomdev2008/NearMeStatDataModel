package com.nearme.statistics.model.sys.admin;

import java.util.Date;

/**
 * 角色
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-26
 */
public class Role {
	private int id;
	private int roleID;
	private String roleName;
	private String roleDesc;
	private String systemID;
	private int updateBy;// 最近被谁更新
	private Date updateTime;// 最近更新时间
	private int state;// 状态：0 OK; 1 DELETED

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
}
