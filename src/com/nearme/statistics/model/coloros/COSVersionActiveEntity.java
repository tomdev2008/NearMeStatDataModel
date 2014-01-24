package com.nearme.statistics.model.coloros;

import java.io.Serializable;
import java.util.Date;

/**
 * 版本活跃排行Entity
 * 
 * @author 林逸聪
 * @version 1.0
 * @since 1.0, 2013-10-23
 */
public class COSVersionActiveEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String version;
	private long activeTotal;// 累积用户

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getActiveTotal() {
		return activeTotal;
	}

	public void setActiveTotal(long activeTotal) {
		this.activeTotal = activeTotal;
	}
}
