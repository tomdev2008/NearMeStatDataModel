package com.nearme.statistics.action.app.common;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.common.EventDTO;
import com.nearme.statistics.form.app.common.EventForm;
import com.nearme.statistics.model.common.KVEventDetailEntity;
import com.nearme.statistics.model.common.KVEventEntity;
import com.nearme.statistics.model.common.KVEventIndexEntity;
import com.nearme.statistics.service.app.common.EventService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 自定义事件
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-10-23
 */
public class EventAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private EventForm form;
	private List<KVEventIndexEntity> indexList;
	private List<KVEventDetailEntity> keyList;
	private List<KVEventDetailEntity> detailList;
	private List<KVEventDetailEntity> sumList;
	private List<KVEventEntity> eventList;
	private EventService service;

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
		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());

		EventDTO dto = new EventDTO();
		//此事件设置用于查询日志记录
		dto.setStartDate(DateUtil.parseDate(startDate,"yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate,"yyyy-MM-dd"));
		
		dto.setStartDateInt(Integer.parseInt(startDate.replaceAll("-", "")));
		dto.setEndDateInt(Integer.parseInt(endDate.replaceAll("-", "")));
		dto.setSystemID(form.getSystemIDValue());
		
		//初始化默认事件
		this.eventList = this.service.listKVEvent(dto);
		String eventID = this.eventList!=null&&this.eventList.size()>0 ? this.eventList.get(0).getEventID() : "";
		form.setEventID(eventID);
		dto.setEventID(eventID);
		
		//初始化默认EventKey
		this.keyList=this.service.listKVEventKey(dto);
		String eventKey = this.keyList!=null&&this.keyList.size()>0 ? this.keyList.get(0).getEventKey() : "";
		form.setEventKey(eventKey);
		dto.setEventKey(eventKey);
		
		//查询每天事件统计列表
		this.indexList = service.listKVEventIndex(dto);
		ServletActionContext.getRequest().getSession().setAttribute(Constants.NEARME_SESSION_REPROT_DATA, indexList);
		
		//查询事件统计详情
		this.sumList = service.listKVEventDetailSum(dto);
		this.setPercent(sumList);
		
		LogUtil.log(dto, "自定义事件");//log记录查询

		return Action.SUCCESS;
	}
	
	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String eventID = form.getEventID();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		EventDTO dto = new EventDTO();
		//此事件设置用于查询日志记录
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		
		dto.setStartDateInt(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		dto.setEndDateInt(Integer.parseInt(DateUtil.formatDate(endDate, "yyyyMMdd")));
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setEventID(eventID);
		//查询KV事件统计列表
		this.indexList = service.listKVEventIndex(dto);
		ServletActionContext.getRequest().getSession().setAttribute(Constants.NEARME_SESSION_REPROT_DATA, indexList);
		
		//初始化默认EventKey
		this.keyList=this.service.listKVEventKey(dto);
		String eventKey = this.keyList!=null&&this.keyList.size()>0 ? this.keyList.get(0).getEventKey() : "";
		form.setEventKey(eventKey);
		dto.setEventKey(eventKey);
		
		//查询事件统计详情
		this.sumList = service.listKVEventDetailSum(dto);
		this.setPercent(sumList);

		//初始化
		this.eventList = this.service.listKVEvent(dto);
		
		LogUtil.log(dto, "自定义事件");//log记录查询
		
		return Action.SUCCESS;
	}
	
	/**
	 * 设置占比
	 * @param list
	 */
	private void setPercent(List<KVEventDetailEntity> list){
		List<KVEventDetailEntity> tmplist = new ArrayList<KVEventDetailEntity>();
		if(list!=null && list.size()>0){
			int count = 0;
			for( int i=0,size=list.size(); i<size; i++){
				count+=list.get(i).getEventCounts();
			}
			if(count<=0){
				return;
			}
			KVEventDetailEntity entity = null;
			NumberFormat nf = NumberFormat.getNumberInstance();
		    nf.setMaximumFractionDigits(2);
			for( int i=0,size=list.size(); i<size; i++){
				entity = list.get(i);
				entity.setEventCountsPercent(nf.format(entity.getEventCounts()*1.0/count*100.0));
				tmplist.add(entity);
			}
			list = tmplist;
		}
	}
	
	/**
	 * 事件在查询时间段内的发生总计
	 * @return
	 */
	public String queryDetailSum() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String eventID = form.getEventID();
		String eventKey = form.getEventKey();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		EventDTO dto = new EventDTO();
		//此事件设置用于查询日志记录
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		
		dto.setStartDateInt(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		dto.setEndDateInt(Integer.parseInt(DateUtil.formatDate(endDate, "yyyyMMdd")));
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setEventID(eventID);
		dto.setEventKey(eventKey);
		
		//查询事件统计详情
		this.sumList = service.listKVEventDetailSum(dto);
		this.setPercent(sumList);

		LogUtil.log(dto, "自定义事件");//log记录查询
		
		return "event_detail_sum";
	}
	
	/**
	 * 事件中某一个EventKey在查询时间内每天的发生次数
	 * @return
	 */
	public String queryDetail() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String eventID = form.getEventID();
		String eventKey = form.getEventKey();
		String eventValue = form.getEventValue();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		EventDTO dto = new EventDTO();
		//此事件设置用于查询日志记录
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		
		dto.setStartDateInt(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		dto.setEndDateInt(Integer.parseInt(DateUtil.formatDate(endDate, "yyyyMMdd")));
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setEventID(eventID);
		dto.setEventKey(eventKey);
		dto.setEventValue(eventValue);
		
		//查询事件统计详情
		this.detailList = service.listKVEventDetail(dto);
		this.setPercent(detailList);

		LogUtil.log(dto, "自定义事件");//log记录查询
		
		return "event_detail";
	}

	/**
	 * 导出报表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<KVEventIndexEntity> list = (List<KVEventIndexEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("自定义事件_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "自定义事件" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "启动IMEI数", "启动次数",	"事件发生IMEI数", "事件发生次数" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				KVEventIndexEntity entity = list.get(i);
				eu.writeLine(new String[] {
						String.valueOf(entity.getStatdate()),
						String.valueOf(entity.getStartImeis()),
						String.valueOf(entity.getStartCounts()),
						String.valueOf(entity.getEventImeis()),
						String.valueOf(entity.getEventCounts())});
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public EventForm getForm() {
		return form;
	}

	public void setForm(EventForm form) {
		this.form = form;
	}

	public List<KVEventIndexEntity> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<KVEventIndexEntity> indexList) {
		this.indexList = indexList;
	}

	public List<KVEventDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<KVEventDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public List<KVEventEntity> getEventList() {
		return eventList;
	}

	public void setEventList(List<KVEventEntity> eventList) {
		this.eventList = eventList;
	}

	public List<KVEventDetailEntity> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<KVEventDetailEntity> keyList) {
		this.keyList = keyList;
	}

	public List<KVEventDetailEntity> getSumList() {
		return sumList;
	}

	public void setSumList(List<KVEventDetailEntity> sumList) {
		this.sumList = sumList;
	}

	public EventService getService() {
		return service;
	}

	public void setService(EventService service) {
		this.service = service;
	}

}
