package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 软件下载
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBSoftdownEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long downtotal;// 总下载
	private long backupdatecnt;// 后台更新量
	private float backupdateratio;// 后台更新占比
	private long selfdown;// 手动点击下载
	private float selfdownratio;// 手动点击下载占比
	private long selfresdown;// 自定义资源包下载
	private float selfresdownratio;// 自定义资源包下载占比

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

	public long getDowntotal() {
		return downtotal;
	}

	public void setDowntotal(long downtotal) {
		this.downtotal = downtotal;
	}

	public long getBackupdatecnt() {
		return backupdatecnt;
	}

	public void setBackupdatecnt(long backupdatecnt) {
		this.backupdatecnt = backupdatecnt;
	}

	public float getBackupdateratio() {
		return backupdateratio;
	}

	public void setBackupdateratio(float backupdateratio) {
		this.backupdateratio = backupdateratio;
	}

	public long getSelfdown() {
		return selfdown;
	}

	public void setSelfdown(long selfdown) {
		this.selfdown = selfdown;
	}

	public float getSelfdownratio() {
		return selfdownratio;
	}

	public void setSelfdownratio(float selfdownratio) {
		this.selfdownratio = selfdownratio;
	}

	public long getSelfresdown() {
		return selfresdown;
	}

	public void setSelfresdown(long selfresdown) {
		this.selfresdown = selfresdown;
	}

	public float getSelfresdownratio() {
		return selfresdownratio;
	}

	public void setSelfresdownratio(float selfresdownratio) {
		this.selfresdownratio = selfresdownratio;
	}
}
