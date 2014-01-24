package com.nearme.statistics.action.app.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.dto.app.game.GameDTO;
import com.nearme.statistics.form.app.game.GameForm;
import com.nearme.statistics.model.game.ProductEntity;
import com.nearme.statistics.model.game.ProductreportdailyEntity;
import com.nearme.statistics.service.app.game.GameService;
import com.nearme.statistics.util.DateUtil;
import com.nearme.statistics.util.ExcelUtil;
import com.nearme.statistics.util.LogUtil;
import com.nearme.statistics.util.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 产品日报
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-4
 */
public class ProductreportdailyAction extends ActionSupport {
	private static final long serialVersionUID = -1940603784500226768L;

	private GameForm form;
	private List<ProductEntity> productList;// 产品列表
	private List<ProductreportdailyEntity> productreportdailyList;
	private GameService service;

	public String init() {
		// 检查action传递过来的systemID合法性
		if (form == null || StringUtil.isNullOrEmpty(form.getSystemID())) {
			return Action.ERROR;
		}
		
		String startDate = DateUtil.getDateOfXdaysAgo(7);
		String endDate = DateUtil.getToday();

		form.setStartTime(startDate);
		form.setEndTime(endDate);

		GameDTO dto = new GameDTO();
		dto.setStartDate(DateUtil.parseDate(startDate, "yyyy-MM-dd"));
		dto.setEndDate(DateUtil.parseDate(endDate, "yyyy-MM-dd"));
		productList = service.getProductList(dto);
		dto.setProductid(productList.get(0).getProductid());
		productreportdailyList = service.getProductreportdailyList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, productreportdailyList);
		
		LogUtil.log(dto, "产品日报");
		
		return Action.SUCCESS;
	}

	public String query() {
		Date startDate = form.getStartDate();
		Date endDate = form.getEndDate();
		String model = form.getModel();
		String appVersion = form.getAppVersion();
		String productid = form.getProductid();

		if (startDate == null || endDate == null || startDate.after(endDate)) {
			return Action.SUCCESS;
		}

		GameDTO dto = new GameDTO();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setModel(model);
		dto.setAppVersion(appVersion);
		dto.setProductid(productid);
		productList = service.getProductList(dto);
		productreportdailyList = service.getProductreportdailyList(dto);
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.NEARME_SESSION_REPROT_DATA, productreportdailyList);
		
		LogUtil.log(dto, "产品日报");

		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String export() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.NEARME_SESSION_REPROT_DATA);

		if (obj == null) {
			return Action.SUCCESS;
		}

		List<ProductreportdailyEntity> list = (List<ProductreportdailyEntity>) obj;

		HttpServletResponse response = ServletActionContext.getResponse();
		ExcelUtil eu = new ExcelUtil("产品日报_" + DateUtil.getToday());

		try {
			eu.createSheets(1, new String[] { "产品日报" });

			// 设置标题
			eu.setFont(ExcelUtil.getTitleFont());
			eu.writeLine(new String[] { "日期", "总用户", "总下载", "登陆用户", "新增手机",
					"新增用户", "下载次数", "启动IMEI", "启动次数", "沉默用户", "流失用户",
					"NRR1（%）", "NRR2（%）", "NRR3（%）", "LRR1（%）", "LRR2（%）",
					"LRR3（%）" });

			// 设置信息体
			eu.setFont(ExcelUtil.getBodyFont());
			for (int i = list.size() - 1; i >= 0; i--) {
				ProductreportdailyEntity entity = list.get(i);
				eu.writeLine(new String[] {
						DateUtil.formatDate2Short(entity.getStatDate()),
						String.valueOf(entity.getTotaluser()),
						String.valueOf(entity.getTotaldownload()),
						String.valueOf(entity.getLoginuser()),
						String.valueOf(entity.getNewimei()),
						String.valueOf(entity.getNewuser()),
						String.valueOf(entity.getDownloadtimes()),
						String.valueOf(entity.getStartimei()),
						String.valueOf(entity.getStarttimes()),
						String.valueOf(entity.getSilentuser()),
						String.valueOf(entity.getLostuser()),
						String.valueOf(entity.getNrr1()),
						String.valueOf(entity.getNrr2()),
						String.valueOf(entity.getNrr3()),
						String.valueOf(entity.getLrr1()),
						String.valueOf(entity.getLrr2()),
						String.valueOf(entity.getLrr3()) });
			}
			eu.endWrite();
			eu.export(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public GameForm getForm() {
		return form;
	}

	public void setForm(GameForm form) {
		this.form = form;
	}

	public List<ProductEntity> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductEntity> productList) {
		this.productList = productList;
	}

	public List<ProductreportdailyEntity> getProductreportdailyList() {
		return productreportdailyList;
	}

	public void setProductreportdailyList(
			List<ProductreportdailyEntity> productreportdailyList) {
		this.productreportdailyList = productreportdailyList;
	}

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
}
