package com.nearme.statistics.action.sys.systemTable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.CategoryDTO;
import com.nearme.statistics.form.sys.CategoryForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.systemTable.CategoryEntity;
import com.nearme.statistics.service.sys.SystemTableService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryInfoAction extends ActionSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CategoryEntity> categoryList;
	private CategoryForm form;
	private SystemTableService service;
	
	public void setService(SystemTableService service) {
		this.service = service;
	}

	public String execute(){
		//权限检查
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if("POST".equalsIgnoreCase(request.getMethod())){
			CategoryDTO dto = new CategoryDTO();
			dto.fillFromForm(form);
			dto.setUpdateBy(((Admin)request.getSession().getAttribute(Constants.SESSION_ADMIN_KEY)).getId());
			if("ADD".equalsIgnoreCase(request.getParameter("action"))){
				//新增机型
				service.addCategory(dto);
			} else if("UPDATE".equalsIgnoreCase(request.getParameter("action"))) {
				//更改机型信息
				service.updateCategory(dto);
			} else{
				//删除机型
				service.deleteCategory(dto);
			}
		}
		
		//加载机型列表
		categoryList = service.getCategoryList(null);
		return Action.SUCCESS;
	}

	public List<CategoryEntity> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryEntity> categoryList) {
		this.categoryList = categoryList;
	}

	public CategoryForm getForm() {
		return form;
	}

	public void setForm(CategoryForm form) {
		this.form = form;
	}

	
}
