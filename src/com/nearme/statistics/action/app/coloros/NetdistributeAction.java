package com.nearme.statistics.action.app.coloros;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.form.app.coloros.ColorosForm;
import com.nearme.statistics.model.coloros.COSNetdistributeEntity;
import com.nearme.statistics.service.app.coloros.ColorosService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 网络分布
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-27
 */
public class NetdistributeAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "网络分布";
	private ColorosForm form;
	private List<COSNetdistributeEntity> netdistributeList;
	private ColorosService service;

	/**
	 * 初始化
	 *
	 * @return
	 */
	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(1);

		form.setStartTime(startDate);
		int systemID = form.getSystemIDValue();

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(systemID);
		
		netdistributeList = service.getNetdistributeList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, netdistributeList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	/**
	 * 查询
	 *
	 * @return
	 */
	public String query() {
		int systemID = form.getSystemIDValue();
		Date startDate = form.getStartDate();
		String model = form.getModel();
		
		ColorosDTO dto = new ColorosDTO();
		dto.setStartDate(startDate);
		dto.setModel(model);
		dto.setSystemID(systemID);
		
		netdistributeList = service.getNetdistributeList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, netdistributeList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<COSNetdistributeEntity> list = (List<COSNetdistributeEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "网络类型", "用户留存数" , "用户留存率(%)" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (COSNetdistributeEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getNettype()),
						String.valueOf(entity.getUsercnt()),
						String.valueOf(entity.getUserratio())});
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public ColorosForm getForm() {
		return form;
	}

	public void setForm(ColorosForm form) {
		this.form = form;
	}

	public List<COSNetdistributeEntity> getNetdistributeList() {
		return netdistributeList;
	}

	public void setNetdistributeList(List<COSNetdistributeEntity> netdistributeList) {
		this.netdistributeList = netdistributeList;
	}

	public ColorosService getService() {
		return service;
	}

	public void setService(ColorosService service) {
		this.service = service;
	}
}
