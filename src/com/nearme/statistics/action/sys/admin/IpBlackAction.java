package com.nearme.statistics.action.sys.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.model.sys.admin.IpFilter;
import com.nearme.statistics.service.sys.AdminService;
import com.nearme.statistics.util.IpUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class IpBlackAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdminService service;
	private String ipStart;
	private String ipEnd;
	private String ipRegex;
	private String deleteFilter;
	private List<IpFilter> ipList;
	
	public String execute(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String resultMsg = "";
		if("post".equalsIgnoreCase(request.getMethod())){
			//ip段匹配优先
			if(!StringUtil.isNullOrEmpty(ipStart)){
				if(!IpUtil.checkIP(ipStart)){
					resultMsg += "IP段起始IP格式错误";
				}
				if(!IpUtil.checkIP(ipEnd)){
					resultMsg += "IP段结束IP格式错误";
				}
				if("".equals(resultMsg)){
					IpFilter filter = new IpFilter();
					filter.setStrIpStart(ipStart);
					filter.setStrIpEnd(ipEnd);
					filter.setNumIpStart(IpUtil.getIPNumber(ipStart));
					filter.setNumIpEnd(IpUtil.getIPNumber(ipEnd));
					
					int result = service.addIPBlackFilter(filter);
					if(result > 0){
						resultMsg += "添加成功";
						//CachedItems.removeCache("IP_BLACK_LIST");
					}else{
						resultMsg += "添加失败";
					}
				}
			}
			//IP模式匹配
			else{
				if(!IpUtil.checkIPFilter(ipRegex)){
					resultMsg += "匹配模式格式错误";
				}else{
					IpFilter filter = new IpFilter();
					filter.setStrIpRegex(ipRegex);
					
					int result = service.addIPBlackFilter(filter);
					if(result > 0){
						resultMsg += "添加成功";
						//CachedItems.removeCache("IP_BLACK_LIST");
					}else{
						resultMsg += "添加失败";
					}
				}
			}
		}
		if(deleteFilter != null){
			int id = NumericUtil.tryParse(request.getParameter("serial"), -1);
			if(id > 0){
				int result = service.deleteBlackFilter(id);
				if(result > 0){
					resultMsg += "删除成功";
					//CachedItems.removeCache("IP_BLACK_LIST");
				}else{
					resultMsg += "删除失败";
				}
			}else{
				resultMsg += "参数有误";
			}
		}
	
		ipList=service.getBlackFilterList();
		return Action.SUCCESS;
	}

	public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getIpEnd() {
		return ipEnd;
	}

	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}

	public String getIpRegex() {
		return ipRegex;
	}

	public void setIpRegex(String ipRegex) {
		this.ipRegex = ipRegex;
	}

	public String getDeleteFilter() {
		return deleteFilter;
	}

	public void setDeleteFilter(String deleteFilter) {
		this.deleteFilter = deleteFilter;
	}

	public List<IpFilter> getIpList() {
		return ipList;
	}

	public void setIpList(List<IpFilter> ipList) {
		this.ipList = ipList;
	}
	
	
	
}
