/**
 * Copyright (c) 2011, TNT All Rights Reserved.
 * FileName:MemCached.java
 * ProjectName:UserCenter
 * PackageName:com.oppo.common
 * Description:TODO 
 * Create Date:2011-3-21
 * History:
 *   ver	date	  author		desc	
 * ────────────────────────────────────────────────────────
 *   1.0	2011-3-21	  80053813 create
 *	 1.1    2011-03-24 	  80053813	更改缓存开关控制机制
 *
 * 
 */

package com.nearme.statistics.cache;

import java.util.Date;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.nearme.statistics.util.ConfigUtil;

/**
 * ClassName:MemCached Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author 80053813
 * @version
 * @since Ver 1.1
 * @Date 2011-3-21 下午03:00:20
 */

public class MemCached implements ICache{
	
	/**
	 * 缓存默认失效时间为12小时
	 */
	public static final int DEFAULT_EXPIRY_SECONDS = 12 * 60 * 60;
	
	// 创建并保持全局唯一连接实例
	protected static MemCachedClient mcc = new MemCachedClient();
	
	private static MemCached instance;
	
	// 设置与缓存服务器的连接池
	static {
		try {
			// 服务器列表和其权重
			String[] servers = ConfigUtil.getConfig("memcache.properties",
					"memcachedServs").split(",");
			String[] ws = ConfigUtil.getConfig("memcache.properties",
					"memcachedWeights").split(",");
			Integer[] weights = new Integer[ws.length];
			for (int i = 0; i < ws.length; i++) {
				weights[i] = Integer.parseInt(ws[i]);
			}

			// 获取socke连接池的实例对象
			SockIOPool pool = SockIOPool.getInstance();

			// 设置服务器信息
			pool.setServers(servers);
			pool.setWeights(weights);

			// 设置初始连接数、最小和最大连接数以及最大处理时间
			pool.setInitConn(5);
			pool.setMinConn(5);
			pool.setMaxConn(250);
			pool.setMaxIdle(1000 * 60 * 60 * 6);

			// 设置主线程的睡眠时间
			pool.setMaintSleep(30);

			// 设置TCP的参数，连接超时等
			pool.setNagle(false);
			pool.setSocketTO(3000);
			pool.setSocketConnectTO(0);

			// 初始化连接池
			pool.initialize();

			// // 压缩设置，超过指定大小（单位为K）的数据都会被压缩
			// mcc.setCompressEnable( true );
			// mcc.setCompressThreshold( 64 * 1024 );
		} catch (Exception exp) {
			// 目前发现唯一可能出现异常的地方在“设置服务器列表和其权重”处
			// （从配置文件导入的参数有误导致）
			Logger log = Logger.getLogger(MemCached.class);
			log.error("初始化Memcached异常，" + exp.toString());
		}
		
		instance = new MemCached();
	}

	/**
	 * 私有构造函数
	 */
	private MemCached(){
		
	}
	/**
	 * 获取单例
	 * @return
	 */
	public static MemCached getInstance(){
		return instance;
	}
	
	private boolean getCacheEnable() {
		return Boolean.parseBoolean(ConfigUtil.getConfig("memcache.properties",
				"memcachedEnabled"));
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * key书写规则：1.尽量全部大写
	 * 			2.类似功能前缀一致
	 * 			3.按照功能路径拼凑key,包路径+类名称+方法名称+参数列表
	 * 			例如：APP_COMMON_DOWNLOAD_TOTALDOWNLOAD_3
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, Object value) {
		if (getCacheEnable()) {
			mcc.set(key, value);
		}
	}

	/**
	 * 设置缓存
	 */
	public void set(String key, Object value, long timeout) {
		if (getCacheEnable()) {
			mcc.set(key, value, new Date(new Date().getTime() + timeout));
		}
	}

	/**
	 * 设置缓存
	 */
	public void set(String key, Object value, Date timeout) {
		if (getCacheEnable()) {
			mcc.set(key, value, timeout);
		}
	}

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		if (getCacheEnable())
			return mcc.get(key);
		return null;
	}

	/**
	 * 删除缓存
	 */
	public void delete(String key) {
		if (getCacheEnable()){
			mcc.delete(key);
		}
	}


}
