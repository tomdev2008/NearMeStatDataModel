package com.nearme.statistics.action.sys.systemTable;

import com.nearme.statistics.cache.SystemTableCache;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 系统缓存管理
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-27
 */
public class CachecontrolAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	public String init(){
		return Action.SUCCESS;
	}
	
	/**
	 * 清除系统缓存
	 * @return
	 */
	public String clear(){
		SystemTableCache.clearCache();
		
		return Action.SUCCESS;
	}
}
