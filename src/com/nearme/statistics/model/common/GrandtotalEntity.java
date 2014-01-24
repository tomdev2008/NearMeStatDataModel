package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 累计数据 Entity 
 * @version 1.0
 * @since 1.0, 2013-5-10
 */
public class GrandtotalEntity {
	private Date statDate;// 时间
	private long totalStart;// 累计启动次数
	private long totalImei;// 累计IMEI数
	private long totalUser;// 累计账号数

	public long getTotalStart() {
		return totalStart;
	}

	public void setTotalStart(long totalStart) {
		this.totalStart = totalStart;
	}

	public long getTotalImei() {
		return totalImei;
	}

	public void setTotalImei(long totalImei) {
		this.totalImei = totalImei;
	}

	public long getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(long totalUser) {
		this.totalUser = totalUser;
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
}
