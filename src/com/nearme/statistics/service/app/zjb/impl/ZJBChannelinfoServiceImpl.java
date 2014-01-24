package com.nearme.statistics.service.app.zjb.impl;

import java.util.List;

import com.nearme.statistics.dao.app.zjb.ZjbChannelinfoDao;
import com.nearme.statistics.dto.app.zjb.ZJBChannelinfoDTO;
import com.nearme.statistics.model.zjbsetting.ZJBChannelinfoEntity;
import com.nearme.statistics.service.app.zjb.ZJBChannelinfoService;

public class ZJBChannelinfoServiceImpl implements ZJBChannelinfoService {
	private ZjbChannelinfoDao dao;

	public ZjbChannelinfoDao getDao() {
		return dao;
	}

	public void setDao(ZjbChannelinfoDao dao) {
		this.dao = dao;
	}

	/**
	 * 添加
	 */
	public void add(ZJBChannelinfoDTO dto) {
		dao.add(dto);
	}

	/**
	 * 删除
	 */
	public void delete(ZJBChannelinfoDTO dto) {
		dao.delete(dto);
	}

	/**
	 * 查询
	 */
	public List<ZJBChannelinfoEntity> getZJBChnnelinfoList(ZJBChannelinfoDTO dto) {
		return dao.getZJBChnnelinfoList(dto);
	}

	/**
	 * 更新
	 */
	public void update(ZJBChannelinfoDTO dto) {
		dao.update(dto);
	}
}
