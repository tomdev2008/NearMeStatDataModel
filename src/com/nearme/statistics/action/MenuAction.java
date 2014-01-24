package com.nearme.statistics.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.model.sys.MenuItem;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport {
	private static final long serialVersionUID = -6764130157477813655L;

	private String select;//select选项

	@SuppressWarnings("unchecked")
	public String select() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<MenuItem> menuList = (List<MenuItem>) session
				.getAttribute("SESSION_MENULIST");
		Map<String, String> sessionPerms = (Map<String, String>) session
				.getAttribute("SESSION_PERMS");
		StringBuilder menuStr = new StringBuilder();
		if (sessionPerms != null) {
			for (MenuItem menu : menuList) {
				if (sessionPerms.containsKey("all")
						|| sessionPerms.containsKey("M" + menu.getMenuKey())) {
					String menuKey = menu.getMenuKey();
					String menuPkey = menu.getMenuParentKey();
					String menutext = menu.getMenuText();
					String menuUrl = menu.getMenuUrl();
					
					if(select.startsWith("91")){
						if (menuKey.length() == 6 && menuKey.startsWith("91")) {
							menuKey = menuKey.substring(2);
							menuPkey = (menuPkey.length() == 2) ? "0" : menuPkey
									.substring(2);
							menuStr.append(String.format(
									"d.add('%s','%s','%s','%s');\r\n", menuKey,
									menuPkey, menutext, menuUrl));
						}
					}
					else if (menuKey.length() > 6 && menuKey.startsWith(select)) {
						menuKey = menuKey.substring(6);
						menuPkey = (menuPkey.length() == 6) ? "0" : menuPkey
								.substring(6);
						menuStr.append(String.format(
								"d.add('%s','%s','%s','%s');\r\n", menuKey,
								menuPkey, menutext, menuUrl));
					}
				}
			}
		}
		
		//拼出menuStr写入session
		if (menuStr.length() > 0) {
			ServletActionContext.getRequest().getSession().setAttribute(
					Constants.SESSION_MENU_STR, menuStr.toString());
		}
		
		return Action.SUCCESS;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}
}
