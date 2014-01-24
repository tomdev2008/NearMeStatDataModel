package com.nearme.statistics.dao.app.liren.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.liren.LirenDao;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.model.liren.LRCatchgoodsEntity;
import com.nearme.statistics.model.liren.LRCategoryEntity;
import com.nearme.statistics.model.liren.LRGoodsEntity;
import com.nearme.statistics.model.liren.LRGoodsqualityEntity;
import com.nearme.statistics.model.liren.LRHomepageEntity;
import com.nearme.statistics.model.liren.LRPushEntity;
import com.nearme.statistics.model.liren.LRTaghotEntity;
import com.nearme.statistics.model.liren.LRVersion4upEntity;

public class LirenDaoImpl implements LirenDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 首页
	 */
	public List<LRHomepageEntity> getHomepageList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao.getHomepageList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 类目
	 */
	public List<LRCategoryEntity> getCategoryList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao.getCategoryList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 商品
	 */
	public List<LRGoodsEntity> getGoodsList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao.getGoodsList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 商品质量
	 */
	public List<LRGoodsqualityEntity> getGoodsqualityList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao.getGoodsqualityList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 标签热度
	 */
	public List<LRTaghotEntity> getTaghotList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao.getTaghotList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * push数据
	 */
	public List<LRPushEntity> getPushList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao." +
					"getPushList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 抓取商品
	 */
	public List<LRCatchgoodsEntity> getCatchgoodsList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao." +
					"getCatchgoodsList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * V4.0以上版本数据
	 */
	public List<LRVersion4upEntity> getVersion4upList(LirenDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.liren.LirenDao." +
					"getVersion4upList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
