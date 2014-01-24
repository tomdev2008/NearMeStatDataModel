package com.nearme.statistics.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nearme.statistics.chart.Line2D;
import com.nearme.statistics.chart.MSLine;
import com.nearme.statistics.chart.multi.Category;
import com.nearme.statistics.chart.multi.Dataset;
import com.nearme.statistics.model.BaseFxModel;

public class ChartUtil {

	/**
	 * 取BaseFxModel的字段用于显示曲线
	 * @param caption
	 * @param list
	 * @param field 1:显示cntUser,2:显示cntImei,3:显示cntTimes
	 * @return
	 */
	public static String baseFxModel2SingleLineChart(String caption, List<BaseFxModel> data, int field){
		if(data == null 
				|| data.size() == 0
				|| (field < 1 || field > 3)){
			return "";
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(BaseFxModel model : data){
			Map<String, String> map = new HashMap<String, String>();
			map.put("DDT", DateUtil.formatDate(model.getStatDate(), DateUtil.FMT_SHORT_DATETIME));
			if(field == 1)map.put("CNT", String.valueOf(model.getCntUser()));
			else if(field == 2)map.put("CNT", String.valueOf(model.getCntImei()));
			else if(field == 3)map.put("CNT", String.valueOf(model.getCntTimes()));
			list.add(map);
		}

		Line2D chart = new Line2D();
		chart.setCaption(caption);
		chart.setData(list);

		return chart.getXmlStr();
	}
	/**
	 * 同时显示cntUser,cntImei,cntTimes
	 * @param caption
	 * @param data
	 * @return
	 */
	public static String baseFxModel2MultiLineChart(String caption, List<BaseFxModel> data){
		if(data == null 
				|| data.size() == 0){
			return "";
		}
		MSLine chart = new MSLine();
		chart.setCaption(caption + "账号、手机、次数");
		
		List<Category> categories = new ArrayList<Category>();
		List<Dataset> datasets = new ArrayList<Dataset>();
		Dataset datasetUser = new Dataset();
		Dataset datasetImei = new Dataset();
		Dataset datasetTimes = new Dataset();
		List<String> valueUser = new ArrayList<String>();
		List<String> valueImei = new ArrayList<String>();
		List<String> valueTimes = new ArrayList<String>();
		for(BaseFxModel start : data){
			Category category = new Category();
			category.setName(DateUtil.formatDate(start.getStatDate(), DateUtil.FMT_SHORT_DATETIME));
			categories.add(category);
			
			valueUser.add(String.format("%d",start.getCntUser()));
			valueImei.add(String.format("%d",start.getCntImei()));
			valueTimes.add(String.format("%d",start.getCntTimes()));
		}
		
		datasetUser.setValue(valueUser);
		datasetUser.setSeriesName(caption + "账号");
		datasetUser.setColor("1D8BD1");
		datasets.add(datasetUser);
		datasetImei.setValue(valueImei);
		datasetImei.setSeriesName(caption + "手机");
		datasetImei.setColor("F1683C");
		datasets.add(datasetImei);
		datasetTimes.setValue(valueTimes);
		datasetTimes.setSeriesName(caption + "次数");
		datasetTimes.setColor("2AD62A");
		datasets.add(datasetTimes);
		
		chart.setCategories(categories);
		chart.setDatasets(datasets);
		return chart.generateChartDocument().asXML().replace("\"", "'").replace("\r", "").replace("\n", "");
	}
}
