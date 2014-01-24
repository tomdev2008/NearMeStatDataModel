package com.nearme.statistics.model;

import java.util.List;

/**
 * 基础 Entity
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-18
 */
public class BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 2516604953313753079L;

	private String tblName;//报表名称
	private ColumnEntity columns;// 列名称
	private List<ColumnValueEntity> valueList;// 列值list

	public ColumnEntity getColumns() {
		return columns;
	}

	public void setColumns(ColumnEntity columns) {
		this.columns = columns;
	}

	public List<ColumnValueEntity> getValueList() {
		return valueList;
	}

	public void setValueList(List<ColumnValueEntity> valueList) {
		this.valueList = valueList;
	}

	public String getTblName() {
		return tblName;
	}

	public void setTblName(String tblName) {
		this.tblName = tblName;
	}
}
