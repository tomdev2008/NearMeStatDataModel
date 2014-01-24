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
import com.nearme.statistics.model.common.FSNaturemodleChildEntity;
import com.nearme.statistics.model.common.FSNaturemodlenumEntity;
import com.nearme.statistics.model.common.FSOperatepointChildEntity;
import com.nearme.statistics.model.common.FSOperatepointEntity;
import com.nearme.statistics.model.common.FSResourcetypeChildEntity;
import com.nearme.statistics.model.common.FSResourcetypeEntity;
import com.nearme.statistics.model.common.FSTopresourceEntity;
import com.nearme.statistics.model.common.FSUpdatenumEntity;
import com.nearme.statistics.model.common.HivejobEntity;
import com.nearme.statistics.model.common.HivesqlEntity;
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
 * 流量结构分析
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-10-14
 */
public class FlowstructAction extends ActionSupport {
	private static final long serialVersionUID = -4656688923848692953L;

	public static final String TAB_RESULT = "dm_hive_flow_struct";// 结果表
	private final String TAG = "流量结构分析";
	private BaseForm form;
	private String errorinfo;// 错误信息
	private List<FSUpdatenumEntity> updatenumList;// 更新数量总体及分析
	private List<FSUpdatenumEntity> updatenumTotal;// 更新总体数据
	private List<FSResourcetypeEntity> resourcetypeList;// 资源类别总体及分析
	private List<FSTopresourceEntity> topresourceList;// top资源总体及分析
	private List<FSTopresourceEntity> topresourceTotal;// top资源总体数据
	private List<FSNaturemodlenumEntity> naturemodlenumList;// 自然模块数量总体及分析
	private List<FSOperatepointEntity> operatepointList;// 运营点总体及分析

	private final String WEIDU_UPDATE_NUM = "UPDATE_NUM";// 更新数量总体及分析
	private final String WEIDU_RESOURCE_TYPE = "RESOURCE_TYPE";// 资源类别总体及分析
	private final String WEIDU_TOP_RESOURCE = "TOP_RESOURCE";// top资源总体及分析
	private final String WEIDU_NATURE_MODLE_NUM = "NATURE_MODLE_NUM";// 自然模块数量总体及分析
	private final String WEIDU_OPERATE_POINT = "OPERATE_POINT";// 运营点总体及分析

	private List<HivejobEntity> hivejobList;// hive 任务列表
	private List<HivesqlEntity> hivesqlList;// hive sql列表
	private CommonService service;// 含hive和oracle连接
	private HivejobService jobservice;// hive job相关

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
		String lidu = "DAILY";
		String qudao = "1";// 1代表OPPO渠道

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setQudao(qudao);
		form.setLidu(lidu);
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
		String lidu = form.getLidu();
		final String weidu = form.getWeidu();

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
		// 模块 还是 运营点
		dto.setType(Constants.getPackageGroupName(weidu));

		HivejobDTO hdto = new HivejobDTO();
		hdto.setSystemID(systemID);
		hdto.setResulttable(TAB_RESULT);
		hdto.setWeidu(weidu);
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
					+ qudao + "] [维度：" + weidu + "]" + "[粒度：" + lidu + "]";

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
						if (null != weidu) {
							if (Constants.WEIDU_UPDATE_NUM
									.equalsIgnoreCase(weidu)) {
								service.queryAndInsertFSUpdatenum(fbdto, fhdto);

								// 资源类别
							} else if (Constants.WEIDU_RESOURCE_TYPE
									.equalsIgnoreCase(weidu)) {
								service.queryAndInsertFSResourcetype(fbdto,
										fhdto);

							} else if (Constants.WEIDU_TOP_RESOURCE
									.equalsIgnoreCase(weidu)) {
								service.queryAndInsertFSTopresource(fbdto,
										fhdto);

								// 自然模块
							} else if (Constants.WEIDU_NATURE_MODLE_NUM
									.equalsIgnoreCase(weidu)) {
								service.queryAndInsertFSNaturemodlenum(fbdto,
										fhdto);

								// 运营点
							} else if (Constants.WEIDU_OPERATE_POINT
									.equalsIgnoreCase(weidu)) {
								service.queryAndInsertFSNaturemodlenum(fbdto,
										fhdto);
							}

							// 5.完成任务 修改状态
							jobservice.finishHivejob(fhdto);
						}
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

		String weidu = form.getWeidu();

		if (WEIDU_UPDATE_NUM.equalsIgnoreCase(weidu)) {
			return "flowstruct_updatenum";
		} else if (WEIDU_RESOURCE_TYPE.equalsIgnoreCase(weidu)) {
			return "flowstruct_resourcetype";
		} else if (WEIDU_TOP_RESOURCE.equalsIgnoreCase(weidu)) {
			return "flowstruct_topresource";
		} else if (WEIDU_NATURE_MODLE_NUM.equalsIgnoreCase(weidu)) {
			return "flowstruct_naturemodlenum";
		} else if (WEIDU_OPERATE_POINT.equalsIgnoreCase(weidu)) {
			return "flowstruct_operatepoint";
		} else {
			return Action.SUCCESS;
		}
	}

	private void doqueryresult() {
		String jobID = form.getJobID();
		String weidu = form.getWeidu();
		String lidu = form.getLidu();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setJobID(jobID);
		hdto.setWeidu(weidu);
		hdto.setResulttable(lidu);

		if (WEIDU_UPDATE_NUM.equalsIgnoreCase(weidu)) {
			updatenumTotal = service.doGetFSUpdatenumTotal(hdto);
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.NEARME_SESSION_REPROT_DATA + jobID,
					updatenumTotal);
			updatenumList = service.doGetFSUpdatenum(hdto);
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.NEARME_SESSION_REPROT_DATA_2 + jobID,
					updatenumList);

			if (null == updatenumList || updatenumList.size() <= 0) {
				errorinfo = "查询结果为空";
			}

			// 资源类别
		} else if (WEIDU_RESOURCE_TYPE.equalsIgnoreCase(weidu)) {
			resourcetypeList = service.doGetFSResourcetype(hdto);
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.NEARME_SESSION_REPROT_DATA + jobID,
					resourcetypeList);

			if (null == resourcetypeList || resourcetypeList.size() <= 0) {
				errorinfo = "查询结果为空";
			}
		} else if (WEIDU_TOP_RESOURCE.equalsIgnoreCase(weidu)) {
			topresourceTotal = service.doGetFSTopresourceTotal(hdto);
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.NEARME_SESSION_REPROT_DATA + jobID,
					topresourceTotal);
			topresourceList = service.doGetFSTopresource(hdto);
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.NEARME_SESSION_REPROT_DATA_2 + jobID,
					topresourceList);

			if (null == topresourceList || topresourceList.size() <= 0) {
				errorinfo = "查询结果为空";
			}
			// 自然模块
		} else if (WEIDU_NATURE_MODLE_NUM.equalsIgnoreCase(weidu)) {
			naturemodlenumList = service.doGetFSNaturemodlenum(hdto);
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.NEARME_SESSION_REPROT_DATA + jobID,
					naturemodlenumList);

			if (null == naturemodlenumList || naturemodlenumList.size() <= 0) {
				errorinfo = "查询结果为空";
			}
			// 运营点
		} else if (WEIDU_OPERATE_POINT.equalsIgnoreCase(weidu)) {
			naturemodlenumList = service.doGetFSNaturemodlenum(hdto);
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.NEARME_SESSION_REPROT_DATA + jobID,
					naturemodlenumList);

			if (null == naturemodlenumList || naturemodlenumList.size() <= 0) {
				errorinfo = "查询结果为空";
			}
		}
	}

	/**
	 * 删除结果<br>
	 * 
	 * @return
	 */
	public String deleteresult() {
		String jobID = form.getJobID();
		String weidu = form.getWeidu();

		HivejobDTO hdto = new HivejobDTO();
		hdto.setJobID(jobID);

		jobservice.deleteHivejob(hdto);
		if (WEIDU_UPDATE_NUM.endsWith(weidu)) {
			service.deleteFSUpdatenum(hdto);
		} else if (WEIDU_TOP_RESOURCE.endsWith(weidu)) {
			service.deleteFSTopresource(hdto);
		} else if (WEIDU_RESOURCE_TYPE.endsWith(weidu)) {
			service.deleteFSResourcetype(hdto);
		} else if (WEIDU_OPERATE_POINT.endsWith(weidu)) {
			service.deleteFSNaturemodlenum(hdto);
		} else if (WEIDU_NATURE_MODLE_NUM.endsWith(weidu)) {
			service.deleteFSNaturemodlenum(hdto);
		} 

		return "hive_listjob";
	}
	
	/**
	 * 查询sql
	 * @return
	 */
	public String querySQL() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String qudao = form.getQudao();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		final String weidu = form.getWeidu();

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
		// 模块 还是 运营点
		dto.setType(Constants.getPackageGroupName(weidu));

		HivejobDTO hdto = new HivejobDTO();
		hdto.setSystemID(systemID);
		hdto.setResulttable(TAB_RESULT);
		hdto.setWeidu(weidu);
		hdto.setLidu(lidu);
		
		if (WEIDU_UPDATE_NUM.equals(weidu)) {
			hivesqlList = service.getHqlFSUpdatenum(dto);
		} else if (WEIDU_TOP_RESOURCE.equals(weidu)) {
			hivesqlList = service.getHqlFSTopresource(dto);
		} else if (WEIDU_RESOURCE_TYPE.equals(weidu)) {
			hivesqlList = service.getHqlFSResourcetype(dto);
		} else if (WEIDU_OPERATE_POINT.equals(weidu)) {
			hivesqlList = service.getHqlFSNaturemodle(dto);
		} else if (WEIDU_NATURE_MODLE_NUM.equals(weidu)) {
			hivesqlList = service.getHqlFSNaturemodle(dto);
		}
		
		return "hive_listsql";
	}

	/**
	 * 结果表导出
	 * 
	 * @return
	 */
	public String exportresult() {
		doqueryresult();

		String weidu = form.getWeidu();

		if (WEIDU_UPDATE_NUM.equalsIgnoreCase(weidu)) {
			if (null == updatenumList || updatenumList.size() <= 0) {
				return "hive_listjob";
			}
			exportUpdatenum(updatenumTotal, updatenumList);
		} else if (WEIDU_RESOURCE_TYPE.equalsIgnoreCase(weidu)) {
			if (null == resourcetypeList || resourcetypeList.size() <= 0) {
				return "hive_listjob";
			}
			exportResourcetype(resourcetypeList);
		} else if (WEIDU_TOP_RESOURCE.equalsIgnoreCase(weidu)) {
			if (null == topresourceList || topresourceList.size() <= 0) {
				return "hive_listjob";
			}
			exportTopresource(topresourceTotal, topresourceList);
		} else if (WEIDU_NATURE_MODLE_NUM.equalsIgnoreCase(weidu)) {
			if (null == naturemodlenumList || naturemodlenumList.size() <= 0) {
				return "hive_listjob";
			}
			exportNaturemodlenum(naturemodlenumList);
		} else if (WEIDU_OPERATE_POINT.equalsIgnoreCase(weidu)) {
			if (null == naturemodlenumList || naturemodlenumList.size() <= 0) {
				return "hive_listjob";
			}
			exportNaturemodlenum(naturemodlenumList);
		}

		return Action.SUCCESS;
	}

	/**
	 * 导出-更新数量总体及分析
	 * 
	 * @param listtotal
	 * @param list
	 */
	private void exportUpdatenum(List<FSUpdatenumEntity> listtotal,
			List<FSUpdatenumEntity> list) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 总体数据
			writeUpdatenumTotal(eu, listtotal);

			// 明细数据
			writeUpdatenumDetail(eu, list);

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeUpdatenumTotal(ExcelUtil eu,
			List<FSUpdatenumEntity> listtotal) {
		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(new String[] { "总体数据", "下载IMEI", "下载次数", "更新次数",
				"更新imei数", "更新资源数" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (FSUpdatenumEntity entity : listtotal) {
			eu.writeLine(new String[] { "",
					String.valueOf(entity.getDownimei()),
					String.valueOf(entity.getDowncnt()),
					String.valueOf(entity.getUpdatecnt()),
					String.valueOf(entity.getUpdateimei()),
					String.valueOf(entity.getUpdateres()) });
		}
	}

	private void writeUpdatenumDetail(ExcelUtil eu, List<FSUpdatenumEntity> list) {
		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(new String[] { "时间", "下载IMEI", "下载次数", "更新次数", "更新imei数",
				"更新资源数" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (FSUpdatenumEntity entity : list) {
			eu.writeLine(new String[] { 
					DateUtil.formatDate2Short(entity.getStatDate()),
					String.valueOf(entity.getDownimei()),
					String.valueOf(entity.getDowncnt()),
					String.valueOf(entity.getUpdatecnt()),
					String.valueOf(entity.getUpdateimei()),
					String.valueOf(entity.getUpdateres()) });
		}
	}

	/**
	 * 导出-资源类别总体及分析
	 * 
	 * @param list
	 */
	private void exportResourcetype(List<FSResourcetypeEntity> list) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 1.总体数据
			writeResourcetypeTotal(eu, list);

			// 2.明细数据
			writeResourcetypeDetail(eu, list);

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeResourcetypeDetail(ExcelUtil eu,
			List<FSResourcetypeEntity> list) {
		// 拼出列名称
		FSResourcetypeEntity item = list.get(0);
		List<FSResourcetypeChildEntity> clist = item.getList();
		int size = clist.size();
		String[] colnames = new String[size * 2 + 3];
		colnames[0] = "时间";
		colnames[1] = "总下载次数";
		colnames[2] = "总下载imei";
		for (int i = 0; i < clist.size(); i++) {
			colnames[i * 2 + 3] = clist.get(i).getTypename() + "下载次数";
			colnames[i * 2 + 4] = clist.get(i).getTypename() + "下载资源数";
		}

		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(colnames);

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());

		for (int i = 1; i < list.size(); i++) {
			FSResourcetypeEntity entity = list.get(i);
			String[] strs = new String[200];
			int j = 0;

			Date statDate = entity.getStatDate();
			long totaldown = entity.getTotaldown();
			List<FSResourcetypeChildEntity> childlist = entity.getList();

			strs[j++] = DateUtil.formatDate(statDate, "yyyy-MM-dd");
			strs[j++] = String.valueOf(totaldown);

			for (FSResourcetypeChildEntity childEntity : childlist) {
				long downcnt = childEntity.getDowncnt();
				long downapp = childEntity.getDownapp();

				strs[j++] = String.valueOf(downcnt);
				strs[j++] = String.valueOf(downapp);
			}

			eu.writeLine(strs);
		}
	}

	private void writeResourcetypeTotal(ExcelUtil eu,
			List<FSResourcetypeEntity> list) {
		// 拼出列名称
		FSResourcetypeEntity item = list.get(0);
		List<FSResourcetypeChildEntity> clist = item.getList();
		int size = clist.size();
		String[] colnames = new String[size * 2 + 3];
		colnames[0] = "总体数据";
		colnames[1] = "总下载次数";
		colnames[2] = "总下载imei";
		for (int i = 0; i < clist.size(); i++) {
			colnames[i * 2 + 3] = clist.get(i).getTypename() + "下载次数";
			colnames[i * 2 + 4] = clist.get(i).getTypename() + "下载资源数";
		}

		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu.writeLine(colnames);

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		if (null != list) {
			FSResourcetypeEntity entity = list.get(0);
			String[] strs = new String[200];
			int i = 0;

			Date statDate = entity.getStatDate();
			long totaldown = entity.getTotaldown();
			List<FSResourcetypeChildEntity> childlist = entity.getList();

			strs[i++] = DateUtil.formatDate(statDate, "yyyy-MM-dd");
			strs[i++] = String.valueOf(totaldown);

			for (FSResourcetypeChildEntity childEntity : childlist) {
				long downcnt = childEntity.getDowncnt();
				long downapp = childEntity.getDownapp();

				strs[i++] = String.valueOf(downcnt);
				strs[i++] = String.valueOf(downapp);
			}
		}
	}

	/**
	 * 导出-top资源总体及分析
	 * 
	 * @param list
	 */
	private void exportTopresource(List<FSTopresourceEntity> listtotal,
			List<FSTopresourceEntity> list) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 总体数据
			writeTopresourceTotal(eu, listtotal);

			// 明细数据
			writeTopresourceDetail(eu, list);

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入excel总体数据
	 * 
	 * @param eu
	 * @param listtotal
	 */
	private void writeTopresourceTotal(ExcelUtil eu,
			List<FSTopresourceEntity> listtotal) {
		eu.setFont(ExcelUtil.getTitleFont());
		eu
				.writeLine(new String[] { "总体数据", "总下载次数", "top50资源下载次数",
						"top100资源下载次数", "top200资源下载次数", "top300资源下载次数",
						"top500资源下载次数" });
		eu.setFont(ExcelUtil.getBodyFont());
		for (FSTopresourceEntity entity : listtotal) {
			eu.writeLine(new String[] { "",
					String.valueOf(entity.getTotaldown()),
					String.valueOf(entity.getTop50()),
					String.valueOf(entity.getTop100()),
					String.valueOf(entity.getTop200()),
					String.valueOf(entity.getTop300()),
					String.valueOf(entity.getTop500()) });
		}
	}

	/**
	 * 写入excel明细数据
	 * 
	 * @param eu
	 * @param list
	 */
	private void writeTopresourceDetail(ExcelUtil eu,
			List<FSTopresourceEntity> list) {
		// 设置标题
		eu.setFont(ExcelUtil.getTitleFont());
		eu
				.writeLine(new String[] { "时间", "总下载次数", "top50资源下载次数",
						"top100资源下载次数", "top200资源下载次数", "top300资源下载次数",
						"top500资源下载次数" });

		// 设置信息体
		eu.setFont(ExcelUtil.getBodyFont());
		for (FSTopresourceEntity entity : list) {
			eu.writeLine(new String[] { 
					DateUtil.formatDate2Short(entity.getStatDate()),
					String.valueOf(entity.getTotaldown()),
					String.valueOf(entity.getTop50()),
					String.valueOf(entity.getTop100()),
					String.valueOf(entity.getTop200()),
					String.valueOf(entity.getTop300()),
					String.valueOf(entity.getTop500()) });
		}
	}

	/**
	 * 导出-自然模块数量总体及分析
	 * 
	 * @param list
	 */
	private void exportNaturemodlenum(List<FSNaturemodlenumEntity> list) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 拼出列名称
			FSNaturemodlenumEntity item = list.get(0);
			List<FSNaturemodleChildEntity> clist = item.getList();
			int size = clist.size();
			String[] colnames = new String[size * 2 + 3];
			colnames[0] = "时间";
			colnames[1] = "下载imei";
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

			for (FSNaturemodlenumEntity entity : list) {
				String[] strs = new String[200];
				int i = 0;

				Date statDate = entity.getStatDate();
				long downimei = entity.getDownimei();
				long totaldown = entity.getTotaldown();
				List<FSNaturemodleChildEntity> childlist = entity.getList();

				strs[i++] = DateUtil.formatDate(statDate, "yyyy-MM-dd");
				strs[i++] = String.valueOf(downimei);
				strs[i++] = String.valueOf(totaldown);

				for (FSNaturemodleChildEntity childEntity : childlist) {
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

	public static void main(String[] args) {
		int i = 10;
		System.out.println(i % 2);
	}

	/**
	 * 导出-运营点总体及分析
	 * 
	 * @param list
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private void exportOperatepoint(List<FSOperatepointEntity> list) {
		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 拼出列名称
			FSOperatepointEntity item = list.get(0);
			List<FSOperatepointChildEntity> clist = item.getList();
			int size = clist.size();
			String[] colnames = new String[size * 2 + 3];
			colnames[0] = "时间";
			colnames[1] = "下载imei";
			colnames[2] = "总下载次数";
			for (int i = 0; i < clist.size(); i++) {
				colnames[i * 2 + 3] = clist.get(i).getPoint() + "下载次数";
				colnames[i * 2 + 4] = clist.get(i).getPoint() + "下载imei数";
			}

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(colnames);

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());

			for (FSOperatepointEntity entity : list) {
				String[] strs = new String[200];
				int i = 0;

				Date statDate = entity.getStatDate();
				long downimei = entity.getDownimei();
				long totaldown = entity.getTotaldown();
				List<FSOperatepointChildEntity> childlist = entity.getList();

				strs[i++] = DateUtil.formatDate(statDate, "yyyy-MM-dd");
				strs[i++] = String.valueOf(downimei);
				strs[i++] = String.valueOf(totaldown);

				for (FSOperatepointChildEntity childEntity : childlist) {
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

	public List<FSUpdatenumEntity> getUpdatenumList() {
		return updatenumList;
	}

	public void setUpdatenumList(List<FSUpdatenumEntity> updatenumList) {
		this.updatenumList = updatenumList;
	}

	public List<FSResourcetypeEntity> getResourcetypeList() {
		return resourcetypeList;
	}

	public void setResourcetypeList(List<FSResourcetypeEntity> resourcetypeList) {
		this.resourcetypeList = resourcetypeList;
	}

	public List<FSTopresourceEntity> getTopresourceList() {
		return topresourceList;
	}

	public void setTopresourceList(List<FSTopresourceEntity> topresourceList) {
		this.topresourceList = topresourceList;
	}

	public List<FSNaturemodlenumEntity> getNaturemodlenumList() {
		return naturemodlenumList;
	}

	public void setNaturemodlenumList(
			List<FSNaturemodlenumEntity> naturemodlenumList) {
		this.naturemodlenumList = naturemodlenumList;
	}

	public List<FSOperatepointEntity> getOperatepointList() {
		return operatepointList;
	}

	public void setOperatepointList(List<FSOperatepointEntity> operatepointList) {
		this.operatepointList = operatepointList;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<FSUpdatenumEntity> getUpdatenumTotal() {
		return updatenumTotal;
	}

	public void setUpdatenumTotal(List<FSUpdatenumEntity> updatenumTotal) {
		this.updatenumTotal = updatenumTotal;
	}

	public List<FSTopresourceEntity> getTopresourceTotal() {
		return topresourceTotal;
	}

	public void setTopresourceTotal(List<FSTopresourceEntity> topresourceTotal) {
		this.topresourceTotal = topresourceTotal;
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
