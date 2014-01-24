package com.nearme.statistics.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.nearme.statistics.cache.CacheConstant;
import com.nearme.statistics.cache.SystemTableCache;
import com.nearme.statistics.dao.app.common.impl.ChannelinfoDaoImpl;
import com.nearme.statistics.dto.app.common.ChannelinfoDTO;
import com.nearme.statistics.model.commonsetting.ChannelinfoEntity;

public class ChannelSelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 系统ID
	 */
	private int systemID = 0;
	/**
	 * 选中值
	 */
	private String value;
	/**
	 * 客户端ID
	 */
	private String clientID = "channel";
	/**
	 * name
	 */
	private String clientName = "channel";
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
	 * 获取渠道信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ChannelinfoEntity> getChannelList() {
		String cacheKey = CacheConstant.COMMON_CHANNELINFO_ + systemID;
		List<ChannelinfoEntity> cache = (List<ChannelinfoEntity>) SystemTableCache
				.get(cacheKey);

		if (cache == null) {
			try {
				ApplicationContext context = TagContext.getContextInstance();
				Object bean = context.getBean("channelinfoDao");
				if (bean != null && bean instanceof ChannelinfoDaoImpl) {
					ChannelinfoDTO dto = new ChannelinfoDTO();
					dto.setSystemID(this.systemID);
					cache = ((ChannelinfoDaoImpl) bean).getChnnelinfoList(dto);
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

		List<ChannelinfoEntity> channels = getChannelList();
		if (channels != null && !channels.isEmpty()) {
			for (ChannelinfoEntity channel : channels) {
				resp.append("<option value=\"").append(channel.getChannelID())
						.append("\"");
				if (("" + channel.getChannelID()).equals(value)) {
					resp.append(" selected=\"selected\"");
				}
				resp.append(">").append(channel.getChannelName());
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

	public int getSystemID() {
		return systemID;
	}

	public void setSystemID(int systemID) {
		this.systemID = systemID;
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
