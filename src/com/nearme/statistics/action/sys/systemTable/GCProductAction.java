package com.nearme.statistics.action.sys.systemTable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.GCProductDTO;
import com.nearme.statistics.form.sys.GCProductForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.systemTable.GCProductEntity;
import com.nearme.statistics.service.sys.SystemTableService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class GCProductAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private SystemTableService service;
	private GCProductForm form;
	private List<GCProductEntity> gcProductList;
	
	public String execute(){
		//权限检查
		HttpServletRequest request = ServletActionContext.getRequest();
		if("POST".equalsIgnoreCase(request.getMethod())){
			GCProductDTO dto = new GCProductDTO();
			dto.fillFromForm(form);
			dto.setUpdateBy(((Admin)request.getSession().getAttribute(Constants.SESSION_ADMIN_KEY)).getId());
			if("ADD".equalsIgnoreCase(request.getParameter("action"))){
				//新增
				service.addGCProduct(dto);
			} else if("UPDATE".equalsIgnoreCase(request.getParameter("action"))) {
				//更改
				service.updateGCProduct(dto);
			} else{
				//删除
				service.deleteGCProduct(dto);
			}
		}
		
		//加载列表
		gcProductList = service.getGCProductList(null);
		
		return Action.SUCCESS;
	}
	
	public void setService(SystemTableService service) {
		this.service = service;
	}
	public SystemTableService getService() {
		return service;
	}
	public void setForm(GCProductForm form) {
		this.form = form;
	}
	public GCProductForm getForm() {
		return form;
	}
	public void setGcProductList(List<GCProductEntity> gcProductList) {
		this.gcProductList = gcProductList;
	}
	public List<GCProductEntity> getGcProductList() {
		return gcProductList;
	}
}
