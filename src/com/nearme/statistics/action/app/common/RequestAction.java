package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.RequestjobDTO;
import com.nearme.statistics.dto.app.common.MeasureDTO;
import com.nearme.statistics.form.RequestForm;
import com.nearme.statistics.model.commonsetting.MeasureEntity;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.service.app.common.MeasureService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 需求提交
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-19
 */
public class RequestAction extends ActionSupport {
	private static final long serialVersionUID = -7747673878091973779L;

	private final String TAG = "需求提交";
	private RequestForm form;
	
	private List<MeasureEntity> measureList;//指标list
	
	private CommonService service;
	private MeasureService measureservice;// 指标service

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}
		
		MeasureDTO dto = new MeasureDTO();
		measureList = measureservice.getMeasureList(dto);

		return Action.SUCCESS;
	}

	/**
	 * 提交需求
	 * 
	 * @return
	 */
	public String submit() {
		// 查询条件
		int systemID = form.getSystemIDValue();
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String qudao = form.getQudao();
		String path = form.getPath();
		String productname = form.getProductname();

		// 样本选择
		String type = form.getType();
		Date sampleStartDate = form.getSampleStartDate();
		Date sampleEndDate = form.getSampleEndDate();

		// 查询指标
		String zhibiao = form.getZhibiao();

		if (startDate == null || endDate == null || startDate.after(endDate)
				|| zhibiao == null) {
			return Action.SUCCESS;
		}

		// 操作人
		Admin admin = (Admin) ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.SESSION_ADMIN_KEY);
		String username = admin.getUserName();// 获取用户名

		String startDateStr = DateUtil.formatDate(startDate, "yyyy-MM-dd");
		String endDateStr = DateUtil.formatDate(endDate, "yyyy-MM-dd");
		String sampleStartDateStr = DateUtil.formatDate(sampleStartDate,
				"yyyy-MM-dd");
		String sampleEndDateStr = DateUtil.formatDate(sampleEndDate,
				"yyyy-MM-dd");

		// 查询条件转换
		StringBuilder condition = new StringBuilder(256);
		condition.append("startDateStr=").append(
				startDateStr.replaceAll("&&", "  ")).append("&&").append(
				"endDateStr=").append(endDateStr.replaceAll("&&", "  "))
				.append("&&").append("lidu=").append(
						lidu == null ? "" : lidu.replaceAll("&&", "  "))
				.append("&&").append("appVersion=").append(
						appVersion == null ? "" : appVersion.replaceAll("&&",
								"  ")).append("&&").append("model=").append(
						model == null ? "" : model.replaceAll("&&", "  "))
				.append("&&").append("qudao=").append(
						qudao == null ? "" : qudao.replaceAll("&&", "  "))
				.append("&&").append("path=").append(
						path == null ? "" : path.replaceAll("&&", "  "))
				.append("&&").append("productname=").append(
						productname == null ? "" : productname.replaceAll("&&",
								"  "));

		// 样本选择转换
		StringBuilder sample = new StringBuilder(256);
		sample.append("type=").append(
				type == null ? "" : type.replaceAll("&&", "  ")).append("&&")
				.append("sampleStartDateStr=").append(
						sampleStartDateStr.replaceAll("&&", "  ")).append("&&")
				.append("sampleEndDateStr=").append(
						sampleEndDateStr.replaceAll("&&", "  "));

		// 插入DB
		RequestjobDTO dto = new RequestjobDTO();
		dto.setSystemID("" + systemID);
		dto.setUsername(username);
		dto.setCondition(condition.toString());
		dto.setSample(sample.toString());
		dto.setIndicat(zhibiao);
		service.createRequest(dto);

		LogUtil.log(dto, TAG);// log记录查询

		return Action.SUCCESS;
	}

	public RequestForm getForm() {
		return form;
	}

	public void setForm(RequestForm form) {
		this.form = form;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

	public MeasureService getMeasureservice() {
		return measureservice;
	}

	public void setMeasureservice(MeasureService measureservice) {
		this.measureservice = measureservice;
	}

	public List<MeasureEntity> getMeasureList() {
		return measureList;
	}

	public void setMeasureList(List<MeasureEntity> measureList) {
		this.measureList = measureList;
	}
}
