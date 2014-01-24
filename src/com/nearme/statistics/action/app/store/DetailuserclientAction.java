package com.nearme.statistics.action.app.store;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.form.app.store.StoreForm;
import com.nearme.statistics.model.store.DetailuserclientEntity;
import com.nearme.statistics.service.app.store.StoreService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 细分用户客户端行为统计
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class DetailuserclientAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private StoreForm form;
	private List<DetailuserclientEntity> detailuserclientList;
	private StoreService service;

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
		form.setLidu("DAILY");
		form.setAppVersion("all");
		form.setModel("all");
		form.setUsertype("all");

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		dto.setUserType(form.getUsertype());
		dto.setAppVersion(form.getAppVersion());
		dto.setModel(form.getModel());
		detailuserclientList = service.getDetailuserclientList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailuserclientList);
		
		LogUtil.log(dto, "细分用户客户端");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String userType = form.getUsertype();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setUserType(userType);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		detailuserclientList = service.getDetailuserclientList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailuserclientList);
		
		LogUtil.log(dto, "细分用户客户端");

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

		List<DetailuserclientEntity> list = (List<DetailuserclientEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("细分用户客户端行为统计_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "细分用户客户端行为统计" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动用户数", "下载用户数", "下载启动比",
					"下载次数", "启动次数", "启动用户人均下载次数", "下载用户人均下载次数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				DetailuserclientEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getStartUser()),
						String.valueOf(entity.getDownUser()),
						String.valueOf(entity.getDownStartRatio()),
						String.valueOf(entity.getDownCnt()),
						String.valueOf(entity.getStartCnt()),
						String.valueOf(entity.getAvgStartUserDown()),
						String.valueOf(entity.getAvgDownUserDown()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public StoreForm getForm() {
		return form;
	}

	public void setForm(StoreForm form) {
		this.form = form;
	}

	public List<DetailuserclientEntity> getDetailuserclientList() {
		return detailuserclientList;
	}

	public void setDetailuserclientList(
			List<DetailuserclientEntity> detailuserclientList) {
		this.detailuserclientList = detailuserclientList;
	}

	public StoreService getService() {
		return service;
	}

	public void setService(StoreService service) {
		this.service = service;
	}
}
