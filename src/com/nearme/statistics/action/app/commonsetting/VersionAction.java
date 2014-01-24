package com.nearme.statistics.action.app.commonsetting;

import java.util.List;

import com.nearme.statistics.cache.CacheConstant;
import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dto.app.common.VersionDTO;
import com.nearme.statistics.form.app.common.VersionForm;
import com.nearme.statistics.model.commonsetting.VersionEntity;
import com.nearme.statistics.service.app.common.VersionService;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 版本管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-6
 */
public class VersionAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private String TAG = "版本管理";
	private VersionForm form;// 渠道form
	private List<VersionEntity> versionList;
	private VersionService service;

	public String init() {
		// 检查action传递过来的systemID合法性r
		if (form == null) {
			return Action.ERROR;
		}
		int systemID = form.getSystemID();
		if (systemID < 0) {
			return Action.ERROR;
		}

		VersionDTO dto = new VersionDTO();
		dto.setSystemID(systemID);
		versionList = service.getVersionList(dto);

		LogUtil.log(dto, TAG + "-查询");

		return Action.SUCCESS;
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		VersionDTO dto = new VersionDTO();
		dto.fillFromForm(form);
		service.update(dto);

		// 对渠道做了操作，清理一下缓存
		int systemID = form.getSystemID();
		SystemTableCache.delete(CacheConstant.SYS_SYSTEMTABLE_APPVERINFO_
				+ systemID);

		LogUtil.log(dto, TAG + "-更新");

		return Action.SUCCESS;
	}

	public VersionForm getForm() {
		return form;
	}

	public void setForm(VersionForm form) {
		this.form = form;
	}

	public List<VersionEntity> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<VersionEntity> versionList) {
		this.versionList = versionList;
	}

	public VersionService getService() {
		return service;
	}

	public void setService(VersionService service) {
		this.service = service;
	}
}
