package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ZJBSoftdownEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 软件下载
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class SoftdownAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "软件下载";
	private ZjbForm form;
	private List<ZJBSoftdownEntity> softdownList;
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form = new ZjbForm();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setLidu(Constants.DAILY);

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setLidu(form.getLidu());
		softdownList = service.getSoftdownList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, softdownList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String qudao = form.getQudao();

		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setQudao(qudao);
		softdownList = service.getSoftdownList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, softdownList);

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

		List<ZJBSoftdownEntity> list = (List<ZJBSoftdownEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "时间", "总下载", "后台更新量", "后台更新量占比",
					"手动点击下载量", "手动点击下载占比", "自定义资源包下载量", "自定义资源包下载占比" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (ZJBSoftdownEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getDowntotal()),
						String.valueOf(entity.getBackupdatecnt()),
						String.valueOf(entity.getBackupdateratio()),
						String.valueOf(entity.getSelfdown()),
						String.valueOf(entity.getSelfdownratio()),
						String.valueOf(entity.getSelfresdown()),
						String.valueOf(entity.getSelfresdownratio()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public ZjbForm getForm() {
		return form;
	}

	public void setForm(ZjbForm form) {
		this.form = form;
	}

	public List<ZJBSoftdownEntity> getSoftdownList() {
		return softdownList;
	}

	public void setSoftdownList(List<ZJBSoftdownEntity> softdownList) {
		this.softdownList = softdownList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
