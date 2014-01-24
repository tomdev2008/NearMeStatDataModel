package com.nearme.statistics.model.common;

public class HiveFSTopresourceEntity implements java.io.Serializable {
	private static final long serialVersionUID = -8812086311436452733L;

	private int statdate;
	private int position;
	private long downnum;

	public int getStatdate() {
		return statdate;
	}

	public void setStatdate(int statdate) {
		this.statdate = statdate;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public long getDownnum() {
		return downnum;
	}

	public void setDownnum(long downnum) {
		this.downnum = downnum;
	}
}
