package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 单个资源下载
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-13
 */
public class DownsourceEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long downcnt;// 下载量
	private long browsecnt;// 浏览量

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

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}

	public long getBrowsecnt() {
		return browsecnt;
	}

	public void setBrowsecnt(long browsecnt) {
		this.browsecnt = browsecnt;
	}
}
