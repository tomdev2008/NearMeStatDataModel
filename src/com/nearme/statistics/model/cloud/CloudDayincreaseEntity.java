package com.nearme.statistics.model.cloud;

import java.io.Serializable;
import java.util.Date;

/**
 * 日增情况
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-13
 */
public class CloudDayincreaseEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long increaseuser;// 日新增存储帐号
	private long increasecnt;// 日增存储数量

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

	public long getIncreaseuser() {
		return increaseuser;
	}

	public void setIncreaseuser(long increaseuser) {
		this.increaseuser = increaseuser;
	}

	public long getIncreasecnt() {
		return increasecnt;
	}

	public void setIncreasecnt(long increasecnt) {
		this.increasecnt = increasecnt;
	}
}
