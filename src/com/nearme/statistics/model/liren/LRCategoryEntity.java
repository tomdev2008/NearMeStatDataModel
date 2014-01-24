package com.nearme.statistics.model.liren;

import java.io.Serializable;
import java.util.Date;

/**
 * 类目Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 * @version 1.1, 2013-11-19
 */
public class LRCategoryEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String category;// 类目
	private long shangxinTotal;// 上新总量
	private long shangxinCnt;// 上新次数
	private long browseCnt;// 浏览数
	private long likeCnt;// 喜欢数
	private long visitImei;// 访问设备数
	private long visitCnt;// 访问次数
	private float avgbrowse;// 每浏览商品数
	private float avglike;// 每喜欢商品数
	
	private long ysxtotal;// 应上新总量
	private long ysxcnt;// 应上新次数
	private String recentsxtime;// 最近一批商品上新时间
	private long recentcnt;// 最近一批商品数量
	private float avgrecentbrowse;// 最近一批商品平均被浏览量
	private long browsenewimei;// 浏览“最新”设备数
	private long browsenewcnt;// 浏览“最新”次数

	public long getBrowseCnt() {
		return browseCnt;
	}

	public void setBrowseCnt(long browseCnt) {
		this.browseCnt = browseCnt;
	}

	public long getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(long likeCnt) {
		this.likeCnt = likeCnt;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getShangxinTotal() {
		return shangxinTotal;
	}

	public void setShangxinTotal(long shangxinTotal) {
		this.shangxinTotal = shangxinTotal;
	}

	public long getShangxinCnt() {
		return shangxinCnt;
	}

	public void setShangxinCnt(long shangxinCnt) {
		this.shangxinCnt = shangxinCnt;
	}

	public float getAvgbrowse() {
		return avgbrowse;
	}

	public void setAvgbrowse(float avgbrowse) {
		this.avgbrowse = avgbrowse;
	}

	public float getAvglike() {
		return avglike;
	}

	public void setAvglike(float avglike) {
		this.avglike = avglike;
	}

	public long getVisitImei() {
		return visitImei;
	}

	public void setVisitImei(long visitImei) {
		this.visitImei = visitImei;
	}

	public long getVisitCnt() {
		return visitCnt;
	}

	public void setVisitCnt(long visitCnt) {
		this.visitCnt = visitCnt;
	}

	public long getYsxtotal() {
		return ysxtotal;
	}

	public void setYsxtotal(long ysxtotal) {
		this.ysxtotal = ysxtotal;
	}

	public long getYsxcnt() {
		return ysxcnt;
	}

	public void setYsxcnt(long ysxcnt) {
		this.ysxcnt = ysxcnt;
	}

	public long getRecentcnt() {
		return recentcnt;
	}

	public void setRecentcnt(long recentcnt) {
		this.recentcnt = recentcnt;
	}

	public long getBrowsenewimei() {
		return browsenewimei;
	}

	public void setBrowsenewimei(long browsenewimei) {
		this.browsenewimei = browsenewimei;
	}

	public long getBrowsenewcnt() {
		return browsenewcnt;
	}

	public void setBrowsenewcnt(long browsenewcnt) {
		this.browsenewcnt = browsenewcnt;
	}

	public String getRecentsxtime() {
		return recentsxtime;
	}

	public void setRecentsxtime(String recentsxtime) {
		this.recentsxtime = recentsxtime;
	}

	public float getAvgrecentbrowse() {
		return avgrecentbrowse;
	}

	public void setAvgrecentbrowse(float avgrecentbrowse) {
		this.avgrecentbrowse = avgrecentbrowse;
	}

}
