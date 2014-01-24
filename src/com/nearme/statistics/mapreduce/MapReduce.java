package com.nearme.statistics.mapreduce;

import java.util.HashMap;
import java.util.Map;


/**
 * 按功能分表规则
 * @author 80053813
 *
 */
public class MapReduce {

	private static Map<FuncCodeEnum, Map<Integer, String>> tableMapRule;
	
	/**
	 * 映射关系暂时写死在代码中,
	 * 待分析程序方案定型之后改为配置文件
	 */
	static{
		tableMapRule = new HashMap<FuncCodeEnum, Map<Integer, String>>();
		//启动分表规则
		Map<Integer, String> tblStart = new HashMap<Integer, String>();
		tblStart.put(0, "0");
		tblStart.put(2, "2");
		tblStart.put(3, "3");
		tblStart.put(4, "4");
		tblStart.put(6, "6");
		tblStart.put(7, "7");
		tblStart.put(1000, "1000");
		tblStart.put(1001, "1001");
		tblStart.put(1002, "1002");
		tblStart.put(2000, "2000");
		tblStart.put(2001, "2001");
		tblStart.put(2002, "2002");
		tblStart.put(2003, "2003");
		tblStart.put(2004, "2004");
		tblStart.put(2005, "2005");
		tblStart.put(2006, "2006");
		tblStart.put(2007, "2007");
		tblStart.put(3000, "3000");
		tblStart.put(10001, "10001");
		tblStart.put(10002, "10002");
		tblStart.put(10008, "10008");
		tblStart.put(10010, "10010");
		tblStart.put(1001714, "1001714");
		tblStart.put(1001717, "1001717");
		tblStart.put(1001718, "1001718");
		tableMapRule.put(FuncCodeEnum.CLIENT_START, tblStart);
		
		//下载分表规则
		Map<Integer, String> tblDownload = new HashMap<Integer, String>();
		tblDownload.put(0, "0");
		tblDownload.put(2, "2");
		tblDownload.put(3, "3");
		tblDownload.put(4, "4");
		tblDownload.put(7, "7");
		tblDownload.put(1000, "1000");
		tableMapRule.put(FuncCodeEnum.TRACE_DOWNLOAD, tblDownload);
		
		//浏览分表规则（与下载分表规则一样）
		Map<Integer, String> tblBrowse = new HashMap<Integer, String>();
		tblBrowse.put(0, "0");
		tableMapRule.put(FuncCodeEnum.TRACE_BROWSE, tblBrowse);
		
		//用户行为分表规则
		Map<Integer, String> tblAction = new HashMap<Integer, String>();
		tblAction.put(0, "0");
		tblAction.put(2, "2");
		tblAction.put(3, "3");
		tblAction.put(4, "4");
		tblAction.put(6, "6");
		tblAction.put(7, "7");
		tblAction.put(1000, "1000");
		tblAction.put(1002, "1002");
		tblAction.put(2001, "2001");
		tblAction.put(2101, "2101");
		tblAction.put(10001, "10001");
		tblAction.put(10008, "10008");
		tableMapRule.put(FuncCodeEnum.USER_ACTION, tblAction);
		
		//页面访问分表规则
		Map<Integer, String> tblPv = new HashMap<Integer, String>();
		tblPv.put(0, "0");
		tblPv.put(7, "7");
		tblPv.put(1000, "1000");
		tblPv.put(2007, "2007");
		tableMapRule.put(FuncCodeEnum.CLIENT_PAGEVISIT, tblPv);
	}
	
	public static String getMapTable(FuncCodeEnum funcCode, int systemID){
		Map<Integer, String> map = tableMapRule.get(funcCode);
		String tbl = map.get(systemID);
		return tbl == null ? "0" : tbl;
	}
}
