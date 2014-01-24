package com.nearme.statistics.model.cloud;

import java.io.Serializable;
import java.util.Date;

/**
 * 路径统计
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-13
 */
public class CloudPathEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long entermainpage;// 进入到主界面
	private long fileup;// 文件上传
	private long filedelete;// 删除文件
	private long filedown;// 下载文件
	private long wifiopen;// wifi自动上传打开
	private long g2g3open;// 2G/3G流量限制打开
	private long mainpage;// 主界面
	private long fileupbutton;// 文件上传按钮
	private long upsuccess;// 上传成功

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

	public long getEntermainpage() {
		return entermainpage;
	}

	public void setEntermainpage(long entermainpage) {
		this.entermainpage = entermainpage;
	}

	public long getFileup() {
		return fileup;
	}

	public void setFileup(long fileup) {
		this.fileup = fileup;
	}

	public long getFiledelete() {
		return filedelete;
	}

	public void setFiledelete(long filedelete) {
		this.filedelete = filedelete;
	}

	public long getFiledown() {
		return filedown;
	}

	public void setFiledown(long filedown) {
		this.filedown = filedown;
	}

	public long getWifiopen() {
		return wifiopen;
	}

	public void setWifiopen(long wifiopen) {
		this.wifiopen = wifiopen;
	}

	public long getG2g3open() {
		return g2g3open;
	}

	public void setG2g3open(long g2g3open) {
		this.g2g3open = g2g3open;
	}

	public long getMainpage() {
		return mainpage;
	}

	public void setMainpage(long mainpage) {
		this.mainpage = mainpage;
	}

	public long getFileupbutton() {
		return fileupbutton;
	}

	public void setFileupbutton(long fileupbutton) {
		this.fileupbutton = fileupbutton;
	}

	public long getUpsuccess() {
		return upsuccess;
	}

	public void setUpsuccess(long upsuccess) {
		this.upsuccess = upsuccess;
	}
}
