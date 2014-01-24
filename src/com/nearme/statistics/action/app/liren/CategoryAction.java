package com.nearme.statistics.action.app.liren;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.form.app.liren.LirenForm;
import com.nearme.statistics.model.liren.LRCategoryEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类目
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class CategoryAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private LirenForm form;
	private List<LRCategoryEntity> categoryList;
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
		categoryList = service.getCategoryList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, categoryList);

		LogUtil.log(dto, "类目");

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
		categoryList = service.getCategoryList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, categoryList);

		LogUtil.log(dto, "类目");

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

		List<LRCategoryEntity> list = (List<LRCategoryEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("类目_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "类目" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "类目", "日期", "应上新总量", "上新总量", "应上新次数",
					"上新次数", "每浏览商品数", "每喜欢商品数", "访问设备数", "访问次数", "最近一批商品上新时间",
					"最近一批商品数量", "最近一批商品平均被浏览量", "浏览“最新”设备数", "浏览“最新”次数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (LRCategoryEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getCategory()),
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getYsxtotal()),
						String.valueOf(entity.getShangxinTotal()),
						String.valueOf(entity.getYsxcnt()),
						String.valueOf(entity.getShangxinCnt()),
						String.valueOf(entity.getAvgbrowse()),
						String.valueOf(entity.getAvglike()),
						String.valueOf(entity.getVisitImei()),
						String.valueOf(entity.getVisitCnt()),
						String.valueOf(entity.getRecentsxtime()),
						String.valueOf(entity.getRecentcnt()),
						String.valueOf(entity.getAvgrecentbrowse()),
						String.valueOf(entity.getBrowsenewimei()),
						String.valueOf(entity.getBrowsenewcnt()) });
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

	public List<LRCategoryEntity> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<LRCategoryEntity> categoryList) {
		this.categoryList = categoryList;
	}

	public LirenService getService() {
		return service;
	}

	public void setService(LirenService service) {
		this.service = service;
	}
}
