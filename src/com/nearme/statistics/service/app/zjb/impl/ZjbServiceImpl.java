package com.nearme.statistics.service.app.zjb.impl;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.dao.app.zjb.ZjbDao;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.model.zjb.BasedateEntity;
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
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.model.zjb.ChannelserviceEntity;

public class ZjbServiceImpl implements ZjbService {
	private ZjbDao dao;

	public ZjbDao getDao() {
		return dao;
	}

	public void setDao(ZjbDao dao) {
		this.dao = dao;
	}

	/**
	 * 版本
	 */
	public List<ZjbVersionEntity> getZjbVersionList(ZjbDTO dto) {
		return dao.getZjbVersionList(dto);
	}

	/**
	 * 基础数据
	 */
	public List<BasedateEntity> getBasedateList(ZjbDTO dto) {
		List<BasedateEntity> list = dao.getBasedateList(dto);

		for (BasedateEntity entity : list) {
			long apks = entity.getApks();
			long conns = entity.getConns();
			long computers = entity.getComputers();

			float avguser = computers == 0 ? 0 : Math.round(apks * 100
					/ computers)
					/ (float) 100;
			float avgphone = conns == 0 ? 0 : Math.round(apks * 100 / conns)
					/ (float) 100;

			entity.setAvguser(avguser);
			entity.setAvgphone(avgphone);

			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

	/**
	 * 分渠道服务数据
	 */
	public List<ChannelserviceEntity> getChannelserviceList(ZjbDTO dto) {
		List<ChannelserviceEntity> list = dao.getChannelserviceList(dto);

		for (ChannelserviceEntity entity : list) {
			entity.setIn_not_oppo_avg(entity.getIn_not_oppo() == 0 ? 0 : Math.round(entity.getIn_not_oppo_apps() / entity.getIn_not_oppo()));
			entity.setIn_oppo_avg(entity.getIn_oppo() == 0 ? 0 : Math.round(entity.getIn_oppo_apps() / entity.getIn_oppo()));
			entity.setOut_not_oppo_avg(entity.getOut_not_oppo() == 0 ? 0 : Math.round(entity.getOut_not_oppo_apps() / entity.getOut_not_oppo()));
			entity.setOut_oppo_avg(entity.getOut_oppo() == 0 ? 0 : Math.round(entity.getOut_oppo_apps() / entity.getOut_oppo()));
		}

		return list;
	}

	/**
	 * 模块安装量
	 */
	public List<ModuleinstallEntity> getModuleinstallList(ZjbDTO dto) {
		List<ModuleinstallEntity> list = dao.getModuleinstallList(dto);

		for (ModuleinstallEntity entity : list) {
			long total = entity.getSoft();
			long online = entity.getOnline1();
			long self = entity.getSelf();
			long onlineSoft = entity.getOnlineSoft();
			long search = entity.getSearch();
			long upgrade = entity.getUpgrade();

			float onlineRatio = total == 0 ? 0 : Math.round(online * 10000
					/ total)
					/ (float) 100;
			float selfRatio = total == 0 ? 0 : Math.round(self * 10000 / total)
					/ (float) 100;
			float onlineSoftRatio = total == 0 ? 0 : Math.round(onlineSoft
					* 10000 / total)
					/ (float) 100;
			float searchRatio = total == 0 ? 0 : Math.round(search * 10000
					/ total)
					/ (float) 100;
			float upgradeRatio = total == 0 ? 0 : Math.round(upgrade * 10000
					/ total)
					/ (float) 100;

			entity.setOnlineRatio(onlineRatio);
			entity.setSelfRatio(selfRatio);
			entity.setOnlineSoftRatio(onlineSoftRatio);
			entity.setSearchRatio(searchRatio);
			entity.setUpgradeRatio(upgradeRatio);
		}

		return list;
	}

	/**
	 * 性能指标
	 */
	public List<PerformanceEntity> getPerformanceList(ZjbDTO dto) {
		List<PerformanceEntity> list = dao.getPerformanceList(dto);

		for (PerformanceEntity entity : list) {
			long connectCnt = entity.getConnectCnt();
			long connectSuccess = entity.getConnectSuccess();
			long downCnt = entity.getDownCnt();
			long downSuccess = entity.getDownSuccess();
			long installCnt = entity.getInstallCnt();
			long installSuccess = entity.getInstallSuccess();
			long softStart = entity.getSoftStart();
			long softCrash = entity.getSoftCrash();
			long restoreCnt = entity.getRestoreCnt();
			long restoreSuccess = entity.getRestoreSuccess();

			float connectRatio = connectCnt == 0 ? 0 : Math
					.round(connectSuccess * 10000 / connectCnt)
					/ (float) 100;
			float downRatio = downCnt == 0 ? 0 : Math.round(downSuccess * 10000
					/ downCnt)
					/ (float) 100;
			float installRatio = installCnt == 0 ? 0 : Math
					.round(installSuccess * 10000 / installCnt)
					/ (float) 100;
			float softCrashRatio = softStart == 0 ? 0 : Math.round(softCrash
					* 10000 / softStart)
					/ (float) 100;
			float restoreRatio = restoreCnt == 0 ? 0 : Math
					.round(restoreSuccess * 10000 / restoreCnt)
					/ (float) 100;

			entity.setConnectRatio(connectRatio);
			entity.setDownRatio(downRatio);
			entity.setInstallRatio(installRatio);
			entity.setSoftCrashRatio(softCrashRatio);
			entity.setRestoreRatio(restoreRatio);
		}

		return list;
	}

	/**
	 * 服务机型
	 */
	public List<ServermodelEntity> getServermodelList(ZjbDTO dto) {
		List<ServermodelEntity> list = dao.getServermodelList(dto);

		for (ServermodelEntity entity : list) {
			float zhanbi = entity.getZhanbi();
			float installratio = entity.getInstallratio();

			zhanbi = Math.round(zhanbi * 10000) / (float) 100;
			installratio = Math.round(installratio * 10000) / (float) 100;

			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				Date statEndDate = DateUtil.AddDays(statDate, 6);
				entity.setStatEndDate(statEndDate);
			}

			entity.setZhanbi(zhanbi);
			entity.setInstallratio(installratio);
		}
		return list;
	}

	/**
	 * 服务机型2
	 */
	public List<ServermodelEntity> getServermodelList2(ZjbDTO dto) {
		List<ServermodelEntity> list = dao.getServermodelList2(dto);

		for (ServermodelEntity entity : list) {
			float zhanbi = entity.getZhanbi();
			float installratio = entity.getInstallratio();

			zhanbi = Math.round(zhanbi * 10000) / (float) 100;
			installratio = Math.round(installratio * 10000) / (float) 100;

			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				Date statEndDate = DateUtil.AddDays(statDate, 6);
				entity.setStatEndDate(statEndDate);
			}

			entity.setZhanbi(zhanbi);
			entity.setInstallratio(installratio);
		}
		return list;
	}

	/**
	 * 软件安装量
	 */
	public List<SoftinstallEntity> getSoftinstallList(ZjbDTO dto) {
		List<SoftinstallEntity> list = dao.getSoftinstallList(dto);

		if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
			for (SoftinstallEntity entity : list) {
				Date statDate = entity.getStatDate();
				Date statEndDate = DateUtil.AddDays(statDate, 6);

				entity.setStatEndDate(statEndDate);
			}
		}

		return list;
	}

	/**
	 * 推广软件安装
	 */
	public List<TuiguanginstallEntity> getTuiguanginstallList(ZjbDTO dto) {
		return dao.getTuiguanginstallList(dto);
	}

	public List<ZJBActivedaysdistributeEntity> getActivedaysdistributeList(
			ZjbDTO dto) {
		List<ZJBActivedaysdistributeEntity> list = dao
				.getActivedaysdistributeList(dto);

		// 计算总安装量
		long totalinstallapp = 0;
		for (ZJBActivedaysdistributeEntity entity : list) {
			long installapp = entity.getInstallapp();
			totalinstallapp += installapp;
		}

		// 填充安装量占比
		for (ZJBActivedaysdistributeEntity entity : list) {
			long installapp = entity.getInstallapp();
			String activedays = entity.getActivedays();
			long usercnt = entity.getUsercnt();
			long servemobile = entity.getServemobile();
			float avgactdays = entity.getAvgactdays();

			long avginstaluser = usercnt == 0 ? 0 : installapp / usercnt;
			long avginstallmobel = servemobile == 0 ? 0 : installapp
					/ servemobile;

			// activedays转换
			if (null != activedays && activedays.contains("_")
					&& activedays.length() > 3) {
				activedays = activedays.substring(2);
			}

			float installratio = totalinstallapp == 0 ? 0 : Math
					.round(installapp * 10000 / totalinstallapp)
					/ (float) 100;
			avgactdays = Math.round(avgactdays * 100) / (float) 100;

			entity.setInstallratio(installratio);
			entity.setActivedays(activedays);
			entity.setAvginstaluser(avginstaluser);
			entity.setAvginstallmobel(avginstallmobel);
			entity.setAvgactdays(avgactdays);
		}

		return list;
	}

	public List<ZJBOnlinepkginnerinstallEntity> getOnlinepkginnerinstallList(
			ZjbDTO dto) {
		List<ZJBOnlinepkginnerinstallEntity> list = dao
				.getOnlinepkginnerinstallList(dto);

		for (ZJBOnlinepkginnerinstallEntity entity : list) {
			long innerinstallcnt = entity.getInnerinstallcnt();
			long installtotal = entity.getInstalltotal();

			float innerinstallratio = installtotal == 0 ? 0 : Math
					.round(innerinstallcnt * 10000 / installtotal)
					/ (float) 100;

			entity.setInnerinstallratio(innerinstallratio);
		}

		return list;
	}

	public List<ZJBOnlinepkginstallEntity> getOnlinepkginstallList(ZjbDTO dto) {
		List<ZJBOnlinepkginstallEntity> list = dao.getOnlinepkginstallList(dto);

		// 计算软件安装总量
		long installtotal = 0;
		for (ZJBOnlinepkginstallEntity entity : list) {
			long installcnt = entity.getInstallcnt();
			installtotal += installcnt;
		}

		for (ZJBOnlinepkginstallEntity entity : list) {
			long installcnt = entity.getInstallcnt();
			long innerpkgcnt = entity.getInnerpkgcnt();

			float installratio = installtotal == 0 ? 0 : Math.round(installcnt
					* 10000 / installtotal)
					/ (float) 100;
			long avginstall = innerpkgcnt == 0 ? 0 : installcnt / innerpkgcnt;

			entity.setInstalltotal(installtotal);
			entity.setInstallratio(installratio);
			entity.setAvginstall(avginstall);
		}

		return list;
	}

	public List<ZJBPkginstallEntity> getPkginstallList(ZjbDTO dto) {
		List<ZJBPkginstallEntity> list = dao.getPkginstallList(dto);

		for (ZJBPkginstallEntity entity : list) {
			long installtotal = entity.getInstalltotal();
			long keycnt = entity.getKeycnt();
			long selfcnt = entity.getSelfcnt();
			long onlinecnt = entity.getOnlinecnt();
			long innercnt = entity.getInnercnt();
			long selfinnercnt = entity.getSelfinnercnt();
			long onlineinnercnt = entity.getOnlineinnercnt();

			float keyratio = installtotal == 0 ? 0 : Math.round(keycnt * 10000
					/ installtotal)
					/ (float) 100;
			float selfratio = installtotal == 0 ? 0 : Math.round(selfcnt
					* 10000 / installtotal)
					/ (float) 100;
			float onlineratio = installtotal == 0 ? 0 : Math.round(onlinecnt
					* 10000 / installtotal)
					/ (float) 100;
			float innerratio = installtotal == 0 ? 0 : Math.round(innercnt
					* 10000 / installtotal)
					/ (float) 100;
			float selfinnerratio = installtotal == 0 ? 0 : Math
					.round(selfinnercnt * 10000 / installtotal)
					/ (float) 100;
			float onlineinnerratio = installtotal == 0 ? 0 : Math
					.round(onlineinnercnt * 10000 / installtotal)
					/ (float) 100;

			entity.setKeyratio(keyratio);
			entity.setSelfratio(selfratio);
			entity.setOnlineratio(onlineratio);
			entity.setInnerratio(innerratio);
			entity.setSelfinnerratio(selfinnerratio);
			entity.setOnlineinnerratio(onlineinnerratio);

			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

	public List<ZJBRunpointEntity> getRunpointAddList(ZjbDTO dto) {
		List<ZJBRunpointEntity> list = dao.getRunpointAddList(dto);

		for (ZJBRunpointEntity entity : list) {
			entity.setLidu(dto.getLidu());
		}

		return list;
	}

	public List<ZJBRunpointEntity> getRunpointList(ZjbDTO dto) {
		List<ZJBRunpointEntity> list = dao.getRunpointList(dto);

		for (ZJBRunpointEntity entity : list) {
			entity.setLidu(dto.getLidu());
		}

		return list;
	}

	public List<ZJBRunpointEntity> getRunpointSearchList(ZjbDTO dto) {
		List<ZJBRunpointEntity> list = dao.getRunpointSearchList(dto);

		for (ZJBRunpointEntity entity : list) {
			entity.setLidu(dto.getLidu());
		}

		return list;
	}

	public void deleteRunpointsoft(ZjbDTO dto) {
		dao.deleteRunpointsoft(dto);
	}

	public void addRunpointsoft(ZjbDTO dto) {
		dao.addRunpointsoft(dto);
	}

	public List<ZJBSoftdownEntity> getSoftdownList(ZjbDTO dto) {
		List<ZJBSoftdownEntity> list = dao.getSoftdownList(dto);

		for (ZJBSoftdownEntity entity : list) {
			long downtotal = entity.getDowntotal();
			long backupdatecnt = entity.getBackupdatecnt();
			long selfdown = entity.getSelfdown();
			long selfresdown = entity.getSelfresdown();

			float backupdateratio = downtotal == 0 ? 0 : Math
					.round(backupdatecnt * 10000 / downtotal)
					/ (float) 100;
			float selfdownratio = downtotal == 0 ? 0 : Math.round(selfdown
					* 10000 / downtotal)
					/ (float) 100;
			float selfresdownratio = downtotal == 0 ? 0 : Math
					.round(selfresdown * 10000 / downtotal)
					/ (float) 100;

			entity.setBackupdateratio(backupdateratio);
			entity.setSelfdownratio(selfdownratio);
			entity.setSelfresdownratio(selfresdownratio);

			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

	public List<ZJBSoftdowntopEntity> getSoftdowntopList(ZjbDTO dto) {
		List<ZJBSoftdowntopEntity> list = dao.getSoftdowntopList(dto);

		for (ZJBSoftdowntopEntity entity : list) {
			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

	public List<ZJBUseractionEntity> getUseractionList(ZjbDTO dto) {
		List<ZJBUseractionEntity> list = dao.getUseractionList(dto);

		for (ZJBUseractionEntity entity : list) {
			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

	public List<ZJBVersionanalyEntity> getVersionanalyList(ZjbDTO dto) {
		List<ZJBVersionanalyEntity> list = dao.getVersionanalyList(dto);

		// 计算总启动电脑数和启动用户数
		long totalcomp = 0;
		long totaluser = 0;
		for (ZJBVersionanalyEntity entity : list) {
			long startcomp = entity.getStartcomp();
			long startuser = entity.getStartuser();

			totalcomp += startcomp;
			totaluser += startuser;
		}

		for (ZJBVersionanalyEntity entity : list) {
			long startcomp = entity.getStartcomp();
			long startuser = entity.getStartuser();

			float startcompratio = totalcomp == 0 ? 0 : Math.round(startcomp
					* 10000 / totalcomp)
					/ (float) 100;
			float startuserratio = totaluser == 0 ? 0 : Math.round(startuser
					* 10000 / totaluser)
					/ (float) 100;

			entity.setStartuserratio(startuserratio);
			entity.setStartcompratio(startcompratio);
		}

		return list;
	}
}
