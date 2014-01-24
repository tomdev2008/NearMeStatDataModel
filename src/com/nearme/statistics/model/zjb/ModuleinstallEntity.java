package com.nearme.statistics.model.zjb;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块安装量
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class ModuleinstallEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long soft;// 软件安装量
	private long online1;// 在线资源包安装量
	private float onlineRatio;// 在线资源包安装占比
	private long self;// 自定义资源包安装量
	private float selfRatio;// 自定义资源包安装占比
	private long onlineSoft;// 在线软件安装量
	private float onlineSoftRatio;// 在线软件安装占比
	private long search;// 搜索模块安装量
	private float searchRatio;// 搜索模块安装量占比
	private long upgrade;// 升级模块安装量
	private float upgradeRatio;// 升级模块安装量占比
	private long bigGame;// 大型游戏安装量
	private long daohang;// 导航软件安装量
	private long installer;// 安装器安装量

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

	public long getSoft() {
		return soft;
	}

	public void setSoft(long soft) {
		this.soft = soft;
	}
	
	public long getOnline1() {
		return online1;
	}

	public void setOnline1(long online1) {
		this.online1 = online1;
	}

	public float getOnlineRatio() {
		return onlineRatio;
	}

	public void setOnlineRatio(float onlineRatio) {
		this.onlineRatio = onlineRatio;
	}

	public long getSelf() {
		return self;
	}

	public void setSelf(long self) {
		this.self = self;
	}

	public float getSelfRatio() {
		return selfRatio;
	}

	public void setSelfRatio(float selfRatio) {
		this.selfRatio = selfRatio;
	}

	public long getOnlineSoft() {
		return onlineSoft;
	}

	public void setOnlineSoft(long onlineSoft) {
		this.onlineSoft = onlineSoft;
	}

	public float getOnlineSoftRatio() {
		return onlineSoftRatio;
	}

	public void setOnlineSoftRatio(float onlineSoftRatio) {
		this.onlineSoftRatio = onlineSoftRatio;
	}

	public long getSearch() {
		return search;
	}

	public void setSearch(long search) {
		this.search = search;
	}

	public float getSearchRatio() {
		return searchRatio;
	}

	public void setSearchRatio(float searchRatio) {
		this.searchRatio = searchRatio;
	}

	public long getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(long upgrade) {
		this.upgrade = upgrade;
	}

	public float getUpgradeRatio() {
		return upgradeRatio;
	}

	public void setUpgradeRatio(float upgradeRatio) {
		this.upgradeRatio = upgradeRatio;
	}

	public long getBigGame() {
		return bigGame;
	}

	public void setBigGame(long bigGame) {
		this.bigGame = bigGame;
	}

	public long getDaohang() {
		return daohang;
	}

	public void setDaohang(long daohang) {
		this.daohang = daohang;
	}

	public long getInstaller() {
		return installer;
	}

	public void setInstaller(long installer) {
		this.installer = installer;
	}
}
