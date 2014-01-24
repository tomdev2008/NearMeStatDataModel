package com.nearme.statistics.dao.app.store;

import java.util.List;

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

public interface StoreDao {
	// 细分用户客户端行为统计
	public List<DetailuserclientEntity> getDetailuserclientList(StoreDTO dto);

	// 首页推荐报表
	public List<HomepagerecommendEntity> getHomepagerecommendList(StoreDTO dto);

	// push推送报表
	public List<PushEntity> getPushList(StoreDTO dto);

	// 应用榜报表
	public List<AppEntity> getAppList(StoreDTO dto);

	// 游戏榜报表
	public List<GameEntity> getGameList(StoreDTO dto);

	// 相关推荐报表
	public List<TuijianEntity> getTuijianList(StoreDTO dto);

	// 搜索报表
	public List<SearchEntity> getSearchList(StoreDTO dto);

	// 下载有礼日报表
	public List<DowngiftEntity> getDowngiftList(StoreDTO dto);

	// 专题日报表
	public List<ZuantiEntity> getZuantiList(StoreDTO dto);

	// 活动中心日报表
	public List<ActivecenterEntity> getActivecenterList(StoreDTO dto);
	
	// 装机必备
	public List<InstallmustEntity> getInstallmustList(StoreDTO dto);
	
	// 最近流行
	public List<PoprecentEntity> getPoprecentList(StoreDTO dto);
	
	// 分类
	public List<SortEntity> getSortList(StoreDTO dto);
	
	// 客户端内容分布
	public List<ClientcontentEntity> getClientcontentList(StoreDTO dto);
	
	// 小编推荐
	public List<StoreEditorrecommendEntity> getEditorrecommendList(StoreDTO dto);
	// 48小时热搜榜
	public List<StoreHotsearchEntity> getHotsearchList(StoreDTO dto);
	// 分类精选
	public List<StoreTypeselectEntity> getTypeselectList(StoreDTO dto);
	
	// 下载安装成功率
	public List<DownInstallEntity> listDownInstall(StoreDTO dto);
	
	// 总体流量分布
	public List<InstallStatEntity> listInstallTotal(StoreDTO dto);
	// top100渠道分布
	public List<InstallStatEntity> listInstallChannelTop(StoreDTO dto);
	// 某单个渠道的流量情况
	public List<InstallStatEntity> listInstallByChannel(StoreDTO dto);
	// 单竞品分析(列表)
	public List<InstallStatEntity> listInstallFromApp(StoreDTO dto);
	// 单竞品分析(详情)
	public List<InstallStatEntity> listAppSource(StoreDTO dto);
	
}
