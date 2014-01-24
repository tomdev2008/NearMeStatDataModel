package com.nearme.statistics.model.common;

/**
 * 时段分析
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-16
 */
public class HHStartEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hh;
	
	private long yesterday;
	private long downuser;
	private long last7;
	private long last30;
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public long getYesterday() {
		return yesterday;
	}
	public void setYesterday(long yesterday) {
		this.yesterday = yesterday;
	}
	public long getDownuser() {
		return downuser;
	}
	public void setDownuser(long downuser) {
		this.downuser = downuser;
	}
	public long getLast7() {
		return last7;
	}
	public void setLast7(long last7) {
		this.last7 = last7;
	}
	public long getLast30() {
		return last30;
	}
	public void setLast30(long last30) {
		this.last30 = last30;
	}

}
