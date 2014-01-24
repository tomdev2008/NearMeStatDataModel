package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 下载日明细
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-10
 */
public class DowndayEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	// 时间
	private Date statDate;
	private Date statEndDate;
	
	private long downimei;// 下载imei数
	private long downuser;// 下载用户数
	private long downcnt;// 下载次数

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

	public long getDownimei() {
		return downimei;
	}

	public void setDownimei(long downimei) {
		this.downimei = downimei;
	}

	public long getDownuser() {
		return downuser;
	}

	public void setDownuser(long downuser) {
		this.downuser = downuser;
	}

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}
}
