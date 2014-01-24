package com.nearme.statistics.action.sys.systemTable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.NetworkTypeDTO;
import com.nearme.statistics.form.sys.NetworkTypeForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.systemTable.NetworkTypeEntity;
import com.nearme.statistics.service.sys.SystemTableService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 网络类型列表
 * @author 80053813
 *
 */
public class NetworkTypeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SystemTableService service;
	private NetworkTypeForm form;
	private List<NetworkTypeEntity> networkTypeList;
	
	public String execute(){
		//权限检查
		HttpServletRequest request = ServletActionContext.getRequest();
		if("POST".equalsIgnoreCase(request.getMethod())){
			NetworkTypeDTO dto = new NetworkTypeDTO();
			dto.fillFromForm(form);
			dto.setUpdateBy(((Admin)request.getSession().getAttribute(Constants.SESSION_ADMIN_KEY)).getId());
			if("ADD".equalsIgnoreCase(request.getParameter("action"))){
				//新增
				service.addNetworkType(dto);
			} else if("UPDATE".equalsIgnoreCase(request.getParameter("action"))) {
				//更改
				service.updateNetworkType(dto);
			} else{
				//删除
				service.deleteNetworkType(dto);
			}
		}
		
		//加载列表
		networkTypeList = service.getNetworkTypeList(null);
		
		return Action.SUCCESS;
	}
	
	public SystemTableService getService() {
		return service;
	}
	public void setService(SystemTableService service) {
		this.service = service;
	}
	public NetworkTypeForm getForm() {
		return form;
	}
	public void setForm(NetworkTypeForm form) {
		this.form = form;
	}
	public List<NetworkTypeEntity> getNetworkTypeList() {
		return networkTypeList;
	}
	public void setNetworkTypeList(List<NetworkTypeEntity> networkTypeList) {
		this.networkTypeList = networkTypeList;
	}

}
