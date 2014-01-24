package com.nearme.statistics.cache;

import java.util.Date;

/**
 * 缓存接口
 * @author 80053813 罗勇
 * @version 1.0
 * @since 1.0 2012-9-7
 */
public interface ICache {
	
	/**
	 * 设置、更新缓存
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value);
	/**
	 * 设置、更新缓存
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public void set(String key, Object value, long timeout);
	/**
	 * 设置、更新缓存
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public void set(String key, Object value, Date timeout);
	
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public Object get(String key);
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public void delete(String key);
}
