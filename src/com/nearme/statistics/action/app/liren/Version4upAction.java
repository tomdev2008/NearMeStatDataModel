package com.nearme.statistics.action.app.liren;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.form.app.liren.LirenForm;
import com.nearme.statistics.model.liren.LRVersion4upEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * V4.0以上版本数据
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2014-1-7
 */
public class Version4upAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private final String TAG = "V4.0以上版本数据";
	private LirenForm form;
	private List<LRVersion4upEntity> version4upList;
	private LirenService service;

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
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		String lidu = Constants.DAILY;

		form.setSystemID("" + systemID);
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setLidu(lidu);

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		version4upList = service.getVersion4upList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, version4upList);

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
		Date endDate = form.getEndDate();
		String lidu = form.getLidu();
		String model = form.getModel();

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setModel(model);
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		version4upList = service.getVersion4upList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, version4upList);

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

		List<LRVersion4upEntity> list = (List<LRVersion4upEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动IMEI数", "启动留存LRR1（%）",
					"新增IMEI数", "新增账号数", "新增留存LRR1（%）", "该版本启动IMEI占比 （%）",
					"该版本新增IMEI占比（%）" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (LRVersion4upEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getStartimei()),
						String.valueOf(entity.getStartlrr()),
						String.valueOf(entity.getNewimei()),
						String.valueOf(entity.getNewssoid()),
						String.valueOf(entity.getNewlrr()),
						String.valueOf(entity.getStartimeiratio()),
						String.valueOf(entity.getNewimeiratio()) });
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

	public List<LRVersion4upEntity> getVersion4upList() {
		return version4upList;
	}

	public void setVersion4upList(List<LRVersion4upEntity> version4upList) {
		this.version4upList = version4upList;
	}

	public LirenService getService() {
		return service;
	}

	public void setService(LirenService service) {
		this.service = service;
	}
}
