package com.nearme.statistics.action.app.commonsetting;

import java.util.List;

import com.nearme.statistics.dto.app.common.EventDTO;
import com.nearme.statistics.form.app.common.EventForm;
import com.nearme.statistics.model.common.KVEventEntity;
import com.nearme.statistics.service.app.common.EventService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户行为编码
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-13
 */
public class EventAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EventForm form;
	private List<KVEventEntity> eventList;
	private EventService service;

	/**
	 * 查询KV事件列表
	 * @return
	 */
	public String init() {
		// 检查action传递过来的systemID合法性r
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		form.setSystemID(form.getSystemID());
		
		EventDTO dto = new EventDTO();
		dto.setSystemID(systemID);
		
		//查询KV事件列表
		this.eventList = this.service.listKVEvent(dto);
		
		LogUtil.log(dto, "自定义事件-列表");

		return Action.SUCCESS;
	}

	
	/**
	 * 修改自定义事件
	 * @return
	 */
	public String updateKVEvent(){
		int systemID = form.getSystemIDValue();
		int id = form.getId();
		String eventID = form.getEventID();
		String eventName = form.getEventName();
		String eventDesc = form.getEventDesc();
		if (systemID == 0 || null==eventID || "".equals(eventID.trim()) 
				|| null==eventName || "".equals(eventName.trim())) {
			return Action.SUCCESS;
		}

		EventDTO dto = new EventDTO();
		dto.setSystemID(systemID);
		dto.setId(id);
		dto.setEventID(eventID);
		dto.setEventName(eventName);
		dto.setEventDesc(eventDesc);
		service.updateKVEvent(dto);
		
		LogUtil.log(dto, "自定义事件-更新");
		
		return Action.SUCCESS;
	}
	
	/**
	 *添加自定义事件
	 * @return
	 */
	public String addKVEvent(){
		int systemID = form.getSystemIDValue();
		String eventID = form.getEventID();
		String eventName = form.getEventName();
		String eventDesc = form.getEventDesc();
		if (systemID == 0 || null==eventID || "".equals(eventID.trim()) 
				|| null==eventName || "".equals(eventName.trim())) {
			return Action.SUCCESS;
		}

		EventDTO dto = new EventDTO();
		dto.setSystemID(systemID);
		dto.setEventID(eventID);
		dto.setEventName(eventName);
		dto.setEventDesc(eventDesc);
		service.addKVEvent(dto);
		
		LogUtil.log(dto, "自定义事件-更新");
		
		return Action.SUCCESS;
	}
	
	/**
	 * 删除分组信息
	 * @return
	 */
	public String deleteKVEvent(){
		int systemID = form.getSystemIDValue();
		int id = form.getId();
		if (systemID == 0 || id<=0) {
			return Action.SUCCESS;
		}
		EventDTO dto = new EventDTO();
		dto.setSystemID(systemID);
		dto.setId(id);
		service.deleteKVEvent(dto);
		
		LogUtil.log(dto, "自定义事件-删除");
		
		return Action.SUCCESS;
	}
	
	public EventForm getForm() {
		return form;
	}

	public void setForm(EventForm form) {
		this.form = form;
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

}
