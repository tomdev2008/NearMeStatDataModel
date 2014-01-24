package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.model.common.HivejobEntity;

public interface HivejobDao {
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
