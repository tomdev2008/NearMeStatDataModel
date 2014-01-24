package com.nearme.statistics.service.app.zjb.impl;

import java.util.List;

import com.nearme.statistics.dao.app.zjb.ZjbUseractioncodeDao;
import com.nearme.statistics.dto.app.zjb.ZJBUseractioncodeDTO;
import com.nearme.statistics.model.zjbsetting.ZJBUseractioncodeEntity;
import com.nearme.statistics.service.app.zjb.ZJBUseractioncodeService;

public class ZJBUseractioncodeServiceImpl implements ZJBUseractioncodeService {
	private ZjbUseractioncodeDao dao;
	
	public ZjbUseractioncodeDao getDao() {
		return dao;
	}

	public void setDao(ZjbUseractioncodeDao dao) {
		this.dao = dao;
	}

	public void add(ZJBUseractioncodeDTO dto) {
		dao.add(dto);
	}

	public void delete(ZJBUseractioncodeDTO dto) {
		dao.delete(dto);
	}

	public List<ZJBUseractioncodeEntity> getZJBUseractioncodeList(
			ZJBUseractioncodeDTO dto) {
		return dao.getZJBUseractioncodeList(dto);
	}

	public void update(ZJBUseractioncodeDTO dto) {
		dao.update(dto);
	}
}
