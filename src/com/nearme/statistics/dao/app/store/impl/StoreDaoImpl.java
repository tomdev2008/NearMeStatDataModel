package com.nearme.statistics.dao.app.store.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.store.StoreDao;
import com.nearme.statistics.dto.app.store.StoreDTO;
import com.nearme.statistics.model.store.ActivecenterEntity;
import com.nearme.statistics.model.store.AppEntity;
import com.nearme.statistics.model.store.ClientcontentEntity;
import com.nearme.statistics.model.store.DetailuserclientEntity;
import com.nearme.statistics.model.store.DownInstallEntity;
import com.nearme.statistics.model.store.DowngiftEntity;
import com.nearme.statistics.model.store.GameEntity;
import com.nearme.statistics.model.store.HomepagerecommendEntity;
import com.nearme.statistics.model.store.InstallStatEntity;
import com.nearme.statistics.model.store.InstallmustEntity;
import com.nearme.statistics.model.store.PoprecentEntity;
import com.nearme.statistics.model.store.PushEntity;
import com.nearme.statistics.model.store.SearchEntity;
import com.nearme.statistics.model.store.SortEntity;
import com.nearme.statistics.model.store.StoreEditorrecommendEntity;
import com.nearme.statistics.model.store.StoreHotsearchEntity;
import com.nearme.statistics.model.store.StoreTypeselectEntity;
import com.nearme.statistics.model.store.TuijianEntity;
import com.nearme.statistics.model.store.ZuantiEntity;

public class StoreDaoImpl implements StoreDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 细分用户客户端行为统计
	 */
	public List<DetailuserclientEntity> getDetailuserclientList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getDetailuserclientList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 活动中心报表
	 */
	public List<ActivecenterEntity> getActivecenterList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getActivecenterList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 应用榜报表
	 */
	public List<AppEntity> getAppList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getAppList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 下载有礼报表
	 */
	public List<DowngiftEntity> getDowngiftList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getDowngiftList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 游戏榜报表
	 */
	public List<GameEntity> getGameList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getGameList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 首页推荐报表
	 */
	public List<HomepagerecommendEntity> getHomepagerecommendList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getHomepagerecommendList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * push推送报表
	 */
	public List<PushEntity> getPushList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getPushList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 搜索报表
	 */
	public List<SearchEntity> getSearchList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getSearchList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 相关推荐报表
	 */
	public List<TuijianEntity> getTuijianList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getTuijianList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 专题日报表
	 */
	public List<ZuantiEntity> getZuantiList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getZuantiList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 装机必备
	 */
	public List<InstallmustEntity> getInstallmustList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getInstallmustList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 最近流行
	 */
	public List<PoprecentEntity> getPoprecentList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getPoprecentList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 分类
	 */
	public List<SortEntity> getSortList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getSortList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 客户端内容
	 */
	public List<ClientcontentEntity> getClientcontentList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.getClientcontentList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 下载安装成功率
	 */
	public List<DownInstallEntity> listDownInstall(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.listDownInstall", dto);
	}


	/**
	 * 单竞品分析(详情)
	 */
	public List<InstallStatEntity> listAppSource(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.listAppSource", dto);
		}finally {
			sqlSession.close();
		}
	}

	/**
	 * 某单个渠道的流量情况
	 */
	public List<InstallStatEntity> listInstallByChannel(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.listInstallByChannel", dto);
		}finally {
			sqlSession.close();
		}
	}

	/**
	 * top100渠道分布
	 */
	public List<InstallStatEntity> listInstallChannelTop(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.listInstallChannelTop", dto);
		}finally {
			sqlSession.close();
		}
	}

	/**
	 * 单竞品分析(列表)
	 */
	public List<InstallStatEntity> listInstallFromApp(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.listInstallFromApp", dto);
		}finally {
			sqlSession.close();
		}
	}

	/**
	 * 总体流量分布
	 */
	public List<InstallStatEntity> listInstallTotal(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao.listInstallTotal", dto);
		}finally {
			sqlSession.close();
		}
	}

	/**
	 * 小编推荐
	 */
	public List<StoreEditorrecommendEntity> getEditorrecommendList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao." +
					"getEditorrecommendList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 48小时热搜榜
	 */
	public List<StoreHotsearchEntity> getHotsearchList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao." +
					"getHotsearchList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 分类精选
	 */
	public List<StoreTypeselectEntity> getTypeselectList(StoreDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nearme.statistics.dao.app.store.StoreDao." +
					"getTypeselectList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
