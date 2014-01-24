package com.nearme.statistics.action.app.zjb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.form.app.zjb.ZjbForm;
import com.nearme.statistics.model.zjb.ChannelserviceEntity;
import com.nearme.statistics.service.app.zjb.ZjbService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 分渠道服务数据
 *
 * @author 林逸聪
 * @version 1.0
 * @since 1.0, 2014-1-3
 */
public class ChannelserviceAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private final String TAG = "分渠道服务数据";
	private ZjbForm form;
	private List<ChannelserviceEntity> channelserviceList;
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
		channelserviceList = service.getChannelserviceList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, channelserviceList);

		LogUtil.log(dto, TAG);

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String qudao  = form.getQudao();

		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		ZjbDTO dto = new ZjbDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setLidu(lidu);
		dto.setAppVersion(appVersion);
		dto.setQudao(qudao);
		channelserviceList = service.getChannelserviceList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, channelserviceList);

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

		List<ChannelserviceEntity> list = (List<ChannelserviceEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "登陆用户", "登陆用户服务的非OPPO手机数", "分成手机数",
					"登陆用户服务的非OPPO手机台均安装量", "登陆用户服务的OPPO手机数", "分成手机数", "登陆用户服务的OPPO手机台均安装量",
					"未登陆用户数", "未登陆用户服务的非OPPO手机数", "分成手机数",
					"未登陆用户服务的非OPPO手机台均安装量", "未登陆用户服务的OPPO手机数", "分成手机数", "未登陆用户服务的OPPO手机台均安装量", });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				ChannelserviceEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getIn_users()),
						String.valueOf(entity.getIn_not_oppo()),
						String.valueOf(entity.getIn_not_oppo_bonus()),
						String.valueOf(entity.getIn_not_oppo_avg()),

						String.valueOf(entity.getIn_oppo()),
						String.valueOf(entity.getIn_oppo_bonus()),
						String.valueOf(entity.getIn_oppo_avg()),

						String.valueOf(entity.getOut_users()),
						String.valueOf(entity.getOut_not_oppo()),
						String.valueOf(entity.getOut_not_oppo_bonus()),
						String.valueOf(entity.getIn_not_oppo_avg()),

						String.valueOf(entity.getOut_oppo()),
						String.valueOf(entity.getOut_oppo_bonus()),
						String.valueOf(entity.getOut_oppo_avg())
						});
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

	public List<ChannelserviceEntity> getChannelserviceList() {
		return channelserviceList;
	}

	public void setChannelserviceList(List<ChannelserviceEntity> channelserviceList) {
		this.channelserviceList = channelserviceList;
	}

	public ZjbService getService() {
		return service;
	}

	public void setService(ZjbService service) {
		this.service = service;
	}

}
