package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.EventDao;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.EventDTO;
import com.nearme.statistics.dto.app.common.EventFlowDTO;
import com.nearme.statistics.model.common.KVEventDetailEntity;
import com.nearme.statistics.model.common.KVEventEntity;
import com.nearme.statistics.model.common.KVEventFlowEntity;
import com.nearme.statistics.model.common.KVEventIndexEntity;

/**
 * 自定义事件
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-10-23
 */
public class EventDaoImpl implements EventDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * KV事件 列表 
	 */
	public List<KVEventEntity> listKVEvent(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listKVEvent",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * KV事件统计列表 
	 */
	public List<KVEventDetailEntity> listKVEventDetail(EventDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listKVEventDetail",dto);
		}  finally {
			sqlSession.close();
		}
	}
	
	/**
	 * KV事件统计列表 
	 */
	public List<KVEventDetailEntity> listKVEventDetailSum(EventDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listKVEventDetailSum",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * KV事件Key列表
	 */
	public List<KVEventIndexEntity> listKVEventIndex(EventDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listKVEventIndex",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * KV事件详情统计列表
	 */
	public List<KVEventDetailEntity> listKVEventKey(EventDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listKVEventKey",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * KV事件新增
	 */
	public int addKVEvent(EventDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.insert("com.nearme.statistics.dao.app.common.EventDao.addKVEvent",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * KV事件删除
	 */
	public int deleteKVEvent(EventDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.update("com.nearme.statistics.dao.app.common.EventDao.deleteKVEvent",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * KV事件更新
	 */
	public int updateKVEvent(EventDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.update("com.nearme.statistics.dao.app.common.EventDao.updateKVEvent",dto);
		}  finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 添加KV事件流
	 */
	public int addKVEventFlow(EventFlowDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.insert("com.nearme.statistics.dao.app.common.EventDao.addKVEventFlow",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * 删除KV事件流
	 */
	public int deleteKVEventFlow(EventFlowDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.update("com.nearme.statistics.dao.app.common.EventDao.deleteKVEventFlow",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * 查询 KV事件流列表
	 */
	public List<KVEventFlowEntity> listKVEventFlow(BaseDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listKVEventFlow",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * 一个事件流详情
	 */
	public List<KVEventFlowEntity> getTheKVEventFlow(EventFlowDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.getTheKVEventFlow",dto);
		}  finally {
			sqlSession.close();
		}
	}

	/**
	 * 转化率详情
	 */
	public List<KVEventFlowEntity> listConversionDetail(EventFlowDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listConversionDetail",dto);
		}  finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 转化率在一段时间内的详情
	 */
	public List<KVEventFlowEntity> listConversionDetailPeriod(EventFlowDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.EventDao.listConversionDetailPeriod",dto);
		}  finally {
			sqlSession.close();
		}
	}
}
