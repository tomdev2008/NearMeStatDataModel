package com.nearme.statistics.dao.hiveapp.coloros;

import java.util.List;

import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.model.coloros.COSAvgstartcntrankEntity;
import com.nearme.statistics.model.coloros.COSInstallrankEntity;
import com.nearme.statistics.model.coloros.COSStartrankEntity;

public interface ColorosDao {
	/**
	 * 启动用户排行<br>
	 */
	public List<COSStartrankEntity> hiveQueryStartrank(ColorosDTO dto);
	public int[] insertStartrank(List<COSStartrankEntity> list,
			String jobID);
	public List<COSStartrankEntity> getStartrank(HivejobDTO hdto);
	public List<COSStartrankEntity> getStartrankweekList(ColorosDTO dto);

	/**
	 * 人均启动次数排行<br>
	 */
	public List<COSAvgstartcntrankEntity> hiveQueryAvgstartcntrank(ColorosDTO dto);
	public int[] insertAvgstartcntrank(List<COSAvgstartcntrankEntity> list,
			String jobID);
	public List<COSAvgstartcntrankEntity> getAvgstartcntrank(HivejobDTO hdto);
	public List<COSAvgstartcntrankEntity> getAvgstartrankweekList(ColorosDTO dto);

	/**
	 * 安装排行<br>
	 */
	public List<COSInstallrankEntity> hiveQueryInstallrank(ColorosDTO dto);
	public int[] insertInstallrank(List<COSInstallrankEntity> list,
			String jobID);
	public List<COSInstallrankEntity> getInstallrank(HivejobDTO hdto);
	public List<COSInstallrankEntity> getInstallrankweekList(ColorosDTO dto);

	/**
	 * 人均启动次数排行<br>
	 * 查询sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlAvgstartcntrank(ColorosDTO dto);

	/**
	 * 安装排行<br>
	 * 查询sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlInstallrank(ColorosDTO dto);

	/**
	 * 启动排行<br>
	 * 查询sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlStartrank(ColorosDTO dto);



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
