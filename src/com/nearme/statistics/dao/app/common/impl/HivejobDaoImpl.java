package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.HivejobDao;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.model.common.HivejobEntity;

public class HivejobDaoImpl implements HivejobDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * hive任务管理-查询任务数
	 */
	public long getHivejobCnt(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Long) sqlSession.selectOne(
					"com.nearme.statistics.dao.app.common.HivejobDao."
							+ "getHivejobCnt", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * hive任务管理-添加任务
	 */
	public void addHivejob(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.insert("com.nearme.statistics.dao.app.common.HivejobDao."
					+ "addHivejob", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * hive任务管理-任务完成
	 */
	public void finishHivejob(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.HivejobDao."
					+ "finishHivejob", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * hive任务管理-查看所有任务
	 */
	public List<HivejobEntity> getHivejobList(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.HivejobDao."
							+ "getHivejobList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public void deleteHivejob(HivejobDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.common.HivejobDao."
					+ "deleteHivejob", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
