package com.nearme.statistics.model.liren;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签热度Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class LRTaghotEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String tag;// 标签
	private long likecnt;// 当日被喜欢数
	private long totallikecnt;// 累计被喜欢总数
	private long dayliketotal;// 全天喜欢数
	private float zanbi;// 占当天全部喜欢量的比例

	public long getDayliketotal() {
		return dayliketotal;
	}

	public void setDayliketotal(long dayliketotal) {
		this.dayliketotal = dayliketotal;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(long likecnt) {
		this.likecnt = likecnt;
	}

	public long getTotallikecnt() {
		return totallikecnt;
	}

	public void setTotallikecnt(long totallikecnt) {
		this.totallikecnt = totallikecnt;
	}

	public float getZanbi() {
		return zanbi;
	}

	public void setZanbi(float zanbi) {
		this.zanbi = zanbi;
	}

}
