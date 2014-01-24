package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.ModuleDTO;
import com.nearme.statistics.model.commonsetting.ModuleEntity;

/**
 * 模块管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-29
 */
public interface ModuleDao {
	/**
	 * 分组查询
	 * @param dto
	 * @return
	 */
	public List<ModuleEntity> getGroupList(ModuleDTO dto);
	
	/**
	 * 分组详情查询
	 * @param dto
	 * @return
	 */
	public List<ModuleEntity> getDetailList(ModuleDTO dto);
	
	/**
	 * 全部查询
	 * @param dto
	 * @return
	 */
	public List<ModuleEntity> getModuleAllList();
	
	/**
	 * 当前systemID对应查询
	 * @param dto
	 * @return
	 */
	public List<ModuleEntity> getModuleList(ModuleDTO dto);
	
	/**
	 * 删除分组
	 * @param dto
	 */
	public void deleteGroup(ModuleDTO dto);
	
	/**
	 * 删除详情
	 * @param dto
	 */
	public void deleteDetail(ModuleDTO dto);
	
	/**
	 * 更新分组
	 * @param dto
	 */
	public void updateGroup(ModuleDTO dto);
	
	/**
	 * 更新详情
	 */
	public void updateDetail(ModuleDTO dto);
	
	/**
	 * 添加
	 * @param dto
	 */
	public void add(ModuleDTO dto);
	
	/**
	 * 设置的数据从oracle上同步到hive上
	 * @param list
	 */
	public void sync2Hive(List<ModuleEntity> list);
}
