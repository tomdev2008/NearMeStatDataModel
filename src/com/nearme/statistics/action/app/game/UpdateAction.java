package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.UpdateEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 游戏更新
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-7
 */
public class UpdateAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm formgamename;
	private GameForm form;

	private List<UpdateEntity> updateList;

	private GameService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(10);
		String endDate = DateUtil.getToday();

		formgamename = new GameForm();
		formgamename.setSystemID(form.getSystemID());

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");

		GameDTO dto = new GameDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		updateList = service.getUpdateList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, updateList);

		LogUtil.log(dto, "游戏更新");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();
		String productIDs = form.getProductIDs();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		dto.setProductIDs(productIDs);
		updateList = service.getUpdateList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, updateList);

		LogUtil.log(dto, "游戏更新");

		return "update";
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<UpdateEntity> list = (List<UpdateEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("游戏更新_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "游戏更新" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "更新", "首页更新", "排行榜更新", "分类更新",
					"专题更新", "搜索更新", "我的更新", "详情更新", "活动中心更新" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				UpdateEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getUpdatecnt()),
						String.valueOf(entity.getHomepage()),
						String.valueOf(entity.getRank()),
						String.valueOf(entity.getSort()),
						String.valueOf(entity.getSpecialtopic()),
						String.valueOf(entity.getSearch()),
						String.valueOf(entity.getMyupdate()),
						String.valueOf(entity.getDetail()),
						String.valueOf(entity.getActivecenter()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public GameForm getFormgamename() {
		return formgamename;
	}

	public void setFormgamename(GameForm formgamename) {
		this.formgamename = formgamename;
	}

	public GameForm getForm() {
		return form;
	}

	public void setForm(GameForm form) {
		this.form = form;
	}

	public List<UpdateEntity> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<UpdateEntity> updateList) {
		this.updateList = updateList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
