package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 搜索下载 Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class DownsearchEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long down;// 下载
	private long downdirect;// 直接下载
	private long detail;// 详情
	private long downdetail;// 详情下载

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

	public long getDown() {
		return down;
	}

	public void setDown(long down) {
		this.down = down;
	}

	public long getDowndirect() {
		return downdirect;
	}

	public void setDowndirect(long downdirect) {
		this.downdirect = downdirect;
	}

	public long getDetail() {
		return detail;
	}

	public void setDetail(long detail) {
		this.detail = detail;
	}

	public long getDowndetail() {
		return downdetail;
	}

	public void setDowndetail(long downdetail) {
		this.downdetail = downdetail;
	}

}
