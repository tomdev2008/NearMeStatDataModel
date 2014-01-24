package com.nearme.statistics.dao.app.kkdesk.impl;

import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.kkdesk.KkdeskDao;

public class KkdeskDaoImpl implements KkdeskDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
}