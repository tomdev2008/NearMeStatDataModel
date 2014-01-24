package com.nearme.statistics.service.app.common.impl;

import java.util.List;

import com.nearme.statistics.dao.app.common.UseractionDao;
import com.nearme.statistics.dto.app.common.UseractionDTO;
import com.nearme.statistics.model.commonsetting.UseractionEntity;
import com.nearme.statistics.service.app.common.UseractionService;

public class UseractionServiceImpl implements UseractionService {
	private UseractionDao dao;

	public UseractionDao getDao() {
		return dao;
	}

	public void setDao(UseractionDao dao) {
		this.dao = dao;
	}

	/**
	 * 查询行为编码
	 */
	public List<UseractionEntity> getUseractionList(UseractionDTO dto) {
		return dao.getUseractionList(dto);
	}

	/**
	 * 查询分组编码
	 */
	public List<UseractionEntity> getUsergroupList(UseractionDTO dto) {
		return dao.getUsergroupList(dto);
	}

	/**
	 * 删除行为编码
	 */
	public void deleteActioncode(UseractionDTO dto) {
		dao.deleteActioncode(dto);
	}

	/**
	 * 删除分组编码
	 */
	public void deleteGroupcode(UseractionDTO dto) {
		dao.deleteGroupcode(dto);
	}

	/**
	 * 更新行为编码
	 */
	public void updateActioncode(UseractionDTO dto) {
		dao.updateActioncode(dto);
	}

	/**
	 * 更新分组编码
	 */
	public void updateGroupcode(UseractionDTO dto) {
		dao.updateGroupcode(dto);
	}

	/**
	 * 插入行为编码
	 */
	public void insertActioncode(UseractionDTO dto) {
		dao.insertActioncode(dto);
		
	}

	/**
	 * 插入分组编码
	 */
	public void insertGroupcode(UseractionDTO dto) {
		dao.insertGroupcode(dto);
	}
}
