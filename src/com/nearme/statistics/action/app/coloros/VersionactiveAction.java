package com.nearme.statistics.action.app.coloros;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.form.app.coloros.ColorosForm;
import com.nearme.statistics.model.coloros.COSVersionActiveEntity;
import com.nearme.statistics.service.app.coloros.ColorosService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 版本活跃用户排名
 * 
 * @author 林逸聪
 * @version 1.0
 * @since 1.0, 2013-10-18
 */
public class VersionactiveAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "版本活跃用户排名";
	private ColorosForm form;
	private List<COSVersionActiveEntity> versionactiveList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(1);
		String lidu = Constants.DAILY;
		int systemID = form.getSystemIDValue();

		form.setStartTime(startDate);
		form.setLidu(lidu);

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		versionactiveList = service.getVersionactiveList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, versionactiveList);

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
		Date endDate = startDate;// 周数据用到
		String lidu = form.getLidu();
		String model = form.getModel();
		
		if (Constants.MONTHLY.equals(lidu)) {
			startDate = DateUtil.getFirstDayOfXmonthAgo(startDate, 0);
		} else if(Constants.WEEKLY.equals(lidu)){
			startDate = DateUtil.AddDays(startDate, -6);
		}

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		dto.setModel(model);
		versionactiveList = service.getVersionactiveList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, versionactiveList);

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

		List<COSVersionActiveEntity> list = (List<COSVersionActiveEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "版本", "日期", "活跃用户" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (COSVersionActiveEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getVersion()),
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getActiveTotal()) });
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

	public List<COSVersionActiveEntity> getVersionactiveList() {
		return versionactiveList;
	}

	public void setVersionactiveList(
			List<COSVersionActiveEntity> versionactiveList) {
		this.versionactiveList = versionactiveList;
	}

	public ColorosService getService() {
		return service;
	}

	public void setService(ColorosService service) {
		this.service = service;
	}
}
