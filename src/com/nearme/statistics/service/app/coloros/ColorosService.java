package com.nearme.statistics.service.app.coloros;

import java.util.List;

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

public interface ColorosService {
	/**
	 * 人均启动次数排行
	 * @param dto
	 * @return
	 */
	public List<COSAvgstartcntrankEntity> getAvgstartcntrankList(ColorosDTO dto);

	/**
	 * 启动imei排行
	 * @param dto
	 * @return
	 */
	public List<COSStartrankEntity> getStartrankList(ColorosDTO dto);

	/**
	 * 明细数据
	 * @param dto
	 * @return
	 */
	public List<COSDetailEntity> getDetailList(ColorosDTO dto);

	/**
	 * 版本活跃
	 * @param dto
	 * @return
	 */
	public List<COSVersionActiveEntity> getVersionactiveList(ColorosDTO dto);

	/**
	 * 活跃用户地域分布
	 * @param dto
	 * @return
	 */
	public List<COSAreaEntity> listAreaStart(ColorosDTO dto);

	/**
	 * 周新增留存
	 * @param dto
	 * @return
	 */
	public List<COSWeeknirEntity> getWeeknirList(ColorosDTO dto);

	/**
	 * 月启动留存
	 * @param dto
	 * @return
	 */
	public List<COSMonthsrEntity> getMonthsrList(ColorosDTO dto);

	/**
	 * 网络分布
	 * @param dto
	 * @return
	 */
	public List<COSNetdistributeEntity> getNetdistributeList(ColorosDTO dto);

	/**
	 * 终端占比
	 * @param dto
	 * @return
	 */
	public List<ColumnValueEntity> getMobilezhanbiList(ColorosDTO dto);
}
