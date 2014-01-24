package com.nearme.statistics.dto;

import java.util.Date;

/**
 * HivejobDTO Hive任务
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-10
 */
public class HivejobDTO {
	private String jobID;// 任务id
	private int systemID;
	private String condition;// 查询条件
	private String username;// 查询人
	private Date starttime;// 任务执行时间
	private int state;// 任务执行状态
	
	//结果表(由下列三项决定)
	private String resulttable;// 结果表参考
	private String weidu; // 具体细分表
	private String lidu; // 日表、周表、月表
	
	private String flag; // 标志(通用保留字段)
	
	public String getWeidu() {
		return weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	public String getLidu() {
		return lidu;
	}

	public void setLidu(String lidu) {
		this.lidu = lidu;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getResulttable() {
		return resulttable;
	}

	public void setResulttable(String resulttable) {
		this.resulttable = resulttable;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
