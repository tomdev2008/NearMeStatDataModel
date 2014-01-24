package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.GameFeeEntity;
import com.nearme.statistics.model.game.GameFeeUserEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 付费分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class FeeAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "付费分析";
	private GameForm formgamename;
	private GameForm form;
	private List<GameFeeEntity> feeList;
	private List<GameFeeUserEntity> feeUserList;
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

		// GameDTO dto = new GameDTO();
		// dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		// dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		// dto.setSystemID(form.getSystemIDValue());
		// dto.setLidu(form.getLidu());
		// feeList = service.getFeeList(dto);
		// ServletActionContext.getRequest().getSession().setAttribute(
		// Constants.NEARME_SESSION_REPROT_DATA, feeList);

		// LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String qudao = form.getQudao();
		String productid = form.getProductid();

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
		dto.setQudao(qudao);
		dto.setProductid(productid);
		feeList = service.getFeeList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, feeList);

		LogUtil.log(dto, TAG);

		return "query";
	}
	
	
	public String getuser() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String qudao = form.getQudao();
		String productid = form.getProductid();
		String statdate = form.getStatdate();
		String type = form.getType();
		int records = form.getRecords();

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
		dto.setQudao(qudao);
		dto.setProductid(productid);
		dto.setStatdate(statdate);
		dto.setType(type);
		dto.setRecords(records);
		
		feeUserList = service.getFeeUserList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, feeList);

		LogUtil.log(dto, TAG);

		return "fee_user";
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<GameFeeEntity> list = (List<GameFeeEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "新增鲸鱼", "总鲸鱼用户",
					"鲸鱼流失7", "鲸鱼付费流失7", "鲸鱼付费流失30"});

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				GameFeeEntity entity = list.get(i);
				eu.writeLine(new String[] {
						entity.getStatDate(),
						String.valueOf(entity.getNewWhale()),
						String.valueOf(entity.getTotalWhale()),
						String.valueOf(entity.getWhaleLost7()),
						String.valueOf(entity.getWhalePayLost7()),
						String.valueOf(entity.getWhalePayLost30()) });
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

	public List<GameFeeEntity> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<GameFeeEntity> feeList) {
		this.feeList = feeList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
	public List<GameFeeUserEntity> getFeeUserList() {
		return feeUserList;
	}
	public void setFeeUserList(List<GameFeeUserEntity> feeUserList) {
		this.feeUserList = feeUserList;
	}
}
