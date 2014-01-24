package com.nearme.statistics.action.app.coloros;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.form.app.coloros.ColorosForm;
import com.nearme.statistics.model.coloros.COSAreaEntity;
import com.nearme.statistics.service.app.coloros.ColorosService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 活跃用户地域分布
 *
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-11-22
 */
public class AreaAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private ColorosForm form;
	private List<COSAreaEntity> resultList;
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

		form.setStartTime(startDate);
		form.setLidu(lidu);
		int systemID = form.getSystemIDValue();

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDateStr(DateUtil.formatDate(DateUtil.parseDate(startDate,
				"yyyy-MM-dd"), "yyyyMMdd"));
		dto.setSystemID(systemID);
		resultList = service.listAreaStart(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, resultList);

		LogUtil.log(dto, "活跃用户地域分布");

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
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		
		String startDateStr = DateUtil.formatDate(startDate, "yyyyMMdd");

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDateStr(startDateStr);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setSystemID(systemID);
		resultList = service.listAreaStart(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, resultList);

		LogUtil.log(dto, "活跃用户地域分布");

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<COSAreaEntity> list = (List<COSAreaEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("活跃用户地域分布");

		try {
			eu.createSheets(1, new String[] { "活跃用户地域分布" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "城市", "活跃用户" , "占比" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			String tmp = null;
			StringBuffer sbf = null;
			for (COSAreaEntity entity : list) {
				sbf = new StringBuffer();
				tmp = entity.getCountry();
				sbf.append(tmp==null ? "" : tmp).append("[");
				tmp = entity.getProvince();
				sbf.append(tmp==null ? "" : tmp).append("][");
				tmp = entity.getCity();
				sbf.append(tmp==null ? "" : tmp).append("]");
				eu.writeLine(new String[] {
						String.valueOf(entity.getStatDate()),
						String.valueOf(sbf.toString()),
						String.valueOf(entity.getStartImeis()),
						String.valueOf(entity.getPercent())});
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


	public List<COSAreaEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<COSAreaEntity> resultList) {
		this.resultList = resultList;
	}

	public ColorosService getService() {
		return service;
	}

	public void setService(ColorosService service) {
		this.service = service;
	}
}
