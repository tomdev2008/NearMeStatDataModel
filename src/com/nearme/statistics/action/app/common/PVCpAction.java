package com.nearme.statistics.action.app.common;

import java.util.List;

import com.nearme.statistics.dto.BaseDTO;
import com.nearme.statistics.dto.app.common.PvDTO;
import com.nearme.statistics.form.BaseForm;
import com.nearme.statistics.model.common.PvEntity;
import com.nearme.statistics.service.app.common.PvService;
import com.nearme.statistics.util.LogUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 对比使用时长、使用频率
 * @author fgx
 * @version 1.0
 * @since 1.0, 2013-12-18
 */
public class PVCpAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BaseForm form;
	private List<PvEntity> cpList;
	private PvService service;

	public String query() {
		String systemID = form.getSystemID();
		String appVersion = form.getAppVersion();
		String channelID = form.getQudao();
		String lidu = form.getLidu();

		PvDTO dto = new PvDTO();
		dto.setSystemID(systemID);
		//选择的所有日期
		dto.setStatDates(form.getDays());
		dto.setAppVersion(appVersion);
		dto.setChannelID(channelID);
		dto.setLidu(lidu);
		//对比数据类型(使用时长/使用频率等)
		dto.setFlag(form.getType());
		cpList = service.listCp(dto);

		LogUtil.log(new BaseDTO(), "PV_对比");//log记录查询
		
		if("frequency".equals(form.getType())){
			return "frequency_cp";
		}else{
			return "duration_cp";
		}
	}


	public BaseForm getForm() {
		return form;
	}
	public void setForm(BaseForm form) {
		this.form = form;
	}
	public List<PvEntity> getCpList() {
		return cpList;
	}
	public void setCpList(List<PvEntity> cpList) {
		this.cpList = cpList;
	}
	public PvService getService() {
		return service;
	}
	public void setService(PvService service) {
		this.service = service;
	}

}
