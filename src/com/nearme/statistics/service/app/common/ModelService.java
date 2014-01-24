package com.nearme.statistics.service.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.ModelDTO;
import com.nearme.statistics.model.commonsetting.ModelEntity;

/**
 * 机型
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-20
 */
public interface ModelService {
	/**
	 * 查询
	 * @param dto
	 * @return
	 */
	public List<ModelEntity> getModelList(ModelDTO dto);

	/**
	 * 管理查询
	 * @param dto
	 * @return
	 */
	public List<ModelEntity> getModelmanageList(ModelDTO dto);
	
	/**
	 * 修改
	 * @param dto
	 */
	public void update(ModelDTO dto);
	
	
}
