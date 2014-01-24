package com.nearme.statistics.service.app.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.ModuleDao;
import com.nearme.statistics.dto.app.common.ModuleDTO;
import com.nearme.statistics.model.commonsetting.ModuleEntity;
import com.nearme.statistics.service.app.common.ModuleService;

public class ModuleServiceImpl implements ModuleService {
	private ModuleDao dao;

	public ModuleDao getDao() {
		return dao;
	}

	public void setDao(ModuleDao dao) {
		this.dao = dao;
	}

	public void add(ModuleDTO dto) {
		dao.add(dto);
	}

	public void deleteDetail(ModuleDTO dto) {
		dao.deleteDetail(dto);
	}

	public void deleteGroup(ModuleDTO dto) {
		dao.deleteGroup(dto);
	}

	public List<ModuleEntity> getDetailList(ModuleDTO dto) {
		return dao.getDetailList(dto);
	}

	public List<ModuleEntity> getGroupList(ModuleDTO dto) {
		return dao.getGroupList(dto);
	}
	
	public List<ModuleEntity> getModuleList(ModuleDTO dto) {
		return dao.getModuleList(dto);
	}
	
	public List<ModuleEntity> getModuleAllList() {
		return dao.getModuleAllList();
	}

	public void updateDetail(ModuleDTO dto) {
		dao.updateDetail(dto);
	}

	public void updateGroup(ModuleDTO dto) {
		dao.updateGroup(dto);
	}
	
	public void sync2Hive() {
		List<ModuleEntity> list = getModuleAllList();
		dao.sync2Hive(list);
	}
}
