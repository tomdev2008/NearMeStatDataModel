package com.nearme.statistics.dao.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.ChannelinfoDTO;
import com.nearme.statistics.model.commonsetting.ChannelinfoEntity;

/**
 * 渠道管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-11
 */
public interface ChannelinfoDao {
	// 渠道查询
	public List<ChannelinfoEntity> getChnnelinfoList(ChannelinfoDTO dto);

	// 渠道删除
	public void delete(ChannelinfoDTO dto);

	// 渠道添加
	public void add(ChannelinfoDTO dto);

	// 渠道修改
	public void update(ChannelinfoDTO dto);
}
