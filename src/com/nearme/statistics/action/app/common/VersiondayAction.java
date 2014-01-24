package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.VersiondayEntity;
import com.nearme.statistics.model.sys.systemTable.AppVersionEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.tags.AppVersionSelectTag;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 版本日明细
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-10
 */
public class VersiondayAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;

	private BaseForm form;
	private List<VersiondayEntity> versiondayList;
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

		String startDate = DateUtil.getTimeOf7daysAgo();
		String endDate = DateUtil.getToday();
		// 获取第一个版本
		List<AppVersionEntity> versionlist = AppVersionSelectTag
				.getVersionList(systemID);
		String appVersion = versionlist.get(0).getAppVersion();
		
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setAppVersion(appVersion);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setAppVersion(appVersion);
		versiondayList = service.getVersiondayList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, versiondayList);
		
		LogUtil.log(dto, "版本日明细");//log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();

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
		versiondayList = service.getVersiondayList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, versiondayList);
		
		LogUtil.log(dto, "版本日明细");//log记录查询

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

		List<VersiondayEntity> list = (List<VersiondayEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("版本日明细_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "版本日明细" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "全版本启动IMEI数", "全版本新增IMEI数",
					"该版本启动IMEI数", "该版本启动IMEI占比", "该版本新增IMEI数", "该版本新增IMEI占比",
					"升级至该版本IMEI数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				VersiondayEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getAllstartImei()),
						String.valueOf(entity.getAllnewImei()),
						String.valueOf(entity.getStartImei()),
						String.valueOf(entity.getStartRatio()),
						String.valueOf(entity.getNewImei()),
						String.valueOf(entity.getNewRatio()),
						String.valueOf(entity.getUpdateImei()) });
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

	public List<VersiondayEntity> getVersiondayList() {
		return versiondayList;
	}

	public void setVersiondayList(List<VersiondayEntity> versiondayList) {
		this.versiondayList = versiondayList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

}
