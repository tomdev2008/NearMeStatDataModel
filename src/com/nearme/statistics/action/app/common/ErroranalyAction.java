package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.ErrorDetailEntity;
import com.nearme.statistics.model.common.ErrorEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 错误分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-18
 */
public class ErroranalyAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;

	private final String TAG = "错误分析";
	private final int PAGE_SHOW_ROWS = 50;// 分页显示的条数
	private BaseForm form;
	private List<ErrorEntity> errorList;// 错误日明细
	private List<ErrorDetailEntity> errordetailList;// 错误详情
	private List<ErrorDetailEntity> errormodeldistributeList;// 错误机型分布
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

		String startDate = DateUtil.getDateOfXdaysAgo(7);
		String endDate = DateUtil.getToday();

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID("" + systemID);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());

		errorList = service.getErrordailyList(dto);

		// 查询详情条数，分页
		long detailcnt = service.getErrordetailCnt(dto);// 记录数
		int pagerows = PAGE_SHOW_ROWS;// 每页显示条数
		int pagecnt = (int) (detailcnt % PAGE_SHOW_ROWS == 0 ? detailcnt
				/ PAGE_SHOW_ROWS : (detailcnt / PAGE_SHOW_ROWS + 1));// 总页数
		int pagecurr = 1;// 显示第几页

		form.setPagerows(pagerows);
		form.setPagecnt(pagecnt);
		form.setPagecurr(pagecurr);

		dto.setRownumbegin((pagecurr - 1) * pagerows + 1);
		dto.setRownumend(pagecurr * pagerows);
		errordetailList = service.getErrordetailList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, errordetailList);

		LogUtil.log(dto, TAG);// log记录查询

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

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);

		errorList = service.getErrordailyList(dto);

		// 查询详情条数，分页
		long detailcnt = service.getErrordetailCnt(dto);// 记录数
		int pagerows = PAGE_SHOW_ROWS;// 每页显示条数
		int pagecnt = (int) (detailcnt % PAGE_SHOW_ROWS == 0 ? detailcnt
				/ PAGE_SHOW_ROWS : (detailcnt / PAGE_SHOW_ROWS + 1));// 总页数
		int pagecurr = 1;// 显示第几页

		form.setPagerows(pagerows);
		form.setPagecnt(pagecnt);
		form.setPagecurr(pagecurr);

		dto.setRownumbegin((pagecurr - 1) * pagerows + 1);
		dto.setRownumend(pagecurr * pagerows);
		errordetailList = service.getErrordetailList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, errordetailList);

		LogUtil.log(dto, TAG);// log记录查询

		return Action.SUCCESS;
	}

	/**
	 * 查询某一页
	 * 
	 * @return
	 */
	public String queryFixpage() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		int pagecurr = form.getPagecurr();// 要查询的页

		if (systemID == 0) {
			return "erroranaly_general";
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "erroranaly_general";
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setRownumbegin((pagecurr - 1) * PAGE_SHOW_ROWS + 1);
		dto.setRownumend(pagecurr * PAGE_SHOW_ROWS);

		// 查询详情条数，分页
		long detailcnt = service.getErrordetailCnt(dto);// 记录数
		int pagerows = PAGE_SHOW_ROWS;// 每页显示条数
		int pagecnt = (int) (detailcnt % PAGE_SHOW_ROWS == 0 ? detailcnt
				/ PAGE_SHOW_ROWS : (detailcnt / PAGE_SHOW_ROWS + 1));// 总页数

		form.setPagerows(pagerows);
		form.setPagecnt(pagecnt);
		form.setPagecurr(pagecurr);

		errordetailList = service.getErrordetailList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, errordetailList);

		LogUtil.log(dto, TAG);// log记录查询

		return "erroranaly_general";
	}

	public String queryModeldistribute() {
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String errordetail = form.getErrordetail();

		if (systemID == 0) {
			return "erroranaly_detail_modeldistribute";
		}

		BaseDTO dto = new BaseDTO();
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setErrordetail(errordetail);

		errormodeldistributeList = service.getErrormodeldistribute(dto);

		return "erroranaly_detail_modeldistribute";
	}

	/**
	 * 导出当前页
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportCurrpage() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<ErrorDetailEntity> list = (List<ErrorDetailEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "错误摘要", "错误详情", "应用版本", "错误次数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (ErrorDetailEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getGenneral()),
						String.valueOf(entity.getDetail()),
						String.valueOf(entity.getVersion()),
						String.valueOf(entity.getErrorcnt()) });
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

	public List<ErrorEntity> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorEntity> errorList) {
		this.errorList = errorList;
	}

	public List<ErrorDetailEntity> getErrordetailList() {
		return errordetailList;
	}

	public void setErrordetailList(List<ErrorDetailEntity> errordetailList) {
		this.errordetailList = errordetailList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

	public List<ErrorDetailEntity> getErrormodeldistributeList() {
		return errormodeldistributeList;
	}

	public void setErrormodeldistributeList(
			List<ErrorDetailEntity> errormodeldistributeList) {
		this.errormodeldistributeList = errormodeldistributeList;
	}
}
