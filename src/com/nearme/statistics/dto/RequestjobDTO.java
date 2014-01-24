package com.nearme.statistics.dto;

import java.util.Date;

/**
 * 需求提交DTO
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-19
 */
public class RequestjobDTO {
	private String jobid;// 需求id
	private String systemID;
	private String condition;// 查询条件
	private String sample;// 样本选择
	private String indicat;// 输出指标
	private String username;// 需求提出人
	private int state;// 需求状态(0.未解决；1.已解决；2.需求不合理)
	private Date requesttime;// 提需求时间
	private Date finishtime;// 需求关闭时间
	private Date lastupdatetime;// 最后更新时间

	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getIndicat() {
		return indicat;
	}

	public void setIndicat(String indicat) {
		this.indicat = indicat;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}
}
