package com.nearme.statistics.model.commonsetting;

/**
 * 指标管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-20
 */
public class MeasureEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5652634774199196661L;

	private String name;// 指标名称
	private String code;// Code
	private String cycle;// 计算周期
	private String desc;// 规则描述
	private String sql;// sql
	private String type;// 类型

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
