package com.nearme.statistics.dto.app.common;

import com.nearme.statistics.form.app.common.ModelForm;

public class ModelDTO {
	private String model;// 机型
	private String typename;// 分组名称
	private int isoppo;// 是否OPPO手机（0否/1是）
	private int isused;// 是否隐藏（0显示/1隐藏）

	public void fillFromForm(ModelForm form) {
		this.model = form.getModel();
		this.typename = form.getTypename();
		this.isoppo = form.getIsoppo();
		this.isused = form.getIsused();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getIsoppo() {
		return isoppo;
	}

	public void setIsoppo(int isoppo) {
		this.isoppo = isoppo;
	}

	public int getIsused() {
		return isused;
	}

	public void setIsused(int isused) {
		this.isused = isused;
	}
}
