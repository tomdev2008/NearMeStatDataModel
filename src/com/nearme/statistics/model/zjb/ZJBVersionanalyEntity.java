package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 版本分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBVersionanalyEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String version;// 版本
	private long startcnt;// 启动次数
	private long startcomp;// 启动电脑数
	private float startcompratio;// 启动电脑数占比
	private long startuser;// 启动账号数
	private float startuserratio;// 启动账号占比

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

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public long getStartcomp() {
		return startcomp;
	}

	public void setStartcomp(long startcomp) {
		this.startcomp = startcomp;
	}

	public float getStartcompratio() {
		return startcompratio;
	}

	public void setStartcompratio(float startcompratio) {
		this.startcompratio = startcompratio;
	}

	public long getStartuser() {
		return startuser;
	}

	public void setStartuser(long startuser) {
		this.startuser = startuser;
	}

	public float getStartuserratio() {
		return startuserratio;
	}

	public void setStartuserratio(float startuserratio) {
		this.startuserratio = startuserratio;
	}
}
