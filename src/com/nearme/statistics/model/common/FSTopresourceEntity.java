package com.nearme.statistics.model.common;

import java.util.Date;

public class FSTopresourceEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8812086311436452733L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private long totaldown;
	private long top50;
	private long top100;
	private long top200;
	private long top300;
	private long top500;

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

	public long getTotaldown() {
		return totaldown;
	}

	public void setTotaldown(long totaldown) {
		this.totaldown = totaldown;
	}

	public long getTop50() {
		return top50;
	}

	public void setTop50(long top50) {
		this.top50 = top50;
	}

	public long getTop100() {
		return top100;
	}

	public void setTop100(long top100) {
		this.top100 = top100;
	}

	public long getTop200() {
		return top200;
	}

	public void setTop200(long top200) {
		this.top200 = top200;
	}

	public long getTop300() {
		return top300;
	}

	public void setTop300(long top300) {
		this.top300 = top300;
	}

	public long getTop500() {
		return top500;
	}

	public void setTop500(long top500) {
		this.top500 = top500;
	}
}
