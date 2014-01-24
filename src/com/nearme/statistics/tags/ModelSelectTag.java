package com.nearme.statistics.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.nearme.statistics.cache.CacheConstant;
import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dto.app.common.ModelDTO;
import com.nearme.statistics.model.commonsetting.ModelEntity;
import com.nearme.statistics.service.app.common.impl.ModelServiceImpl;

public class ModelSelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 选中值
	 */
	private String value;
	/**
	 * 客户端ID
	 */
	private String clientID = "model";
	/**
	 * name
	 */
	private String clientName = "model";
	/**
	 * client css class
	 */
	private String cssClass;
	/**
	 * client css style
	 */
	private String cssStyle;
	/**
	 * client onchange
	 */
	private String onchange;
	/**
	 * 是否添加一个空值表示“全部” 默认添加
	 */
	private boolean addEmptyAll = true;

	/**
	 * 是否只显示OPPO手机
	 */
	private boolean showOppoOnly = true;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		generateHtml();

		return EVAL_PAGE;
	}

	/**
	 * 获取机型信息<br>
	 * cache 中存放区分先后顺序<br>
	 * 先是oppo机型，然后是非OPPO机型<br>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ModelEntity> getModelList() {
		String cacheKey = CacheConstant.SYS_SYSTEMTABLE_MODELDATA;
		List<ModelEntity> cache = (List<ModelEntity>) SystemTableCache
				.get(cacheKey);

		if (cache == null || cache.isEmpty()) {
			try {
				ApplicationContext context = TagContext.getContextInstance();
				Object bean = context.getBean("modelService");
				if (bean != null && bean instanceof ModelServiceImpl) {
					ModelDTO dto = new ModelDTO();
					//查询OPPO手机
					dto.setIsoppo(1);
					cache = ((ModelServiceImpl) bean).getModelList(dto);
					
					//查询非OPPO手机
					dto.setIsoppo(0);
					List<ModelEntity> list2 = ((ModelServiceImpl) bean).getModelList(dto);
					
					//组合后缓存
					if (null != list2) {
						cache.addAll(list2);
					}
					SystemTableCache.set(cacheKey, cache);
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}

		return cache;
	}
	
	/**
	 * 对外调用
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<ModelEntity> getModels() {
		String cacheKey = CacheConstant.SYS_SYSTEMTABLE_MODELDATA;
		List<ModelEntity> cache = (List<ModelEntity>) SystemTableCache
				.get(cacheKey);

		if (cache == null || cache.isEmpty()) {
			try {
				ApplicationContext context = TagContext.getContextInstance();
				Object bean = context.getBean("modelService");
				if (bean != null && bean instanceof ModelServiceImpl) {
					ModelDTO dto = new ModelDTO();
					//查询OPPO手机
					dto.setIsoppo(1);
					cache = ((ModelServiceImpl) bean).getModelList(dto);
					
					//查询非OPPO手机
					dto.setIsoppo(0);
					List<ModelEntity> list2 = ((ModelServiceImpl) bean).getModelList(dto);
					
					//组合后缓存
					if (null != list2) {
						cache.addAll(list2);
					}
					SystemTableCache.set(cacheKey, cache);
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}

		return cache;
	}

	@SuppressWarnings("null")
	private void generateHtml() {
		StringBuilder resp = new StringBuilder("<select");
		resp.append(" id=\"").append(this.clientID).append("\" ");
		resp.append(" name=\"").append(this.clientName).append("\" ");
		if (this.cssClass != null) {
			resp.append(" class=\"").append(this.cssClass).append("\" ");
		}
		if (this.cssClass != null) {
			resp.append(" style=\"").append(this.cssStyle).append("\"");
		}
		if (this.onchange != null) {
			resp.append(" onchange=\"").append(this.onchange).append("\"");
		}
		resp.append(">");

		if (this.addEmptyAll) {
			resp.append("<option value=\"all\">--全部--</option>");
		}

		List<ModelEntity> modelList = getModelList();
		String serialPtr = null;
		if (modelList != null || !modelList.isEmpty()) {
			for (ModelEntity model : modelList) {
				if (showOppoOnly && 0 == model.getIsoppo()) {
					continue;
				}

				if (model.getTypename() != null
						&& !model.getTypename().equalsIgnoreCase(serialPtr)) {
					resp.append("<optgroup label=\"").append(
							model.getTypename()).append("\">");
					serialPtr = model.getTypename();
				}
				resp.append("<option value=\"").append(model.getModel())
						.append("\" ");
				if (this.value != null
						&& this.value.equalsIgnoreCase(model.getModel())) {
					resp.append(" selected=\"selected\"");
				}
				resp.append(">").append(model.getModel()).append("</option>");
			}
		}
		resp.append("</select>");

		JspWriter out = pageContext.getOut();
		try {
			out.println(resp.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public boolean isAddEmptyAll() {
		return addEmptyAll;
	}

	public void setAddEmptyAll(boolean addEmptyAll) {
		this.addEmptyAll = addEmptyAll;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setShowOppoOnly(boolean showOppoOnly) {
		this.showOppoOnly = showOppoOnly;
	}

	public boolean getShowOppoOnly() {
		return showOppoOnly;
	}
}
