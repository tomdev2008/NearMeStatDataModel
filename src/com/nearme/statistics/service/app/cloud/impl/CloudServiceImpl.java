package com.nearme.statistics.service.app.cloud.impl;

import java.util.List;

import com.nearme.statistics.dao.app.cloud.CloudDao;
import com.nearme.statistics.dto.app.cloud.CloudDTO;
import com.nearme.statistics.model.cloud.CloudDayincreaseEntity;
import com.nearme.statistics.model.cloud.CloudFlowdistributeEntity;
import com.nearme.statistics.model.cloud.CloudPathEntity;
import com.nearme.statistics.model.cloud.CloudRemainEntity;
import com.nearme.statistics.service.app.cloud.CloudService;

public class CloudServiceImpl implements CloudService{
	private CloudDao dao;
	
	public CloudDao getDao() {
		return dao;
	}

	public void setDao(CloudDao dao) {
		this.dao = dao;
	}

	/**
	 * 日增情况
	 */
	public List<CloudDayincreaseEntity> getDayincreaseList(CloudDTO dto) {
		return dao.getDayincreaseList(dto);
	}

	/**
	 * 流量分配
	 */
	public List<CloudFlowdistributeEntity> getFlowdistributeList(CloudDTO dto) {
		return dao.getFlowdistributeList(dto);
	}

	/**
	 * 路径统计
	 */
	public List<CloudPathEntity> getPathList(CloudDTO dto) {
		return dao.getPathList(dto);
	}

	/**
	 * 存储留存
	 */
	public List<CloudRemainEntity> getRemainList(CloudDTO dto) {
		List<CloudRemainEntity> list = dao.getRemainList(dto);
		
		for (CloudRemainEntity entity : list) {
			float remain1x = entity.getRemain1x();
			float remain2x = entity.getRemain2x();
			float remain3x = entity.getRemain3x();
			
			remain1x = Math.round(remain1x * 10000) / (float) 100;
			remain2x = Math.round(remain2x * 10000) / (float) 100;
			remain3x = Math.round(remain3x * 10000) / (float) 100;
			
			entity.setRemain1x(remain1x);
			entity.setRemain2x(remain2x);
			entity.setRemain3x(remain3x);
		}
		
		return list;
	}

}
