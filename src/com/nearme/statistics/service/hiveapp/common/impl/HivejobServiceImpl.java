package com.nearme.statistics.service.hiveapp.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.HivejobDao;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.model.common.HivejobEntity;
import com.nearme.statistics.service.hiveapp.common.HivejobService;

public class HivejobServiceImpl implements HivejobService {
	private HivejobDao dao;

	public HivejobDao getDao() {
		return dao;
	}

	public void setDao(HivejobDao dao) {
		this.dao = dao;
	}

	public long getHivejobCnt(HivejobDTO dto) {
		return dao.getHivejobCnt(dto);
	}

	public void addHivejob(HivejobDTO dto) {
		dao.addHivejob(dto);
	}

	public void finishHivejob(HivejobDTO dto) {
		dao.finishHivejob(dto);
	}

	public List<HivejobEntity> getHivejobList(HivejobDTO dto) {
		return dao.getHivejobList(dto);
	}

	public void deleteHivejob(HivejobDTO dto) {
		dao.deleteHivejob(dto);
	}
}
