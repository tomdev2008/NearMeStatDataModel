package com.nearme.statistics.action.app.cloud;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.cloud.CloudDTO;
import com.nearme.statistics.form.app.cloud.CloudForm;
import com.nearme.statistics.model.cloud.CloudFlowdistributeEntity;
import com.nearme.statistics.service.app.cloud.CloudService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 流量分配
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-13
 */
public class FlowdistributeAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "流量分配";
	private CloudForm form;
	private List<CloudFlowdistributeEntity> flowdistributeList;
	private CloudService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());

		CloudDTO dto = new CloudDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		flowdistributeList = service.getFlowdistributeList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, flowdistributeList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		CloudDTO dto = new CloudDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		flowdistributeList = service.getFlowdistributeList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, flowdistributeList);

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

		List<CloudFlowdistributeEntity> list = (List<CloudFlowdistributeEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu
					.writeLine(new String[] { "日期", "主界面", "联系人备份", "短信备份",
							"云相册", "网盘", "云笔记", "找回手机", "一键备份", "一键恢复", "个人资料",
							"云存储空间", "帮助" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				CloudFlowdistributeEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getMainpage()),
						String.valueOf(entity.getContectbak()),
						String.valueOf(entity.getSmsbak()),
						String.valueOf(entity.getCloadphoto()),
						String.valueOf(entity.getNetdisk()),
						String.valueOf(entity.getCloudnote()),
						String.valueOf(entity.getFindphone()),
						String.valueOf(entity.getKeybak()),
						String.valueOf(entity.getKeyreturn()),
						String.valueOf(entity.getPersonal()),
						String.valueOf(entity.getCloudspace()),
						String.valueOf(entity.getHelp()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public CloudForm getForm() {
		return form;
	}

	public void setForm(CloudForm form) {
		this.form = form;
	}

	public List<CloudFlowdistributeEntity> getFlowdistributeList() {
		return flowdistributeList;
	}

	public void setFlowdistributeList(
			List<CloudFlowdistributeEntity> flowdistributeList) {
		this.flowdistributeList = flowdistributeList;
	}

	public CloudService getService() {
		return service;
	}

	public void setService(CloudService service) {
		this.service = service;
	}
}
