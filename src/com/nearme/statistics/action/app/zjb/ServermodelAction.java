package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ServermodelEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 服务机型
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-7-9
 */
public class ServermodelAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private ZjbForm form;
	private List<ServermodelEntity> servermodelList1;// 结果集1
	private List<ServermodelEntity> servermodelList2;// 结果集2
	private ZjbService service;

	public String init() {
		String startDate = DateUtil.getDateOfXdaysAgo(1);
		form = new ZjbForm();
		form.setStartTime(startDate);
		form.setLidu("DAILY");

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setLidu(form.getLidu());
		servermodelList1 = service.getServermodelList(dto);
		servermodelList2 = service.getServermodelList2(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, servermodelList1);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, servermodelList2);

		LogUtil.log(dto, "服务机型");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
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
		servermodelList1 = service.getServermodelList(dto);
		servermodelList2 = service.getServermodelList2(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, servermodelList1);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, servermodelList2);

		LogUtil.log(dto, "服务机型");

		return Action.SUCCESS;
	}

	/**
	 * 导出报表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object obj1 = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);
		Object obj2 = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);

		if (obj1 == null && obj2 == null) {
			return Action.SUCCESS;
		}

		List<ServermodelEntity> list1 = (List<ServermodelEntity>) obj1;
		List<ServermodelEntity> list2 = (List<ServermodelEntity>) obj2;

		export1(list1);// 导出表1
		export2(list2);// 导出表2

		return Action.SUCCESS;
	}

	/**
	 * 导出表2
	 * 
	 * @param list1
	 */
	private void export2(List<ServermodelEntity> list1) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("服务机型2_" + DateUtil.getToday());
		// 导出表1
		try {
			eu.createSheets(1, new String[] { "服务机型" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "品牌", "机型", "机型连接次数", "机型连接占比",
					"机型安装量", "机型安装占比", "机型平均安装量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list1.size() - 1; i >= 0; i--) {
				ServermodelEntity entity = list1.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getPinpai()),
						String.valueOf(entity.getModel()),
						String.valueOf(entity.getConnectCnt()),
						String.valueOf(entity.getZhanbi()),
						String.valueOf(entity.getInstallcnt()),
						String.valueOf(entity.getInstallratio()),
						String.valueOf(entity.getAvginstall()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出表1
	 * 
	 * @param list1
	 */
	private void export1(List<ServermodelEntity> list2) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("服务机型1_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "服务机型" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "品牌", "品牌连接次数", "品牌连接占比",
					"品牌安装量", "品牌安装占比", "品牌平均安装量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list2.size() - 1; i >= 0; i--) {
				ServermodelEntity entity = list2.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getPinpai()),
						String.valueOf(entity.getConnectCnt()),
						String.valueOf(entity.getZhanbi()),
						String.valueOf(entity.getInstallcnt()),
						String.valueOf(entity.getInstallratio()),
						String.valueOf(entity.getAvginstall()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ZjbForm getForm() {
		return form;
	}

	public void setForm(ZjbForm form) {
		this.form = form;
	}

	public List<ServermodelEntity> getServermodelList1() {
		return servermodelList1;
	}

	public void setServermodelList1(List<ServermodelEntity> servermodelList1) {
		this.servermodelList1 = servermodelList1;
	}

	public List<ServermodelEntity> getServermodelList2() {
		return servermodelList2;
	}

	public void setServermodelList2(List<ServermodelEntity> servermodelList2) {
		this.servermodelList2 = servermodelList2;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}
}
