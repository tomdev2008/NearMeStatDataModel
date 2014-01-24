package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.common.EventFlowDTO;
import com.nearme.statistics.form.app.common.EventFlowForm;
import com.nearme.statistics.model.common.KVEventEntity;
import com.nearme.statistics.model.common.KVEventFlowEntity;
import com.nearme.statistics.model.sys.systemTable.AppVersionEntity;
import com.nearme.statistics.service.app.common.EventService;
import com.nearme.statistics.tags.AppVersionSelectTag;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 事件转化率
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-10-23
 */
public class EventConversionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private EventFlowForm form;
	private List<KVEventFlowEntity> eventFlowList;
	private List<KVEventFlowEntity> flowList;
	private List<KVEventEntity> eventList;
	private EventService service;

	public String init() {
		return this.query();
	}
	
	public String query() {
		// 检查action传递过来的systemID合法性r
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			form.setTips("参数错误!");
			return "tips";
		}
		int systemID = form.getSystemIDValue();
		if (systemID <= 0) {
			form.setTips("参数错误!");
			return "tips";
		}
		
		EventFlowDTO dto = new EventFlowDTO();
		dto.setSystemID(systemID);
		//时间默认为上周周一
		dto.setStatDate(Integer.parseInt(DateUtil.getMonday(DateUtil.getTimeOfXdaysAgo(7),"yyyyMMdd")));
		//粒度默认设置为"周"
		dto.setLidu(Constants.WEEKLY);
		//如果版本为空,则默认取版本公共列表中的第一个
		String appVersion = form.getAppVersion();
		if(appVersion==null || "".equals(appVersion.trim())){
			List<AppVersionEntity>  versionList = AppVersionSelectTag.getVersionList(systemID);
			if(versionList!=null && versionList.size()>0){
				appVersion = versionList.get(0).getAppVersion();
				form.setAppVersion(appVersion);
			}
		}
		dto.setAppVersion(form.getAppVersion());
		
		//查询KV事件流列表
		this.eventFlowList = this.service.listConversionAllDetail(dto);
		
		LogUtil.log(dto, "事件转化率-列表");

		return Action.SUCCESS;
	}
	
	public String detail() {
		int systemID = form.getSystemIDValue();
		String eventFlowName = form.getEventFlowName();
		if(systemID<=0 || eventFlowName==null ||"".equals(eventFlowName.trim())){
			form.setTips("参数错误!");
			return "tips";
		}
		//如果版本为空,则默认取版本公共列表中的第一个
		String appVersion = form.getAppVersion();
		if(appVersion==null || "".equals(appVersion.trim())){
			List<AppVersionEntity>  versionList = AppVersionSelectTag.getVersionList(systemID);
			if(versionList!=null && versionList.size()>0){
				appVersion = versionList.get(0).getAppVersion();
				form.setAppVersion(appVersion);
			}
		}
		//如果粒度为空,则默认取"日"
		String lidu = form.getLidu();
		if(lidu==null || "".equals(lidu.trim())){
			lidu = Constants.DAILY;
			form.setLidu(lidu);
		}
		//如果时间为空,则默认取昨天
		Date startDate = form.getStartDate();
		if(startDate==null){
			startDate = DateUtil.getTimeOfXdaysAgo(1);
			form.setStartTime(DateUtil.formatDate(startDate,"yyyy-MM-dd"));
		}
		
		EventFlowDTO dto = new EventFlowDTO();
		dto.setSystemID(systemID);
		dto.setEventFlowName(eventFlowName);
		dto.setAppVersion(appVersion);
		dto.setLidu(lidu);
		if(Constants.MONTHLY.equals(dto.getLidu())){
			dto.setStatDate(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMM")));
		}else if(Constants.WEEKLY.equals(dto.getLidu())){
			dto.setStatDate(Integer.parseInt(DateUtil.getMonday(startDate, "yyyyMMdd")));
		}else{
			dto.setStatDate(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		}
		//查询KV事件流详情
		this.eventFlowList = this.service.listConversionDetail(dto);
		
		
		//初始化事件流下拉列表数据
		this.flowList = this.service.listKVEventFlow(dto);
		
		LogUtil.log(dto, "事件转化率-详情");

		return "detail";
	}
	
	public String period() {
		int systemID = form.getSystemIDValue();
		String eventFlowName = form.getEventFlowName();
		if(systemID<=0 || eventFlowName==null ||"".equals(eventFlowName.trim())){
			form.setTips("参数错误!");
			return "tips";
		}
		//如果版本为空,则默认取版本公共列表中的第一个
		String appVersion = form.getAppVersion();
		//如果粒度为空,则默认取"日"
		String lidu = form.getLidu();
		//如果时间为空,则默认取昨天
		Date startDate = form.getStartDate();
		int eventFlowStep = form.getEventFlowStep();
		
		EventFlowDTO dto = new EventFlowDTO();
		dto.setSystemID(systemID);
		dto.setEventFlowName(eventFlowName);
		dto.setAppVersion(appVersion);
		dto.setLidu(lidu);
		dto.setEventFlowStep(eventFlowStep);
		
		if(Constants.MONTHLY.equals(dto.getLidu())){
			dto.setStatDate(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMM")));
		}else if(Constants.WEEKLY.equals(dto.getLidu())){
			dto.setStatDate(Integer.parseInt(DateUtil.getMonday(startDate, "yyyyMMdd")));
		}else{
			dto.setStatDate(Integer.parseInt(DateUtil.formatDate(startDate, "yyyyMMdd")));
		}
		//查询KV事件流详情
		this.eventFlowList = this.service.listConversionDetailPeriod(dto);
		if(this.eventFlowList!=null && this.eventFlowList.size()>0){
			form.setEventName(eventFlowList.get(0).getFinalEventName());
			form.setPrevEventName(eventFlowList.get(0).getPrevEventName());
		}
		
		LogUtil.log(dto, "事件转化率-趋势");

		return "period";
	}

	public EventFlowForm getForm() {
		return form;
	}

	public void setForm(EventFlowForm form) {
		this.form = form;
	}

	public List<KVEventFlowEntity> getEventFlowList() {
		return eventFlowList;
	}

	public void setEventFlowList(List<KVEventFlowEntity> eventFlowList) {
		this.eventFlowList = eventFlowList;
	}

	public List<KVEventEntity> getEventList() {
		return eventList;
	}

	public void setEventList(List<KVEventEntity> eventList) {
		this.eventList = eventList;
	}

	public EventService getService() {
		return service;
	}

	public void setService(EventService service) {
		this.service = service;
	}
	public List<KVEventFlowEntity> getFlowList() {
		return flowList;
	}
	public void setFlowList(List<KVEventFlowEntity> flowList) {
		this.flowList = flowList;
	}

}
