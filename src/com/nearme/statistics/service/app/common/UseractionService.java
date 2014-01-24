package com.nearme.statistics.service.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.UseractionDTO;
import com.nearme.statistics.model.commonsetting.UseractionEntity;

public interface UseractionService {
	// 用户行为编码-查询分组信息
	public List<UseractionEntity> getUsergroupList(UseractionDTO dto);

	// 用户行为编码-删除分组信息
	public void deleteGroupcode(UseractionDTO dto);

	// 用户行为编码-修改分组信息
	public void updateGroupcode(UseractionDTO dto);
	
	// 用户行为编码-插入分组信息
	public void insertGroupcode(UseractionDTO dto);
	
	// 用户行为编码-查询具体行为信息
	public List<UseractionEntity> getUseractionList(UseractionDTO dto);
	
	// 用户行为编码-删除行为信息
	public void deleteActioncode(UseractionDTO dto);

	// 用户行为编码-修改行为信息
	public void updateActioncode(UseractionDTO dto);
	
	// 用户行为编码-插入行为信息
	public void insertActioncode(UseractionDTO dto);
}
