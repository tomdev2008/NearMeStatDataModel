package com.nearme.statistics.service.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.ModulerunpointDTO;
import com.nearme.statistics.model.commonsetting.ModulerunpointEntity;

/**
 * 模块运营点管理
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-19
 */
public interface ModulerunpointService {
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
	 * oracle查询数据<br>
	 * 同步数据到hive上<br>
	 * @param dto
	 */
	public void sync2Hive(ModulerunpointDTO dto);
}
