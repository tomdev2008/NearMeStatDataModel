package com.nearme.statistics.cache;



/**
 * 系统相对稳定(但可编辑)的数据缓存
 * 例如：机型、应用、渠道等信息可编辑但是相对固定,可使用此缓存
 * @author 80053813 罗勇
 * @version 1.0
 * @since 1.0 2012-9-7
 */
public class SystemTableCache {

	/**
	 * 设置缓存
	 * (为方便调用管理,内置设置一个相当长的缓存有效期)
	 * @param key
	 * @param value
	 */
	public static void set(String key, Object value){
		//首先在内存缓存中保存一份
		MyMemoryCache.getInstance().set(key, value, MyMemoryCache.TwoHour);
		
		//在MemCached中保存一份
		MemCached.getInstance().set(key, value, MyMemoryCache.TwoHour);
	}
	/**
	 * 获取缓存内容
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		//首先尝试从内存中直接读取
		Object cache = MyMemoryCache.getInstance().get(key);
		if(cache != null){
			return cache;
		}
		
		//从MemCached中读取
		cache = MemCached.getInstance().get(key);
		if(cache != null){
			//将MemCached缓存写入内存缓存
			MyMemoryCache.getInstance().set(key, cache, MyMemoryCache.TwoHour);
			return cache;
		}
		
		return null;
	}
	/**
	 * 数据被编辑时直接删除缓存
	 * @param key
	 */
	public static void delete(String key){
		//删除内存缓存
		MyMemoryCache.getInstance().delete(key);
		//删除MemCached缓存
		MemCached.getInstance().delete(key);
	}
	
	/**
	 * 清除使用到的缓存
	 */
	public static void clearCache(){
		delete(CacheConstant.SYS_SYSTEMTABLE_GCPRODUCT);
		delete(CacheConstant.SYS_SYSTEMTABLE_MODELDATA);
		delete(CacheConstant.SYS_SYSTEMTABLE_NETWORK);
		
		delete(CacheConstant.ZJB_APPVERINFO);
		delete(CacheConstant.ZJB_CHANNELINFO);
		delete(CacheConstant.ZJB_USERACTIONCODE);
		
		for (String systemID : CacheConstant.SYSTEM_IDS) {
			//清理渠道缓存
			delete(CacheConstant.COMMON_CHANNELINFO_ + systemID);
			//清理版本缓存
			delete(CacheConstant.SYS_SYSTEMTABLE_APPVERINFO_ + systemID);
		}
	}
}
