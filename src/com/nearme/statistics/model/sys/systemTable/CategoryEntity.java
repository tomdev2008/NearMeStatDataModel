package com.nearme.statistics.model.sys.systemTable;


/**
 * @author 段锦涛
 * @function 
 * @version 1.0
 * @since 1.0, 2013-1-17
 */
public class CategoryEntity extends BaseSystemEntity {

	private String categoryID;
	private String categoryName;
	private int categoryLevel;//资源分类级别:1,2
	private int systemID;//系统应用ID
	private String categoryParentID;//父分类ID(1级分类无父分类ID)
	
	
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
