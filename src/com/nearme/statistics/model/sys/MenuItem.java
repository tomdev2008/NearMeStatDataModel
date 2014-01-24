package com.nearme.statistics.model.sys;

import java.io.Serializable;

/**
 * 菜单项实体
 * @author 80053813 罗勇
 * @version 1.0
 * @since 1.0 2012-9-17
 */
public class MenuItem implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	
	private String menuKey;
	private String menuParentKey;
	private String menuText;
	private String menuUrl;
	private int menuTabIndex;//控制缩进
	private String menuType;
	private int isUsed;
	private int orderNum;
	
	
	public MenuItem(){
		this("","","","",0);
	}
	/**
	 * 构造菜单项
	 * @param menuKey
	 * @param menuParentKey
	 * @param menuText 显示文字
	 * @param menuUrl
	 * @param menuTabIndex 菜单项目级别(控制缩进)
	 */
	public MenuItem(String menuKey, String menuParentKey, String menuText, String menuUrl,int menuTabIndex){
		this.menuKey = menuKey;
		this.menuParentKey = menuParentKey;
		this.menuText = menuText;
		this.menuUrl = menuUrl;
		this.menuTabIndex = menuTabIndex;
	}
	
	public String getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	public String getMenuParentKey() {
		return menuParentKey;
	}
	public void setMenuParentKey(String menuParentKey) {
		this.menuParentKey = menuParentKey;
	}
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getMenuTabIndex() {
		return menuTabIndex;
	}
	public void setMenuTabIndex(int menuTabIndex) {
		this.menuTabIndex = menuTabIndex;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	public Object clone(){
		MenuItem o = null;
        try{
            o = (MenuItem)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return o;
    }
}
