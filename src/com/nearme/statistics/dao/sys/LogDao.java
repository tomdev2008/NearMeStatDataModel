package com.nearme.statistics.dao.sys;

import com.nearme.statistics.dto.LogDTO;

public interface LogDao {
	// 用户操作日志记录
	public void logInfo(LogDTO dto);
}
