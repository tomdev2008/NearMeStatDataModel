package com.nearme.statistics.model.common;

/**
 * @author 朱峰
 * @function 页面访问分析-页面跳转明细 Entity
 * @version 1.0
 * @since 1.0, 2013-4-24
 */
public class PagejumpEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4834611884603801850L;

	private String jumppage;// 跳转页面
	private String vjumpRatio;// 页面访问跳转比率
	private String vimeiJumpRatio;// 页面访问IMEI跳转比率

	public String getJumppage() {
		return jumppage;
	}

	public void setJumppage(String jumppage) {
		this.jumppage = jumppage;
	}

	public String getVjumpRatio() {
		return vjumpRatio;
	}

	public void setVjumpRatio(String vjumpRatio) {
		this.vjumpRatio = vjumpRatio;
	}

	public String getVimeiJumpRatio() {
		return vimeiJumpRatio;
	}

	public void setVimeiJumpRatio(String vimeiJumpRatio) {
		this.vimeiJumpRatio = vimeiJumpRatio;
	}
}
