package com.nearme.statistics.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用内存缓存
 * @author 80053813 罗勇
 * @version 1.0
 * @since 1.0 2012-9-7
 */
public class MyMemoryCache implements ICache{

	public static final int OneHour = 60 * 60;
	public static final int TwoHour = 2 * 60 * 60;
	public static final int OneDay = 24 * 60 * 60;
	
	
	private static MyMemoryCache instance = new MyMemoryCache();//单例
	
	private Map<String,Map<String,Object>> caches;
	private static final String VALUE = "VALUE";
	private static final String EXPIRY = "EXPIRY";
	
	private MyMemoryCache(){
		caches  = new HashMap<String,Map<String,Object>>();
	}
	
	public static MyMemoryCache getInstance(){
		return instance;
	}
	
	/**
	 * 设置缓存
	 */
	public void set(String key, Object value) {
		set(key, value, OneHour);
	}
	/**
	 * 设置缓存
	 */
	public void set(String key, Object value, long timeout) {
		set(key,value,new Date(new Date().getTime() + timeout * 1000));
	}
	/**
	 * 设置缓存
	 */
	public void set(String key, Object value, Date timeout) {
		if(key == null || value == null || timeout == null){
			return;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(EXPIRY, timeout);
		map.put(VALUE, value);
		caches.put(key, map);
	}
	
	/**
	 * 获取缓存
	 */
	public Object get(String key) {
		Map<String,Object> map = caches.get(key);
		if(map == null){
			return null;
		}
		Date d = (Date) map.get(EXPIRY);
		Date now = new Date();
		if(now.after(d)){
			caches.remove(key);
		}else{
			return map.get(VALUE);
		}
		
		return null;
	}
	/**
	 * 删除缓存
	 */
	public void delete(String key) {
		caches.remove(key);
	}
	/**
	 * 清空全部缓存
	 */
	public void clearAllCache(){
		caches.clear();
	}
}
