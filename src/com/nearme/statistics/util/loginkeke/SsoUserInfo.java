package com.nearme.statistics.util.loginkeke;

import java.sql.Timestamp;

/**
 * 个人中心登陆
 * 信息体
 * 
 * 由于该系统只需要从sso获取用户信息的一个接口，因此没有对错误码进行
 * 转码，所传过来的错误码均为sso的错误码，请注意
 * @author 80055892
 *
 */
public class SsoUserInfo {
	private String userId; // sso中的用户标识
	private String userName; // 用户名称
	private String oldEmail; // 旧邮箱
	private String createBy; // 应用ip
	private String createIP; // 客户端ip
	private String password; // 密码
	private String OPPOIdentity; // 加密结果
	private String userAgent; //
	private String email; // 用户邮箱
	private String sessionKey; // 登录时产生的sessionKey
	private String status; // 用户状态: OK和LOCKED,DENIED
	private String emailStatus; // 邮箱状态：OK和LOCKED,DENIED
	private String mobileStatus; // 手机状态：OK和LOCKED,DENIED
	private String mobile; // 手机号
	private String validateCode; //
	private String passwordOriginal;// 原始密码
	private String createTime; // 创建时间
	private String lastUpdate; // 最后更新时间
	private String resultCode; // 返回结果：参见SsoConstants
	private String resultDesc; // 状态描述
	private String applicationKey; // 应用KEY
	private String loginType; // 登录终端
	private String activationCode; // 激活码
	private String activationType; // 激活码类型(0表示email, 1表示mobile)

	private String question0; // 密保问题1
	private String question1; // 密保问题2
	private String question2; // 密保问题3
	private String answer0; // 密保答案1
	private String answer1; // 密保答案2
	private String answer2; // 密保答案3

	private String templateCode; // 短信模板代码

	private int templateId;
	private String templateDesc;
	private String templateMsg;
	private String templateType;
	private String updateBy;
	private Timestamp updateTime;
	
	private String filterWord;   //包含的敏感词
	
	public String getFilterWord() {
		return filterWord;
	}

	public void setFilterWord(String filterWord) {
		this.filterWord = filterWord;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public String getTemplateMsg() {
		return templateMsg;
	}

	public void setTemplateMsg(String templateMsg) {
		this.templateMsg = templateMsg;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getCommonErrorCode() {
		return this.resultCode;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

	public String getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(String mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getApplicationKey() {
		return applicationKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOPPOIdentity() {
		return OPPOIdentity;
	}

	public void setOPPOIdentity(String oPPOIdentity) {
		OPPOIdentity = oPPOIdentity;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getPasswordOriginal() {
		return passwordOriginal;
	}

	public void setPasswordOriginal(String passwordOriginal) {
		this.passwordOriginal = passwordOriginal;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateIP() {
		return createIP;
	}

	public void setCreateIP(String createIP) {
		this.createIP = createIP;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * 激活码类型(0表示email, 1表示mobile) 填充：activationType
	 *
	 * @param activationType
	 *            the activationType to set
	 * @since CodingExample Ver 1.0
	 */

	public void setActivationType(String activationType) {
		this.activationType = activationType;
	}

	/**
	 * 激活码类型(0表示email, 1表示mobile) 获取 activationType
	 *
	 * @return the activationType
	 * @since CodingExample Ver 1.0
	 */

	public String getActivationType() {
		return activationType;
	}

	public void setQuestion0(String question0) {
		this.question0 = question0;
	}

	public String getQuestion0() {
		return question0;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setAnswer0(String answer0) {
		this.answer0 = answer0;
	}

	public String getAnswer0() {
		return answer0;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer2() {
		return answer2;
	}

}