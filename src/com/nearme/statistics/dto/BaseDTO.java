package com.nearme.statistics.dto;

import java.util.Date;

import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.mapreduce.FuncCodeEnum;
import com.nearme.statistics.mapreduce.MapReduce;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.NumericUtil;

/**
 * 统计基础DTO
 * 
 * @author 刘超
 * @author 段锦涛
 * @author 朱峰
 * @version 1.0
 * @since 1.0,2012-6-14
 * @version 1.2,2013-5-14
 */
public class BaseDTO {

	private Date sampleStartDate;// 样本起始时间
	private Date sampleEndDate;// 样本终止时间

	private int days;
	private Date startDate;
	private Date endDate;

	private int systemID;
	private String eventTag;

	private String bakTableAffix;// 按月备份表后缀,由startDate计算yyyyMM得出

	private String model;// 机型
	private int networkID;
	private String networkType;// 网络环境
	private int channelID;
	private String sourceCode;
	private String appVersion;

	private String lidu;// 粒度（日、周、月）
	private String decaycircle;// 衰减周期
	private String qudao;// 渠道
	private String type;// 类型（启动IMEI、新增IMEI）
	private String place;// 地区

	private String page;// 页面跳转参数

	private String groupcode;// 用户分组code
	private String actioncode;// 用户行为code

	private String groupname;// 模块名称
	private int position;// 位置

	// 分页
	private int rownumbegin;// 查询结果集起始位置
	private int rownumend;// 查询结果集结束位置
	
	private String errordetail;//异常详情

	public int getRownumbegin() {
		return rownumbegin;
	}

	public void setRownumbegin(int rownumbegin) {
		this.rownumbegin = rownumbegin;
	}

	public int getRownumend() {
		return rownumend;
	}

	public void setRownumend(int rownumend) {
		this.rownumend = rownumend;
	}

	public Date getSampleStartDate() {
		return sampleStartDate;
	}

	public void setSampleStartDate(Date sampleStartDate) {
		this.sampleStartDate = sampleStartDate;
	}

	public Date getSampleEndDate() {
		return sampleEndDate;
	}

	public void setSampleEndDate(Date sampleEndDate) {
		this.sampleEndDate = sampleEndDate;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getQudao() {
		return qudao;
	}

	public void setQudao(String qudao) {
		this.qudao = qudao;
	}

	public String getLidu() {
		return lidu;
	}

	public void setLidu(String lidu) {
		this.lidu = lidu;
	}

	public String getDecaycircle() {
		return decaycircle;
	}

	public void setDecaycircle(String decaycircle) {
		this.decaycircle = decaycircle;
	}

	private FuncCodeEnum funcCode = FuncCodeEnum.CLIENT_START;

	/**
	 * 按分表规则获取分表后缀
	 * 
	 * @return
	 */
	public String getMapTable() {
		return MapReduce.getMapTable(funcCode, systemID);
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public boolean fillFromForm(BaseForm form) {
		if (form == null) {
			return false;
		}
		// 提取起始、结束日期
		Date t1 = DateUtil.AddDays(DateUtil.trunc(new Date()), -1);// 昨天
		if (form.getDays() != null) {
			endDate = t1;
			form.setEndTime(DateUtil.formatDate(t1, "yyyy-MM-dd"));
			startDate = DateUtil.AddDays(t1, 0 - NumericUtil.tryParse(form
					.getDays(), 30));
			form.setStartTime(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
		} else {
			startDate = form.getStartDate();
			endDate = form.getEndDate();
		}

		// 判断日期有效性
		if (getEndDate() == null) {
			endDate = t1;
			// 设置回FORM,避免查询数据使用的日期和页面显示日期不一致
			form.setEndTime(DateUtil.formatDate(t1, "yyyy-MM-dd"));
		}
		if (getStartDate() == null || getEndDate().before(getStartDate())) {
			startDate = DateUtil.AddDays(t1, -30);
			// 设置回FORM,避免查询数据使用的日期和页面显示日期不一致
			form.setStartTime(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
		}

		systemID = form.getSystemIDValue();
		model = form.getModel();
		appVersion = form.getAppVersion();

		return true;
	}

	/**
	 * 缓存KEY
	 */
	public String toString() {
		return String.format("%s_%s_%s_%s", this.systemID, DateUtil.formatDate(
				this.startDate, "yyyyMMdd"), DateUtil.formatDate(this.endDate,
				"yyyyMMdd"), this.model);
	}

	public String getBakTableAffix() {
		if (this.bakTableAffix == null && this.startDate != null) {
			this.bakTableAffix = DateUtil.formatDate(this.startDate, "yyyyMM");
		}
		return bakTableAffix;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
	}

	public String getEventTag() {
		return eventTag;
	}

	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNetworkID() {
		return networkID;
	}

	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}

	public int getChannelID() {
		return channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public FuncCodeEnum getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(FuncCodeEnum funcCode) {
		this.funcCode = funcCode;
	}

	public void setBakTableAffix(String bakTableAffix) {
		this.bakTableAffix = bakTableAffix;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getDays() {
		return days;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
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

	public String getErrordetail() {
		return errordetail;
	}

	public void setErrordetail(String errordetail) {
		this.errordetail = errordetail;
	}
}
