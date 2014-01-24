package com.nearme.statistics.model.common;

import java.io.Serializable;

/**
 * 页面访问路径实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-18
 */
public class VisitPathEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String curPage;	//当前页
	private long jumpTimes;	//访问次数
	private String visitRate;	//访问次数占比
	private long duration;	//访问时长
	private String avgDuration;	//平均访问时长
	private String durationRate;	//访问时长占比
	private long exitTimes;	//跳出次数
	private String exitRate;	//跳出率
	private String nextPage;	//跳至页面
	private String rate;	//跳至页面占比
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public long getJumpTimes() {
		return jumpTimes;
	}
	public void setJumpTimes(long jumpTimes) {
		this.jumpTimes = jumpTimes;
	}
	public String getVisitRate() {
		return visitRate;
	}
	public void setVisitRate(String visitRate) {
		this.visitRate = visitRate;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getAvgDuration() {
		return avgDuration;
	}
	public void setAvgDuration(String avgDuration) {
		this.avgDuration = avgDuration;
	}
	public String getDurationRate() {
		return durationRate;
	}
	public void setDurationRate(String durationRate) {
		this.durationRate = durationRate;
	}
	public long getExitTimes() {
		return exitTimes;
	}
	public void setExitTimes(long exitTimes) {
		this.exitTimes = exitTimes;
	}
	public String getExitRate() {
		return exitRate;
	}
	public void setExitRate(String exitRate) {
		this.exitRate = exitRate;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
}
