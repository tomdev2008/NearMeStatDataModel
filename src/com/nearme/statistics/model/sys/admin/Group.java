package com.nearme.statistics.model.sys.admin;

import java.util.Date;

/**
 * 分组
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-25
 */
public class Group {
	private int id;
	private int groupID;
	private String groupname;
	private String groupdesc;
	private int updateBy;// 最近被谁更新
	private Date updateTime;// 最近更新时间
	private int state;// 状态：0 OK; 1 DELETED

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupdesc() {
		return groupdesc;
	}

	public void setGroupdesc(String groupdesc) {
		this.groupdesc = groupdesc;
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

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
}
