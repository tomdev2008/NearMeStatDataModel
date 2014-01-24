package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.HivejobDTO;
import com.nearme.statistics.dto.LogDTO;
import com.nearme.statistics.dto.app.common.ProductinfoDTO;
import com.nearme.statistics.form.app.common.ProductinfoForm;
import com.nearme.statistics.model.common.HivejobEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
import com.nearme.statistics.model.common.ProductrunEntity;
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
 * 单个资源运营
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-15
 */
public class ProductrunAction extends ActionSupport {
	private static final long serialVersionUID = -4656688923848692953L;

	public static final String TAB_RESULT = "dm_hive_productrun";// 结果表
	private final String TAG = "单个资源运营";

	private ProductinfoForm form;
	private String errorinfo;//错误信息

	private List<ProductrunEntity> moduleList;// 模块分布
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

		String startDate = DateUtil.getDateOfXdaysAgo(7);
		String endDate = DateUtil.getToday();
		String qudao = "1";// 1代表OPPO渠道

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setQudao(qudao);
		form.setSystemID(form.getSystemID());
		
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
		String productIDs = form.getProductIDs();
		String lidu = form.getLidu();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		ProductinfoDTO dto = new ProductinfoDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setQudao(qudao);
		dto.setSystemID(systemID);
		dto.setProductIDs(productIDs);
		dto.setLidu(lidu);

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
					+ qudao + "]" + "[产品ids ： " + productIDs + "]" + "[粒度 ： "
					+ lidu + "]";

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
				final ProductinfoDTO fbdto = dto;
				Thread thread = new Thread(new Runnable() {
					public void run() {
						service.queryAndInsertProductrunmoduleList(fbdto, fhdto);
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
		
		if (null == moduleList || moduleList.size() <= 0) {
			errorinfo = "查询结果为空";
		}

		return "productrun_jobresult";
	}
	
	private void doqueryresult() {
		String jobID = form.getJobID();
		String lidu = form.getLidu();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setJobID(jobID);
		hdto.setResulttable(lidu);

		moduleList = service.doGetProductrunmoduleList(hdto);
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
		service.deleteProductrun(hdto);

		return "hive_listjob";
	}
	
	public String querySQL() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String qudao = form.getQudao();
		int systemID = form.getSystemIDValue();
		String productIDs = form.getProductIDs();
		String lidu = form.getLidu();

		if (systemID == 0) {
			return "hive_listsql";
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "hive_listsql";
		}

		ProductinfoDTO dto = new ProductinfoDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setQudao(qudao);
		dto.setSystemID(systemID);
		dto.setProductIDs(productIDs);
		dto.setLidu(lidu);
		
        hivesqlList = service.getHqlProductrun(dto);
		
		return "hive_listsql";
	}

	/**
	 * 结果表导出
	 * 
	 * @return
	 */
	public String exportresult() {
        doqueryresult();
		
		if (null == moduleList || moduleList.size() <= 0) {
			errorinfo = "查询结果为空";
			return "hive_listjob";
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			exportmodule(eu, moduleList);

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	/**
	 * 导出<br>
	 * 模块分布数据
	 * 
	 * @param listmodule
	 * @throws Exception
	 */
	private void exportmodule(ExcelUtil eu, List<ProductrunEntity> listmodule)
			throws Exception {
		eu.createSheets(1, new String[] { TAG + "_模块" });

		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(new String[] { "模块名称", " 下载imei数量", "浏览imei次数", "直接下载次数",
				"详情下载次数" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (ProductrunEntity entity : listmodule) {
			eu.writeLine(new String[] { String.valueOf(entity.getName()),
					String.valueOf(entity.getDownimei()),
					String.valueOf(entity.getBrowseimei()),
					String.valueOf(entity.getDirectdown()),
					String.valueOf(entity.getDetaildown()) });
		}
	}

	public ProductinfoForm getForm() {
		return form;
	}

	public void setForm(ProductinfoForm form) {
		this.form = form;
	}

	public List<ProductrunEntity> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<ProductrunEntity> moduleList) {
		this.moduleList = moduleList;
	}

	public List<HivejobEntity> getHivejobList() {
		return hivejobList;
	}

	public void setHivejobList(List<HivejobEntity> hivejobList) {
		this.hivejobList = hivejobList;
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
