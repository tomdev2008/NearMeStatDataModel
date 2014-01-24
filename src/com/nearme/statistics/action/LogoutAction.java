package com.nearme.statistics.action;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute(){
		ServletActionContext.getRequest().getSession().removeAttribute(Constants.SESSION_ADMIN_KEY);
		try {
			ServletActionContext.getResponse().sendRedirect("index.jsp");
		}catch(Exception exp){
			
		}
		return Action.SUCCESS;
	}

}
