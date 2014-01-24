package com.nearme.statistics.service.app.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dao.app.common.EventDao;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.EventDTO;
import com.nearme.statistics.dto.app.common.EventFlowDTO;
import com.nearme.statistics.model.common.KVEventDetailEntity;
import com.nearme.statistics.model.common.KVEventEntity;
import com.nearme.statistics.model.common.KVEventFlowEntity;
import com.nearme.statistics.model.common.KVEventIndexEntity;
import com.nearme.statistics.service.app.common.EventService;
import com.nearme.statistics.util.DateUtil;

public class EventServiceImpl implements EventService {
	private EventDao dao;
	
	public void setDao(EventDao dao) {
		this.dao = dao;
	}

	@Override
	public List<KVEventEntity> listKVEvent(BaseDTO dto) {
		List<KVEventEntity> resultList = null;
		try{
			resultList=this.dao.listKVEvent(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return resultList;
	}
	
	/**
	 * KV事件每天发生次数统计列表
	 */
	public List<KVEventIndexEntity> listKVEventIndex(EventDTO dto) {
		List<KVEventIndexEntity> resultList = null;
		try{
			resultList = this.dao.listKVEventIndex(dto);
			for (KVEventIndexEntity entity : resultList) {
				entity.setStatdateStr(DateUtil.getFormatResult(String.valueOf(entity.getStatdate()), "yyyyMMdd", Constants.DAILY));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return resultList;
	}
	
	/**
	 * KV事件Key列表
	 */
	public List<KVEventDetailEntity> listKVEventKey(EventDTO dto) {
		List<KVEventDetailEntity> resultList = null;
		try{
			resultList = this.dao.listKVEventKey(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return resultList;
	}


	/**
	 * KV事件发生详情
	 */
	public List<KVEventDetailEntity> listKVEventDetail(EventDTO dto) {
		List<KVEventDetailEntity> resultList = null;
		try{
			resultList = this.dao.listKVEventDetail(dto);
			
			for (KVEventDetailEntity entity : resultList) {
				entity.setStatdateStr(DateUtil.getFormatResult(String.valueOf(entity.getStatdate()), "yyyyMMdd", Constants.DAILY));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return resultList;
	}
	
	/**
	 * KV事件发生详情
	 */
	public List<KVEventDetailEntity> listKVEventDetailSum(EventDTO dto) {
		List<KVEventDetailEntity> resultList = null;
		try{
			resultList = this.dao.listKVEventDetailSum(dto);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return resultList;
	}

	/**
	 * KV事件新增
	 */
	public int addKVEvent(EventDTO dto) {
		try{
			return this.dao.addKVEvent(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return -1;
	}

	/**
	 * KV事件删除
	 */
	public int deleteKVEvent(EventDTO dto) {
		try{
			return this.dao.deleteKVEvent(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return -1;
	}

	/**
	 * KV事件更新
	 */
	public int updateKVEvent(EventDTO dto) {
		try{
			return this.dao.updateKVEvent(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return -1;
	}

	/**
	 * 添加KV事件流
	 */
	public int addKVEventFlow(List<EventFlowDTO> list) {
		try{
			if(list==null || list.size()<=0){
				return -1;	
			}
			for(int i=0, size=list.size(); i<size; i++){
				this.dao.addKVEventFlow(list.get(i));
			}
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return -1;
	}

	/**
	 * 删除KV事件流
	 */
	public int deleteKVEventFlow(EventFlowDTO dto) {
		try{
			return this.dao.deleteKVEventFlow(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return -1;
	}

	/**
	 * 查询某个事件流详情
	 */
	public List<KVEventFlowEntity> getTheKVEventFlow(EventFlowDTO dto) {
		List<KVEventFlowEntity> resultList = null;
		try{
			resultList = this.dao.getTheKVEventFlow(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return resultList;
	}

	/**
	 * 查询所有事件流列表
	 */
	public List<KVEventFlowEntity> listKVEventFlow(BaseDTO dto) {
		List<KVEventFlowEntity> resultList = null;
		try{
			resultList = this.dao.listKVEventFlow(dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return resultList;
	}

	/**
	 * 更新一个事件流
	 */
	public int updateKVEventFlow(List<EventFlowDTO> list) {
		try{
			if(list==null || list.size()<=0){
				return -1;	
			}
			//老的数据全部删除
			this.dao.deleteKVEventFlow(list.get(0));
			//重新添加更新后的数据
			for(int i=0, size=list.size(); i<size; i++){
				this.dao.addKVEventFlow(list.get(i));
			}
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return -1;
	}
	
	/**
	 * 转化率详情
	 */
	public List<KVEventFlowEntity> listConversionAllDetail(EventFlowDTO dto) {
		
		List<KVEventFlowEntity> resultList = new ArrayList<KVEventFlowEntity>();
		try{
			List<KVEventFlowEntity> flowList = this.dao.listKVEventFlow(dto);
			if(flowList==null || flowList.size()<=0){
				return resultList;
			}
			List<KVEventFlowEntity> tmpList = null;
			for(int i=0, size=flowList.size(); i<size; i++){
				KVEventFlowEntity entity = new KVEventFlowEntity();
				entity = flowList.get(i);
				dto.setEventFlowName(flowList.get(i).getEventFlowName());
				tmpList = this.listConversionDetail(dto);
				if(tmpList!=null && tmpList.size()>=0){
					entity.setEventTCR(tmpList.get(tmpList.size()-1).getEventTCR());
				}
				resultList.add(entity);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		return resultList;
	}

	/**
	 * 转化率详情
	 */
	public List<KVEventFlowEntity> listConversionDetail(EventFlowDTO dto) {
		
		List<KVEventFlowEntity> resultList = new ArrayList<KVEventFlowEntity>();
		try{
			//每个步骤的发生次数,IMEI数
			List<KVEventFlowEntity> conversionList = this.dao.listConversionDetail(dto);
			if(conversionList==null || conversionList.size()<=0){
				return resultList;
			}
			//计算转化率
			int firstCounts = 0;
			int firstImeis = 0;
			KVEventFlowEntity lastEntity = new KVEventFlowEntity();
			KVEventFlowEntity entity = null;
			KVEventFlowEntity tmpEntity = null;
			for(int i=0,size=conversionList.size(); i<size; i++){
				tmpEntity = conversionList.get(i);
				if(i==0){
					firstCounts = tmpEntity.getEventCounts();
					firstImeis = tmpEntity.getEventImeis();
				}
				entity = new KVEventFlowEntity();
				entity.setEventFlowName(tmpEntity.getEventFlowName());
				entity.setEventFlowStep(tmpEntity.getEventFlowStep());
				entity.setFinalEventName(tmpEntity.getFinalEventName());
				entity.setEventCounts(tmpEntity.getEventCounts());
				entity.setEventImeis(tmpEntity.getEventImeis());
				float cr = (float) (lastEntity.getEventCounts()==0 ? 0 : tmpEntity.getEventCounts()*1.0/lastEntity.getEventCounts()*100.0);
				entity.setEventCR(String.format("%.2f", cr));
				float tcr = (float) (firstCounts==0 ? 0 : tmpEntity.getEventCounts()*1.0/firstCounts*100.0);
				entity.setEventTCR(String.format("%.2f", tcr));
				float icr = (float) (lastEntity.getEventImeis()==0 ? 0 : tmpEntity.getEventImeis()*1.0/lastEntity.getEventImeis()*100.0);
				entity.setEventICR(String.format("%.2f", icr));
				float itcr = (float) (firstImeis==0 ? 0 : tmpEntity.getEventImeis()*1.0/firstImeis*100.0);
				entity.setEventITCR(String.format("%.2f", itcr));
				
				lastEntity = conversionList.get(i);
				
				resultList.add(entity);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		return resultList;
	}
	
	/**
	 * 事件流中的一步在一段时间内的转化率详情
	 */
	public List<KVEventFlowEntity> listConversionDetailPeriod(EventFlowDTO dto) {
		
		List<KVEventFlowEntity> resultList = new ArrayList<KVEventFlowEntity>();
		try{
			//设置参数
			this.setParas(dto);
			
			//每个步骤的发生次数,IMEI数
			List<KVEventFlowEntity> conversionList = this.dao.listConversionDetailPeriod(dto);
			if(conversionList==null || conversionList.size()<=0){
				return resultList;
			}
			
			//要统计的这一步的转化率
			int step = dto.getEventFlowStep();
			
			//查询当前事件流中这一步骤和上一步骤的名称,用来显示说明
			String eventName = "";
			String prevEventName = "";
			List<KVEventFlowEntity> detailList = this.dao.getTheKVEventFlow(dto);
			KVEventFlowEntity tmpEntity = null;
			KVEventFlowEntity lastEntity = null;
			for(int i=0,size=detailList.size(); i<size; i++){
				tmpEntity = detailList.get(i);
				if(step==tmpEntity.getEventFlowStep()){
					break;
				}
				lastEntity = detailList.get(i);
			}
			eventName = tmpEntity.getFinalEventName();
			prevEventName = lastEntity.getFinalEventName();
			
			int statDate = 0;
			for(int m=6; m>=0; m--){
				statDate = this.getNextDay(dto, m);
				int counts = 0;
				int lastCounts = 0;
				int imeis = 0;
				int lastImeis = 0;
				for(int i=0,size=conversionList.size(); i<size; i++){
					tmpEntity = conversionList.get(i);
					if(tmpEntity.getStatDate()==statDate){
						if(step==tmpEntity.getEventFlowStep()){
							counts = tmpEntity.getEventCounts();
							lastCounts = lastEntity==null ? 0: lastEntity.getEventCounts();
							imeis = tmpEntity.getEventImeis();
							lastImeis = lastEntity==null ? 0: lastEntity.getEventImeis();
						}
						lastEntity = conversionList.get(i);
					}
				}
				KVEventFlowEntity entity = new KVEventFlowEntity();
				entity.setStatDate(statDate);
				float tcr = (float) (lastCounts==0 ? 0 : counts*100.0/lastCounts*1.0);
				entity.setEventTCR(String.format("%.2f", tcr));
				float itcr = (float) (lastImeis==0 ? 0 : imeis*100.0/lastImeis*1.0);
				entity.setEventITCR(String.format("%.2f", itcr));
				//设置当前事件流中这一步骤和上一步骤的名称,用来显示说明
				entity.setFinalEventName(eventName);
				entity.setPrevEventName(prevEventName);
				
				resultList.add(entity);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		return resultList;
	}
	
	private int getNextDay(EventFlowDTO dto, int m){
		int day = 0;
		if(Constants.DAILY.equals(dto.getLidu())){
			Date date = DateUtil.parseDate(String.valueOf(dto.getStatDate()), "yyyyMMdd");
			day = Integer.parseInt(DateUtil.getDateOfXdaysAgo(date, m, "yyyyMMdd"));
		}else if(Constants.WEEKLY.equals(dto.getLidu())){
			Date date = DateUtil.parseDate(String.valueOf(dto.getStatDate()), "yyyyMMdd");
			day = Integer.parseInt(DateUtil.getDateOfXdaysAgo(date, 7*m, "yyyyMMdd"));
		}else if(Constants.MONTHLY.equals(dto.getLidu())){
			Date date = DateUtil.parseDate(String.valueOf(dto.getStatDate()), "yyyyMM");
			day = Integer.parseInt(DateUtil.getDateOfXmonthsAgo(date, m, "yyyyMM"));
		}
		return day;
	}
	
	private void setParas(EventFlowDTO dto){
		if(Constants.DAILY.equals(dto.getLidu())){
			Date date = DateUtil.parseDate(String.valueOf(dto.getStatDate()), "yyyyMMdd");
			int statFromDate = Integer.parseInt(DateUtil.getDateOfXdaysAgo(date, 6, "yyyyMMdd"));
			dto.setStatFromDate(statFromDate);
		}else if(Constants.WEEKLY.equals(dto.getLidu())){
			Date date = DateUtil.parseDate(String.valueOf(dto.getStatDate()), "yyyyMMdd");
			int statFromDate = Integer.parseInt(DateUtil.getDateOfXdaysAgo(date, 7*6, "yyyyMMdd"));
			dto.setStatFromDate(statFromDate);
		}else if(Constants.MONTHLY.equals(dto.getLidu())){
			Date date = DateUtil.parseDate(String.valueOf(dto.getStatDate()), "yyyyMM");
			int statFromDate = Integer.parseInt(DateUtil.getDateOfXmonthsAgo(date, 6, "yyyyMM"));
			dto.setStatFromDate(statFromDate);
		}
	}
	
}
