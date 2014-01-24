package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.PagejumpEntity;
import com.nearme.statistics.model.common.PagevisitEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 页面访问分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class PagevisitAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;
	private List<PagevisitEntity> pagevisitList;
	private List<PagejumpEntity> jumpList;
	private CommonService service;

	private String page;// 跳转明细参数

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXmonthAgo(7);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());

		pagevisitList = service.getPagevisitList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pagevisitList);
		
		LogUtil.log(dto, "页面访问分析");//log记录查询

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

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);

		pagevisitList = service.getPagevisitList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pagevisitList);
		
		LogUtil.log(dto, "页面访问分析");//log记录查询

		return Action.SUCCESS;
	}

	public String queryjump() {
		if(page == null){
			return Action.SUCCESS;
		}
		
		BaseDTO dto = new BaseDTO();
		dto.setPage(page);
		jumpList = service.getPagejumpList(dto);

		return "queryjump";
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

		List<PagevisitEntity> list = (List<PagevisitEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("页面访问分析_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "页面访问分析" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "页面", "页面访问次数", "页面访问IMEI数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (PagevisitEntity entity : list) {
				eu.writeLine(new String[] { entity.getPage(),
						entity.getPageVisit(), entity.getPageVisitImei() });
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

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

	public List<PagevisitEntity> getPagevisitList() {
		return pagevisitList;
	}

	public void setPagevisitList(List<PagevisitEntity> pagevisitList) {
		this.pagevisitList = pagevisitList;
	}

	public List<PagejumpEntity> getJumpList() {
		return jumpList;
	}

	public void setJumpList(List<PagejumpEntity> jumpList) {
		this.jumpList = jumpList;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
