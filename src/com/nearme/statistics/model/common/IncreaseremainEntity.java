package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 新增留存 Entity
 * @version 1.0
 * @since 1.0, 2013-4-24
 */
public class IncreaseremainEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private String tableName;
	// 时间
	private Date statDate;
	private Date statEndDate;
	private int systemID;// 应用标识

	private long newImei;// 新增IMEI
	private long newUser;// 新增账号

	// 新增IMEI留存
	private double nrr1;
	private double nrr2;
	private double nrr3;

	// 新增账号留存
	private double nurr1;
	private double nurr2;
	private double nurr3;

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

	public long getNewImei() {
		return newImei;
	}

	public void setNewImei(long newImei) {
		this.newImei = newImei;
	}

	public long getNewUser() {
		return newUser;
	}

	public void setNewUser(long newUser) {
		this.newUser = newUser;
	}

	public double getNurr1() {
		return nurr1;
	}

	public void setNurr1(double nurr1) {
		this.nurr1 = nurr1;
	}

	public double getNurr2() {
		return nurr2;
	}

	public void setNurr2(double nurr2) {
		this.nurr2 = nurr2;
	}

	public double getNurr3() {
		return nurr3;
	}

	public void setNurr3(double nurr3) {
		this.nurr3 = nurr3;
	}

	public double getNrr1() {
		return nrr1;
	}

	public void setNrr1(double nrr1) {
		this.nrr1 = nrr1;
	}

	public double getNrr2() {
		return nrr2;
	}

	public void setNrr2(double nrr2) {
		this.nrr2 = nrr2;
	}

	public double getNrr3() {
		return nrr3;
	}

	public void setNrr3(double nrr3) {
		this.nrr3 = nrr3;
	}
}
