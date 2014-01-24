package com.nearme.statistics.dao.app.zjb;

import java.util.List;

import com.nearme.statistics.dto.app.zjb.ZJBUseractioncodeDTO;
import com.nearme.statistics.model.zjbsetting.ZJBUseractioncodeEntity;

/**
 * 用户行为编码
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-28
 */
public interface ZjbUseractioncodeDao {
	//查询
	public List<ZJBUseractioncodeEntity> getZJBUseractioncodeList(ZJBUseractioncodeDTO dto);
	
	//删除
	public void delete(ZJBUseractioncodeDTO dto);
	
	//添加
	public void add(ZJBUseractioncodeDTO dto);
	
	//修改
	public void update(ZJBUseractioncodeDTO dto);
}
