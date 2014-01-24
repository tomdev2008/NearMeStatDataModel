package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.InstallSourceDTO;
import com.nearme.statistics.model.commonsetting.InstallSourceEntity;

/**
 * 安装来源管理
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-30
 */
public interface InstallSourceDao {
	// 安装来源查询
	public List<InstallSourceEntity> listInstallSource(InstallSourceDTO dto);

}
