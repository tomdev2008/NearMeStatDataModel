package com.nearme.statistics.model.store;

import java.io.Serializable;
import java.util.Date;

/**
 * 细分用户客户端行为统计Entity
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class DetailuserclientEntity implements Serializable {
	private static final long serialVersionUID = -4836723705987434245L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long startUser;// 启动用户数
	private long downUser;// 下载用户数
	private float downStartRatio;// 下载启动比
	private long downCnt;// 下载次数
	private long startCnt;// 启动次数
	private float avgStartUserDown;// 启动用户人均下载次数
	private float avgDownUserDown;// 下载用户人均下载次数

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

	public long getStartUser() {
		return startUser;
	}

	public void setStartUser(long startUser) {
		this.startUser = startUser;
	}

	public long getDownUser() {
		return downUser;
	}

	public void setDownUser(long downUser) {
		this.downUser = downUser;
	}

	public float getDownStartRatio() {
		return downStartRatio;
	}

	public void setDownStartRatio(float downStartRatio) {
		this.downStartRatio = downStartRatio;
	}

	public long getDownCnt() {
		return downCnt;
	}

	public void setDownCnt(long downCnt) {
		this.downCnt = downCnt;
	}

	public long getStartCnt() {
		return startCnt;
	}

	public void setStartCnt(long startCnt) {
		this.startCnt = startCnt;
	}

	public float getAvgStartUserDown() {
		return avgStartUserDown;
	}

	public void setAvgStartUserDown(float avgStartUserDown) {
		this.avgStartUserDown = avgStartUserDown;
	}

	public float getAvgDownUserDown() {
		return avgDownUserDown;
	}

	public void setAvgDownUserDown(float avgDownUserDown) {
		this.avgDownUserDown = avgDownUserDown;
	}
}
