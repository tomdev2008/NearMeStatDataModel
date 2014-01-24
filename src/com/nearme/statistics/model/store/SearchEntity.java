package com.nearme.statistics.model.store;

import java.io.Serializable;
import java.util.Date;

/**
 * 搜索日报表Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class SearchEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long totalDown;// 搜索下载总量
	private long total;// 搜索总量
	private long downTotalUser;// 搜索下载总用户数
	private long totalUser;// 搜索总用户数
	private long notDownUser;// 搜索未下载用户数
	private long searchPhbCnt;//从搜索排行榜产生的下载量
	private long searchBaiduCnt;//从百度产生的下载量

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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getDownTotalUser() {
		return downTotalUser;
	}

	public void setDownTotalUser(long downTotalUser) {
		this.downTotalUser = downTotalUser;
	}

	public long getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(long totalUser) {
		this.totalUser = totalUser;
	}

	public long getNotDownUser() {
		return notDownUser;
	}

	public void setNotDownUser(long notDownUser) {
		this.notDownUser = notDownUser;
	}

	public long getSearchPhbCnt() {
		return searchPhbCnt;
	}

	public void setSearchPhbCnt(long searchPhbCnt) {
		this.searchPhbCnt = searchPhbCnt;
	}

	public long getSearchBaiduCnt() {
		return searchBaiduCnt;
	}

	public void setSearchBaiduCnt(long searchBaiduCnt) {
		this.searchBaiduCnt = searchBaiduCnt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
