package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.VersionDao;
import com.nearme.statistics.dto.app.common.VersionDTO;
import com.nearme.statistics.model.commonsetting.VersionEntity;

public class VersionDaoImpl implements VersionDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<VersionEntity> getVersionList(VersionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.common.VersionDao."
							+ "getVersionList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public void update(VersionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.common.VersionDao."
							+ "update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
