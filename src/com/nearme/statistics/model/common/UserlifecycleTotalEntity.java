package com.nearme.statistics.model.common;


/**
 * @author 朱峰
 * @function 用户生命周期-整体数据 Entity
 * @version 1.0
 * @since 1.0, 2013-5-10
 */
public class UserlifecycleTotalEntity implements java.io.Serializable {
	private static final long serialVersionUID = -4912365518307315904L;

	private long totalNewImei;// 新增Imei
	private long totalNewUser;// 新增用户数

	public long getTotalNewImei() {
		return totalNewImei;
	}

	public void setTotalNewImei(long totalNewImei) {
		this.totalNewImei = totalNewImei;
	}

	public long getTotalNewUser() {
		return totalNewUser;
	}

	public void setTotalNewUser(long totalNewUser) {
		this.totalNewUser = totalNewUser;
	}
}
