package com.nearme.statistics.action.sys.systemTable;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.dto.sys.GCProductDTO;
import com.nearme.statistics.model.sys.systemTable.GCProductEntity;
import com.nearme.statistics.service.sys.SystemTableService;
import com.nearme.statistics.util.NumericUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 游戏中心产品信息维护Action
 *
 * @author 刘超
 * @version 1.0
 * @since 1.0,2012-8-16
 */
public class GCProductInfoAction extends ActionSupport {

	private static final long serialVersionUID = -7233774442095341683L;
	private SystemTableService service;
	private List<GCProductEntity> list;

	public String init() {
		list = service.getGCProductList(null);
		return Action.SUCCESS;
	}

	public String execute() {
		String action = ServletActionContext.getRequest().getParameter(
				"operation");

		GCProductDTO dto = null;
		int result = -1;

		if ("add".equals(action)) {
			String productID = ServletActionContext.getRequest().getParameter(
					"productID");
			String productName = ServletActionContext.getRequest()
					.getParameter("productName");
			String systemID = ServletActionContext.getRequest().getParameter(
					"systemID");

			dto = new GCProductDTO();
			dto.setProductID(productID);
			dto.setProductName(productName);
			dto.setSystemID(NumericUtil.tryParse(systemID, 0));
			result = service.addGCProduct(dto);

		} else if ("update".equals(action)) {
			String productID = ServletActionContext.getRequest().getParameter(
					"selectProductID");
			String productName = ServletActionContext.getRequest()
					.getParameter("productName");
			String systemID = ServletActionContext.getRequest().getParameter(
					"systemID");
			dto = new GCProductDTO();
			dto.setProductID(productID);
			dto.setProductName(productName);
			dto.setSystemID(NumericUtil.tryParse(systemID, 0));
			result = service.updateGCProduct(dto);
		} else if ("delete".equals(action)) {
			String productID = ServletActionContext.getRequest().getParameter(
					"selectProductID");
			dto = new GCProductDTO();
			dto.setProductID(productID);
			result = service.deleteGCProduct(dto);

		}

		if (result != -1) {
			list = service.getGCProductList(null);
			return Action.SUCCESS;
		} else {
			return Action.ERROR;
		}
	}

	public void setService(SystemTableService service) {
		this.service = service;
	}

	public List<GCProductEntity> getList() {
		return list;
	}

	public void setList(List<GCProductEntity> list) {
		this.list = list;
	}
}
