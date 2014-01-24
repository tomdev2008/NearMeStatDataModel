package com.nearme.statistics.chart;

import java.util.List;

public class FCFXmlUtil {

	private String caption;// 主标题
	private String subCaption;// 副标题
	private String xAxisName;// X轴名称
	@SuppressWarnings("unused")
	private String yAxisName;// Y轴名称
	private String xAxisMaxValue;// X最大值
	private String yAxisMaxValue;// Y最大值

	public String getXmlStr(eumChartType type, List<String[]> list) {

		if(type == eumChartType.Area2D
				|| type == eumChartType.Column2D
				|| type == eumChartType.Column3D){
			return getSimpleXmlStr(type, list);
		}
		
		return "";
	}
	
	@SuppressWarnings("unused")
	private String getHeadStr(eumChartType type){
		return "";
	}

	private String getSimpleXmlStr(eumChartType type,List<String[]> list) {
		StringBuilder sb = new StringBuilder("<graph ");

		sb.append("caption='").append(this.caption).append("' ");
		sb.append("subcaption='").append(this.subCaption).append("' ");
		sb.append("xAxisName='").append(this.xAxisName).append("' ");
		sb.append("yAxisName='").append(this.xAxisName).append("' ");
		sb.append("xAxisMaxValue='").append(this.xAxisMaxValue).append("' ");
		sb.append("yAxisMaxValue='").append(this.yAxisMaxValue).append("'>");
		
		//<set name='Jan' value='17400' [color='FFFFFF']/>
		for(String[] item : list){
			sb.append("<set name='").append(item[0]).append("' ");
			sb.append("value='").append(item[1]).append("' ");
			if(item.length>2){
				sb.append("color='").append(item[2]).append("' ");
			}
			sb.append("/>");
		}
		
		sb.append("</graph>");
		return sb.toString();
	}
}
