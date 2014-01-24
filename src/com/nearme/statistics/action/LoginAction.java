package com.nearme.statistics.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.CheckAdminAccess;
import com.nearme.statistics.common.Constants;
import com.nearme.statistics.model.sys.MenuItem;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Parameters;
import com.nearme.statistics.service.sys.AdminService;
import com.nearme.statistics.util.IpUtil;
import com.nearme.statistics.util.NumericUtil;
import com.nearme.statistics.util.StringUtil;
import com.nearme.statistics.util.loginkeke.LoginUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.oppo.security.MD5Util;

/**
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-24
 */
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private AdminService service;

	private String loginName;
	private String loginPwd;
	private String errMsg;

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String ip = IpUtil.getIPAddress(request);
		if (request.getMethod().equalsIgnoreCase("get")) {
			return Action.LOGIN;
		}

		if (StringUtil.isNullOrEmpty(loginName)) {
			errMsg = "请输入登录名";
			return Action.LOGIN;
		}
		if (StringUtil.isNullOrEmpty(loginPwd)) {
			errMsg = "请输入密码";
			return Action.LOGIN;
		}

		// 验证用户名密码
		Admin admin = service.Login(loginName, MD5Util.md5(loginPwd, "UTF-8"));
		if (null == admin) {
			//通过可可账号登陆
			admin = loginKeke(loginName, loginPwd, ip);
			if (null != admin) {
				loginName = admin.getUserName();
				int id = service.checkAdminNameExists(loginName);
				// 第一次用可可账号登陆，将可可账号记录到统计数据库中
				if (id <= 0){
					service.addAdmin(admin);
				} else {
					admin.setId(id);
				}
			} else {
				//既不是统计账号，也不是可可账号
				errMsg = "用户名或密码错误";
				return Action.LOGIN;
			}
		}
		
		if (admin == null) {
			errMsg = "用户名或密码错误";
			return Action.LOGIN;
		}

		// 验证IP
		if (!CheckAdminAccess.checkIPAccessable(service, admin, ip)) {
			errMsg = "在不允许的IP范围内禁止登录";
			return Action.LOGIN;
		}

		// 写入SESSION
		request.getSession().setAttribute(Constants.SESSION_ADMIN_KEY, admin);

		// 将用户名写入Cookie
		Cookie loginNameCookie = new Cookie("loginName", loginName);
		response.addCookie(loginNameCookie);

		// 获取权限
		List<String> perms = null;
		Map<String, String> mp = new HashMap<String, String>();
		if ("admin".equalsIgnoreCase(admin.getUserName())) {
			// admin具有全部权限
			mp.put("all", "all");
		} else {
			perms = service.getAdminPermissions(admin.getId());
			if (perms != null && perms.size() > 0) {
				for (String p : perms) {
					mp.put("M" + p, "OK");
				}
			}
		}
		request.getSession().setAttribute(Constants.SESSION_PERMS_KEY, mp);

		// 加载所有菜单列表 放入session
		List<MenuItem> menuList = listMenus();
		request.getSession().setAttribute(Constants.SESSION_MENULIST_KEY,
				menuList);

		this.formatPerms(perms, menuList);

		// 包含配置文件中字符串的地址不作拦截(登录、退出等)
		List<Parameters> exceptUrlList = service
				.listParameters(Constants.PARAM_EXCEPT_URL);
		request.getSession().setAttribute(Constants.SESSION_EXCEPT_URL_KEY,
				exceptUrlList);

		try {
			response.sendRedirect("index.jsp");

			// response.addHeader("Content-Type",
			// "text/html; charset=utf-8");
			// response.getWriter().write("<html><head><meta http-equiv='refresh' content='0;url=index.jsp?sid="+Math.random()+"'></head></html>");
			return Action.NONE;
		} catch (IOException e) {
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 罗列菜单
	 * @return
	 */
	private List<MenuItem> listMenus() {
		List<MenuItem> result = new ArrayList<MenuItem>();
		List<MenuItem> list = service.listAllMenus();
		
		//1.拆分通用菜单  + 私有菜单
		List<MenuItem> selflist = new ArrayList<MenuItem>();
		List<MenuItem> commonlist = new ArrayList<MenuItem>();
		for (MenuItem menu : list) {
			String menukey = menu.getMenuKey();
			if (menukey.startsWith("010001")) {
				commonlist.add(menu);//通用菜单
			} else {
				selflist.add(menu);//私有菜单
			}
		}
		
		// 重新构造菜单
		for (MenuItem selfmenu : selflist) {
			String menukey = selfmenu.getMenuKey();
			if (menukey.length() == 6 && !menukey.startsWith("9")){
				// 添加菜单名
				result.add(selfmenu);
				
				// 获取systemID
				String systemID = selfmenu.getMenuUrl();
				if (null != systemID && !"".equals(systemID)) {
					systemID = NumericUtil.tryParse(systemID, -1) == -1 ? null : "?form.systemID=" + systemID;
				}
				
				// 添加通用菜单
				for (MenuItem commenu : commonlist) {
					MenuItem menu = (MenuItem) commenu.clone();
					menu.setMenuKey(menukey + menu.getMenuKey().substring(6));
					if (menu.getMenuParentKey().length() >= 6) {
						menu.setMenuParentKey(menukey + menu.getMenuParentKey().substring(6));
					}
					if (null != systemID && menu.getMenuTabIndex() == 3) {
						menu.setMenuUrl(menu.getMenuUrl() + systemID);
					}
					if (menu.getMenuKey().length() > 6) {
						result.add(menu);
					}
				}
				
				//3.3修正私有菜单url并添加
				for (MenuItem menuItem : selflist) {
					MenuItem menu = (MenuItem) menuItem.clone();
					String murl = menu.getMenuUrl();
					String mkey = menu.getMenuKey();
					if (mkey.startsWith(menukey) && mkey.length() > 6) {
						if (null != systemID && menu.getMenuTabIndex() == 3) {
							menu.setMenuUrl(murl + systemID);
						}
						result.add(menu);
					}
				}
			} else if (menukey.length() < 6 || menukey.startsWith("9")) {
				result.add(selfmenu);
			}
		}
		
		return result;
	}

	/**
	 * 可可账号登陆
	 * @author 朱峰
	 * @param name
	 * @param pwd
	 * @param ip
	 * @return
	 */
	private Admin loginKeke(String name,String pwd,String ip){
		Admin admin = null;
		String userName =  LoginUtil.loginKeke(name, pwd, ip);
		
		if (userName != null) {
			admin = new Admin();
			admin.setUserName(userName);
		}
		
		return admin;
	}

	private void formatPerms(List<String> perms, List<MenuItem> menuList) {
		List<String> formatPerms = new ArrayList<String>();
		if (perms != null && menuList != null) {
			String code = null;
			MenuItem menuItem = null;
			for (int i = 0, len = perms.size(); i < len; i++) {
				code = perms.get(i);
				for (int j = 0, size = menuList.size(); j < size; j++) {
					menuItem = menuList.get(j);
					if (code.equals(menuItem.getMenuKey())) {
						String result = StringUtil.formatRequestUrl(menuItem
								.getMenuUrl());
						if (result != null && !"".equals(result)) {
							formatPerms.add(result);
						}
					}
				}
			}
		}

		// 写入SESSION
		ServletActionContext.getRequest().getSession().setAttribute(
				Constants.SESSION_FORMAT_PERMS_KEY, formatPerms);
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public String getErrMsg() {
		return errMsg;
	}
}
