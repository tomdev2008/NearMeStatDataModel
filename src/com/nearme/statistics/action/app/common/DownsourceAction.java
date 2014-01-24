package com.nearme.statistics.action.app.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.common.ModuleDTO;
import com.nearme.statistics.dto.app.common.ProductinfoDTO;
import com.nearme.statistics.form.app.common.ProductinfoForm;
import com.nearme.statistics.model.common.DownsourceCPTEntity;
import com.nearme.statistics.model.common.DownsourceEntity;
import com.nearme.statistics.model.commonsetting.ModuleEntity;
import com.nearme.statistics.service.app.common.CommonService;
import com.nearme.statistics.service.app.common.ModuleService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 单个资源下载
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-12-13
 */
public class DownsourceAction extends ActionSupport {
	private static final long serialVersionUID = -7747673878091973779L;

	private final String TAG = "单个资源下载";

	private ProductinfoForm form;
	private ProductinfoForm formcpt;

	private List<ModuleEntity> moduleList;// 模块列表(界面选择条件)
	private ModuleService moduleservice;// 模块查询(界面查询)

	private List<DownsourceEntity> downsourceList;
	private List<DownsourceCPTEntity> cptList;
	private CommonService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(30);
		String endDate = DateUtil.getToday();

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID("" + systemID);
		
		formcpt = new ProductinfoForm();
		formcpt.setStartTime(startDate);
		formcpt.setEndTime(endDate);
		formcpt.setSystemID("" + systemID);

		// 初始化 "模块 " 下拉列表
		ModuleDTO dto = new ModuleDTO();
		dto.setSystemID(form.getSystemID());
		moduleList = moduleservice.getGroupList(dto);

		return Action.SUCCESS;
	}

	public String queryDownsource() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String qudao = form.getQudao();// 渠道
		String groupname = form.getGroupname();// 模块
		String productIDs = form.getProductIDs();

		if (systemID == 0) {
			return "downsource_result";
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "downsource_result";
		}
		if (productIDs == null || "".equals(productIDs)) {
			return "downsource_result";
		}

		ProductinfoDTO dto = new ProductinfoDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setQudao(qudao);
		dto.setGroupname(groupname);
		dto.setProductIDs(productIDs);
		downsourceList = service.getDownsourceList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downsourceList);

		LogUtil.log(dto, TAG);// log记录查询

		return "downsource_result";
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportDownsource() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return "downsource_result";
		}

		List<DownsourceEntity> list = (List<DownsourceEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "下载量", "浏览量" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (DownsourceEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getDowncnt()),
						String.valueOf(entity.getBrowsecnt()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	/**
	 * 查询CPT
	 * 
	 * @return
	 */
	public String queryCPT() {
		Date startDate = formcpt.getStartDate();
		Date endDate = formcpt.getEndDate();
		int systemID = formcpt.getSystemIDValue();
		String productIDs = formcpt.getProductIDs();

		if (systemID == 0) {
			return "downsource_cpt";
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "downsource_cpt";
		}

		ProductinfoDTO dto = new ProductinfoDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setProductIDs(productIDs);
		cptList = service.getDownsourceCPTList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, cptList);

		LogUtil.log(dto, TAG);// log记录查询

		return "downsource_cpt";
	}

	/**
	 * 导出CPT
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportCPT() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);

		if (obj == null) {
			return "downsource_cpt";
		}

		List<DownsourceCPTEntity> list = (List<DownsourceCPTEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil(TAG + "_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { TAG });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "总下载量", "总浏览量", "热门推荐", "小编推荐",
					"应用榜", "游戏榜", "最近流行", "分类精选", "二级分类", "大家都在搜", "48小时热搜榜",
					"搜索排行榜", "主动搜索", "装机必备", "相关推荐", "下载有礼", "升级", "单个资源广告",
					"活动页面" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (DownsourceCPTEntity entity : list) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getTotalDown()),
						String.valueOf(entity.getTotalBrowse()),
						String.valueOf(entity.getHottuijian()),
						String.valueOf(entity.getEditortujian()),
						String.valueOf(entity.getApp()),
						String.valueOf(entity.getGame()),
						String.valueOf(entity.getPop()),
						String.valueOf(entity.getFenlei()),
						String.valueOf(entity.getSecondfenlei()),
						String.valueOf(entity.getAllsearch()),
						String.valueOf(entity.getHotsearch()),
						String.valueOf(entity.getSearchrank()),
						String.valueOf(entity.getTosearch()),
						String.valueOf(entity.getInstallmust()),
						String.valueOf(entity.getRecommondpush()),
						String.valueOf(entity.getDowngift()),
						String.valueOf(entity.getUpgrade()),
						String.valueOf(entity.getResourceads()),
						String.valueOf(entity.getActivepage()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public ProductinfoForm getForm() {
		return form;
	}

	public void setForm(ProductinfoForm form) {
		this.form = form;
	}

	public ProductinfoForm getFormcpt() {
		return formcpt;
	}

	public void setFormcpt(ProductinfoForm formcpt) {
		this.formcpt = formcpt;
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

	public List<DownsourceEntity> getDownsourceList() {
		return downsourceList;
	}

	public void setDownsourceList(List<DownsourceEntity> downsourceList) {
		this.downsourceList = downsourceList;
	}

	public List<DownsourceCPTEntity> getCptList() {
		return cptList;
	}

	public void setCptList(List<DownsourceCPTEntity> cptList) {
		this.cptList = cptList;
	}

	public CommonService getService() {
		return service;
	}

	public void setService(CommonService service) {
		this.service = service;
	}
}
