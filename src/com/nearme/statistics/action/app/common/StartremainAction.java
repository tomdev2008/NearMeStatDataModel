package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.StartremainEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 启动留存
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class StartremainAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;
	private List<StartremainEntity> startRemainList;
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
		startRemainList = service.getStartremainList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, startRemainList);
		
		LogUtil.log(dto, "启动留存");//log记录查询

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
		startRemainList = service.getStartremainList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, startRemainList);
		
		LogUtil.log(dto, "启动留存");//log记录查询

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

		List<StartremainEntity> list = (List<StartremainEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("月明细数据_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "月明细数据" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动IMEI数", "启动账号数",
					"1X启动IMEI留存率", "2X启动IMEI留存率", "3X启动IMEI留存率", "1X启动账号留存率",
					"2X启动账号留存率", "3X启动账号留存率" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				StartremainEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getStartImei()),
						String.valueOf(entity.getStartUser()),
						String.valueOf(entity.getLrr1()),
						String.valueOf(entity.getLrr2()),
						String.valueOf(entity.getLrr3()),
						String.valueOf(entity.getLurr1()),
						String.valueOf(entity.getLurr2()),
						String.valueOf(entity.getLurr3()) });
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

	public List<StartremainEntity> getStartRemainList() {
		return startRemainList;
	}

	public void setStartRemainList(List<StartremainEntity> startRemainList) {
		this.startRemainList = startRemainList;
	}
}
