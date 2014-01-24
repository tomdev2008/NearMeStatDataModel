package com.nearme.statistics.action.sys.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.AdminDTO;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Role;
import com.nearme.statistics.service.sys.AdminService;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class RoleListAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService service;

	// 输入参数
	private String btnAdd;
	private String roleID;
	private String roleName;
	private String roleDesc;
	private String systemID;
	private String deleteRole;

	// 输出参数
	private String errMsg;
	private String successMsg;
	private List<Role> roles;

	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		// 添加角色
		if ("post".equalsIgnoreCase(request.getMethod()) && btnAdd != null) {
			if (roleDesc != null && roleDesc.length() > 80) {
				roleDesc = roleDesc.substring(0, 80);
			}
			if (StringUtil.isNullOrEmpty(roleName)) {
				errMsg = "角色名不能为空";
			} else if (service.checkRoleNameExists(roleName) != 0) {
				errMsg = "角色名已存在";
			} else if (StringUtil.isNullOrEmpty(systemID)) {
				errMsg = "系统ID不能为空";
			} else if (systemID != null) {
				if (NumericUtil.tryParse(systemID, 0) <= 0) {
					errMsg = "系统ID需为大于0的数字";
				} else {
					Role role = new Role();
					role.setRoleName(roleName);
					role.setRoleDesc(roleDesc);
					role.setSystemID(systemID);

					int result = service.addRole(role);
					if (result > 0) {
						successMsg = "角色添加成功";
					} else {
						errMsg = "角色添加失败";
					}
				}
			}
		}
		// 删除角色
		if (deleteRole != null && roleID != null) {
			int roleid = NumericUtil.tryParse(roleID, -1);
			if (roleid <= 0) {
				errMsg = "参数有误";
			} else {
				int result = service.deleteRole(roleid);
				if (result > 0) {
					successMsg = "角色删除成功";
				} else {
					errMsg = "角色删除失败";
				}
			}
		}

		// 填充操作人
		Admin admin = (Admin) ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.SESSION_ADMIN_KEY);
		String operuser = admin.getUserName();
		// 根据当前登录的用户名进行查询
		AdminDTO dto = new AdminDTO();
		dto.setUsername(operuser);
		roles = service.getRoleFixedUser(dto);

		return Action.SUCCESS;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getDeleteRole() {
		return deleteRole;
	}

	public void setDeleteRole(String deleteRole) {
		this.deleteRole = deleteRole;
	}

	public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setBtnAdd(String btnAdd) {
		this.btnAdd = btnAdd;
	}

	public String getBtnAdd() {
		return btnAdd;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleID() {
		return roleID;
	}

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
}
