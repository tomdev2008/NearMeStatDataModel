package com.nearme.statistics.service.app.coloros.impl;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dao.app.coloros.ColorosDao;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.model.ColumnValueEntity;
import com.nearme.statistics.model.coloros.COSAreaEntity;
import com.nearme.statistics.model.coloros.COSAvgstartcntrankEntity;
import com.nearme.statistics.model.coloros.COSDetailEntity;
import com.nearme.statistics.model.coloros.COSMonthsrEntity;
import com.nearme.statistics.model.coloros.COSNetdistributeEntity;
import com.nearme.statistics.model.coloros.COSStartrankEntity;
import com.nearme.statistics.model.coloros.COSVersionActiveEntity;
import com.nearme.statistics.model.coloros.COSWeeknirEntity;
import com.nearme.statistics.service.app.coloros.ColorosService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.StringUtil;

public class ColorosServiceImpl implements ColorosService {
	private ColorosDao dao;

	public ColorosDao getDao() {
		return dao;
	}

	public void setDao(ColorosDao dao) {
		this.dao = dao;
	}

	public List<COSAvgstartcntrankEntity> getAvgstartcntrankList(ColorosDTO dto) {
		List<COSAvgstartcntrankEntity> list = dao.getAvgstartcntrankList(dto);

		Date statDate = dto.getStartDate();
		Date statEndDate = dto.getEndDate();

		for (int i = 0; i < list.size(); i++) {
			COSAvgstartcntrankEntity entity = list.get(i);
			entity.setPosition("" + (i + 1));
			entity.setStatDate(statDate);
			entity.setStatEndDate(statEndDate);
		}

		return list;
	}

	public List<COSStartrankEntity> getStartrankList(ColorosDTO dto) {
		List<COSStartrankEntity> list = dao.getStartrankList(dto);

		Date statDate = dto.getStartDate();
		Date statEndDate = dto.getEndDate();

		for (int i = 0; i < list.size(); i++) {
			COSStartrankEntity entity = list.get(i);
			entity.setPosition("" + (i + 1));
			entity.setStatDate(statDate);
			entity.setStatEndDate(statEndDate);
		}

		return list;
	}


	public List<COSVersionActiveEntity> getVersionactiveList(ColorosDTO dto) {
		List<COSVersionActiveEntity> list = dao.getVersionactiveList(dto);

		for (COSVersionActiveEntity entity : list) {
			Date statDate = entity.getStatDate();
			if (Constants.WEEKLY.equals(dto.getLidu())) {
				Date statEndDate = DateUtil.parseDate(DateUtil.getDateOfXdaysAgo(statDate, -6, "yyyy-MM-dd"), "yyyy-MM-dd");
				entity.setStatEndDate(statEndDate);
			}
		}

		return list;
	}

	/**
	 * 活跃用户地域分析
	 * @param dto
	 * @return
	 */
	public List<COSAreaEntity> listAreaStart(ColorosDTO dto) {
		List<COSAreaEntity> list = null;
		try{
			list = dao.listAreaStart(dto);

			if(list==null || list.size()==0){
				return null;
			}

			long count = 0;
			for(int i=0,size=list.size(); i<size; i++){
				count += list.get(i).getStartImeis();
			}
			if(count>0){
				COSAreaEntity entity = null;
				String tmp = null;
				for(int i=0,size=list.size(); i<size; i++){
					entity = list.get(i);
					String percent = String.format("%.2f", entity.getStartImeis()*100.0/count);
					entity.setPercent(percent);
					tmp = entity.getCountry();
					entity.setCountry(tmp==null?"":tmp.replaceAll("'", " "));
					tmp = entity.getProvince();
					entity.setProvince(tmp==null?"":tmp.replaceAll("'", " "));
					tmp = entity.getCity();
					entity.setCity(tmp==null?"":tmp.replaceAll("'", " "));
				}
			}

			if(list.size()>100){
				list = list.subList(0, 100);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<COSMonthsrEntity> getMonthsrList(ColorosDTO dto) {
		List<COSMonthsrEntity> list = dao.getMonthsrList(dto);

		for (COSMonthsrEntity entity : list) {
			entity.setLrr1_imei(Math.round((entity.getLrr1_imei()) * 10000/ (float) 100));
			entity.setLrr2_imei(Math.round((entity.getLrr2_imei()) * 10000/ (float) 100));
			entity.setLrr3_imei(Math.round((entity.getLrr3_imei()) * 10000/ (float) 100));
		}

		return list;
	}

	public List<COSWeeknirEntity> getWeeknirList(ColorosDTO dto) {
		List<COSWeeknirEntity> list = dao.getWeeknirList(dto);

		for (COSWeeknirEntity entity : list) {
			Date statdate = entity.getStatDate();
			Date enddate = DateUtil.AddDays(statdate, 6);
			entity.setStatEndDate(enddate);
			entity.setNrr1_imei(Math.round((entity.getNrr1_imei()) * 10000/ (float) 100));
			entity.setNrr2_imei(Math.round((entity.getNrr2_imei()) * 10000/ (float) 100));
			entity.setNrr3_imei(Math.round((entity.getNrr3_imei()) * 10000/ (float) 100));
		}

		return list;
	}

	public List<COSNetdistributeEntity> getNetdistributeList(ColorosDTO dto) {
		List<COSNetdistributeEntity> list = dao.getNetdistributeList(dto);

		// 计算总量
		long usertotal = 0;
		for (COSNetdistributeEntity entity : list) {
			long usercnt = entity.getUsercnt();
			usertotal += usercnt;
		}

		// 填充占比
		for (COSNetdistributeEntity entity : list) {
			long usercnt = entity.getUsercnt();

			float userratio = usertotal == 0 ? 0 : Math.round(usercnt * 10000
					/ (float) usertotal)
					/ (float) 100;

			entity.setUserratio(userratio);
		}

		return list;
	}

	public List<ColumnValueEntity> getMobilezhanbiList(ColorosDTO dto) {
		List<ColumnValueEntity> list = dao.getMobilezhanbiList(dto);
		// 计算总imei
		long total = 0;
		for (ColumnValueEntity entity : list) {
			total += StringUtil.parseLong(entity.getValue2());
		}
		// 填充value3
		for (ColumnValueEntity entity : list) {
			long value2 = StringUtil.parseLong(entity.getValue2());
			float value3 = Math.round(value2 * 10000 / (float) total)
					/ (float) 100;
			entity.setValue3(String.valueOf(value3).trim());
		}
		return list;
	}

	public List<COSDetailEntity> getDetailList(ColorosDTO dto) {
		List<COSDetailEntity> list = dao.getDetailList(dto);

		for (COSDetailEntity entity : list) {
			Date statDate = entity.getStatDate();
			if (Constants.WEEKLY.equals(dto.getLidu())) {
				Date statEndDate = DateUtil.parseDate(DateUtil.getDateOfXdaysAgo(statDate, -6, "yyyy-MM-dd"), "yyyy-MM-dd");
				entity.setStatEndDate(statEndDate);
			}
		}

		return list;
	}
}
