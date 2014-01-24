package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ModuleinstallEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 模块安装量
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class ModuleinstallAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "模块安装量";
	private ZjbForm form;
	private List<ModuleinstallEntity> moduleinstallList;
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
		moduleinstallList = service.getModuleinstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, moduleinstallList);
		
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
		dto.setQudao(qudao);
		dto.setAppVersion(appVersion);
		moduleinstallList = service.getModuleinstallList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, moduleinstallList);
		
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

		List<ModuleinstallEntity> list = (List<ModuleinstallEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "软件安装量", "在线资源包安装量", "在线资源包安装占比",
					"自定义资源包安装量", "自定义资源包安装占比", "在线软件安装量", "在线软件安装占比",
					"搜索模块安装量", "搜索模块安装量占比", "升级模块安装量", "升级模块安装量占比", "大型游戏安装量",
					"导航软件安装量","安装器安装量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				ModuleinstallEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getSoft()),
						String.valueOf(entity.getOnline1()),
						String.valueOf(entity.getOnlineRatio()),
						String.valueOf(entity.getSelf()),
						String.valueOf(entity.getSelfRatio()),
						String.valueOf(entity.getOnlineSoft()),
						String.valueOf(entity.getOnlineSoftRatio()),
						String.valueOf(entity.getSearch()),
						String.valueOf(entity.getSearchRatio()),
						String.valueOf(entity.getUpgrade()),
						String.valueOf(entity.getUpgradeRatio()),
						String.valueOf(entity.getBigGame()),
						String.valueOf(entity.getDaohang()),
						String.valueOf(entity.getInstaller()) });
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

	public List<ModuleinstallEntity> getModuleinstallList() {
		return moduleinstallList;
	}

	public void setModuleinstallList(List<ModuleinstallEntity> moduleinstallList) {
		this.moduleinstallList = moduleinstallList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
