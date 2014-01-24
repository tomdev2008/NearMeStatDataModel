package com.nearme.statistics.action.app.coloros;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.form.app.coloros.ColorosForm;
import com.nearme.statistics.model.coloros.COSDetailEntity;
import com.nearme.statistics.service.app.coloros.ColorosService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 明细数据
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-16
 */
public class DetailAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "明细数据";
	private ColorosForm form;
	private List<COSDetailEntity> detailList;
	private ColorosService service;

	/**
	 * 初始化
	 * 
	 * @return
	 */
	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		String lidu = Constants.DAILY;

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setLidu(lidu);
		int systemID = form.getSystemIDValue();

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		detailList = service.getDetailList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String query() {
		int systemID = form.getSystemIDValue();
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String lidu = form.getLidu();

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		detailList = service.getDetailList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<COSDetailEntity> list = (List<COSDetailEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动用户", "新增用户", "累计用户" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (COSDetailEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getStartuser()),
						String.valueOf(entity.getNewuser()),
						String.valueOf(entity.getLeijiuser()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public ColorosForm getForm() {
		return form;
	}

	public void setForm(ColorosForm form) {
		this.form = form;
	}

	public List<COSDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<COSDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public ColorosService getService() {
		return service;
	}

	public void setService(ColorosService service) {
		this.service = service;
	}
}
