package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.PvDTO;
import com.nearme.statistics.model.common.PvEntity;
import com.nearme.statistics.model.common.VisitPathEntity;

public interface PvDao {
	// 使用时长/单次
	public List<PvEntity> listDurationTimes(PvDTO dto);
	// 使用时长/单日
	public List<PvEntity> listDurationImeis(PvDTO dto);
	// 使用频率
	public List<PvEntity> listFrequency(PvDTO dto);
	// 访问页面
	public List<PvEntity> listVistPages(PvDTO dto);
	
	// 对比使用时长/单次
	public List<PvEntity> listDurationTimesCp(PvDTO dto);
	// 对比使用时长/单日
	public List<PvEntity> listDurationImeisCp(PvDTO dto);
	// 对比使用频率
	public List<PvEntity> listFrequencyCp(PvDTO dto);
	// 当前页面访问情况
	public List<VisitPathEntity> listCurPageVisit(PvDTO dto);
	// 当前页面跳转情况
	public List<VisitPathEntity> listCurPageJump(PvDTO dto);
	
}
