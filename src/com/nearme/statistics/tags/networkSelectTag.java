package com.nearme.statistics.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.model.sys.systemTable.NetworkTypeEntity;
import com.nearme.statistics.service.sys.impl.SystemTableServiceImpl;

/**
 * @author 段锦涛
 * @function 网络选择公共控件
 * @version 1.0
 * @since 1.0, 2013-4-8
 */
public class networkSelectTag extends TagSupport {

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
	private String clientID = "netWork";
	/**
	 * name
	 */
	private String clientName = "netWork";
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

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		generateHtml();

		return EVAL_PAGE;
	}

	@SuppressWarnings("unchecked")
	private List<NetworkTypeEntity> getNetworkList() {
		String cacheKey = "SYS_SYSTEMTABLE_NETWORK";
		List<NetworkTypeEntity> cache = (List<NetworkTypeEntity>) SystemTableCache
				.get(cacheKey);

		if (cache == null) {
			try {
				ApplicationContext context = TagContext.getContextInstance();
				Object bean = context.getBean("systemTableService");
				if (bean != null && bean instanceof SystemTableServiceImpl) {
					cache = ((SystemTableServiceImpl) bean)
							.getNetworkTypeList(null);
					SystemTableCache.set(cacheKey, cache);
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}

		return cache;
	}
	
	/**
	 * 对外接口
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<NetworkTypeEntity> getNetList() {
		String cacheKey = "SYS_SYSTEMTABLE_NETWORK";
		List<NetworkTypeEntity> cache = (List<NetworkTypeEntity>) SystemTableCache
				.get(cacheKey);

		if (cache == null) {
			try {
				ApplicationContext context = TagContext.getContextInstance();
				Object bean = context.getBean("systemTableService");
				if (bean != null && bean instanceof SystemTableServiceImpl) {
					cache = ((SystemTableServiceImpl) bean)
							.getNetworkTypeList(null);
					SystemTableCache.set(cacheKey, cache);
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}

		return cache;
	}

	private void generateHtml() {
		// 添加<select标签起始
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

		// 添加"全部"选择项
		if (this.addEmptyAll) {
			resp.append("<option value=\"all\">--全部--</option>");
		}
		// 添加单项选择
		List<NetworkTypeEntity> list = getNetworkList();
		if (list != null && !list.isEmpty()) {
			for (NetworkTypeEntity network : list) {
				resp.append("<option value=\"")
						.append(network.getNetworkName()).append("\"");
				if (network.getNetworkName().equals(value)) {
					resp.append(" selected=\"selected\"");
				}
				resp.append(">").append(network.getNetworkName());
				resp.append("</option>");
			}
		}

		// 结束标签
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
}
