package com.nearme.statistics.model.liren;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class LRGoodsqualityEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String betweentype;// 所在区间
	private long numb;// 数量
	private float zanbi;// 占比

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

	public String getBetweentype() {
		return betweentype;
	}

	public void setBetweentype(String betweentype) {
		this.betweentype = betweentype;
	}

	public long getNumb() {
		return numb;
	}

	public void setNumb(long numb) {
		this.numb = numb;
	}

	public float getZanbi() {
		return zanbi;
	}

	public void setZanbi(float zanbi) {
		this.zanbi = zanbi;
	}

}
