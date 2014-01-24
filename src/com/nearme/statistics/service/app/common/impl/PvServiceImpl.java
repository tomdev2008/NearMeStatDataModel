package com.nearme.statistics.service.app.common.impl;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dao.app.common.PvDao;
import com.nearme.statistics.dto.app.common.PvDTO;
import com.nearme.statistics.model.common.PvEntity;
import com.nearme.statistics.model.common.VisitPathEntity;
import com.nearme.statistics.service.app.common.PvService;
import com.nearme.statistics.util.DateUtil;

public class PvServiceImpl implements PvService {
	private PvDao dao;

	public void setDao(PvDao dao) {
		this.dao = dao;
	}

	/**
	 * 使用时长/单次
	 */
	public List<PvEntity> listDurationTimes(PvDTO dto) {
		List<PvEntity> list = null;
		try{
			String appVersion = dto.getAppVersion();
			if(appVersion==null || "all".equals(appVersion.toLowerCase())){
				dto.setAppVersion("ALL");
			}
			String channelID = dto.getChannelID();
			if(channelID==null || "all".equals(channelID.toLowerCase())){
				dto.setChannelID("ALL");
			}
			
			list = dao.listDurationTimes(dto);
			if(list!=null){
				int sum = 0;
				for(PvEntity entity : list){
					sum += entity.getStartTimes();
				}
				for(PvEntity entity : list){
					entity.setRate(sum<=0 ? "0" : String.format("%.2f", entity.getStartTimes()*100.0/sum));
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return list;
	}
	
	/**
	 * 使用时长/单日
	 */
	public List<PvEntity> listDurationImeis(PvDTO dto) {
		List<PvEntity> list = null;
		try{
			String appVersion = dto.getAppVersion();
			if(appVersion==null || "all".equals(appVersion.toLowerCase())){
				dto.setAppVersion("ALL");
			}
			String channelID = dto.getChannelID();
			if(channelID==null || "all".equals(channelID.toLowerCase())){
				dto.setChannelID("ALL");
			}
			
			list = dao.listDurationImeis(dto);
			if(list!=null){
				int sum = 0;
				for(PvEntity entity : list){
					sum += entity.getStartImeis();
				}
				for(PvEntity entity : list){
					entity.setRate(sum<=0 ? "0" : String.format("%.2f", entity.getStartImeis()*100.0/sum));
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return list;
	}


	/**
	 * 使用频率
	 */
	public List<PvEntity> listFrequency(PvDTO dto) {
		List<PvEntity> list = null;
		try{
			String appVersion = dto.getAppVersion();
			if(appVersion==null || "all".equals(appVersion.toLowerCase())){
				dto.setAppVersion("ALL");
			}
			String channelID = dto.getChannelID();
			if(channelID==null || "all".equals(channelID.toLowerCase())){
				dto.setChannelID("ALL");
			}
			if(Constants.WEEKLY.equals(dto.getLidu())){
				Date monday = DateUtil.getMonday(DateUtil.parseDate(String.valueOf(dto.getStatDateInt()), "yyyyMMdd"));
				dto.setStatDateInt(Integer.parseInt(DateUtil.formatDate(monday, "yyyyMMdd")));
			}else if(Constants.MONTHLY.equals(dto.getLidu())){
				dto.setStatDateInt(Math.round(dto.getStatDateInt()/100)*100+1);
			}
			
			list = dao.listFrequency(dto);
			if(list!=null){
				int sum = 0;
				for(PvEntity entity : list){
					sum += entity.getStartImeis();
				}
				for(PvEntity entity : list){
					entity.setRate(sum<=0 ? "0" : String.format("%.2f", entity.getStartImeis()*100.0/sum));
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return list;
	}


	/**
	 * 访问页面
	 */
	public List<PvEntity> listVistPages(PvDTO dto) {
		List<PvEntity> list = null;
		try{
			list = dao.listVistPages(dto);
			if(list!=null){
				int sum = 0;
				for(PvEntity entity : list){
					sum += entity.getStartTimes();
				}
				for(PvEntity entity : list){
					entity.setRate(sum<=0 ? "0" : String.format("%.2f", entity.getStartTimes()*100.0/sum));
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return list;
	}

	/**
	 * 对比使用时长/单次、使用时长/单日、频率
	 */
	public List<PvEntity> listCp(PvDTO dto) {
		List<PvEntity> list = null;
		try{
			String appVersion = dto.getAppVersion();
			if(appVersion==null || "all".equals(appVersion.toLowerCase())){
				dto.setAppVersion("ALL");
			}
			String channelID = dto.getChannelID();
			if(channelID==null || "all".equals(channelID.toLowerCase())){
				dto.setChannelID("ALL");
			}
			if(dto.getStatDates()==null || "".equals(dto.getStatDates())){
				return list;
			}
			
			String[] days = dto.getStatDates().replaceAll("-", "").split(",");
			StringBuffer sbf = new StringBuffer();
			for(int i=0; i<days.length; i++){
				if(i>0){
					sbf.append(",");
				}
				if("frequency".equals(dto.getFlag()) && Constants.WEEKLY.equals(dto.getLidu())){
					Date monday = DateUtil.getMonday(DateUtil.parseDate(days[i], "yyyyMMdd"));
					sbf.append(DateUtil.formatDate(monday, "yyyyMMdd"));
				}else if("frequency".equals(dto.getFlag()) && Constants.MONTHLY.equals(dto.getLidu())){
					sbf.append(days[i].substring(0, 6)).append("01");
				}else{
					sbf.append(days[i]);
				}
			}
			dto.setStatDates(sbf.toString());
			
			if("durationT".equals(dto.getFlag())){
				list = dao.listDurationTimesCp(dto);
			}else if("durationI".equals(dto.getFlag())){
				list = dao.listDurationImeisCp(dto);
			}else if("frequency".equals(dto.getFlag())){
				list = dao.listFrequencyCp(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return list;
	}

	/**
	 * 当前页面访问情况
	 */
	public List<VisitPathEntity> listCurPageJump(PvDTO dto) {
		List<VisitPathEntity> list = null;
		try{
			String appVersion = dto.getAppVersion();
			if(appVersion==null || "all".equals(appVersion.toLowerCase())){
				dto.setAppVersion("ALL");
			}
			
			list = this.dao.listCurPageJump(dto);
			if(list==null){
				return list;
			}
			
			int sum = 0;
			for(VisitPathEntity entity : list){
				sum += entity.getJumpTimes();
			}
			
			VisitPathEntity entity = null;
			for(int i=0,size=list.size(); i<size; i++){
				entity = list.get(i);
				entity.setRate(sum<=0 ? "0" : String.format("%.2f", entity.getJumpTimes()*100.0/sum));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return list;
	}

	/**
	 * 当前页面跳转情况
	 */
	public List<VisitPathEntity> listCurPageVisit(PvDTO dto) {
		List<VisitPathEntity> list = null;
		try{
			String appVersion = dto.getAppVersion();
			if(appVersion==null || "all".equals(appVersion.toLowerCase())){
				dto.setAppVersion("ALL");
			}
			
			list = this.dao.listCurPageVisit(dto);
			if(list==null){
				return list;
			}
			
			long visitSum = 0;
			long durationSum = 0;
			for(VisitPathEntity entity : list){
				visitSum += entity.getJumpTimes();
				durationSum += entity.getDuration();
			}
			
			VisitPathEntity entity = null;
			for(int i=0,size=list.size(); i<size; i++){
				entity = list.get(i);
				entity.setVisitRate(visitSum<=0 ? "0" : String.format("%.2f", entity.getJumpTimes()*100.0/visitSum));
				entity.setDurationRate(durationSum<=0 ? "0" : String.format("%.2f", entity.getDuration()*100.0/durationSum));
				entity.setAvgDuration(entity.getJumpTimes()<=0 ? "0" : String.format("%.2f", entity.getDuration()*1.0/entity.getJumpTimes()));
				entity.setExitRate(entity.getJumpTimes()<=0 ? "0" : String.format("%.2f", entity.getExitTimes()*100.0/entity.getJumpTimes()));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{}
		
		return list;
	}

}
