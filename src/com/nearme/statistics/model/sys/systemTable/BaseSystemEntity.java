package com.nearme.statistics.model.sys.systemTable;

import java.util.Date;

/**
 * 
 * @author 80053813
 *
 */
public class BaseSystemEntity {

	private int seqID;//自增序列
	private Date updateDate;//最后更新时间
	private int updateBy;//最后更新管理员ID
	//扩展属性:最后执行更改的管理员名称
	private String updateAdminName;

	public int getSeqID() {
		return seqID;
	}

	public void setSeqID(int seqID) {
		this.seqID = seqID;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateAdminName() {
		return updateAdminName;
	}

	public void setUpdateAdminName(String updateAdminName) {
		this.updateAdminName = updateAdminName;
	}
}
