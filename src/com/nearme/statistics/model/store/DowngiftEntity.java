package com.nearme.statistics.model.store;

import java.io.Serializable;
import java.util.Date;

/**
 * 下载有礼日报表Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class DowngiftEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String position;// 位置
	private String sourceName;// 资源名称
	private long downCnt;// 下载量
	private long liulanCnt;// 浏览量
	private float liulanDownRatio;// 浏览下载比

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

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public long getDownCnt() {
		return downCnt;
	}

	public void setDownCnt(long downCnt) {
		this.downCnt = downCnt;
	}

	public long getLiulanCnt() {
		return liulanCnt;
	}

	public void setLiulanCnt(long liulanCnt) {
		this.liulanCnt = liulanCnt;
	}

	public float getLiulanDownRatio() {
		return liulanDownRatio;
	}

	public void setLiulanDownRatio(float liulanDownRatio) {
		this.liulanDownRatio = liulanDownRatio;
	}
}
