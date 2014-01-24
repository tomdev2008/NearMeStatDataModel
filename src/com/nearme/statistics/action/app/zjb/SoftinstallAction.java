package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.SoftinstallEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 软件安装排行
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class SoftinstallAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private ZjbForm form;
	private List<SoftinstallEntity> softinstallList;
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXdaysAgo(1);
		form = new ZjbForm();
		form.setStartTime(startDate);
		form.setLidu("DAILY");

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setLidu(form.getLidu());
		softinstallList = service.getSoftinstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, softinstallList);
		
		LogUtil.log(dto, "软件安装排行");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();

		if (startDate == null) {
			return Action.SUCCESS;
		}

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(startDate);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		softinstallList = service.getSoftinstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, softinstallList);
		
		LogUtil.log(dto, "软件安装排行");

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

		List<SoftinstallEntity> list = (List<SoftinstallEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("软件安装排行_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "软件安装排行" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "软件ID", "软件名称", "软件安装数量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				SoftinstallEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getSoftID()),
						String.valueOf(entity.getSoftName()),
						String.valueOf(entity.getSoftInstall()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public ZjbForm getForm() {
		return form;
	}

	public void setForm(ZjbForm form) {
		this.form = form;
	}

	public List<SoftinstallEntity> getSoftinstallList() {
		return softinstallList;
	}

	public void setSoftinstallList(List<SoftinstallEntity> softinstallList) {
		this.softinstallList = softinstallList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
