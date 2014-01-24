package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.IncreaseremainEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 新增留存
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class IncreaseremainAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;
	private List<IncreaseremainEntity> increaseRemainList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		increaseRemainList = service.getIncreaseremainList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, increaseRemainList);
		
		LogUtil.log(dto, "新增留存");//log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}
		//时间修正(for查询)
		if ("WEEKLY".equalsIgnoreCase(lidu)){
			startDate = DateUtil.AddDays(startDate, -6);
		} else if ("MONTHLY".equalsIgnoreCase(lidu)){
			startDate = DateUtil.getFirstDayOfXmonthAgo(startDate, 0);
			endDate = DateUtil.getFirstDayOfXmonthAgo(endDate, -1);
		}
		
		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		increaseRemainList = service.getIncreaseremainList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, increaseRemainList);
		
		LogUtil.log(dto, "新增留存");//log记录查询

		return Action.SUCCESS;
	}

	/**
	 * 导出报表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<IncreaseremainEntity> list = (List<IncreaseremainEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("新增留存_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "新增留存" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "新增IMEI数", "新增账号数",
					"1X新增IMEI留存数", "2X新增IMEI留存数", "3X新增IMEI留存数", "1X新增账号留存数",
					"2X新增账号留存数", "3X新增账号留存数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				IncreaseremainEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getNewImei()),
						String.valueOf(entity.getNewUser()),
						String.valueOf(entity.getNrr1()),
						String.valueOf(entity.getNrr2()),
						String.valueOf(entity.getNrr3()),
						String.valueOf(entity.getNurr1()),
						String.valueOf(entity.getNurr2()),
						String.valueOf(entity.getNurr3()) });
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
	
	public List<IncreaseremainEntity> getIncreaseRemainList() {
		return increaseRemainList;
	}

	public void setIncreaseRemainList(List<IncreaseremainEntity> increaseRemainList) {
		this.increaseRemainList = increaseRemainList;
	}
}
