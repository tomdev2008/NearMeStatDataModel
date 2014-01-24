package com.nearme.statistics.chart;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nearme.statistics.chart.pie.PieSet;

public class Pie2D extends BaseChart {

	private List<String[]> data = null;
	private List<PieSet> datasets;

	@Override
	protected String[] getHeadProperties() {
		return new String[] { "showNames", "decimalPrecision" };
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean setData(Object data) {
		List<String[]> list = (List<String[]>) data;
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
		for (String[] item : this.data) {
			if (item == null) {
				continue;
			}
			if (item.length >= 1) {
				sb.append("<set name='").append(item[0]).append("' ");
			}
			if (item.length >= 2) {
				sb.append("value='").append(item[1]).append("' ");
			}
			if (item.length >= 3) {
				sb.append("isSliced='").append(item[2]).append("' ");
			}
			sb.append("/>");
		}

		return sb.toString();
	}

	public List<PieSet> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<PieSet> datasets) {
		this.datasets = datasets;
	}

	public Document generateChartDocument() {
		Document document = DocumentHelper.createDocument();
		// root element
		Element graphElement = document.addElement(ChartConstants.ELEMENT_ROOT);
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_CAPTION,
				getCaption());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_DECIMALPRECISION,
				getDecimalPrecision());

		for (int i = 0; i < datasets.size(); i++) {

			PieSet set = datasets.get(i);
			// dataset element
			Element datasetElement = graphElement
					.addElement(ChartConstants.Line.ELEMENT_SET);
			datasetElement.addAttribute(ChartConstants.Line.ATTRIBUTE_SET_NAME,
					set.getName());
			datasetElement.addAttribute(
					ChartConstants.Line.ATTRIBUTE_SET_VALUE, set.getValue());
		}
		return document;
	}

	public static void main(String[] args) {
		Pie2D chart = new Pie2D();

		List<PieSet> datasets = new ArrayList<PieSet>();

		for (int i = 0; i < 7; i++) {
			PieSet set = new PieSet();
			set.setName(String.valueOf(i + 1));
			set.setValue(String.valueOf(i + 1));
			datasets.add(set);
		}

		chart.setDatasets(datasets);

		System.out.println(chart.generateChartDocument().asXML());
	}
}
