package com.nearme.statistics.service.hiveapp.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.common.ProductinfoDTO;
import com.nearme.statistics.model.common.ActivedaysdistributeEntity;
import com.nearme.statistics.model.common.FSNaturemodlenumEntity;
import com.nearme.statistics.model.common.FSOperatepointEntity;
import com.nearme.statistics.model.common.FSResourcetypeEntity;
import com.nearme.statistics.model.common.FSTopresourceEntity;
import com.nearme.statistics.model.common.FSUpdatenumEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
import com.nearme.statistics.model.common.ModuledownEntity;
import com.nearme.statistics.model.common.ModulepositionEntity;
import com.nearme.statistics.model.common.NewuseractiveEntity;
import com.nearme.statistics.model.common.ProductinfoEntity;
import com.nearme.statistics.model.common.ProductrunEntity;
import com.nearme.statistics.model.common.WholeuserlifecycleEntity;

public interface CommonService {
	/**
	 * 整体用户生命周期<br>
	 * hive查询存储
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertWholeuserlifecycleList(
			BaseDTO dto, HivejobDTO hdto);

	/**
	 * 整体用户生命周期<br>
	 * 从结果表中查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<WholeuserlifecycleEntity> doGetWholeuserlifecycleList(
			HivejobDTO hdto);

	/**
	 * 月活跃天数和分布<br>
	 * hive查询存储
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertActivedaysdistributeList(
			BaseDTO dto, HivejobDTO hdto);

	/**
	 * 月活跃天数和分布<br>
	 * 从结果表中查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<ActivedaysdistributeEntity> doGetActivedaysdistributeList(
			HivejobDTO hdto);

	/**
	 * 新用户活跃 -- 第一个月<br>
	 * hive查询存储
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertNewuseractivemonthList(BaseDTO dto,
			HivejobDTO hdto);

	/**
	 * 新用户活跃 -- 第一个月<br>
	 * 从结果表中查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<NewuseractiveEntity> doGetNewuseractivemonthList(HivejobDTO hdto);

	/**
	 * 新用户活跃 -- 第一个周 <br>
	 * hive查询存储
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertNewuseractiveweekList(BaseDTO dto,
			HivejobDTO hdto);

	/**
	 * 新用户活跃 -- 第一个周 <br>
	 * 从结果表中查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<NewuseractiveEntity> doGetNewuseractiveweekList(HivejobDTO hdto);

	/**
	 * 流量结构分析(hive查询存储到oracle)<br>
	 * 更新数量总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertFSUpdatenum(BaseDTO dto, HivejobDTO hdto);

	/**
	 * 流量结构分析(hive查询存储到oracle)<br>
	 * 资源类别总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertFSResourcetype(BaseDTO dto,HivejobDTO hdto);
	
	/**
	 * 流量结构分析(hive查询存储到oracle)<br>
	 * top资源总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertFSTopresource(BaseDTO dto,
			HivejobDTO hdto);

	/**
	 * 流量结构分析(hive查询存储到oracle)<br>
	 * 自然模块数量总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertFSNaturemodlenum(BaseDTO dto,
			HivejobDTO hdto);

	/**
	 * 流量结构分析(hive查询存储到oracle)<br>
	 * 运营点总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertFSOperatepoint(BaseDTO dto,
			HivejobDTO hdto);
	
	/**
	 * (hive查询存储到oracle)<br>
	 * 模块下载量分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertModuledown(BaseDTO dto,
			HivejobDTO hdto);
	
	/**
	 * (hive查询存储到oracle)<br>
	 * 模块下载位置分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertModuleposition(BaseDTO dto,
			HivejobDTO hdto);

	/**
	 * 流量结构分析<br>
	 * 更新数量总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSUpdatenumEntity> doGetFSUpdatenum(HivejobDTO hdto);
	public List<FSUpdatenumEntity> doGetFSUpdatenumTotal(HivejobDTO hdto);

	/**
	 * 流量结构分析<br>
	 * 资源类别总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSResourcetypeEntity> doGetFSResourcetype(HivejobDTO hdto);

	/**
	 * 流量结构分析<br>
	 * top资源总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSTopresourceEntity> doGetFSTopresource(HivejobDTO hdto);
	public List<FSTopresourceEntity> doGetFSTopresourceTotal(HivejobDTO hdto);

	/**
	 * 流量结构分析<br>
	 * 自然模块数量总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSNaturemodlenumEntity> doGetFSNaturemodlenum(HivejobDTO hdto);
	
	/**
	 * 模块下载量分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<ModuledownEntity> doGetModuledown(HivejobDTO hdto);
	
	/**
	 * 模块位置下载分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<ModulepositionEntity> doGetModuleposition(HivejobDTO hdto);

	/**
	 * 流量结构分析<br>
	 * 运营点总体及分析
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSOperatepointEntity> doGetFSOperatepoint(HivejobDTO hdto);

	/**
	 * 产品名
	 * 
	 * @param dto
	 * @return
	 */
	public List<ProductinfoEntity> getProductinfoList(ProductinfoDTO dto);

	/**
	 * 单个资源运营(hive查询存储到oracle)<br>
	 * 模块分布
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public void queryAndInsertProductrunmoduleList(ProductinfoDTO dto,
			HivejobDTO hdto);

	/**
	 * 单个资源运营(结果表查询)<br>
	 * 模块分布
	 * 
	 * @param hdto
	 * @return
	 */
	public List<ProductrunEntity> doGetProductrunmoduleList(HivejobDTO hdto);

	
	
	/**
	 * 获取查询的sql<br>
	 * 整体用户生命周期
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlWholeuserlifecycle(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 单个资源运营
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlProductrun(ProductinfoDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 新用户活跃<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlNewuseractive(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 更新<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlFSUpdatenum(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * top资源<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlFSTopresource(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 资源分类<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlFSResourcetype(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 自然模块<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlFSNaturemodle(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 模块下载<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlModuledown(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 模块下载位置<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlModuleposition(BaseDTO dto);
	
	/**
	 * 获取查询的sql<br>
	 * 月活天数和分布<br>
	 * @param dto
	 * @return
	 */
	public List<HivesqlEntity> getHqlActivedaysdistribute(BaseDTO dto);

	
	// 删除查询结果
	public void deleteWholeuserlifecycle(HivejobDTO hdto);
	public void deleteProductrun(HivejobDTO hdto);
	public void deleteNewuseractive(HivejobDTO hdto);
	public void deleteFSUpdatenum(HivejobDTO hdto);
	public void deleteFSTopresource(HivejobDTO hdto);
	public void deleteFSResourcetype(HivejobDTO hdto);
	public void deleteFSNaturemodlenum(HivejobDTO hdto);
	public void deleteActivedaysdistribute(HivejobDTO hdto);
	public void deleteModuledown(HivejobDTO hdto);
	public void deleteModuleposition(HivejobDTO hdto);
}
