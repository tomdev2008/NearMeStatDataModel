package com.nearme.statistics.model.coloros;

import java.util.Date;

/**
 * 网络分布
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-27
 */
public class COSNetdistributeEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1152163763934526975L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String nettype;// 网络类型
	private long usercnt;// 用户数
	private float userratio;// 占比

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

	public String getNettype() {
		return nettype;
	}

	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	public long getUsercnt() {
		return usercnt;
	}

	public void setUsercnt(long usercnt) {
		this.usercnt = usercnt;
	}

	public float getUserratio() {
		return userratio;
	}

	public void setUserratio(float userratio) {
		this.userratio = userratio;
	}
}
