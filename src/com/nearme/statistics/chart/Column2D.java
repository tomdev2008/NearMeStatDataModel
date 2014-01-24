package com.nearme.statistics.chart;

import java.util.List;

public class Column2D extends BaseChart {
	private List<String[]> data = null;

	@Override
	protected String[] getHeadProperties() {
		return new String[] { "caption", "xAxisName", "yAxisName",
				"decimalPrecision", "formatNumberScale" };
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
			if(item.length >= 3){
				sb.append("color='").append(item[2]).append("' ");
			}
			sb.append("/>");
		}

		return sb.toString();
	}
}
