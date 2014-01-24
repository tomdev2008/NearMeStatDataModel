package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.ActivedaysEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 活跃天数
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class ActivedaysAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;
	private List<ActivedaysEntity> activeDaysList;
	private CommonService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXmonthAgo(1);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate.substring(0, startDate.lastIndexOf("-")));
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		activeDaysList = service.getActivedaysList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, activeDaysList);
		
		LogUtil.log(dto, "活跃天数");//log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate("yyyy-MM");
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		activeDaysList = service.getActivedaysList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, activeDaysList);
		
		LogUtil.log(dto, "活跃天数");//log记录查询

		return Action.SUCCESS;
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

	public List<ActivedaysEntity> getActiveDaysList() {
		return activeDaysList;
	}

	public void setActiveDaysList(List<ActivedaysEntity> activeDaysList) {
		this.activeDaysList = activeDaysList;
	}
}
