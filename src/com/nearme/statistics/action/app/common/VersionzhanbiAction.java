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
 * 版本占比
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-10
 */
public class VersionzhanbiAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;

	private BaseEntity entity;

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
		entity = new BaseEntity();

		String startDate = DateUtil.getDateOfXdaysAgo(1);
		String type = "STARTIMEI";

		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());
		form.setType(type);

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setType(type);

		List<ColumnValueEntity> list = service.getVersionzhanbiList(dto);
		setColumn(type);
		entity.setValueList(list);

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, entity);
		
		LogUtil.log(dto, "版本占比");//log记录查询

		return Action.SUCCESS;
	}

	public String query() {
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

		entity = new BaseEntity();
		List<ColumnValueEntity> list = service.getVersionzhanbiList(dto);
		setColumn(type);
		entity.setValueList(list);

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, entity);
		
		LogUtil.log(dto, "版本占比");//log记录查询

		return Action.SUCCESS;
	}

	/**
	 * 设置列名称及报表名称
	 * 
	 * @param type
	 */
	private void setColumn(String type) {
		ColumnEntity columns = new ColumnEntity();
		if ("STARTIMEI".equalsIgnoreCase(type)) {
			columns.setColumn1("版本");
			columns.setColumn2("启动IMEI数");
			columns.setColumn3("占比（%）");
		} else if ("NEWIMEI".equals(type)) {
			columns.setColumn1("版本");
			columns.setColumn2("新增IMEI数");
			columns.setColumn3("占比（%）");
		}
		entity.setColumns(columns);
		entity.setTblName("版本占比");
	}

	/**
	 * 导出报表
	 * 
	 * @return
	 */
	public String export() {
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
			for (ColumnValueEntity entity : valuelist) {
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

	public BaseEntity getEntity() {
		return entity;
	}

	public void setEntity(BaseEntity entity) {
		this.entity = entity;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

}
