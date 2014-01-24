package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.GameRemainEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 留存分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class RemainAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "留存分析";
	private GameForm formgamename;
	private GameForm form;
	private List<GameRemainEntity> remainList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String lidu = "DAILY";

		formgamename = new GameForm();
		formgamename.setSystemID(form.getSystemID());

		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());
		form.setLidu(lidu);

		// GameDTO dto = new GameDTO();
		// dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		// dto.setSystemID(form.getSystemIDValue());
		// dto.setLidu(lidu);
		// remainList = service.getRemainList(dto);
		// ServletActionContext.getRequest().getSession().setAttribute(
		// Constants.NEARME_SESSION_REPROT_DATA, remainList);
		//
		// LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String model = form.getModel();
		String qudao = form.getQudao();
		String productid = form.getProductid();

		if (systemID == 0) {
			return "query";
		}
		if (startDate == null) {
			return "query";
		}
		if (productid == null || "".equals(productid)) {
			return "query";
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setModel(model);
		dto.setQudao(qudao);
		dto.setProductid(productid);
		remainList = service.getRemainList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, remainList);

		LogUtil.log(dto, TAG);

		return "query";
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<GameRemainEntity> list = (List<GameRemainEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "账号留存", "账号留存率", "IMEI留存", "IMEI留存率" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (GameRemainEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getRemainDate()),
						String.valueOf(entity.getRemain()),
						String.valueOf(entity.getRemainratio()),
						String.valueOf(entity.getImeiremain()),
						String.valueOf(entity.getImeiremainratio()) });
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

	public List<GameRemainEntity> getRemainList() {
		return remainList;
	}

	public void setRemainList(List<GameRemainEntity> remainList) {
		this.remainList = remainList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
