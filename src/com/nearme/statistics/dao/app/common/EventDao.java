package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.EventDTO;
import com.nearme.statistics.dto.app.common.EventFlowDTO;
import com.nearme.statistics.model.common.KVEventDetailEntity;
import com.nearme.statistics.model.common.KVEventEntity;
import com.nearme.statistics.model.common.KVEventFlowEntity;
import com.nearme.statistics.model.common.KVEventIndexEntity;

public interface EventDao {
	// KV事件删除
	public int deleteKVEvent(EventDTO dto);
	// KV事件新增 
	public int addKVEvent(EventDTO dto);
	// KV事件更新 
	public int updateKVEvent(EventDTO dto);
	// KV事件 列表 
	public List<KVEventEntity> listKVEvent(BaseDTO dto);
	// KV事件统计列表
	public List<KVEventIndexEntity> listKVEventIndex(EventDTO dto);
	// KV事件Key列表
	public List<KVEventDetailEntity> listKVEventKey(EventDTO dto);
	// KV事件详情统计列表
	public List<KVEventDetailEntity> listKVEventDetail(EventDTO dto);
	// KV事件详情统计列表
	public List<KVEventDetailEntity> listKVEventDetailSum(EventDTO dto);
	
	// KV事件流删除
	public int deleteKVEventFlow(EventFlowDTO dto);
	// KV事件流新增 
	public int addKVEventFlow(EventFlowDTO dto);
	// KV事件流 列表 
	public List<KVEventFlowEntity> listKVEventFlow(BaseDTO dto);
	// 一个KV事件流详情
	public List<KVEventFlowEntity> getTheKVEventFlow(EventFlowDTO dto);
	// 转化率详情
	public List<KVEventFlowEntity> listConversionDetail(EventFlowDTO dto);
	// 转化率在一段时间内的详情
	public List<KVEventFlowEntity> listConversionDetailPeriod(EventFlowDTO dto);
	
	
}
