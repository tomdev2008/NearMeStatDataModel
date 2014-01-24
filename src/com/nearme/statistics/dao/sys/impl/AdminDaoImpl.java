package com.nearme.statistics.dao.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.sys.AdminDao;
import com.nearme.statistics.dto.sys.AdminDTO;
import com.nearme.statistics.model.sys.MenuItem;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Group;
import com.nearme.statistics.model.sys.admin.IpFilter;
import com.nearme.statistics.model.sys.admin.Parameters;
import com.nearme.statistics.model.sys.admin.Role;

public class AdminDaoImpl implements AdminDao {
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public Admin Login(String adminName, String md5pwd) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("adminName", adminName);
		param.put("passwd", md5pwd);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return (Admin) session.selectOne(
					"com.nearme.statistics.dao.sys.AdminDao.Login", param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Admin> getAdminAll() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getAdminAll");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public Admin getAdminFixedUsername(AdminDTO dto) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectOne("com.nearme.statistics.dao.sys.AdminDao."
					+ "getAdminFixedUsername", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public int checkAdminNameExists(String adminName) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Object obj = session.selectOne(
					"com.nearme.statistics.dao.sys.AdminDao."
							+ "checkAdminNameExists", adminName);
			if (obj != null) {
				return (Integer) obj;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int addAdmin(Admin admin) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.insert("com.nearme.statistics.dao.sys.AdminDao."
					+ "addAdmin", admin);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int updateAdminPasswd(int id, String md5pwd) {
		Admin param = new Admin();
		param.setId(id);
		param.setUserPasswd(md5pwd);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "updateAdminPasswd", param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int updateAdminInfo(Admin admin) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "updateAdminInfo", admin);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int updateAdminRole(int adminID, List<Integer> roles) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adminID", adminID);

		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,
				false);
		try {
			session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteAdminRole", adminID);
			if (roles != null && roles.size() > 0) {
				for (int rid : roles) {
					param.put("roleID", rid);
					session.insert("com.nearme.statistics.dao.sys.AdminDao."
							+ "addAdminRole", param);
				}
			}
			session.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}
	
	public int updateAdminGroup(int adminID, List<Integer> groups) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adminID", adminID);

		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,
				false);
		try {
			session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteAdminGroup", adminID);
			if (groups != null && groups.size() > 0) {
				for (int gid : groups) {
					param.put("groupID", gid);
					session.insert("com.nearme.statistics.dao.sys.AdminDao."
							+ "addAdminGroup", param);
				}
			}
			session.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int modifyMyInfo(Admin admin) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "modifyMyInfo", admin);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int deleteAdmin(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteAdmin", id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	/******************* 组管理 **********************/
	public List<Role> getRoleAll() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getRoleAll");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public List<Role> getRoleFixedUser(AdminDTO dto) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getRoleFixedUser", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public int checkRoleNameExists(String roleName) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Object obj = session.selectOne(
					"com.nearme.statistics.dao.sys.AdminDao."
							+ "checkRoleNameExists", roleName);
			if (obj != null) {
				return (Integer) obj;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int addRole(Role role) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.insert("com.nearme.statistics.dao.sys.AdminDao."
					+ "addRole", role);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int deleteRole(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteRole", id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	/********************* 权限管理 ********************/
	public int deletePermission(int roleID) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deletePermission", roleID);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int grantPermission(int roleID, List<String> perms,
			int operateAdminID) {
		if (perms == null || perms.size() <= 0) {
			return 0;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleID", roleID);
		param.put("updateBy", operateAdminID);

		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH,
				false);
		int count = 0;
		try {
			for (String perm : perms) {
				param.put("permCode", perm);
				session.insert("com.nearme.statistics.dao.sys.AdminDao."
						+ "grantPermission", param);
				count++;
				if (count % 1000 == 0) {
					session.commit();
				}
			}

			session.commit();
			return perms.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public List<String> getAdminPermissions(int adminID) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getAdminPermissions", adminID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<String> getRolePermissions(int roleID) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getRolePermissions", roleID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	/********************* IP限制管理 ********************/
	public int addIPBlackFilter(IpFilter filter) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tableName", "GLT_IP_BLACKLIST");
		param.put("filter", filter);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "addIPFilter", param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int addIPWhiteFilter(IpFilter filter) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tableName", "GLT_IP_WHITELIST");
		param.put("filter", filter);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "addIPFilter", param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public List<IpFilter> getBlackFilterList() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getFilterList", "GLT_IP_BLACKLIST");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<IpFilter> getWhiteFilterList() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getFilterList", "GLT_IP_WHITELIST");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public int deleteBlackFilter(int sid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tableName", "GLT_IP_BLACKLIST");
		param.put("id", sid);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteIPFilter", param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int deleteWhiteFilter(int sid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tableName", "GLT_IP_WHITELIST");
		param.put("id", sid);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteIPFilter", param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int deleteAdminRole(int adminID) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteAdminRole", adminID);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public List<MenuItem> listAllMenus() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "listAllMenus");
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	public List<Parameters> listParameters(String code) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "listParameters", code);
		} finally {
			session.close();
		}
	}

	public int addGroup(Group group) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.insert("com.nearme.statistics.dao.sys.AdminDao."
					+ "addGroup", group);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int checkGroupNameExists(String groupname) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Object obj = session.selectOne(
					"com.nearme.statistics.dao.sys.AdminDao."
							+ "checkGroupNameExists", groupname);
			if (obj != null) {
				return (Integer) obj;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int deleteAdminGroup(int adminID) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteAdminGroup", adminID);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int deleteGroup(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.update("com.nearme.statistics.dao.sys.AdminDao."
					+ "deleteGroup", id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}
	
	public List<Group> getGroupAll() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getGroupAll");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public List<Group> getGroupFixedUser(AdminDTO dto) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getGroupFixedUser", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Group> getGroups(AdminDTO dto) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getGroups", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Role> getRoles(AdminDTO dto) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "getRoles", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<MenuItem> listMenus(String menuKey) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList("com.nearme.statistics.dao.sys.AdminDao."
					+ "listMenus", menuKey);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public String getMenuKey(String systemID) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectOne("com.nearme.statistics.dao.sys.AdminDao."
					+ "getMenuKey", systemID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
}
