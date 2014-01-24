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
 * 使用频率时长分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-5-16
 */
public class UseoftenanalyAction extends ActionSupport {
	private static final long serialVersionUID = 3344613703440222126L;
	private BaseForm form;// 日启动次数分析form
	private BaseForm formusetime;// 日使用时长分析form

	private BaseEntity startcntEntity;
	private BaseEntity usetimeEntity;

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
		formusetime = new BaseForm();
		startcntEntity = new BaseEntity();
		usetimeEntity = new BaseEntity();

		initStartcnt();
		initUsetime();
		return Action.SUCCESS;
	}
	
	/**
	 * 初始化日启动次数
	 */
	private void initStartcnt() {
		String startDate = DateUtil.getDateOfXdaysAgo(1);

		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());

		List<ColumnValueEntity> list = service.getUseoftenStartcntList(dto);
		setStartcntColumn();
		startcntEntity.setValueList(list);
		
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, startcntEntity);
		
		LogUtil.log(dto, "使用频率时长分析");//log记录查询
	}

	/**
	 * 初始化日使用时长
	 */
	private void initUsetime() {
		String startDate = DateUtil.getDateOfXdaysAgo(1);

		formusetime.setStartTime(startDate);
		formusetime.setSystemID(form.getSystemID());

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(formusetime.getSystemIDValue());

		List<ColumnValueEntity> list = service.getUseoftenUsetimeList(dto);
		setUsetimeColumn();
		usetimeEntity.setValueList(list);

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, usetimeEntity);
		
		LogUtil.log(dto, "使用频率时长分析");//log记录查询
	}

	/**
	 * 查询日使用时长
	 * 
	 * @return
	 */
	public String queryusetime() {
		Date startDate = formusetime.getStartDate();
		int systemID = formusetime.getSystemIDValue();
		String appVersion = formusetime.getAppVersion();
		String model = formusetime.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);

		usetimeEntity = new BaseEntity();
		List<ColumnValueEntity> list = service.getUseoftenUsetimeList(dto);
		setUsetimeColumn();
		usetimeEntity.setValueList(list);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, usetimeEntity);
		
		LogUtil.log(dto, "使用频率时长分析");//log记录查询

		return "queryusetime";
	}

	/**
	 * 查询日启动次数
	 * 
	 * @return
	 */
	public String querystartcnt() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);

		startcntEntity = new BaseEntity();
		List<ColumnValueEntity> list = service.getUseoftenStartcntList(dto);
		setStartcntColumn();
		startcntEntity.setValueList(list);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, startcntEntity);
		
		LogUtil.log(dto, "使用频率时长分析");//log记录查询

		return "querystartcnt";
	}

	/**
	 * 设置
	 * 日启动次数
	 * 列名称及报表名称
	 */
	private void setStartcntColumn() {
		ColumnEntity columns = new ColumnEntity();
		columns.setColumn1("启动次数");
		columns.setColumn2("启动IMEI数");
		columns.setColumn3("占比（%）");
		startcntEntity.setColumns(columns);
		startcntEntity.setTblName("使用频率时长分析_日启动次数");
	}

	/**
	 * 设置
	 * 日使用时长
	 * 列名称及报表名称
	 */
	private void setUsetimeColumn() {
		ColumnEntity columns = new ColumnEntity();
		columns.setColumn1("平均时长");
		columns.setColumn2("启动IMEI数");
		columns.setColumn3("占比（%）");
		usetimeEntity.setColumns(columns);
		usetimeEntity.setTblName("使用频率时长分析_日使用时长");
	}

	/**
	 * 导出报表-日启动次数信息
	 * 
	 * @return
	 */
	public String exportstartcnt() {
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
	
	/**
	 * 导出报表-日使用时长信息
	 * 
	 * @return
	 */
	public String exportusetime() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);

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

	public BaseForm getFormusetime() {
		return formusetime;
	}

	public void setFormusetime(BaseForm formusetime) {
		this.formusetime = formusetime;
	}

	public BaseEntity getStartcntEntity() {
		return startcntEntity;
	}

	public void setStartcntEntity(BaseEntity startcntEntity) {
		this.startcntEntity = startcntEntity;
	}

	public BaseEntity getUsetimeEntity() {
		return usetimeEntity;
	}

	public void setUsetimeEntity(BaseEntity usetimeEntity) {
		this.usetimeEntity = usetimeEntity;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
