<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>错误信息</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<script type="text/javascript" src="../FCF/FusionCharts.js">
</script>
	</head>
	<body>
		<table>
			<tr>
				<td>
					<img alt="加载中..." src="../../images/loading.gif">
				</td>
				<td>
					<h3>
						<font color="red">服务器繁忙，请稍后重试！</font>
					</h3>
				</td>
			</tr>
		</table>
		<!--
		<sx:tabbedpanel id="test2" cssStyle="width: 100%; height: 400px;"
			doLayout="true">
			<sx:div label="错误信息">
				<h3>
					Error Message
				</h3>

				<s:actionerror />

				<p>
					<s:property value="%{exception.message}" />
				</p>

				<hr />

				<h3>
					Technical Details
				</h3>

				<p>
					<s:property value="%{exceptionStack}" />
				</p>
			</sx:div>
		</sx:tabbedpanel>
	     -->
	</body>
</html>