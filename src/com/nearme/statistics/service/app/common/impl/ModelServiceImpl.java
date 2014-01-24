package com.nearme.statistics.service.app.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.ModelDao;
import com.nearme.statistics.dto.app.common.ModelDTO;
import com.nearme.statistics.model.commonsetting.ModelEntity;
import com.nearme.statistics.service.app.common.ModelService;

public class ModelServiceImpl implements ModelService {
	private ModelDao dao;

	public ModelDao getDao() {
		return dao;
	}

	public void setDao(ModelDao dao) {
		this.dao = dao;
	}

	public List<ModelEntity> getModelList(ModelDTO dto) {
		return dao.getModelList(dto);
	}

	public List<ModelEntity> getModelmanageList(ModelDTO dto) {
		return dao.getModelmanageList(dto);
	}

	public void update(ModelDTO dto) {
		dao.update(dto);
	}
}
