package com.nearme.statistics.action.app.liren;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.form.app.liren.LirenForm;
import com.nearme.statistics.model.liren.LRHomepageEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class HomepageAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private LirenForm form;
	private List<LRHomepageEntity> homepageList;
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

		String startDate = DateUtil.getDateOfXdaysAgo(7);
		String endDate = DateUtil.getToday();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		homepageList = service.getHomepageList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, homepageList);
		
		LogUtil.log(dto, "首页");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		homepageList = service.getHomepageList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, homepageList);
		
		LogUtil.log(dto, "首页");

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

		List<LRHomepageEntity> list = (List<LRHomepageEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("首页_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "首页" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "拉取轮播图设备数",
					"拉取轮播图次数", "热门推荐拉去设备数", "热门推荐拉去次数", "热门推荐浏览设备数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (LRHomepageEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getLbtimei()),
						String.valueOf(entity.getLbtcnt()),
						String.valueOf(entity.getHotrecommendImei()),
						String.valueOf(entity.getHotrecommendCnt()),
						String.valueOf(entity.getHotrecommendBrose()),
						String.valueOf(entity.getHottop50()), 
						String.valueOf(entity.getHottop100()),
						String.valueOf(entity.getRefertime()),
						String.valueOf(entity.getReferyestime()) });
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

	public List<LRHomepageEntity> getHomepageList() {
		return homepageList;
	}

	public void setHomepageList(List<LRHomepageEntity> homepageList) {
		this.homepageList = homepageList;
	}

	public LirenService getService() {
		return service;
	}

	public void setService(LirenService service) {
		this.service = service;
	}
}
