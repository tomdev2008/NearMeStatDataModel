<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>修改用户密码</title>
		<sx:head extraLocales="en-us,nl-nl,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
	</head>
	<body class="context">
		<s:form method="post" action="admin_doModifyPasswd.do" cssClass="submit">
			<div class="title" align="center">
				修改密码
			</div>
			<table class="submit" align="center" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						用户ID*:
					</td>
					<td>
						<s:textfield readonly="true" name="formModify.id" label="用户ID*">
						</s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						用户名称*:
					</td>
					<td>
						<s:textfield readonly="true" name="formModify.userName"
							label="用户名称*"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						用户新密码*:
					</td>
					<td>
						<s:password name="formModify.adminNewPasswd" label="用户新密码*"></s:password>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<s:submit value="提交" cssClass="btn-style"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>

