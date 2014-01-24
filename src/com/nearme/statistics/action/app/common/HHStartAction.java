package com.nearme.statistics.action.app.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.HHStartEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 时段分析
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-16
 */
public class HHStartAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BaseForm form;
	private List<HHStartEntity> hhStartList;
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
		hhStartList = service.listHHStart(dto);
		
		LogUtil.log(dto, "时段分析");//log记录查询

		return Action.SUCCESS;
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public List<HHStartEntity> getHhStartList() {
		return hhStartList;
	}

	public void setHhStartList(List<HHStartEntity> hhStartList) {
		this.hhStartList = hhStartList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
