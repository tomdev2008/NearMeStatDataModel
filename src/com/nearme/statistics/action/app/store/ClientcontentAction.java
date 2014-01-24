package com.nearme.statistics.action.app.store;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.form.app.store.StoreForm;
import com.nearme.statistics.model.store.ClientcontentEntity;
import com.nearme.statistics.service.app.store.StoreService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 客户端内容分布
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class ClientcontentAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private StoreForm form;
	private List<ClientcontentEntity> clientcontentList;
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

		StoreDTO dto = new StoreDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		clientcontentList = service.getClientcontentList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, clientcontentList);
		
		LogUtil.log(dto, "客户端内容分布");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
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
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		clientcontentList = service.getClientcontentList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, clientcontentList);
		
		LogUtil.log(dto, "客户端内容分布");

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

		List<ClientcontentEntity> list = (List<ClientcontentEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("客户端内容分布_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "客户端内容分布" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "应用", "应用更新量", "应用有效流量",
					"游戏", "游戏更新量", "游戏有效流量", "主题", "新主题","壁纸","铃声","字体","电子书","彩铃" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				ClientcontentEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getApp()),
						String.valueOf(entity.getAppupdate()),
						String.valueOf(entity.getAppeffect()),
						String.valueOf(entity.getGame()),
						String.valueOf(entity.getGameupdate()),
						String.valueOf(entity.getGameeffect()),
						String.valueOf(entity.getTheme()),
						String.valueOf(entity.getThemenew()),
						String.valueOf(entity.getWallpaper()),
						String.valueOf(entity.getRing()),
						String.valueOf(entity.getFont()),
						String.valueOf(entity.getEbook()),
						String.valueOf(entity.getColorring()) });
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
	
	public List<ClientcontentEntity> getClientcontentList() {
		return clientcontentList;
	}

	public void setClientcontentList(List<ClientcontentEntity> clientcontentList) {
		this.clientcontentList = clientcontentList;
	}

	public StoreService getService() {
		return service;
	}

	public void setService(StoreService service) {
		this.service = service;
	}
}
