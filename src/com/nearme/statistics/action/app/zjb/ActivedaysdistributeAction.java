package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ZJBActivedaysdistributeEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 月活天数分布
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class ActivedaysdistributeAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "月活天数分布";
	private ZjbForm form;
	private List<ZJBActivedaysdistributeEntity> activedaysdistributeList;
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXmonthAgo(1);
		form = new ZjbForm();
		form.setStartTime(startDate.substring(0, startDate.lastIndexOf("-")));

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM"));
		dto.setLidu(form.getLidu());
		activedaysdistributeList = service.getActivedaysdistributeList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, activedaysdistributeList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate("yyyy-MM");
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String qudao = form.getQudao();

		if (startDate == null) {
			return Action.SUCCESS;
		}

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(startDate);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setQudao(qudao);
		activedaysdistributeList = service.getActivedaysdistributeList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, activedaysdistributeList);

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

		List<ZJBActivedaysdistributeEntity> list = (List<ZJBActivedaysdistributeEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu
					.writeLine(new String[] { "活跃天数", "用户数", "新增用户数", "登陆用户",
							"新增登陆用户", "启动次数", "安装量", "安装量占比", "服务手机数", "人均安装量",
							"台均安装量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (ZJBActivedaysdistributeEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getActivedays()),
						String.valueOf(entity.getUsercnt()),
						String.valueOf(entity.getNewusercnt()),
						String.valueOf(entity.getLoginuser()),
						String.valueOf(entity.getNewloginuser()),
						String.valueOf(entity.getStartcnt()),
						String.valueOf(entity.getInstallapp()),
						String.valueOf(entity.getInstallratio()),
						String.valueOf(entity.getServemobile()),
						String.valueOf(entity.getAvginstaluser()),
						String.valueOf(entity.getAvginstallmobel()) });
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

	public List<ZJBActivedaysdistributeEntity> getActivedaysdistributeList() {
		return activedaysdistributeList;
	}

	public void setActivedaysdistributeList(
			List<ZJBActivedaysdistributeEntity> activedaysdistributeList) {
		this.activedaysdistributeList = activedaysdistributeList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
