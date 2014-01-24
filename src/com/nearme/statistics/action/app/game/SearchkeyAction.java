package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.SearchkeyEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 搜索关键字
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-8
 */
public class SearchkeyAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm form;

	private List<SearchkeyEntity> searchkeyList;

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

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");

		GameDTO dto = new GameDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		searchkeyList = service.getSearchkeyList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, searchkeyList);
		
		LogUtil.log(dto, "搜索关键字");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();

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
		searchkeyList = service.getSearchkeyList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, searchkeyList);
		
		LogUtil.log(dto, "搜索关键字");

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<SearchkeyEntity> list = (List<SearchkeyEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("搜索关键字_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "搜索关键字" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "总", "0", "1", "2", "3", "4",
					"5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
					"15", "16", "17", "18", "19" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				SearchkeyEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getPositiontotal()),
						String.valueOf(entity.getPosition0()),
						String.valueOf(entity.getPosition1()),
						String.valueOf(entity.getPosition2()),
						String.valueOf(entity.getPosition3()),
						String.valueOf(entity.getPosition4()),
						String.valueOf(entity.getPosition5()),
						String.valueOf(entity.getPosition6()),
						String.valueOf(entity.getPosition7()),
						String.valueOf(entity.getPosition8()),
						String.valueOf(entity.getPosition9()),
						String.valueOf(entity.getPosition10()),
						String.valueOf(entity.getPosition11()),
						String.valueOf(entity.getPosition12()),
						String.valueOf(entity.getPosition13()),
						String.valueOf(entity.getPosition14()),
						String.valueOf(entity.getPosition15()),
						String.valueOf(entity.getPosition16()),
						String.valueOf(entity.getPosition17()),
						String.valueOf(entity.getPosition18()),
						String.valueOf(entity.getPosition19()) });
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

	public List<SearchkeyEntity> getSearchkeyList() {
		return searchkeyList;
	}

	public void setSearchkeyList(List<SearchkeyEntity> searchkeyList) {
		this.searchkeyList = searchkeyList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
