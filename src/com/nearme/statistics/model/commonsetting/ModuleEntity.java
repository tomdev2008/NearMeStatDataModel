package com.nearme.statistics.model.commonsetting;

/**
 * 模块管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-29
 */
public class ModuleEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5652634774199196661L;

	private String systemID;// 应用标识

	private String id;// id (唯一对应的键值)
	private String groupname;// 分组名称
	private String sourcecode;// 来源source_code
	private String sourcedesc;// 来源描述
	private String categoryid;// 分类id
	private String downtype;// 下载类型

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

	public String getDowntype() {
		return downtype;
	}

	public void setDowntype(String downtype) {
		this.downtype = downtype;
	}
}
