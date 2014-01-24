package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.LogDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.HivejobEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
import com.nearme.statistics.model.common.NewuseractiveEntity;
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
 * 新用户活跃
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-25
 */
public class NewuseractiveAction extends ActionSupport {
	private static final long serialVersionUID = -4656688923848692953L;

	public static final String TAB_RESULT = "dm_hive_new_user_active";// 结果表
	private final String TAG = "新用户活跃";
	private BaseForm form;
	
	private String errorinfo;//错误信息
	
	private List<NewuseractiveEntity> monthList;
	private List<NewuseractiveEntity> weekList;
	private List<HivejobEntity> hivejobList;// hive 任务列表
	private List<HivesqlEntity> hivesqlList;// hive sql列表
	private CommonService service;// 含hive和oracle连接
	private HivejobService jobservice;//hive job相关

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String sampleStartDate = DateUtil.getFirstDayOfXmonthAgo(7);
		;
		String sampleEndDate = DateUtil.getFirstDayOfXmonthAgo(6);
		String qudao = "1";// 1代表OPPO渠道

		form.setSampleStartTime(sampleStartDate);
		form.setSampleEndTime(sampleEndDate);
		form.setQudao(qudao);
		form.setSystemID(form.getSystemID());
		
		queryjob();

		return Action.SUCCESS;
	}

	public String query() {
		Date sampleStartDate = form.getSampleStartDate();
		Date sampleEndDate = form.getSampleEndDate();
		String qudao = form.getQudao();
		int systemID = form.getSystemIDValue();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (sampleStartDate == null || sampleEndDate == null
				|| sampleStartDate.after(sampleEndDate)) {
			return Action.SUCCESS;
		}

		BaseDTO dto = new BaseDTO();
		dto.setSampleStartDate(sampleStartDate);
		dto.setSampleEndDate(sampleEndDate);
		dto.setQudao(qudao);
		dto.setSystemID(systemID);

		HivejobDTO hdto = new HivejobDTO();
		hdto.setSystemID(systemID);
		hdto.setResulttable(TAB_RESULT);

		// 1.限定执行的任务数量
		long taskcnt = jobservice.getHivejobCnt(hdto);
		if (taskcnt < Constants.HIVE_TASK_LIMIT_LONGTIME) {
			// log记录查询
			LogDTO logdto = LogUtil.log(dto, TAG);

			// 2.产生2条任务记录
			String username = logdto.getUsername();
			Date starttime = new Date();
			String jobID = username + "_"
					+ DateUtil.formatDate(starttime, "yyyyMMddHHmmss") + "_"
					+ Math.round(Math.random() * 1000);
			String condition = "[新用户样本："
					+ DateUtil.formatDate(sampleStartDate, "yyyyMMdd") + " ~ "
					+ DateUtil.formatDate(sampleEndDate, "yyyyMMdd") + "] [渠道："
					+ qudao + "]";

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
						service.queryAndInsertNewuseractivemonthList(fbdto, fhdto);
						service.queryAndInsertNewuseractiveweekList(fbdto, fhdto);
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
	 * @param hdto
	 * @return
	 */
	private boolean isSameQuery(HivejobDTO hdto){
		List<HivejobEntity>  list = jobservice.getHivejobList(hdto);
		
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
	 * 结果表查询
	 * 
	 * @return
	 */
	public String queryresult() {
		doqueryresult();
		
		if (null == monthList || monthList.size() <= 0) {
			if (null == weekList || weekList.size() <= 0) {
				errorinfo = "查询结果为空";
			}
		}
		
		return "newuseractive_jobresult";
	}
	
    private void doqueryresult(){
    	String jobID = form.getJobID();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setJobID(jobID);

		monthList = service.doGetNewuseractivemonthList(hdto);
		weekList = service.doGetNewuseractiveweekList(hdto);
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
		service.deleteNewuseractive(hdto);

		return "hive_listjob";
	}
	
	public String querySQL() {
		Date sampleStartDate = form.getSampleStartDate();
		Date sampleEndDate = form.getSampleEndDate();
		String qudao = form.getQudao();
		int systemID = form.getSystemIDValue();

		if (systemID == 0) {
			return "hive_listsql";
		}
		if (sampleStartDate == null || sampleEndDate == null
				|| sampleStartDate.after(sampleEndDate)) {
			return "hive_listsql";
		}

		BaseDTO dto = new BaseDTO();
		dto.setSampleStartDate(sampleStartDate);
		dto.setSampleEndDate(sampleEndDate);
		dto.setQudao(qudao);
		dto.setSystemID(systemID);
		
		hivesqlList = service.getHqlNewuseractive(dto);
		
		return "hive_listsql";
	}

	/**
	 * 导出报表
	 * 
	 * @return
	 */
	public String exportresult() {
		doqueryresult();
		
		if (null == monthList || monthList.size() <= 0) {
			if (null == weekList || weekList.size() <= 0) {
				errorinfo = "查询结果为空";
				return "hive_listjob";
			}
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			exportmonth(eu, monthList);
			exportweek(eu, weekList);

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	/**
	 * 导出月数据
	 * 
	 * @param listmonth
	 * @throws Exception
	 */
	private void exportmonth(ExcelUtil eu, List<NewuseractiveEntity> listmonth)
			throws Exception {
		eu.createSheets(1, new String[] { TAG + "_月" });

		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(new String[] { "活跃天数", "启动imei", "下载次数", "启动次数" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (NewuseractiveEntity entity : listmonth) {
			eu.writeLine(new String[] { String.valueOf(entity.getActivedays()),
					String.valueOf(entity.getStartimei()),
					String.valueOf(entity.getDowncnt()),
					String.valueOf(entity.getStartcnt()) });
		}
	}

	/**
	 * 导出周数据
	 * 
	 * @param listweek
	 * @throws Exception
	 */
	private void exportweek(ExcelUtil eu, List<NewuseractiveEntity> listweek)
			throws Exception {
		eu.createSheets(1, new String[] { TAG + "_周" });

		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(new String[] { "活跃天数", "启动imei", "下载次数", "启动次数" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (NewuseractiveEntity entity : listweek) {
			eu.writeLine(new String[] { String.valueOf(entity.getActivedays()),
					String.valueOf(entity.getStartimei()),
					String.valueOf(entity.getDowncnt()),
					String.valueOf(entity.getStartcnt()) });
		}
	}

	public BaseForm getForm() {
		return form;
	}

	public void setForm(BaseForm form) {
		this.form = form;
	}

	public List<HivejobEntity> getHivejobList() {
		return hivejobList;
	}

	public void setHivejobList(List<HivejobEntity> hivejobList) {
		this.hivejobList = hivejobList;
	}

	public List<NewuseractiveEntity> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<NewuseractiveEntity> monthList) {
		this.monthList = monthList;
	}

	public List<NewuseractiveEntity> getWeekList() {
		return weekList;
	}

	public void setWeekList(List<NewuseractiveEntity> weekList) {
		this.weekList = weekList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
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
}
