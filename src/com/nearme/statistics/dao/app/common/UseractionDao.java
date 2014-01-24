package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.UseractionDTO;
import com.nearme.statistics.model.commonsetting.UseractionEntity;

/**
 * 设置
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public interface UseractionDao {
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
