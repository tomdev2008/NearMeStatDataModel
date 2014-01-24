package com.nearme.statistics.model.liren;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class LRHomepageEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long homepageImei;// 进入首页设备数
	private long homepageCnt;// 进入首页次数
	private long lbtimei;// 拉去轮播图设备数
	private long lbtcnt;// 拉去轮播图次数
	private long hotrecommendImei;// 热门推荐拉去设备数
	private long hotrecommendCnt;// 热门推荐拉去次数
	private long hotrecommendBrose;// 热门推荐浏览设备数

	private long hottop50;// 热门推荐前50变化量
	private long hottop100;// 热门推荐前100变化量
	private String refertime;// 当日参照时刻
	private String referyestime;// 前一天参照时刻

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

	public long getHomepageImei() {
		return homepageImei;
	}

	public void setHomepageImei(long homepageImei) {
		this.homepageImei = homepageImei;
	}

	public long getHomepageCnt() {
		return homepageCnt;
	}

	public void setHomepageCnt(long homepageCnt) {
		this.homepageCnt = homepageCnt;
	}

	public long getLbtimei() {
		return lbtimei;
	}

	public void setLbtimei(long lbtimei) {
		this.lbtimei = lbtimei;
	}

	public long getLbtcnt() {
		return lbtcnt;
	}

	public void setLbtcnt(long lbtcnt) {
		this.lbtcnt = lbtcnt;
	}

	public long getHotrecommendImei() {
		return hotrecommendImei;
	}

	public void setHotrecommendImei(long hotrecommendImei) {
		this.hotrecommendImei = hotrecommendImei;
	}

	public long getHotrecommendCnt() {
		return hotrecommendCnt;
	}

	public void setHotrecommendCnt(long hotrecommendCnt) {
		this.hotrecommendCnt = hotrecommendCnt;
	}

	public long getHotrecommendBrose() {
		return hotrecommendBrose;
	}

	public void setHotrecommendBrose(long hotrecommendBrose) {
		this.hotrecommendBrose = hotrecommendBrose;
	}

	public long getHottop50() {
		return hottop50;
	}

	public void setHottop50(long hottop50) {
		this.hottop50 = hottop50;
	}

	public long getHottop100() {
		return hottop100;
	}

	public void setHottop100(long hottop100) {
		this.hottop100 = hottop100;
	}

	public String getRefertime() {
		return refertime;
	}

	public void setRefertime(String refertime) {
		this.refertime = refertime;
	}

	public String getReferyestime() {
		return referyestime;
	}

	public void setReferyestime(String referyestime) {
		this.referyestime = referyestime;
	}
}
