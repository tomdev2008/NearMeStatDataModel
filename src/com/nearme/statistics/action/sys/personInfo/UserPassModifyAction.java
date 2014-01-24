package com.nearme.statistics.action.sys.personInfo;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.form.sys.AdminListForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.service.sys.PersonInfoService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.oppo.security.MD5Util;

/**
 * 用户密码更改Action
 *
 * @author 段锦涛
 * @version 1.0
 * @since 1.0, 2012-8-21
 */
public class UserPassModifyAction extends ActionSupport {

	private static final long serialVersionUID = 5562268875978935120L;
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

		form = new AdminListForm();
		form.setId(id);
		form.setUserName(userName);

		form.setAdminNewPasswd(null);
		form.setAdminNewPasswd2(null);

		return Action.SUCCESS;

	}

	public String update() {
		Admin admin = (Admin) ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.SESSION_ADMIN_KEY);

		int id = admin.getId();

		// 获取页面提交参数
		String newPasswd = form.getAdminNewPasswd();
		String newPasswd2 = form.getAdminNewPasswd2();
		if (newPasswd == null || newPasswd2 == null) {
			errMsg = "密码不能为空，请重新输入";
			return Action.SUCCESS;
		}
		if (!newPasswd2.equals(newPasswd)) {
			errMsg = "两次密码输入不一致，请重新输入";
			return Action.SUCCESS;
		}
		int adminID = admin.getId();
		Admin admin2 = new Admin();
		newPasswd = MD5Util.md5(newPasswd, "UTF-8");
		admin2.setUserPasswd(newPasswd);
		admin2.setId(adminID);
		admin2.setUpdateBy(id);

		int result = service.updateUserPasswd(admin2);
		if (result > 0) {

			errMsg = "密码修改成功";
			return Action.SUCCESS;
		} else {
			this.addActionMessage("密码修改失败");
			errMsg = "密码修改失败";
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
