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
public class LRGoodsEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long shangxinTotal;// 上新商品总量
	private long shangxinCnt;// 上新次数
	private long newbrowseTotal;// 浏览数(总)
	private long newbrowseDetail;// 浏览数（详情）
	private long newlike;// 新商品喜欢数
	private long deviceCnt;// 设备数
	private float avgnewbrowseTotal;//平均浏览总
	private float avgnewbrowseDetail;//平均浏览详情
	private float avgnewlike;// 每新商品喜欢数
	private float avgquality;// 商品平均品质

	public float getAvgnewbrowseTotal() {
		return avgnewbrowseTotal;
	}

	public void setAvgnewbrowseTotal(float avgnewbrowseTotal) {
		this.avgnewbrowseTotal = avgnewbrowseTotal;
	}

	public float getAvgnewbrowseDetail() {
		return avgnewbrowseDetail;
	}

	public void setAvgnewbrowseDetail(float avgnewbrowseDetail) {
		this.avgnewbrowseDetail = avgnewbrowseDetail;
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

	public long getShangxinTotal() {
		return shangxinTotal;
	}

	public void setShangxinTotal(long shangxinTotal) {
		this.shangxinTotal = shangxinTotal;
	}

	public long getShangxinCnt() {
		return shangxinCnt;
	}

	public void setShangxinCnt(long shangxinCnt) {
		this.shangxinCnt = shangxinCnt;
	}

	public long getNewbrowseTotal() {
		return newbrowseTotal;
	}

	public void setNewbrowseTotal(long newbrowseTotal) {
		this.newbrowseTotal = newbrowseTotal;
	}

	public long getNewbrowseDetail() {
		return newbrowseDetail;
	}

	public void setNewbrowseDetail(long newbrowseDetail) {
		this.newbrowseDetail = newbrowseDetail;
	}

	public long getNewlike() {
		return newlike;
	}

	public void setNewlike(long newlike) {
		this.newlike = newlike;
	}

	public long getDeviceCnt() {
		return deviceCnt;
	}

	public void setDeviceCnt(long deviceCnt) {
		this.deviceCnt = deviceCnt;
	}

	public float getAvgnewlike() {
		return avgnewlike;
	}

	public void setAvgnewlike(float avgnewlike) {
		this.avgnewlike = avgnewlike;
	}

	public float getAvgquality() {
		return avgquality;
	}

	public void setAvgquality(float avgquality) {
		this.avgquality = avgquality;
	}

}
