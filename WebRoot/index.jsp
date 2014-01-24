<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>NearMe统计与分析系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<s:if test="#session.SESSION_ADMIN == null">
		<script type="text/javascript">
window.top.location.href = "login.do";
</script>
	</s:if>
	<s:if test="#session.SESSION_ADMIN != null">
		<frameset rows="50,*" cols="*" frameborder="NO" border="0"
			framespacing="0">
			<frame src="pages/HeaderPage.jsp" name="topFrame" id="topFrame"
				scrolling="No" noresize>
			<frameset rows="*" cols="250,*" framespacing="0" frameborder="NO"
				border="0">
				<frame src="pages/Menu-new.jsp" name="left" id="left" scrolling="auto"
					frameborder="0">
				<frame src="pages/Overview.jsp" name="right" id="right"
					frameborder="0" scrolling="Auto">
			</frameset>
		</frameset>
		<noframes>
			<body>
				You need to enable frames in your browser to see FusionCharts
				documentation.
			</body>
		</noframes>
	</s:if>
</html>