package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 排行Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-8
 */
public class RankEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String position;// 位置
	private long directdown;// 直接下载
	private long detailbrowse;// 详情浏览
	private long detaildown;// 详情下载

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public long getDirectdown() {
		return directdown;
	}

	public void setDirectdown(long directdown) {
		this.directdown = directdown;
	}

	public long getDetailbrowse() {
		return detailbrowse;
	}

	public void setDetailbrowse(long detailbrowse) {
		this.detailbrowse = detailbrowse;
	}

	public long getDetaildown() {
		return detaildown;
	}

	public void setDetaildown(long detaildown) {
		this.detaildown = detaildown;
	}
}
