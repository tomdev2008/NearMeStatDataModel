package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.Remain30daysEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 30天留存
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-11
 */
public class Remain30daysAction extends ActionSupport {
	private static final long serialVersionUID = -7747673878091973779L;

	private final String TAG = "30天留存";
	private BaseForm form;
	private List<Remain30daysEntity> remain30daysList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(7);
		String endDate = DateUtil.getToday();
		String type = "STARTIMEI";

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID("" + systemID);
		form.setType(type);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setType(type);
		remain30daysList = service.getRemain30daysList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, remain30daysList);

		LogUtil.log(dto, TAG);// log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String type = form.getType();
		String appVersion = form.getAppVersion();
		String qudao = form.getQudao();

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
		dto.setType(type);
		dto.setAppVersion(appVersion);
		dto.setQudao(qudao);
		remain30daysList = service.getRemain30daysList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, remain30daysList);

		LogUtil.log(dto, TAG);// log记录查询

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

		List<Remain30daysEntity> list = (List<Remain30daysEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "首次使用时间", "新增用户", "1天后（%）", "2天后（%）",
					"3天后（%）", "4天后（%）", "5天后（%）", "6天后（%）", "7天后（%）",
					"14天后（%）", "30天后（%）" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (Remain30daysEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getRemain()),
						String.valueOf(entity.getRemainratio1day()),
						String.valueOf(entity.getRemainratio2day()),
						String.valueOf(entity.getRemainratio3day()),
						String.valueOf(entity.getRemainratio4day()),
						String.valueOf(entity.getRemainratio5day()),
						String.valueOf(entity.getRemainratio6day()),
						String.valueOf(entity.getRemainratio7day()),
						String.valueOf(entity.getRemainratio14day()),
						String.valueOf(entity.getRemainratio30day()) });
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

	public List<Remain30daysEntity> getRemain30daysList() {
		return remain30daysList;
	}

	public void setRemain30daysList(List<Remain30daysEntity> remain30daysList) {
		this.remain30daysList = remain30daysList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
