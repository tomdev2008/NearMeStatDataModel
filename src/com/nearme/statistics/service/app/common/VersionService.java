package com.nearme.statistics.service.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.VersionDTO;
import com.nearme.statistics.model.commonsetting.VersionEntity;

/**
 * 版本
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-6
 */
public interface VersionService {
	/**
	 * 查询
	 * @param dto
	 * @return
	 */
	public List<VersionEntity> getVersionList(VersionDTO dto);

	/**
	 * 修改
	 * @param dto
	 */
	public void update(VersionDTO dto);
}
