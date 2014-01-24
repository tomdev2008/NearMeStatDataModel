package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.BaseEntity;
import com.nearme.statistics.model.ColumnEntity;
import com.nearme.statistics.model.ColumnValueEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 地区分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class AreaanalyAction extends ActionSupport {
	private static final long serialVersionUID = 353523249462494531L;

	private BaseForm form;// 地区top10对应的form
	private BaseForm formdetail;// 具体地区对应的form

	private BaseEntity top10Entity;
	private BaseEntity detailEntity;

	private CommonService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		// 初始化对象
		formdetail = new BaseForm();
		top10Entity = new BaseEntity();
		detailEntity = new BaseEntity();

		initTop10();
		initDetail();
		return Action.SUCCESS;
	}

	/**
	 * 初始化地区top10
	 */
	private void initTop10() {
		String startDate = DateUtil.getDateOfXdaysAgo(1);
		String type = "STARTIMEI";

		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());
		form.setType(type);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setType(type);

		List<ColumnValueEntity> list = service.getAreatop10List(dto);
		setTop10Column(type);
		top10Entity.setValueList(list);
		
		LogUtil.log(dto, "地区分析");//log记录查询
	}

	/**
	 * 初始化具体地区
	 */
	private void initDetail() {
		String startDate = DateUtil.getDateOfXdaysAgo(7);
		String endDate = DateUtil.getToday();
		String type = "STARTIMEI";

		formdetail.setStartTime(startDate);
		formdetail.setEndTime(endDate);
		formdetail.setSystemID(form.getSystemID());
		formdetail.setType(type);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(formdetail.getSystemIDValue());
		dto.setType(type);

		List<ColumnValueEntity> list = service.getAreadetailList(dto);
		setDetailColumn(type);
		detailEntity.setValueList(list);

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailEntity);
		
		LogUtil.log(dto, "地区分析");//log记录查询
	}

	/**
	 * 查询具体地区数据
	 * 
	 * @return
	 */
	public String querydetail() {
		Date startDate = formdetail.getStartDate();
		Date endDate = formdetail.getEndDate();
		int systemID = formdetail.getSystemIDValue();
		String type = formdetail.getType();
		String appVersion = formdetail.getAppVersion();
		String place = formdetail.getPlace();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setType(type);
		dto.setAppVersion(appVersion);
		dto.setPlace(place);

		detailEntity = new BaseEntity();
		List<ColumnValueEntity> list = service.getAreadetailList(dto);
		setDetailColumn(type);
		detailEntity.setValueList(list);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, detailEntity);
		
		LogUtil.log(dto, "地区分析");//log记录查询

		return "querydetail";
	}

	/**
	 * 查询all数据
	 * 
	 * @return
	 */
	public String querytop10() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String type = form.getType();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setType(type);

		top10Entity = new BaseEntity();
		List<ColumnValueEntity> list = service.getAreatop10List(dto);
		setTop10Column(type);
		top10Entity.setValueList(list);
		
		LogUtil.log(dto, "地区分析");//log记录查询

		return "querytop10";
	}

	/**
	 * 设置top10列名称及报表名称
	 * 
	 * @param type
	 */
	private void setTop10Column(String type) {
		ColumnEntity columns = new ColumnEntity();
		if ("STARTIMEI".equalsIgnoreCase(type)) {
			columns.setColumn1("网络类型");
			columns.setColumn2("启动IMEI数");
			columns.setColumn3("占比（%）");
		} else if ("NEWIMEI".equals(type)) {
			columns.setColumn1("网络类型");
			columns.setColumn2("新增IMEI数");
			columns.setColumn3("占比（%）");
		}
		top10Entity.setColumns(columns);
		top10Entity.setTblName("地区分析_top10");
	}

	/**
	 * 设置detail列名称及报表名称
	 * 
	 * @param type
	 */
	private void setDetailColumn(String type) {
		ColumnEntity columns = new ColumnEntity();
		if ("STARTIMEI".equalsIgnoreCase(type)) {
			columns.setColumn1("日期");
			columns.setColumn2("启动IMEI数");
			columns.setColumn3("占启动IMEI比率（%）");
		} else if ("NEWIMEI".equals(type)) {
			columns.setColumn1("日期");
			columns.setColumn2("新增IMEI数");
			columns.setColumn3("占新增IMEI比率（%）");
		}
		detailEntity.setColumns(columns);
		detailEntity.setTblName("地区分析_detail");
	}

	/**
	 * 导出报表-具体终端信息
	 * 
	 * @return
	 */
	public String exportdetail() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		BaseEntity baseEntity = (BaseEntity) obj;
		String tblname = baseEntity.getTblName();
		ColumnEntity columns = baseEntity.getColumns();
		List<ColumnValueEntity> valuelist = baseEntity.getValueList();

		if (valuelist == null) {
			return Action.SUCCESS;
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(tblname + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { tblname });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { columns.getColumn1(),
					columns.getColumn2(), columns.getColumn3() });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = valuelist.size() - 1; i >= 0; i--) {
				ColumnValueEntity entity = valuelist.get(i);
				eu.writeLine(new String[] { entity.getValue1(),
						entity.getValue2(), entity.getValue3() });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public BaseForm getFormdetail() {
		return formdetail;
	}

	public void setFormdetail(BaseForm formdetail) {
		this.formdetail = formdetail;
	}

	public BaseEntity getTop10Entity() {
		return top10Entity;
	}

	public void setTop10Entity(BaseEntity top10Entity) {
		this.top10Entity = top10Entity;
	}

	public BaseEntity getDetailEntity() {
		return detailEntity;
	}

	public void setDetailEntity(BaseEntity detailEntity) {
		this.detailEntity = detailEntity;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
