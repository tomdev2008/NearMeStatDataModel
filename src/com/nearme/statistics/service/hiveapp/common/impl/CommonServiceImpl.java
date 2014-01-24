package com.nearme.statistics.service.hiveapp.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nearme.statistics.dao.hiveapp.common.CommonDao;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.app.common.ProductinfoDTO;
import com.nearme.statistics.model.common.ActivedaysdistributeEntity;
import com.nearme.statistics.model.common.FSNaturemodleChildEntity;
import com.nearme.statistics.model.common.FSNaturemodlenumEntity;
import com.nearme.statistics.model.common.FSOperatepointEntity;
import com.nearme.statistics.model.common.FSResourcetypeChildEntity;
import com.nearme.statistics.model.common.FSResourcetypeEntity;
import com.nearme.statistics.model.common.FSTopresourceEntity;
import com.nearme.statistics.model.common.FSUpdatenumEntity;
import com.nearme.statistics.model.common.HiveFSTopresourceEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
import com.nearme.statistics.model.common.ModuledownChildEntity;
import com.nearme.statistics.model.common.ModuledownEntity;
import com.nearme.statistics.model.common.ModulepositionChildEntity;
import com.nearme.statistics.model.common.ModulepositionEntity;
import com.nearme.statistics.model.common.NewuseractiveEntity;
import com.nearme.statistics.model.common.ProductinfoEntity;
import com.nearme.statistics.model.common.ProductrunEntity;
import com.nearme.statistics.model.common.WholeuserlifecycleEntity;
import com.nearme.statistics.service.hiveapp.common.CommonService;

public class CommonServiceImpl implements CommonService {
	private CommonDao dao;

	public CommonDao getDao() {
		return dao;
	}

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

	/**
	 * 整体用户生命周期<br>
	 * hive查询存储
	 */
	public void queryAndInsertWholeuserlifecycleList(BaseDTO dto,
			HivejobDTO hdto) {
		try {
			List<WholeuserlifecycleEntity> list = dao
					.hiveQueryWholeuserlifecycle(dto);
			dao.insertWholeuserlifecycle(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	/**
	 * 整体用户生命周期<br>
	 * 从结果表中查询
	 */
	public List<WholeuserlifecycleEntity> doGetWholeuserlifecycleList(
			HivejobDTO hdto) {
		return dao.getWholeuserlifecycle(hdto);
	}

	/**
	 * 月活跃天数和分布<br>
	 * hive查询存储
	 */
	public void queryAndInsertActivedaysdistributeList(BaseDTO dto,
			HivejobDTO hdto) {
		try {
			List<ActivedaysdistributeEntity> list = dao
					.hiveQueryActivedaysdistribute(dto);
			dao.insertActivedaysdistribute(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	/**
	 * 月活跃天数和分布<br>
	 * 从结果表中查询
	 */
	public List<ActivedaysdistributeEntity> doGetActivedaysdistributeList(
			HivejobDTO hdto) {
		List<ActivedaysdistributeEntity> list = dao.getActivedaysdistribute(hdto);
		
		for (ActivedaysdistributeEntity entity : list) {
			String activedays = entity.getActivedays();
			
			activedays = activedays.substring(2);
			
			entity.setActivedays(activedays);
		}
		
		return list;
	}

	/**
	 * 新用户活跃 -- 第一个月<br>
	 * hive查询存储
	 */
	public void queryAndInsertNewuseractivemonthList(BaseDTO dto,
			HivejobDTO hdto) {
		try {
			List<NewuseractiveEntity> listmonth = dao
					.hiveQueryNewuseractivemonth(dto);
			dao.insertNewuseractivemonth(listmonth, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	/**
	 * 新用户活跃 -- 第一个月<br>
	 * 从结果表中查询
	 */
	public List<NewuseractiveEntity> doGetNewuseractivemonthList(HivejobDTO hdto) {
		return dao.getNewuseractivemonth(hdto);
	}

	/**
	 * 新用户活跃 -- 第一个周<br>
	 * hive查询存储
	 */
	public void queryAndInsertNewuseractiveweekList(BaseDTO dto, HivejobDTO hdto) {
		try {
			List<NewuseractiveEntity> listweek = dao
					.hiveQueryNewuseractiveweek(dto);
			dao.insertNewuseractiveweek(listweek, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	/**
	 * 新用户活跃 -- 第一个周<br>
	 * 从结果表中查询
	 */
	public List<NewuseractiveEntity> doGetNewuseractiveweekList(HivejobDTO hdto) {
		return dao.getNewuseractiveweek(hdto);
	}
	
	/**
	 * 内容子平台->自然模块  结果查询
	 */
	public List<FSNaturemodlenumEntity> doGetFSNaturemodlenum(HivejobDTO hdto) {
		List<FSNaturemodlenumEntity> list = new ArrayList<FSNaturemodlenumEntity>();

		// 要查询的所有日期
		List<FSNaturemodleChildEntity> dateList = dao.getFSNaturemodleDate(hdto);
		// 要列出的所有模块
		List<FSNaturemodleChildEntity> categoryList = dao.getFSNaturemodleSource(hdto);

		// 查询样本时间段内的明细
		List<FSNaturemodleChildEntity> detailList = dao.getFSNaturemodle(hdto);

		FSNaturemodlenumEntity entity = null;
		List<FSNaturemodleChildEntity> childList = null;
		FSNaturemodleChildEntity childEntity = null;
		FSNaturemodleChildEntity tmpChildEntity = null;
		Date statdate = null;
		String category = "";

		/** 总体数据 **/
		entity = new FSNaturemodlenumEntity();
		childList = new ArrayList<FSNaturemodleChildEntity>();
		//总imei 总下载
		childEntity = new FSNaturemodleChildEntity();
		for (int k = 0, size3 = detailList.size(); k < size3; k++) {
			tmpChildEntity = detailList.get(k);
			if ((tmpChildEntity.getStatDate() == null || "".equals(tmpChildEntity.getStatDate()))
					&& (tmpChildEntity.getModulename() == null || "".equals(tmpChildEntity.getModulename()))) {
				childEntity = tmpChildEntity;
			}
		}
		entity.setTotaldown(childEntity.getDowncnt());
		entity.setDownimei(childEntity.getDownimei());
		
		// 分类下载明细
		for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
			childEntity = new FSNaturemodleChildEntity();
			// 模块
			category = categoryList.get(j).getModulename();
			childEntity.setModulename(category);
			for (int k = 0, size3 = detailList.size(); k < size3; k++) {
				tmpChildEntity = detailList.get(k);
				if ((statdate == null || "".equals(statdate))
						&& category.equals(tmpChildEntity.getModulename())) {
					childEntity = tmpChildEntity;
				}
			}
			childList.add(childEntity);
		}
		entity.setList(childList);
		// 总体数据放在第一条
		list.add(entity);

		/** 明细数据 **/
		// 遍历需要展示的所有日期
		for (int i = 0, size1 = dateList.size(); i < size1; i++) {
			entity = new FSNaturemodlenumEntity();
			statdate = dateList.get(i).getStatDate();
			// 日期
			entity.setStatDate(statdate);
			childList = new ArrayList<FSNaturemodleChildEntity>();
			
			//每天总IMEI、总下载
			childEntity = new FSNaturemodleChildEntity();
			for (int k = 0, size3 = detailList.size(); k < size3; k++) {
				tmpChildEntity = detailList.get(k);
				if (statdate.equals(tmpChildEntity.getStatDate())
						&& (tmpChildEntity.getModulename() == null || "".equals(tmpChildEntity.getModulename()))) {
					childEntity = tmpChildEntity;
				}
			}
			entity.setTotaldown(childEntity.getDowncnt());
			entity.setDownimei(childEntity.getDownimei());
			
			// 每天所有分类下载明细
			for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
				childEntity = new FSNaturemodleChildEntity();
				// 资源分类
				category = categoryList.get(j).getModulename();
				for (int k = 0, size3 = detailList.size(); k < size3; k++) {
					tmpChildEntity = detailList.get(k);
					if (statdate.equals(tmpChildEntity.getStatDate())
							&& category.equals(tmpChildEntity.getModulename())) {
						childEntity = tmpChildEntity;
					}
				}
				childList.add(childEntity);
			}
			// 各个分类下载数
			entity.setList(childList);
			// 追加明细数据
			list.add(entity);
		}

		return list;
	}

	public List<FSOperatepointEntity> doGetFSOperatepoint(HivejobDTO hdto) {
		return dao.getFSOperatepoint(hdto);
	}

	/**
	 * 内容子平台->资源分类 结果查询
	 */
	public List<FSResourcetypeEntity> doGetFSResourcetype(HivejobDTO hdto) {
		List<FSResourcetypeEntity> list = new ArrayList<FSResourcetypeEntity>();

		// 要查询的所有日期
		List<FSResourcetypeChildEntity> dateList = dao.getFSResourcetypeDate(hdto);
		// 要列出的所有类别
		List<FSResourcetypeChildEntity> categoryList = dao.getFSResourcetypeCategory(hdto);

		// 查询样本时间段内的明细
		List<FSResourcetypeChildEntity> detailList = dao.getFSResourcetype(hdto);

		FSResourcetypeEntity entity = null;
		List<FSResourcetypeChildEntity> childList = null;
		FSResourcetypeChildEntity childEntity = null;
		FSResourcetypeChildEntity tmpChildEntity = null;
		Date statdate = null;
		String category = "";
		int totaldown = 0;

		/** 总体数据 **/
		entity = new FSResourcetypeEntity();
		childList = new ArrayList<FSResourcetypeChildEntity>();
		// 遍历所有需要展示的分类
		for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
			childEntity = new FSResourcetypeChildEntity();
			// 资源分类
			category = categoryList.get(j).getTypename();
			childEntity.setTypename(category);
			for (int k = 0, size3 = detailList.size(); k < size3; k++) {
				tmpChildEntity = detailList.get(k);
				if ((statdate == null || "".equals(statdate))
						&& category.equals(tmpChildEntity.getTypename())) {
					childEntity = tmpChildEntity;
				}
			}
			childList.add(childEntity);
			totaldown += childEntity.getDowncnt();
		}
		entity.setList(childList);
		entity.setTotaldown(totaldown);
		// 总体数据放在第一条
		list.add(entity);

		/** 明细数据 **/
		// 遍历需要展示的所有日期
		for (int i = 0, size1 = dateList.size(); i < size1; i++) {
			entity = new FSResourcetypeEntity();
			statdate = dateList.get(i).getStatDate();
			// 日期
			entity.setStatDate(statdate);
			childList = new ArrayList<FSResourcetypeChildEntity>();
			totaldown = 0;
			// 遍历所有需要展示的分类
			for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
				childEntity = new FSResourcetypeChildEntity();
				// 资源分类
				category = categoryList.get(j).getTypename();
				for (int k = 0, size3 = detailList.size(); k < size3; k++) {
					tmpChildEntity = detailList.get(k);
					if (statdate.equals(tmpChildEntity.getStatDate())
							&& category.equals(tmpChildEntity.getTypename())) {
						childEntity = tmpChildEntity;
					}
				}
				childList.add(childEntity);
				totaldown += childEntity.getDowncnt();
			}
			// 各个分类下载数
			entity.setList(childList);
			// 所有分类下载数和
			entity.setTotaldown(totaldown);
			// 追加明细数据
			list.add(entity);
		}

		return list;
	}

	public List<FSTopresourceEntity> doGetFSTopresource(HivejobDTO hdto) {
		return dao.getFSTopresource(hdto);
	}
	
	public List<FSTopresourceEntity> doGetFSTopresourceTotal(HivejobDTO hdto) {
		return dao.getFSTopresourceTotal(hdto);
	}

	public List<FSUpdatenumEntity> doGetFSUpdatenum(HivejobDTO hdto) {
		return dao.getFSUpdatenum(hdto);
	}
	
	public List<FSUpdatenumEntity> doGetFSUpdatenumTotal(HivejobDTO hdto) {
		return dao.getFSUpdatenumTotal(hdto);
	}
	
	/**
	 * 内容子平台->自然模块  Hive查询结果,并存到结果表
	 */
	public void queryAndInsertFSNaturemodlenum(BaseDTO dto, HivejobDTO hdto) {
		try {
			//Hive查询结果
			List<FSNaturemodleChildEntity> list = dao.hiveQueryFSNaturemodlenum(dto);
			if(null != list && list.size()>0){
				//将Hive结果写入结果表
				dao.insertFSNaturemodlenum(list, hdto.getJobID());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void queryAndInsertFSOperatepoint(BaseDTO dto, HivejobDTO hdto) {
		try {
			List<FSOperatepointEntity> list = dao.hiveQueryFSOperatepoint(dto);
			dao.insertFSOperatepoint(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	/**
	 * 内容子平台->资源分类 Hive查询结果,并存到结果表
	 */
	public void queryAndInsertFSResourcetype(BaseDTO dto, HivejobDTO hdto) {
		try {
			//Hive查询结果
			List<FSResourcetypeChildEntity> list = this.dao.hiveQueryFSResourcetype(dto);
			if(null != list && list.size()>0){
				//将Hive结果写入结果表
				dao.insertFSResourcetype(list, hdto.getJobID());
			}
			
		} catch (Exception e) {
		}
	}

	public void queryAndInsertFSTopresource(BaseDTO dto, HivejobDTO hdto) {
		try {
			List<HiveFSTopresourceEntity> list = this.dao
					.hiveQueryFSTopresource(dto);
			dao.insertFSTopresource(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	public void queryAndInsertFSUpdatenum(BaseDTO dto, HivejobDTO hdto) {
		try {
			List<FSUpdatenumEntity> list = this.dao.hiveQueryFSUpdatenum(dto);
			dao.insertFSUpdatenum(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	public List<ProductrunEntity> doGetProductrunmoduleList(HivejobDTO hdto) {
		return dao.getProductrunmodule(hdto);
	}

	public void queryAndInsertProductrunmoduleList(ProductinfoDTO dto,
			HivejobDTO hdto) {
		try {
			List<ProductrunEntity> list = dao.hiveQueryProductrunmodule(dto);
			dao.insertProductrunmodule(list, hdto.getJobID());
		} catch (Exception e) {
		}
	}

	public List<ProductinfoEntity> getProductinfoList(ProductinfoDTO dto) {
		return dao.getProductinfoList(dto);
	}

	public List<HivesqlEntity> getHqlWholeuserlifecycle(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlWholeuserlifecycle(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public List<HivesqlEntity> getHqlProductrun(ProductinfoDTO dto) {
        List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
        // 模块sql
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlProductrunmodule(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		list.add(entity);
		
		return list;
	}

	public List<HivesqlEntity> getHqlNewuseractive(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
        // 月
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlNewuseractivemonth(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		list.add(entity);
		
		// 周
		sql = dao.getHqlNewuseractiveweek(dto);
		entity = new HivesqlEntity();
		entity.setIndex(2);
		entity.setSql(sql);
		list.add(entity);
		
		return list;
	}

	public List<HivesqlEntity> getHqlActivedaysdistribute(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlActivedaysdistribute(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public List<HivesqlEntity> getHqlFSNaturemodle(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlFSNaturemodle(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public List<HivesqlEntity> getHqlFSResourcetype(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlFSResourcetype(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public List<HivesqlEntity> getHqlFSTopresource(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlFSTopresource(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public List<HivesqlEntity> getHqlFSUpdatenum(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlFSUpdatenum(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public void deleteActivedaysdistribute(HivejobDTO hdto) {
		dao.deleteActivedaysdistribute(hdto);
	}

	public void deleteFSNaturemodlenum(HivejobDTO hdto) {
		dao.deleteFSNaturemodlenum(hdto);
	}

	public void deleteFSResourcetype(HivejobDTO hdto) {
		dao.deleteFSResourcetype(hdto);
	}

	public void deleteFSTopresource(HivejobDTO hdto) {
		dao.deleteFSTopresource(hdto);
	}

	public void deleteFSUpdatenum(HivejobDTO hdto) {
		dao.deleteFSUpdatenum(hdto);
	}

	public void deleteNewuseractive(HivejobDTO hdto) {
		dao.deleteNewuseractivemonth(hdto);
		dao.deleteNewuseractiveweek(hdto);
	}

	public void deleteProductrun(HivejobDTO hdto) {
		dao.deleteProductrunmodule(hdto);
	}

	public void deleteWholeuserlifecycle(HivejobDTO hdto) {
		dao.deleteWholeuserlifecycle(hdto);
	}

	public List<ModuledownEntity> doGetModuledown(HivejobDTO hdto) {
		List<ModuledownEntity> list = new ArrayList<ModuledownEntity>();

		// 要查询的所有日期
		List<ModuledownChildEntity> dateList = dao.getModuledownDate(hdto);
		// 要列出的所有模块
		List<ModuledownChildEntity> categoryList = dao.getModuledownSource(hdto);

		// 查询样本时间段内的明细
		List<ModuledownChildEntity> detailList = dao.getModuledown(hdto);

		ModuledownEntity entity = null;
		List<ModuledownChildEntity> childList = null;
		ModuledownChildEntity childEntity = null;
		ModuledownChildEntity tmpChildEntity = null;
		Date statdate = null;
		String category = "";

		/** 总体数据 **/
		entity = new ModuledownEntity();
		childList = new ArrayList<ModuledownChildEntity>();
		//总imei 总下载
		childEntity = new ModuledownChildEntity();
		for (int k = 0, size3 = detailList.size(); k < size3; k++) {
			tmpChildEntity = detailList.get(k);
			if ((tmpChildEntity.getStatDate() == null)
					&& (tmpChildEntity.getModulename() == null || "".equals(tmpChildEntity.getModulename()))) {
				childEntity = tmpChildEntity;
			}
		}
		entity.setTotaldown(childEntity.getDowncnt());
		entity.setDownimei(childEntity.getDownimei());
		
		// 分类下载明细
		for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
			childEntity = new ModuledownChildEntity();
			// 模块
			category = categoryList.get(j).getModulename();
			childEntity.setModulename(category);
			for (int k = 0, size3 = detailList.size(); k < size3; k++) {
				tmpChildEntity = detailList.get(k);
				if ((statdate == null || "".equals(statdate))
						&& category.equals(tmpChildEntity.getModulename())) {
					childEntity = tmpChildEntity;
				}
			}
			childList.add(childEntity);
		}
		entity.setList(childList);
		// 总体数据放在第一条
		list.add(entity);

		/** 明细数据 **/
		// 遍历需要展示的所有日期
		for (int i = 0, size1 = dateList.size(); i < size1; i++) {
			entity = new ModuledownEntity();
			statdate = dateList.get(i).getStatDate();
			// 日期
			entity.setStatDate(statdate);
			childList = new ArrayList<ModuledownChildEntity>();
			
			//每天总IMEI、总下载
			childEntity = new ModuledownChildEntity();
			for (int k = 0, size3 = detailList.size(); k < size3; k++) {
				tmpChildEntity = detailList.get(k);
				if (statdate.equals(tmpChildEntity.getStatDate())
						&& (tmpChildEntity.getModulename() == null || "".equals(tmpChildEntity.getModulename()))) {
					childEntity = tmpChildEntity;
				}
			}
			entity.setTotaldown(childEntity.getDowncnt());
			entity.setDownimei(childEntity.getDownimei());
			
			// 每天所有分类下载明细
			for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
				childEntity = new ModuledownChildEntity();
				// 资源分类
				category = categoryList.get(j).getModulename();
				for (int k = 0, size3 = detailList.size(); k < size3; k++) {
					tmpChildEntity = detailList.get(k);
					if (statdate.equals(tmpChildEntity.getStatDate())
							&& category.equals(tmpChildEntity.getModulename())) {
						childEntity = tmpChildEntity;
					}
				}
				childList.add(childEntity);
			}
			// 各个分类下载数
			entity.setList(childList);
			// 追加明细数据
			list.add(entity);
		}

		return list;
	}

	public List<HivesqlEntity> getHqlModuledown(BaseDTO dto) {
        List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlModuledown(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public void queryAndInsertModuledown(BaseDTO dto, HivejobDTO hdto) {
		try {
			//Hive查询结果
			List<ModuledownChildEntity> list = dao.hiveQueryModuledown(dto);
			if(null != list && list.size()>0){
				//将Hive结果写入结果表
				dao.insertModuledown(list, hdto.getJobID());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteModuledown(HivejobDTO hdto) {
		dao.deleteModuledown(hdto);
	}

	
	
	public void deleteModuleposition(HivejobDTO hdto) {
		dao.deleteModuleposition(hdto);
	}

	public List<ModulepositionEntity> doGetModuleposition(HivejobDTO hdto) {
		List<ModulepositionEntity> list = new ArrayList<ModulepositionEntity>();

		// 要查询的所有日期
		List<ModulepositionChildEntity> dateList = dao.getModulepositionDate(hdto);
		// 要列出的所有模块
		List<ModulepositionChildEntity> categoryList = dao.getModulepositionSource(hdto);

		// 查询样本时间段内的明细
		List<ModulepositionChildEntity> detailList = dao.getModuleposition(hdto);

		ModulepositionEntity entity = null;
		List<ModulepositionChildEntity> childList = null;
		ModulepositionChildEntity childEntity = null;
		ModulepositionChildEntity tmpChildEntity = null;
		Date statdate = null;
		String category = "";

		/** 总体数据 **/
		entity = new ModulepositionEntity();
		childList = new ArrayList<ModulepositionChildEntity>();
		//总imei 总下载
		childEntity = new ModulepositionChildEntity();
		for (int k = 0, size3 = detailList.size(); k < size3; k++) {
			tmpChildEntity = detailList.get(k);
			if ((tmpChildEntity.getStatDate() == null)
					&& (tmpChildEntity.getModulename() == null || "".equals(tmpChildEntity.getModulename()))) {
				childEntity = tmpChildEntity;
			}
		}
		entity.setTotaldown(childEntity.getDowncnt());
		entity.setDownimei(childEntity.getDownimei());
		
		// 分类下载明细
		for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
			childEntity = new ModulepositionChildEntity();
			// 模块
			category = categoryList.get(j).getModulename();
			childEntity.setModulename(category);
			for (int k = 0, size3 = detailList.size(); k < size3; k++) {
				tmpChildEntity = detailList.get(k);
				if ((statdate == null || "".equals(statdate))
						&& category.equals(tmpChildEntity.getModulename())) {
					childEntity = tmpChildEntity;
				}
			}
			childList.add(childEntity);
		}
		entity.setList(childList);
		// 总体数据放在第一条
		list.add(entity);

		/** 明细数据 **/
		// 遍历需要展示的所有日期
		for (int i = 0, size1 = dateList.size(); i < size1; i++) {
			entity = new ModulepositionEntity();
			statdate = dateList.get(i).getStatDate();
			// 日期
			entity.setStatDate(statdate);
			childList = new ArrayList<ModulepositionChildEntity>();
			
			//每天总IMEI、总下载
			childEntity = new ModulepositionChildEntity();
			for (int k = 0, size3 = detailList.size(); k < size3; k++) {
				tmpChildEntity = detailList.get(k);
				if (statdate.equals(tmpChildEntity.getStatDate())
						&& (tmpChildEntity.getModulename() == null || "".equals(tmpChildEntity.getModulename()))) {
					childEntity = tmpChildEntity;
				}
			}
			entity.setTotaldown(childEntity.getDowncnt());
			entity.setDownimei(childEntity.getDownimei());
			
			// 每天所有分类下载明细
			for (int j = 0, size2 = categoryList.size(); j < size2; j++) {
				childEntity = new ModulepositionChildEntity();
				// 资源分类
				category = categoryList.get(j).getModulename();
				for (int k = 0, size3 = detailList.size(); k < size3; k++) {
					tmpChildEntity = detailList.get(k);
					if (statdate.equals(tmpChildEntity.getStatDate())
							&& category.equals(tmpChildEntity.getModulename())) {
						childEntity = tmpChildEntity;
					}
				}
				childList.add(childEntity);
			}
			// 各个分类下载数
			entity.setList(childList);
			// 追加明细数据
			list.add(entity);
		}

		return list;
	}

	public List<HivesqlEntity> getHqlModuleposition(BaseDTO dto) {
		List<HivesqlEntity> list = new ArrayList<HivesqlEntity>();
		
		HivesqlEntity entity = new HivesqlEntity();
		String sql = dao.getHqlModuleposition(dto);
		entity.setIndex(1);
		entity.setSql(sql);
		
		list.add(entity);
		
		return list;
	}

	public void queryAndInsertModuleposition(BaseDTO dto, HivejobDTO hdto) {
		try {
			//Hive查询结果
			List<ModulepositionChildEntity> list = dao.hiveQueryModuleposition(dto);
			if(null != list && list.size()>0){
				//将Hive结果写入结果表
				dao.insertModuleposition(list, hdto.getJobID());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
