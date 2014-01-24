package com.nearme.statistics.dao.sys.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.sys.ReflectDao;
import com.nearme.statistics.dto.ReflectDTO;

public class ReflectDaoImpl implements ReflectDao {
	private SqlSessionFactory sqlSessionFactory;
	
	//反馈
	public void setReflectInfo(ReflectDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.insert("com.nearme.statistics.dao.sys.ReflectDao.setReflectInfo",dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
}
