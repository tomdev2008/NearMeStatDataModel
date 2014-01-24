package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.DownremainEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 下载留存
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-11
 */
public class DownremainAction extends ActionSupport {
	private static final long serialVersionUID = -7747673878091973779L;

	private final String TAG = "下载留存";
	private BaseForm form;
	private List<DownremainEntity> downremainList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		String lidu  = Constants.DAILY;
		
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID("" + systemID);
		form.setLidu(lidu);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(lidu);
		downremainList = service.getDownremainList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downremainList);

		LogUtil.log(dto, TAG);// log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String qudao = form.getQudao();

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
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setQudao(qudao);
		downremainList = service.getDownremainList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downremainList);

		LogUtil.log(dto, TAG);// log记录查询

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

		List<DownremainEntity> list = (List<DownremainEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "下载IMEI数", "DR1", "DR2", "DR3",
					"DRR1(%)", "DRR2(%)", "DRR3(%)", "新增下载IMEI数", "NDR1",
					"NDR2", "NDR3", "NDRR1(%)", "NDRR2(%)", "NDRR3(%)" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (DownremainEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getDownimei()),
						String.valueOf(entity.getDr1()),
						String.valueOf(entity.getDr2()),
						String.valueOf(entity.getDr3()),
						String.valueOf(entity.getDrr1()),
						String.valueOf(entity.getDrr2()),
						String.valueOf(entity.getDrr3()),
						String.valueOf(entity.getNewdownimei()),
						String.valueOf(entity.getNdr1()),
						String.valueOf(entity.getNdr2()),
						String.valueOf(entity.getNdr3()),
						String.valueOf(entity.getNdrr1()),
						String.valueOf(entity.getNdrr2()),
						String.valueOf(entity.getNdrr3()) });
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

	public List<DownremainEntity> getDownremainList() {
		return downremainList;
	}

	public void setDownremainList(List<DownremainEntity> downremainList) {
		this.downremainList = downremainList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
