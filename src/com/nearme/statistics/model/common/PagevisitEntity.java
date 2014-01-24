package com.nearme.statistics.model.common;

/**
 * @author 朱峰
 * @function 页面访问分析 Entity
 * @version 1.0
 * @since 1.0, 2013-4-24
 */
public class PagevisitEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private String page;// 页面
	private String pageVisit;// 页面访问次数
	private String pageVisitImei; // 页面访问IMEI数

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageVisit() {
		return pageVisit;
	}

	public void setPageVisit(String pageVisit) {
		this.pageVisit = pageVisit;
	}

	public String getPageVisitImei() {
		return pageVisitImei;
	}

	public void setPageVisitImei(String pageVisitImei) {
		this.pageVisitImei = pageVisitImei;
	}
}
