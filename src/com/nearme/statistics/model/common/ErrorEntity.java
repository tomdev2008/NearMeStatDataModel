package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * 错误分析<br>
 * 错误/启动数
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-17
 */
public class ErrorEntity implements java.io.Serializable {
	private static final long serialVersionUID = 475367768481695190L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long errorcnt;// 错误数
	private long startcnt;// 启动次数
	private float errorstartratio;// 错误/启动数

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

	public long getErrorcnt() {
		return errorcnt;
	}

	public void setErrorcnt(long errorcnt) {
		this.errorcnt = errorcnt;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public float getErrorstartratio() {
		return errorstartratio;
	}

	public void setErrorstartratio(float errorstartratio) {
		this.errorstartratio = errorstartratio;
	}
}
