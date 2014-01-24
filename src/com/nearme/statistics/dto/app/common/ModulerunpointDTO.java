package com.nearme.statistics.dto.app.common;

/**
 * 模块运营点管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-11
 */
public class ModulerunpointDTO {
	private String systemID;// 应用标识

	private String id;// id (唯一对应的键值)
	private String packagename;// 类型
	private String groupname;// 分组名称
	private String sourcecode;// 来源source_code
	private String sourcedesc;// 来源描述
	private String categoryid;// 分类id
	private String clickindex;// 位置
	private String tag;// 标签

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public String getSourcedesc() {
		return sourcedesc;
	}

	public void setSourcedesc(String sourcedesc) {
		this.sourcedesc = sourcedesc;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getClickindex() {
		return clickindex;
	}

	public void setClickindex(String clickindex) {
		this.clickindex = clickindex;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
