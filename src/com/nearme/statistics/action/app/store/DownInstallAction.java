package com.nearme.statistics.action.app.store;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.form.app.store.StoreForm;
import com.nearme.statistics.model.store.DownInstallEntity;
import com.nearme.statistics.service.app.store.StoreService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 下载安装成功率
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-16
 */
public class DownInstallAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreForm form;
	private List<DownInstallEntity> downInstallList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		downInstallList = service.listDownInstall(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downInstallList);
		
		LogUtil.log(dto, "下载安装成功率");

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

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		downInstallList = service.listDownInstall(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downInstallList);
		
		LogUtil.log(dto, "下载安装成功率");

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

		List<DownInstallEntity> list = (List<DownInstallEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("下载安装成功率_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "下载安装成功率" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "请求次数 ", "下载成功次数", "下载成功比",
					"下载失败次数", "下载失败比", "安裝成功次数", "安裝成功比" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				DownInstallEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatdate()),
						String.valueOf(entity.getDownRequest()),
						String.valueOf(entity.getDownSuccess()),
						String.valueOf(entity.getDownSuccessRate()),
						String.valueOf(entity.getDownFail()),
						String.valueOf(entity.getDownFailRate()),
						String.valueOf(entity.getInstallSuccess()),
						String.valueOf(entity.getInstallSuccessRate()) });
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

	public StoreService getService() {
		return service;
	}

	public void setService(StoreService service) {
		this.service = service;
	}

	public List<DownInstallEntity> getDownInstallList() {
		return downInstallList;
	}

	public void setDownInstallList(List<DownInstallEntity> downInstallList) {
		this.downInstallList = downInstallList;
	}

}
