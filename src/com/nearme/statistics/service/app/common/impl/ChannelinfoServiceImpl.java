package com.nearme.statistics.service.app.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.ChannelinfoDao;
import com.nearme.statistics.dto.app.common.ChannelinfoDTO;
import com.nearme.statistics.model.commonsetting.ChannelinfoEntity;
import com.nearme.statistics.service.app.common.ChannelinfoService;

public class ChannelinfoServiceImpl implements ChannelinfoService {
	private ChannelinfoDao dao;

	public ChannelinfoDao getDao() {
		return dao;
	}

	public void setDao(ChannelinfoDao dao) {
		this.dao = dao;
	}

	/**
	 * 添加
	 */
	public void add(ChannelinfoDTO dto) {
		dao.add(dto);
	}

	/**
	 * 删除
	 */
	public void delete(ChannelinfoDTO dto) {
		dao.delete(dto);
	}

	/**
	 * 查询
	 */
	public List<ChannelinfoEntity> getChnnelinfoList(ChannelinfoDTO dto) {
		return dao.getChnnelinfoList(dto);
	}

	/**
	 * 更新
	 */
	public void update(ChannelinfoDTO dto) {
		dao.update(dto);
	}
}
