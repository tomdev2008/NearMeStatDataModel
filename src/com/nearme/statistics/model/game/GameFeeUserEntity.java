package com.nearme.statistics.model.game;

import java.io.Serializable;

/**
 * 付费用户实体类
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-11-19
 */
public class GameFeeUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ssoid;
	private String userName;
	private String keke;
	private String channelId;
	private String newWhaleDate;
	private String lastLoginDate;
	private String lastPayDate;
	private int payCount;
	private int paySum;
	private int kekeBalance;
	private int rechargeCount;
	private int rechargeSum;
	
	public int getSsoid() {
		return ssoid;
	}
	public void setSsoid(int ssoid) {
		this.ssoid = ssoid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getKeke() {
		return keke;
	}
	public void setKeke(String keke) {
		this.keke = keke;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getNewWhaleDate() {
		return newWhaleDate;
	}
	public void setNewWhaleDate(String newWhaleDate) {
		this.newWhaleDate = newWhaleDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getLastPayDate() {
		return lastPayDate;
	}
	public void setLastPayDate(String lastPayDate) {
		this.lastPayDate = lastPayDate;
	}
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
	public int getPaySum() {
		return paySum;
	}
	public void setPaySum(int paySum) {
		this.paySum = paySum;
	}
	public int getKekeBalance() {
		return kekeBalance;
	}
	public void setKekeBalance(int kekeBalance) {
		this.kekeBalance = kekeBalance;
	}
	public int getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(int rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	public int getRechargeSum() {
		return rechargeSum;
	}
	public void setRechargeSum(int rechargeSum) {
		this.rechargeSum = rechargeSum;
	}
	
}
