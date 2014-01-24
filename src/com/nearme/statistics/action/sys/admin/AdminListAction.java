package com.nearme.statistics.action.sys.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.AdminDTO;
import com.nearme.statistics.form.sys.AdminListForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Group;
import com.nearme.statistics.model.sys.admin.Role;
import com.nearme.statistics.service.sys.AdminService;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.oppo.security.MD5Util;

/**
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-25
 */
public class AdminListAction extends ActionSupport {
	private static final long serialVersionUID = -96726707690427135L;

	private AdminListForm formModify;
	private AdminListForm form;
	private AdminListForm addForm;
	private List<Admin> admins;
	private List<Role> roles;// 角色下拉列表
	private List<Group> groups;// 分组下拉列表
	private AdminService service;

	/**
	 * 1.列出系统中所有的账户<br>
	 * 2.提供操作菜单
	 * 
	 * @return
	 */
	public String init() {
		Admin loginer = getLoginUserInfo();

		if (null == loginer) {
			return "admins";
		}

		AdminDTO dto = new AdminDTO();
		dto.setId(loginer.getId());
		dto.setUsername(loginer.getUserName());

		admins = service.getAdmins(dto);
		roles = service.getRoleFixedUser(dto);
		groups = service.getGroupFixedUser(dto);

		return "admins";
	}

	/**
	 * 根据登陆用户名查询此用户完整信息
	 * 
	 * @return
	 */
	private Admin getLoginUserInfo() {
		// 填充操作人
		Admin admin = (Admin) ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.SESSION_ADMIN_KEY);

		if (null != admin) {
			// 根据当前登录的用户名进行查询
			String operuser = admin.getUserName();
			AdminDTO dto = new AdminDTO();
			dto.setUsername(operuser);
			return service.getAdminFixedUsername(dto);
		} else {
			return null;
		}
	}

	/**
	 * 进入修改密码页面
	 * 
	 * @return
	 */
	public String modifyPasswd() {
		int adminID = NumericUtil.tryParse(ServletActionContext.getRequest()
				.getParameter("id"), -1);
		String adminName = ServletActionContext.getRequest().getParameter(
				"userName");

		formModify = new AdminListForm();
		formModify.setId(adminID);
		formModify.setUserName(adminName);

		return "modifyPasswd";
	}

	/**
	 * 处理提交更改密码请求
	 * 
	 * @return
	 */
	public String doModifyPasswd() {
		String newPasswd = formModify.getAdminNewPasswd();
		int id = formModify.getId();
		if (!StringUtil.checkNewPasswd(newPasswd)) {
			this.addActionError("新密码不符合要求");
		}
		Admin admin = new Admin();
		newPasswd = MD5Util.md5(newPasswd, "UTF-8");
		admin.setUserPasswd(newPasswd);
		int result = service.updateAdminPasswd(id, newPasswd);
		if (result > 0) {
			this.addActionMessage("密码更改成功");
		} else {
			this.addActionMessage("密码更改失败");
		}
		return "admin_init";
	}

	/**
	 * 进入修改用户信息页面
	 * 
	 * @return
	 */
	public String adminModify() {
		int id = NumericUtil.tryParse(ServletActionContext.getRequest()
				.getParameter("id"), -1);
		String userName = ServletActionContext.getRequest().getParameter(
				"userName");
		String realName = ServletActionContext.getRequest().getParameter(
				"realName");
		String email = ServletActionContext.getRequest().getParameter("email");
		String mobileNumber = ServletActionContext.getRequest().getParameter(
				"mobileNumber");
		int ipLimit = NumericUtil.tryParse(ServletActionContext.getRequest()
				.getParameter("ipLimit"), -1);
		String roleIDs = ServletActionContext.getRequest().getParameter(
				"roleIDs");
		String groupIDs = ServletActionContext.getRequest().getParameter(
				"groupIDs");

		String[] roleIDlist = roleIDs.split(",");
		String[] groupIDlist = groupIDs.split(",");

		form = new AdminListForm();
		form.setId(id);
		form.setUserName(userName);
		form.setRealName(realName);
		form.setEmail(email);
		form.setMobileNumber(mobileNumber);
		form.setIpLimit(ipLimit);
		form.setRoleIDs(roleIDs);
		form.setGroupIDs(groupIDs);
		form.setRoleIDlist(roleIDlist);
		form.setGroupIDlist(groupIDlist);

		// 查询登录用户能管理的分组和角色
		Admin loginer = getLoginUserInfo();
		if (null == loginer) {
			return "modify";
		}
		AdminDTO dto = new AdminDTO();
		dto.setId(loginer.getId());
		dto.setUsername(loginer.getUserName());
		roles = service.getRoleFixedUser(dto);
		groups = service.getGroupFixedUser(dto);

		return "modify";
	}

	/**
	 * 处理提交更改用户信息请求
	 * 
	 * @return
	 */
	public String doModify() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int adminID = form.getId();
		if (adminID == -1) {
			return Action.ERROR;
		}
		// 获取页面提交参数
		String realName = form.getRealName();
		String email = form.getEmail();
		String mobileNumber = form.getMobileNumber();
		int ipLimit = form.getIpLimit();
		String[] roleIDs = form.getRoleIDs() == null ? null : form.getRoleIDs().split(",");
		String[] groupIDs = form.getGroupIDs() == null ? null :form.getGroupIDs().split(",");

		// 1.更新用户信息
		Admin admin = new Admin();
		admin.setId(adminID);
		admin.setRealName(realName);
		admin.setEmail(email);
		admin.setMobileNumber(mobileNumber);
		admin.setIpLimit(ipLimit);
		admin.setUpdateBy(((Admin) request.getSession().getAttribute(
				Constants.SESSION_ADMIN_KEY)).getId());
		int result = service.updateAdminInfo(admin);

		if (result > 0) {
			// 2.删除原来用户存在的角色
			service.deleteAdminRole(adminID);
			// 3.删除原来用户存在的分组
			service.deleteAdminGroup(adminID);

			// 4.将用户加入新角色(需要做批量插入操作)
			List<Integer> list = new ArrayList<Integer>();
			if (null != roleIDs) {
				for (String roleID : roleIDs) {
					list.add(NumericUtil.tryParse(roleID.trim(), 0));
				}
			}
			service.updateAdminRole(adminID, list);

			// 5.将用户加入新分组(需要做批量插入操作)
			list = new ArrayList<Integer>();
			if (null != groupIDs) {
				for (String groupID : groupIDs) {
					list.add(NumericUtil.tryParse(groupID.trim(), 0));
				}
			}
			service.updateAdminGroup(adminID, list);
		}
		return "admin_init";
	}

	/**
	 * function:删除用户
	 * 
	 * @return
	 */
	public String deleteAdmin() {
		int adminID = NumericUtil.tryParse(ServletActionContext.getRequest()
				.getParameter("id"), -1);
		if (adminID > 0) {
			int result = service.deleteAdmin(adminID);
			if (result > 0) {
				this.addActionMessage("管理员删除成功");
			} else {
				this.addActionError("管理员删除失败");
			}
		} else {
			this.addActionError("参数错误");
		}
		return "admin_init";
	}

	/**
	 * 进入添加用户页面
	 * 
	 * @return
	 */
	public String addAdmin() {
		// 查询此登录用户能管理的分组和角色
		Admin loginer = getLoginUserInfo();
		if (null == loginer) {
			return "add";
		}
		AdminDTO dto = new AdminDTO();
		dto.setId(loginer.getId());
		dto.setUsername(loginer.getUserName());
		roles = service.getRoleFixedUser(dto);
		groups = service.getGroupFixedUser(dto);

		return "add";
	}

	/**
	 * 处理增加管理员请求
	 */
	public String doAddAdmin() {
		String adminName = addForm.getUserName();
		String adminPwd = addForm.getUserPasswd();
		String realName = addForm.getRealName();
		String email = addForm.getEmail();
		String mobile = addForm.getMobileNumber();
		int ipLimit = addForm.getIpLimit();
		String[] roleIDs = addForm.getRoleIDs() == null ? null : addForm
				.getRoleIDs().split(",");
		String[] groupIDs = addForm.getGroupIDs() == null ? null : addForm
				.getGroupIDs().split(",");

		if (StringUtil.isNullOrEmpty(adminName)) {
			this.addActionError("管理员登录名必须填写");
		} else if ("admin".equalsIgnoreCase(adminName)
				|| adminName.toLowerCase().contains("admin")) {
			this.addActionError("不能添加'admin'管理员");
		} else if (!StringUtil.checkNewPasswd(adminPwd)) {
			this.addActionError("密码不符合要求");
		} else if (service.checkAdminNameExists(adminName) > 0) {
			this.addActionError("管理员已经存在");
		} else {
			// 添加用户
			Admin admin = new Admin();
			admin.setUserName(adminName);
			admin.setUserPasswd(MD5Util.md5(adminPwd, "UTF-8"));
			admin.setRealName(realName);
			admin.setEmail(email);
			admin.setMobileNumber(mobile);
			admin.setIpLimit(ipLimit);
			if (ServletActionContext.getRequest().getSession().getAttribute(
					Constants.SESSION_ADMIN_KEY) != null) {
				admin
						.setUpdateBy(((Admin) ServletActionContext.getRequest()
								.getSession().getAttribute(
										Constants.SESSION_ADMIN_KEY)).getId());
			}
			int result = service.addAdmin(admin);
			if (result > 0) {
				// 将用户加入角色
				List<Integer> list = new ArrayList<Integer>();
				if (null != roleIDs) {
					for (String roleID : roleIDs) {
						list.add(NumericUtil.tryParse(roleID.trim(), 0));
					}
				}
				int adminID = service.checkAdminNameExists(adminName);
				service.updateAdminRole(adminID, list);

				// 将用户加入分组
				list = new ArrayList<Integer>();
				if (null != groupIDs) {
					for (String groupID : groupIDs) {
						list.add(NumericUtil.tryParse(groupID.trim(), 0));
					}
				}
				adminID = service.checkAdminNameExists(adminName);
				service.updateAdminGroup(adminID, list);

				this.addActionMessage("管理员添加成功");
			} else {
				this.addActionError("管理员添加失败");
			}
		}
		return "admin_init";
	}

	public AdminListForm getFormModify() {
		return formModify;
	}

	public void setFormModify(AdminListForm formModify) {
		this.formModify = formModify;
	}

	public AdminListForm getForm() {
		return form;
	}

	public void setForm(AdminListForm form) {
		this.form = form;
	}

	public AdminListForm getAddForm() {
		return addForm;
	}

	public void setAddForm(AdminListForm addForm) {
		this.addForm = addForm;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}
}
