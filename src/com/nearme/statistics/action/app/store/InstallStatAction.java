package com.nearme.statistics.action.app.store;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.form.app.store.StoreForm;
import com.nearme.statistics.model.store.InstallStatEntity;
import com.nearme.statistics.service.app.store.StoreService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 应用安装统计
 * 
 * @author Fgx
 * @version 1.0
 * @since 1.0, 2013-12-31
 */
public class InstallStatAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StoreForm form;
	private List<InstallStatEntity> installTotalList;
	private List<InstallStatEntity> installChannelTopList;
	private List<InstallStatEntity> installByChannelList;
	private List<InstallStatEntity> installFromAppList;
	private List<InstallStatEntity> appSourceList;
	private StoreService service;

	public String inittotal() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");
		form.setPackageName("com.oppo.market");
		form.setType("TOTAL");

		StoreDTO dto = new StoreDTO();
		dto.setStatDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "")));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		dto.setChannel(form.getPackageName());
		
		installTotalList = this.service.listInstallTotal(dto);
		
		LogUtil.log(dto, "流量分析");

		return "install_total";
	}
	
	public String inittop() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");
		form.setPackageName("com.oppo.market");
		form.setType("TOP");

		StoreDTO dto = new StoreDTO();
		dto.setStatDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "")));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		dto.setChannel(form.getPackageName());
		
		installChannelTopList = this.service.listInstallChannelTop(dto);
		
		LogUtil.log(dto, "流量分析");

		return "install_top";
	}
	
	public String initchannel() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");
		form.setPackageName("com.oppo.market");
		form.setType("CHANNEL");

		StoreDTO dto = new StoreDTO();
		dto.setStatDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "")));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		dto.setChannel(form.getPackageName());
		
		installByChannelList = this.service.listInstallByChannel(dto);
		
		LogUtil.log(dto, "流量分析");

		return "install_channel";
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String channel = form.getPackageName();
		String type = form.getType();

		if(startDate==null){
			form.setStartTime(DateUtil.getDateOfXdaysAgo(30));
			startDate = DateUtil.parseShortDateTime(form.getStartTime());
		}
		if(endDate==null){
			form.setEndTime(DateUtil.getToday());
			endDate = DateUtil.parseShortDateTime(form.getEndTime());
		}
		
		StoreDTO dto = new StoreDTO();
		dto.setStatDateInt(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		dto.setStartDateInt(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		dto.setEndDateInt(Integer.parseInt(DateUtil.formatDate(endDate, "yyyyMMdd")));
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setChannel(channel);
		
		String page = "install_channel";
		if("TOTAL".equals(type)){
			installTotalList = this.service.listInstallTotal(dto);
			page = "install_total";
		}else if("TOP".equals(type)){
			installChannelTopList = this.service.listInstallChannelTop(dto);
			page = "install_top";
		}else if("CHANNEL".equals(type)){
			installByChannelList = this.service.listInstallByChannel(dto);
		}else{
			installTotalList = this.service.listInstallTotal(dto);
			installChannelTopList = this.service.listInstallChannelTop(dto);
			installByChannelList = this.service.listInstallByChannel(dto);
		}
		
		LogUtil.log(dto, "流量分析");

		return page;
	}
	
	public String initJP() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");
		form.setPackageName("com.oppo.market");

		StoreDTO dto = new StoreDTO();
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "")));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		dto.setChannel(form.getPackageName());
		
		installFromAppList = this.service.listInstallFromApp(dto);
		
		LogUtil.log(dto, "竞品分析");

		return "install_index";
	}

	public String queryJP() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String channel = form.getPackageName();

		if(startDate==null){
			form.setStartTime(DateUtil.getDateOfXdaysAgo(30));
			startDate = DateUtil.parseShortDateTime(form.getStartTime());
		}
		if(endDate==null){
			form.setEndTime(DateUtil.getToday());
			endDate = DateUtil.parseShortDateTime(form.getEndTime());
		}
		
		StoreDTO dto = new StoreDTO();
		dto.setStartDateInt(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		dto.setEndDateInt(Integer.parseInt(DateUtil.formatDate(endDate, "yyyyMMdd")));
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setChannel(channel);

		installFromAppList = this.service.listInstallFromApp(dto);
		
		LogUtil.log(dto, "竞品分析");

		return "install_index";
	}
	
	public String detailJP() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String channel = form.getPackageName();
		int statDateInt = form.getStatDateInt();
		
		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "install_detail";
		}

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setChannel(channel);
		dto.setStatDateInt(statDateInt);

		appSourceList = this.service.listAppSource(dto);
		
		LogUtil.log(dto, "竞品分析_详情");

		return "install_detail";
	}
	

	public StoreForm getForm() {
		return form;
	}

	public void setForm(StoreForm form) {
		this.form = form;
	}

	public StoreService getService() {
		return service;
	}

	public void setService(StoreService service) {
		this.service = service;
	}

	public List<InstallStatEntity> getInstallTotalList() {
		return installTotalList;
	}

	public void setInstallTotalList(List<InstallStatEntity> installTotalList) {
		this.installTotalList = installTotalList;
	}

	public List<InstallStatEntity> getInstallChannelTopList() {
		return installChannelTopList;
	}

	public void setInstallChannelTopList(
			List<InstallStatEntity> installChannelTopList) {
		this.installChannelTopList = installChannelTopList;
	}

	public List<InstallStatEntity> getInstallByChannelList() {
		return installByChannelList;
	}

	public void setInstallByChannelList(List<InstallStatEntity> installByChannelList) {
		this.installByChannelList = installByChannelList;
	}

	public List<InstallStatEntity> getInstallFromAppList() {
		return installFromAppList;
	}

	public void setInstallFromAppList(List<InstallStatEntity> installFromAppList) {
		this.installFromAppList = installFromAppList;
	}

	public List<InstallStatEntity> getAppSourceList() {
		return appSourceList;
	}

	public void setAppSourceList(List<InstallStatEntity> appSourceList) {
		this.appSourceList = appSourceList;
	}

}
