package com.nearme.statistics.model.game;

import java.io.Serializable;

/**
 * 付费分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class GameFeeEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	private String statDate;
	private int newWhale;
	private int totalWhale;
	private int whaleLost7;
	private int whalePayLost7;
	private int whalePayLost30;
	
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	public int getNewWhale() {
		return newWhale;
	}
	public void setNewWhale(int newWhale) {
		this.newWhale = newWhale;
	}
	public int getTotalWhale() {
		return totalWhale;
	}
	public void setTotalWhale(int totalWhale) {
		this.totalWhale = totalWhale;
	}
	public int getWhaleLost7() {
		return whaleLost7;
	}
	public void setWhaleLost7(int whaleLost7) {
		this.whaleLost7 = whaleLost7;
	}
	public int getWhalePayLost7() {
		return whalePayLost7;
	}
	public void setWhalePayLost7(int whalePayLost7) {
		this.whalePayLost7 = whalePayLost7;
	}
	public int getWhalePayLost30() {
		return whalePayLost30;
	}
	public void setWhalePayLost30(int whalePayLost30) {
		this.whalePayLost30 = whalePayLost30;
	}
	
}
