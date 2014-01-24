package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 资源包安装
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ZJBPkginstallEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long installtotal;// 安装总量
	private long keycnt;// 一键安装量
	private float keyratio;// 一键安装占比
	private long selfcnt;// 自定义资源一键安装量
	private float selfratio;// 自定义资源一键安装占比
	private long onlinecnt;// 在线资源一键安装量
	private float onlineratio;// 在线资源一键安装占比
	private long innercnt;// 包内安装量
	private float innerratio;// 包内安装占比
	private long selfinnercnt;// 自定义资源包内安装量
	private float selfinnerratio;// 自定义资源包内安装占比
	private long onlineinnercnt;// 在线资源包内安装量
	private float onlineinnerratio;// 在线资源包内安装占比

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

	public long getInstalltotal() {
		return installtotal;
	}

	public void setInstalltotal(long installtotal) {
		this.installtotal = installtotal;
	}

	public long getKeycnt() {
		return keycnt;
	}

	public void setKeycnt(long keycnt) {
		this.keycnt = keycnt;
	}

	public float getKeyratio() {
		return keyratio;
	}

	public void setKeyratio(float keyratio) {
		this.keyratio = keyratio;
	}

	public long getSelfcnt() {
		return selfcnt;
	}

	public void setSelfcnt(long selfcnt) {
		this.selfcnt = selfcnt;
	}

	public float getSelfratio() {
		return selfratio;
	}

	public void setSelfratio(float selfratio) {
		this.selfratio = selfratio;
	}

	public long getOnlinecnt() {
		return onlinecnt;
	}

	public void setOnlinecnt(long onlinecnt) {
		this.onlinecnt = onlinecnt;
	}

	public float getOnlineratio() {
		return onlineratio;
	}

	public void setOnlineratio(float onlineratio) {
		this.onlineratio = onlineratio;
	}

	public long getInnercnt() {
		return innercnt;
	}

	public void setInnercnt(long innercnt) {
		this.innercnt = innercnt;
	}

	public float getInnerratio() {
		return innerratio;
	}

	public void setInnerratio(float innerratio) {
		this.innerratio = innerratio;
	}

	public long getSelfinnercnt() {
		return selfinnercnt;
	}

	public void setSelfinnercnt(long selfinnercnt) {
		this.selfinnercnt = selfinnercnt;
	}

	public float getSelfinnerratio() {
		return selfinnerratio;
	}

	public void setSelfinnerratio(float selfinnerratio) {
		this.selfinnerratio = selfinnerratio;
	}

	public long getOnlineinnercnt() {
		return onlineinnercnt;
	}

	public void setOnlineinnercnt(long onlineinnercnt) {
		this.onlineinnercnt = onlineinnercnt;
	}

	public float getOnlineinnerratio() {
		return onlineinnerratio;
	}

	public void setOnlineinnerratio(float onlineinnerratio) {
		this.onlineinnerratio = onlineinnerratio;
	}
}
