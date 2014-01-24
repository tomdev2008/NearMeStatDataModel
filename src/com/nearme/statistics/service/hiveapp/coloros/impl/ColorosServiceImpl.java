package com.nearme.statistics.service.hiveapp.coloros.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nearme.statistics.dao.hiveapp.coloros.ColorosDao;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.model.coloros.COSAvgstartcntrankEntity;
import com.nearme.statistics.model.coloros.COSInstallrankEntity;
import com.nearme.statistics.model.coloros.COSStartrankEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
import com.nearme.statistics.service.hiveapp.coloros.ColorosService;
import com.nearme.statistics.util.DateUtil;

public class ColorosServiceImpl implements ColorosService {
	private ColorosDao dao;

	public ColorosDao getDao() {
		return dao;
	}

	public void setDao(ColorosDao dao) {
		this.dao = dao;
	}

	public void queryAndInsertStartrankList(ColorosDTO dto, HivejobDTO hdto) {
		try {
			List<COSStartrankEntity> list = dao.hiveQueryStartrank(dto);
			dao.insertStartrank(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	public List<COSStartrankEntity> doGetStartrankList(HivejobDTO hdto) {
		return dao.getStartrank(hdto);
	}

	public void queryAndInsertAvgstartcntrankList(ColorosDTO dto,
			HivejobDTO hdto) {
		try {
			List<COSAvgstartcntrankEntity> list = dao
					.hiveQueryAvgstartcntrank(dto);
			dao.insertAvgstartcntrank(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	public List<COSAvgstartcntrankEntity> doGetAvgstartcntrankList(
			HivejobDTO hdto) {
		return dao.getAvgstartcntrank(hdto);
	}

	public void queryAndInsertInstallrankList(ColorosDTO dto, HivejobDTO hdto) {
		try {
			List<COSInstallrankEntity> list = dao.hiveQueryInstallrank(dto);
			dao.insertInstallrank(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	public List<COSInstallrankEntity> doGetInstallrankList(HivejobDTO hdto) {
		return dao.getInstallrank(hdto);
	}

	public List<HivesqlEntity> getHqlAvgstartcntrank(ColorosDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();

		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlAvgstartcntrank(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		list.add(entity);

		return list;
	}

	public List<HivesqlEntity> getHqlInstallrank(ColorosDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();

		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlInstallrank(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		list.add(entity);

		return list;
	}

	public List<HivesqlEntity> getHqlStartrank(ColorosDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();

		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlStartrank(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		list.add(entity);

		return list;
	}

	public void deleteAvgstartcntrank(HivejobDTO dto) {
		dao.deleteAvgstartcntrank(dto);
	}

	public void deleteInstallrank(HivejobDTO dto) {
		dao.deleteInstallrank(dto);
	}

	public void deleteStartrank(HivejobDTO dto) {
		dao.deleteStartrank(dto);
	}

	public List<COSStartrankEntity> getStartrankweekList(ColorosDTO dto) {
		List<COSStartrankEntity> list = dao.getStartrankweekList(dto);

		for (int i = 0; i < list.size(); i++) {
			COSStartrankEntity entity = list.get(i);
			Date statDate = entity.getStatDate();
			Date statEndDate = DateUtil.AddDays(statDate, 6);
			entity.setPosition("" + (i + 1));
			entity.setStatDate(statDate);
			entity.setStatEndDate(statEndDate);
		}

		return list;
	}
	
	public List<COSAvgstartcntrankEntity> getAvgstartrankweekList(ColorosDTO dto) {
		List<COSAvgstartcntrankEntity> list = dao.getAvgstartrankweekList(dto);

		for (int i = 0; i < list.size(); i++) {
			COSAvgstartcntrankEntity entity = list.get(i);
			Date statDate = entity.getStatDate();
			Date statEndDate = DateUtil.AddDays(statDate, 6);
			entity.setPosition("" + (i + 1));
			entity.setStatDate(statDate);
			entity.setStatEndDate(statEndDate);
		}
		return list;
	}

	public List<COSInstallrankEntity> getInstallrankweekList(ColorosDTO dto) {
		List<COSInstallrankEntity> list = dao.getInstallrankweekList(dto);

		for (int i = 0; i < list.size(); i++) {
			COSInstallrankEntity entity = list.get(i);
			Date statDate = entity.getStatDate();
			Date statEndDate = DateUtil.AddDays(statDate, 6);
			entity.setPosition("" + (i + 1));
			entity.setStatDate(statDate);
			entity.setStatEndDate(statEndDate);
		}

		return list;
	}
}
