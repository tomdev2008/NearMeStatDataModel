package com.nearme.statistics.model.sys.admin;

import java.util.Date;

/**
 * 参数实体类
 * @author 80054686
 * @version 1.0
 * @since 1.0 2013-05-10
 */
public class Parameters implements java.io.Serializable{
	private static final long serialVersionUID = -2416986680446727065L;
	private String pCode;
	private String pName;
	private String pDesc;
	private Date updateTime;//最近更新时间
	
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDesc() {
		return pDesc;
	}
	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
