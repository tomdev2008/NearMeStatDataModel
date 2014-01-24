package com.nearme.statistics.action.app.commonsetting;

import java.util.List;

import com.nearme.statistics.cache.CacheConstant;
import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dto.app.common.ModelDTO;
import com.nearme.statistics.form.app.common.ModelForm;
import com.nearme.statistics.model.commonsetting.ModelEntity;
import com.nearme.statistics.service.app.common.ModelService;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 机型管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-6
 */
public class ModelAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private String TAG = "机型管理";
	private ModelForm form;
	private List<ModelEntity> modelmanageList;
	private ModelService service;

	public String init() {
		ModelDTO dto = new ModelDTO();
		modelmanageList = service.getModelmanageList(dto);

		LogUtil.log(dto, TAG + "-查询");

		return Action.SUCCESS;
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		ModelDTO dto = new ModelDTO();
		dto.fillFromForm(form);
		service.update(dto);

		// 对机型做了操作，清理一下缓存
		SystemTableCache.delete(CacheConstant.SYS_SYSTEMTABLE_MODELDATA);

		LogUtil.log(dto, TAG + "-更新");

		return Action.SUCCESS;
	}

	public ModelForm getForm() {
		return form;
	}

	public void setForm(ModelForm form) {
		this.form = form;
	}

	public List<ModelEntity> getModelmanageList() {
		return modelmanageList;
	}

	public void setModelmanageList(List<ModelEntity> modelmanageList) {
		this.modelmanageList = modelmanageList;
	}

	public ModelService getService() {
		return service;
	}

	public void setService(ModelService service) {
		this.service = service;
	}
}
