package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.LogDTO;
import com.nearme.statistics.dto.app.common.ModuleDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.HivejobEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
import com.nearme.statistics.model.common.ModulepositionChildEntity;
import com.nearme.statistics.model.common.ModulepositionEntity;
import com.nearme.statistics.model.commonsetting.ModuleEntity;
import com.nearme.statistics.service.app.common.ModuleService;
import com.nearme.statistics.service.hiveapp.common.CommonService;
import com.nearme.statistics.service.hiveapp.common.HivejobService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 模块位置分析
 * 
 * @author fgx
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-03
 */
public class ModulePositionAction extends ActionSupport {
	private static final long serialVersionUID = -4656688923848692953L;

	public static final String TAB_RESULT = "dm_hive_module_position";// 结果表
	private final String TAG = "模块位置分析";
	private BaseForm form;
	private String errorinfo;// 错误信息
	private List<ModulepositionEntity> modulepositionList;// 模块下载量总体及具体

	private List<HivejobEntity> hivejobList;// hive 任务列表
	private List<HivesqlEntity> hivesqlList;// hive sql列表
	private List<ModuleEntity> moduleList;// 模块列表(界面选择条件)

	private ModuleService moduleservice;// 模块查询(界面查询)
	private HivejobService jobservice;// hive job相关
	private CommonService service;// 含hive和oracle连接

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(7);
		String endDate = DateUtil.getToday();
		int position = 20;

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setPosition(position);

		// 初始化 "模块 " 下拉列表
		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID(form.getSystemID());
		moduleList = moduleservice.getGroupList(dto);
		
		queryjob();

		return Action.SUCCESS;
	}

	/**
	 * 查询--返回hive任务列表
	 * 
	 * @return
	 */
	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String qudao = form.getQudao();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String groupname = form.getGroupname();
		int position = form.getPosition();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setQudao(qudao);
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setGroupname(groupname);
		dto.setPosition(position);

		HivejobDTO hdto = new HivejobDTO();
		hdto.setSystemID(systemID);
		hdto.setResulttable(TAB_RESULT);
		hdto.setLidu(lidu);

		// 1.限定执行的任务数量
		long taskcnt = jobservice.getHivejobCnt(hdto);
		if (taskcnt < Constants.HIVE_TASK_LIMIT) {
			// log记录查询
			LogDTO logdto = LogUtil.log(dto, TAG);

			// 2.产生一条任务记录
			String username = logdto.getUsername();
			Date starttime = new Date();
			String jobID = username + "_"
					+ DateUtil.formatDate(starttime, "yyyyMMddHHmmss") + "_"
					+ Math.round(Math.random() * 1000);
			String condition = "[查询时间段："
					+ DateUtil.formatDate(startDate, "yyyyMMdd") + " ~ "
					+ DateUtil.formatDate(endDate, "yyyyMMdd") + "] [渠道："
					+ qudao + "] [模块：" + groupname + "] [版本：" + appVersion
					+ "] [粒度：" + lidu + "] [位置：" + position + "]";

			hdto.setJobID(jobID);
			hdto.setCondition(condition);
			hdto.setStarttime(starttime);
			hdto.setUsername(username);

			if (isSameQuery(hdto)) {
				errorinfo = "查询条件相同，一小时内已提交过相同查询";
			} else {
				jobservice.addHivejob(hdto);

				// 3.线程实现查询结果，将结果塞到结果表里
				final HivejobDTO fhdto = hdto;
				final BaseDTO fbdto = dto;
				Thread thread = new Thread(new Runnable() {
					public void run() {
						service.queryAndInsertModuleposition(fbdto, fhdto);

						// 5.完成任务 修改状态
						jobservice.finishHivejob(fhdto);
					}
				});
				thread.start();
			}
		} else {
			errorinfo = "提交任务过多，请稍后再提交。。。";
		}

		// 4.返回任务列表
		hivejobList = jobservice.getHivejobList(hdto);
		return "hive_listjob";
	}

	/**
	 * 判断在一小时内是否有相同条件的查询
	 * 
	 * @param hdto
	 * @return
	 */
	private boolean isSameQuery(HivejobDTO hdto) {
		List<HivejobEntity> list = jobservice.getHivejobList(hdto);

		String currCondition = hdto.getCondition();
		long currTime = new Date().getTime();

		for (HivejobEntity entity : list) {
			String condition = entity.getCondition();
			long starttime = entity.getStarttime().getTime();

			// 查询条件相同且查询时间间隔小于1小时
			if (null != condition && condition.equalsIgnoreCase(currCondition)) {
				if (currTime - starttime < 3600 * 1000) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 查看job
	 * 
	 * @return
	 */
	public String queryjob() {
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		HivejobDTO hdto = new HivejobDTO();
		hdto.setSystemID(systemID);
		hdto.setResulttable(TAB_RESULT);

		hivejobList = jobservice.getHivejobList(hdto);

		return "hive_listjob";
	}

	/**
	 * 结果表查询<br>
	 * 维度和粒度可以决定查询的具体表名
	 * 
	 * @return
	 */
	public String queryresult() {
		doqueryresult();
		return "moduleposition_jobresult";
	}

	private void doqueryresult() {
		String jobID = form.getJobID();
		String lidu = form.getLidu();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setJobID(jobID);
		hdto.setResulttable(lidu);

		modulepositionList = service.doGetModuleposition(hdto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA + jobID, modulepositionList);

		if (null == modulepositionList || modulepositionList.size() <= 0) {
			errorinfo = "查询结果为空";
		}
	}

	/**
	 * 删除结果<br>
	 * 
	 * @return
	 */
	public String deleteresult() {
		String jobID = form.getJobID();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setJobID(jobID);

		jobservice.deleteHivejob(hdto);
		service.deleteModuleposition(hdto);

		return "hive_listjob";
	}

	/**
	 * 查询sql
	 * 
	 * @return
	 */
	public String querySQL() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String qudao = form.getQudao();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String appVersion = form.getAppVersion();
		String groupname = form.getGroupname();
		int position = form.getPosition();

		if (systemID == 0) {
			return "hive_listsql";
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "hive_listsql";
		}

		BaseDTO dto = new BaseDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setQudao(qudao);
		dto.setLidu(lidu);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setGroupname(groupname);
		dto.setPosition(position);

		HivejobDTO hdto = new HivejobDTO();
		hdto.setSystemID(systemID);
		hdto.setResulttable(TAB_RESULT);
		hdto.setLidu(lidu);

		hivesqlList = service.getHqlModuleposition(dto);

		return "hive_listsql";
	}

	/**
	 * 结果表导出
	 * 
	 * @return
	 */
	public String exportresult() {
		doqueryresult();

		if (null == modulepositionList || modulepositionList.size() <= 0) {
			return "hive_listjob";
		}
		exportModuleposition(modulepositionList);

		return Action.SUCCESS;
	}

	/**
	 * 导出-自然模块数量总体及分析
	 * 
	 * @param list
	 */
	private void exportModuleposition(List<ModulepositionEntity> list) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 拼出列名称
			ModulepositionEntity item = list.get(0);
			List<ModulepositionChildEntity> clist = item.getList();
			int size = clist.size();
			String[] colnames = new String[size * 2 + 3];
			colnames[0] = "时间";
			colnames[1] = "总下载imei";
			colnames[2] = "总下载次数";
			for (int i = 0; i < clist.size(); i++) {
				String modulename = clist.get(i).getModulename();
				colnames[i * 2 + 3] = modulename + "下载次数";
				colnames[i * 2 + 4] = modulename + "下载imei数";
			}

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(colnames);

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());

			for (ModulepositionEntity entity : list) {
				String[] strs = new String[200];
				int i = 0;

				Date statDate = entity.getStatDate();
				long downimei = entity.getDownimei();
				long totaldown = entity.getTotaldown();
				List<ModulepositionChildEntity> childlist = entity.getList();

				strs[i++] = DateUtil.formatDate(statDate, "yyyy-MM-dd");
				strs[i++] = String.valueOf(downimei);
				strs[i++] = String.valueOf(totaldown);

				for (ModulepositionChildEntity childEntity : childlist) {
					long downcnt = childEntity.getDowncnt();
					long downimei2 = childEntity.getDownimei();

					strs[i++] = String.valueOf(downcnt);
					strs[i++] = String.valueOf(downimei2);
				}

				eu.writeLine(strs);
			}

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

	public List<ModulepositionEntity> getModulepositionList() {
		return modulepositionList;
	}

	public void setModulepositionList(List<ModulepositionEntity> modulepositionList) {
		this.modulepositionList = modulepositionList;
	}

	public List<HivejobEntity> getHivejobList() {
		return hivejobList;
	}

	public void setHivejobList(List<HivejobEntity> hivejobList) {
		this.hivejobList = hivejobList;
	}

	public List<HivesqlEntity> getHivesqlList() {
		return hivesqlList;
	}

	public void setHivesqlList(List<HivesqlEntity> hivesqlList) {
		this.hivesqlList = hivesqlList;
	}

	public List<ModuleEntity> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<ModuleEntity> moduleList) {
		this.moduleList = moduleList;
	}

	public ModuleService getModuleservice() {
		return moduleservice;
	}

	public void setModuleservice(ModuleService moduleservice) {
		this.moduleservice = moduleservice;
	}

	public HivejobService getJobservice() {
		return jobservice;
	}

	public void setJobservice(HivejobService jobservice) {
		this.jobservice = jobservice;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
