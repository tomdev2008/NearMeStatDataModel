package com.nearme.statistics.util;

/**
 * 数字工具集
 *
 * @author 刘 超
 * @version 1.0
 * @since 1.0,2011-12-8
 */
public class NumericUtil {

	private static int seq = 0;

	/**
	 * 获取四位随机验证码
	 *
	 * @return 四位验证码
	 */
	public static String getVeriCode() {
		int randomInt = 9999;
		randomInt = (int) (Math.random() * 10000);
		String fourRandom = "" + randomInt;
		int randLength = fourRandom.length();
		if (randLength < 4) {
			for (int i = 1; i <= 4 - randLength; i++)
				fourRandom = "0" + fourRandom;
		} else {
			fourRandom = "" + randomInt;
		}

		return fourRandom;
	}

	public static int getUUIDInteager() {

		if (seq < Integer.MAX_VALUE) {
			return ++seq;
		} else {
			seq = 0;
			return ++seq;
		}
	}

	public static int tryParse(String input,int defaultValue){
		if(input == null || "".equals(input)){
			return defaultValue;
		}
		try{
			return Integer.parseInt(input);
		}catch(Exception exp){
			return defaultValue;
		}
	}
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}
}
