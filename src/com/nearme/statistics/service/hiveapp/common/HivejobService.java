package com.nearme.statistics.service.hiveapp.common;

import java.util.List;

import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.model.common.HivejobEntity;

/**
 * hive 任务管理
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-11
 */
public interface HivejobService {
	/**
	 * 查询任务数
	 * @param dto
	 * @return
	 */
	public long getHivejobCnt(HivejobDTO dto);

	/**
	 * 添加任务
	 * @param dto
	 */
	public void addHivejob(HivejobDTO dto);

	/**
	 * 任务完成
	 * @param dto
	 */
	public void finishHivejob(HivejobDTO dto);

	/**
	 * 查看所有任务
	 * @param dto
	 * @return
	 */
	public List<HivejobEntity> getHivejobList(HivejobDTO dto);
	
	/**
	 * 删除任务
	 * @param dto
	 */
	public void deleteHivejob(HivejobDTO dto);
}
