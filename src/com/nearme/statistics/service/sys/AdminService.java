package com.nearme.statistics.service.sys;

import java.util.List;

import com.nearme.statistics.dto.sys.AdminDTO;
import com.nearme.statistics.model.sys.MenuItem;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Group;
import com.nearme.statistics.model.sys.admin.IpFilter;
import com.nearme.statistics.model.sys.admin.Parameters;
import com.nearme.statistics.model.sys.admin.Role;

public interface AdminService {
	//--------------------用户信息管理--------------------
	public Admin Login(String adminName, String md5pwd);
	
	/**
	 * 查询指定用户能查看的账号信息
	 * @param dto
	 * @return
	 */
	public List<Admin> getAdmins(AdminDTO dto);
	/**
	 * 查询指定用户的账号信息
	 * @param dto
	 * @return
	 */
	public Admin getAdminFixedUsername(AdminDTO dto);
	public int checkAdminNameExists(String adminName);
	public int addAdmin(Admin admin);
	/**
	 * 只更改密码
	 * @param id
	 * @param md5pwd
	 * @return
	 */
	public int updateAdminPasswd(int id,String md5pwd);
	/**
	 * 超级管理员更改普通管理员所有信息(包括所属角色等)
	 * @param admin
	 * @return
	 */
	public int updateAdminInfo(Admin admin);
	/**
	 * 管理员更改自身信息
	 * @param admin
	 * @return
	 */
	public int modifyMyInfo(Admin admin);
	public int deleteAdmin(int id);
	
	
	//--------------------角色管理--------------------
	/**
	 * 查询指定账号id对应的角色信息
	 * @param admin
	 * @return
	 */
	public List<Role> getRoleFixedUser(AdminDTO dto);
	public int checkRoleNameExists(String roleName);
	public int addRole(Role role);
	public int deleteRole(int id);
	public int deleteAdminRole(int adminID);
	public int updateAdminRole(int adminID,List<Integer> roles);
	
	//--------------------分组管理--------------------
	/**
	 * 查询指定账号id对应的分组信息
	 * @param admin
	 * @return
	 */
	public List<Group> getGroupFixedUser(AdminDTO dto);
	public int checkGroupNameExists(String groupname);
	public int addGroup(Group group);
	public int deleteGroup(int id);
	public int deleteAdminGroup(int adminID);
	public int updateAdminGroup(int adminID,List<Integer> groups);
	
	//--------------------权限管理--------------------
	public int deletePermission(int roleID);
	public int grantPermission(int roleID,List<String> perms,int operateAdminID);
	public List<String> getAdminPermissions(int adminID);
	public List<String> getRolePermissions(int roleID);
	
	//--------------------IP限制--------------------
	public int addIPBlackFilter(IpFilter filter);
	public int addIPWhiteFilter(IpFilter filter);
	public List<IpFilter> getBlackFilterList();
	public List<IpFilter> getWhiteFilterList();
	public int deleteBlackFilter(int sid);
	public int deleteWhiteFilter(int sid);
	
	//-------------------菜单管理--------------------
	public List<MenuItem> listAllMenus();
	public String getMenuKey(String systemID);// 根据指定systemID查询func_code
	public List<MenuItem> listMenus(String menuKey);// 根据指定menuKey查询显示的菜单
	public List<Parameters> listParameters(String code);	
}
