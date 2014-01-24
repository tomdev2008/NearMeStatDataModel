package com.nearme.statistics.action.app.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.PvDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.VisitPathEntity;
import com.nearme.statistics.service.app.common.PvService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 当前页面访问路径统计
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-18
 */
public class PVPathAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BaseForm form;
	private List<VisitPathEntity> visitList;
	private List<VisitPathEntity> jumpList;
	private PvService service;

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

		PvDTO dto = new PvDTO();
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "").substring(0, 8)));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "").substring(0, 8)));
		dto.setSystemID(form.getSystemID());
		dto.setAppVersion(form.getAppVersion());
		visitList = service.listCurPageVisit(dto);
		
		LogUtil.log(new BaseDTO(), "PV_页面访问路径");//log记录查询

		return "visit";
	}
	
	public String visit() {
		String startDate = form.getStartTime();
		String endDate = form.getEndTime();
		String systemID = form.getSystemID();
		String appVersion = form.getAppVersion();

		PvDTO dto = new PvDTO();
		dto.setSystemID(systemID);
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "").substring(0, 8)));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "").substring(0, 8)));
		dto.setAppVersion(appVersion);
		visitList = service.listCurPageVisit(dto);

		LogUtil.log(new BaseDTO(), "PV_页面访问路径");//log记录查询
		
		return "visit";
	}
	
	public String jump() {
		String startDate = form.getStartTime();
		String endDate = form.getEndTime();
		String systemID = form.getSystemID();
		String appVersion = form.getAppVersion();
		String curPage = form.getName();

		PvDTO dto = new PvDTO();
		dto.setSystemID(systemID);
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "").substring(0, 8)));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "").substring(0, 8)));
		dto.setAppVersion(appVersion);
		dto.setCurPage(curPage);
		
		jumpList = service.listCurPageJump(dto);

		LogUtil.log(new BaseDTO(), "PV_页面访问路径_跳转");//log记录查询
		
		return "jump";
	}


	public BaseForm getForm() {
		return form;
	}
	public void setForm(BaseForm form) {
		this.form = form;
	}
	public List<VisitPathEntity> getVisitList() {
		return visitList;
	}
	public void setVisitList(List<VisitPathEntity> visitList) {
		this.visitList = visitList;
	}
	public List<VisitPathEntity> getJumpList() {
		return jumpList;
	}
	public void setJumpList(List<VisitPathEntity> jumpList) {
		this.jumpList = jumpList;
	}
	public PvService getService() {
		return service;
	}
	public void setService(PvService service) {
		this.service = service;
	}

}
