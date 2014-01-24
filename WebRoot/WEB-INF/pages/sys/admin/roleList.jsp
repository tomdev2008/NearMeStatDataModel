<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>角色管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="js/global.js">
</script>
	</head>
	<body class="context">
		<div class="title">
			角色管理
		</div>
		<div class="data-load">
			<table class="data-load" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						ID
					</th>
					<th>
						角色名称
					</th>
					<th>
						描述
					</th>
					<th>
						系统ID
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="roles">
					<tr>
						<td>
							<s:property value="id" />
						</td>
						<td>
							<s:property value="roleName" />
						</td>
						<td>
							<s:property value="roleDesc" />
						</td>
						<td>
							<s:property value="systemID" />
						</td>
						<td>
							<s:if test="roleName!='超级管理员'">
								<a href="javascript:{if(confirm('确认删除该角色?\r\n\r\n(角色删除之后,该角色的管理员将不具备登录权限外任何权限!)')){window.location.href='roleList.do?roleID=${id }&deleteRole=1';}}">删除</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>


		<br />
		<br />
		<form method="post" action="roleList.do" class="submit">
			<table class="submit" align="center" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						角色名：
						<input type="text" id="roleName" name="roleName" />
					</td>
				</tr>
				<tr>
					<td>
						角色描述:
						<textarea id="roleDesc" name="roleDesc" rows="5" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						系统ID：
						<input type="text" id="systemID" name="systemID" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="添加角色" id="btnAdd" name="btnAdd"
							class="btn-style" />
						<input type="reset" value="重新填写" class="btn-style" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" id="id" />
		</form>
		<script language="javascript" type="text/javascript">
if ("${errMsg}") {
	alert("${errMsg}");
}
if ("${successMsg}") {
	alert("${successMsg}");
}
</script>
	</body>
</html>