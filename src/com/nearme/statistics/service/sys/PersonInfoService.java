package com.nearme.statistics.service.sys;

import com.nearme.statistics.model.sys.admin.Admin;

public interface PersonInfoService {
	
	/**
	 * 用户更改自己的个人信息
	 * @param admin
	 * @return
	 */
	public int updateUserInfo(Admin admin);
	/**
	 * 用户只更改自身密码
	 * @param admin
	 * @return
	 */
	public int updateUserPasswd(Admin admin);

}
