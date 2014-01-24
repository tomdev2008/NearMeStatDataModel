package com.nearme.statistics.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nearme.statistics.chart.line.LineSet;

public class Line2D extends BaseChart {

	private List<Map<String, Object>> data = null;
	private List<LineSet> set = null;

	private String xAxisValueKey = "DDT";
	private String yAxisValueKey = "CNT";

	public String getxAxisValueKey() {
		return xAxisValueKey;
	}

	public String getyAxisValueKey() {
		return yAxisValueKey;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public List<LineSet> getSet() {
		return set;
	}

	public void setSet(List<LineSet> set) {
		this.set = set;
	}

	public void setyAxisValueKey(String yAxisValueKey) {
		this.yAxisValueKey = yAxisValueKey;
	}

	public void setxAxisValueKey(String xAxisValueKey) {
		this.xAxisValueKey = xAxisValueKey;
	}

	@Override
	protected String[] getHeadProperties() {
		return new String[] { "caption", "subcaption", "xAxisName",
				"yAxisMaxValue", "yAxisMinValue", "yAxisName", "numberPrefix",
				"numberSuffix", "decimalPrecision", "formatNumberScale" };
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean setData(Object data) {
		List<Map<String, Object>> list = (List<Map<String, Object>>) data;
		if (list == null) {
			return false;
		}
		this.data = list;
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean setData(Object data, String clazz) {
		try {
			Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> list = (List<Map<String, Object>>) data;
		if (list == null) {
			return false;
		}
		this.data = list;
		return true;
	}

	@Override
	protected String getBodyStr() {
		if (this.data == null || this.data.size() == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		// <set name='Jan' value='17400' />
		for (Map<String, Object> item : this.data) {
			if (item == null) {
				continue;
			}
			sb.append("<set name='");
			sb.append(item.get(xAxisValueKey)).append("' ");

			sb.append("value='").append(item.get(yAxisValueKey)).append("' />");
		}

		return sb.toString();
	}

	public Document generateChartDocument() {
		Document document = DocumentHelper.createDocument();
		// root element
		Element graphElement = document.addElement(ChartConstants.ELEMENT_ROOT);
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_CAPTION,
				getCaption());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_SUBCAPTION,
				getSubCaption());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_FORMATNUMBERSCALE,
				getFormatNumberScale());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_DECIMALPRECISION,
				getDecimalPrecision());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_YAXISMINVALUE,
				getyAxisMinValue());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_YAXISMAXVALUE,
				getyAxisMaxValue());

		int size = set.size();
		int span = size / 10;

		if (size > 0) {
			Element setElement = graphElement
					.addElement(ChartConstants.Line.ELEMENT_SET);
			LineSet lineSet = set.get(0);
			setElement.addAttribute(ChartConstants.Line.ATTRIBUTE_SET_NAME,
					lineSet.getName());
			setElement.addAttribute(ChartConstants.Line.ATTRIBUTE_SET_VALUE,
					lineSet.getValue());
			setElement.addAttribute(
					ChartConstants.Line.ATTRIBUTE_SET_HOVERTEXT, lineSet
							.getHoverText());
		}

		// set element
		for (int i = 1; i < set.size(); i++) {
			Element setElement = graphElement
					.addElement(ChartConstants.Line.ELEMENT_SET);
			LineSet lineSet = set.get(i);
			if (i == i * span) {

			}
			setElement.addAttribute(ChartConstants.Line.ATTRIBUTE_SET_NAME,
					lineSet.getName());
			setElement.addAttribute(ChartConstants.Line.ATTRIBUTE_SET_VALUE,
					lineSet.getValue());
			setElement.addAttribute(
					ChartConstants.Line.ATTRIBUTE_SET_HOVERTEXT, lineSet
							.getHoverText());

		}

		return document;
	}

	public static void main(String[] args) {
		List<LineSet> listSet = new ArrayList<LineSet>();
		LineSet set = null;
		for (int i = 0; i < 100; i++) {
			set = new LineSet();
			set.setName(String.valueOf(i));
			set.setValue(String.valueOf(i));
			set.setHoverText(String.valueOf(i));

			listSet.add(set);

		}
		Line2D chart = new Line2D();
		chart.setCaption("测试");
		chart.setSet(listSet);

		System.out.println(chart.generateChartDocument().asXML());
	}
}
