package com.nearme.statistics.chart;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nearme.statistics.chart.multi.Category;
import com.nearme.statistics.chart.multi.Dataset;

public class MSLine extends BaseChart {

	private String hovercapbg = "FFECAA";
	private String hovercapborder = "F47E00";
	private String formatNumberScale = "0";
	private String decimalPrecision = "0";
	private String showvalues = "0";
	private String numdivlines = "3";
	private String numVdivlines = "0";
	private String rotateNames = "1";

	private List<Category> categories;
	private List<Dataset> datasets;

	public String getHovercapbg() {
		return hovercapbg;
	}

	public void setHovercapbg(String hovercapbg) {
		this.hovercapbg = hovercapbg;
	}

	public String getHovercapborder() {
		return hovercapborder;
	}

	public void setHovercapborder(String hovercapborder) {
		this.hovercapborder = hovercapborder;
	}

	public String getFormatNumberScale() {
		return formatNumberScale;
	}

	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}

	public String getDecimalPrecision() {
		return decimalPrecision;
	}

	public void setDecimalPrecision(String decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public String getShowvalues() {
		return showvalues;
	}

	public void setShowvalues(String showvalues) {
		this.showvalues = showvalues;
	}

	public String getNumdivlines() {
		return numdivlines;
	}

	public void setNumdivlines(String numdivlines) {
		this.numdivlines = numdivlines;
	}

	public String getNumVdivlines() {
		return numVdivlines;
	}

	public void setNumVdivlines(String numVdivlines) {
		this.numVdivlines = numVdivlines;
	}

	public String getRotateNames() {
		return rotateNames;
	}

	public void setRotateNames(String rotateNames) {
		this.rotateNames = rotateNames;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	public Document generateChartDocument() {
		Document document = DocumentHelper.createDocument();
		// root element
		Element graphElement = document.addElement(ChartConstants.ELEMENT_ROOT);
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_CAPTION,
				getCaption());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_SUBCAPTION,
				getSubCaption());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_HOVERCAPBG,
				getHovercapbg());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_HOVERCAPBORDER,
				getHovercapborder());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_FORMATNUMBERSCALE,
				getFormatNumberScale());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_DECIMALPRECISION,
				getDecimalPrecision());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_SHOWVALUES,
				getShowvalues());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_NUMDIVLINES,
				getNumdivlines());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_NUMVDIVLINES,
				getNumVdivlines());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_YAXISMINVALUE,
				getyAxisMinValue());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_YAXISMAXVALUE,
				getyAxisMaxValue());
		graphElement.addAttribute(ChartConstants.ATTRIBUTE_ROTATENAMES,
				getRotateNames());

		// categories element
		Element categoriesElement = graphElement
				.addElement(ChartConstants.MSLine.ELEMENT_CATEGORIES);

		for (int i = 0; i < categories.size(); i++) {
			Element nameElement = categoriesElement
					.addElement(ChartConstants.MSLine.ELEMENT_CATEGORY);
			nameElement.addAttribute(
					ChartConstants.MSLine.ATTRIBUTE_CATEGORY_NAME, categories
							.get(i).getName());

		}

		for (int i = 0; i < datasets.size(); i++) {

			Dataset dataset = datasets.get(i);
			// dataset element
			Element datasetElement = graphElement
					.addElement(ChartConstants.MSLine.ELEMENT_DATASET);
			datasetElement.addAttribute(
					ChartConstants.MSLine.ATTRIBUTE_DATASET_SERIESNAME, dataset
							.getSeriesName());
			datasetElement.addAttribute(
					ChartConstants.MSLine.ATTRIBUTE_DATASET_COLOR, dataset
							.getColor());
			datasetElement.addAttribute(
					ChartConstants.MSLine.ATTRIBUTE_DATASET_ANCHORBORDERCOLOR,
					dataset.getAnchorBorderColor());
			datasetElement.addAttribute(
					ChartConstants.MSLine.ATTRIBUTE_DATASET_ANCHORBGCOLOR,
					dataset.getAnchorBgColor());

			List<String> lstValue = dataset.getValue();
			for (int j = 0; j < lstValue.size(); j++) {
				Element setElement = datasetElement
						.addElement(ChartConstants.MSLine.ELEMENT_SET);
				setElement.addAttribute(
						ChartConstants.MSLine.ATTRIBUTE_SET_VALUE, lstValue
								.get(j));
			}
		}
		return document;
	}

	public static void main(String[] args) {
		MSLine chart = new MSLine();
		chart.setCaption("Daily Visits");
		chart.setyAxisMinValue("0");
		chart.setyAxisMaxValue("100");

		List<Category> categories = new ArrayList<Category>();
		Category category = new Category();
		category.setName("2012-04-01");
		categories.add(category);
		category = new Category();
		category.setName("2012-04-02");
		categories.add(category);
		category = new Category();
		category.setName("2012-04-03");
		categories.add(category);
		category = new Category();
		category.setName("2012-04-04");
		categories.add(category);
		category = new Category();
		category.setName("2012-04-05");
		categories.add(category);
		category = new Category();
		category.setName("2012-04-06");
		categories.add(category);
		category = new Category();
		category.setName("2012-04-07");
		categories.add(category);

		List<Dataset> datasets = new ArrayList<Dataset>();
		Dataset dataset = null;
		List<String> value = null;

		dataset = new Dataset();
		value = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			value.add(String.valueOf(i + 1));
		}
		dataset.setValue(value);
		dataset.setSeriesName("1-2次");
		datasets.add(dataset);

		dataset = new Dataset();
		value = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			value.add(String.valueOf(i + 1));
		}
		dataset.setValue(value);
		dataset.setSeriesName("3-5次");
		datasets.add(dataset);

		dataset = new Dataset();
		value = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			value.add(String.valueOf(i + 1));
		}
		dataset.setValue(value);
		dataset.setSeriesName("6-9次");
		datasets.add(dataset);

		dataset = new Dataset();
		value = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			value.add(String.valueOf(i + 1));
		}
		dataset.setValue(value);
		dataset.setSeriesName("10-19次");
		datasets.add(dataset);

		dataset = new Dataset();
		value = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			value.add(String.valueOf(i + 1));
		}
		dataset.setValue(value);
		dataset.setSeriesName("20-49次");
		datasets.add(dataset);

		dataset = new Dataset();
		value = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			value.add(String.valueOf(i + 1));
		}
		dataset.setValue(value);
		dataset.setSeriesName("50+次");
		datasets.add(dataset);

		chart.setCategories(categories);
		chart.setDatasets(datasets);

		System.out.println(chart.generateChartDocument().asXML());
	}

}