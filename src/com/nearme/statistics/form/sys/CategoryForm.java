package com.nearme.statistics.form.sys;

public class CategoryForm {
	private String categoryID;
	private String categoryName;
	private int categoryLevel;//资源分类级别:1,2
	private String categoryParentID;//父分类ID(1级分类无父分类ID)
	private int systemID;
	
	
	public int getSystemID() {
		return systemID;
	}
	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryLevel() {
		return categoryLevel;
	}
	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	public String getCategoryParentID() {
		return categoryParentID;
	}
	public void setCategoryParentID(String categoryParentID) {
		this.categoryParentID = categoryParentID;
	}
}
