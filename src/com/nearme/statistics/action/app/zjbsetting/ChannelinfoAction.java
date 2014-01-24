package com.nearme.statistics.action.app.zjbsetting;

import java.util.List;

import com.nearme.statistics.cache.CacheConstant;
import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dto.app.zjb.ZJBChannelinfoDTO;
import com.nearme.statistics.form.app.zjb.ZJBChannelinfoForm;
import com.nearme.statistics.model.zjbsetting.ZJBChannelinfoEntity;
import com.nearme.statistics.service.app.zjb.ZJBChannelinfoService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 渠道管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-25
 */
public class ChannelinfoAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private final String TAG = "装机宝渠道管理";
	private ZJBChannelinfoForm form;// 渠道form
	private List<ZJBChannelinfoEntity> channelinfoList;
	private ZJBChannelinfoService service;

	public String init() {
		form = new ZJBChannelinfoForm();
		ZJBChannelinfoDTO dto = new ZJBChannelinfoDTO();
		channelinfoList = service.getZJBChnnelinfoList(dto);

		LogUtil.log(dto, TAG + "-查询");

		return Action.SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		int channelID = NumericUtil.tryParse(form.getChannelID(), -1);

		if (channelID <= 0) {
			return Action.SUCCESS;
		}

		ZJBChannelinfoDTO dto = new ZJBChannelinfoDTO();
		dto.setChannelID(channelID);
		service.delete(dto);
		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.ZJB_CHANNELINFO);

		LogUtil.log(dto, TAG + "-删除");

		return "setting_useractioncode_detail";
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		int channelID = NumericUtil.tryParse(form.getChannelID(), -1);
		String channelName = form.getChannelName();
		String channelDesc = form.getChannelDesc();

		if (channelID <= 0) {
			return Action.SUCCESS;
		}

		ZJBChannelinfoDTO dto = new ZJBChannelinfoDTO();
		dto.setChannelID(channelID);
		dto.setChannelName(channelName);
		dto.setChannelDesc(channelDesc);
		service.update(dto);

		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.ZJB_CHANNELINFO);
		
		LogUtil.log(dto, TAG + "-更新");

		return Action.SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		String channelName = form.getChannelName();
		String channelDesc = form.getChannelDesc();
		
		ZJBChannelinfoDTO dto = new ZJBChannelinfoDTO();
		dto.setChannelName(channelName);
		dto.setChannelDesc(channelDesc);
		service.add(dto);

		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.ZJB_CHANNELINFO);
		
		LogUtil.log(dto, TAG + "-添加");

		return Action.SUCCESS;
	}

	public ZJBChannelinfoForm getForm() {
		return form;
	}

	public void setForm(ZJBChannelinfoForm form) {
		this.form = form;
	}

	public List<ZJBChannelinfoEntity> getChannelinfoList() {
		return channelinfoList;
	}

	public void setChannelinfoList(List<ZJBChannelinfoEntity> channelinfoList) {
		this.channelinfoList = channelinfoList;
	}

	public ZJBChannelinfoService getService() {
		return service;
	}

	public void setService(ZJBChannelinfoService service) {
		this.service = service;
	}
}
