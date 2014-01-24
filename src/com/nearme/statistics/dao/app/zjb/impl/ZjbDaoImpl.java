package com.nearme.statistics.dao.app.zjb.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.app.zjb.ZjbDao;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.model.zjb.BasedateEntity;
import com.nearme.statistics.model.zjb.ChannelserviceEntity;
import com.nearme.statistics.model.zjb.ModuleinstallEntity;
import com.nearme.statistics.model.zjb.PerformanceEntity;
import com.nearme.statistics.model.zjb.ServermodelEntity;
import com.nearme.statistics.model.zjb.SoftinstallEntity;
import com.nearme.statistics.model.zjb.TuiguanginstallEntity;
import com.nearme.statistics.model.zjb.ZJBActivedaysdistributeEntity;
import com.nearme.statistics.model.zjb.ZJBOnlinepkginnerinstallEntity;
import com.nearme.statistics.model.zjb.ZJBOnlinepkginstallEntity;
import com.nearme.statistics.model.zjb.ZJBPkginstallEntity;
import com.nearme.statistics.model.zjb.ZJBRunpointEntity;
import com.nearme.statistics.model.zjb.ZJBSoftdownEntity;
import com.nearme.statistics.model.zjb.ZJBSoftdowntopEntity;
import com.nearme.statistics.model.zjb.ZJBUseractionEntity;
import com.nearme.statistics.model.zjb.ZJBVersionanalyEntity;
import com.nearme.statistics.model.zjb.ZjbVersionEntity;

public class ZjbDaoImpl implements ZjbDao {
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 版本
	 */
	public List<ZjbVersionEntity> getZjbVersionList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getZjbVersionList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 基础数据
	 */
	public List<BasedateEntity> getBasedateList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getBasedateList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 分渠道数据
	 * @param dto
	 * @return
	 */
	public List<ChannelserviceEntity> getChannelserviceList(ZjbDTO dto){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getChannelserviceList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 模块安装量
	 */
	public List<ModuleinstallEntity> getModuleinstallList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getModuleinstallList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 性能指标
	 */
	public List<PerformanceEntity> getPerformanceList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getPerformanceList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 服务机型
	 */
	public List<ServermodelEntity> getServermodelList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getServermodelList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 服务机型2
	 */
	public List<ServermodelEntity> getServermodelList2(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getServermodelList2", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 软件安装排行
	 */
	public List<SoftinstallEntity> getSoftinstallList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getSoftinstallList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 推广软件安装
	 */
	public List<TuiguanginstallEntity> getTuiguanginstallList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getTuiguanginstallList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBActivedaysdistributeEntity> getActivedaysdistributeList(
			ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getActivedaysdistributeList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBOnlinepkginnerinstallEntity> getOnlinepkginnerinstallList(
			ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getOnlinepkginnerinstallList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBOnlinepkginstallEntity> getOnlinepkginstallList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getOnlinepkginstallList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBPkginstallEntity> getPkginstallList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getPkginstallList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBRunpointEntity> getRunpointAddList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getRunpointAddList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBRunpointEntity> getRunpointList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getRunpointList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBRunpointEntity> getRunpointSearchList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getRunpointSearchList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
	
	public void deleteRunpointsoft(ZjbDTO dto){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "deleteRunpointsoft", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	public void addRunpointsoft(ZjbDTO dto){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "addRunpointsoft", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBSoftdownEntity> getSoftdownList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getSoftdownList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBSoftdowntopEntity> getSoftdowntopList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getSoftdowntopList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBUseractionEntity> getUseractionList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getUseractionList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public List<ZJBVersionanalyEntity> getVersionanalyList(ZjbDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					"com.nearme.statistics.dao.app.zjb.ZjbDao."
							+ "getVersionanalyList", dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}
}
