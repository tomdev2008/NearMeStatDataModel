package com.nearme.statistics.dao.hiveapp.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.common.ProductinfoDTO;
import com.nearme.statistics.model.common.ActivedaysdistributeEntity;
import com.nearme.statistics.model.common.FSNaturemodleChildEntity;
import com.nearme.statistics.model.common.FSOperatepointEntity;
import com.nearme.statistics.model.common.FSResourcetypeChildEntity;
import com.nearme.statistics.model.common.FSTopresourceEntity;
import com.nearme.statistics.model.common.FSUpdatenumEntity;
import com.nearme.statistics.model.common.HiveFSTopresourceEntity;
import com.nearme.statistics.model.common.ModuledownChildEntity;
import com.nearme.statistics.model.common.ModulepositionChildEntity;
import com.nearme.statistics.model.common.NewuseractiveEntity;
import com.nearme.statistics.model.common.ProductinfoEntity;
import com.nearme.statistics.model.common.ProductrunEntity;
import com.nearme.statistics.model.common.WholeuserlifecycleEntity;

public interface CommonDao {
	/**
	 * 整体用户生命周期<br>
	 * hive查询
	 */
	public List<WholeuserlifecycleEntity> hiveQueryWholeuserlifecycle(BaseDTO dto);

	/**
	 * 整体用户生命周期<br>
	 * insert
	 */
	public int[] insertWholeuserlifecycle(List<WholeuserlifecycleEntity> list,
			String jobID);

	/**
	 * 整体用户生命周期<br>
	 * 查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<WholeuserlifecycleEntity> getWholeuserlifecycle(HivejobDTO hdto);

	
	
	
	/**
	 * 月活跃天数和分布<br>
	 * hive查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<ActivedaysdistributeEntity> hiveQueryActivedaysdistribute(
			BaseDTO dto);
	
	/**
	 * 月活跃天数和分布<br>
	 * insert
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public int[] insertActivedaysdistribute(
			List<ActivedaysdistributeEntity> list,String jobID);

	/**
	 * 月活跃天数和分布<br>
	 * 查询
	 */
	public List<ActivedaysdistributeEntity> getActivedaysdistribute(
			HivejobDTO hdto);

	
	
	
	/**
	 * 新用户活跃 -- 第一个月<br>
	 * hive查询
	 */
	public List<NewuseractiveEntity> hiveQueryNewuseractivemonth(BaseDTO dto);
	
	/**
	 * 新用户活跃--第一个月<br>
	 * insert
	 */
	public int[] insertNewuseractivemonth(List<NewuseractiveEntity> list,String jobID);

	/**
	 * 新用户活跃 -- 第一个月<br>
	 * 查询
	 */
	public List<NewuseractiveEntity> getNewuseractivemonth(HivejobDTO hdto);

	
	
	
	/**
	 * 新用户活跃 -- 第一个周 <br>
	 * hive查询
	 */
	public List<NewuseractiveEntity> hiveQueryNewuseractiveweek(BaseDTO dto);
	
	/**
	 * 新用户活跃 -- 第一个周 <br>
	 * insert
	 * 
	 * @param list
	 * @param jobID
	 * @return
	 */
	public int[] insertNewuseractiveweek(List<NewuseractiveEntity> list,String jobID);

	/**
	 * 新用户活跃 -- 第一个周 <br>
	 * 查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<NewuseractiveEntity> getNewuseractiveweek(HivejobDTO hdto);

	
	
	/**
	 * 流量结构分析--更新数量总体及分析<br>
	 * hive查询
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSUpdatenumEntity> hiveQueryFSUpdatenum(BaseDTO dto);
	
	/**
	 * 流量结构分析--更新数量总体及分析<br>
	 * insert
	 * 
	 * @param list
	 * @param jobID
	 * @return
	 */
	public int[] insertFSUpdatenum(List<FSUpdatenumEntity> list,String jobID);
	
	/**
	 * 流量结构分析--更新数量总体及分析<br>
	 * 查询
	 * @param hdto
	 * @return
	 */
	public List<FSUpdatenumEntity> getFSUpdatenum(HivejobDTO hdto);
	public List<FSUpdatenumEntity> getFSUpdatenumTotal(HivejobDTO hdto);
	
	
	
	/**
	 * 流量结构分析--资源类别总体及分析<br>
	 * hive查询
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSResourcetypeChildEntity> hiveQueryFSResourcetype(BaseDTO dto);
	
	/**
	 * 流量结构分析--资源类别总体及分析<br>
	 * insert
	 * @param list
	 * @param jobID
	 * @return
	 */
	public int[] insertFSResourcetype(List<FSResourcetypeChildEntity> list,String jobID);
	
	/**
	 * 流量结构分析--资源类别总体及分析<br>
	 * 查询
	 * @param hdto
	 * @return
	 */
	public List<FSResourcetypeChildEntity> getFSResourcetype(HivejobDTO hdto);
	public List<FSResourcetypeChildEntity> getFSResourcetypeDate(HivejobDTO dto);
	public List<FSResourcetypeChildEntity> getFSResourcetypeCategory(HivejobDTO dto);
	

	/**
	 * 流量结构分析--top资源总体及分析<br>
	 * hive查询
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<HiveFSTopresourceEntity> hiveQueryFSTopresource(BaseDTO dto);
	
	/**
	 * 流量结构分析--top资源总体及分析<br>
	 * insert
	 * @param list
	 * @param jobID
	 * @return
	 */
	public int[] insertFSTopresource(List<HiveFSTopresourceEntity> list,String jobID);
	
	/**
	 * 流量结构分析--top资源总体及分析<br>
	 * 查询
	 * @param hdto
	 * @return
	 */
	public List<FSTopresourceEntity> getFSTopresource(HivejobDTO hdto);
	public List<FSTopresourceEntity> getFSTopresourceTotal(HivejobDTO hdto);

	
	
	
	/**
	 * 流量结构分析--自然模块数量总体及分析<br>
	 * hive查询
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSNaturemodleChildEntity> hiveQueryFSNaturemodlenum(BaseDTO dto);
	
	/**
	 * 流量结构分析--自然模块数量总体及分析<br>
	 * insert
	 * @param list
	 * @param jobID
	 * @return
	 */
	public int[] insertFSNaturemodlenum(List<FSNaturemodleChildEntity> list,String jobID);
	
	/**
	 * 流量结构分析--自然模块数量总体及分析<br>
	 * 查询
	 * @param hdto
	 * @return
	 */
	public List<FSNaturemodleChildEntity> getFSNaturemodle(HivejobDTO hdto);
	public List<FSNaturemodleChildEntity> getFSNaturemodleDate(HivejobDTO dto);
	public List<FSNaturemodleChildEntity> getFSNaturemodleSource(HivejobDTO dto);
	
	
	
	/**
	 * 流量结构分析--运营点总体及分析<br>
	 * hive查询 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<FSOperatepointEntity> hiveQueryFSOperatepoint(BaseDTO dto);
	
	/**
	 * 流量结构分析--运营点总体及分析<br>
	 * insert
	 * 
	 * @param list
	 * @param jobID
	 * @return
	 */
	public int[] insertFSOperatepoint(List<FSOperatepointEntity> list,String jobID);
	
	/**
	 * 流量结构分析--运营点总体及分析<br>
	 * 查询
	 * @param hdto
	 * @return
	 */
	public List<FSOperatepointEntity> getFSOperatepoint(HivejobDTO hdto);


	
	
	
	

	
	
	
	
	/**
	 * 产品名
	 * 
	 * @param dto
	 * @return
	 */
	public List<ProductinfoEntity> getProductinfoList(ProductinfoDTO dto);

	/**
	 * 单个资源运营--模块分布<br>
	 * hive查询
	 * 
	 * @param dto
	 * @param hdto
	 * @return
	 */
	public List<ProductrunEntity> hiveQueryProductrunmodule(ProductinfoDTO dto);
	
	/**
	 * 单个资源运营--模块分布<br>
	 * insert
	 * @param list
	 * @param jobID
	 * @return
	 */
	public int[] insertProductrunmodule(List<ProductrunEntity> list,String jobID);
	
	/**
	 * 单个资源运营--模块分布<br>
	 * 查询
	 * @param hdto
	 * @return
	 */
	public List<ProductrunEntity> getProductrunmodule(HivejobDTO hdto);

	
	
	/**
	 * 整体用户生命周期<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlWholeuserlifecycle(BaseDTO dto);
	
	/**
	 * 单个资源运营-模块分布<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlProductrunmodule(ProductinfoDTO dto);
	
	/**
	 * 新用户活跃-周<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlNewuseractiveweek(BaseDTO dto);
	
	/**
	 * 新用户活跃-月<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlNewuseractivemonth(BaseDTO dto);
	
	/**
	 * 流量结构分析--更新数量总体及分析<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlFSUpdatenum(BaseDTO dto);
	
	/**
	 * 流量结构分析--top资源总体及分析<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlFSTopresource(BaseDTO dto);
	
	/**
	 * 流量结构分析--资源类别<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlFSResourcetype(BaseDTO dto);
	
	/**
	 * 流量结构分析--自然模块<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlFSNaturemodle(BaseDTO dto);
	
	/**
	 * 月活天数和分布<br>
	 * 获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlActivedaysdistribute(BaseDTO dto);

	//删除结果表数据
	public void deleteActivedaysdistribute(HivejobDTO hdto);
	public void deleteFSNaturemodlenum(HivejobDTO hdto);
	public void deleteFSResourcetype(HivejobDTO hdto);
	public void deleteFSTopresource(HivejobDTO hdto);
	public void deleteFSUpdatenum(HivejobDTO hdto);
	public void deleteNewuseractivemonth(HivejobDTO hdto);
	public void deleteNewuseractiveweek(HivejobDTO hdto);
	public void deleteProductrunmodule(HivejobDTO hdto);
	public void deleteWholeuserlifecycle(HivejobDTO hdto);

	
	/**
	 * 模块下载-要查询的所有日期
	 * @param hdto
	 * @return
	 */
	public List<ModuledownChildEntity> getModuledownDate(HivejobDTO hdto);
	/**
	 * 模块下载-要列出的所有模块
	 * @param hdto
	 * @return
	 */
	public List<ModuledownChildEntity> getModuledownSource(HivejobDTO hdto);
	/**
	 * 模块下载-查询样本时间段内的明细
	 * @param hdto
	 * @return
	 */
	public List<ModuledownChildEntity> getModuledown(HivejobDTO hdto);
	/**
	 * 模块下载-获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlModuledown(BaseDTO dto);
	/**
	 * 模块下载-查询
	 * @param dto
	 * @return
	 */
	public List<ModuledownChildEntity> hiveQueryModuledown(BaseDTO dto);
	/**
	 * 模块下载-insert
	 * @param list
	 * @param jobID
	 */
	public void insertModuledown(List<ModuledownChildEntity> list, String jobID);
	/**
	 * 删除结果表数据
	 * @param hdto
	 */
	public void deleteModuledown(HivejobDTO hdto);

	
	/**
	 * 删除结果表数据
	 * @param hdto
	 */
	public void deleteModuleposition(HivejobDTO hdto);
	/**
	 * 模块位置下载-要查询的所有日期
	 * @param hdto
	 * @return
	 */
	public List<ModulepositionChildEntity> getModulepositionDate(HivejobDTO hdto);
	/**
	 * 模块位置下载-要列出的所有模块
	 * @param hdto
	 * @return
	 */
	public List<ModulepositionChildEntity> getModulepositionSource(
			HivejobDTO hdto);
	/**
	 * 模块位置下载-查询样本时间段内的明细
	 * @param hdto
	 * @return
	 */
	public List<ModulepositionChildEntity> getModuleposition(HivejobDTO hdto);
	/**
	 * 模块位置下载-获取sql<br>
	 * @param dto
	 * @return
	 */
	public String getHqlModuleposition(BaseDTO dto);
	/**
	 * 模块位置下载-查询
	 * @param dto
	 * @return
	 */
	public List<ModulepositionChildEntity> hiveQueryModuleposition(BaseDTO dto);
	/**
	 * 模块位置下载-insert
	 * @param list
	 * @param jobID
	 */
	public void insertModuleposition(List<ModulepositionChildEntity> list,
			String jobID);
}
