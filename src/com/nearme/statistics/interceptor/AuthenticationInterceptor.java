package com.nearme.statistics.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nearme.statistics.common.Constants;
import com.nearme.statistics.model.sys.admin.Admin;
import com.nearme.statistics.model.sys.admin.Parameters;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 权限认证拦截器
 * 
 * @author 刘超
 * @version 1.0
 * @since 1.0,2012-5-10
 */
public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = -8697336323020582007L;

	public void init() {
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation actionInvocation) throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getRequestURI();

		// 登录、退出不拦截
		if (path.contains("login") || path.contains("logout")
				|| path.contains("menu_select")) {
			return actionInvocation.invoke();
		}

		// 菜单不拦截
		if (path.contains("menu_select")) {
			return actionInvocation.invoke();
		}

		// 反馈不拦截
		if (path.contains("reflect_")) {
			return actionInvocation.invoke();
		}

		Admin admin = (Admin) ServletActionContext.getRequest().getSession()
				.getAttribute(Constants.SESSION_ADMIN_KEY);
		if (admin == null || admin.getId() <= 0) {
			return "logout_global";
		}

		List<Parameters> exceptUrlList = (List<Parameters>) ServletActionContext
				.getRequest().getSession().getAttribute(
						Constants.SESSION_EXCEPT_URL_KEY);
		if (exceptUrlList != null && exceptUrlList.size() > 0) {
			for (int i = 0, size = exceptUrlList.size(); i < size; i++) {
				if (path.contains(exceptUrlList.get(i).getpName())) {
					return actionInvocation.invoke();
				}
			}
		}

		// admin用户默认拥有所有权限,不拦截
		if (admin != null && "admin".equals(admin.getUserName())) {
			return actionInvocation.invoke();
		}

		// 除admin用户,拦截,进行权限过滤
		List<String> formatPerms = (List<String>) ServletActionContext
				.getRequest().getSession().getAttribute(
						Constants.SESSION_FORMAT_PERMS_KEY);
		for (int i = 0, size = formatPerms.size(); i < size; i++) {
			if (path.contains(formatPerms.get(i))) {
				return actionInvocation.invoke();
			}
		}

		return "noAccess";
	}

	public void destroy() {
	}
}
