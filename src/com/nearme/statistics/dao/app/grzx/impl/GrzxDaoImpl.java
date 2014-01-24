package com.nearme.statistics.dao.app.grzx.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.grzx.GrzxDao;
import com.nearme.statistics.dto.app.grzx.GrzxDTO;
import com.nearme.statistics.model.grzx.GrzxNewfromEntity;
import com.nearme.statistics.model.grzx.GrzxRegisterfromEntity;
import com.nearme.statistics.model.grzx.GrzxUsercoverEntity;

public class GrzxDaoImpl implements GrzxDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 新增来源
	 */
	public List<GrzxNewfromEntity> getNewfromList(GrzxDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession
					.selectList(
							"com.nearme.statistics.dao.app.grzx.GrzxDao.getNewfromList",
							dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 注册来源
	 */
	public List<GrzxRegisterfromEntity> getRegisterfromList(GrzxDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession
					.selectList(
							"com.nearme.statistics.dao.app.grzx.GrzxDao.getRegisterfromList",
							dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 账号覆盖率 
	 */
	public List<GrzxUsercoverEntity> getUsercoverList(GrzxDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession
					.selectList(
							"com.nearme.statistics.dao.app.grzx.GrzxDao.getUsercoverList",
							dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
