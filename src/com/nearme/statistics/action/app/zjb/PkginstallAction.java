package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ZJBPkginstallEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 资源包安装
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class PkginstallAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "资源包安装";
	private ZjbForm form;
	private List<ZJBPkginstallEntity> pkginstallList;
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
		pkginstallList = service.getPkginstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pkginstallList);

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
		pkginstallList = service.getPkginstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, pkginstallList);

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

		List<ZJBPkginstallEntity> list = (List<ZJBPkginstallEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "安装总量", "一键安装量", "一键安装占比",
					"自定义资源一键安装量", "自定义资源一键安装占比", "在线资源一键安装量", "在线资源一键安装占比",
					"包内安装量", "包内安装占比", "自定义资源包内安装量", "自定义资源包内安装占比",
					"在线资源包内安装量", "在线资源包内安装占比" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (ZJBPkginstallEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getInstalltotal()),
						String.valueOf(entity.getKeycnt()),
						String.valueOf(entity.getKeyratio()),
						String.valueOf(entity.getSelfcnt()),
						String.valueOf(entity.getSelfratio()),
						String.valueOf(entity.getOnlinecnt()),
						String.valueOf(entity.getOnlineratio()),
						String.valueOf(entity.getInnercnt()),
						String.valueOf(entity.getInnerratio()),
						String.valueOf(entity.getSelfinnercnt()),
						String.valueOf(entity.getSelfinnerratio()),
						String.valueOf(entity.getOnlineinnercnt()),
						String.valueOf(entity.getOnlineinnerratio()) });
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

	public List<ZJBPkginstallEntity> getPkginstallList() {
		return pkginstallList;
	}

	public void setPkginstallList(List<ZJBPkginstallEntity> pkginstallList) {
		this.pkginstallList = pkginstallList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
