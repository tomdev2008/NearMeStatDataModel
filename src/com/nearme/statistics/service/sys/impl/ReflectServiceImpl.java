package com.nearme.statistics.service.sys.impl;

import com.nearme.statistics.dao.sys.ReflectDao;
import com.nearme.statistics.dto.ReflectDTO;
import com.nearme.statistics.service.sys.ReflectService;

public class ReflectServiceImpl implements ReflectService {
	private ReflectDao dao;

	public void setReflectInfo(ReflectDTO dto) {
		dao.setReflectInfo(dto);
	}

	public ReflectDao getDao() {
		return dao;
	}

	public void setDao(ReflectDao dao) {
		this.dao = dao;
	}
}
