package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 单个资源下载——CPT
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-13
 */
public class DownsourceCPTEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long totalDown;// 总下载量
	private long totalBrowse;// 总浏览量
	private long hottuijian;// 热门推荐
	private long editortujian;// 小编推荐
	private long app;// 应用榜
	private long game;// 游戏榜
	private long pop;// 最近流行
	private long fenlei;// 分类精选
	private long secondfenlei;// 二级分类
	private long allsearch;// 大家都在搜
	private long hotsearch;// 48小时热搜榜
	private long searchrank;// 搜索排行榜
	private long tosearch;// 主动搜索
	private long installmust;// 装机必备
	private long recommondpush;// 相关推荐
	private long downgift;// 下载有礼
	private long upgrade;// 升级
	private long resourceads;// 单个资源广告
	private long activepage;// 活动页面

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public Date getStatEndDate() {
		return statEndDate;
	}

	public void setStatEndDate(Date statEndDate) {
		this.statEndDate = statEndDate;
	}

	public long getTotalDown() {
		return totalDown;
	}

	public void setTotalDown(long totalDown) {
		this.totalDown = totalDown;
	}

	public long getTotalBrowse() {
		return totalBrowse;
	}

	public void setTotalBrowse(long totalBrowse) {
		this.totalBrowse = totalBrowse;
	}

	public long getHottuijian() {
		return hottuijian;
	}

	public void setHottuijian(long hottuijian) {
		this.hottuijian = hottuijian;
	}

	public long getEditortujian() {
		return editortujian;
	}

	public void setEditortujian(long editortujian) {
		this.editortujian = editortujian;
	}

	public long getApp() {
		return app;
	}

	public void setApp(long app) {
		this.app = app;
	}

	public long getGame() {
		return game;
	}

	public void setGame(long game) {
		this.game = game;
	}

	public long getPop() {
		return pop;
	}

	public void setPop(long pop) {
		this.pop = pop;
	}

	public long getFenlei() {
		return fenlei;
	}

	public void setFenlei(long fenlei) {
		this.fenlei = fenlei;
	}

	public long getSecondfenlei() {
		return secondfenlei;
	}

	public void setSecondfenlei(long secondfenlei) {
		this.secondfenlei = secondfenlei;
	}

	public long getAllsearch() {
		return allsearch;
	}

	public void setAllsearch(long allsearch) {
		this.allsearch = allsearch;
	}

	public long getHotsearch() {
		return hotsearch;
	}

	public void setHotsearch(long hotsearch) {
		this.hotsearch = hotsearch;
	}

	public long getSearchrank() {
		return searchrank;
	}

	public void setSearchrank(long searchrank) {
		this.searchrank = searchrank;
	}

	public long getTosearch() {
		return tosearch;
	}

	public void setTosearch(long tosearch) {
		this.tosearch = tosearch;
	}

	public long getInstallmust() {
		return installmust;
	}

	public void setInstallmust(long installmust) {
		this.installmust = installmust;
	}

	public long getRecommondpush() {
		return recommondpush;
	}

	public void setRecommondpush(long recommondpush) {
		this.recommondpush = recommondpush;
	}

	public long getDowngift() {
		return downgift;
	}

	public void setDowngift(long downgift) {
		this.downgift = downgift;
	}

	public long getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(long upgrade) {
		this.upgrade = upgrade;
	}

	public long getResourceads() {
		return resourceads;
	}

	public void setResourceads(long resourceads) {
		this.resourceads = resourceads;
	}

	public long getActivepage() {
		return activepage;
	}

	public void setActivepage(long activepage) {
		this.activepage = activepage;
	}
}
