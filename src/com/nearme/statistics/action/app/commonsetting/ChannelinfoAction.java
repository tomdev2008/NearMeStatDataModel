package com.nearme.statistics.action.app.commonsetting;

import java.util.List;

import com.nearme.statistics.cache.CacheConstant;
import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dto.app.common.ChannelinfoDTO;
import com.nearme.statistics.form.app.common.ChannelinfoForm;
import com.nearme.statistics.model.commonsetting.ChannelinfoEntity;
import com.nearme.statistics.service.app.common.ChannelinfoService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 渠道管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-11
 */
public class ChannelinfoAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private ChannelinfoForm form;// 渠道form
	private List<ChannelinfoEntity> channelinfoList;
	private ChannelinfoService service;

	public String init() {
		// 检查action传递过来的systemID合法性r
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		ChannelinfoDTO dto = new ChannelinfoDTO();
		dto.setSystemID(systemID);
		channelinfoList = service.getChnnelinfoList(dto);

		LogUtil.log(dto, "渠道管理-查询");

		return Action.SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		int channelID = NumericUtil.tryParse(form.getChannelID(), -1);

		if (systemID <= 0 || channelID <= 0) {
			return Action.SUCCESS;
		}

		ChannelinfoDTO dto = new ChannelinfoDTO();
		dto.setSystemID(systemID);
		dto.setChannelID(channelID);
		service.delete(dto);
		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.COMMON_CHANNELINFO_ + systemID);

		LogUtil.log(dto, "渠道管理-删除");

		return "setting_useractioncode_detail";
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		int channelID = NumericUtil.tryParse(form.getChannelID(), -1);
		String channelName = form.getChannelName();
		String channelDesc = form.getChannelDesc();

		if (systemID <= 0 || channelID <= 0) {
			return Action.SUCCESS;
		}

		ChannelinfoDTO dto = new ChannelinfoDTO();
		dto.setSystemID(systemID);
		dto.setChannelID(channelID);
		dto.setChannelName(channelName);
		dto.setChannelDesc(channelDesc);
		service.update(dto);

		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.COMMON_CHANNELINFO_ + systemID);
		
		LogUtil.log(dto, "渠道管理-更新");

		return Action.SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		int channelID = NumericUtil.tryParse(form.getChannelID(), -1);
		String channelName = form.getChannelName();
		String channelDesc = form.getChannelDesc();
		
		if (systemID <= 0 || channelID <= 0) {
			return Action.SUCCESS;
		}

		ChannelinfoDTO dto = new ChannelinfoDTO();
		dto.setSystemID(systemID);
		dto.setChannelID(channelID);
		dto.setChannelName(channelName);
		dto.setChannelDesc(channelDesc);
		service.add(dto);

		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.COMMON_CHANNELINFO_ + systemID);
		
		LogUtil.log(dto, "渠道管理-添加");

		return Action.SUCCESS;
	}

	public ChannelinfoForm getForm() {
		return form;
	}

	public void setForm(ChannelinfoForm form) {
		this.form = form;
	}

	public List<ChannelinfoEntity> getChannelinfoList() {
		return channelinfoList;
	}

	public void setChannelinfoList(List<ChannelinfoEntity> channelinfoList) {
		this.channelinfoList = channelinfoList;
	}

	public ChannelinfoService getService() {
		return service;
	}

	public void setService(ChannelinfoService service) {
		this.service = service;
	}
}
