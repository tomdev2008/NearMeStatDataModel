package com.nearme.statistics.form.app.store;

import com.nearme.statistics.form.BaseForm;

/**
 * 商店Form
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class StoreForm extends BaseForm {
	private String usertype;//用户类型
	private int statDateInt;
	private int startDateInt;
	private int endDateInt;
	private String packageName;

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public int getStatDateInt() {
		return statDateInt;
	}

	public void setStatDateInt(int statDateInt) {
		this.statDateInt = statDateInt;
	}

	public int getStartDateInt() {
		return startDateInt;
	}

	public void setStartDateInt(int startDateInt) {
		this.startDateInt = startDateInt;
	}

	public int getEndDateInt() {
		return endDateInt;
	}

	public void setEndDateInt(int endDateInt) {
		this.endDateInt = endDateInt;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
