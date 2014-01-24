package com.nearme.statistics.action.sys.personInfo;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.form.sys.AdminListForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.service.sys.PersonInfoService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户信息更改Action
 *
 * @author 段锦涛
 * @version 1.0
 * @since 1.0, 2012-8-21
 */
public class UserInfoModifyAction extends ActionSupport {

	private static final long serialVersionUID = -4319677560378186142L;
	private PersonInfoService service;
	private AdminListForm form;
	private String errMsg;

	public void setService(PersonInfoService service) {
		this.service = service;
	}

	public String init() {
		Admin admin = (Admin) ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.SESSION_ADMIN_KEY);

		int id = admin.getId();
		String userName = admin.getUserName();
		String realName = admin.getRealName();
		String email = admin.getEmail();
		String mobileNumber = admin.getMobileNumber();

		form = new AdminListForm();
		form.setId(id);
		form.setUserName(userName);
		form.setRealName(realName);
		form.setEmail(email);
		form.setMobileNumber(mobileNumber);

		return Action.SUCCESS;

	}

	public String update() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int adminID = form.getId();
		if (adminID == -1) {
			return Action.ERROR;
		}
		// 获取页面提交参数
		String realName = form.getRealName();
		String email = form.getEmail();
		String mobileNumber = form.getMobileNumber();

		Admin admin = new Admin();
		admin.setId(adminID);
		admin.setRealName(realName);
		admin.setEmail(email);
		admin.setMobileNumber(mobileNumber);

		admin.setUpdateBy(((Admin) request.getSession().getAttribute(
				Constants.SESSION_ADMIN_KEY)).getId());

		int result = service.updateUserInfo(admin);
		if (result > 0) {
			this.addActionMessage("个人信息修改成功");
			errMsg = "个人信息修改成功";
			return Action.SUCCESS;
		} else {
			this.addActionMessage("个人信息修改失败");
			errMsg = "个人信息修改失败";
			return Action.ERROR;
		}
	}

	public AdminListForm getForm() {
		return form;
	}

	public void setForm(AdminListForm form) {
		this.form = form;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
