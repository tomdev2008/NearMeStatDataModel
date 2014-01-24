package com.nearme.statistics.model.common;

import java.io.Serializable;

/**
 * 错误分析<br>
 * 错误信息<br>
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-21
 */
public class ErrorDetailEntity implements Serializable {
	private static final long serialVersionUID = 5770043452302809944L;

	private String genneral;// 错误摘要
	private String detail;// 错误详情
	private String version;// 版本
	private long errorcnt;// 错误次数
	private String model;// 机型

	public String getGenneral() {
		return genneral;
	}

	public void setGenneral(String genneral) {
		this.genneral = genneral;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getErrorcnt() {
		return errorcnt;
	}

	public void setErrorcnt(long errorcnt) {
		this.errorcnt = errorcnt;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
