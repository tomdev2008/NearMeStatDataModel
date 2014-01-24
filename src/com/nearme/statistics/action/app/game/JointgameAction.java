package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.JointgameEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 联运游戏数据
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-11
 */
public class JointgameAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm formgamename;
	private GameForm form;

	private List<JointgameEntity> jointgameList;

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

		/* GameDTO dto = new GameDTO();
		 dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		 dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		 dto.setSystemID(form.getSystemIDValue());
		 jointgameList = service.getJointgameList(dto);
		 ServletActionContext.getRequest().getSession().setAttribute(
		 Constants.NEARME_SESSION_REPROT_DATA, jointgameList);
		 LogUtil.log(dto, "联运游戏数据");*/

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String model = form.getModel();
		String qudao = form.getQudao();
		String productid = form.getProductid();

		if (systemID == 0) {
			return "query";
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "query";
		}
		if (productid == null || "".equals(productid)) {
			return "query";
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setModel(model);
		dto.setQudao(qudao);
		dto.setProductid(productid);
		jointgameList = service.getJointgameList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, jointgameList);

		LogUtil.log(dto, "联运游戏数据");

		return "query";
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<JointgameEntity> list = (List<JointgameEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("联运游戏数据_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "联运游戏数据" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "总用户", "新增激活", "新增用户", "登陆用户",
					"启动次数", "流失7", "流失30", "次日留存", "7日留存", "付费人数", "付费总额",
					"付费率", "ARPU", "ARPPU" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				JointgameEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getTotaluser()),
						String.valueOf(entity.getNewimei()),
						String.valueOf(entity.getNewuser()),
						String.valueOf(entity.getLoginuser()),
						String.valueOf(entity.getStartcnt()),
						String.valueOf(entity.getLost7()),
						String.valueOf(entity.getLost30()),
						String.valueOf(entity.getRemain2()),
						String.valueOf(entity.getRemain7()),
						String.valueOf(entity.getFeeuser()),
						String.valueOf(entity.getFeetotal()),
						String.valueOf(entity.getFeeratio()),
						String.valueOf(entity.getArpu()),
						String.valueOf(entity.getArppu()) });
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

	public List<JointgameEntity> getJointgameList() {
		return jointgameList;
	}

	public void setJointgameList(List<JointgameEntity> jointgameList) {
		this.jointgameList = jointgameList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
