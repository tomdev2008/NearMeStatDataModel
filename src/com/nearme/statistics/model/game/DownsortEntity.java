package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类下载Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class DownsortEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long down;// 下载
	private long downdirect;// 直接下载
	private long detail;// 详情
	private long downdetail;// 详情下载
	private String categoryname1;// 分类名称1
	private String categoryname2;// 分类名称2
	private String categoryname3;// 分类名称3
	private long top1;// top1分类
	private long top2;// top2分类
	private long top3;// top3分类

	private String catname;// 分类名称
	private long topvalue;// 分类值

	public String getCategoryname1() {
		return categoryname1;
	}

	public void setCategoryname1(String categoryname1) {
		this.categoryname1 = categoryname1;
	}

	public String getCategoryname2() {
		return categoryname2;
	}

	public void setCategoryname2(String categoryname2) {
		this.categoryname2 = categoryname2;
	}

	public String getCategoryname3() {
		return categoryname3;
	}

	public void setCategoryname3(String categoryname3) {
		this.categoryname3 = categoryname3;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public long getTopvalue() {
		return topvalue;
	}

	public void setTopvalue(long topvalue) {
		this.topvalue = topvalue;
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

	public long getTop1() {
		return top1;
	}

	public void setTop1(long top1) {
		this.top1 = top1;
	}

	public long getTop2() {
		return top2;
	}

	public void setTop2(long top2) {
		this.top2 = top2;
	}

	public long getTop3() {
		return top3;
	}

	public void setTop3(long top3) {
		this.top3 = top3;
	}

}
