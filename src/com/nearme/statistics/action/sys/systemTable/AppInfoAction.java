package com.nearme.statistics.action.sys.systemTable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.AppInfoDTO;
import com.nearme.statistics.form.sys.AppInfoForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.systemTable.AppInfoEntity;
import com.nearme.statistics.service.sys.SystemTableService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author 80053813
 *
 */
public class AppInfoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private SystemTableService service;
	private AppInfoForm form;
	private List<AppInfoEntity> appInfoList;
	
	public String execute(){
		//权限检查
		HttpServletRequest request = ServletActionContext.getRequest();
		if("POST".equalsIgnoreCase(request.getMethod())){
			AppInfoDTO dto = new AppInfoDTO();
			dto.fillFromForm(form);
			dto.setUpdateBy(((Admin)request.getSession().getAttribute(Constants.SESSION_ADMIN_KEY)).getId());
			if("ADD".equalsIgnoreCase(request.getParameter("action"))){
				//新增
				service.addAppInfo(dto);
			} else if("UPDATE".equalsIgnoreCase(request.getParameter("action"))) {
				//更改
				service.updateAppInfo(dto);
			} else{
				//删除
				service.deleteAppInfo(dto);
			}
		}
		
		//加载列表
		appInfoList = service.getAppInfoList(null);
		
		return Action.SUCCESS;
	}
	
	
	public void setService(SystemTableService service) {
		this.service = service;
	}
	public SystemTableService getService() {
		return service;
	}
	public void setForm(AppInfoForm form) {
		this.form = form;
	}
	public AppInfoForm getForm() {
		return form;
	}
	public void setAppInfoList(List<AppInfoEntity> appInfoList) {
		this.appInfoList = appInfoList;
	}
	public List<AppInfoEntity> getAppInfoList() {
		return appInfoList;
	}
}
