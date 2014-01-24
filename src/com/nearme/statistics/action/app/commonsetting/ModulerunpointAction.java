package com.nearme.statistics.action.app.commonsetting;

import java.util.List;

import com.nearme.statistics.dto.app.common.ModulerunpointDTO;
import com.nearme.statistics.form.app.common.ModulerunpointForm;
import com.nearme.statistics.model.commonsetting.ModulerunpointEntity;
import com.nearme.statistics.service.app.common.ModulerunpointService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 模块运营点管理
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-19
 */
public class ModulerunpointAction extends ActionSupport {
	private static final long serialVersionUID = 8118258883806860108L;

	private final String TAG = "模块运营点管理";
	private ModulerunpointForm form;// 模块运营点form
	private List<ModulerunpointEntity> modulerunpointList;
	private ModulerunpointService service;

	public String init() {
		// 检查action传递过来的systemID合法性r
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		ModulerunpointDTO dto = new ModulerunpointDTO();
		dto.setSystemID("" + systemID);
		modulerunpointList = service.getModulerunpointList(dto);

		LogUtil.log(dto, TAG + "-查询");

		return Action.SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		String id = form.getId();
		String systemID = form.getSystemID();
		
		if (null == id) {
			return Action.SUCCESS;
		}

		ModulerunpointDTO dto = new ModulerunpointDTO();
		dto.setId(id);
		dto.setSystemID(systemID);
		service.delete(dto);
		service.sync2Hive(dto);

		LogUtil.log(dto, TAG + "-删除");

		return "setting_useractioncode_detail";
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		String id = form.getId();
		String packagename = form.getPackagename();
		String groupname = form.getGroupname();
		String sourcecode = form.getSourcecode();
		String sourcedesc = form.getSourcedesc();
		String categoryid = form.getCategoryid();
		String clickindex = form.getClickindex();
		String tag = form.getTag();
		String systemID = form.getSystemID();
		
		ModulerunpointDTO dto = new ModulerunpointDTO();
		dto.setId(id);
		dto.setPackagename(packagename);
		dto.setGroupname(groupname);
		dto.setSourcecode(sourcecode);
		dto.setSourcedesc(sourcedesc);
		dto.setCategoryid(categoryid);
		dto.setClickindex(clickindex);
		dto.setTag(tag);
		dto.setSystemID(systemID);
		
		service.update(dto);
		service.sync2Hive(dto);

		LogUtil.log(dto, TAG + "-更新");

		return Action.SUCCESS;
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	public String add() {
		String systemID = form.getSystemID();
		String packagename = form.getPackagename();
		String groupname = form.getGroupname();
		String sourcecodes = form.getSourcecode();
		String sourcedesc = form.getSourcedesc();
		String categoryid = form.getCategoryid();
		String clickindex = form.getClickindex();
		String tag = form.getTag();
		
		ModulerunpointDTO dto = new ModulerunpointDTO();
		dto.setSystemID(systemID);
		dto.setPackagename(packagename);
		dto.setGroupname(groupname);
		dto.setSourcedesc(sourcedesc);
		dto.setCategoryid(categoryid);
		dto.setClickindex(clickindex);
		dto.setTag(tag);
		
		//支持多sourcecode插入
		String[] scodes = sourcecodes.split(",");
		for (String sourcecode : scodes) {
			if(null != sourcecode && !"".equals(sourcecode)){
				dto.setSourcecode(sourcecode.trim());
				service.add(dto);
			}
		}
		service.sync2Hive(dto);

		LogUtil.log(dto, TAG + "-添加");

		return Action.SUCCESS;
	}
	
	public ModulerunpointForm getForm() {
		return form;
	}

	public void setForm(ModulerunpointForm form) {
		this.form = form;
	}

	public List<ModulerunpointEntity> getModulerunpointList() {
		return modulerunpointList;
	}

	public void setModulerunpointList(List<ModulerunpointEntity> modulerunpointList) {
		this.modulerunpointList = modulerunpointList;
	}

	public ModulerunpointService getService() {
		return service;
	}

	public void setService(ModulerunpointService service) {
		this.service = service;
	}
}
