<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>一键清理缓存</title>
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
	</head>
	<body class="context">
		<div class="title">
			一键清理缓存
		</div>

		<br>

		<div class="data-load">
			<s:form namespace="/sys/systemTable">
				<s:submit name="query" value="清除缓存"
					action="system_cachecontrol_clear">
				</s:submit>
			</s:form>
		</div>
	</body>
</html>