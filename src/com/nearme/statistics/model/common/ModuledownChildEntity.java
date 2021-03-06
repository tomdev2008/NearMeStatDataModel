package com.nearme.statistics.model.common;

import java.util.Date;

public class ModuledownChildEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8812086311436452733L;

	// 时间
	private Date statDate;

	private String statDatestr;

	private String modulename;
	private long downcnt;
	private long downimei;

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public String getStatDatestr() {
		return statDatestr;
	}

	public void setStatDatestr(String statDatestr) {
		this.statDatestr = statDatestr;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
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
}
