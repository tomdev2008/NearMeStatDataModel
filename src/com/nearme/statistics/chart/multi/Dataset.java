package com.nearme.statistics.chart.multi;

import java.util.List;

public class Dataset {
	private String seriesName;
	private String color;
	private String anchorBorderColor;
	private String anchorBgColor;

	private List<String> value;

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAnchorBorderColor() {
		return anchorBorderColor;
	}

	public void setAnchorBorderColor(String anchorBorderColor) {
		this.anchorBorderColor = anchorBorderColor;
	}

	public String getAnchorBgColor() {
		return anchorBgColor;
	}

	public void setAnchorBgColor(String anchorBgColor) {
		this.anchorBgColor = anchorBgColor;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

}