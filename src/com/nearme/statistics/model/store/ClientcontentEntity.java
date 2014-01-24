package com.nearme.statistics.model.store;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户端内容分布Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class ClientcontentEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long app;// 应用
	private long appupdate;// 应用更新量
	private long appeffect;// 应用有效流量
	private long game;// 游戏
	private long gameupdate;// 游戏更新量
	private long gameeffect;// 游戏有效流量
	private long theme;// 主题
	private long themenew;// 新主题
	private long wallpaper;// 壁纸
	private long ring;// 铃声
	private long font;// 字体
	private long ebook;// 电子书
	private long colorring;// 彩铃

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

	public long getApp() {
		return app;
	}

	public void setApp(long app) {
		this.app = app;
	}

	public long getAppupdate() {
		return appupdate;
	}

	public void setAppupdate(long appupdate) {
		this.appupdate = appupdate;
	}

	public long getAppeffect() {
		return appeffect;
	}

	public void setAppeffect(long appeffect) {
		this.appeffect = appeffect;
	}

	public long getGame() {
		return game;
	}

	public void setGame(long game) {
		this.game = game;
	}

	public long getGameupdate() {
		return gameupdate;
	}

	public void setGameupdate(long gameupdate) {
		this.gameupdate = gameupdate;
	}

	public long getGameeffect() {
		return gameeffect;
	}

	public void setGameeffect(long gameeffect) {
		this.gameeffect = gameeffect;
	}

	public long getTheme() {
		return theme;
	}

	public void setTheme(long theme) {
		this.theme = theme;
	}

	public long getWallpaper() {
		return wallpaper;
	}

	public void setWallpaper(long wallpaper) {
		this.wallpaper = wallpaper;
	}

	public long getRing() {
		return ring;
	}

	public void setRing(long ring) {
		this.ring = ring;
	}

	public long getFont() {
		return font;
	}

	public void setFont(long font) {
		this.font = font;
	}

	public long getEbook() {
		return ebook;
	}

	public void setEbook(long ebook) {
		this.ebook = ebook;
	}

	public long getColorring() {
		return colorring;
	}

	public void setColorring(long colorring) {
		this.colorring = colorring;
	}

	public long getThemenew() {
		return themenew;
	}

	public void setThemenew(long themenew) {
		this.themenew = themenew;
	}
}
