package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.UseractionDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.UactionEntity;
import com.nearme.statistics.model.commonsetting.UseractionEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.service.app.common.UseractionService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户行为分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-15
 */
public class UseractionAction extends ActionSupport {
	private static final long serialVersionUID = -7747673878091973779L;

	private BaseForm form;
	private List<UseractionEntity> selectactionList;// 菜单选项
	private List<UactionEntity> uactionList;// 用户行为查询结果集
	private CommonService service;
	private UseractionService actionService;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		// 界面行为select集
		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(systemID);
		selectactionList = actionService.getUseractionList(dto);

		// 设置界面
		String startDate = DateUtil.getDateOfXdaysAgo(15);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		String groupcode = form.getGroupcode();
		String actioncode = form.getActioncode();
		if (null == groupcode || "".equals(groupcode) || null == actioncode
				|| "".equals(actioncode)) {
			if(null !=  selectactionList && selectactionList.size() > 0){
				UseractionEntity def = selectactionList.get(0);
				groupcode = def.getGroupcode();
				actioncode = def.getActioncode();
				form.setGroupcode(groupcode);
				form.setActioncode(groupcode);
			}
		}

		// 查询
		BaseDTO dto2 = new BaseDTO();
		dto2.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto2.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto2.setSystemID(form.getSystemIDValue());
		dto2.setGroupcode(groupcode);
		dto2.setActioncode(actioncode);

		uactionList = service.getUactionList(dto2);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, uactionList);
		
		LogUtil.log(dto2, "用户行为分析");//log记录查询

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String groupcode = form.getGroupcode();
		String actioncode = form.getActioncode();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}
		
		// 界面行为select集
		UseractionDTO dto2 = new UseractionDTO();
		dto2.setSystemID(systemID);
		selectactionList = actionService.getUseractionList(dto2);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setGroupcode(groupcode);
		dto.setActioncode(actioncode);
		uactionList = service.getUactionList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, uactionList);
		
		LogUtil.log(dto, "用户行为分析");//log记录查询

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

		List<UactionEntity> list = (List<UactionEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("用户行为分析_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "用户行为分析" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动账号数", "启动手机数", "启动次数",
					"事件消息账号数", "事件消息手机数", "事件消息次数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				UactionEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getStartuser()),
						String.valueOf(entity.getStartimei()),
						String.valueOf(entity.getStartcnt()),
						String.valueOf(entity.getEventuser()),
						String.valueOf(entity.getEventimei()),
						String.valueOf(entity.getEventcnt()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public List<UseractionEntity> getSelectactionList() {
		return selectactionList;
	}

	public void setSelectactionList(List<UseractionEntity> selectactionList) {
		this.selectactionList = selectactionList;
	}

	public List<UactionEntity> getUactionList() {
		return uactionList;
	}

	public void setUactionList(List<UactionEntity> uactionList) {
		this.uactionList = uactionList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

	public UseractionService getActionService() {
		return actionService;
	}

	public void setActionService(UseractionService actionService) {
		this.actionService = actionService;
	}
}
