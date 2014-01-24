package com.nearme.statistics.dao.app.common.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.ModuleDao;
import com.nearme.statistics.dto.app.common.ModuleDTO;
import com.nearme.statistics.model.commonsetting.ModuleEntity;
import com.nearme.statistics.util.HiveDataUtil;

public class ModuleDaoImpl implements ModuleDao {
	private SqlSessionFactory sqlSessionFactory;
	private SqlSessionFactory hiveSessionFactory;

	public SqlSessionFactory getHiveSessionFactory() {
		return hiveSessionFactory;
	}

	public void setHiveSessionFactory(SqlSessionFactory hiveSessionFactory) {
		this.hiveSessionFactory = hiveSessionFactory;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void add(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.ModuleDao."
					+ "add", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void deleteDetail(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.delete("com.nearme.statistics.dao.app.common.ModuleDao."
					+ "deleteDetail", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void deleteGroup(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.delete("com.nearme.statistics.dao.app.common.ModuleDao."
					+ "deleteGroup", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public List<ModuleEntity> getDetailList(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.ModuleDao."
							+ "getDetailList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ModuleEntity> getGroupList(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.ModuleDao."
							+ "getGroupList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	public void updateDetail(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.ModuleDao."
					+ "updateDetail", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void updateGroup(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.ModuleDao."
					+ "updateGroup", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 拼写hdfs用于写到hive上
	 * 
	 * @param list
	 * @return
	 */
	private String getHDFSStr(List<ModuleEntity> list) {
		StringBuilder sb = new StringBuilder(256);
		for (ModuleEntity entity : list) {
			// String id = entity.getId();
			String groupname = entity.getGroupname();
			String systemID = entity.getSystemID();
			String sourcecode = entity.getSourcecode();
			String sourcedesc = entity.getSourcedesc();
			String categoryid = entity.getCategoryid();
			String downtype = entity.getDowntype();
			String updatetime = "NA";

			// 需要考虑为空的情况
			sb.append(groupname == null ? "" : groupname).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(systemID == null ? "" : systemID).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(sourcecode == null ? "" : sourcecode).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(sourcedesc == null ? "" : sourcedesc).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(categoryid == null ? "" : categoryid).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(downtype == null ? "" : downtype).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(updatetime == null ? "" : updatetime).append(
					HiveDataUtil.CHARACTER_END);
		}
		return sb.toString();
	}

	public void sync2Hive(List<ModuleEntity> list) {
		String hdfsStr = getHDFSStr(list);

//		System.out.println(hdfsStr);

		String hdfsFilename = "bt_module.log";
		String hivetablename = "client_start_ods.bt_module";
		if (null != hdfsStr && !"".equals(hdfsStr)) {
			SqlSession sqlSession = null;
			Statement stmt = null;
			try {
				sqlSession = hiveSessionFactory.openSession();
				stmt = sqlSession.getConnection().createStatement();
				String hivesql = HiveDataUtil.writeHDFS(hdfsStr, hdfsFilename,
						hivetablename);
				stmt.execute(hivesql);
			} catch (Exception e) {
				e.printStackTrace();
				list = null;
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (null != sqlSession) {
					sqlSession.close();
				}
				// HiveDataUtil.deleteTmpHDFSfile(hdfsFilename);
			}
		}
	}

	public List<ModuleEntity> getModuleAllList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.ModuleDao."
							+ "getModuleAllList");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ModuleEntity> getModuleList(ModuleDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.ModuleDao."
							+ "getModuleList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
