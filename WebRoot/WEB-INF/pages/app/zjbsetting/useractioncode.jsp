<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>用户行为编码</title>
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
			用户行为编码
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<div class="data-load">
			<table class="data-load" id="tblActions" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						行为ID
					</th>
					<th>
						行为编码
					</th>
					<th>
						行为名称
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="useractioncodeList">
					<tr>
						<td>
							${actionID }
						</td>
						<td>
							${actioncode }
						</td>
						<td>
							${actionname }
						</td>
						<td align="center">
							<input type="button" value="编辑" class="btn-style"
								onclick="btnEditClick('${actionID}','${actioncode}','${actionname}')" />
							<input type="button" value="删除" class="btn-style"
								onclick="if(confirm('确定删除  ${actioncode }  行为编码?')){btnDeleteClick('${actionID}');}" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br>
		<br>
		<s:form cssClass="submit" namespace="/app/zjbsetting">
			<s:hidden id="form.actionID" name="form.actionID"></s:hidden>
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						行为编码
					</td>
					<td>
						<input type="text" id="form.actioncode" name="form.actioncode" />
					</td>
				</tr>
				<tr>
					<td>
						行为名称
					</td>
					<td>
						<input type="text" id="form.actionname" name="form.actionname" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnadd" value="添加" class="btn-style"
							onclick="addAction();" style="display: block;" />
						<input type="button" id="btnupdate" value="更新" class="btn-style"
							onclick="updateAction();" style="display: none;" />
					</td>
				</tr>
			</table>
		</s:form>

		<script type="text/javascript">
// 编辑		
function btnEditClick(actionID, actioncode, actionname) {
	document.getElementById("form.actionID").value = actionID;
	document.getElementById("form.actioncode").value = actioncode;
	document.getElementById("form.actionname").value = actionname;

	document.getElementById("btnadd").style.display = 'none';
	document.getElementById("btnupdate").style.display = 'block';
}

// 删除
function btnDeleteClick(actionID) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_delete.do',
		data : '&form.actionID=' + actionID,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 添加渠道
function addAction() {
	var actioncode = document.getElementById("form.actioncode").value;
	var actionname = document.getElementById("form.actionname").value;
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_add.do',
		data : '&form.actioncode=' + actioncode
			 + '&form.actionname=' + actionname,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 更新
function updateAction() {
	var actionID = document.getElementById("form.actionID").value;
	var actioncode = document.getElementById("form.actioncode").value;
	var actionname = document.getElementById("form.actionname").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_update.do',
		data : '&form.actionID=' + actionID
			 + '&form.actioncode=' + actioncode
			 + '&form.actionname=' + actionname,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>