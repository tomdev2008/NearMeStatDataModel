package com.nearme.statistics.action.app.commonsetting;

import java.util.List;

import com.nearme.statistics.dto.app.common.UseractionDTO;
import com.nearme.statistics.form.app.common.UseractionForm;
import com.nearme.statistics.model.commonsetting.UseractionEntity;
import com.nearme.statistics.service.app.common.UseractionService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户行为编码
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class UseractioncodeAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private UseractionForm form;//添加分组form
	private UseractionForm groupform;//分组详情form
	private UseractionForm actionform;//行为form
	
	private List<UseractionEntity> usergroupList;// 分组结果集
	private List<UseractionEntity> useractionList;// 分组行为结果集
	private UseractionService service;

	/**
	 * 查询分组信息
	 * @return
	 */
	public String init() {
		// 检查action传递过来的systemID合法性r
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		groupform = new UseractionForm();
		actionform = new UseractionForm();
		
		form.setSystemID(form.getSystemID());

		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(form.getSystemIDValue());
		usergroupList = service.getUsergroupList(dto);
		
		LogUtil.log(dto, "用户行为编码-查询分组");

		return Action.SUCCESS;
	}

	/**
	 * 查询行为信息
	 * 
	 * @return
	 */
	public String queryactioncode() {
		int systemID = form.getSystemIDValue();
		String groupcode = form.getGroupcode();

		if (systemID == 0) {
			return Action.SUCCESS;
		}

		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(systemID);
		dto.setGroupcode(groupcode);
		useractionList = service.getUseractionList(dto);
		
		LogUtil.log(dto, "用户行为编码-查询行为");

		return "setting_useractioncode_detail";
	}
	
	/**
	 * 更新分组信息
	 * @return
	 */
	public String updategroupinfo(){
		int systemID = form.getSystemIDValue();
		String groupcode = groupform.getGroupcode();
		String groupname = groupform.getGroupname();

		if (systemID == 0) {
			return Action.SUCCESS;
		}

		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(systemID);
		dto.setGroupcode(groupcode);
		dto.setGroupname(groupname);
		service.updateGroupcode(dto);
		
		LogUtil.log(dto, "用户行为编码-更新分组");
		
		return Action.SUCCESS;
	}
	
	/**
	 * 删除分组信息
	 * @return
	 */
	public String deletegroupinfo(){
		int systemID = form.getSystemIDValue();
		String groupcode = form.getGroupcode();

		if (systemID == 0) {
			return Action.SUCCESS;
		}

		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(systemID);
		dto.setGroupcode(groupcode);
		service.deleteGroupcode(dto);
		
		LogUtil.log(dto, "用户行为编码-删除分组");
		
		return Action.SUCCESS;
	}
	
	/**
	 * 删除行为信息
	 * @return
	 */
	public String deleteactioninfo(){
		int systemID = form.getSystemIDValue();
		String groupcode = actionform.getGroupcode();
		String actioncode = actionform.getActioncode();

		if (systemID == 0) {
			return Action.SUCCESS;
		}

		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(systemID);
		dto.setGroupcode(groupcode);
		dto.setActioncode(actioncode);
		service.deleteActioncode(dto);
		
		LogUtil.log(dto, "用户行为编码-删除行为");
		
		return Action.SUCCESS;
	}
	
	/**
	 * 添加分组信息
	 * @return
	 */
	public String addgroupinfo(){
		int systemID = form.getSystemIDValue();
		String groupcode = form.getGroupcode();
		String groupname = form.getGroupname();
		
		if (systemID == 0) {
			return Action.SUCCESS;
		}
		
		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(systemID);
		dto.setGroupname(groupname);
		dto.setGroupcode(groupcode);
		service.insertGroupcode(dto);
		
		LogUtil.log(dto, "用户行为编码-添加分组");
		
		return Action.SUCCESS;
	}
	
	/**
	 * 操作行为信息
	 * @return
	 */
	public String operationactioninfo(){
		int systemID = form.getSystemIDValue();
		String groupcode = actionform.getGroupcode();
		String actioncode = actionform.getActioncode();
		String actionname = actionform.getActionname();
		
		String operation = actionform.getOperation();//获取具体执行的操作
		
		if (systemID == 0) {
			return Action.SUCCESS;
		}
		
		UseractionDTO dto = new UseractionDTO();
		dto.setSystemID(systemID);
		dto.setGroupcode(groupcode);
		dto.setActionname(actionname);
		dto.setActioncode(actioncode);
		
		if("add".equalsIgnoreCase(operation)){
			service.insertActioncode(dto);
		} else if ("update".equalsIgnoreCase(operation)){
			service.updateActioncode(dto);
		} else {//默认执行添加操作
			service.insertActioncode(dto);
		}
		
		LogUtil.log(dto, "用户行为编码-操作行为信息");
		
		return Action.SUCCESS;
	}

	public UseractionForm getForm() {
		return form;
	}

	public void setForm(UseractionForm form) {
		this.form = form;
	}

	public List<UseractionEntity> getUsergroupList() {
		return usergroupList;
	}

	public void setUsergroupList(List<UseractionEntity> usergroupList) {
		this.usergroupList = usergroupList;
	}

	public List<UseractionEntity> getUseractionList() {
		return useractionList;
	}

	public void setUseractionList(List<UseractionEntity> useractionList) {
		this.useractionList = useractionList;
	}

	public UseractionService getService() {
		return service;
	}

	public void setService(UseractionService service) {
		this.service = service;
	}

	public UseractionForm getGroupform() {
		return groupform;
	}

	public void setGroupform(UseractionForm groupform) {
		this.groupform = groupform;
	}

	public UseractionForm getActionform() {
		return actionform;
	}

	public void setActionform(UseractionForm actionform) {
		this.actionform = actionform;
	}
}
