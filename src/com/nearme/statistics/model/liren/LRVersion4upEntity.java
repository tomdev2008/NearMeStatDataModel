package com.nearme.statistics.model.liren;

import java.io.Serializable;
import java.util.Date;

/**
 * V4.0以上版本数据Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2014-1-7
 */
public class LRVersion4upEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long startimei;// 启动IMEI数
	private long startlrrcnt;// 启动留存数
	private float startlrr;// 启动留存LRR1
	private long newimei;// 新增IMEI数
	private long newssoid;// 新增账号数
	private long newlrrcnt;// 新增留存数
	private float newlrr;// 新增留存LRR1
	private float startimeiratio;// 启动IMEI占比
	private long startimeitotal;// 启动IMEI总量
	private long newimeitotal; // 新增IMEI总量
	private float newimeiratio;// 新增IMEI占比

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

	public long getStartimei() {
		return startimei;
	}

	public void setStartimei(long startimei) {
		this.startimei = startimei;
	}

	public float getStartlrr() {
		return startlrr;
	}

	public void setStartlrr(float startlrr) {
		this.startlrr = startlrr;
	}

	public long getNewimei() {
		return newimei;
	}

	public void setNewimei(long newimei) {
		this.newimei = newimei;
	}

	public long getNewssoid() {
		return newssoid;
	}

	public void setNewssoid(long newssoid) {
		this.newssoid = newssoid;
	}

	public float getNewlrr() {
		return newlrr;
	}

	public void setNewlrr(float newlrr) {
		this.newlrr = newlrr;
	}

	public float getStartimeiratio() {
		return startimeiratio;
	}

	public void setStartimeiratio(float startimeiratio) {
		this.startimeiratio = startimeiratio;
	}

	public long getStartimeitotal() {
		return startimeitotal;
	}

	public void setStartimeitotal(long startimeitotal) {
		this.startimeitotal = startimeitotal;
	}

	public long getNewlrrcnt() {
		return newlrrcnt;
	}

	public void setNewlrrcnt(long newlrrcnt) {
		this.newlrrcnt = newlrrcnt;
	}

	public long getStartlrrcnt() {
		return startlrrcnt;
	}

	public void setStartlrrcnt(long startlrrcnt) {
		this.startlrrcnt = startlrrcnt;
	}

	public long getNewimeitotal() {
		return newimeitotal;
	}

	public void setNewimeitotal(long newimeitotal) {
		this.newimeitotal = newimeitotal;
	}

	public float getNewimeiratio() {
		return newimeiratio;
	}

	public void setNewimeiratio(float newimeiratio) {
		this.newimeiratio = newimeiratio;
	}
}
