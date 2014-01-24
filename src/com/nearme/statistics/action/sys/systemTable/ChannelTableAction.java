package com.nearme.statistics.action.sys.systemTable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.sys.ChannelDTO;
import com.nearme.statistics.form.sys.ChannelForm;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.systemTable.ChannelEntity;
import com.nearme.statistics.service.sys.SystemTableService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 段锦涛
 * @version 1.0
 * @since 1.0, 2012-8-7
 */
public class ChannelTableAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SystemTableService service;
	private List<ChannelEntity> channelList;
	private ChannelForm form;
	
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if("POST".equalsIgnoreCase(request.getMethod())){
			ChannelDTO dto =new ChannelDTO();
			dto.fillFromForm(form);
			dto.setUpdateBy(((Admin)request.getSession().getAttribute(Constants.SESSION_ADMIN_KEY)).getId());
			if("ADD".equalsIgnoreCase(request.getParameter("action"))){
				service.addChannel(dto);
			}else if("UPDATE".equalsIgnoreCase(request.getParameter("action"))){
				service.updateChannel(dto);
			}else{
				service.deleteChannel(dto);
			}
		}
		channelList=service.getChannelList(null);
		return Action.SUCCESS;
	}

	public void setService(SystemTableService service) {
		this.service = service;
	}

	public List<ChannelEntity> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<ChannelEntity> channelList) {
		this.channelList = channelList;
	}

	public ChannelForm getForm() {
		return form;
	}

	public void setForm(ChannelForm form) {
		this.form = form;
	}
	
}
