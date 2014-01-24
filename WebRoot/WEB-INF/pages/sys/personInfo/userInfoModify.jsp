<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>更改个人信息</title>
		<sx:head extraLocales="en-us,nl-nl,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
	</head>
	<body class="context">
		<s:form method="post" action="UserInfoModify_update.do"
			cssClass="submit">
			<div class="title" align="center">
				更改个人信息
			</div>
			<table class="submit" align="center" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						用户ID*:
					</td>
					<td>
						<s:textfield readonly="true" name="form.id" label="用户ID*"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						用户名*:
					</td>
					<td>
						<s:textfield name="form.userName" label="用户名*"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						用户真实姓名:
					</td>
					<td>
						<s:textfield name="form.realName" label="用户真实姓名"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						邮箱:
					</td>
					<td>
						<s:textfield name="form.email" label="邮箱"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						联系电话:
					</td>
					<td>
						<s:textfield name="form.mobileNumber" label="联系电话"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="提交" align="center" cssClass="btn-style"></s:submit>
						<s:if test="errMsg != null">
							<br />
							<CENTER>
								<font color="red">${errMsg }</font>
							</CENTER>
						</s:if>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
