package com.nearme.statistics.model.common;

import java.util.Date;

public class FSResourcetypeChildEntity implements java.io.Serializable{
	private static final long serialVersionUID = -8812086311436452733L;

	// 时间
	private Date statDate;
	private String statDatestr;

	private String typename;// 类别名
	private long downcnt;// 下载数
	private long downapp;// 资源下载数

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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public long getDowncnt() {
		return downcnt;
	}

	public void setDowncnt(long downcnt) {
		this.downcnt = downcnt;
	}

	public long getDownapp() {
		return downapp;
	}

	public void setDownapp(long downapp) {
		this.downapp = downapp;
	}
}
