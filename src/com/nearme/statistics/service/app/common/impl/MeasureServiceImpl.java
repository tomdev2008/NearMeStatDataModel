package com.nearme.statistics.service.app.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.MeasureDao;
import com.nearme.statistics.dto.app.common.MeasureDTO;
import com.nearme.statistics.model.commonsetting.MeasureEntity;
import com.nearme.statistics.service.app.common.MeasureService;

public class MeasureServiceImpl implements MeasureService {
	private MeasureDao dao;

	public MeasureDao getDao() {
		return dao;
	}

	public void setDao(MeasureDao dao) {
		this.dao = dao;
	}

	public List<MeasureEntity> getMeasureList(MeasureDTO dto) {
		return dao.getMeasureList(dto);
	}

}
