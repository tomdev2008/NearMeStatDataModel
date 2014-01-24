package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.UseractionDao;
import com.nearme.statistics.dto.app.common.UseractionDTO;
import com.nearme.statistics.model.commonsetting.UseractionEntity;

public class UseractionDaoImpl implements UseractionDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 用户行为编码-查询行为信息
	 */
	public List<UseractionEntity> getUseractionList(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.getUseractionList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 用户行为编码-查询分组信息
	 */
	public List<UseractionEntity> getUsergroupList(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.getUsergroupList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 用户行为编码-删除行为信息
	 */
	public void deleteActioncode(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.delete("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.deleteActioncode", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 用户行为编码-删除分组信息
	 */
	public void deleteGroupcode(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.delete("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.deleteGroupcode", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 用户行为编码-更新行为编码
	 */
	public void updateActioncode(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.updateActioncode", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 用户行为编码-更新分组信息
	 */
	public void updateGroupcode(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.updateGroupcode", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void insertActioncode(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.insertActioncode", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public void insertGroupcode(UseractionDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app"
					+ ".common.UseractionDao.insertGroupcode", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
