package com.nearme.statistics.form.app.game;

import com.nearme.statistics.form.BaseForm;

/**
 * 游戏堂Form
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class GameForm extends BaseForm {
	private String gamename;// 游戏名
	private String ranktype;// 排行类型
	private int position;// 位置
	private String productIDs;// 游戏产品IDs

	private String productid;// 产品id
	private String productname;// 产品名
	
	private String statdate;// 产品名
	private int records;// 记录条数
	
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getRanktype() {
		return ranktype;
	}

	public void setRanktype(String ranktype) {
		this.ranktype = ranktype;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getProductIDs() {
		return productIDs;
	}

	public void setProductIDs(String productIDs) {
		this.productIDs = productIDs;
	}

	public String getStatdate() {
		return statdate;
	}

	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

}
