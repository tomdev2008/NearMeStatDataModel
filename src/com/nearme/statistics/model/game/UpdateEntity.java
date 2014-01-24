package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 游戏更新Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class UpdateEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long updatecnt;// 更新
	private long homepage;// 首页更新
	private long rank;// 排行榜更新
	private long sort;// 分类更新
	private long specialtopic;// 专题更新
	private long search;// 搜索更新
	private long myupdate;// 我的更新
	private long detail;// 详情更新
	private long activecenter;// 活动中心更新

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

	public long getUpdatecnt() {
		return updatecnt;
	}

	public void setUpdatecnt(long updatecnt) {
		this.updatecnt = updatecnt;
	}

	public long getHomepage() {
		return homepage;
	}

	public void setHomepage(long homepage) {
		this.homepage = homepage;
	}

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}

	public long getSpecialtopic() {
		return specialtopic;
	}

	public void setSpecialtopic(long specialtopic) {
		this.specialtopic = specialtopic;
	}

	public long getSearch() {
		return search;
	}

	public void setSearch(long search) {
		this.search = search;
	}

	public long getMyupdate() {
		return myupdate;
	}

	public void setMyupdate(long myupdate) {
		this.myupdate = myupdate;
	}

	public long getDetail() {
		return detail;
	}

	public void setDetail(long detail) {
		this.detail = detail;
	}

	public long getActivecenter() {
		return activecenter;
	}

	public void setActivecenter(long activecenter) {
		this.activecenter = activecenter;
	}

}
