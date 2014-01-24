package com.nearme.statistics.dao.app.game.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.game.GameDao;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.model.game.DownactivecenterEntity;
import com.nearme.statistics.model.game.DownhomepageEntity;
import com.nearme.statistics.model.game.DownloadEntity;
import com.nearme.statistics.model.game.DownrankEntity;
import com.nearme.statistics.model.game.DownsearchEntity;
import com.nearme.statistics.model.game.DownsearchrecommendEntity;
import com.nearme.statistics.model.game.DownsortEntity;
import com.nearme.statistics.model.game.DownspecialtopicEntity;
import com.nearme.statistics.model.game.GameFeeEntity;
import com.nearme.statistics.model.game.GameRemainEntity;
import com.nearme.statistics.model.game.GameinfoEntity;
import com.nearme.statistics.model.game.JointgameEntity;
import com.nearme.statistics.model.game.ProductEntity;
import com.nearme.statistics.model.game.ProductreportdailyEntity;
import com.nearme.statistics.model.game.ProductreportmonthlyEntity;
import com.nearme.statistics.model.game.ProductreportweeklyEntity;
import com.nearme.statistics.model.game.RankEntity;
import com.nearme.statistics.model.game.SearchkeyEntity;
import com.nearme.statistics.model.game.StartEntity;
import com.nearme.statistics.model.game.UpdateEntity;

public class GameDaoImpl implements GameDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 活动中心下载
	 */
	public List<DownactivecenterEntity> getDownactivecenterList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownactivecenterList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 首页下载
	 */
	public List<DownhomepageEntity> getDownhomepageList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownhomepageList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 游戏下载
	 */
	public List<DownloadEntity> getDownloadList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownloadList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 排行榜下载
	 */
	public List<DownrankEntity> getDownrankList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownrankList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 搜索下载
	 */
	public List<DownsearchEntity> getDownsearchList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownsearchList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 搜推下载
	 */
	public List<DownsearchrecommendEntity> getDownsearchrecommendList(
			GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownsearchrecommendList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 分类下载
	 */
	public List<DownsortEntity> getDownsortList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownsortList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 分类下载top
	 */
	public List<DownsortEntity> getDownsorttopList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownsorttopList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 专题下载
	 */
	public List<DownspecialtopicEntity> getDownspecialtopicList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getDownspecialtopicList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 游戏信息
	 */
	public List<GameinfoEntity> getGameinfoList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getGameinfoList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 游戏名2
	 */
	public List<GameinfoEntity> getGameList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getGameList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 游戏更新
	 */
	public List<UpdateEntity> getUpdateList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getUpdateList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 游戏启动
	 */
	public List<StartEntity> getStartList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getStartList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 排行位置
	 */
	public List<RankEntity> getRankpositionList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getRankpositionList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 排行top30
	 */
	public List<RankEntity> getRanktop30List(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getRanktop30List",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 搜索关键字
	 */
	public List<SearchkeyEntity> getSearchkeyList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getSearchkeyList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ProductEntity> getProductList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getProductList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 产品日报
	 */
	public List<ProductreportdailyEntity> getProductreportdailyList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getProductreportdailyList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 产品月报
	 */
	public List<ProductreportmonthlyEntity> getProductreportmonthlyList(
			GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getProductreportmonthlyList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 产品周报
	 */
	public List<ProductreportweeklyEntity> getProductreportweeklyList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getProductreportweeklyList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 联运游戏数据
	 */
	public List<JointgameEntity> getJointgameList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getJointgameList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 留存分析
	 */
	public List<GameRemainEntity> getRemainList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getRemainList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 付费分析
	 */
	public List<GameFeeEntity> getFeeList(GameDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.game.GameDao.getFeeList",dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
