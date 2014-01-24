package com.nearme.statistics.service.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.PvDTO;
import com.nearme.statistics.model.common.PvEntity;
import com.nearme.statistics.model.common.VisitPathEntity;

public interface PvService {
	// 使用时长/单次
	public List<PvEntity> listDurationTimes(PvDTO dto);
	// 使用时长/单日
	public List<PvEntity> listDurationImeis(PvDTO dto);
	// 使用频率
	public List<PvEntity> listFrequency(PvDTO dto);
	// 访问页面
	public List<PvEntity> listVistPages(PvDTO dto);
	
	// 对比使用时长/单次、使用时长/单日、频率
	public List<PvEntity> listCp(PvDTO dto);
	// 当前页面访问情况
	public List<VisitPathEntity> listCurPageVisit(PvDTO dto);
	// 当前页面跳转情况
	public List<VisitPathEntity> listCurPageJump(PvDTO dto);
}
