package com.nearme.statistics.mapreduce;

/**
 * 分表规则枚举
 * @author 80053813
 *
 */
public enum FuncCodeEnum {

	TRACE_BROWSE(202),
	TRACE_DOWNLOAD(203),
	
	CLIENT_START(301),
	USER_ACTION(302),
	CLIENT_PAGEVISIT(307);
	
	private int myValue;
	
	/**
	 * value取值方式参见 《统计定义.doc》 文档中格式定义位置
	 * 例如:浏览统计位于第二章第2节,则TRACE_BROWSE=0202,化为整数变成202
	 * @param value
	 */
	private FuncCodeEnum(int value){
		myValue = value;
	}
	public int getValue(){
		return myValue;
	}
}
