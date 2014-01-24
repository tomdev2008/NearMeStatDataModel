package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 软件下载top
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBSoftdowntopEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String softname;// 软件名
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

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}
}
