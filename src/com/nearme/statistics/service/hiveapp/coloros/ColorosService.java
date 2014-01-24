package com.nearme.statistics.service.hiveapp.coloros;

import java.util.List;

import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.model.coloros.COSAvgstartcntrankEntity;
import com.nearme.statistics.model.coloros.COSInstallrankEntity;
import com.nearme.statistics.model.coloros.COSStartrankEntity;
import com.nearme.statistics.model.common.HivesqlEntity;

public interface ColorosService {
	/**
	 * 启动用户排行<br>
	 * hive查询存储
	 *
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertStartrankList(
			ColorosDTO dto, HivejobDTO hdto);

	/**
	 * 启动排行周查询
	 * @param dto
	 */
	public List<COSStartrankEntity> getStartrankweekList(ColorosDTO dto);
	/**
	 * 平均启动排行周查询
	 * @param dto
	 */
	public List<COSAvgstartcntrankEntity> getAvgstartrankweekList(ColorosDTO dto);
	/**
	 * 安装排行周查询
	 * @param dto
	 */
	public List<COSInstallrankEntity> getInstallrankweekList(ColorosDTO dto);

	/**
	 * 启动用户排行<br>
	 * 从结果表中查询
	 *
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<COSStartrankEntity> doGetStartrankList(
			HivejobDTO hdto);

	/**
	 * 平均启动次数排行<br>
	 * hive查询存储
	 *
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertAvgstartcntrankList(
			ColorosDTO dto, HivejobDTO hdto);

	/**
	 * 平均启动次数排行<br>
	 * 从结果表中查询
	 *
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<COSAvgstartcntrankEntity> doGetAvgstartcntrankList(
			HivejobDTO hdto);

	/**
	 * 安装排行<br>
	 * hive查询存储
	 *
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertInstallrankList(
			ColorosDTO dto, HivejobDTO hdto);

	/**
	 * 安装排行<br>
	 * 从结果表中查询
	 *
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<COSInstallrankEntity> doGetInstallrankList(
			HivejobDTO hdto);

	/**
	 * 人均启动次数排行<br>
	 * 查询sql<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlAvgstartcntrank(ColorosDTO dto);

	/**
	 * 安装排行<br>
	 * 查询sql<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlInstallrank(ColorosDTO dto);

	/**
	 * 启动排行<br>
	 * 查询sql<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlStartrank(ColorosDTO dto);



	/**
	 * 删除结果数据<br>
	 * 启动排行
	 * @param dto
	 */
	public void deleteStartrank(HivejobDTO dto);

	/**
	 * 删除结果数据<br>
	 * 安装排行
	 * @param dto
	 */
	public void deleteInstallrank(HivejobDTO dto);

	/**
	 * 删除结果数据<br>
	 * 人均启动次数排行
	 * @param dto
	 */
	public void deleteAvgstartcntrank(HivejobDTO dto);
}
