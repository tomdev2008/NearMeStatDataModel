package com.nearme.statistics.action.app.common;

import java.util.List;

import com.nearme.statistics.dto.app.common.ProductinfoDTO;
import com.nearme.statistics.form.app.common.ProductinfoForm;
import com.nearme.statistics.model.common.ProductinfoEntity;
import com.nearme.statistics.service.hiveapp.common.CommonService;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 产品名
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-15
 */
public class ProductAction extends ActionSupport {
	private static final long serialVersionUID = -4656688923848692953L;

	private final String TAG = "产品名";
	private ProductinfoForm form;
	private List<ProductinfoEntity> productinfoList;
	private CommonService service;// 含hive和oracle连接

	/**
	 * 查询游戏名
	 * 
	 * @return
	 */
	public String queryproduct() {
		String productname = form.getProductname();
		if (productname == null || productname.equals("")) {
			return Action.SUCCESS;
		}
		
		String systemID = form.getSystemID();
		if(systemID == null || systemID.equals("")){
			return Action.SUCCESS;
		}

		ProductinfoDTO dto = new ProductinfoDTO();
		dto.setProductname(productname);
		dto.setSystemID(Integer.parseInt(systemID));

		productinfoList = service.getProductinfoList(dto);
		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public ProductinfoForm getForm() {
		return form;
	}

	public void setForm(ProductinfoForm form) {
		this.form = form;
	}

	public List<ProductinfoEntity> getProductinfoList() {
		return productinfoList;
	}

	public void setProductinfoList(List<ProductinfoEntity> productinfoList) {
		this.productinfoList = productinfoList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
