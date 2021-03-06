package com.nearme.statistics.action.app.store;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.form.app.store.StoreForm;
import com.nearme.statistics.model.store.PushEntity;
import com.nearme.statistics.service.app.store.StoreService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * push推送报表
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class PushAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private StoreForm form;
	private List<PushEntity> pushList;
	private StoreService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(10);
		String endDate = DateUtil.getToday();
		String appVersion = "V2.3+";
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");
		form.setAppVersion(appVersion);

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		dto.setAppVersion(appVersion);
		pushList = service.getPushList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pushList);
		
		LogUtil.log(dto, "push推送");

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

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		pushList = service.getPushList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pushList);
		
		LogUtil.log(dto, "push推送");

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

		List<PushEntity> list = (List<PushEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("Push推送报表_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "Push推送报表" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "推送对象", "统计时间段", "推送细分用户", "发送push数量",
					"实际到达IMEI数", "到达率", "查看IMEI数", "拒绝IMEI数", "推送产生下载量",
					"该软件/专题此期间总下载量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				PushEntity entity = list.get(i);
				eu.writeLine(new String[] {
						String.valueOf(entity.getPushObj()),
						String.valueOf(entity.getTimePeriod()),
						String.valueOf(entity.getPushDetailUser()),
						String.valueOf(entity.getSendPushCnt()),
						String.valueOf(entity.getArriveImei()),
						String.valueOf(entity.getArriveRatio()),
						String.valueOf(entity.getLookImei()),
						String.valueOf(entity.getRefuseImei()),
						String.valueOf(entity.getPushCauseDown()),
						String.valueOf(entity.getPeriodTotalDown()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public StoreForm getForm() {
		return form;
	}

	public void setForm(StoreForm form) {
		this.form = form;
	}

	public List<PushEntity> getPushList() {
		return pushList;
	}

	public void setPushList(List<PushEntity> pushList) {
		this.pushList = pushList;
	}

	public StoreService getService() {
		return service;
	}

	public void setService(StoreService service) {
		this.service = service;
	}
}
