package com.nearme.statistics.service.app.zjb;

import java.util.List;

import com.nearme.statistics.dto.app.zjb.ZJBChannelinfoDTO;
import com.nearme.statistics.model.zjbsetting.ZJBChannelinfoEntity;

/**
 * 渠道
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-24
 */
public interface ZJBChannelinfoService {
	//渠道查询
	public List<ZJBChannelinfoEntity> getZJBChnnelinfoList(ZJBChannelinfoDTO dto);
	
	//渠道删除
	public void delete(ZJBChannelinfoDTO dto);
	
	//渠道添加
	public void add(ZJBChannelinfoDTO dto);
	
	//渠道修改
	public void update(ZJBChannelinfoDTO dto);
}
