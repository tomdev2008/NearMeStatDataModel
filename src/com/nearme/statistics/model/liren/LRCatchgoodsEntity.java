package com.nearme.statistics.model.liren;

import java.io.Serializable;
import java.util.Date;

/**
 * 抓取商品Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-19
 */
public class LRCatchgoodsEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String leimu;// 类目
	private long catchcnt;// 抓取商品数
	private long newcnt;// 新商品数

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

	public String getLeimu() {
		return leimu;
	}

	public void setLeimu(String leimu) {
		this.leimu = leimu;
	}

	public long getCatchcnt() {
		return catchcnt;
	}

	public void setCatchcnt(long catchcnt) {
		this.catchcnt = catchcnt;
	}

	public long getNewcnt() {
		return newcnt;
	}

	public void setNewcnt(long newcnt) {
		this.newcnt = newcnt;
	}
}
