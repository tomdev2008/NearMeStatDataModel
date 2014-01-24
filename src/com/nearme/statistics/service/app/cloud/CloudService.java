package com.nearme.statistics.service.app.cloud;

import java.util.List;

import com.nearme.statistics.dto.app.cloud.CloudDTO;
import com.nearme.statistics.model.cloud.CloudDayincreaseEntity;
import com.nearme.statistics.model.cloud.CloudFlowdistributeEntity;
import com.nearme.statistics.model.cloud.CloudPathEntity;
import com.nearme.statistics.model.cloud.CloudRemainEntity;

public interface CloudService {
	// 流量分配
	public List<CloudFlowdistributeEntity> getFlowdistributeList(CloudDTO dto);

	// 日增情况
	public List<CloudDayincreaseEntity> getDayincreaseList(CloudDTO dto);

	// 存储留存
	public List<CloudRemainEntity> getRemainList(CloudDTO dto);

	// 路径统计
	public List<CloudPathEntity> getPathList(CloudDTO dto);
}
