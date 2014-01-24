package com.nearme.statistics.model.common;

import java.util.Date;

public class FSUpdatenumEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8812086311436452733L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private long downimei;
	private long downcnt;
	private long updatecnt;
	private long updateimei;
	private long updateres;

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

	public String getStatDatestr() {
		return statDatestr;
	}

	public void setStatDatestr(String statDatestr) {
		this.statDatestr = statDatestr;
	}

	public String getStatEndDatestr() {
		return statEndDatestr;
	}

	public void setStatEndDatestr(String statEndDatestr) {
		this.statEndDatestr = statEndDatestr;
	}

	public long getDownimei() {
		return downimei;
	}

	public void setDownimei(long downimei) {
		this.downimei = downimei;
	}

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}

	public long getUpdatecnt() {
		return updatecnt;
	}

	public void setUpdatecnt(long updatecnt) {
		this.updatecnt = updatecnt;
	}

	public long getUpdateimei() {
		return updateimei;
	}

	public void setUpdateimei(long updateimei) {
		this.updateimei = updateimei;
	}

	public long getUpdateres() {
		return updateres;
	}

	public void setUpdateres(long updateres) {
		this.updateres = updateres;
	}
}
