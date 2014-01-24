package com.nearme.statistics.model.common;

import java.util.Date;
import java.util.List;

public class FSOperatepointEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8812086311436452733L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String statDatestr;
	private String statEndDatestr;

	private long totaldown;
	private long downimei;
	private List<FSOperatepointChildEntity> list;

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

	public long getDownimei() {
		return downimei;
	}

	public void setDownimei(long downimei) {
		this.downimei = downimei;
	}

	public List<FSOperatepointChildEntity> getList() {
		return list;
	}

	public void setList(List<FSOperatepointChildEntity> list) {
		this.list = list;
	}
}
