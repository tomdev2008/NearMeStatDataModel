package com.nearme.statistics.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

	/**
	 * 取得客户端IP
	 * 
	 * @param request
	 *            客户端请求
	 * @return
	 */
	public static String getIPAddress(HttpServletRequest request) {

		// 取得IP地址
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		// 返回IP
		return ip;
	}
	public static long getIPNumber(String ip){
		if(ip == null || ip.length() < 7){
			return 0;
		}
		String[] ips = ip.split("\\.");
		if(ips.length != 4){
			return 0;
		}
		long ipNumber = 0;
		for(String str : ips){
			ipNumber <<= 8;
			ipNumber += NumericUtil.tryParse(str, 0);
		}
		
		return ipNumber;
	}
	
	public static boolean checkIP(String input){
		return StringUtil.isMatch(input,"^(\\d{1,3}\\.){3}\\d{1,3}$");
	}
	
	public static boolean checkIPFilter(String input){
		return StringUtil.isMatch(input,"^((\\d{1,3}|\\*)\\.){3}((\\d){1,3}|\\*)$");
	}
}
