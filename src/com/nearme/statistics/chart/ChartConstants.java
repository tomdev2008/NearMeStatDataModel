package com.nearme.statistics.chart;

/**
 * 统计图常量类
 *
 * @author 刘超
 * @version 1.0
 * @since 1.0,2012-4-12
 */
public class ChartConstants {

	public static final String ELEMENT_ROOT = "graph";

	public static final String ATTRIBUTE_CAPTION = "caption";
	public static final String ATTRIBUTE_SUBCAPTION = "subcaption";

	public static final String ATTRIBUTE_XAXISNAME = "xAxisName";
	public static final String ATTRIBUTE_YAXISNAME = "yAxisName";
	public static final String ATTRIBUTE_NUMBERPREFIX = "numberPrefix";
	public static final String ATTRIBUTE_SHOWNAMES = "showNames";

	public static final String ATTRIBUTE_HOVERCAPBG = "hovercapbg";
	public static final String ATTRIBUTE_HOVERCAPBORDER = "hovercapborder";
	public static final String ATTRIBUTE_FORMATNUMBERSCALE = "formatNumberScale";
	public static final String ATTRIBUTE_DECIMALPRECISION = "decimalPrecision";
	public static final String ATTRIBUTE_SHOWVALUES = "showvalues";
	public static final String ATTRIBUTE_NUMDIVLINES = "numdivlines";
	public static final String ATTRIBUTE_NUMVDIVLINES = "numVdivlines";
	public static final String ATTRIBUTE_YAXISMINVALUE = "yaxisminvalue";
	public static final String ATTRIBUTE_YAXISMAXVALUE = "yaxismaxvalue";
	public static final String ATTRIBUTE_ROTATENAMES = "rotateNames";

	protected interface Line {

		public static final String ELEMENT_SET = "set";

		public static final String ATTRIBUTE_SET_NAME = "name";
		public static final String ATTRIBUTE_SET_VALUE = "value";
		public static final String ATTRIBUTE_SET_HOVERTEXT = "hoverText";

	}

	protected interface MSLine {

		public static final String ELEMENT_CATEGORIES = "categories";
		public static final String ELEMENT_CATEGORY = "category";
		public static final String ELEMENT_DATASET = "dataset";
		public static final String ELEMENT_SET = "set";

		public static final String ATTRIBUTE_CATEGORY_NAME = "name";

		public static final String ATTRIBUTE_DATASET_SERIESNAME = "seriesName";
		public static final String ATTRIBUTE_DATASET_COLOR = "color";
		public static final String ATTRIBUTE_DATASET_ANCHORBORDERCOLOR = "anchorBorderColor";
		public static final String ATTRIBUTE_DATASET_ANCHORBGCOLOR = "anchorBgColor";

		public static final String ATTRIBUTE_SET_VALUE = "value";

	}

}
