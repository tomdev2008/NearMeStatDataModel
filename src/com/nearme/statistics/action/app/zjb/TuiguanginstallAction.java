package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.TuiguanginstallEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 推广软件安装
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class TuiguanginstallAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private ZjbForm form;
	private List<TuiguanginstallEntity> tuiguanginstallList;
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form = new ZjbForm();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setLidu("DAILY");

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setLidu(form.getLidu());
		tuiguanginstallList = service.getTuiguanginstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, tuiguanginstallList);
		
		LogUtil.log(dto, "推广软件安装");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String softName = form.getSoftName();

		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setSoftName(softName);
		tuiguanginstallList = service.getTuiguanginstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, tuiguanginstallList);
		
		LogUtil.log(dto, "推广软件安装");

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

		List<TuiguanginstallEntity> list = (List<TuiguanginstallEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("推广软件安装_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "推广软件安装" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "软件名", "下载量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				TuiguanginstallEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getSoftname()),
						String.valueOf(entity.getDowncnt()) });
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

	public List<TuiguanginstallEntity> getTuiguanginstallList() {
		return tuiguanginstallList;
	}

	public void setTuiguanginstallList(
			List<TuiguanginstallEntity> tuiguanginstallList) {
		this.tuiguanginstallList = tuiguanginstallList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
