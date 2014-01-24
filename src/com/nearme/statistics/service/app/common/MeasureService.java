package com.nearme.statistics.service.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.MeasureDTO;
import com.nearme.statistics.model.commonsetting.MeasureEntity;

/**
 * 指标管理
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-20
 */
public interface MeasureService {
	/**
	 * 指标查询
	 * @param dto
	 * @return
	 */
	public List<MeasureEntity> getMeasureList(MeasureDTO dto);
}
