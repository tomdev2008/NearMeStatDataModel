package com.nearme.statistics.action.app.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.DownleijiEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 下载累计
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-25
 */
public class DownleijiAction extends ActionSupport {
	private static final long serialVersionUID = -6607727377925511975L;

	private final String TAG = "下载累计";
	private BaseForm form;
	private List<DownleijiEntity> downleijiList;
	private CommonService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setSystemID(form.getSystemIDValue());
		downleijiList = service.getDownleijiList(dto);

		LogUtil.log(dto, TAG);//log记录查询

		return Action.SUCCESS;
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public List<DownleijiEntity> getDownleijiList() {
		return downleijiList;
	}

	public void setDownleijiList(List<DownleijiEntity> downleijiList) {
		this.downleijiList = downleijiList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
