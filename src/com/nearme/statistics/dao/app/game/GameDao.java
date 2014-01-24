package com.nearme.statistics.dao.app.game;

import java.util.List;

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

public interface GameDao {
	// 游戏信息
	public List<GameinfoEntity> getGameinfoList(GameDTO dto);
	
	// 游戏名2
	public List<GameinfoEntity> getGameList(GameDTO dto);

	// 游戏下载
	public List<DownloadEntity> getDownloadList(GameDTO dto);

	// 首页下载
	public List<DownhomepageEntity> getDownhomepageList(GameDTO dto);

	// 排行榜下载
	public List<DownrankEntity> getDownrankList(GameDTO dto);

	// 分类下载
	public List<DownsortEntity> getDownsortList(GameDTO dto);
	public List<DownsortEntity> getDownsorttopList(GameDTO dto);

	// 专题下载
	public List<DownspecialtopicEntity> getDownspecialtopicList(GameDTO dto);

	// 搜索下载
	public List<DownsearchEntity> getDownsearchList(GameDTO dto);

	// 搜推下载
	public List<DownsearchrecommendEntity> getDownsearchrecommendList(
			GameDTO dto);

	// 活动中心下载
	public List<DownactivecenterEntity> getDownactivecenterList(GameDTO dto);
	
	// 更新
	public List<UpdateEntity> getUpdateList(GameDTO dto);
	
	// 启动
	public List<StartEntity> getStartList(GameDTO dto);
	
	//排行
	public List<RankEntity> getRanktop30List(GameDTO dto);
	public List<RankEntity> getRankpositionList(GameDTO dto);
	
	//搜索关键字
	public List<SearchkeyEntity> getSearchkeyList(GameDTO dto);
	
	//产品列表
	public List<ProductEntity> getProductList(GameDTO dto);
	
	//产品日报
	public List<ProductreportdailyEntity> getProductreportdailyList(GameDTO dto);
	
	// 产品周报
	public List<ProductreportweeklyEntity> getProductreportweeklyList(GameDTO dto);

	// 产品月报
	public List<ProductreportmonthlyEntity> getProductreportmonthlyList(
			GameDTO dto);
	
	// 联运游戏数据
	public List<JointgameEntity> getJointgameList(GameDTO dto);
	
	// 留存分析
	public List<GameRemainEntity> getRemainList(GameDTO dto);
	
	// 付费分析
	public List<GameFeeEntity> getFeeList(GameDTO dto);
}
