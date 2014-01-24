package com.nearme.statistics.action.sys.admin;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.AdminDTO;
import com.nearme.statistics.model.sys.MenuItem;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Role;
import com.nearme.statistics.service.sys.AdminService;
import com.nearme.statistics.util.NumericUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class GrantPermissionAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// service
	private AdminService service;
	// request input
	private String roleID;
	// template show variables
	private List<Role> roles;// 角色选项
	private List<String> rolePerms;// 角色权限
	private List<MenuItem> menuList;// 显示的菜单

	public String execute() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			int selectedRoleID = -1;
			selectedRoleID = NumericUtil.tryParse(roleID, -1);
			if (request.getParameter("btnSubmit") != null) {
				// 点击保存触发提交
				List<String> perms = getSelectedPerms(request);
				Admin operateAdmin = (Admin) request.getSession().getAttribute(
						Constants.SESSION_ADMIN_KEY);
				service.deletePermission(selectedRoleID);
				service.grantPermission(selectedRoleID, perms, operateAdmin
						.getId());
			} else {
				// 更改下拉框触发提交
			}

			// 填充操作人
			Admin admin = (Admin) ServletActionContext.getRequest()
					.getSession().getAttribute(Constants.SESSION_ADMIN_KEY);
			String operuser = admin.getUserName();
			// 根据当前登录的用户名进行查询
			AdminDTO dto = new AdminDTO();
			dto.setUsername(operuser);
			roles = service.getRoleFixedUser(dto);
			if (selectedRoleID == -1 && roles != null && roles.size() > 0) {
				for (Role r : roles) {
					if (!"超级管理员".equals(r.getRoleName())) {
						selectedRoleID = r.getId();
						break;
					}
				}
			}

			// 根据
			if (null != roles) {
				for (Role r : roles) {
					if (r.getId() == selectedRoleID) {
						String systemID = r.getSystemID();
						String menuKey = service.getMenuKey(systemID);
						if (null != menuKey) {
							menuList = service.listMenus(menuKey);
						}
						break;
					}
					;
				}
			}
			rolePerms = service.getRolePermissions(selectedRoleID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private List<String> getSelectedPerms(HttpServletRequest request) {
		List<String> result = new ArrayList<String>();

		Enumeration ee = request.getParameterNames();
		while (ee.hasMoreElements()) {
			String k = ee.nextElement().toString();
			if (k.startsWith("PERM_")) {
				result.add(k.substring(5));
			}
		}

		return result;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRolePerms(List<String> rolePerms) {
		this.rolePerms = rolePerms;
	}

	public List<String> getRolePerms() {
		return rolePerms;
	}

	public List<MenuItem> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuItem> menuList) {
		this.menuList = menuList;
	}
}
