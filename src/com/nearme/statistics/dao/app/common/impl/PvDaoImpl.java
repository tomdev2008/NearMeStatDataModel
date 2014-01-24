package com.nearme.statistics.dao.app.common.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.common.PvDao;
import com.nearme.statistics.dto.app.common.PvDTO;
import com.nearme.statistics.model.common.PvEntity;
import com.nearme.statistics.model.common.VisitPathEntity;

/**
 * 页面访问DaoImpl
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-17
 */
public class PvDaoImpl implements PvDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * 使用时长/单次
	 */
	public List<PvEntity> listDurationTimes(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listDurationTimes", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	/**
	 * 使用时长/单日
	 */
	public List<PvEntity> listDurationImeis(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listDurationImeis", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	/**
	 * 使用频率
	 */
	public List<PvEntity> listFrequency(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listFrequency", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	/**
	 * 访问页面
	 */
	public List<PvEntity> listVistPages(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listVistPages", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	@Override
	public List<PvEntity> listDurationImeisCp(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listDurationImeisCp", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	@Override
	public List<PvEntity> listDurationTimesCp(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listDurationTimesCp", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	@Override
	public List<PvEntity> listFrequencyCp(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listFrequencyCp", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	/**
	 * 当前页面访问统计
	 */
	public List<VisitPathEntity> listCurPageJump(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listCurPageJump", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

	/**
	 * 当前页面跳转统计
	 */
	public List<VisitPathEntity> listCurPageVisit(PvDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("com.nearme.statistics.dao.app.common.PvDao.listCurPageVisit", dto);
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
}
