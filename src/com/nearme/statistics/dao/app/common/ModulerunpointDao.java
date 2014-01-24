package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.ModulerunpointDTO;
import com.nearme.statistics.model.commonsetting.ModulerunpointEntity;

/**
 * 渠道管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-11
 */
public interface ModulerunpointDao {
	/**
	 * 模块运营点查询
	 * @param dto
	 * @return
	 */
	public List<ModulerunpointEntity> getModulerunpointList(ModulerunpointDTO dto);
	
	/**
	 * 模块运营点删除
	 * @param dto
	 */
	public void delete(ModulerunpointDTO dto);
	
	/**
	 * 模块运营点添加
	 * @param dto
	 */
	public void add(ModulerunpointDTO dto);
	
	/**
	 * 模块运营点修改
	 * @param dto
	 */
	public void update(ModulerunpointDTO dto);
	
	/**
	 * 设置的数据从oracle上同步到hive上
	 * @param list
	 */
	public void sync2Hive(List<ModulerunpointEntity> list);
}
