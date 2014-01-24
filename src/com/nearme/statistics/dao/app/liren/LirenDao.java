package com.nearme.statistics.dao.app.liren;

import java.util.List;

import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.model.liren.LRCatchgoodsEntity;
import com.nearme.statistics.model.liren.LRCategoryEntity;
import com.nearme.statistics.model.liren.LRGoodsEntity;
import com.nearme.statistics.model.liren.LRGoodsqualityEntity;
import com.nearme.statistics.model.liren.LRHomepageEntity;
import com.nearme.statistics.model.liren.LRPushEntity;
import com.nearme.statistics.model.liren.LRTaghotEntity;
import com.nearme.statistics.model.liren.LRVersion4upEntity;

public interface LirenDao {
	// 首页统计
	public List<LRHomepageEntity> getHomepageList(LirenDTO dto);

	// 类目统计
	public List<LRCategoryEntity> getCategoryList(LirenDTO dto);

	// 商品统计
	public List<LRGoodsEntity> getGoodsList(LirenDTO dto);

	// 商品质量分布
	public List<LRGoodsqualityEntity> getGoodsqualityList(LirenDTO dto);

	// 标签热度统计
	public List<LRTaghotEntity> getTaghotList(LirenDTO dto);
	
	// push数据
	public List<LRPushEntity> getPushList(LirenDTO dto);
	
	// 抓取商品
	public List<LRCatchgoodsEntity> getCatchgoodsList(LirenDTO dto);

	// V4.0以上版本数据
	public List<LRVersion4upEntity> getVersion4upList(LirenDTO dto);
}
