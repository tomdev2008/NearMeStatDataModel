package com.nearme.statistics.action.app.liren;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.form.app.liren.LirenForm;
import com.nearme.statistics.model.liren.LRPushEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * push
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-19
 */
public class PushAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "push数据";
	private LirenForm form;
	private List<LRPushEntity> pushList;
	private LirenService service;

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

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());

		pushList = service.getPushList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pushList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);

		pushList = service.getPushList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pushList);

		LogUtil.log(dto, TAG);

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

		List<LRPushEntity> list = (List<LRPushEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "推送主题", "日期", "推送用户数", "发送push数",
					"push状态栏显示数", "到达率", "点击push数", "到达push页面数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());

			for (LRPushEntity entity : list) {
				eu.writeLine(new String[] { String.valueOf(entity.getTheme()),
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getUsercnt()),
						String.valueOf(entity.getSendcnt()),
						String.valueOf(entity.getShowcnt()),
						String.valueOf(entity.getArriveratio()),
						String.valueOf(entity.getClickcnt()),
						String.valueOf(entity.getArrivepagecnt()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public LirenForm getForm() {
		return form;
	}

	public void setForm(LirenForm form) {
		this.form = form;
	}

	public List<LRPushEntity> getPushList() {
		return pushList;
	}

	public void setPushList(List<LRPushEntity> pushList) {
		this.pushList = pushList;
	}

	public LirenService getService() {
		return service;
	}

	public void setService(LirenService service) {
		this.service = service;
	}
}
