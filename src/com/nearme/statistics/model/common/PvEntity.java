package com.nearme.statistics.model.common;

import java.io.Serializable;

/**
 * 页面访问实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-18
 */
public class PvEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String duration;
	private long startTimes;
	private long startImeis;
	private String frequency;
	private String rate;
	private String visitPages;
	
	private String statdate;
	private String clumn1;
	private String clumn2;
	private String clumn3;
	private String clumn4;
	private String clumn5;
	private String clumn6;
	private String clumn7;
	private String clumn8;
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public long getStartTimes() {
		return startTimes;
	}
	public void setStartTimes(long startTimes) {
		this.startTimes = startTimes;
	}
	public long getStartImeis() {
		return startImeis;
	}
	public void setStartImeis(long startImeis) {
		this.startImeis = startImeis;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getVisitPages() {
		return visitPages;
	}
	public void setVisitPages(String visitPages) {
		this.visitPages = visitPages;
	}
	public String getStatdate() {
		return statdate;
	}
	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}
	public String getClumn1() {
		return clumn1;
	}
	public void setClumn1(String clumn1) {
		this.clumn1 = clumn1;
	}
	public String getClumn2() {
		return clumn2;
	}
	public void setClumn2(String clumn2) {
		this.clumn2 = clumn2;
	}
	public String getClumn3() {
		return clumn3;
	}
	public void setClumn3(String clumn3) {
		this.clumn3 = clumn3;
	}
	public String getClumn4() {
		return clumn4;
	}
	public void setClumn4(String clumn4) {
		this.clumn4 = clumn4;
	}
	public String getClumn5() {
		return clumn5;
	}
	public void setClumn5(String clumn5) {
		this.clumn5 = clumn5;
	}
	public String getClumn6() {
		return clumn6;
	}
	public void setClumn6(String clumn6) {
		this.clumn6 = clumn6;
	}
	public String getClumn7() {
		return clumn7;
	}
	public void setClumn7(String clumn7) {
		this.clumn7 = clumn7;
	}
	public String getClumn8() {
		return clumn8;
	}
	public void setClumn8(String clumn8) {
		this.clumn8 = clumn8;
	}
	
}
