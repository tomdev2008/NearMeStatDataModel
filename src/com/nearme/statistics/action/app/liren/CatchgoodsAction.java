package com.nearme.statistics.action.app.liren;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.form.app.liren.LirenForm;
import com.nearme.statistics.model.liren.LRCatchgoodsEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 抓取商品
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-11-19
 */
public class CatchgoodsAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "抓取商品";
	private LirenForm form;
	private List<LRCatchgoodsEntity> catchgoodsList;
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
		String lidu = Constants.DAILY;

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu(lidu);

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(lidu);

		catchgoodsList = service.getCatchgoodsList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, catchgoodsList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String lidu = form.getLidu();

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
		dto.setLidu(lidu);

		catchgoodsList = service.getCatchgoodsList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, catchgoodsList);

		LogUtil.log(dto, TAG);

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

		List<LRCatchgoodsEntity> list = (List<LRCatchgoodsEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("商品_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "商品" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "抓取时间", "类目", "抓取商品数量", "新商品数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (LRCatchgoodsEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getLeimu()),
						String.valueOf(entity.getCatchcnt()),
						String.valueOf(entity.getNewcnt()) });
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

	public List<LRCatchgoodsEntity> getCatchgoodsList() {
		return catchgoodsList;
	}

	public void setCatchgoodsList(List<LRCatchgoodsEntity> catchgoodsList) {
		this.catchgoodsList = catchgoodsList;
	}

	public LirenService getService() {
		return service;
	}

	public void setService(LirenService service) {
		this.service = service;
	}
}
