package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 启动留存 Entity
 * @version 1.0
 * @since 1.0, 2013-4-24
 */
public class StartremainEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private String tableName;
	// 时间
	private Date statDate;
	private Date statEndDate;
	private int systemID;// 应用标识

	private long startImei;// 启动IMEI
	private long startUser;// 启动账号

	//启动IMEI留存
	private double lrr1;
	private double lrr2;
	private double lrr3;
	
	// 启动账号留存
	private double lurr1;
	private double lurr2;
	private double lurr3;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public long getStartImei() {
		return startImei;
	}

	public void setStartImei(long startImei) {
		this.startImei = startImei;
	}

	public long getStartUser() {
		return startUser;
	}

	public void setStartUser(long startUser) {
		this.startUser = startUser;
	}

	public double getLrr1() {
		return lrr1;
	}

	public void setLrr1(double lrr1) {
		this.lrr1 = lrr1;
	}

	public double getLrr2() {
		return lrr2;
	}

	public void setLrr2(double lrr2) {
		this.lrr2 = lrr2;
	}

	public double getLrr3() {
		return lrr3;
	}

	public void setLrr3(double lrr3) {
		this.lrr3 = lrr3;
	}

	public double getLurr1() {
		return lurr1;
	}

	public void setLurr1(double lurr1) {
		this.lurr1 = lurr1;
	}

	public double getLurr2() {
		return lurr2;
	}

	public void setLurr2(double lurr2) {
		this.lurr2 = lurr2;
	}

	public double getLurr3() {
		return lurr3;
	}

	public void setLurr3(double lurr3) {
		this.lurr3 = lurr3;
	}
}
