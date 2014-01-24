package com.nearme.statistics.form.app.zjb;

import com.nearme.statistics.form.BaseForm;

/**
 * 商店Form
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-5
 */
public class ZjbForm extends BaseForm {
	private String softName;//软件名
	private String actionname;

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}
}
