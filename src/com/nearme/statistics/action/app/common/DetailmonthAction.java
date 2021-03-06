package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.DetailmonthEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 月明细数据
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class DetailmonthAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;
	private List<DetailmonthEntity> detailMonthList;
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
		form.setStartTime(startDate.substring(0,startDate.lastIndexOf("-")));
		form.setEndTime(endDate.substring(0,startDate.lastIndexOf("-")));
		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		detailMonthList = service.getDetailmonthList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailMonthList);
		
		LogUtil.log(dto, "月明细数据");//log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate("yyyy-MM");
		Date endDate = form.getEndDate("yyyy-MM");
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

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
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		detailMonthList = service.getDetailmonthList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailMonthList);
		
		LogUtil.log(dto, "月明细数据");//log记录查询

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

		List<DetailmonthEntity> list = (List<DetailmonthEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("月明细数据_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "月明细数据" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动IMEI数", "日均启动IMEI数", "启动帐号数",
					"日均启动帐号数", "新增IMEI数", "日均新增IMEI数", "新增帐号数", "日均新增帐号数",
					"平均活跃天数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				DetailmonthEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getStartImei()),
						String.valueOf(entity.getAvgStartImei()),
						String.valueOf(entity.getStartUser()),
						String.valueOf(entity.getAvgStartUser()),
						String.valueOf(entity.getNewImei()),
						String.valueOf(entity.getAvgNewImei()),
						String.valueOf(entity.getNewUser()),
						String.valueOf(entity.getAvgNewUser()),
						String.valueOf(entity.getAvgActiveDay())});
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

	public List<DetailmonthEntity> getDetailMonthList() {
		return detailMonthList;
	}

	public void setDetailMonthList(List<DetailmonthEntity> detailMonthList) {
		this.detailMonthList = detailMonthList;
	}
}
