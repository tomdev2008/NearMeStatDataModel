package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 下载Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class DownloadEntity implements Serializable {
	private static final long serialVersionUID = -6797489204506508587L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long down;// 下载
	private long downHomepage;// 首页下载
	private long downRank;// 排行榜下载
	private long downSort;// 分类下载
	private long downSpecialtopic;// 专题下载
	private long downSearch;// 搜索下载
	private long downSearchrecommend;// 搜推下载
	private long detailBrowse;// 详情浏览
	private long detailDown;// 详情下载
	private long downActivecenter;// 活动中心下载

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

	public long getDown() {
		return down;
	}

	public void setDown(long down) {
		this.down = down;
	}

	public long getDownHomepage() {
		return downHomepage;
	}

	public void setDownHomepage(long downHomepage) {
		this.downHomepage = downHomepage;
	}

	public long getDownRank() {
		return downRank;
	}

	public void setDownRank(long downRank) {
		this.downRank = downRank;
	}

	public long getDownSort() {
		return downSort;
	}

	public void setDownSort(long downSort) {
		this.downSort = downSort;
	}

	public long getDownSpecialtopic() {
		return downSpecialtopic;
	}

	public void setDownSpecialtopic(long downSpecialtopic) {
		this.downSpecialtopic = downSpecialtopic;
	}

	public long getDownSearch() {
		return downSearch;
	}

	public void setDownSearch(long downSearch) {
		this.downSearch = downSearch;
	}

	public long getDownSearchrecommend() {
		return downSearchrecommend;
	}

	public void setDownSearchrecommend(long downSearchrecommend) {
		this.downSearchrecommend = downSearchrecommend;
	}

	public long getDetailBrowse() {
		return detailBrowse;
	}

	public void setDetailBrowse(long detailBrowse) {
		this.detailBrowse = detailBrowse;
	}

	public long getDetailDown() {
		return detailDown;
	}

	public void setDetailDown(long detailDown) {
		this.detailDown = detailDown;
	}

	public long getDownActivecenter() {
		return downActivecenter;
	}

	public void setDownActivecenter(long downActivecenter) {
		this.downActivecenter = downActivecenter;
	}
}
