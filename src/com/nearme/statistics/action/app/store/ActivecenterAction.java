package com.nearme.statistics.action.app.store;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.form.app.store.StoreForm;
import com.nearme.statistics.model.store.ActivecenterEntity;
import com.nearme.statistics.service.app.store.StoreService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 活动中心报表
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class ActivecenterAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private StoreForm form;
	private List<ActivecenterEntity> activecenterList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(1);
		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		activecenterList = service.getActivecenterList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, activecenterList);
		
		LogUtil.log(dto, "活动中心");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		activecenterList = service.getActivecenterList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, activecenterList);
		
		LogUtil.log(dto, "活动中心");

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

		List<ActivecenterEntity> list = (List<ActivecenterEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("活动中心报表_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "活动中心报表" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "位置", "资源名称", "下载量", "浏览次数",
					"浏览下载比" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				ActivecenterEntity entity = list.get(i);
				eu.writeLine(new String[] {
						String.valueOf(entity.getPosition()),
						String.valueOf(entity.getSourceName()),
						String.valueOf(entity.getDownCnt()),
						String.valueOf(entity.getLiulanCnt()),
						String.valueOf(entity.getLiulanDownRatio()) });
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
	
	public List<ActivecenterEntity> getActivecenterList() {
		return activecenterList;
	}

	public void setActivecenterList(List<ActivecenterEntity> activecenterList) {
		this.activecenterList = activecenterList;
	}

	public StoreService getService() {
		return service;
	}

	public void setService(StoreService service) {
		this.service = service;
	}
}
