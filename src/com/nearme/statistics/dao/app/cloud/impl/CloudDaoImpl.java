package com.nearme.statistics.dao.app.cloud.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.cloud.CloudDao;
import com.nearme.statistics.dto.app.cloud.CloudDTO;
import com.nearme.statistics.model.cloud.CloudDayincreaseEntity;
import com.nearme.statistics.model.cloud.CloudFlowdistributeEntity;
import com.nearme.statistics.model.cloud.CloudPathEntity;
import com.nearme.statistics.model.cloud.CloudRemainEntity;

public class CloudDaoImpl implements CloudDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 日增情况
	 */
	public List<CloudDayincreaseEntity> getDayincreaseList(CloudDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app."
					+ "cloud.CloudDao.getDayincreaseList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 流量分配
	 */
	public List<CloudFlowdistributeEntity> getFlowdistributeList(CloudDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app."
					+ "cloud.CloudDao.getFlowdistributeList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 路径统计
	 */
	public List<CloudPathEntity> getPathList(CloudDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app."
					+ "cloud.CloudDao.getPathList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 存储留存
	 */
	public List<CloudRemainEntity> getRemainList(CloudDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app."
					+ "cloud.CloudDao.getRemainList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

}
