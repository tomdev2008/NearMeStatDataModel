package com.nearme.statistics.form;

import java.util.Date;

import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.NumericUtil;

/**
 * 需求提交Form
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-19
 */
public class RequestForm {
	// 1.查询设置
	private String systemID;
	private String startTime;
	private String endTime;

	private String lidu;// 查询的粒度
	private String model;// 机型
	private String appVersion;// 版本
	private String qudao;// 渠道
	private String path;// 路径
	private String productname;// 产品名称

	// 2.样本选择
	private String type;// 类型
	private String sampleStartTime;
	private String sampleEndTime;

	// 3.指标设置
	private String zhibiao;// 指标

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSampleStartTime() {
		return sampleStartTime;
	}

	public void setSampleStartTime(String sampleStartTime) {
		this.sampleStartTime = sampleStartTime;
	}

	public String getSampleEndTime() {
		return sampleEndTime;
	}

	public void setSampleEndTime(String sampleEndTime) {
		this.sampleEndTime = sampleEndTime;
	}

	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * 获取endTime的Date值
	 * 
	 * @return
	 */
	public Date getEndDate() {
		return getEndDate("yyyy-MM-dd");
	}

	public Date getEndDate(String fmt) {
		return DateUtil.parseDate(endTime, fmt);
	}

	public String getEndTime() {
		return endTime;
	}

	public String getLidu() {
		return lidu;
	}

	public String getModel() {
		return model;
	}

	public String getQudao() {
		return qudao;
	}

	/**
	 * 获取startTime的Date值
	 * 
	 * @return
	 */
	public Date getStartDate() {
		return getStartDate("yyyy-MM-dd");
	}

	public Date getStartDate(String fmt) {
		return DateUtil.parseDate(startTime, fmt);
	}

	public String getStartTime() {
		return startTime;
	}

	public String getSystemID() {
		return systemID;
	}

	/**
	 * 获取SystemID的int值
	 * 
	 * @return
	 */
	public int getSystemIDValue() {
		return NumericUtil.tryParse(systemID, 0);
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setLidu(String lidu) {
		this.lidu = lidu;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setQudao(String qudao) {
		this.qudao = qudao;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getZhibiao() {
		return zhibiao;
	}

	public void setZhibiao(String zhibiao) {
		this.zhibiao = zhibiao;
	}

	public Date getSampleEndDate() {
		return DateUtil.parseDate(sampleEndTime, "yyyy-MM-dd");
	}

	public Date getSampleStartDate() {
		return DateUtil.parseDate(sampleStartTime, "yyyy-MM-dd");
	}
}
