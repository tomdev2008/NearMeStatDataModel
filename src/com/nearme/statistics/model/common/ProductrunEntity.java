package com.nearme.statistics.model.common;

import java.io.Serializable;

/**
 * 产品名Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class ProductrunEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	private String name;// 名称
	private long downimei;// 下载imei
	private long browseimei;// 浏览imei
	private long directdown;// 直接下载
	private long detaildown;// 详情下载

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDownimei() {
		return downimei;
	}

	public void setDownimei(long downimei) {
		this.downimei = downimei;
	}

	public long getBrowseimei() {
		return browseimei;
	}

	public void setBrowseimei(long browseimei) {
		this.browseimei = browseimei;
	}

	public long getDirectdown() {
		return directdown;
	}

	public void setDirectdown(long directdown) {
		this.directdown = directdown;
	}

	public long getDetaildown() {
		return detaildown;
	}

	public void setDetaildown(long detaildown) {
		this.detaildown = detaildown;
	}
}
