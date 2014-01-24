package com.nearme.statistics.action.app.zjbsetting;

import java.util.List;

import com.nearme.statistics.cache.CacheConstant;
import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dto.app.zjb.ZJBUseractioncodeDTO;
import com.nearme.statistics.form.app.zjb.ZJBUseractioncodeForm;
import com.nearme.statistics.model.zjbsetting.ZJBUseractioncodeEntity;
import com.nearme.statistics.service.app.zjb.ZJBUseractioncodeService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 装机宝用户行为编码管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-28
 */
public class UseractioncodeAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private final String TAG = "装机宝用户行为编码管理";
	private ZJBUseractioncodeForm form;// 渠道form
	private List<ZJBUseractioncodeEntity> useractioncodeList;
	private ZJBUseractioncodeService service;

	public String init() {
		form = new ZJBUseractioncodeForm();
		ZJBUseractioncodeDTO dto = new ZJBUseractioncodeDTO();
		useractioncodeList = service.getZJBUseractioncodeList(dto);

		LogUtil.log(dto, TAG + "-查询");

		return Action.SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		int actionID = NumericUtil.tryParse(form.getActionID(), -1);

		if (actionID <= 0) {
			return Action.SUCCESS;
		}

		ZJBUseractioncodeDTO dto = new ZJBUseractioncodeDTO();
		dto.setActionID(actionID);
		service.delete(dto);
		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.ZJB_USERACTIONCODE);

		LogUtil.log(dto, TAG + "-删除");

		return "setting_useractioncode_detail";
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		int actionID = NumericUtil.tryParse(form.getActionID(), -1);
		String actioncode = form.getActioncode();
		String actionname = form.getActionname();

		if (actionID <= 0) {
			return Action.SUCCESS;
		}

		ZJBUseractioncodeDTO dto = new ZJBUseractioncodeDTO();
		dto.setActionID(actionID);
		dto.setActioncode(actioncode);
		dto.setActionname(actionname);
		service.update(dto);

		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.ZJB_USERACTIONCODE);
		
		LogUtil.log(dto, TAG + "-更新");

		return Action.SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		String actioncode = form.getActioncode();
		String actionname = form.getActionname();
		
		ZJBUseractioncodeDTO dto = new ZJBUseractioncodeDTO();
		dto.setActioncode(actioncode);
		dto.setActionname(actionname);
		service.add(dto);

		//对渠道做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.ZJB_USERACTIONCODE);
		
		LogUtil.log(dto, TAG + "-添加");

		return Action.SUCCESS;
	}

	public ZJBUseractioncodeForm getForm() {
		return form;
	}

	public void setForm(ZJBUseractioncodeForm form) {
		this.form = form;
	}

	public List<ZJBUseractioncodeEntity> getUseractioncodeList() {
		return useractioncodeList;
	}

	public void setUseractioncodeList(
			List<ZJBUseractioncodeEntity> useractioncodeList) {
		this.useractioncodeList = useractioncodeList;
	}

	public ZJBUseractioncodeService getService() {
		return service;
	}

	public void setService(ZJBUseractioncodeService service) {
		this.service = service;
	}
}
