package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 下载累计 Entity
 * @version 1.0
 * @since 1.0, 2013-12-25
 */
public class DownleijiEntity {
	private Date statDate;// 时间
	private long downcnt;// 累计下载量
	private long downimei;// 累计下载IMEI数
	private long downssoid;// 累计下载SSOID数

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}

	public long getDownimei() {
		return downimei;
	}

	public void setDownimei(long downimei) {
		this.downimei = downimei;
	}

	public long getDownssoid() {
		return downssoid;
	}

	public void setDownssoid(long downssoid) {
		this.downssoid = downssoid;
	}
}
