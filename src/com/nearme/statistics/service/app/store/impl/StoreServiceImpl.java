package com.nearme.statistics.service.app.store.impl;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.Constants;
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
import com.nearme.statistics.service.app.store.StoreService;
import com.nearme.statistics.util.DateUtil;

public class StoreServiceImpl implements StoreService {
	private StoreDao dao;

	/**
	 * 细分用户客户端行为统计
	 */
	public List<DetailuserclientEntity> getDetailuserclientList(StoreDTO dto) {
		List<DetailuserclientEntity> list = dao.getDetailuserclientList(dto);

		for (DetailuserclientEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long downCnt = entity.getDownCnt();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(downCnt
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(downCnt
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 首页推荐日报表
	 */
	public List<HomepagerecommendEntity> getHomepagerecommendList(StoreDTO dto) {
		List<HomepagerecommendEntity> list = dao.getHomepagerecommendList(dto);

		for (HomepagerecommendEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * push推送报表
	 */
	public List<PushEntity> getPushList(StoreDTO dto) {
		List<PushEntity> list = dao.getPushList(dto);

		for (PushEntity entity : list) {
			long arriveImei = entity.getArriveImei();
			long sendPushCnt = entity.getSendPushCnt();

			float arriveRatio = sendPushCnt == 0 ? 0 : Math.round(arriveImei
					* 100 / sendPushCnt)
					/ (float) 100;

			entity.setArriveRatio(arriveRatio);
		}

		return list;
	}

	/**
	 * 应用榜报表
	 */
	public List<AppEntity> getAppList(StoreDTO dto) {
		List<AppEntity> list = dao.getAppList(dto);

		for (AppEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 游戏榜报表
	 */
	public List<GameEntity> getGameList(StoreDTO dto) {
		List<GameEntity> list = dao.getGameList(dto);

		for (GameEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 相关推荐报表
	 */
	public List<TuijianEntity> getTuijianList(StoreDTO dto) {
		List<TuijianEntity> list = dao.getTuijianList(dto);

		for (TuijianEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 搜索报表
	 */
	public List<SearchEntity> getSearchList(StoreDTO dto) {
		List<SearchEntity> list = dao.getSearchList(dto);

		for (SearchEntity entity : list) {
			long totalUser = entity.getTotalUser();
			long downTotaluser = entity.getDownTotalUser();

			long notDownUser = totalUser - downTotaluser;

			entity.setNotDownUser(notDownUser);
		}

		return list;
	}

	/**
	 * 下载有礼日报表
	 */
	public List<DowngiftEntity> getDowngiftList(StoreDTO dto) {
		List<DowngiftEntity> list = dao.getDowngiftList(dto);

		for (DowngiftEntity entity : list) {
			long downCnt = entity.getDownCnt();
			long liulanCnt = entity.getLiulanCnt();

			float liulanDownRatio = downCnt == 0 ? 0 : Math.round(liulanCnt
					* 100 / downCnt)
					/ (float) 100;

			entity.setLiulanDownRatio(liulanDownRatio);
		}

		return list;
	}

	/**
	 * 专题日报表
	 */
	public List<ZuantiEntity> getZuantiList(StoreDTO dto) {
		List<ZuantiEntity> list = dao.getZuantiList(dto);

		for (ZuantiEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 活动中心日报表
	 */
	public List<ActivecenterEntity> getActivecenterList(StoreDTO dto) {
		List<ActivecenterEntity> list = dao.getActivecenterList(dto);

		for (ActivecenterEntity entity : list) {
			long downCnt = entity.getDownCnt();
			long liulanCnt = entity.getLiulanCnt();

			float liulanDownRatio = downCnt == 0 ? 0 : Math.round(liulanCnt
					* 100 / downCnt)
					/ (float) 100;

			entity.setLiulanDownRatio(liulanDownRatio);
		}

		return list;
	}

	/**
	 * 装机必备
	 */
	public List<InstallmustEntity> getInstallmustList(StoreDTO dto) {
		List<InstallmustEntity> list = dao.getInstallmustList(dto);

		for (InstallmustEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	public StoreDao getDao() {
		return dao;
	}

	public void setDao(StoreDao dao) {
		this.dao = dao;
	}

	/**
	 * 最近流行
	 */
	public List<PoprecentEntity> getPoprecentList(StoreDTO dto) {
		List<PoprecentEntity> list = dao.getPoprecentList(dto);

		for (PoprecentEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 分类
	 */
	public List<SortEntity> getSortList(StoreDTO dto) {
		List<SortEntity> list = dao.getSortList(dto);

		for (SortEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 客户端内容分布
	 */
	public List<ClientcontentEntity> getClientcontentList(StoreDTO dto) {
		List<ClientcontentEntity> list = dao.getClientcontentList(dto);

		for (ClientcontentEntity entity : list) {
			long app = entity.getApp();
			long appupdate = entity.getAppupdate();
			long game = entity.getGame();
			long gameupdate = entity.getGameupdate();

			long appeffect = app - appupdate;
			long gameeffect = game - gameupdate;

			entity.setAppeffect(appeffect);
			entity.setGameeffect(gameeffect);
		}

		return list;
	}

	/**
	 * 下载安装成功率
	 */
	public List<DownInstallEntity> listDownInstall(StoreDTO dto) {
		List<DownInstallEntity> list = null;
		try{
			list = dao.listDownInstall(dto);
			if(list==null || list.size()==0){
				return null;
			}
			float downSuccessRate = 0;
			float downFailRate = 0;
			float installSuccessRate = 0;
			for (DownInstallEntity entity : list) {
				long downRequest = entity.getDownRequest();
				long downSuccess = entity.getDownSuccess();
				long downFail = entity.getDownFail();
				long install = entity.getInstall();
				long installSuccess = entity.getInstallSuccess();
				//计算成功率 保留2位小数
				downSuccessRate = downRequest<=0 ? 0 : Math.round(downSuccess*10000/downRequest)/100;
				downFailRate = downRequest<=0 ? 0 : Math.round(downFail*10000/downRequest)/100;
				installSuccessRate = install<=0 ? 0 : Math.round(installSuccess*10000/install)/100;
				entity.setDownSuccessRate(downSuccessRate);
				entity.setDownFailRate(downFailRate);
				entity.setInstallSuccessRate(installSuccessRate);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}

		return list;
	}

	/**
	 * 单竞品分析(详情)
	 */
	public List<InstallStatEntity> listAppSource(StoreDTO dto) {
		List<InstallStatEntity> list = null;
		try{
			list = dao.listAppSource(dto);
			if(list==null || list.size()==0){
				return null;
			}
			long sum = 0;
			for (InstallStatEntity entity : list) {
				sum +=entity.getInstallTimes();
			}
			
			for (InstallStatEntity entity : list) {
				entity.setAvgInstall(sum<=0 ? "0" : String.format("%.2f", entity.getInstallTimes()*100.0/sum));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}

		return list;
	}

	/**
	 * 某单个渠道的流量情况
	 */
	public List<InstallStatEntity> listInstallByChannel(StoreDTO dto) {
		List<InstallStatEntity> list = null;
		try{
			list = dao.listInstallByChannel(dto);
			if(list==null || list.size()==0){
				return null;
			}
			for (InstallStatEntity entity : list) {
				entity.setAvgInstall(String.format("%.2f", Float.parseFloat(entity.getAvgInstall())));
				entity.setStatDateStr(DateUtil.getFormatResult(String.valueOf(entity.getStatDate()), "yyyyMMdd", dto.getLidu()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}

		return list;
	}

	/**
	 * top100渠道分布
	 */
	public List<InstallStatEntity> listInstallChannelTop(StoreDTO dto) {
		List<InstallStatEntity> list = null;
		try{
			if(Constants.WEEKLY.equals(dto.getLidu())){
				Date statDate = DateUtil.parseDate(String.valueOf(dto.getStatDateInt()), "yyyyMMdd");
				dto.setStatDateInt(Integer.parseInt(DateUtil.getMonday(statDate, "yyyyMMdd")));
			}else if(Constants.MONTHLY.equals(dto.getLidu())){
				dto.setStatDateInt(Integer.parseInt(String.valueOf(dto.getStatDateInt()).substring(0, 6)+"01"));
			}
			list = dao.listInstallChannelTop(dto);
			if(list==null || list.size()==0){
				return null;
			}
			int i = 1;
			for (InstallStatEntity entity : list) {
				entity.setRank(i++);
				entity.setAvgInstall(String.format("%.2f", Float.parseFloat(entity.getAvgInstall())));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}

		return list;
	}

	/**
	 * 单竞品分析(列表) 
	 */
	public List<InstallStatEntity> listInstallFromApp(StoreDTO dto) {
		List<InstallStatEntity> list = null;
		try{
			list = dao.listInstallFromApp(dto);
			if(list==null || list.size()==0){
				return null;
			}
			for (InstallStatEntity entity : list) {
				entity.setStatDateStr(DateUtil.getFormatResult(String.valueOf(entity.getStatDate()), "yyyyMMdd", dto.getLidu()));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}

		return list;
	}

	/**
	 * 总体流量分布
	 */
	public List<InstallStatEntity> listInstallTotal(StoreDTO dto) {
		List<InstallStatEntity> list = null;
		try{
			list = dao.listInstallTotal(dto);
			if(list==null || list.size()==0){
				return null;
			}
			for (InstallStatEntity entity : list) {
				entity.setAvgInstall(String.format("%.2f", Float.parseFloat(entity.getAvgInstall())));
				entity.setStatDateStr(DateUtil.getFormatResult(String.valueOf(entity.getStatDate()), "yyyyMMdd", dto.getLidu()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}

		return list;
	}

	/**
	 * 小编推荐
	 */
	public List<StoreEditorrecommendEntity> getEditorrecommendList(StoreDTO dto) {
		List<StoreEditorrecommendEntity> list = dao.getEditorrecommendList(dto);
		List<HomepagerecommendEntity> startlist =  dao.getHomepagerecommendList(dto);

		for (StoreEditorrecommendEntity entity : list) {
			Date statDate = entity.getStatDate();
			for (HomepagerecommendEntity hentity : startlist) {
				Date hdate = hentity.getStatDate();
				if (DateUtil.formatDate(hdate, "yyyyMMdd").equals(DateUtil.formatDate(statDate, "yyyyMMdd"))) {
					long startUser = hentity.getStartUser();
					long downUser = entity.getDownUser();
					long moduleDown = entity.getModuleDown();

					float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
							* 10000 / startUser)
							/ (float) 100;
					float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
							* 100 / startUser)
							/ (float) 100;
					float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
							* 100 / downUser)
							/ (float) 100;

					entity.setStartUser(startUser);
					entity.setDownStartRatio(downStartRatio);
					entity.setAvgStartUserDown(avgStartUserDown);
					entity.setAvgDownUserDown(avgDownUserDown);
				}
			}
		}

		return list;
	}

	/**
	 * 48小时热搜榜
	 */
	public List<StoreHotsearchEntity> getHotsearchList(StoreDTO dto) {
		List<StoreHotsearchEntity> list = dao.getHotsearchList(dto);

		for (StoreHotsearchEntity entity : list) {
			long startUser = entity.getStartUser();
			long downUser = entity.getDownUser();
			long moduleDown = entity.getModuleDown();

			float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
					* 10000 / startUser)
					/ (float) 100;
			float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
					* 100 / startUser)
					/ (float) 100;
			float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
					* 100 / downUser)
					/ (float) 100;

			entity.setDownStartRatio(downStartRatio);
			entity.setAvgStartUserDown(avgStartUserDown);
			entity.setAvgDownUserDown(avgDownUserDown);
		}

		return list;
	}

	/**
	 * 分类精选
	 */
	public List<StoreTypeselectEntity> getTypeselectList(StoreDTO dto) {
		List<StoreTypeselectEntity> list = dao.getTypeselectList(dto);
		List<SortEntity> startlist =  dao.getSortList(dto);
		for (StoreTypeselectEntity entity : list) {
			Date statDate = entity.getStatDate();
			for (SortEntity hentity : startlist) {
				Date hdate = hentity.getStatDate();
				if (DateUtil.formatDate(hdate, "yyyyMMdd").equals(DateUtil.formatDate(statDate, "yyyyMMdd"))) {
					long startUser = hentity.getStartUser();
					long downUser = entity.getDownUser();
					long moduleDown = entity.getModuleDown();

					float downStartRatio = startUser == 0 ? 0 : Math.round(downUser
							* 10000 / startUser)
							/ (float) 100;
					float avgStartUserDown = startUser == 0 ? 0 : Math.round(moduleDown
							* 100 / startUser)
							/ (float) 100;
					float avgDownUserDown = downUser == 0 ? 0 : Math.round(moduleDown
							* 100 / downUser)
							/ (float) 100;

					entity.setStartUser(startUser);
					entity.setDownStartRatio(downStartRatio);
					entity.setAvgStartUserDown(avgStartUserDown);
					entity.setAvgDownUserDown(avgDownUserDown);
				}
			}
		}

		return list;
	}
}
