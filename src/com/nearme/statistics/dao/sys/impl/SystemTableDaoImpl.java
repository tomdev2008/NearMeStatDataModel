package com.nearme.statistics.dao.sys.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nearme.statistics.dao.sys.SystemTableDao;
import com.nearme.statistics.dto.sys.AppInfoDTO;
import com.nearme.statistics.dto.sys.AppVersionDTO;
import com.nearme.statistics.dto.sys.CategoryDTO;
import com.nearme.statistics.dto.sys.ChannelDTO;
import com.nearme.statistics.dto.sys.GCProductDTO;
import com.nearme.statistics.dto.sys.NetworkTypeDTO;
import com.nearme.statistics.model.sys.systemTable.AppInfoEntity;
import com.nearme.statistics.model.sys.systemTable.AppVersionEntity;
import com.nearme.statistics.model.sys.systemTable.CategoryEntity;
import com.nearme.statistics.model.sys.systemTable.ChannelEntity;
import com.nearme.statistics.model.sys.systemTable.GCProductEntity;
import com.nearme.statistics.model.sys.systemTable.NetworkTypeEntity;

/**
 * 系统表维护DAO实现类
 *
 * @author 段锦涛
 * @author 刘超
 * @version 1.0
 * @since 1.0, 2012-8-6
 */
@SuppressWarnings("unchecked")
public class SystemTableDaoImpl implements SystemTableDao {
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private int addEntity(String func, Object dto){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.insert(
					"com.nearme.statistics.dao.sys.SystemTableDao." + func, dto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}
	private int deleteEntity(String func, Object dto){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.delete(
					"com.nearme.statistics.dao.sys.SystemTableDao." + func, dto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}
	private List<?> selectEntity(String func, Object dto){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectList(
					"com.nearme.statistics.dao.sys.SystemTableDao." + func, dto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	private int updateEntity(String func, Object dto){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.delete(
					"com.nearme.statistics.dao.sys.SystemTableDao." + func, dto);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}
	public int addAppInfo(AppInfoDTO dto) {
		return addEntity("addAppInfo", dto);
	}

	public int addAppVersion(AppVersionDTO dto) {
		return addEntity("addAppVersion", dto);
	}

	public int addCategory(CategoryDTO dto) {
		return addEntity("addCategory", dto);
	}

	public int addChannel(ChannelDTO dto) {
		return addEntity("addChannel", dto);
	}

	public int addGCProduct(GCProductDTO dto) {
		return addEntity("addGCProduct", dto);
	}

	public int addNetworkType(NetworkTypeDTO dto) {
		return addEntity("addNetworkType", dto);
	}

	public int deleteAppInfo(AppInfoDTO dto) {
		return deleteEntity("deleteAppInfo", dto);
	}

	public int deleteAppVersion(AppVersionDTO dto) {
		return deleteEntity("deleteAppVersion", dto);
	}

	public int deleteCategory(CategoryDTO dto) {
		return deleteEntity("deleteCategory", dto);
	}

	public int deleteChannel(ChannelDTO dto) {
		return deleteEntity("deleteChannel", dto);
	}

	public int deleteGCProduct(GCProductDTO dto) {
		return deleteEntity("deleteGCProduct", dto);
	}

	public int deleteNetworkType(NetworkTypeDTO dto) {
		return deleteEntity("deleteNetworkType", dto);
	}

	public List<AppInfoEntity> getAppInfoList(AppInfoDTO dto) {
		return (List<AppInfoEntity>) selectEntity("getAppInfoList", dto);
	}

	public List<AppVersionEntity> getAppVersionList(AppVersionDTO dto) {
		return (List<AppVersionEntity>) selectEntity("getAppVersionList", dto);
	}

	public List<CategoryEntity> getCategoryList(CategoryDTO dto) {
		return (List<CategoryEntity>) selectEntity("getCategoryList", dto);
	}

	public List<ChannelEntity> getChannelList(ChannelDTO dto) {
		return (List<ChannelEntity>) selectEntity("getChannelList", dto);
	}

	public List<GCProductEntity> getGCProductList(GCProductDTO dto) {
		return (List<GCProductEntity>) selectEntity("getGCProductList", dto);
	}

	public List<NetworkTypeEntity> getNetworkTypeList(NetworkTypeDTO dto) {
		return (List<NetworkTypeEntity>) selectEntity("getNetworkTypeList", dto);
	}

	public int updateAppInfo(AppInfoDTO dto) {
		return updateEntity("updateAppInfo", dto);
	}

	public int updateAppVersion(AppVersionDTO dto) {
		return updateEntity("updateAppVersion", dto);
	}

	public int updateCategory(CategoryDTO dto) {
		return updateEntity("updateCategory", dto);
	}

	public int updateChannel(ChannelDTO dto) {
		return updateEntity("updateChannel", dto);
	}

	public int updateGCProduct(GCProductDTO dto) {
		return updateEntity("updateGCProduct", dto);
	}

	public int updateNetworkType(NetworkTypeDTO dto) {
		return updateEntity("updateNetworkType", dto);
	}

}
