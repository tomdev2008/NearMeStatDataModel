package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.DownactivecenterEntity;
import com.nearme.statistics.model.game.DownhomepageEntity;
import com.nearme.statistics.model.game.DownloadEntity;
import com.nearme.statistics.model.game.DownrankEntity;
import com.nearme.statistics.model.game.DownsearchEntity;
import com.nearme.statistics.model.game.DownsearchrecommendEntity;
import com.nearme.statistics.model.game.DownsortEntity;
import com.nearme.statistics.model.game.DownspecialtopicEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 游戏下载
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-2
 */
public class DownloadAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm formgamename;
	private GameForm form;

	private List<DownloadEntity> downloadList;
	private List<DownhomepageEntity> downhomepageList;
	private List<DownrankEntity> downrankList;
	private List<DownsortEntity> downsortList;
	private List<DownspecialtopicEntity> downspecialtopicList;
	private List<DownsearchEntity> downsearchList;
	private List<DownsearchrecommendEntity> downsearchrecommendList;
	private List<DownactivecenterEntity> downactiveList;

	private GameService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(10);
		String endDate = DateUtil.getToday();

		formgamename = new GameForm();
		formgamename.setSystemID(form.getSystemID());

		form.setStartTime(startDate);
		form.setEndTime(endDate);
		form.setSystemID(form.getSystemID());
		form.setLidu("DAILY");

		GameDTO dto = new GameDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());
		dto.setLidu(form.getLidu());
		downloadList = service.getDownloadList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downloadList);
		
		LogUtil.log(dto, "游戏下载");

		return Action.SUCCESS;
	}

	/**
	 * 查询游戏下载
	 * 
	 * @return
	 */
	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String appVersion = form.getAppVersion();
		String model = form.getModel();
		String productIDs = form.getProductIDs();

		if (systemID == 0) {
			return "download_down";
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return "download_down";
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setAppVersion(appVersion);
		dto.setModel(model);
		dto.setProductIDs(productIDs);
		downloadList = service.getDownloadList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, downloadList);
		
		LogUtil.log(dto, "游戏下载");

		return "download_down";
	}

	/**
	 * 导出报表-游戏下载
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<DownloadEntity> list = (List<DownloadEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("游戏下载_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "游戏下载" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "下载", "首页下载", "排行榜下载", "分类下载",
					"专题下载", "搜索下载", "搜推下载", "详情浏览", "详情下载", "活动中心下载" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				DownloadEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getDown()),
						String.valueOf(entity.getDownHomepage()),
						String.valueOf(entity.getDownRank()),
						String.valueOf(entity.getDownSort()),
						String.valueOf(entity.getDownSpecialtopic()),
						String.valueOf(entity.getDownSearch()),
						String.valueOf(entity.getDownSearchrecommend()),
						String.valueOf(entity.getDetailBrowse()),
						String.valueOf(entity.getDetailDown()),
						String.valueOf(entity.getDownActivecenter()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	/**
	 * 首页下载
	 * 
	 * @return
	 */
	public String queryHomepage() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		downhomepageList = service.getDownhomepageList(dto);
		
		LogUtil.log(dto, "游戏下载-首页下载");

		return "download_down_homepage";
	}

	/**
	 * 排行榜下载
	 * 
	 * @return
	 */
	public String queryRank() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		downrankList = service.getDownrankList(dto);
		
		LogUtil.log(dto, "游戏下载-排行榜下载");
		
		return "download_down_rank";
	}

	/**
	 * 分类下载
	 * 
	 * @return
	 */
	public String querySort() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		downsortList = service.getDownsortList(dto);
		
		LogUtil.log(dto, "游戏下载--分类下载");
		
		return "download_down_sort";
	}

	/**
	 * 专题下载
	 * 
	 * @return
	 */
	public String querySpecialtopic() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		downspecialtopicList = service.getDownspecialtopicList(dto);
		
		LogUtil.log(dto, "游戏下载-专题下载");
		
		return "download_down_specialtopic";
	}

	/**
	 * 搜索下载
	 * 
	 * @return
	 */
	public String querySearch() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		downsearchList = service.getDownsearchList(dto);
		
		LogUtil.log(dto, "游戏下载-搜索下载");
		
		return "download_down_search";
	}

	/**
	 * 搜推下载
	 * 
	 * @return
	 */
	public String querySearchrecommend() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		downsearchrecommendList = service.getDownsearchrecommendList(dto);
		
		LogUtil.log(dto, "游戏下载-搜推下载");
		
		return "download_down_searchrecommend";
	}

	/**
	 * 活动中心下载
	 * 
	 * @return
	 */
	public String queryActivecenter() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		int systemID = form.getSystemIDValue();
		String lidu = form.getLidu();
		String network = form.getNetwork();
		String model = form.getModel();
		String appVersion = form.getAppVersion();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setSystemID(systemID);
		dto.setLidu(lidu);
		dto.setNetworkType(network);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		downactiveList = service.getDownactivecenterList(dto);
		
		LogUtil.log(dto, "游戏下载-活动中心下载");
		
		return "download_down_activecenter";
	}

	public GameForm getForm() {
		return form;
	}

	public void setForm(GameForm form) {
		this.form = form;
	}

	public GameForm getFormgamename() {
		return formgamename;
	}

	public void setFormgamename(GameForm formgamename) {
		this.formgamename = formgamename;
	}

	public List<DownloadEntity> getDownloadList() {
		return downloadList;
	}

	public void setDownloadList(List<DownloadEntity> downloadList) {
		this.downloadList = downloadList;
	}

	public List<DownhomepageEntity> getDownhomepageList() {
		return downhomepageList;
	}

	public void setDownhomepageList(List<DownhomepageEntity> downhomepageList) {
		this.downhomepageList = downhomepageList;
	}

	public List<DownrankEntity> getDownrankList() {
		return downrankList;
	}

	public void setDownrankList(List<DownrankEntity> downrankList) {
		this.downrankList = downrankList;
	}

	public List<DownsortEntity> getDownsortList() {
		return downsortList;
	}

	public void setDownsortList(List<DownsortEntity> downsortList) {
		this.downsortList = downsortList;
	}

	public List<DownspecialtopicEntity> getDownspecialtopicList() {
		return downspecialtopicList;
	}

	public void setDownspecialtopicList(
			List<DownspecialtopicEntity> downspecialtopicList) {
		this.downspecialtopicList = downspecialtopicList;
	}

	public List<DownsearchEntity> getDownsearchList() {
		return downsearchList;
	}

	public void setDownsearchList(List<DownsearchEntity> downsearchList) {
		this.downsearchList = downsearchList;
	}

	public List<DownsearchrecommendEntity> getDownsearchrecommendList() {
		return downsearchrecommendList;
	}

	public void setDownsearchrecommendList(
			List<DownsearchrecommendEntity> downsearchrecommendList) {
		this.downsearchrecommendList = downsearchrecommendList;
	}

	public List<DownactivecenterEntity> getDownactiveList() {
		return downactiveList;
	}

	public void setDownactiveList(List<DownactivecenterEntity> downactiveList) {
		this.downactiveList = downactiveList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
