package com.nearme.statistics.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dto.app.zjb.ZjbDTO;
import com.nearme.statistics.model.zjb.ZjbVersionEntity;
import com.nearme.statistics.service.app.zjb.impl.ZjbServiceImpl;

public class ZjbVersionSelectTag extends TagSupport {
	private static final long serialVersionUID = -8139123655053122700L;

	/**
	 * 选中值
	 */
	private String value;
	/**
	 * 客户端ID
	 */
	private String clientID = "appVersion";
	/**
	 * name
	 */
	private String clientName = "appVersion";
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

	/**
	 * 获取机型信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ZjbVersionEntity> getVersionList() {
		String cacheKey = "ZJB_APPVERINFO";
		List<ZjbVersionEntity> cache = (List<ZjbVersionEntity>) SystemTableCache
				.get(cacheKey);

		if (cache == null) {
			try {
				ApplicationContext context = TagContext.getContextInstance();
				Object bean = context.getBean("zjbService");
				if (bean != null && bean instanceof ZjbServiceImpl) {
					ZjbDTO dto = new ZjbDTO();
					cache = ((ZjbServiceImpl) bean).getZjbVersionList(dto);
					SystemTableCache.set(cacheKey, cache);
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}

		return cache;
	}

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

		List<ZjbVersionEntity> vers = getVersionList();
		if (vers != null && !vers.isEmpty()) {
			for (ZjbVersionEntity ver : vers) {
				if ("Y".equalsIgnoreCase(ver.getHide())) {
					continue;
				}
				resp.append("<option value=\"").append(ver.getAppVersion())
						.append("\"");
				if (ver.getAppVersion().equals(value)) {
					resp.append(" selected=\"selected\"");
				}
				resp.append(">").append(ver.getAppVersion());
				resp.append("</option>");
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
}
