package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.InstallSourceDao;
import com.nearme.statistics.dto.app.common.InstallSourceDTO;
import com.nearme.statistics.model.commonsetting.InstallSourceEntity;

public class InstallSourceDaoImpl implements InstallSourceDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 查询
	 */
	public List<InstallSourceEntity> listInstallSource(InstallSourceDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.InstallSourceDao.listInstallSource", dto);
		}finally {
			sqlSession.close();
		}
	}

}
