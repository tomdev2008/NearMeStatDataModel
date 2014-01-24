package com.nearme.statistics.model.common;

/**
 * hive sql实体对象
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-16
 */
public class HivesqlEntity implements java.io.Serializable {
	private static final long serialVersionUID = 7203753453765869016L;

	private int index;// sql index
	private String sql; // sql 语句

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
