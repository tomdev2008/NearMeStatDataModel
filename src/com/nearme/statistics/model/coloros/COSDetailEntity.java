package com.nearme.statistics.model.coloros;

import java.io.Serializable;
import java.util.Date;

/**
 * 明细数据
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-16
 */
public class COSDetailEntity implements Serializable {
	private static final long serialVersionUID = 5526693550533044134L;

	// 时间
	private Date statDate;
	private Date statEndDate;

	private long startuser;// 启动用户
	private long newuser;// 新增用户
	private long leijiuser;// 累计用户

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public Date getStatEndDate() {
		return statEndDate;
	}

	public void setStatEndDate(Date statEndDate) {
		this.statEndDate = statEndDate;
	}

	public long getStartuser() {
		return startuser;
	}

	public void setStartuser(long startuser) {
		this.startuser = startuser;
	}

	public long getNewuser() {
		return newuser;
	}

	public void setNewuser(long newuser) {
		this.newuser = newuser;
	}

	public long getLeijiuser() {
		return leijiuser;
	}

	public void setLeijiuser(long leijiuser) {
		this.leijiuser = leijiuser;
	}
}
