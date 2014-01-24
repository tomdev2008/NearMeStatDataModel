package com.nearme.statistics.dao.app.zjb.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.zjb.ZjbUseractioncodeDao;
import com.nearme.statistics.dto.app.zjb.ZJBUseractioncodeDTO;
import com.nearme.statistics.model.zjbsetting.ZJBUseractioncodeEntity;

public class ZjbUseractiontcodeDaoImpl implements ZjbUseractioncodeDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void add(ZJBUseractioncodeDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.zjb.ZjbUseractioncodeDao." +
					"add", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void delete(ZJBUseractioncodeDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.zjb.ZjbUseractioncodeDao." +
					"delete", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBUseractioncodeEntity> getZJBUseractioncodeList(
			ZJBUseractioncodeDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.zjb.ZjbUseractioncodeDao." +
					"getZJBUseractioncodeList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public void update(ZJBUseractioncodeDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app.zjb.ZjbUseractioncodeDao." +
					"update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

}
