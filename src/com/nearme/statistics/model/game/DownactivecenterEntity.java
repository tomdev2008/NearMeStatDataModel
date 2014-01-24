package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动中心下载Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class DownactivecenterEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long down;// 下载
	private long downtask;// 任务下载
	private long downgift;// 礼包下载
	private long downactive;// 活动下载

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

	public long getDowntask() {
		return downtask;
	}

	public void setDowntask(long downtask) {
		this.downtask = downtask;
	}

	public long getDowngift() {
		return downgift;
	}

	public void setDowngift(long downgift) {
		this.downgift = downgift;
	}

	public long getDownactive() {
		return downactive;
	}

	public void setDownactive(long downactive) {
		this.downactive = downactive;
	}

}
