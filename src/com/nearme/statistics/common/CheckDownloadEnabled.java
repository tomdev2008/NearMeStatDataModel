package com.nearme.statistics.common;

/**
 * 检查应用是否有下载功能
 * @author 80053813
 *
 */
public class CheckDownloadEnabled {

	public static boolean checkEnabled(int systemID){
		return (systemID == 2
				|| systemID == 3
				|| systemID == 4
				|| systemID == 7
				|| systemID == 1000
				);
	}
}
