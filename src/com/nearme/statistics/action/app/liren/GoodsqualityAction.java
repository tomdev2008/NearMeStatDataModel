package com.nearme.statistics.action.app.liren;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.liren.LirenDTO;
import com.nearme.statistics.form.app.liren.LirenForm;
import com.nearme.statistics.model.liren.LRGoodsqualityEntity;
import com.nearme.statistics.service.app.liren.LirenService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品质量
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-8-6
 */
public class GoodsqualityAction extends ActionSupport {
	private static final long serialVersionUID = 9038018083239546674L;

	private LirenForm form;
	private List<LRGoodsqualityEntity> browsetotalList;// 浏览总数
	private List<LRGoodsqualityEntity> browsedetailList;// 浏览详情
	private List<LRGoodsqualityEntity> likeList;// 喜欢数
	private List<LRGoodsqualityEntity> qualityList;// 商品平均质量
	private LirenService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		int systemID = NumericUtil.tryParse(form.getSystemID(), -1);
		if (systemID <= 0) {
			return Action.ERROR;
		}

		String startDate = DateUtil.getDateOfXdaysAgo(1);
		form.setStartTime(startDate);
		form.setSystemID(form.getSystemID());

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setSystemID(form.getSystemIDValue());

		dto.setType("browse_total");
		browsetotalList = service.getGoodsqualityList(dto);

		dto.setType("browse_detail");
		browsedetailList = service.getGoodsqualityList(dto);

		dto.setType("like");
		likeList = service.getGoodsqualityList(dto);

		dto.setType("quality");
		qualityList = service.getGoodsqualityList(dto);

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, browsetotalList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, browsedetailList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_3, likeList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_4, qualityList);
		
		LogUtil.log(dto, "商品质量");

		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		int systemID = form.getSystemIDValue();
		String appVersion = form.getAppVersion();
		String model = form.getModel();

		if (systemID == 0) {
			return Action.SUCCESS;
		}
		if (startDate == null) {
			return Action.SUCCESS;
		}

		LirenDTO dto = new LirenDTO();
		dto.setStartDate(startDate);
		dto.setSystemID(systemID);
		dto.setAppVersion(appVersion);
		dto.setModel(model);

		dto.setType("browse_total");
		browsetotalList = service.getGoodsqualityList(dto);

		dto.setType("browse_detail");
		browsedetailList = service.getGoodsqualityList(dto);

		dto.setType("like");
		likeList = service.getGoodsqualityList(dto);

		dto.setType("quality");
		qualityList = service.getGoodsqualityList(dto);

		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, browsetotalList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_2, browsedetailList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_3, likeList);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA_4, qualityList);
		
		LogUtil.log(dto, "商品质量");

		return Action.SUCCESS;
	}

	/**
	 * 导出报表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);
		Object obj2 = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_2);
		Object obj3 = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_3);
		Object obj4 = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA_4);

		if (obj == null || obj2 == null || obj3 == null || obj4 == null) {
			return Action.SUCCESS;
		}

		List<LRGoodsqualityEntity> list = (List<LRGoodsqualityEntity>) obj;
		List<LRGoodsqualityEntity> list2 = (List<LRGoodsqualityEntity>) obj2;
		List<LRGoodsqualityEntity> list3 = (List<LRGoodsqualityEntity>) obj3;
		List<LRGoodsqualityEntity> list4 = (List<LRGoodsqualityEntity>) obj4;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("商品质量_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "每商品浏览（总）" });
			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "每商品被多少人浏览（总）", "数量", "占比" });
			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				LRGoodsqualityEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getBetweentype()),
						String.valueOf(entity.getNumb()),
						String.valueOf(entity.getZanbi()) });
			}

			eu.createSheets(1, new String[] { "每商品浏览（详情）" });
			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "每商品被多少人浏览（详情）", "数量", "占比" });
			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (LRGoodsqualityEntity entity : list2) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getBetweentype()),
						String.valueOf(entity.getNumb()),
						String.valueOf(entity.getZanbi()) });
			}

			eu.createSheets(1, new String[] { "每商品喜欢" });
			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "每商品被多少人喜欢", "数量", "占比" });
			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (LRGoodsqualityEntity entity : list3) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getBetweentype()),
						String.valueOf(entity.getNumb()),
						String.valueOf(entity.getZanbi()) });
			}

			eu.createSheets(1, new String[] { "商品平均品质" });
			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "商品平均品质", "数量", "占比" });
			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (LRGoodsqualityEntity entity : list4) {
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getBetweentype()),
						String.valueOf(entity.getNumb()),
						String.valueOf(entity.getZanbi()) });
			}

			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public LirenForm getForm() {
		return form;
	}

	public void setForm(LirenForm form) {
		this.form = form;
	}

	public List<LRGoodsqualityEntity> getBrowsetotalList() {
		return browsetotalList;
	}

	public void setBrowsetotalList(List<LRGoodsqualityEntity> browsetotalList) {
		this.browsetotalList = browsetotalList;
	}

	public List<LRGoodsqualityEntity> getBrowsedetailList() {
		return browsedetailList;
	}

	public void setBrowsedetailList(List<LRGoodsqualityEntity> browsedetailList) {
		this.browsedetailList = browsedetailList;
	}

	public List<LRGoodsqualityEntity> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<LRGoodsqualityEntity> likeList) {
		this.likeList = likeList;
	}

	public List<LRGoodsqualityEntity> getQualityList() {
		return qualityList;
	}

	public void setQualityList(List<LRGoodsqualityEntity> qualityList) {
		this.qualityList = qualityList;
	}

	public LirenService getService() {
		return service;
	}

	public void setService(LirenService service) {
		this.service = service;
	}
}
