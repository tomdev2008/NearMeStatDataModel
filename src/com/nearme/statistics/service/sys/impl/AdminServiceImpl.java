package com.nearme.statistics.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import com.nearme.statistics.dao.sys.AdminDao;
import com.nearme.statistics.dto.sys.AdminDTO;
import com.nearme.statistics.model.sys.MenuItem;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Group;
import com.nearme.statistics.model.sys.admin.IpFilter;
import com.nearme.statistics.model.sys.admin.Parameters;
import com.nearme.statistics.model.sys.admin.Role;
import com.nearme.statistics.service.sys.AdminService;
import com.nearme.statistics.util.StringUtil;

public class AdminServiceImpl implements AdminService {

	private AdminDao dao;

	public void setDao(AdminDao dao) {
		this.dao = dao;
	}

	public Admin Login(String adminName, String md5pwd) {
		return dao.Login(adminName, md5pwd);
	}

	public int addAdmin(Admin admin) {
		return dao.addAdmin(admin);
	}

	public int addRole(Role role) {
		return dao.addRole(role);
	}

	public int checkAdminNameExists(String adminName) {
		return dao.checkAdminNameExists(adminName);
	}

	public int checkRoleNameExists(String roleName) {
		return dao.checkRoleNameExists(roleName);
	}

	public int deleteAdmin(int id) {
		return dao.deleteAdmin(id);
	}

	public int deletePermission(int roleID) {
		return dao.deletePermission(roleID);
	}

	public int deleteRole(int id) {
		return dao.deleteRole(id);
	}

	public List<String> getAdminPermissions(int adminID) {
		return dao.getAdminPermissions(adminID);
	}

	public Admin getAdminFixedUsername(AdminDTO dto) {
		return dao.getAdminFixedUsername(dto);
	}

	// public List<Admin> getAdmins(AdminDTO dto) {
	// // 1.查询所有用户信息
	// List<Admin> adminList = dao.getAdminAll();
	//
	// List<Admin> result = new ArrayList<Admin>();
	// for (Admin admin : adminList) {
	// result.add(admin);
	// }
	//
	// // 2.查询指定账号id的(用户-分组)信息
	// // 3.查询指定账号id的(用户-角色)信息
	// List<Group> grouplist = dao.getGroups(dto);
	// List<Role> rolelist = dao.getRoles(dto);
	//
	// // 4.用户信息和（用户-角色）以及（用户-分组）匹配去重
	// for (Admin entity : adminList) {
	// boolean isshown = false;
	// int adminid = entity.getId();
	//
	// // 4.1匹配去重
	// List<String> groupIDs = new ArrayList<String>();
	// List<String> groupNames = new ArrayList<String>();
	// List<String> roleIDs = new ArrayList<String>();
	// List<String> systemIDs = new ArrayList<String>();
	// List<String> roleNames = new ArrayList<String>();
	// for (Group group : grouplist) {
	// int adminidgroup = group.getId();
	// int groupid = group.getGroupID();
	// String groupname = group.getGroupname();
	//
	// if (adminidgroup == adminid) {
	// if (!groupIDs.contains("" + groupid)) {
	// groupIDs.add("" + groupid);
	// groupNames.add(groupname);
	// }
	// }
	// }
	// for (Role role : rolelist) {
	// int adminidrole = role.getId();
	// int roleid = role.getRoleID();
	// String rolename = role.getRoleName();
	// String systemID = role.getSystemID();
	//
	// if (adminidrole == adminid) {
	// if (!roleIDs.contains("" + roleid)) {
	// roleIDs.add("" + roleid);
	// roleNames.add(rolename);
	// systemIDs.add(systemID);
	// }
	// }
	// }
	//
	// if (roleIDs.size() != 0 || groupIDs.size() != 0) {
	// isshown = true;
	// }
	//
	// // 4.2如果符合要求，则拼装添加分组和角色相关信息
	// if (isshown) {
	// String groupIDsStr = StringUtil.build2Str(groupIDs, ",");
	// String groupNamesStr = StringUtil.build2Str(groupNames, ",");
	// String roleIDsStr = StringUtil.build2Str(roleIDs, ",");
	// String systemIDsStr = StringUtil.build2Str(systemIDs, ",");
	// String roleNamesStr = StringUtil.build2Str(roleNames, ",");
	//
	// entity.setId(adminid);
	// entity.setGroupIDs(groupIDsStr);
	// entity.setGroupNames(groupNamesStr); // 分组名称
	// entity.setRoleIDs(roleIDsStr);
	// entity.setSystemIDs(systemIDsStr);
	// entity.setRoleNames(roleNamesStr);// 角色名称
	// } else {
	// // 4.3.否则，此用户没有管理用户的权限
	// if (!"admin".equals(dto.getUsername())) {
	// result.remove(entity);
	// }
	// }
	// }
	// return result;
	// }

	public List<Admin> getAdmins(AdminDTO dto) {
		// 1.查询所有用户完整的信息( 用户 + 完整的分组信息 + 能管理的角色信息)
		List<Admin> adminList = buildAdminsAll(dto);

		List<Admin> result = new ArrayList<Admin>();
		for (Admin admin : adminList) {
			result.add(admin);
		}

		// 2.查询指定账号id的(用户-分组id)
		List<Group> grouplist = dao.getGroups(dto);
		List<String> groupIDs = new ArrayList<String>();
		for (Group group : grouplist) {
			int groupid = group.getGroupID();
			if (!groupIDs.contains("" + groupid)) {
				groupIDs.add("" + groupid);
			}
		}
		
		// 3.剔除不匹配用户信息，该用户的分组groupIDs不包含，则剔除
		if (!"admin".equals(dto.getUsername())) {
			for (Admin entity : adminList) {
				String[] groupids = entity.getGroupIDs() == null ? null : entity.getGroupIDs().split(",");
				for (String id : groupids) {
					if (!groupIDs.contains(id)) {
						result.remove(entity);
					}
				}
			}
		}

		return result;
	}
	
	/**
	 * 查询所有用户<br>
	 * 1.完整的分组信息<br>
	 * 2.该用户能管理的角色信息<br>
	 * @return
	 */
	private List<Admin> buildAdminsAll(AdminDTO dto){
		// 1.查询所有用户信息
		List<Admin> adminList = dao.getAdminAll();

		// 2.根据admin查询完整的用户-分组-角色信息
		AdminDTO admindto = new AdminDTO();
		admindto.setUsername("admin");
		List<Group> grouplist = dao.getGroups(admindto);
		List<Role> rolelist = dao.getRoles(dto);
		for (Admin entity : adminList) {
			int adminid = entity.getId();

			// 4.1匹配去重
			List<String> groupIDs = new ArrayList<String>();
			List<String> groupNames = new ArrayList<String>();
			List<String> roleIDs = new ArrayList<String>();
			List<String> systemIDs = new ArrayList<String>();
			List<String> roleNames = new ArrayList<String>();

			for (Group group : grouplist) {
				int adminidgroup = group.getId();
				int groupid = group.getGroupID();
				String groupname = group.getGroupname();

				if (adminidgroup == adminid) {
					if (!groupIDs.contains("" + groupid)) {
						groupIDs.add("" + groupid);
						groupNames.add(groupname);
					}
				}
			}

			for (Role role : rolelist) {
				int adminidrole = role.getId();
				int roleid = role.getRoleID();
				String rolename = role.getRoleName();
				String systemID = role.getSystemID();

				if (adminidrole == adminid) {
					if (!roleIDs.contains("" + roleid)) {
						roleIDs.add("" + roleid);
						roleNames.add(rolename);
						systemIDs.add(systemID);
					}
				}
			}

			// 4.2 如果符合要求，则拼装添加分组和角色相关信息
			String groupIDsStr = StringUtil.build2Str(groupIDs, ",");
			String groupNamesStr = StringUtil.build2Str(groupNames, ",");
			String roleIDsStr = StringUtil.build2Str(roleIDs, ",");
			String systemIDsStr = StringUtil.build2Str(systemIDs, ",");
			String roleNamesStr = StringUtil.build2Str(roleNames, ",");

			entity.setId(adminid);
			entity.setGroupIDs(groupIDsStr);
			entity.setGroupNames(groupNamesStr); // 分组名称
			entity.setRoleIDs(roleIDsStr);
			entity.setSystemIDs(systemIDsStr);
			entity.setRoleNames(roleNamesStr);// 角色名称
		}
		return adminList;
	}

	public List<String> getRolePermissions(int roleID) {
		return dao.getRolePermissions(roleID);
	}

	public List<Role> getRoleFixedUser(AdminDTO dto) {
		return dao.getRoleFixedUser(dto);
	}

	public int grantPermission(int roleID, List<String> perms,
			int operateAdminID) {
		return dao.grantPermission(roleID, perms, operateAdminID);
	}

	public int modifyMyInfo(Admin admin) {
		return dao.modifyMyInfo(admin);
	}

	public int updateAdminInfo(Admin admin) {
		return dao.updateAdminInfo(admin);
	}

	public int updateAdminPasswd(int id, String md5pwd) {
		return dao.updateAdminPasswd(id, md5pwd);
	}

	public int updateAdminRole(int adminID, List<Integer> roles) {
		return dao.updateAdminRole(adminID, roles);
	}

	public int addIPBlackFilter(IpFilter filter) {
		return dao.addIPBlackFilter(filter);
	}

	public int addIPWhiteFilter(IpFilter filter) {
		return dao.addIPWhiteFilter(filter);
	}

	public int deleteBlackFilter(int sid) {
		return dao.deleteBlackFilter(sid);
	}

	public int deleteWhiteFilter(int sid) {
		return dao.deleteWhiteFilter(sid);
	}

	public List<IpFilter> getBlackFilterList() {
		return dao.getBlackFilterList();
	}

	public List<IpFilter> getWhiteFilterList() {
		return dao.getWhiteFilterList();
	}

	public int deleteAdminRole(int adminID) {
		return dao.deleteAdminRole(adminID);
	}

	public List<MenuItem> listAllMenus() {
		return dao.listAllMenus();
	}

	public List<Parameters> listParameters(String code) {
		try {
			return dao.listParameters(code);

		} catch (Exception e) {
			return null;
		} finally {
		}
	}

	public int addGroup(Group group) {
		return dao.addGroup(group);
	}

	public int checkGroupNameExists(String groupname) {
		return dao.checkGroupNameExists(groupname);
	}

	public int deleteAdminGroup(int adminID) {
		return dao.deleteAdminGroup(adminID);
	}

	public int deleteGroup(int id) {
		return dao.deleteGroup(id);
	}

	public List<Group> getGroupFixedUser(AdminDTO admin) {
		return dao.getGroupFixedUser(admin);
	}

	public int updateAdminGroup(int adminID, List<Integer> groups) {
		return dao.updateAdminGroup(adminID, groups);
	}

	public List<MenuItem> listMenus(String menuKey) {
		return dao.listMenus(menuKey);
	}

	public String getMenuKey(String systemID) {
		return dao.getMenuKey(systemID);
	}

}
