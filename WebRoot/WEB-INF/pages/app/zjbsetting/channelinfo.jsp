<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>渠道管理</title>
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
			渠道管理
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<div class="data-load">
			<table class="data-load" id="tblChannels" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						渠道ID
					</th>
					<th>
						渠道名称
					</th>
					<th>
						渠道描述
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="channelinfoList">
					<tr>
						<td>
							${channelID }
						</td>
						<td>
							${channelName }
						</td>
						<td>
							${channelDesc }
						</td>
						<td align="center">
							<input type="button" value="编辑" class="btn-style"
								onclick="btnEditClick('${channelID}','${channelName}','${channelDesc}')" />
							<input type="button" value="删除" class="btn-style"
								onclick="if(confirm('确定删除  ${channelName }  渠道?')){btnDeleteClick('${channelID}');}" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br>
		<br>
		<s:form cssClass="submit" namespace="/app/zjbsetting">
			<s:hidden id="form.channelID" name="form.channelID"></s:hidden>
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						渠道名称
					</td>
					<td>
						<input type="text" id="form.channelName" name="form.channelName" />
					</td>
				</tr>
				<tr>
					<td>
						渠道描述
					</td>
					<td>
						<input type="text" id="form.channelDesc" name="form.channelDesc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnadd" value="添加" class="btn-style"
							onclick="addChannelinfo();" style="display: block;" />
						<input type="button" id="btnupdate" value="更新" class="btn-style"
							onclick="updateChannelinfo();" style="display: none;" />
					</td>
				</tr>
			</table>
		</s:form>

		<script type="text/javascript">
// 编辑		
function btnEditClick(channelID, channelname, channeldesc) {
	document.getElementById("form.channelID").value = channelID;
	document.getElementById("form.channelName").value = channelname;
	document.getElementById("form.channelDesc").value = channeldesc;

	document.getElementById("btnadd").style.display = 'none';
	document.getElementById("btnupdate").style.display = 'block';
}

// 删除
function btnDeleteClick(channelID) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_channelinfo_delete.do',
		data : '&form.channelID=' + channelID,
		success : function(result) {
			window.location.href = "setting_channelinfo_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 添加渠道
function addChannelinfo() {
	var channelID = document.getElementById("form.channelID").value;
	var channelName = document.getElementById("form.channelName").value;
	var channelDesc = document.getElementById("form.channelDesc").value;
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_channelinfo_add.do',
		data : '&form.channelID=' + channelID
			 + '&form.channelName=' + channelName
			 + '&form.channelDesc=' + channelDesc,
		success : function(result) {
			window.location.href = "setting_channelinfo_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 更新
function updateChannelinfo() {
	var channelID = document.getElementById("form.channelID").value;
	var channelName = document.getElementById("form.channelName").value;
	var channelDesc = document.getElementById("form.channelDesc").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_channelinfo_update.do',
		data : '&form.channelID=' + channelID
			 + '&form.channelName=' + channelName
			 + '&form.channelDesc=' + channelDesc,
		success : function(result) {
			window.location.href = "setting_channelinfo_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>