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
					<h3>
						<font color="red">${form.tips}</font>
					</h3>
				</td>
			</tr>
		</table>
	</body>
</html>