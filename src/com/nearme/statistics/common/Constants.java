package com.nearme.statistics.common;

public final class Constants {

	public static final String SESSION_ADMIN_KEY = "SESSION_ADMIN";
	public static final String SESSION_PERMS_KEY = "SESSION_PERMS";
	public static final String SESSION_MENULIST_KEY = "SESSION_MENULIST";
	public static final String SESSION_FORMAT_PERMS_KEY = "SESSION_FORMAT_PERMS";
	public static final String SESSION_EXCEPT_URL_KEY = "SESSION_EXCEPT_URL";
	public static final String SESSION_MENU_STR = "SESSION_MENU_STR";//菜单项
	
	public static final String NEARME_SESSION_APP_KEY = "NEARME_SESSION_APP_KEY";

	public static final String NEARME_CONFIG_STRJOIN = ",";

	public static final String NEARME_SESSION_REPROT_DATA = "NEARME_SESSION_REPROT_DATA";
	public static final String NEARME_SESSION_REPROT_DATA_2 = "NEARME_SESSION_REPROT_DATA_2";
	public static final String NEARME_SESSION_REPROT_DATA_3 = "NEARME_SESSION_REPROT_DATA_3";
	public static final String NEARME_SESSION_REPROT_DATA_4 = "NEARME_SESSION_REPROT_DATA_4";
	public static final String NEARME_SESSION_REPROT_NAME = "NEARME_SESSION_REPROT_NAME";
	
	public static final String NEARME_SESSION_TOPIC_DATA="NEARME_SESSION_TOPIC_DATA";
	
	public static final String NEARME_SESSION_DOWNLOAD_LIST="NEARME_SESSION_DOWNLOAD_LIST";

	public static final String NEARME_SESSION_EVENT_LIST = "NEARME_SESSION_EVENT_LIST";

	public static final String NEARME_SESSION_EVENT_ID = "NEARME_SESSION_EVENT_ID";
	
	public static final String NEARME_SESSION_BETA_OTA_DETAIL="NEARME_SESSION_BETA_OTA_DETAIL";
	
	public static final String NEARME_SESSION_NORMAL_OTA_DETAIL="NEARME_SESSION_NORMAL_OTA_DETAIL";

	public static final String NEARME_PARAM_PERIOD_ID = "period";
	
	public static final String NEARME_PARAM_DAY="day";

	public static final String NEARME_PARAM_CHANNEL_ID = "channelID";

	public static final String NEARME_PERIOD_WEEK = "7";

	public static final String NEARME_PERIOD_MONTH = "30";

	public static final String NEARME_PERIOD_3MONTH = "90";

	public static final String NEARME_PERIOD_ALL = "all";
	
	public static final String PARAM_EXCEPT_URL = "EXCEPT_URL";
	
	public static final String DAILY = "DAILY";
	public static final String WEEKLY = "WEEKLY";
	public static final String MONTHLY = "MONTHLY";
	
	public static final String WEIDU_UPDATE_NUM = "UPDATE_NUM";// 更新数量总体及分析
	public static final String WEIDU_RESOURCE_TYPE = "RESOURCE_TYPE";// 资源类别总体及分析
	public static final String WEIDU_TOP_RESOURCE = "TOP_RESOURCE";// top资源总体及分析
	public static final String WEIDU_NATURE_MODLE_NUM = "NATURE_MODLE_NUM";// 自然模块数量总体及分析
	public static final String WEIDU_NATURE_MODLE_NAME = "模块";// 模块
	public static final String WEIDU_OPERATE_POINT = "OPERATE_POINT";// 运营点总体及分析
	public static final String WEIDU_OPERATE_POINT_NAME = "运营点";// 运营点
	
	public static String getPackageGroupName(String weidu){
		if(WEIDU_NATURE_MODLE_NUM.equals(weidu)){
			return WEIDU_NATURE_MODLE_NAME;
		}else if(WEIDU_OPERATE_POINT.equals(weidu)){
			return WEIDU_OPERATE_POINT_NAME;
		}
		return "";
	}
	
	/**
	 * 限定提交hive的任务数
	 */
	public static final int HIVE_TASK_LIMIT = 5;//耗时一般的任务
	public static final int HIVE_TASK_LIMIT_LONGTIME = 2;//耗时比较长的任务
}
