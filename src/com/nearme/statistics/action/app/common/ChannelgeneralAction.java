package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.ChannelgeneralEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 渠道概述
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-06
 */
public class ChannelgeneralAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;

	private final String TAG = "渠道概述";
	private BaseForm form;
	private List<ChannelgeneralEntity> channelgeneralList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(1);
		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		channelgeneralList = service.getChannelgeneralList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, channelgeneralList);

		LogUtil.log(dto, TAG);// log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		channelgeneralList = service.getChannelgeneralList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, channelgeneralList);

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

		List<ChannelgeneralEntity> list = (List<ChannelgeneralEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "渠道", "日新增imei", "日新增imei占比",
					"日启动imei", "日启动imei占比", "累计imei", "日新增账号", "日新增账号占比",
					"日启动账号", "日启动账号占比", "累计账号" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (ChannelgeneralEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getChannelname()),
						String.valueOf(entity.getNewimei()),
						String.valueOf(entity.getNewimeiratio()),
						String.valueOf(entity.getStartimei()),
						String.valueOf(entity.getStartimeiratio()),
						String.valueOf(entity.getTotalimei()),
						String.valueOf(entity.getNewssoid()),
						String.valueOf(entity.getNewssoidratio()),
						String.valueOf(entity.getStartssoid()),
						String.valueOf(entity.getStartssoidratio()),
						String.valueOf(entity.getTotalssoid()) });
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

	public List<ChannelgeneralEntity> getChannelgeneralList() {
		return channelgeneralList;
	}

	public void setChannelgeneralList(
			List<ChannelgeneralEntity> channelgeneralList) {
		this.channelgeneralList = channelgeneralList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
