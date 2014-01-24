package com.nearme.statistics.action.app.liren;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.form.app.liren.LirenForm;
import com.nearme.statistics.model.liren.LRTaghotEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 标签热度
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class TaghotAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private LirenForm form;
	private List<LRTaghotEntity> taghotList;
	private LirenService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(1);
		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		taghotList = service.getTaghotList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, taghotList);

		LogUtil.log(dto, "标签热度");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		taghotList = service.getTaghotList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, taghotList);

		LogUtil.log(dto, "标签热度");

		return Action.SUCCESS;
	}

	/**
	 * 导出报表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<LRTaghotEntity> list = (List<LRTaghotEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("标签热度_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "标签热度" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "标签", "当日被喜欢的数量", "被喜欢的总数量",
					"占当天全部喜欢量的比例" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				LRTaghotEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getTag()),
						String.valueOf(entity.getLikecnt()),
						String.valueOf(entity.getTotallikecnt()),
						String.valueOf(entity.getZanbi()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public LirenForm getForm() {
		return form;
	}

	public void setForm(LirenForm form) {
		this.form = form;
	}

	public List<LRTaghotEntity> getTaghotList() {
		return taghotList;
	}

	public void setTaghotList(List<LRTaghotEntity> taghotList) {
		this.taghotList = taghotList;
	}

	public LirenService getService() {
		return service;
	}

	public void setService(LirenService service) {
		this.service = service;
	}
}
