package com.nearme.statistics.model.store;

import java.io.Serializable;

/**
 * 安装统计Entity
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-30
 */
public class InstallStatEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 时间
	private int statDate;
	private String statDateStr;
	
	private String channel;
	private long installImei;//
	private long installTimes;//
	private long installImeiOppo;//
	private long installTimesOppo;//
	private long installImeiOther;//
	private long installTimesOther;//
	private String avgInstall;//
	private long startImei;//
	private long startTimes;//
	private int rank;
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public int getStatDate() {
		return statDate;
	}
	public void setStatDate(int statDate) {
		this.statDate = statDate;
	}
	public long getInstallImei() {
		return installImei;
	}
	public void setInstallImei(long installImei) {
		this.installImei = installImei;
	}
	public long getInstallTimes() {
		return installTimes;
	}
	public void setInstallTimes(long installTimes) {
		this.installTimes = installTimes;
	}
	public long getInstallImeiOppo() {
		return installImeiOppo;
	}
	public void setInstallImeiOppo(long installImeiOppo) {
		this.installImeiOppo = installImeiOppo;
	}
	public long getInstallTimesOppo() {
		return installTimesOppo;
	}
	public void setInstallTimesOppo(long installTimesOppo) {
		this.installTimesOppo = installTimesOppo;
	}
	public long getInstallImeiOther() {
		return installImeiOther;
	}
	public void setInstallImeiOther(long installImeiOther) {
		this.installImeiOther = installImeiOther;
	}
	public long getInstallTimesOther() {
		return installTimesOther;
	}
	public void setInstallTimesOther(long installTimesOther) {
		this.installTimesOther = installTimesOther;
	}
	public String getAvgInstall() {
		return avgInstall;
	}
	public void setAvgInstall(String avgInstall) {
		this.avgInstall = avgInstall;
	}
	public long getStartImei() {
		return startImei;
	}
	public void setStartImei(long startImei) {
		this.startImei = startImei;
	}
	public long getStartTimes() {
		return startTimes;
	}
	public void setStartTimes(long startTimes) {
		this.startTimes = startTimes;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getStatDateStr() {
		return statDateStr;
	}
	public void setStatDateStr(String statDateStr) {
		this.statDateStr = statDateStr;
	}
	
}
