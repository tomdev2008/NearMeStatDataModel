<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"
	import="java.util.*,com.nearme.statistics.model.sys.MenuItem"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>权限管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<style type="text/css">
</style>
	</head>
	<body class="context">
		<div class="title">
			权限管理
		</div>
		<form method="post" action="grantPermission.do" class="submit">
			<table class="data-load" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						请选中角色：
						<s:set name="tmp_roleid" value="roleID" />
						<select id="roleID" name="roleID"
							onchange="javascript:{document.forms[0].submit();}">
							<s:iterator value="roles" var="role">
								<option value="${id}" id="${systemID}" 
									<s:if test="id == #tmp_roleid">selected='selected'</s:if>>
									${roleName}
								</option>
							</s:iterator>
						</select>
						&nbsp;&nbsp;&nbsp;&lt;-请选中需要更改权限的角色 &nbsp;&nbsp;&nbsp;
						<input type="submit" id="btnSubmit" name="btnSubmit" value="快速保存"
							class="btn-style" />
					</td>
				</tr>
				<tr>
					<td>
						现有权限：
						<s:iterator value="menuList">
							<div class="mitem">
								<div class="mtext${menuTabIndex}">
									<input id="PERM_${menuKey}" type="checkbox"
										name="PERM_${menuKey}" />
									<label for="PERM_${menuKey}">
										<s:if test="menuTabIndex == 1">
											<b>${menuText}</b>
										</s:if>
										<s:if test="menuTabIndex != 1">
										${menuText}
										</s:if>
									</label>
								</div>
							</div>
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit" id="btnSubmit" name="btnSubmit" value="保存"
							class="btn-style" />
					</td>
				</tr>
			</table>
		</form>

		<script type="text/javascript" src="../../js/jquery-1.4.2.min.js">
</script>
		<script type="text/javascript">
var initCheckedList= [<s:iterator value="rolePerms" id="rp">'<s:property value="rp"/>',</s:iterator>];
$(function(){
	$(":checkbox").click(function(){
		var flag = $(this).attr("checked");
		var cid=this.id;
		//向上传递
		if(flag){
			$(":checkbox").each(function(){
			if(this.id&&this.id.length<cid.length&&cid.indexOf(this.id)==0){
				$(this).attr("checked",flag);
			}
			});
		}
		
		//向下传递
		$(":checkbox").each(function(){
			if(this.id&&this.id.length>cid.length&&this.id.indexOf(cid)==0){
				$(this).attr("checked",flag);
			}
			});
	});
});

function initChecked(){
	for(i=0;i<initCheckedList.length;i++){
		$("#PERM_"+initCheckedList[i]).attr("checked","checked");
	}
}

initChecked();
</script>
	</body>
</html>