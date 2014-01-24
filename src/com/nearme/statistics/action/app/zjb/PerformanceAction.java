package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.PerformanceEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 性能指标
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class PerformanceAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private ZjbForm form;
	private List<PerformanceEntity> performanceList;
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		form = new ZjbForm();
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setLidu("DAILY");

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setLidu(form.getLidu());
		performanceList = service.getPerformanceList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, performanceList);
		
		LogUtil.log(dto, "性能指标");

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
		performanceList = service.getPerformanceList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, performanceList);
		
		LogUtil.log(dto, "性能指标");

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

		List<PerformanceEntity> list = (List<PerformanceEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("性能指标_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "性能指标" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "连接次数", "连接成功次数", "连接成功率",
					"下载次数", "下载成功次数", "下载成功率",
					"安装次数", "安装成功次数", "安装成功率",
					"软件启动次数", "软件崩溃次数", "软件崩溃比率",
					"演示机还原次数", "演示机还原成功次数", "演示机还原成功率"});

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				PerformanceEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getConnectCnt()),
						String.valueOf(entity.getConnectSuccess()),
						String.valueOf(entity.getConnectRatio()),
						String.valueOf(entity.getDownCnt()),
						String.valueOf(entity.getDownSuccess()),
						String.valueOf(entity.getDownRatio()),
						String.valueOf(entity.getInstallCnt()),
						String.valueOf(entity.getInstallSuccess()),
						String.valueOf(entity.getInstallRatio()),
						String.valueOf(entity.getSoftStart()),
						String.valueOf(entity.getSoftCrash()),
						String.valueOf(entity.getSoftCrashRatio()),
						String.valueOf(entity.getRestoreCnt()),
						String.valueOf(entity.getRestoreSuccess()),
						String.valueOf(entity.getRestoreRatio()) });
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

	public List<PerformanceEntity> getPerformanceList() {
		return performanceList;
	}

	public void setPerformanceList(List<PerformanceEntity> performanceList) {
		this.performanceList = performanceList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
