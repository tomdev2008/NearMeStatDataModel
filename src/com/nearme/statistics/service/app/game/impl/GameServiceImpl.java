package com.nearme.statistics.service.app.game.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.HttpGCPay;
import com.nearme.statistics.dao.app.game.GameDao;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.model.game.DownactivecenterEntity;
import com.nearme.statistics.model.game.DownhomepageEntity;
import com.nearme.statistics.model.game.DownloadEntity;
import com.nearme.statistics.model.game.DownrankEntity;
import com.nearme.statistics.model.game.DownsearchEntity;
import com.nearme.statistics.model.game.DownsearchrecommendEntity;
import com.nearme.statistics.model.game.DownsortEntity;
import com.nearme.statistics.model.game.DownspecialtopicEntity;
import com.nearme.statistics.model.game.GameFeeEntity;
import com.nearme.statistics.model.game.GameFeeUserEntity;
import com.nearme.statistics.model.game.GameRemainEntity;
import com.nearme.statistics.model.game.GameinfoEntity;
import com.nearme.statistics.model.game.JointgameEntity;
import com.nearme.statistics.model.game.ProductEntity;
import com.nearme.statistics.model.game.ProductreportdailyEntity;
import com.nearme.statistics.model.game.ProductreportmonthlyEntity;
import com.nearme.statistics.model.game.ProductreportweeklyEntity;
import com.nearme.statistics.model.game.RankEntity;
import com.nearme.statistics.model.game.SearchkeyEntity;
import com.nearme.statistics.model.game.StartEntity;
import com.nearme.statistics.model.game.UpdateEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;

public class GameServiceImpl implements GameService {
	private GameDao dao;

	public GameDao getDao() {
		return dao;
	}

	public void setDao(GameDao dao) {
		this.dao = dao;
	}

	/**
	 * 游戏信息-从商店查询结果id + 1亿
	 */
	public List<GameinfoEntity> getGameinfoList(GameDTO dto) {
		List<GameinfoEntity> list = dao.getGameinfoList(dto);

		for (GameinfoEntity entity : list) {
			long productID = entity.getProductID() + 100000000;
			entity.setProductID(productID);
		}

		return list;
	}

	/**
	 * 游戏名2
	 */
	public List<GameinfoEntity> getGameList(GameDTO dto) {
		return dao.getGameList(dto);
	}

	/**
	 * 游戏下载
	 */
	public List<DownloadEntity> getDownloadList(GameDTO dto) {
		return dao.getDownloadList(dto);
	}

	/**
	 * 首页下载
	 */
	public List<DownhomepageEntity> getDownhomepageList(GameDTO dto) {
		return dao.getDownhomepageList(dto);
	}

	/**
	 * 排行榜下载
	 */
	public List<DownrankEntity> getDownrankList(GameDTO dto) {
		return dao.getDownrankList(dto);
	}

	/**
	 * 分类下载
	 */
	public List<DownsortEntity> getDownsortList(GameDTO dto) {
		List<DownsortEntity> list = dao.getDownsortList(dto);
		List<DownsortEntity> listtop = dao.getDownsorttopList(dto);// 查询top1,2,3

		// 组合结果
		for (DownsortEntity entity : list) {
			String statDate = DateUtil.formatDate(entity.getStatDate(),
					"yyyyMMdd");

			for (int i = 0; i < listtop.size(); i++) {
				DownsortEntity topent = listtop.get(i);

				String date = DateUtil.formatDate(topent.getStatDate(),
						"yyyyMMdd");
				if (date.equalsIgnoreCase(statDate)) {
					String categoryname1 = topent.getCatname();
					long top1 = topent.getTopvalue();

					topent = listtop.get(i + 1);
					String categoryname2 = topent.getCatname();
					long top2 = topent.getTopvalue();

					topent = listtop.get(i + 2);
					String categoryname3 = topent.getCatname();
					long top3 = topent.getTopvalue();

					entity.setCategoryname1(categoryname1);
					entity.setTop1(top1);
					entity.setCategoryname2(categoryname2);
					entity.setTop2(top2);
					entity.setCategoryname3(categoryname3);
					entity.setTop3(top3);

					break;
				}
			}
		}

		return list;
	}

	/**
	 * 专题下载
	 */
	public List<DownspecialtopicEntity> getDownspecialtopicList(GameDTO dto) {
		return dao.getDownspecialtopicList(dto);
	}

	/**
	 * 搜索下载
	 */
	public List<DownsearchEntity> getDownsearchList(GameDTO dto) {
		return dao.getDownsearchList(dto);
	}

	/**
	 * 搜推下载
	 */
	public List<DownsearchrecommendEntity> getDownsearchrecommendList(
			GameDTO dto) {
		return dao.getDownsearchrecommendList(dto);
	}

	/**
	 * 活动中心下载
	 */
	public List<DownactivecenterEntity> getDownactivecenterList(GameDTO dto) {
		return dao.getDownactivecenterList(dto);
	}

	/**
	 * 更新
	 */
	public List<UpdateEntity> getUpdateList(GameDTO dto) {
		return dao.getUpdateList(dto);
	}

	/**
	 * 启动
	 */
	public List<StartEntity> getStartList(GameDTO dto) {
		return dao.getStartList(dto);
	}

	/**
	 * 排行位置
	 */
	public List<RankEntity> getRankpositionList(GameDTO dto) {
		return dao.getRankpositionList(dto);
	}

	/**
	 * 排行top30
	 */
	public List<RankEntity> getRanktop30List(GameDTO dto) {
		List<RankEntity> list = dao.getRanktop30List(dto);

		// 计算总数
		String position = "总";
		long totaldirectdown = 0;
		long totaldetailbrowse = 0;
		long totaldetaildown = 0;
		for (RankEntity entity : list) {
			long directdown = entity.getDirectdown();
			long detailbrowse = entity.getDetailbrowse();
			long detaildown = entity.getDetaildown();

			totaldirectdown += directdown;
			totaldetailbrowse += detailbrowse;
			totaldetaildown += detaildown;
		}

		// 添加总数据
		RankEntity entity = new RankEntity();
		entity.setPosition(position);
		entity.setDirectdown(totaldirectdown);
		entity.setDetailbrowse(totaldetailbrowse);
		entity.setDetaildown(totaldetaildown);
		list.add(0, entity);

		return list.subList(0, 31);// 只要前31条数据
	}

	/**
	 * 搜索关键字
	 */
	public List<SearchkeyEntity> getSearchkeyList(GameDTO dto) {
		return dao.getSearchkeyList(dto);
	}

	/**
	 * 产品列表
	 */
	public List<ProductEntity> getProductList(GameDTO dto) {
		return dao.getProductList(dto);
	}

	/**
	 * 产品日报
	 */
	public List<ProductreportdailyEntity> getProductreportdailyList(GameDTO dto) {
		return dao.getProductreportdailyList(dto);
	}

	/**
	 * 产品月报
	 */
	public List<ProductreportmonthlyEntity> getProductreportmonthlyList(
			GameDTO dto) {
		return dao.getProductreportmonthlyList(dto);
	}

	/**
	 * 产品周报
	 */
	public List<ProductreportweeklyEntity> getProductreportweeklyList(
			GameDTO dto) {
		List<ProductreportweeklyEntity> list = dao
				.getProductreportweeklyList(dto);

		for (int i = 0; i < list.size(); i++) {
			ProductreportweeklyEntity entity = (ProductreportweeklyEntity) list
					.get(i);
			Date statdate = entity.getStatDate();
			Date enddate = DateUtil.AddDays(statdate, 6);

			String weekStr = String.format("第%s周（%s至%s）", DateUtil
					.getWeekDay(statdate), DateUtil.formatDate(statdate,
					"MM-dd"), DateUtil.formatDate(enddate, "MM-dd"));
			entity.setWeekStr(weekStr);
			list.set(i, entity);
		}

		return list;
	}

	/**
	 * 联运游戏数据
	 */
	public List<JointgameEntity> getJointgameList(GameDTO dto) {
		List<JointgameEntity> list = null;
		try{
			//联运游戏基础数据(启动、留存、流失等)
			list = dao.getJointgameList(dto);
			if(list==null){
				return null;
			}
			
			//获取游戏付费信息
			List<JointgameEntity> payList = new ArrayList<JointgameEntity>();
			List<JointgameEntity> tmpList = null;
			Date startDate = dto.getStartDate();
			Date endDate = dto.getEndDate();
			Date tmpDate = null;
			long days = DateUtil.getDays(startDate, endDate);
			long requests = (days-1)/HttpGCPay.MAX_SIZE;
			//限制, 最多请求50次(防止误操作)
			requests = requests>50 ? 50 : requests;
			for(int i=0; i<requests; i++){
				tmpDate = DateUtil.AddDays(startDate, HttpGCPay.MAX_SIZE-1);
				dto.setStartDate(startDate);
				dto.setEndDate(tmpDate);
				//调用付费接口,获取付费信息
				tmpList = HttpGCPay.getSimpleStatData(dto);
				if(tmpList!=null && tmpList.size()>0){
					payList.addAll( tmpList );
				}
				startDate = DateUtil.AddDays(tmpDate, 1);
			}
			dto.setStartDate(startDate);
			dto.setEndDate(endDate);
			//调用付费接口,获取付费信息
			tmpList = HttpGCPay.getSimpleStatData(dto);
			if(tmpList!=null && tmpList.size()>0){
				payList.addAll( tmpList );
			}
			
			//拼接基础数据和付费信息
			JointgameEntity item = null;
			JointgameEntity tmpEntity = null;
			if(payList!=null && payList.size()>0){
				for(int i=0,size=list.size(); i<size; i++){
					item = list.get(i);
					for(int j=0,length=payList.size(); j<length; j++){
						tmpEntity = payList.get(j);
						if(tmpEntity.getStatDateStr().equals(item.getStatDateStr())){
							item.setFeeuser(tmpEntity.getFeeuser());
							item.setFeetotal(tmpEntity.getFeetotal()/100);
							item.setArppu(tmpEntity.getArppu()/100);
							float feeratio = item.getLoginuser()==0 ? 0 : item.getFeeuser()*(float) 1/item.getLoginuser();
							item.setFeeratio(feeratio);
							float arpu = item.getLoginuser()==0 ? 0 : item.getFeetotal()/item.getLoginuser();
							item.setArpu(arpu);
						}
					}
				}
			}
			//格式化数据
			for (JointgameEntity entity : list) {
				float remain2 = Math.round(entity.getRemain2() * 10000)
						/ (float) 100;
				float remain7 = Math.round(entity.getRemain7() * 10000)
						/ (float) 100;
				float feeratio = Math.round(entity.getFeeratio() * 100000)
						/ (float) 1000;
				float arpu = Math.round(entity.getArpu() * 1000) / (float) 1000;
				float arppu = Math.round(entity.getArppu() * 100) / (float) 100;
	
				// 防止出现负数
				long lost7 = entity.getLost7();
				long lost30 = entity.getLost30();
				lost7 = lost7 < 0 ? 0 : lost7;
				lost30 = lost30 < 0 ? 0 : lost30;
	
				entity.setRemain2(remain2);
				entity.setRemain7(remain7);
				entity.setFeeratio(feeratio);
				entity.setArpu(arpu);
				entity.setArppu(arppu);
				entity.setLost7(lost7);
				entity.setLost30(lost30);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 留存分析
	 */
	public List<GameRemainEntity> getRemainList(GameDTO dto) {
		List<GameRemainEntity> list = dao.getRemainList(dto);
		
		for (GameRemainEntity entity : list) {
			float remainratio = entity.getRemainratio();
			float imeiremainratio = entity.getImeiremainratio();
			
			remainratio = Math.round(remainratio * 10000) / (float) 100;
			imeiremainratio = Math.round(imeiremainratio * 10000) / (float) 100;
			
			entity.setRemainratio(remainratio);
			entity.setImeiremainratio(imeiremainratio);
		}
		
		return list;
	}

	/**
	 * 每天新增鲸鱼、总鲸鱼查询
	 */
	public List<GameFeeEntity> getFeeList(GameDTO dto) {
		List<GameFeeEntity> list = new ArrayList<GameFeeEntity>();
		try{
			//获取鲸鱼用户统计信息
			List<GameFeeEntity> tmpList = null;
			Date startDate = dto.getStartDate();
			Date endDate = dto.getEndDate();
			Date tmpDate = null;
			long days = DateUtil.getDays(startDate, endDate);
			long requests = (days-1)/HttpGCPay.MAX_SIZE;
			//限制, 最多请求20次(防止误操作)
			requests = requests>20 ? 20 : requests;
			for(int i=0; i<requests; i++){
				tmpDate = DateUtil.AddDays(startDate, HttpGCPay.MAX_SIZE-1);
				dto.setStartDate(startDate);
				dto.setEndDate(tmpDate);
				//调用接口,获取鲸鱼用户统计信息
				tmpList = HttpGCPay.getWhaleStatData(dto);
				if(tmpList!=null && tmpList.size()>0){
					list.addAll( tmpList );
				}
				startDate = DateUtil.AddDays(tmpDate, 1);
			}
			dto.setStartDate(startDate);
			dto.setEndDate(endDate);
			//调用接口,获取鲸鱼用户统计信息
			tmpList = HttpGCPay.getWhaleStatData(dto);
			if(tmpList!=null && tmpList.size()>0){
				list.addAll( tmpList );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 鲸鱼用户付费信息查询
	 */
	public List<GameFeeUserEntity> getFeeUserList(GameDTO dto) {
		List<GameFeeUserEntity> list = new ArrayList<GameFeeUserEntity>();
		try{
			//获取鲸鱼用户详细信息
			List<GameFeeUserEntity> tmpList = null;
			int records = dto.getRecords();
			int requests = (records-1)/HttpGCPay.MAX_SIZE;
			int start = 0;
			//限制, 最多请求20次(防止误操作)
			requests = requests>20 ? 20 : requests;
			//一次请求默认[HttpGCPay.MAX_SIZE]条数据
			dto.setSize(HttpGCPay.MAX_SIZE);
			for(int i=0; i<requests; i++){
				dto.setStart(start);
				//调用接口,获取鲸鱼用户详细信息
				tmpList = HttpGCPay.getWhaleUsers(dto);
				if(tmpList!=null && tmpList.size()>0){
					list.addAll( tmpList );
				}
				start = start + HttpGCPay.MAX_SIZE;
			}
			dto.setStart(start);
			//调用接口,获取鲸鱼用户详细信息
			tmpList = HttpGCPay.getWhaleUsers(dto);
			if(tmpList!=null && tmpList.size()>0){
				list.addAll( tmpList );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
