package com.nearme.statistics.model.common;

import java.util.Date;

public class WholeuserlifecycleEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8812086311436452733L;

	// 时间
	private Date statDate;
	private Date statEndDate;
	
	private String statDatestr;
	private String statEndDatestr;
	
	private long startimei;// 总启动IMEI数
	private long downcnt;// 总下载数量
	private long startcnt;// 总启动次数
	private long startdays;// 总启动天数
	
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

	public long getStartimei() {
		return startimei;
	}

	public void setStartimei(long startimei) {
		this.startimei = startimei;
	}

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public long getStartdays() {
		return startdays;
	}

	public void setStartdays(long startdays) {
		this.startdays = startdays;
	}
}
