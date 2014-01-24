package com.nearme.statistics.model.commonsetting;

/**
 * 机型Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-20
 */
public class ModelEntity implements java.io.Serializable {
	private static final long serialVersionUID = 7006779353267613074L;

	private String model;// 机型
	private String typename;// 分组名称
	private int isoppo;// 是否OPPO手机（0否/1是）
	private int isused;// 是否隐藏（0显示/1隐藏）

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
