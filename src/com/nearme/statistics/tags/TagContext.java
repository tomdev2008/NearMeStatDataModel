package com.nearme.statistics.tags;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author 80053813
 *
 */
public class TagContext {

	//一次初始化,防止多次加载spring配置文件
	private static ApplicationContext context;
	static{
		context = new ClassPathXmlApplicationContext(new String[] {
				"spring-datasource.xml", "spring-service.xml",
				"spring-dao.xml"});
	}
	
	public static ApplicationContext getContextInstance(){
		return context;
	}
}
