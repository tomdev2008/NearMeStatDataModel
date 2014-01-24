package com.nearme.statistics.util;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author 刘 超
 * @version 1.0
 * @since 1.0,2012-2-8
 */
public class StringUtil {
	public static boolean isNullOrEmpty(String value) {
		return (value == null) || (value.trim().equals(""));
	}

	public static String getAppKey() {
		String uid = UUID.randomUUID().toString();
		return uid.replace("-", "");
	}

	public static String getShortDateStr(String Date) {
		String shortDate;
		try {
			int index = Date.indexOf("T");
			shortDate = Date.substring(0, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return shortDate;
	}

	/**
	 * 是否匹配正则表达式
	 *
	 * @param input
	 *            待验证的字符串
	 * @param re
	 *            正则表达式
	 * @return
	 */
	public static boolean isMatch(String input, String re) {
		// 强制赋个空值，避免出错。
		// 另外，有可能正则表达式允许匹配空
		if (input == null) {
			input = "";
		}
		try {
			Pattern p = Pattern.compile(re);
			Matcher m = p.matcher(input);
			return m.matches();
		} catch (Exception exp) {
		}
		return false;
	}

	/**
	 * 检查密码是否符合要求
	 *
	 * @param input
	 * @return
	 */
	public static boolean checkNewPasswd(String input) {
		if (isNullOrEmpty(input)) {
			return false;
		}
		return (input.length() >= 6);
	}
	
	/**
	 * 从请求url中解析action字符串
	 * 例如："../sys/systemTable/gcProduct_init.do" 解析之后返回"sys/systemTable/gcProduct"
	 *
	 * @param requestUrl
	 * @return
	 */
	public static String formatRequestUrl(String requestUrl){
		String result = "";
		try{
			if(requestUrl==null || "".endsWith(requestUrl.trim())){
				return "";
			}
			String str = requestUrl.split(".do")[0];
	    	int index = str.indexOf("/");
	    	result = str.substring(index+1);
	    	result = result.split("_")[0];
	    	
		}catch(Exception e){
			return "";
		}
		
		return result;
	}
	
	/**
	 * 将list根据指定规则拼接出需要的字符串
	 * @param list
	 * @param rule
	 * @return
	 */
	public static String build2Str(List<String> list,String rule){
		String result = "";
		if (null == list) {
			return result;
		} else {
			for (String str : list) {
				if ("".equals(result)) {
					result += str;
				} else {
					result += rule + str;
				}
			}
			return result;
		}
	}
	
	public static long parseLong(String tag){
		long result = 0;
		if (null != tag) {
			try {
				result = Long.parseLong(tag);
			} catch (NumberFormatException e) {
				result = 0;
			}
		}
		return result;
	}
}
