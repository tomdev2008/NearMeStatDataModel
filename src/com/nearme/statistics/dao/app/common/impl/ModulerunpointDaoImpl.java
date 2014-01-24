package com.nearme.statistics.dao.app.common.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.ModulerunpointDao;
import com.nearme.statistics.dto.app.common.ModulerunpointDTO;
import com.nearme.statistics.model.commonsetting.ModulerunpointEntity;
import com.nearme.statistics.util.HiveDataUtil;

public class ModulerunpointDaoImpl implements ModulerunpointDao {
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

	public void add(ModulerunpointDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.common.ModulerunpointDao."
							+ "add", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void delete(ModulerunpointDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.delete(
					"com.nearme.statistics.dao.app.common.ModulerunpointDao."
							+ "delete", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public List<ModulerunpointEntity> getModulerunpointList(
			ModulerunpointDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.ModulerunpointDao."
							+ "getModulerunpointList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public void update(ModulerunpointDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.common.ModulerunpointDao."
							+ "update", dto);
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
	private String getHDFSStr(List<ModulerunpointEntity> list) {
		StringBuilder sb = new StringBuilder(256);
		for (ModulerunpointEntity entity : list) {
			// String id = entity.getId();
			String packagename = entity.getPackagename();
			String groupname = entity.getGroupname();
			String systemID = entity.getSystemID();
			String sourcecode = entity.getSourcecode();
			String sourcedesc = entity.getSourcedesc();
			String categoryid = entity.getCategoryid();
			String clikcindex = entity.getClickindex();
			String tag = entity.getTag();
			String updatetime = "''";

			// 需要考虑为空的情况
			sb.append(packagename == null ? "" : packagename).append(
					HiveDataUtil.CHARACTER_BETWEEN);
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
			sb.append(clikcindex == null ? "" : clikcindex).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(tag == null ? "" : tag).append(
					HiveDataUtil.CHARACTER_BETWEEN);
			sb.append(updatetime == null ? "" : updatetime).append(
					HiveDataUtil.CHARACTER_END);
		}
		return sb.toString();
	}

	public void sync2Hive(List<ModulerunpointEntity> list) {
		String hdfsStr = getHDFSStr(list);
		
		System.out.println(hdfsStr);
		
		String hdfsFilename = "bt_source_code_group.log";
		String hivetablename = "client_start_ods.bt_source_code_group";
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
}
