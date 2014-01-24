package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.ChannelinfoDao;
import com.nearme.statistics.dto.app.common.ChannelinfoDTO;
import com.nearme.statistics.model.commonsetting.ChannelinfoEntity;

public class ChannelinfoDaoImpl implements ChannelinfoDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 添加渠道
	 */
	public void add(ChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app"
					+ ".common.ChannelinfoDao.add", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 删除渠道
	 */
	public void delete(ChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.delete("com.nearme.statistics.dao.app"
					+ ".common.ChannelinfoDao.delete", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 查询渠道
	 */
	public List<ChannelinfoEntity> getChnnelinfoList(ChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app"
					+ ".common.ChannelinfoDao.getChnnelinfoList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 更新渠道
	 */
	public void update(ChannelinfoDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.nearme.statistics.dao.app"
					+ ".common.ChannelinfoDao.update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
