<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>修改密码</title>
		<sx:head extraLocales="en-us,nl-nl,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
	</head>
	<body class="context">
		<s:form method="post" action="UserPassModify_update.do" id="form"
			cssClass="submit">
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
						<s:textfield readonly="true" name="form.id" label="用户ID*"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						用户名称*:
					</td>
					<td>
						<s:textfield readonly="true" name="form.userName" label="用户名称*"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						用户新密码*:
					</td>
					<td>
						<s:password name="form.adminNewPasswd" id="newPasswd"
							label="用户新密码*"></s:password>
					</td>
				</tr>
				<tr>
					<td>
						重新输入新密码*:
					</td>
					<td>
						<s:password name="form.adminNewPasswd2" id="newPasswd2"
							label="重新输入密码*"></s:password>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" id="passwdUpdate" value="提交"
							onclick="CheckInput()" class="btn-style" />
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
		<script language="javascript">
function CheckInput() {
	alert("sdfs");
if(document.getElementById("newPasswd").value==''){
            alert("密码不能为空");
            document.getElementById("newPasswd").fosus();
       
        }else if(document.getElementById("newPasswd").value!=document.getElementById("newPasswd2").value){
            alert("密码不一致");
            [color=#FF0000]document.getElementById("newPasswd").fosus();[/color]        }else {
            document.getElementById("form").submit();
        }
        
    }
        </script>
	</body>
</html>

