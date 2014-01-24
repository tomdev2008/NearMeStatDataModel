package com.nearme.statistics.action.app.coloros;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.form.app.coloros.ColorosForm;
import com.nearme.statistics.model.coloros.COSMonthsrEntity;
import com.nearme.statistics.service.app.coloros.ColorosService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 月启动留存
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-25
 */
public class MonthsrAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "月启动留存";
	private ColorosForm form;
	private List<COSMonthsrEntity> monthsrList;
	private ColorosService service;

	/**
	 * 初始化
	 *
	 * @return
	 */
	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXmonthAgo(7);
		String endDate = DateUtil.getToday();

		form.setStartTime(startDate.substring(0,startDate.lastIndexOf("-")));
		form.setEndTime(endDate.substring(0,startDate.lastIndexOf("-")));
		int systemID = form.getSystemIDValue();

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(systemID);

		monthsrList = service.getMonthsrList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, monthsrList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	/**
	 * 查询
	 *
	 * @return
	 */
	public String query() {
		int systemID = form.getSystemIDValue();
		Date startDate = form.getStartDate("yyyy-MM");
		Date endDate = form.getEndDate("yyyy-MM");
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setSystemID(systemID);
		monthsrList = service.getMonthsrList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, monthsrList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<COSMonthsrEntity> list = (List<COSMonthsrEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动用户数" , "启动留存率x1(%)" , "启动留存率x2(%)", "启动留存率x3(%)"});

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (COSMonthsrEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getLaunch_imei()),
						String.valueOf(entity.getLrr1_imei()),
						String.valueOf(entity.getLrr2_imei()),
						String.valueOf(entity.getLrr3_imei())});
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public ColorosForm getForm() {
		return form;
	}

	public void setForm(ColorosForm form) {
		this.form = form;
	}

	public List<COSMonthsrEntity> getMonthsrList() {
		return monthsrList;
	}

	public void setMonthsrList(List<COSMonthsrEntity> monthsrList) {
		this.monthsrList = monthsrList;
	}

	public ColorosService getService() {
		return service;
	}

	public void setService(ColorosService service) {
		this.service = service;
	}
}
