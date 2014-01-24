package com.nearme.statistics.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * properties配置文件读取辅助类
 * @author 80053813 罗勇
 * @version 1.0
 * @since 1.0 2012-8-30
 */
public class ConfigUtil {
	
	//内存中备份，防止读配置文件频繁
	private static Map<String, Map<String, String>> configCache = new HashMap<String, Map<String, String>>();
	
	/**
	 * 读取.properties配置文件的配置项
	 * @param configFile
	 * @param configKey
	 * @return
	 */
	public static String getConfig(String configFile, String configKey) {
		if (StringUtil.isNullOrEmpty(configFile)
				|| StringUtil.isNullOrEmpty(configKey)) {
			return null;
		}
		if(configCache.containsKey(configFile)){
			return configCache.get(configFile).get(configKey);
		}
		
		Properties prop = new Properties();// 属性集合对象
		InputStream fis;
		try {
			fis = ConfigUtil.class.getClassLoader().getResourceAsStream(
					configFile);
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
			
			Map<String, String> config = new HashMap<String, String>();
			for(Object k : prop.keySet()){
				config.put(String.valueOf(k), prop.getProperty(String.valueOf(k)));
			}
			configCache.put(configFile, config);
			
			return configCache.get(configFile).get(configKey);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 一次读取配置文件的全部配置项目
	 * @param configFile
	 * @return
	 */
	public static Map<String, String> getConfigs(String configFile){
		if (StringUtil.isNullOrEmpty(configFile)) {
			return null;
		}
		if(configCache.containsKey(configFile)){
			return configCache.get(configFile);
		}
		
		Properties prop = new Properties();// 属性集合对象
		InputStream fis;
		try {
			fis = ConfigUtil.class.getClassLoader().getResourceAsStream(
					configFile);
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
			
			Map<String, String> config = new HashMap<String, String>();
			for(Object k : prop.keySet()){
				config.put(String.valueOf(k), prop.getProperty(String.valueOf(k)));
			}
			configCache.put(configFile, config);
			
			return config;
		} catch (Exception e) {
			return null;
		}
	}
}
