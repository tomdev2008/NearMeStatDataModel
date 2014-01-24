package com.nearme.statistics.dao.app.grzx;

import java.util.List;

import com.nearme.statistics.dto.app.grzx.GrzxDTO;
import com.nearme.statistics.model.grzx.GrzxNewfromEntity;
import com.nearme.statistics.model.grzx.GrzxRegisterfromEntity;
import com.nearme.statistics.model.grzx.GrzxUsercoverEntity;

public interface GrzxDao {
	// 账号覆盖率
	public List<GrzxUsercoverEntity> getUsercoverList(GrzxDTO dto);
	
	// 新增来源
	public List<GrzxNewfromEntity> getNewfromList(GrzxDTO dto);
	
	// 注册来源 
	public List<GrzxRegisterfromEntity> getRegisterfromList(GrzxDTO dto);
}
