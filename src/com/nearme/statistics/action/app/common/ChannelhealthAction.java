package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.kkdesk.KkdeskDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.ChannelhealthEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 渠道健康度
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-9
 */
public class ChannelhealthAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "渠道健康度";
	private BaseForm form;
	private List<ChannelhealthEntity> healthnewList;// 新增留存
	private List<ChannelhealthEntity> healthstartList;// 启动留存
	private CommonService service;

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
		int systemID = form.getSystemIDValue();

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setLidu(lidu);
		form.setQudao("all");
		form.setAppVersion("all");

		KkdeskDTO dto = new KkdeskDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setLidu(lidu);
		dto.setSystemID(systemID);

		healthnewList = service.getChannelhealthnirList(dto);
		healthstartList = service.getChannelhealthsirList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, healthnewList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, healthstartList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String lidu = form.getLidu();
		String qudao = form.getQudao();
		int systemID = form.getSystemIDValue();

		KkdeskDTO dto = new KkdeskDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setLidu(lidu);
		dto.setQudao(qudao);
		dto.setSystemID(systemID);

		healthnewList = service.getChannelhealthnirList(dto);
		healthstartList = service.getChannelhealthsirList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, healthnewList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, healthstartList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object objnew = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);
		Object objstart = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);

		if (objnew == null && objstart == null) {
			return Action.SUCCESS;
		}

		List<ChannelhealthEntity> newlist = (List<ChannelhealthEntity>) objnew;
		List<ChannelhealthEntity> startlist = (List<ChannelhealthEntity>) objstart;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			writeNew(eu, newlist);
			writeStart(eu, startlist);

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	/**
	 * 新增留存导出
	 * 
	 * @param eu
	 * @param list
	 */
	private void writeNew(ExcelUtil eu, List<ChannelhealthEntity> list) {
		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(new String[] { "日期", "IMEI新增留存1X", "IMEI新增留存2X",
				"IMEI新增留存3X", "帐号新增留存1X", "帐号新增留存2X", "帐号新增留存3X" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (ChannelhealthEntity entity : list) {
			eu.writeLine(new String[] {
					DateUtil.formatDate2Short(entity.getStatDate()),
					String.valueOf(entity.getImei1x()),
					String.valueOf(entity.getImei2x()),
					String.valueOf(entity.getImei3x()),
					String.valueOf(entity.getSsoid1x()),
					String.valueOf(entity.getSsoid2x()),
					String.valueOf(entity.getSsoid3x()) });
		}
	}

	/**
	 * 启动留存导出
	 * 
	 * @param eu
	 * @param list
	 */
	private void writeStart(ExcelUtil eu, List<ChannelhealthEntity> list) {
		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(new String[] { "日期", "IMEI启动留存1X", "IMEI启动留存2X",
				"IMEI启动留存3X", "帐号启动留存1X", "帐号启动留存2X", "帐号启动留存3X" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (ChannelhealthEntity entity : list) {
			eu.writeLine(new String[] {
					DateUtil.formatDate2Short(entity.getStatDate()),
					String.valueOf(entity.getImei1x()),
					String.valueOf(entity.getImei2x()),
					String.valueOf(entity.getImei3x()),
					String.valueOf(entity.getSsoid1x()),
					String.valueOf(entity.getSsoid2x()),
					String.valueOf(entity.getSsoid3x()) });
		}
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public List<ChannelhealthEntity> getHealthnewList() {
		return healthnewList;
	}

	public void setHealthnewList(List<ChannelhealthEntity> healthnewList) {
		this.healthnewList = healthnewList;
	}

	public List<ChannelhealthEntity> getHealthstartList() {
		return healthstartList;
	}

	public void setHealthstartList(List<ChannelhealthEntity> healthstartList) {
		this.healthstartList = healthstartList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}