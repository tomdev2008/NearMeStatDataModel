package com.nearme.statistics.model.cloud;

import java.io.Serializable;
import java.util.Date;

/**
 * 流量分配
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-13
 */
public class CloudFlowdistributeEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long mainpage;// 主界面
	private long contectbak;// 联系人备份
	private long smsbak;// 短信备份
	private long cloadphoto;// 云相册
	private long netdisk;// 网盘
	private long cloudnote;// 云笔记
	private long findphone;// 找回手机
	private long keybak;// 一键备份
	private long keyreturn;// 一键恢复
	private long personal;// 个人资料
	private long cloudspace;// 云存储空间
	private long help;// 帮助

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

	public long getMainpage() {
		return mainpage;
	}

	public void setMainpage(long mainpage) {
		this.mainpage = mainpage;
	}

	public long getContectbak() {
		return contectbak;
	}

	public void setContectbak(long contectbak) {
		this.contectbak = contectbak;
	}

	public long getSmsbak() {
		return smsbak;
	}

	public void setSmsbak(long smsbak) {
		this.smsbak = smsbak;
	}

	public long getCloadphoto() {
		return cloadphoto;
	}

	public void setCloadphoto(long cloadphoto) {
		this.cloadphoto = cloadphoto;
	}

	public long getNetdisk() {
		return netdisk;
	}

	public void setNetdisk(long netdisk) {
		this.netdisk = netdisk;
	}

	public long getCloudnote() {
		return cloudnote;
	}

	public void setCloudnote(long cloudnote) {
		this.cloudnote = cloudnote;
	}

	public long getFindphone() {
		return findphone;
	}

	public void setFindphone(long findphone) {
		this.findphone = findphone;
	}

	public long getKeybak() {
		return keybak;
	}

	public void setKeybak(long keybak) {
		this.keybak = keybak;
	}

	public long getKeyreturn() {
		return keyreturn;
	}

	public void setKeyreturn(long keyreturn) {
		this.keyreturn = keyreturn;
	}

	public long getPersonal() {
		return personal;
	}

	public void setPersonal(long personal) {
		this.personal = personal;
	}

	public long getCloudspace() {
		return cloudspace;
	}

	public void setCloudspace(long cloudspace) {
		this.cloudspace = cloudspace;
	}

	public long getHelp() {
		return help;
	}

	public void setHelp(long help) {
		this.help = help;
	}
}
