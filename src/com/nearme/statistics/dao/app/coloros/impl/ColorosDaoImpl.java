package com.nearme.statistics.dao.app.coloros.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.coloros.ColorosDao;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.model.ColumnValueEntity;
import com.nearme.statistics.model.coloros.COSAreaEntity;
import com.nearme.statistics.model.coloros.COSAvgstartcntrankEntity;
import com.nearme.statistics.model.coloros.COSDetailEntity;
import com.nearme.statistics.model.coloros.COSInstallrankEntity;
import com.nearme.statistics.model.coloros.COSMonthsrEntity;
import com.nearme.statistics.model.coloros.COSNetdistributeEntity;
import com.nearme.statistics.model.coloros.COSStartrankEntity;
import com.nearme.statistics.model.coloros.COSVersionActiveEntity;
import com.nearme.statistics.model.coloros.COSWeeknirEntity;

public class ColorosDaoImpl implements ColorosDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<COSAvgstartcntrankEntity> getAvgstartcntrankList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getAvgstartcntrankList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSStartrankEntity> getStartrankList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getStartrankList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSInstallrankEntity> getInstallrankList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getInstallrankList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSVersionActiveEntity> getVersionactiveList(ColorosDTO dto){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getVersionactiveList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 活跃用户地域分析
	 * @param dto
	 * @return
	 */
	public List<COSAreaEntity> listAreaStart(ColorosDTO dto){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao.listAreaStart",dto);
			
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSMonthsrEntity> getMonthsrList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getMonthsrList",dto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSWeeknirEntity> getWeeknirList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getWeeknirList",dto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSNetdistributeEntity> getNetdistributeList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getNetdistributeList",dto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ColumnValueEntity> getMobilezhanbiList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getMobilezhanbiList",dto);
		} catch (Exception e) {
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<COSDetailEntity> getDetailList(ColorosDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.coloros.ColorosDao." +
					"getDetailList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}

