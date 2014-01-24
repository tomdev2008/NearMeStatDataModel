package com.nearme.statistics.service.sys.impl;

import java.util.List;

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
import com.nearme.statistics.service.sys.SystemTableService;

/**
 * @author 段锦涛
 * @version 1.0
 * @since 1.0, 2012-8-6
 */
public class SystemTableServiceImpl implements SystemTableService {
	private SystemTableDao dao;

	public void setDao(SystemTableDao dao) {
		this.dao = dao;
	}

	public int addAppInfo(AppInfoDTO dto) {
		return dao.addAppInfo(dto);
	}

	public int addAppVersion(AppVersionDTO dto) {
		return dao.addAppVersion(dto);
	}

	public int addCategory(CategoryDTO dto) {
		return dao.addCategory(dto);
	}

	public int addChannel(ChannelDTO dto) {
		return dao.addChannel(dto);
	}

	public int addGCProduct(GCProductDTO dto) {
		return dao.addGCProduct(dto);
	}

	public int addNetworkType(NetworkTypeDTO dto) {
		return dao.addNetworkType(dto);
	}

	public int deleteAppInfo(AppInfoDTO dto) {
		return dao.deleteAppInfo(dto);
	}

	public int deleteAppVersion(AppVersionDTO dto) {
		return dao.deleteAppVersion(dto);
	}

	public int deleteCategory(CategoryDTO dto) {
		return dao.deleteCategory(dto);
	}

	public int deleteChannel(ChannelDTO dto) {
		return dao.deleteChannel(dto);
	}

	public int deleteGCProduct(GCProductDTO dto) {
		return dao.deleteGCProduct(dto);
	}

	public int deleteNetworkType(NetworkTypeDTO dto) {
		return dao.deleteNetworkType(dto);
	}

	public List<AppInfoEntity> getAppInfoList(AppInfoDTO dto) {
		return dao.getAppInfoList(dto);
	}

	public List<AppVersionEntity> getAppVersionList(AppVersionDTO dto) {
		return dao.getAppVersionList(dto);
	}

	public List<CategoryEntity> getCategoryList(CategoryDTO dto) {
		return dao.getCategoryList(dto);
	}

	public List<ChannelEntity> getChannelList(ChannelDTO dto) {
		return dao.getChannelList(dto);
	}

	public List<GCProductEntity> getGCProductList(GCProductDTO dto) {
		return dao.getGCProductList(dto);
	}

	public List<NetworkTypeEntity> getNetworkTypeList(NetworkTypeDTO dto) {
		return dao.getNetworkTypeList(dto);
	}

	public int updateAppInfo(AppInfoDTO dto) {
		return dao.updateAppInfo(dto);
	}

	public int updateAppVersion(AppVersionDTO dto) {
		return dao.updateAppVersion(dto);
	}

	public int updateCategory(CategoryDTO dto) {
		return dao.updateCategory(dto);
	}

	public int updateChannel(ChannelDTO dto) {
		return dao.updateChannel(dto);
	}

	public int updateGCProduct(GCProductDTO dto) {
		return dao.updateGCProduct(dto);
	}

	public int updateNetworkType(NetworkTypeDTO dto) {
		return dao.updateNetworkType(dto);
	}

}
