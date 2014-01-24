package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ZJBOnlinepkginnerinstallEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 在线资源包内软件安装
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class OnlinepkginnerinstallAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "在线资源包内软件安装";
	private ZjbForm form;
	private List<ZJBOnlinepkginnerinstallEntity> onlinepkginnerinstallList;
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXdaysAgo(1);
		String lidu = Constants.DAILY;
		form = new ZjbForm();
		form.setStartTime(startDate);
		form.setLidu(lidu);

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setLidu(lidu);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String qudao = form.getQudao();
		String softName = form.getSoftName();

		if (startDate == null || softName == null || "".equals(softName)) {
			return Action.SUCCESS;
		}

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(startDate);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setQudao(qudao);
		dto.setSoftName(softName);
		onlinepkginnerinstallList = service.getOnlinepkginnerinstallList(dto);
		ServletActionContext.getRequest().getSession()
				.setAttribute(Constants.NEARME_SESSION_REPROT_DATA,
						onlinepkginnerinstallList);

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

		List<ZJBOnlinepkginnerinstallEntity> list = (List<ZJBOnlinepkginnerinstallEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "软件名", "资源包名", "包内软件安装量", "软件安装总量",
					"包内安装占比" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				ZJBOnlinepkginnerinstallEntity entity = list.get(i);
				eu.writeLine(new String[] {
						String.valueOf(entity.getSoftname()),
						String.valueOf(entity.getPkgname()),
						String.valueOf(entity.getInnerinstallcnt()),
						String.valueOf(entity.getInstalltotal()),
						String.valueOf(entity.getInnerinstallratio()) });
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

	public List<ZJBOnlinepkginnerinstallEntity> getOnlinepkginnerinstallList() {
		return onlinepkginnerinstallList;
	}

	public void setOnlinepkginnerinstallList(
			List<ZJBOnlinepkginnerinstallEntity> onlinepkginnerinstallList) {
		this.onlinepkginnerinstallList = onlinepkginnerinstallList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
