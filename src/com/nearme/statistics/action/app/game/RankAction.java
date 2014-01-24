package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.RankEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 排行
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-8
 */
public class RankAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm form;// top30
	private GameForm formposition;// 位置

	private List<RankEntity> ranktop30List;
	private List<RankEntity> rankpositionList;

	private GameService service;

	/**
	 * 初始化
	 * @return
	 */
	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		initTop30();
		initPosition();

		return Action.SUCCESS;
	}

	private void initTop30() {
		String startDate = DateUtil.getDateOfXdaysAgo(10);
		String endDate = DateUtil.getToday();
		String lidu = "DAILY";
		String ranktype = "HOT_RANK";// 最热排行

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu(lidu);
		form.setRanktype(ranktype);

		GameDTO dto = new GameDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(lidu);
		dto.setRanktype(ranktype);
		ranktop30List = service.getRanktop30List(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, ranktop30List);
		
		LogUtil.log(dto, "排行");
	}

	private void initPosition() {
		String startDate = DateUtil.getDateOfXdaysAgo(10);
		String endDate = DateUtil.getToday();
		String lidu = "DAILY";
		String ranktype = "HOT_RANK";// 最热排行
		int position = -1;// 位置“总”

		formposition = new GameForm();
		formposition.setSystemID(form.getSystemID());
		formposition.setStartTime(startDate);
		formposition.setEndTime(endDate);
		formposition.setSystemID(form.getSystemID());
		formposition.setLidu(lidu);
		formposition.setRanktype(ranktype);
		formposition.setPosition(position);

		GameDTO dto = new GameDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(lidu);
		dto.setRanktype(ranktype);
		dto.setPosition(position);
		rankpositionList = service.getRankpositionList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, rankpositionList);
		
		LogUtil.log(dto, "排行");
	}

	/**
	 * 查询top30
	 * @return
	 */
	public String querytop30() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String ranktype = form.getRanktype();

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
		dto.setRanktype(ranktype);
		ranktop30List = service.getRanktop30List(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, ranktop30List);
		
		LogUtil.log(dto, "排行");

		return "rank_top30";
	}

	/**
	 * 查询位置信息
	 * @return
	 */
	public String queryposition() {
		Date startDate = formposition.getStartDate();
		Date endDate = formposition.getEndDate();
		int systemID = formposition.getSystemIDValue();
		String lidu = formposition.getLidu();
		String network = formposition.getNetwork();
		String model = formposition.getModel();
		String ranktype = formposition.getRanktype();
		int position = formposition.getPosition();

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
		dto.setRanktype(ranktype);
		dto.setPosition(position);
		rankpositionList = service.getRankpositionList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, rankpositionList);
		
		LogUtil.log(dto, "排行");

		return "rank_position";
	}

	@SuppressWarnings("unchecked")
	public String exporttop30() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<RankEntity> list = (List<RankEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("排行top30_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "排行top30" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "位置", "直接下载", "详情浏览", "详情下载" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				RankEntity entity = list.get(i);
				eu.writeLine(new String[] {
						String.valueOf(entity.getPosition()),
						String.valueOf(entity.getDirectdown()),
						String.valueOf(entity.getDetailbrowse()),
						String.valueOf(entity.getDetaildown()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String exportposition() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<RankEntity> list = (List<RankEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("排行位置详情_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "排行位置详情" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "直接下载", "详情浏览", "详情下载" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				RankEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getDirectdown()),
						String.valueOf(entity.getDetailbrowse()),
						String.valueOf(entity.getDetaildown()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public GameForm getForm() {
		return form;
	}

	public void setForm(GameForm form) {
		this.form = form;
	}

	public GameForm getFormposition() {
		return formposition;
	}

	public void setFormposition(GameForm formposition) {
		this.formposition = formposition;
	}

	public List<RankEntity> getRanktop30List() {
		return ranktop30List;
	}

	public void setRanktop30List(List<RankEntity> ranktop30List) {
		this.ranktop30List = ranktop30List;
	}

	public List<RankEntity> getRankpositionList() {
		return rankpositionList;
	}

	public void setRankpositionList(List<RankEntity> rankpositionList) {
		this.rankpositionList = rankpositionList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
