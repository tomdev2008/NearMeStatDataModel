package com.nearme.statistics.action.app.commonsetting;

import java.util.ArrayList;
import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.EventDTO;
import com.nearme.statistics.dto.app.common.EventFlowDTO;
import com.nearme.statistics.form.app.common.EventFlowForm;
import com.nearme.statistics.model.common.KVEventEntity;
import com.nearme.statistics.model.common.KVEventFlowEntity;
import com.nearme.statistics.service.app.common.EventService;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 事件流
 * 
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-10-28
 */
public class EventFlowAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EventFlowForm form;
	private List<KVEventFlowEntity> eventFlowList;
	private List<KVEventEntity> eventList;
	private EventService service;

	/**
	 * 查询KV事件流列表
	 * @return
	 */
	public String init() {
		try{
			// 检查action传递过来的systemID合法性r
			if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
				return Action.ERROR;
			}
			int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
			if (systemID <= 0) {
				return Action.ERROR;
			}
			form.setSystemID(form.getSystemID());
			form.setTips(form.getTips()==null?"":form.getTips());
			
			BaseDTO dto = new BaseDTO();
			dto.setSystemID(systemID);
			//查询KV事件流列表
			this.eventFlowList = this.service.listKVEventFlow(dto);
			
			LogUtil.log(dto, "事件流-列表");

			return Action.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toadd() {
		try{
			int systemID = form.getSystemIDValue();
			String eventFlowName = form.getEventFlowName();
			if (systemID <= 0 || eventFlowName==null) {
				return Action.ERROR;
			}
			
			EventDTO dto = new EventDTO();
			dto.setSystemID(systemID);
			//查询KV事件列表
			this.eventList = this.service.listKVEvent(dto);
			
			LogUtil.log(dto, "事件流-跳转到添加页面");

			return "add";
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String toedit() {
		try{
			int systemID = form.getSystemIDValue();
			String eventFlowName = form.getEventFlowName();
			if (systemID <= 0 || eventFlowName==null) {
				return Action.ERROR;
			}
			
			EventFlowDTO dto = new EventFlowDTO();
			dto.setSystemID(systemID);
			dto.setEventFlowName(eventFlowName);
			//查询KV事件列表
			this.eventList = this.service.listKVEvent(dto);
			//查询事件流详情
			this.eventFlowList = this.service.getTheKVEventFlow(dto);
			
			if(eventFlowList!=null && eventFlowList.size()>0){
				form.setEventFlowName(eventFlowList.get(0).getEventFlowName());
			}
			
			LogUtil.log(dto, "事件流-跳转到编辑页面");

			return "edit";
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询某个事件流详情
	 * @return
	 */
	public String select() {
		int systemID = form.getSystemIDValue();
		String eventFlowName = form.getEventFlowName();
		if (systemID == 0 || null==eventFlowName || "".equals(eventFlowName.trim())) {
			return Action.SUCCESS;
		}
		
		EventFlowDTO dto = new EventFlowDTO();
		dto.setSystemID(systemID);
		dto.setEventFlowName(eventFlowName);
		//查询某个事件流详情
		this.eventFlowList = this.service.getTheKVEventFlow(dto);
		
		LogUtil.log(dto, "事件流-详情");

		return Action.SUCCESS;
	}

	
	/**
	 * 修改事件流
	 * @return
	 */
	public String update(){
		int systemID = form.getSystemIDValue();
		String eventFlowName = form.getEventFlowName();
		String eventFlowDesc = form.getEventFlowDesc();
		String[] ids = form.getEventID();
		if (systemID == 0 || null==eventFlowName || "".equals(eventFlowName.trim()) 
				|| null==ids || "".equals(ids.length<=0)) {
			return Action.SUCCESS;
		}

		List<EventFlowDTO> list = new ArrayList<EventFlowDTO>();
		EventFlowDTO dto = null;
		for(int i=0,len=ids.length; i<len; i++){
			dto = new EventFlowDTO();
			dto.setSystemID(systemID);
			dto.setEventFlowName(eventFlowName);
			dto.setEventFlowDesc(eventFlowDesc==null?"":eventFlowDesc);
			dto.setEventFlowStep(i+1);
			dto.setFinalEventID(ids[i]);
			list.add(dto);
		}
		//更新操作
		this.service.updateKVEventFlow(list);
		
		LogUtil.log(dto, "事件流-更新");
		
		form.setTips("事件流修改成功!");
		return "success_redirect";
	}
	
	/**
	 *添加事件流
	 * @return
	 */
	public String add(){
		int systemID = form.getSystemIDValue();
		String eventFlowName = form.getEventFlowName();
		String eventFlowDesc = form.getEventFlowDesc();
		String[] ids = form.getEventID();
		if (systemID == 0 || null==eventFlowName || "".equals(eventFlowName.trim()) 
				|| null==ids || "".equals(ids.length<=0)) {
			return Action.SUCCESS;
		}
		
		List<EventFlowDTO> list = new ArrayList<EventFlowDTO>();
		EventFlowDTO dto = null;
		for(int i=0,len=ids.length; i<len; i++){
			dto = new EventFlowDTO();
			dto.setSystemID(systemID);
			dto.setEventFlowName(eventFlowName);
			dto.setEventFlowDesc(eventFlowDesc==null?"":eventFlowDesc);
			dto.setEventFlowStep(i+1);
			dto.setFinalEventID(ids[i]);
			list.add(dto);
		}
		//添加操作
		service.addKVEventFlow(list);
		
		LogUtil.log(dto, "事件流-添加");
		
		form.setTips("事件流添加成功!");
		return "success_redirect";
	}
	
	/**
	 * 删除事件流
	 * @return
	 */
	public String delete(){
		int systemID = form.getSystemIDValue();
		String eventFlowName = form.getEventFlowName();
		if (systemID == 0 || null==eventFlowName || "".equals(eventFlowName.trim())) {
			return Action.SUCCESS;
		}
		EventFlowDTO dto = new EventFlowDTO();
		dto.setSystemID(systemID);
		dto.setEventFlowName(eventFlowName);
		//删除操作
		service.deleteKVEventFlow(dto);
		
		LogUtil.log(dto, "事件流-删除");
		
		return "success_redirect";
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
	public EventService getService() {
		return service;
	}
	public void setService(EventService service) {
		this.service = service;
	}
	public List<KVEventEntity> getEventList() {
		return eventList;
	}
	public void setEventList(List<KVEventEntity> eventList) {
		this.eventList = eventList;
	}

}
