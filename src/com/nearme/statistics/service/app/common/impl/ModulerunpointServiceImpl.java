package com.nearme.statistics.service.app.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.ModulerunpointDao;
import com.nearme.statistics.dto.app.common.ModulerunpointDTO;
import com.nearme.statistics.model.commonsetting.ModulerunpointEntity;
import com.nearme.statistics.service.app.common.ModulerunpointService;

public class ModulerunpointServiceImpl implements ModulerunpointService{
	private ModulerunpointDao dao;
	
	public ModulerunpointDao getDao() {
		return dao;
	}

	public void setDao(ModulerunpointDao dao) {
		this.dao = dao;
	}

	public void add(ModulerunpointDTO dto) {
		dao.add(dto);
	}

	public void delete(ModulerunpointDTO dto) {
		dao.delete(dto);
	}

	public void update(ModulerunpointDTO dto) {
		dao.update(dto);
	}

	public List<ModulerunpointEntity> getModulerunpointList(
			ModulerunpointDTO dto) {
		return dao.getModulerunpointList(dto);
	}

	@Override
	public void sync2Hive(ModulerunpointDTO dto) {
		List<ModulerunpointEntity> list = getModulerunpointList(dto);
		dao.sync2Hive(list);
	}
}
