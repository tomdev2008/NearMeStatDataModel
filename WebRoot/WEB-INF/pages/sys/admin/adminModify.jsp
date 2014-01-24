<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>更改信息</title>
		<sx:head extraLocales="en-us,nl-nl,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="js/global.js"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		
		<link rel="stylesheet" type="text/css" href="../../assets/jquery.multiselect.css" />
		<link rel="stylesheet" type="text/css" href="../../assets/prettify.css" />
		<link rel="stylesheet" type="text/css" href="../../assets/style.css" />
		<link rel="stylesheet" type="text/css" href="../../assets/jquery-ui.css" />
		<script src="../../assets/jquery.js" type="text/javascript"></script>
		<script src="../../assets/jquery-ui.min.js" type="text/javascript"></script>
		<script src="../../assets/prettify.js" type="text/javascript"></script>
		<script src="../../assets/jquery.multiselect.js" type="text/javascript"></script>
		
<script type="text/javascript" language="JavaScript">
$(function(){
	$("#mulslt_group").multiselect({
        noneSelectedText: "--分组--",
        checkAllText: "全选",
        uncheckAllText: '全不选',
        selectedList:10
    });
});
 
$(function(){
	$("#mulslt_role").multiselect({
        noneSelectedText: "--角色--",
        checkAllText: "全选",
        uncheckAllText: '全不选',
        selectedList:10
    });
});
</script>
	</head>
	<body class="context">
		<s:form method="post" action="admin_doModify.do" cssClass="submit">
			<div class="title" align="center">
				更改信息
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
						<s:textfield readonly="true" name="form.userName" label="用户名*"></s:textfield>
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
					<td>
						登录限制*:
					</td>
					<td>
						<select name="form.ipLimit">
							<option value="0"
								<s:if test="form.ipLimit == 0">selected="selected"</s:if>>
								允许白名单
							</option>
							<option value="1"
								<s:if test="form.ipLimit == 1">selected="selected"</s:if>>
								允许黑名单
							</option>
							<option value="2"
								<s:if test="form.ipLimit == 2">selected="selected"</s:if>>
								无限制
							</option>
						</select>

					</td>
				</tr>
				<tr>
					<td>
						分组:
					</td>
					<td>
					    <!-- 
					    <s:select name="form.groupIDs" list="groups" label="分组"
							listKey="id" listValue="groupname"></s:select>
					    -->
						<select id="mulslt_group" multiple="multiple" size="5" name="form.groupIDs">
							<s:iterator value="groups">
							<option value="${id}" <s:if test="id in form.groupIDlist">selected="selected"</s:if>>${groupname}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						角色:
					</td>
					<td>
					    <!-- 
					    <s:select name="form.roleIDs" list="roles" label="角色"
							listKey="id" listValue="roleName"></s:select>
					    -->
						<select id="mulslt_role" multiple="multiple" size="5" name="form.roleIDs">
							<s:iterator value="roles">
								<option value="${id}" <s:if test="id in form.roleIDlist">selected="selected"</s:if>>${roleName}</option>
							</s:iterator>
						</select>
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
