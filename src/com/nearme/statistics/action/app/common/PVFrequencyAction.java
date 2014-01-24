package com.nearme.statistics.action.app.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.PvDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.PvEntity;
import com.nearme.statistics.service.app.common.PvService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 使用时长
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-18
 */
public class PVFrequencyAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BaseForm form;
	private List<PvEntity> frequencyList;
	private PvService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(1);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");

		PvDTO dto = new PvDTO();
		dto.setStatDateInt(Integer.parseInt(startDate.replaceAll("-", "").substring(0, 8)));
		dto.setSystemID(form.getSystemID());
		dto.setLidu("DAILY");
		frequencyList = service.listFrequency(dto);
		
		LogUtil.log(new BaseDTO(), "PV_使用频率");//log记录查询

		return Action.SUCCESS;
	}
	
	public String query() {
		String startDate = form.getStartTime();
		String systemID = form.getSystemID();
		String appVersion = form.getAppVersion();
		String channelID = form.getQudao();
		String lidu = form.getLidu();

		PvDTO dto = new PvDTO();
		dto.setSystemID(systemID);
		dto.setStatDateInt(Integer.parseInt(startDate.replaceAll("-", "").substring(0, 8)));
		dto.setAppVersion(appVersion);
		dto.setChannelID(channelID);
		dto.setLidu(lidu);
		frequencyList = service.listFrequency(dto);

		LogUtil.log(new BaseDTO(), "PV_使用频率");//log记录查询
		
		return Action.SUCCESS;
	}


	public BaseForm getForm() {
		return form;
	}
	public void setForm(BaseForm form) {
		this.form = form;
	}

	public List<PvEntity> getFrequencyList() {
		return frequencyList;
	}
	public void setFrequencyList(List<PvEntity> frequencyList) {
		this.frequencyList = frequencyList;
	}
	public PvService getService() {
		return service;
	}
	public void setService(PvService service) {
		this.service = service;
	}

}
