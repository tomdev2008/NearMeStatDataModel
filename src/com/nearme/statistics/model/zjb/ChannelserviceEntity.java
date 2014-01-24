package com.nearme.statistics.model.zjb;

import java.util.Date;

/**
 * 分渠道服务
 *
 * @author 林逸聪
 * @version 1.0
 * @since 1.0, 2014-1-3
 */
public class ChannelserviceEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5497696900193627602L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long in_users;// 登陆用户数
	private long in_not_oppo;// 登陆用户服务的非OPPO手机数
	private long in_not_oppo_bonus;// 分成手机数
	private float in_not_oppo_avg;// 登陆用户服务的非OPPO手机台均安装量
	private long in_oppo;// 登陆用户服务的OPPO手机数
	private long in_oppo_bonus;// 分成手机数
	private float in_oppo_avg;// 登陆用户服务的OPPO手机台均安装量
	private long out_users;// 未登陆用户
	private long out_not_oppo;// 未登陆用户服务的非OPPO手机数
	private long out_not_oppo_bonus;// 分成手机数
	private float out_not_oppo_avg;// 未登陆用户服务的非OPPO手机台均安装量
	private long out_oppo;// 未登陆用户服务的OPPO手机数
	private long out_oppo_bonus;// 分成手机数
	private float out_oppo_avg;// 未登陆用户服务的OPPO手机台均安装量
	private long in_oppo_apps;//登陆oppo安装app
	private long in_not_oppo_apps;//登陆非oppo安装app
	private long out_oppo_apps;//未登陆oppo安装app
	private long out_not_oppo_apps;//未登陆非oppo安装app


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
	public long getIn_users() {
		return in_users;
	}
	public void setIn_users(long in_users) {
		this.in_users = in_users;
	}
	public long getIn_not_oppo() {
		return in_not_oppo;
	}
	public void setIn_not_oppo(long in_not_oppo) {
		this.in_not_oppo = in_not_oppo;
	}
	public long getIn_not_oppo_bonus() {
		return in_not_oppo_bonus;
	}
	public void setIn_not_oppo_bonus(long in_not_oppo_bonus) {
		this.in_not_oppo_bonus = in_not_oppo_bonus;
	}
	public float getIn_not_oppo_avg() {
		return in_not_oppo_avg;
	}
	public void setIn_not_oppo_avg(float in_not_oppo_avg) {
		this.in_not_oppo_avg = in_not_oppo_avg;
	}
	public long getIn_oppo() {
		return in_oppo;
	}
	public void setIn_oppo(long in_oppo) {
		this.in_oppo = in_oppo;
	}
	public long getIn_oppo_bonus() {
		return in_oppo_bonus;
	}
	public void setIn_oppo_bonus(long in_oppo_bonus) {
		this.in_oppo_bonus = in_oppo_bonus;
	}
	public float getIn_oppo_avg() {
		return in_oppo_avg;
	}
	public void setIn_oppo_avg(float in_oppo_avg) {
		this.in_oppo_avg = in_oppo_avg;
	}
	public long getOut_users() {
		return out_users;
	}
	public void setOut_users(long out_users) {
		this.out_users = out_users;
	}
	public long getOut_not_oppo() {
		return out_not_oppo;
	}
	public void setOut_not_oppo(long out_not_oppo) {
		this.out_not_oppo = out_not_oppo;
	}
	public long getOut_not_oppo_bonus() {
		return out_not_oppo_bonus;
	}
	public void setOut_not_oppo_bonus(long out_not_oppo_bonus) {
		this.out_not_oppo_bonus = out_not_oppo_bonus;
	}
	public float getOut_not_oppo_avg() {
		return out_not_oppo_avg;
	}
	public void setOut_not_oppo_avg(float out_not_oppo_avg) {
		this.out_not_oppo_avg = out_not_oppo_avg;
	}
	public long getOut_oppo() {
		return out_oppo;
	}
	public void setOut_oppo(long out_oppo) {
		this.out_oppo = out_oppo;
	}
	public long getOut_oppo_bonus() {
		return out_oppo_bonus;
	}
	public void setOut_oppo_bonus(long out_oppo_bonus) {
		this.out_oppo_bonus = out_oppo_bonus;
	}
	public float getOut_oppo_avg() {
		return out_oppo_avg;
	}
	public void setOut_oppo_avg(float out_oppo_avg) {
		this.out_oppo_avg = out_oppo_avg;
	}
	public float getIn_oppo_apps() {
		return in_oppo_apps;
	}
	public float getIn_not_oppo_apps() {
		return in_not_oppo_apps;
	}
	public float getOut_oppo_apps() {
		return out_oppo_apps;
	}
	public long getOut_not_oppo_apps() {
		return out_not_oppo_apps;
	}
	public void setOut_not_oppo_apps(long out_not_oppo_apps) {
		this.out_not_oppo_apps = out_not_oppo_apps;
	}
	public void setIn_oppo_apps(long in_oppo_apps) {
		this.in_oppo_apps = in_oppo_apps;
	}
	public void setIn_not_oppo_apps(long in_not_oppo_apps) {
		this.in_not_oppo_apps = in_not_oppo_apps;
	}
	public void setOut_oppo_apps(long out_oppo_apps) {
		this.out_oppo_apps = out_oppo_apps;
	}
}
