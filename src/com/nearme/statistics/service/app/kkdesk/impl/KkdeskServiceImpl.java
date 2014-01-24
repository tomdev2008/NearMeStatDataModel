package com.nearme.statistics.service.app.kkdesk.impl;

import com.nearme.statistics.dao.app.kkdesk.KkdeskDao;
import com.nearme.statistics.service.app.kkdesk.KkdeskService;

public class KkdeskServiceImpl implements KkdeskService {
	private KkdeskDao dao;

	public KkdeskDao getDao() {
		return dao;
	}

	public void setDao(KkdeskDao dao) {
		this.dao = dao;
	}
}
