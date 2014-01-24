package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.UserlifecycleDecayEntity;
import com.nearme.statistics.model.common.UserlifecycleTotalEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户生命周期
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class UserlifecycleAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;
	private List<UserlifecycleTotalEntity> ulcTotalList;
	private List<UserlifecycleDecayEntity> ulcDecayList;
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

		String startDate = DateUtil.getDateOfXmonthAgo(7);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setDecaycircle(form.getDecaycircle());
		ulcTotalList = service.getUserlifecycleTotalList(dto);
		ulcDecayList = service.getUserlifecycleDecayList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, ulcTotalList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, ulcDecayList);
		
		LogUtil.log(dto, "用户生命周期");//log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setDecaycircle(form.getDecaycircle());
		ulcTotalList = service.getUserlifecycleTotalList(dto);
		ulcDecayList = service.getUserlifecycleDecayList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, ulcTotalList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, ulcDecayList);
		
		LogUtil.log(dto, "用户生命周期");//log记录查询

		return Action.SUCCESS;
	}

	/**
	 * 导出报表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object objtotal = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);
		Object objdecay = ServletActionContext.getRequest().getSession()
		.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);

		if (objtotal == null || objdecay == null) {
			return Action.SUCCESS;
		}

		List<UserlifecycleTotalEntity> listtotal = (List<UserlifecycleTotalEntity>) objtotal;
		List<UserlifecycleDecayEntity> listdecay = (List<UserlifecycleDecayEntity>) objdecay;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("用户生命周期_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "用户生命周期" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "","新增IMEI数", "新增账号数" });
			
			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (UserlifecycleTotalEntity entity : listtotal) {
				eu.writeLine(new String[] {
						"",
						String.valueOf(entity.getTotalNewImei()),
						String.valueOf(entity.getTotalNewUser())});
			}
			
			eu.writeLine(new String[] { "时间", "启动IMEI数","启动账号数" });
			for (int i = listdecay.size() - 1; i >= 0; i--) {
				UserlifecycleDecayEntity entity = listdecay.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getStartImei()),
						String.valueOf(entity.getStartUser()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	public List<UserlifecycleTotalEntity> getUlcTotalList() {
		return ulcTotalList;
	}

	public void setUlcTotalList(List<UserlifecycleTotalEntity> ulcTotalList) {
		this.ulcTotalList = ulcTotalList;
	}

	public List<UserlifecycleDecayEntity> getUlcDecayList() {
		return ulcDecayList;
	}

	public void setUlcDecayList(List<UserlifecycleDecayEntity> ulcDecayList) {
		this.ulcDecayList = ulcDecayList;
	}
}
