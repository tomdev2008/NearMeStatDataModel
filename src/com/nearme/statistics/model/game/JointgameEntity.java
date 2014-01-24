package com.nearme.statistics.model.game;

import java.io.Serializable;
import java.util.Date;

import com.nearme.statistics.util.DateUtil;

/**
 * 联运游戏数据
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-12
 */
public class JointgameEntity implements Serializable {
	private static final long serialVersionUID = -837931459152399030L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long totaluser;// 总用户
	private long newimei;// 新增激活
	private long newuser;// 新增用户
	private long loginuser;// 登陆用户
	private long startcnt;// 启动次数
	private long lost7;// 流失7
	private long lost30;// 流失30
	private float remain2;// 次日留存
	private float remain7;// 7日留存
	private long feeuser;// 付费人数
	private float feetotal;// 付费总额
	private float feeratio;// 付费率
	private float arpu;// arpu
	private float arppu;// arppu

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
	
	public String getStatDateStr() {
		return DateUtil.formatDate(statDate, "yyyyMMdd");
	}

	public Date getStatEndDate() {
		return statEndDate;
	}

	public void setStatEndDate(Date statEndDate) {
		this.statEndDate = statEndDate;
	}

	public long getTotaluser() {
		return totaluser;
	}

	public void setTotaluser(long totaluser) {
		this.totaluser = totaluser;
	}

	public long getNewimei() {
		return newimei;
	}

	public void setNewimei(long newimei) {
		this.newimei = newimei;
	}

	public long getNewuser() {
		return newuser;
	}

	public void setNewuser(long newuser) {
		this.newuser = newuser;
	}

	public long getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(long loginuser) {
		this.loginuser = loginuser;
	}

	public long getStartcnt() {
		return startcnt;
	}

	public void setStartcnt(long startcnt) {
		this.startcnt = startcnt;
	}

	public long getLost7() {
		return lost7;
	}

	public void setLost7(long lost7) {
		this.lost7 = lost7;
	}

	public long getLost30() {
		return lost30;
	}

	public void setLost30(long lost30) {
		this.lost30 = lost30;
	}

	public float getRemain2() {
		return remain2;
	}

	public void setRemain2(float remain2) {
		this.remain2 = remain2;
	}

	public float getRemain7() {
		return remain7;
	}

	public void setRemain7(float remain7) {
		this.remain7 = remain7;
	}

	public long getFeeuser() {
		return feeuser;
	}

	public void setFeeuser(long feeuser) {
		this.feeuser = feeuser;
	}

	public float getFeetotal() {
		return feetotal;
	}

	public void setFeetotal(float feetotal) {
		this.feetotal = feetotal;
	}

	public float getFeeratio() {
		return feeratio;
	}

	public void setFeeratio(float feeratio) {
		this.feeratio = feeratio;
	}

	public float getArpu() {
		return arpu;
	}

	public void setArpu(float arpu) {
		this.arpu = arpu;
	}

	public float getArppu() {
		return arppu;
	}

	public void setArppu(float arppu) {
		this.arppu = arppu;
	}

}
