package com.nearme.statistics.model.sys.admin;

import java.sql.Timestamp;

/**
 * IP过滤器实体
 * 黑白名单通用
 * @author 80053813
 *
 */
public class IpFilter {

	private int id;
	
	private String strIpStart;
	private String strIpEnd;
	
	private long numIpStart;
	private long numIpEnd;
	
	private String strIpRegex;
	
	private int updateBy;
	private Timestamp updateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStrIpStart() {
		return strIpStart;
	}
	public void setStrIpStart(String strIpStart) {
		this.strIpStart = strIpStart;
	}
	public String getStrIpEnd() {
		return strIpEnd;
	}
	public void setStrIpEnd(String strIpEnd) {
		this.strIpEnd = strIpEnd;
	}
	public long getNumIpStart() {
		return numIpStart;
	}
	public void setNumIpStart(long numIpStart) {
		this.numIpStart = numIpStart;
	}
	public long getNumIpEnd() {
		return numIpEnd;
	}
	public void setNumIpEnd(long numIpEnd) {
		this.numIpEnd = numIpEnd;
	}
	public String getStrIpRegex() {
		return strIpRegex;
	}
	public void setStrIpRegex(String strIpRegex) {
		this.strIpRegex = strIpRegex;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
