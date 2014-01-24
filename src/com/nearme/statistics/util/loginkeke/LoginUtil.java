package com.nearme.statistics.util.loginkeke;

import org.apache.commons.httpclient.NameValuePair;

import com.oppo.http.HttpUtil;
import com.oppo.security.MD5Util;

/**
 * keke账号登录
 * 
 * @author 朱峰
 * @version 1.0
 * @since 1.0, 2013-9-24
 */
public class LoginUtil {
	// keke账号登陆地址
	private static final String KEKE_LOGIN_URL = "http://i.auth.nearme.com.cn/OPPONewSSO2/Login";

	/**
	 * CoCo账号登陆<br>
	 * 登录成功返回用户名<br>
	 * 登录失败返回null<br>
	 * 
	 * @param name
	 *            用户名/邮箱/手机号
	 * @param password
	 * @param ip
	 * @return
	 */
	public static String loginKeke(String name, String password, String ip) {
		SsoUserInfo sui = buildSui(name, password, ip);
		String requestxml = getRequestXmlString(sui);
		String responsexml;
		String result = null;

		try {
			NameValuePair[] params = new NameValuePair[] { new NameValuePair(
					"xml", requestxml) };
			responsexml = HttpUtil.getResponseByPost(KEKE_LOGIN_URL, params,
					"UTF-8");

			if (requestxml.contains("userName")) {
				result = (String) responsexml.subSequence(responsexml
						.indexOf("userName") + 9, responsexml
						.indexOf("/userName") - 1);
			}
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * 判断username类型<br>
	 * 0 用户名<br>
	 * 1 邮箱<br>
	 * 2 手机号<br>
	 * 
	 * @param username
	 * @return
	 */
	private static int checktype(String username) {
		if (username.contains("@")) {
			return 1;
		} else if (username.startsWith("1") && username.length() == 11) {
			try {
				Integer.parseInt(username);
				return 2;
			} catch (NumberFormatException e) {
			}
		}
		return 0;
	}

	/**
	 * 构造请求实体对象
	 * 
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	private static SsoUserInfo buildSui(String name, String password, String ip) {
		SsoUserInfo sui = new SsoUserInfo();
		// 根据username类型填充（用户名、email、mobile）
		switch (checktype(name)) {
		case 0:
			sui.setUserName(name);
			break;
		case 1:
			sui.setEmail(name);
			break;
		case 2:
			sui.setMobile(name);
			break;
		default:
			sui.setUserName(name);
			break;
		}
		sui.setPassword(MD5Util.md5(password, "UTF-8"));
		sui.setCreateIP(ip);// ip地址
		sui.setLoginType("0");// 登录类型0 为pc端，1为客户端
		sui.setApplicationKey("9effeac61b7ad92a9bef3da596f2158b");// 应用key(个人中心usercenter)
		return sui;
	}

	/**
	 * 发送请求，返回信息
	 * 
	 * @param sui
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static String getRequestXmlString(Object sui) {
		ObjectParse op = new ObjectParse();
		try {
			return op.generateObjectXML(sui, "OppoRequest", true);
		} catch (Exception e1) {
		}

		return "";
	}
}
