package com.nearme.statistics.action.app.grzx;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.grzx.GrzxDTO;
import com.nearme.statistics.form.app.grzx.GrzxForm;
import com.nearme.statistics.model.grzx.GrzxNewfromEntity;
import com.nearme.statistics.service.app.grzx.GrzxService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 新增来源
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class NewfromAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "新增来源";
	private GrzxForm form;
	private List<GrzxNewfromEntity> newfromList;
	private GrzxService service;

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

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());

		GrzxDTO dto = new GrzxDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		newfromList = service.getNewfromList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, newfromList);

		LogUtil.log(dto, TAG);

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

		GrzxDTO dto = new GrzxDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		newfromList = service.getNewfromList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, newfromList);

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

		List<GrzxNewfromEntity> list = (List<GrzxNewfromEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu
					.writeLine(new String[] { "日期", "来源", "当日新增帐号数", "新增帐号数",
							"新增占比" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				GrzxNewfromEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getComefrom()),
						String.valueOf(entity.getTotalnewuser()),
						String.valueOf(entity.getNewuser()),
						String.valueOf(entity.getNewratio()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public GrzxForm getForm() {
		return form;
	}

	public void setForm(GrzxForm form) {
		this.form = form;
	}

	public List<GrzxNewfromEntity> getNewfromList() {
		return newfromList;
	}

	public void setNewfromList(List<GrzxNewfromEntity> newfromList) {
		this.newfromList = newfromList;
	}

	public GrzxService getService() {
		return service;
	}

	public void setService(GrzxService service) {
		this.service = service;
	}
}
