<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>添加用户</title>
		<sx:head extraLocales="en-us,nl-nl,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
	</head>
	<body class="context">
		<s:form method="post" action="admin_doAddAdmin.do" cssClass="submit">
			<div class="title" align="center">
				添加用户
			</div>
			<table class="submit" align="center"  border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						用户名*:
					</td>
					<td>
						<s:textfield name="addForm.userName" label="用户名*"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						用户密码*:
					</td>
					<td>
						<s:password name="addForm.userPasswd" label="用户密码*"></s:password>
					</td>
				</tr>
				<tr>
					<td>
						用户真实姓名:
					</td>
					<td>
						<s:textfield name="addForm.realName" label="用户真实姓名"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						邮箱:
					</td>
					<td>
						<s:textfield name="addForm.email" label="邮箱"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						联系电话:
					</td>
					<td>
						<s:textfield name="addForm.mobileNumber" label="联系电话"></s:textfield>
					</td>
				</tr>
				<tr>

					<td>
						登录限制*:
					</td>
					<td>
						<select name="addForm.ipLimit">
							<option value="0">
								允许白名单
							</option>
							<option value="2">
								无限制
							</option>
							<option value="1">
								允许黑名单
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						角色:
					</td>
					<td>
						<s:select name="addForm.roleID" list="roles" label="角色"
							listKey="id" listValue="roleName"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="提交" cssClass="btn-style"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
