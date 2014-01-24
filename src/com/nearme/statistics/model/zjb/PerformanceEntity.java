package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 性能指标
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 * @since 1.1, 2013-10-29 修改调整
 */
public class PerformanceEntity implements java.io.Serializable {
	private static final long serialVersionUID = -205351673683985453L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long connectCnt; // 连接次数
	private long connectSuccess;// 连接成功次数
	private float connectRatio; // 连接成功率
	private long downCnt; // 下载次数
	private long downSuccess; // 下载成功次数
	private float downRatio;// 下载成功率
	private long installCnt;// 安装次数
	private long installSuccess;// 安装成功次数
	private float installRatio;// 安装成功率
	private long softStart;// 软件启动次数
	private long softCrash;// 软件崩溃次数
	private float softCrashRatio;// 软件崩溃率
	private long restoreCnt;// 演示机还原次数
	private long restoreSuccess;// 演示机还原成功次数
	private float restoreRatio;// 演示机还原成功率

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

	public long getConnectCnt() {
		return connectCnt;
	}

	public void setConnectCnt(long connectCnt) {
		this.connectCnt = connectCnt;
	}

	public long getConnectSuccess() {
		return connectSuccess;
	}

	public void setConnectSuccess(long connectSuccess) {
		this.connectSuccess = connectSuccess;
	}

	public float getConnectRatio() {
		return connectRatio;
	}

	public void setConnectRatio(float connectRatio) {
		this.connectRatio = connectRatio;
	}

	public long getDownCnt() {
		return downCnt;
	}

	public void setDownCnt(long downCnt) {
		this.downCnt = downCnt;
	}

	public long getDownSuccess() {
		return downSuccess;
	}

	public void setDownSuccess(long downSuccess) {
		this.downSuccess = downSuccess;
	}

	public float getDownRatio() {
		return downRatio;
	}

	public void setDownRatio(float downRatio) {
		this.downRatio = downRatio;
	}

	public long getInstallCnt() {
		return installCnt;
	}

	public void setInstallCnt(long installCnt) {
		this.installCnt = installCnt;
	}

	public long getInstallSuccess() {
		return installSuccess;
	}

	public void setInstallSuccess(long installSuccess) {
		this.installSuccess = installSuccess;
	}

	public float getInstallRatio() {
		return installRatio;
	}

	public void setInstallRatio(float installRatio) {
		this.installRatio = installRatio;
	}

	public long getSoftStart() {
		return softStart;
	}

	public void setSoftStart(long softStart) {
		this.softStart = softStart;
	}

	public long getSoftCrash() {
		return softCrash;
	}

	public void setSoftCrash(long softCrash) {
		this.softCrash = softCrash;
	}

	public float getSoftCrashRatio() {
		return softCrashRatio;
	}

	public void setSoftCrashRatio(float softCrashRatio) {
		this.softCrashRatio = softCrashRatio;
	}

	public long getRestoreCnt() {
		return restoreCnt;
	}

	public void setRestoreCnt(long restoreCnt) {
		this.restoreCnt = restoreCnt;
	}

	public long getRestoreSuccess() {
		return restoreSuccess;
	}

	public void setRestoreSuccess(long restoreSuccess) {
		this.restoreSuccess = restoreSuccess;
	}

	public float getRestoreRatio() {
		return restoreRatio;
	}

	public void setRestoreRatio(float restoreRatio) {
		this.restoreRatio = restoreRatio;
	}
}
