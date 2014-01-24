<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>NearMe统计与分析系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/login.css" />
	</head>
	<body>
		<div class="login" align="center">
			<div class="main">
				<form method="post" action="login.do">
					<table>
						<tr class="data-head">
							<td align="center">
								<b>登录</b>
							</td>
						</tr>
						<tr>
							<td align="center">
								登录名：
								<input type="text" id="loginName" name="loginName"
									value="<%//从Cookie中读取，自动填充
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					if (ck.getName().equals("loginName")) {
						response.getWriter().write(ck.getValue());
					}
				}
			}%>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								密&nbsp;&nbsp;码：
								<input type="password" id="loginPwd" name="loginPwd" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input class="submit" type="submit" value="登录" />
							</td>
						</tr>
					</table>
					<s:if test="errMsg != null">
						<br />
						<CENTER>
							<font color="red">${errMsg }</font>
						</CENTER>
					</s:if>
					<CENTER>
						<font color="white">登陆IP：<%=com.nearme.statistics.util.IpUtil.getIPAddress(request)%></font>
					</CENTER>
				</form>
			</div>
		</div>
	</body>
</html>
