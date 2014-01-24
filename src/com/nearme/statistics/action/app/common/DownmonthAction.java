package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.DownmonthEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 下载月明细
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-10
 */
public class DownmonthAction extends ActionSupport {
	private static final long serialVersionUID = -7747673878091973779L;
	
	private final String TAG = "下载月明细";
	private BaseForm form;
	private List<DownmonthEntity> downmonthList;
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

		String startDate = DateUtil.getDateOfXmonthAgo(7);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate.substring(0,startDate.lastIndexOf("-")));
		form.setEndTime(endDate.substring(0,startDate.lastIndexOf("-")));
		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		downmonthList = service.getDownmonthList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downmonthList);
		
		LogUtil.log(dto, TAG);//log记录查询

		return Action.SUCCESS;
	}
	
	public String query() {
		Date startDate = form.getStartDate("yyyy-MM");
		Date endDate = form.getEndDate("yyyy-MM");
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String qudao  = form.getQudao();

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
		dto.setQudao(qudao);
		downmonthList = service.getDownmonthList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downmonthList);

		LogUtil.log(dto, TAG);//log记录查询
		
		return Action.SUCCESS;
	}

	/**
	 * 导出报表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<DownmonthEntity> list = (List<DownmonthEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "下载IMEI数", "下载帐号数", "下载次数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
		    for (DownmonthEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getDownimei()),
						String.valueOf(entity.getDownuser()),
						String.valueOf(entity.getDowncnt()) });
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

	public List<DownmonthEntity> getDownmonthList() {
		return downmonthList;
	}

	public void setDownmonthList(List<DownmonthEntity> downmonthList) {
		this.downmonthList = downmonthList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
