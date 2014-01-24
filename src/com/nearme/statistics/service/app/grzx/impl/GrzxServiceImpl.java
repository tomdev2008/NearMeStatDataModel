package com.nearme.statistics.service.app.grzx.impl;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.dao.app.grzx.GrzxDao;
import com.nearme.statistics.dto.app.grzx.GrzxDTO;
import com.nearme.statistics.model.grzx.GrzxNewfromEntity;
import com.nearme.statistics.model.grzx.GrzxRegisterfromEntity;
import com.nearme.statistics.model.grzx.GrzxUsercoverEntity;
import com.nearme.statistics.service.app.grzx.GrzxService;
import com.nearme.statistics.util.DateUtil;

public class GrzxServiceImpl implements GrzxService {
	private GrzxDao dao;

	public GrzxDao getDao() {
		return dao;
	}

	public void setDao(GrzxDao dao) {
		this.dao = dao;
	}

	/**
	 * 新增来源
	 */
	public List<GrzxNewfromEntity> getNewfromList(GrzxDTO dto) {
		List<GrzxNewfromEntity> list = dao.getNewfromList(dto);

		long totalnewuser = 0;
		for (GrzxNewfromEntity entity : list) {
			totalnewuser += entity.getNewuser();
		}

		for (GrzxNewfromEntity entity : list) {
			long newuser = entity.getNewuser();

			float newratio = totalnewuser == 0 ? 0 : Math.round(newuser * 10000
					/ (float) totalnewuser)
					/ (float) 100;

			entity.setNewratio(newratio);
			entity.setTotalnewuser(totalnewuser);
		}

		return list;
	}

	/**
	 * 注册来源
	 */
	public List<GrzxRegisterfromEntity> getRegisterfromList(GrzxDTO dto) {
		List<GrzxRegisterfromEntity> list  = dao.getRegisterfromList(dto);
		
		for (GrzxRegisterfromEntity entity : list) {
			long mobilereg = entity.getMobilereg();
			long emilereg = entity.getEmailreg();
			long nobandreg = entity.getNobandreg();
			long visitreg = entity.getVisitreg();
			long thirdreg = entity.getThirdreg();
			
			entity.setAllreg(mobilereg + emilereg + nobandreg + visitreg + thirdreg);
			
			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}
		return list;
	}

	/**
	 * 账号覆盖率
	 */
	public List<GrzxUsercoverEntity> getUsercoverList(GrzxDTO dto) {
		List<GrzxUsercoverEntity> list = dao.getUsercoverList(dto);

		for (GrzxUsercoverEntity entity : list) {
			long startImei = entity.getStartImei();
			long startUser = entity.getStartUser();

			float coverratio = startImei == 0 ? 0 : Math.round(startUser
					* 10000 / (float) startImei)
					/ (float) 100;

			entity.setCoverratio(coverratio);
			
			if ("WEEKLY".equalsIgnoreCase(dto.getLidu())) {
				Date statDate = entity.getStatDate();
				entity.setStatEndDate(DateUtil.AddDays(statDate, 6));
			}
		}

		return list;
	}

}
