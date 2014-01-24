package com.nearme.statistics.dao.sys;

import java.util.List;

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
 * 系统表维护DAO
 *
 * @author 段锦涛
 * @author 刘超
 * @author 80053813
 * @version 1.0
 * @since 1.0, 2012-8-6
 */
public interface SystemTableDao {
	
	//应用:增删查改
	List<AppInfoEntity> getAppInfoList(AppInfoDTO dto);
	int addAppInfo(AppInfoDTO dto);
	int updateAppInfo(AppInfoDTO dto);
	int deleteAppInfo(AppInfoDTO dto);
	
	//渠道:增删查改
	List<ChannelEntity> getChannelList(ChannelDTO dto);
	int addChannel(ChannelDTO dto);
	int updateChannel(ChannelDTO dto);
	int deleteChannel(ChannelDTO dto);
	
	//版本:增删查改
	List<AppVersionEntity> getAppVersionList(AppVersionDTO dto);
	int addAppVersion(AppVersionDTO dto);
	int updateAppVersion(AppVersionDTO dto);
	int deleteAppVersion(AppVersionDTO dto);
	
	//资源分类:增删查改
	List<CategoryEntity> getCategoryList(CategoryDTO dto);
	int addCategory(CategoryDTO dto);
	int updateCategory(CategoryDTO dto);
	int deleteCategory(CategoryDTO dto);
	
	//网络:增删查改
	List<NetworkTypeEntity> getNetworkTypeList(NetworkTypeDTO dto);
	int addNetworkType(NetworkTypeDTO dto);
	int updateNetworkType(NetworkTypeDTO dto);
	int deleteNetworkType(NetworkTypeDTO dto);
	
	//游戏中心产品:增删查改
	List<GCProductEntity> getGCProductList(GCProductDTO dto);
	int addGCProduct(GCProductDTO dto);
	int updateGCProduct(GCProductDTO dto);
	int deleteGCProduct(GCProductDTO dto);
	

}
