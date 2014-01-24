package com.nearme.statistics.cache;

/**
 * 缓存采用的key名称
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-27
 */
public class CacheConstant {
	public static final String SYS_SYSTEMTABLE_GCPRODUCT = "SYS_SYSTEMTABLE_GCPRODUCT";
	public static final String SYS_SYSTEMTABLE_MODELDATA = "SYS_SYSTEMTABLE_MODELDATA";
	public static final String SYS_SYSTEMTABLE_NETWORK = "SYS_SYSTEMTABLE_NETWORK";
	public static final String ZJB_APPVERINFO = "ZJB_APPVERINFO";
	public static final String ZJB_CHANNELINFO = "ZJB_CHANNELINFO";
	public static final String ZJB_USERACTIONCODE = "ZJB_USERACTIONCODE";

	/**
	 * 当前系统用到的systemID
	 */
	public static final String[] SYSTEM_IDS = { 
		    "2", 
		    "1000", 
		    "2000", 
		    "2001",
			"2101", 
			"2002", 
			"2004", 
			"2006", 
			"2007", 
			"2008", 
			"2010", 
			"2011",
			"3000","3012","3013",
			"10001", 
			"10002", 
			"10", 
			"12", 
			"13", 
			"265671", "265672",
			"20001", "20002", "20003", "20004", "20005", "20006", "20007", "20008", "20009", "20010", "20011", "20012", "20013",
			"20014", "20015", "20016", "20017", "20018", "20019", "20020", "20021", "20022", "20023", "20024", "20025", "20026",
			"20027", "20028", "20029", "20030", "20031", "20032", "20033", "20034", "20035", "20036", "20037", "20038", "20039",
			"20040", "20041", "20042", "20043", "20044", "20045", "20046", "20047", "20048", "20049", "20050", "20051", "20052",
			"20053", "20054", "20055", "20056", "20057", "20058", "20059"};

	public static final String COMMON_CHANNELINFO_ = "COMMON_CHANNELINFO_";// 渠道_systemID
	public static final String SYS_SYSTEMTABLE_APPVERINFO_ = "SYS_SYSTEMTABLE_APPVERINFO_";//版本_systemID
	public static final String COMMON_INSTALL_SOURCE_LIST_ = "COMMON_INSTALL_SOURCE_LIST_";// 安装来源_systemID
}
