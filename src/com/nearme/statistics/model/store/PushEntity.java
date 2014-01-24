package com.nearme.statistics.model.store;

import java.io.Serializable;
import java.util.Date;

/**
 * push推送报表Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class PushEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private String pushObj;// 推送对象
	private String timePeriod;// 统计时间段
	private String pushType;// 推送形式
	private String pushDetailUser;// 推送细分用户
	private long sendPushCnt;// 发送push数
	private long arriveImei;// 到达IMEI数
	private float arriveRatio;// 到达率
	private long lookImei;// 查看IMEI数
	private long refuseImei;// 拒绝IMEI数
	private long pushCauseDown;// 推送产生下载量
	private long periodTotalDown;// 该软件/专题此期间总下载量

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

	public String getPushObj() {
		return pushObj;
	}

	public void setPushObj(String pushObj) {
		this.pushObj = pushObj;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getPushDetailUser() {
		return pushDetailUser;
	}

	public void setPushDetailUser(String pushDetailUser) {
		this.pushDetailUser = pushDetailUser;
	}

	public long getSendPushCnt() {
		return sendPushCnt;
	}

	public void setSendPushCnt(long sendPushCnt) {
		this.sendPushCnt = sendPushCnt;
	}

	public long getArriveImei() {
		return arriveImei;
	}

	public void setArriveImei(long arriveImei) {
		this.arriveImei = arriveImei;
	}

	public float getArriveRatio() {
		return arriveRatio;
	}

	public void setArriveRatio(float arriveRatio) {
		this.arriveRatio = arriveRatio;
	}

	public long getLookImei() {
		return lookImei;
	}

	public void setLookImei(long lookImei) {
		this.lookImei = lookImei;
	}

	public long getRefuseImei() {
		return refuseImei;
	}

	public void setRefuseImei(long refuseImei) {
		this.refuseImei = refuseImei;
	}

	public long getPushCauseDown() {
		return pushCauseDown;
	}

	public void setPushCauseDown(long pushCauseDown) {
		this.pushCauseDown = pushCauseDown;
	}

	public long getPeriodTotalDown() {
		return periodTotalDown;
	}

	public void setPeriodTotalDown(long periodTotalDown) {
		this.periodTotalDown = periodTotalDown;
	}
}
