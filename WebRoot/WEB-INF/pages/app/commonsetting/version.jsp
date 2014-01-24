<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>版本管理</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
</script>
		<script type="text/javascript" src="../../js/date.js" />
</script>
		<link href="../../css/jquery.msgbox.css" type="text/css"
			rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-latest.min.js"
			type="text/javascript">
</script>
		<script src="../../js/jquery.dragndrop.min.js" type="text/javascript">
</script>
		<script src="../../js/jquery.msgbox.js" type="text/javascript">
</script>
		<script type="text/javascript" language="JavaScript">
$(function() {
	$("#reflect").click(function() {
		//alert("d");
			new $.msgbox( {
				onClose : function() {
				},
				title : '反馈提交',
				type : 'input',
				content : 'Enter your words:'
			}).show();
		});
});

//反馈点击提交
function suggestSubmit() {
	//路径信息
	var url = window.location.href;
	//反馈人
	var reflect_name = document.getElementById('reflect_name').value;
	//反馈信息
	var reflect_info = document.getElementById('reflect_info').value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : '../../reflect_reflect.do',
		data : 'url=' + url + '&detail=' + reflect_info + '&username='
				+ reflect_name,
		success : function(result) {
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</head>
	<body class="context">
		<div class="title">
			版本管理
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		
		<div class="div_help_content" id="div_help_content">
			<div class="body">
				<font color="red">说明：</font>如需统一操作，请先勾选选项。
			</div>
		</div>
		<div id="div_bothedit">
			<s:form cssClass="submit" namespace="/app/commonsetting">
				<s:hidden id="form.systemID" name="form.systemID"></s:hidden>
				<table id="tblEdit" class="submit" align="center" border="1"
					cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
					<tr>
						<td>
							是否显示
						</td>
						<td>
						    <input type="radio" name="hide" value="Y" checked="checked" />隐藏
							<input type="radio" name="hide" value="N" />显示
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<input type="button" id="btnupdate" value="统一标识" class="btn-style"
								onclick="btnBothChange();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		
		
		<div class="data-load">
			<table class="data-load" id="tblChannels" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th title="选项请勿超过限定数200">
						选项
					</th>
					<th>
						版本号
					</th>
					<th>
						版本名称【别名】
					</th>
					<th>
						是否隐藏
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="versionList">
					<tr>
					    <td align="center" width="30px">
							<input type="checkbox" id="${appVersion}" name="chkAppversion"
								value="${appVersion}" />
						</td>
						<td>
							${appVersion }
						</td>
						<td>
							${versionName }
						</td>
						<td>
							<s:if test='hide=="Y"'>
								<font color="#999">隐藏</font>
							</s:if>
							<s:if test='hide=="N"'>
								<font color="#FF8400">显示</font>
							</s:if>
						</td>
						<td align="center">
							<input type="button" value="编辑" class="btn-style"
								onclick="btnEditClick('${versionName}','${appVersion}','${hide}','${systemID}')" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br>
		<br>

		<div id="div_edit" style="display: none;">
		    <s:form cssClass="submit" namespace="/app/commonsetting">
			<s:hidden id="form.systemID" name="form.systemID"></s:hidden>
			<s:hidden id="form.hide" name="form.hide"></s:hidden>
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						版本号
					</td>
					<td>
						<input type="text" disabled="disabled" id="form.appVersion"
							name="form.appVersion" />
					</td>
				</tr>
				<tr>
					<td>
						版本名称【别名】
					</td>
					<td>
						<input type="text" id="form.versionName" name="form.versionName" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnupdate" value="更新" class="btn-style"
							onclick="updateInfo();" />
					</td>
				</tr>
			</table>
		</s:form>
		</div>

		<script type="text/javascript">
/**
 * 生成查询的版本
 */
function buildAppversions() {
	var chks = $("input[name='chkAppversion']:checked");
	var versions = "";
	for (i = 0; i < chks.length; i++) {
		versions += "'" + chks[i].value + "'";
		if (i != chks.length - 1) {
			versions += ",";
		}
	}
	return versions;
}

// 统一调整
function btnBothChange(){
	//限制查询选择太多
	var chks = $("input[name='chkAppversion']:checked");
	if(chks.length > 200){
		alert("批量操作选项超过200条，请拆分后再操作。。。");
		return;
	}
	
	var systemID = document.getElementById("form.systemID").value;
	var versions = buildAppversions();
	var hide = $('input[name="hide"]:checked').val();
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_version_update.do',
		data : '&form.systemID=' + systemID 
		     + '&form.appVersion=' + versions 
			 + '&form.hide=' + hide,
		success : function(result) {
			window.location.href = "setting_version_init.do?form.systemID="
					+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 编辑		
function btnEditClick(versionName, appVersion, hide, systemID) {
	document.getElementById("div_edit").style.display = 'block';
	
	document.getElementById("form.versionName").value = versionName;
	document.getElementById("form.appVersion").value = appVersion;
	document.getElementById("form.hide").value = hide;
	document.getElementById("form.systemID").value = systemID;
	
	document.getElementById('div_edit').scrollIntoView();
}

// 更新
function updateInfo() {
	var systemID = document.getElementById("form.systemID").value;
	var versionName = document.getElementById("form.versionName").value;
	var appVersion = document.getElementById("form.appVersion").value;
	var hide = document.getElementById("form.hide").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_version_update.do',
		data : "&form.systemID=" + systemID 
		     + "&form.appVersion='" + appVersion 
		     + "'&form.versionName=" + versionName
			 + "&form.hide=" + hide,
		success : function(result) {
			window.location.href = "setting_version_init.do?form.systemID="
					+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>