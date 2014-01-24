package com.nearme.statistics.action.app.coloros;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.LogDTO;
import com.nearme.statistics.dto.app.coloros.ColorosDTO;
import com.nearme.statistics.form.app.coloros.ColorosForm;
import com.nearme.statistics.model.coloros.COSStartrankEntity;
import com.nearme.statistics.model.common.HivejobEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
import com.nearme.statistics.service.hiveapp.coloros.ColorosService;
import com.nearme.statistics.service.hiveapp.common.HivejobService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 启动排行
 *
 * @author 林逸聪
 * @version 1.0
 * @since 1.0, 2013-10-29
 */
public class StartrankAction extends ActionSupport {
	private static final long serialVersionUID = -4656688923848692953L;

	public static final String TAB_RESULT = "dm_hive_cos_start_rank";// 结果表
	private final String TAG = "启动排行";
	private ColorosForm form;
	private String errorinfo;// 错误信息
	private List<COSStartrankEntity> startrankList;
	private List<COSStartrankEntity> startrankweekList;//启动排行周数据
	private List<HivejobEntity> hivejobList;// hive 任务列表
	private List<HivesqlEntity> hivesqlList;// hive sql列表
	private ColorosService service;// 含hive和oracle连接
	private HivejobService jobservice;// hive job相关

	/**
	 * 初始化进入启动排行周数据界面
	 * @return
	 */
	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(7);
		int systemID = form.getSystemIDValue();
		
		form.setStartTime(startDate);
		form.setSystemID("" + systemID);

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDateStr(DateUtil.getDateOfXdaysAgo(DateUtil.parseDate(startDate, "yyyy-MM-dd"), 6, "yyyyMMdd"));
		dto.setEndDateStr(DateUtil.formatDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"),"yyyyMMdd"));
		dto.setSystemID(systemID);
		startrankweekList = service.getStartrankweekList(dto);
		
		queryjob();

		return "startrank_week";
	}

	/**
	 * 启动排行周查询
	 * @return
	 */
	public String queryweek(){
		int systemID = form.getSystemIDValue();
		Date startDate = form.getStartDate();
		
		if (startDate == null) {
			return "startrank_week";
		}

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDateStr(DateUtil.getDateOfXdaysAgo(startDate, 6, "yyyyMMdd"));
		dto.setEndDateStr(DateUtil.formatDate(startDate, "yyyyMMdd"));
		dto.setSystemID(systemID);
		startrankweekList = service.getStartrankweekList(dto);

		return "startrank_week";
	}
	
	/**
	 * 启动排行hive查询界面
	 * @return
	 */
	public String initrank() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();
		String systemID = form.getSystemID();

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(systemID);

		return Action.SUCCESS;
	}

	public String query() {
		int systemID = form.getSystemIDValue();
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String model = form.getModel();

		String startDateStr = DateUtil.formatDate(startDate, "yyyyMMdd");
		String endDateStr = DateUtil.formatDate(endDate, "yyyyMMdd");

		if (startDate == null) {
			return Action.SUCCESS;
		}

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDateStr(startDateStr);
		dto.setEndDateStr(endDateStr);
		dto.setModel(model);
		dto.setSystemID(systemID);

		HivejobDTO hdto = new HivejobDTO();
		hdto.setResulttable(TAB_RESULT);
		hdto.setSystemID(systemID);

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
			String condition = "[起始时间："
					+ DateUtil.formatDate(startDate, "yyyyMMdd") + "]"
					+ "[结束时间：" + DateUtil.formatDate(endDate, "yyyyMMdd") + "]"
					+ " [机型：" + model + "]";

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
				final ColorosDTO fbdto = dto;
				Thread thread = new Thread(new Runnable() {
					public void run() {
						service.queryAndInsertStartrankList(fbdto, fhdto);
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
		int systemID = form.getSystemIDValue();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setResulttable(TAB_RESULT);
		hdto.setSystemID(systemID);

		hivejobList = jobservice.getHivejobList(hdto);

		return "hive_listjob";
	}

	/**
	 * 结果表查询
	 *
	 * @return
	 */
	public String queryresult() {
		doqueryresult();

		if (null == startrankList || startrankList.size() <= 0) {
			errorinfo = "查询结果为空";
		}

		return "startrank_jobresult";
	}

	private void doqueryresult() {
		String jobID = form.getJobID();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setJobID(jobID);

		startrankList = service.doGetStartrankList(hdto);
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
		service.deleteStartrank(hdto);

		return "hive_listjob";
	}

	public String querySQL() {
		int systemID = form.getSystemIDValue();
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String model = form.getModel();

		String startDateStr = DateUtil.formatDate(startDate, "yyyyMMdd");
		String endDateStr = DateUtil.formatDate(endDate, "yyyyMMdd");

		if (startDate == null) {
			return "hive_listsql";
		}

		ColorosDTO dto = new ColorosDTO();
		dto.setStartDateStr(startDateStr);
		dto.setEndDateStr(endDateStr);
		dto.setModel(model);
		dto.setSystemID(systemID);

		hivesqlList = service.getHqlStartrank(dto);

		return "hive_listsql";
	}

	/**
	 * 结果表导出
	 *
	 * @return
	 */
	public String exportresult() {
		doqueryresult();

		if (null == startrankList || startrankList.size() <= 0) {
			errorinfo = "查询结果为空";
			return "hive_listjob";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "排名", "应用", "启动imei", "启动次数",
					"安装次数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (COSStartrankEntity entity : startrankList) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getPosition()),
						String.valueOf(entity.getAppname()),
						String.valueOf(entity.getStartimei()),
						String.valueOf(entity.getStartcnt()),
						String.valueOf(entity.getInstallcnt()) });
			}

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public ColorosForm getForm() {
		return form;
	}

	public void setForm(ColorosForm form) {
		this.form = form;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

	public List<COSStartrankEntity> getStartrankList() {
		return startrankList;
	}

	public void setStartrankList(List<COSStartrankEntity> startrankList) {
		this.startrankList = startrankList;
	}

	public List<HivejobEntity> getHivejobList() {
		return hivejobList;
	}

	public void setHivejobList(List<HivejobEntity> hivejobList) {
		this.hivejobList = hivejobList;
	}

	public ColorosService getService() {
		return service;
	}

	public void setService(ColorosService service) {
		this.service = service;
	}

	public HivejobService getJobservice() {
		return jobservice;
	}

	public void setJobservice(HivejobService jobservice) {
		this.jobservice = jobservice;
	}

	public List<HivesqlEntity> getHivesqlList() {
		return hivesqlList;
	}

	public void setHivesqlList(List<HivesqlEntity> hivesqlList) {
		this.hivesqlList = hivesqlList;
	}

	public List<COSStartrankEntity> getStartrankweekList() {
		return startrankweekList;
	}

	public void setStartrankweekList(List<COSStartrankEntity> startrankweekList) {
		this.startrankweekList = startrankweekList;
	}
}
