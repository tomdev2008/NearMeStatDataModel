package com.nearme.statistics.chart;

import java.lang.reflect.Field;

import com.nearme.statistics.util.DateUtil;

public class BaseChart {

	protected String caption;// 主标题
	protected String subCaption;// 副标题
	protected String xAxisName;//X轴名称
	protected String yAxisName;//Y轴名称
	protected String yAxisMaxValue;//Y最大值
	protected String yAxisMinValue;//Y最小值
	protected String numberPrefix;//数字前缀
	protected String numberSuffix;//数字后缀

	protected String decimalPrecision;
	protected String formatNumberScale;

	public String getFormatNumberScale() {
		return formatNumberScale;
	}

	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}
	protected boolean showFull = true;
	protected String showTimeFormat = DateUtil.FMT_FULL_DATETIME;

	/**
	 * 标识头部可以/需要设置的属性
	 * @return
	 */
	protected String[] getHeadProperties() {
		return new String[] { "caption" };
	}

	/**
	 * 设置数据
	 */
	public boolean setData(Object data) {
		return false;
	}

	/**
	 * 获取XML头属性字符串
	 *
	 * @return
	 */
	protected String getHeadPropertiesStr() {
		Field[] fields = BaseChart.class.getDeclaredFields();
		String[] fnames = getHeadProperties();
		StringBuilder sb = new StringBuilder();

		for (String fieldName : fnames) {
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					try {
						if (field.get(this) != null) {
							String value = (String) field.get(this);
							sb.append(fieldName).append("='").append(value)
									.append("' ");
						}
					} catch (IllegalArgumentException e) {
						// e.printStackTrace();
					} catch (IllegalAccessException e) {
						// e.printStackTrace();
					}

					break;
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 获取数据域XML字符串
	 *
	 * @return
	 */
	protected String getBodyStr() {
		return "";
	}

	/**
	 * 获取XML字符串
	 *
	 * @return
	 */
	public String getXmlStr() {
		return "<graph " + getHeadPropertiesStr() + ">" + getBodyStr()
				+ "</graph>";
	}

	/***************** 以下是字段设置、获取值的方法 ********************/

	public void setShowFull(boolean showfull){
		this.showFull = showfull;
	}
	public boolean getShowFull(){
		return this.showFull;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	public String getSubCaption() {
		return this.subCaption;
	}

	public String getyAxisMaxValue() {
		return yAxisMaxValue;
	}

	public void setyAxisMaxValue(String yAxisMaxValue) {
		this.yAxisMaxValue = yAxisMaxValue;
	}

	public String getyAxisMinValue() {
		return yAxisMinValue;
	}

	public void setyAxisMinValue(String yAxisMinValue) {
		this.yAxisMinValue = yAxisMinValue;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	public String getDecimalPrecision() {
		return decimalPrecision;
	}

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	public void setDecimalPrecision(String decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public String getxAxisName() {
		return xAxisName;
	}

	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	public String getyAxisName() {
		return yAxisName;
	}
	public String getShowTimeFormat(){
		return showTimeFormat;
	}
	public void setShowTimeFormat(String fmt){
		showTimeFormat = fmt;
	}
}
