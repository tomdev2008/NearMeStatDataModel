package com.nearme.statistics.service.sys.impl;

import com.nearme.statistics.dao.sys.PersonInfoDao;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.service.sys.PersonInfoService;

public class PersonInfoServiceImpl implements PersonInfoService {
	
	private PersonInfoDao dao;
	
	public void setDao(PersonInfoDao dao) {
		this.dao = dao;
	}

	public int updateUserInfo(Admin admin) {
		// TODO Auto-generated method stub
		return dao.updateUserInfo(admin);
	}

	public int updateUserPasswd(Admin admin) {
		// TODO Auto-generated method stub
		return dao.updateUserPasswd(admin);
	}

}
