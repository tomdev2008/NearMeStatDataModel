package com.nearme.statistics.model.common;

import java.util.Date;

/**
 * @author 朱峰
 * @function 活跃天数 Entity
 * @version 1.0
 * @since 1.0, 2013-5-13
 */
public class ActivedaysEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private String tableName;// 要查询的表名
	private Date statDate;// 时间
	private Date statEndDate;
	private int systemID;// 应用标识

	private String activeDays;//活跃天数
	private long startImei;// 启动IMEI
	private float ratioImei;// 占当月启动IMEI的比率

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

	public String getActiveDays() {
		return activeDays;
	}

	public void setActiveDays(String activeDays) {
		this.activeDays = activeDays;
	}

	public float getRatioImei() {
		return ratioImei;
	}

	public void setRatioImei(float ratioImei) {
		this.ratioImei = ratioImei;
	}
}
