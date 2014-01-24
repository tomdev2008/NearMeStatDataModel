<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>管理员管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<body class="context" onload="init();">
		<div class="title">
			管理员管理
		</div>
		<div id="admin_list" class="data-load">
			<!-- 
			<s:a action="../sys/admin/admin_doAddAdmin.do">添加用户</s:a>
			-->

			<table class="data-load" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						ID
					</th>
					<th>
						登录名
					</th>
					<th>
						真实姓名
					</th>
					<th>
						email
					</th>
					<th>
						联系电话
					</th>
					<th>
						登录IP限制
					</th>
					<th>
						分组
					</th>
					<th>
						角色
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="admins">
					<tr>
						<td>
							<s:property value="id" />
						</td>
						<td>
							<s:property value="userName" />
						</td>
						<td>
							<s:property value="realName" />
						</td>
						<td>
							<s:property value="email" />
						</td>
						<td>
							<s:property value="mobileNumber" />
						</td>

						<td title="${ipLimit }">
							<s:if test="userName != 'admin'">
								<s:if test="ipLimit==0">
									<font color="#000">只允许白名单</font>
								</s:if>
								<s:if test="ipLimit==1 ">
									<font color="#CCCC99">只禁止黑名单</font>
								</s:if>
								<s:if test="ipLimit==2">
									<font color="#CC9900">无限制</font>
								</s:if>
							</s:if>
						</td>
						<td title="${groupIDs }">
							<s:if test="userName=='admin'">超级管理员</s:if>
							<s:if test="userName!='admin'">
							    <span name="span_group" title="${groupNames}">${groupNames}</span>
							</s:if>
						</td>
						<td title="${roleIDs }" class="data-fixlength">
							<s:if test="userName=='admin'">超级管理员</s:if>
							<s:if test="userName!='admin'">
								<span name="span_role" title="${roleNames}">${roleNames}</span>
							</s:if>
						</td>

						<td>
							<!-- keke账号不能修改密码 -->
							<s:if test="userPasswd != null and userPasswd != ''">
								<s:a action="admin_modifyPasswd.do">
									<s:param name="id" value="id" />
									<s:param name="userName" value="userName" />
								          修改密码
								</s:a>
							</s:if>
							<s:a action="admin_adminModify.do">
								<s:param name="id" value="id" />
								<s:param name="userName" value="userName" />
								<s:param name="realName" value="realName" />
								<s:param name="email" value="email" />
								<s:param name="mobileNumber" value="mobileNumber" />
								<s:param name="ipLimit" value="ipLimit" />
								<s:param name="roleIDs" value="roleIDs" />
								<s:param name="groupIDs" value="groupIDs" />
								           更改信息
								</s:a>
							<s:a action="admin_deleteAdmin.do">
								<s:param name="id" value="id" />
								           删除
								</s:a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<br>
		<br>
		<s:form method="post" action="admin_doAddAdmin.do" cssClass="submit">
			<div class="title" align="center">
				添加用户
			</div>
			<table class="submit" align="center" border="1" cellspacing="0"
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
						分组:
					</td>
					<td>
					    <!-- 
					    <s:select name="addForm.groupIDs" list="groups" label="分组"
							listKey="id" listValue="groupname"></s:select>
					    -->
						<select id="mulslt_group" multiple="multiple" size="5" name="addForm.groupIDs">
							<s:iterator value="groups">
								<option value="${id}">${groupname}</option>
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
					    <s:select name="addForm.roleIDs" list="roles" label="角色"
							listKey="id" listValue="roleName"></s:select>
					    -->
						<select id="mulslt_role" multiple="multiple" size="5" name="addForm.roleIDs">
							<s:iterator value="roles">
								<option value="${id}">${roleName}</option>
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
		<script type="text/javascript">
function init(){
	initRole();
	initGroup();
}
		
function initRole() {
	var role = document.getElementsByName("span_role");
	for(var i = 0; i < role.length ; i++){
		if ($(role[i]).html().length > 15){
			$(role[i]).html($(role[i]).html().substr(0,15) + "...");
		}
	}
}

function initGroup() {
	var group = document.getElementsByName("span_group");
	for(var i = 0; i < group.length ; i++){
		if ($(group[i]).html().length > 15){
			$(group[i]).html($(group[i]).html().substr(0,15) + "...");
		}
	}
}
</script>
	</body>
</html>



