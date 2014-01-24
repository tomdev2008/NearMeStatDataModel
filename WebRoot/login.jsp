<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>NearMe统计与分析系统</title>
		<meta content="width=device-width, initial-scale=1.0" name="viewport"></meta>
		<meta content="" name="description"></meta>
		<meta content="" name="author"></meta>
		<!-- Le styles -->
		<link rel="stylesheet" href="css/bootstrap.min.css"></link>
		<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css"></link>
	</head>
	<body>
		<div class="container">
			<form class="form-signin" method="post" action="login.do">
				<h2 class="form-signin-heading">
					登录
				</h2>
				<input class="input-block-level" type="text" id="loginName"
					name="loginName" placeholder="用户名"
					value="<%
	        //从Cookie中读取，自动填充
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					if (ck.getName().equals("loginName")) {
						response.getWriter().write(ck.getValue());
					}
				}
			}%>"></input>
				<input class="input-block-level" type="password" id="loginPwd"
					name="loginPwd" placeholder="密码"></input>
				<!-- 
				<label class="checkbox">
					<input type="checkbox" value="remember-me"></input>
					记住密码
				</label>
				-->
				<button class="btn btn-large btn-primary" type="submit">
					登录
				</button>
				<%--
				<a href="http://uc.nearme.com.cn/usercenter/login.jsp?backurl=http://http://localhost:8080/OStat_NearMe/login.do">nearme账号登录</a>
				--%>
				<s:if test="errMsg != null">
					<br>
					<br>
					<font color="red"> ${errMsg } <br> <!-- 
					登陆IP：<%=com.nearme.statistics.util.IpUtil
						.getIPAddress(request)%>
					--> </font>
				</s:if>
			</form>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="js/jquery-1.4.2.min.js">
</script>
		<script src="js/bootstrap.min.js">
</script>
	</body>
</html>



