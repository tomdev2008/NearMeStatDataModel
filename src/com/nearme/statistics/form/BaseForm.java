package com.nearme.statistics.form;

import java.util.Date;

import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.NumericUtil;

/**
 * 基础信息Form
 * 
 * @author 刘超
 * @author 朱峰
 * @version 1.1
 * @since 1.0,2012-6-14
 * @since 1.1,2012-9-16
 */
public class BaseForm {
	private String jobID;// hive job ID
	private String resulttable;// 结果表参考
	private String weidu;

	private String sampleStartTime;// 样本起始时间
	private String sampleEndTime;// 样本终止时间

	private String systemID;
	private String appVersion;// 版本
	private String days;
	private String startTime;
	private String endTime;

	private String model;// 终端机型
	private String name;
	private String categoryId;// 分类id号

	private String lidu;// 查询的粒度
	private String decaycircle;// 衰减周期
	private String qudao;// 渠道
	private String type;// 分析类型(例如：启动IMEI)
	private String network;// 网络
	private String place;// 区域

	private String groupcode;// 用户分组code
	private String actioncode;// 用户行为code
	private String tips;
	
	private String groupname;//模块名称
	private int position;//位置
	
	// 分页参数
	private int pagecnt;// 总页数
	private int pagerows;// 每页显示条数
	private int pagecurr;// 当前显示第几页
	
	private String errordetail;//异常详情

	public int getPagecnt() {
		return pagecnt;
	}

	public void setPagecnt(int pagecnt) {
		this.pagecnt = pagecnt;
	}

	public int getPagerows() {
		return pagerows;
	}

	public void setPagerows(int pagerows) {
		this.pagerows = pagerows;
	}

	public int getPagecurr() {
		return pagecurr;
	}

	public void setPagecurr(int pagecurr) {
		this.pagecurr = pagecurr;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getResulttable() {
		return resulttable;
	}

	public void setResulttable(String resulttable) {
		this.resulttable = resulttable;
	}

	public String getWeidu() {
		return weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public String getActioncode() {
		return actioncode;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getDays() {
		return days;
	}

	/**
	 * 获取days的int值
	 * 
	 * @return
	 */
	public int getDaysValue() {
		return NumericUtil.tryParse(days, 0);
	}

	public String getDecaycircle() {
		return decaycircle;
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

	public String getGroupcode() {
		return groupcode;
	}

	public String getLidu() {
		return lidu;
	}

	public String getModel() {
		return model;
	}

	public String getName() {
		return name;
	}

	public String getNetwork() {
		return network;
	}

	public String getPlace() {
		return place;
	}

	public String getQudao() {
		return qudao;
	}

	public Date getSampleEndDate() {
		return DateUtil.parseDate(sampleEndTime, "yyyy-MM-dd");
	}

	public Date getSampleStartDate() {
		return DateUtil.parseDate(sampleStartTime, "yyyy-MM-dd");
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

	public String getType() {
		return type;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public void setDecaycircle(String decaycircle) {
		this.decaycircle = decaycircle;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public void setLidu(String lidu) {
		this.lidu = lidu;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setQudao(String qudao) {
		this.qudao = qudao;
	}

	public void setSampleEndTime(String sampleEndTime) {
		this.sampleEndTime = sampleEndTime;
	}

	public void setSampleStartTime(String sampleStartTime) {
		this.sampleStartTime = sampleStartTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSampleStartTime() {
		return sampleStartTime;
	}

	public String getSampleEndTime() {
		return sampleEndTime;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getErrordetail() {
		return errordetail;
	}

	public void setErrordetail(String errordetail) {
		this.errordetail = errordetail;
	}
}
