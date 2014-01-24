package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ZJBRunpointEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 单个资源运营点
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class RunpointAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "单个资源运营点";
	private ZjbForm form;
	private List<ZJBRunpointEntity> runpointList;// 默认
	private List<ZJBRunpointEntity> runpointaddList;// 添加
	private List<ZJBRunpointEntity> runpointsearchList;// 搜索
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXdaysAgo(1);
		form = new ZjbForm();
		form.setStartTime(startDate);
		form.setLidu(Constants.DAILY);

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setLidu(form.getLidu());

		runpointList = service.getRunpointList(dto);
		runpointaddList = service.getRunpointAddList(dto);

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, runpointaddList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, runpointList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String qudao = form.getQudao();
		String softName = form.getSoftName();

		if (startDate == null) {
			return "runpoint_result";
		}

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(startDate);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setQudao(qudao);
		dto.setSoftName(softName);

		runpointList = service.getRunpointList(dto);
		runpointaddList = service.getRunpointAddList(dto);
		if (null != softName && !"".equals(softName)) {
			runpointsearchList = service.getRunpointSearchList(dto);
		}

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, runpointaddList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, runpointList);

		LogUtil.log(dto, TAG);

		return "runpoint_result";
	}

	/**
	 * 添加查询结果
	 * 
	 * @return
	 */
	public String addSoft() {
		String softName = form.getSoftName();
		String lidu = form.getLidu();

		ZjbDTO dto = new ZjbDTO();
		dto.setSoftName(softName);
		dto.setLidu(lidu);

		service.addRunpointsoft(dto);

		return Action.SUCCESS;
	}

	/**
	 * 删除查询结果
	 * 
	 * @return
	 */
	public String deleteSoft() {
		String softName = form.getSoftName();
		String lidu = form.getLidu();

		ZjbDTO dto = new ZjbDTO();
		dto.setSoftName(softName);
		dto.setLidu(lidu);

		service.deleteRunpointsoft(dto);

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
		Object obj2 = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);

		if (obj == null && obj2 == null) {
			return Action.SUCCESS;
		}

		List<ZJBRunpointEntity> list = (List<ZJBRunpointEntity>) obj;
		List<ZJBRunpointEntity> list2 = (List<ZJBRunpointEntity>) obj2;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "软件名", "安装总量", "自定义资源包安装量", "在线资源包安装量",
					"搜索安装量", "在线软件安装量", "强推安装量", "安装器安装量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (ZJBRunpointEntity entity : list) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getSoftname()),
						String.valueOf(entity.getInstalltotal()),
						String.valueOf(entity.getSelfcnt()),
						String.valueOf(entity.getOnlinerescnt()),
						String.valueOf(entity.getSearchcnt()),
						String.valueOf(entity.getOnlinesoftcnt()),
						String.valueOf(entity.getPushinstall()),
						String.valueOf(entity.getInstallercnt()) });
			}
			for (ZJBRunpointEntity entity : list2) {
				eu.writeLine(new String[] {
						String.valueOf(entity.getSoftname()),
						String.valueOf(entity.getInstalltotal()),
						String.valueOf(entity.getSelfcnt()),
						String.valueOf(entity.getOnlinerescnt()),
						String.valueOf(entity.getSearchcnt()),
						String.valueOf(entity.getOnlinesoftcnt()),
						String.valueOf(entity.getPushinstall()),
						String.valueOf(entity.getInstallercnt()) });
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

	public List<ZJBRunpointEntity> getRunpointList() {
		return runpointList;
	}

	public void setRunpointList(List<ZJBRunpointEntity> runpointList) {
		this.runpointList = runpointList;
	}

	public List<ZJBRunpointEntity> getRunpointaddList() {
		return runpointaddList;
	}

	public void setRunpointaddList(List<ZJBRunpointEntity> runpointaddList) {
		this.runpointaddList = runpointaddList;
	}

	public List<ZJBRunpointEntity> getRunpointsearchList() {
		return runpointsearchList;
	}

	public void setRunpointsearchList(List<ZJBRunpointEntity> runpointsearchList) {
		this.runpointsearchList = runpointsearchList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
