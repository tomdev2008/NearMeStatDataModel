package com.nearme.statistics.service.app.liren.impl;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dao.app.common.CommonDao;
import com.nearme.statistics.dao.app.liren.LirenDao;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.model.liren.LRCatchgoodsEntity;
import com.nearme.statistics.model.liren.LRCategoryEntity;
import com.nearme.statistics.model.liren.LRGoodsEntity;
import com.nearme.statistics.model.liren.LRGoodsqualityEntity;
import com.nearme.statistics.model.liren.LRHomepageEntity;
import com.nearme.statistics.model.liren.LRPushEntity;
import com.nearme.statistics.model.liren.LRTaghotEntity;
import com.nearme.statistics.model.liren.LRVersion4upEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;

public class LirenServiceImpl implements LirenService {
	private LirenDao dao;
	private CommonDao commondao;

	public LirenDao getDao() {
		return dao;
	}

	public void setDao(LirenDao dao) {
		this.dao = dao;
	}

	public CommonDao getCommondao() {
		return commondao;
	}

	public void setCommondao(CommonDao commondao) {
		this.commondao = commondao;
	}

	/**
	 * 首页统计
	 */
	public List<LRHomepageEntity> getHomepageList(LirenDTO dto) {
		return dao.getHomepageList(dto);
	}

	/**
	 * 类目统计
	 */
	public List<LRCategoryEntity> getCategoryList(LirenDTO dto) {
		List<LRCategoryEntity> list = dao.getCategoryList(dto);

		for (LRCategoryEntity entity : list) {
			long browseCnt = entity.getBrowseCnt();
			long likeCnt = entity.getLikeCnt();
			long visitImei = entity.getVisitImei();

			float avgbrowse = visitImei == 0 ? 0 : Math.round(browseCnt * 100
					/ (float) visitImei)
					/ (float) 100;
			float avglike = visitImei == 0 ? 0 : Math.round(likeCnt * 100
					/ (float) visitImei)
					/ (float) 100;

			entity.setAvgbrowse(avgbrowse);
			entity.setAvglike(avglike);
		}

		return list;
	}

	/**
	 * 商品统计
	 */
	public List<LRGoodsEntity> getGoodsList(LirenDTO dto) {
		List<LRGoodsEntity> list = dao.getGoodsList(dto);

		for (LRGoodsEntity entity : list) {
			long newbrowseTotal = entity.getNewbrowseTotal();
			long newbrowseDetail = entity.getNewbrowseDetail();
			long newlike = entity.getNewlike();

			long deviceCnt = entity.getDeviceCnt();

			float avgnewbrowseTotal = deviceCnt == 0 ? 0 : Math
					.round(newbrowseTotal * 100 / (float) deviceCnt)
					/ (float) 100;
			float avgnewbrowseDetail = deviceCnt == 0 ? 0 : Math
					.round(newbrowseDetail * 100 / (float) deviceCnt)
					/ (float) 100;
			float avgnewlike = deviceCnt == 0 ? 0 : Math.round(newlike * 100
					/ (float) deviceCnt)
					/ (float) 100;

			entity.setAvgnewbrowseTotal(avgnewbrowseTotal);
			entity.setAvgnewbrowseDetail(avgnewbrowseDetail);
			entity.setAvgnewlike(avgnewlike);
		}

		return list;
	}

	/**
	 * 商品质量分布(单个查询)
	 */
	public List<LRGoodsqualityEntity> getGoodsqualityList(LirenDTO dto) {
		List<LRGoodsqualityEntity> list = dao.getGoodsqualityList(dto);

		long total = 0;

		// 计算总量
		for (LRGoodsqualityEntity entity : list) {
			long shuliang1 = entity.getNumb();

			total += shuliang1;
		}

		// 计算占比
		for (LRGoodsqualityEntity entity : list) {
			String betweentype = entity.getBetweentype();
			long numb = entity.getNumb();

			float zhanbi = total == 0 ? 0 : Math.round(numb * 10000
					/ (float) total)
					/ (float) 100;

			if (betweentype.contains("#")) {
				betweentype = betweentype.substring(
						betweentype.indexOf("#") + 1, betweentype.length());
			}

			entity.setBetweentype(betweentype);
			entity.setZanbi(zhanbi);
		}

		return list;
	}

	/**
	 * 标签热度统计
	 */
	public List<LRTaghotEntity> getTaghotList(LirenDTO dto) {
		List<LRTaghotEntity> list = dao.getTaghotList(dto);

		long dayliketotal = 0;

		// 计算日总量
		for (LRTaghotEntity entity : list) {
			dayliketotal += entity.getLikecnt();
		}

		// 填充占比
		for (LRTaghotEntity entity : list) {
			long likecnt = entity.getLikecnt();
			float zanbi = dayliketotal == 0 ? 0 : Math.round(likecnt * 10000
					/ (float) dayliketotal)
					/ (float) 100;
			entity.setZanbi(zanbi);
		}

		return list;
	}

	public List<LRPushEntity> getPushList(LirenDTO dto) {
		return dao.getPushList(dto);
	}

	public List<LRCatchgoodsEntity> getCatchgoodsList(LirenDTO dto) {
		return dao.getCatchgoodsList(dto);
	}

	/**
	 * V4.0以上版本数据
	 */
	public List<LRVersion4upEntity> getVersion4upList(LirenDTO dto) {
		List<LRVersion4upEntity> list = dao.getVersion4upList(dto);
		
		for (LRVersion4upEntity entity : list) {
			long startlrrcnt = entity.getStartlrrcnt();
			long newlrrcnt = entity.getNewlrrcnt();
			long newimei = entity.getNewimei();
			long newimeitotal  = entity.getNewimeitotal();
			long startimei = entity.getStartimei();
			long startimeitotal = entity.getStartimeitotal();

			float startlrr = startimei == 0 ? 0 : Math.round(startlrrcnt * 10000 / startimei)
					/ (float) 100;
			float newlrr = newimei == 0 ? 0 : Math.round(newlrrcnt * 10000 / newimei)
					/ (float) 100;
			float newimeiratio = newimeitotal == 0 ? 0 : Math
					.round(newimei * 10000 / newimeitotal)
					/ (float) 100;
			float startimeiratio = startimeitotal == 0 ? 0 : Math
					.round(startimei * 10000 / startimeitotal)
					/ (float) 100;

			entity.setStartlrr(startlrr);
			entity.setNewlrr(newlrr);
			entity.setNewimeiratio(newimeiratio);
			entity.setStartimeiratio(startimeiratio);

			Date statDate = entity.getStatDate();
			entity.setStatDate(statDate);
			if (Constants.WEEKLY.equals(dto.getLidu())) {
				Date statEndDate = DateUtil.parseDate(DateUtil.getDateOfXdaysAgo(statDate, -6, "yyyy-MM-dd"), "yyyy-MM-dd");
				entity.setStatEndDate(statEndDate);
			}
		}

		return list;
	}
}
