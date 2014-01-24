<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>自定义事件设置</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" /></script>
		<script type="text/javascript" src="../../js/date.js" /></script>
		<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
		<link href="../../css/jquery.msgbox.css" type="text/css" rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.dragndrop.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.msgbox.js" type="text/javascript"></script>
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
			自定义事件设置
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		
		<s:form cssClass="submit" namespace="/app/commonsetting">
			<s:hidden id="hid_systemid" name="form.systemID"></s:hidden>
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						事件分组
					</td>
					<td>
						<input type="text" id="txt_eventdesc" name="form.eventDesc" />
					</td>
				</tr>
				<tr>
					<td>
						事件ID
					</td>
					<td>
						<input type="hidden" id="hid_id" name = "form.id" />
						<input type="text" id="txt_eventid" name="form.eventID" />
					</td>
				</tr>
				<tr>
					<td>
						事件名称
					</td>
					<td>
						<input type="text" id="txt_eventname" name="form.eventName" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnadd" value="添加" class="btn-style"
							onclick="addEvent();" style="display: block;" />
						<input type="button" id="btnupdate" value="更新" class="btn-style"
							onclick="updateEvent();" style="display: none;" />
					</td>
				</tr>
			</table>
		</s:form>
		

		<div class="data-load">
			<table class="data-load" id="tblChannels" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						事件分组
					</th>
					<th>
						事件ID
					</th>
					<th>
						事件名称
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="eventList">
					<tr>
						<td>
							${eventDesc }
						</td>
						<td>
							${eventID }
						</td>
						<td>
							${eventName }
						</td>
						<td align="center">
							<input type="button" value="编辑" class="btn-style"
								onclick="btnEditClick('${id}','${eventID}','${eventName}','${eventDesc}')" />
							<input type="button" value="删除" class="btn-style"
								onclick="if(confirm('确定删除  [${eventName }]?')){btnDeleteClick('${form.systemID }','${id}');}" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
// 编辑		
function btnEditClick(id,eventID, eventName, eventDesc) {
	$('#hid_id').val(id);
	$('#txt_eventid').val(eventID);
	$('#txt_eventname').val(eventName);
	$('#txt_eventdesc').val(eventDesc);

	document.getElementById("btnadd").style.display = 'none';
	document.getElementById("btnupdate").style.display = 'block';
}

// 删除
function btnDeleteClick(systemID, id) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_event_deleteKVEvent.do',
		data : '&form.systemID=' + systemID 
		     + '&form.id=' + id,
		success : function(result) {
			window.location.href = "setting_event_init.do?form.systemID="+systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 添加渠道
function addEvent() {
	var systemID = $('#hid_systemid').val();
	var eventID = $('#txt_eventid').val();
	var eventName = $('#txt_eventname').val();
	var eventDesc = $('#txt_eventdesc').val();
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_event_addKVEvent.do',
		data : '&form.systemID=' + systemID 
		     + '&form.eventID=' + eventID
			 + '&form.eventName=' + eventName
			 + '&form.eventDesc=' + eventDesc,
		success : function(result) {
			window.location.href = "setting_event_init.do?form.systemID="
					+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 更新
function updateEvent() {
	var id = $('#hid_id').val();
	var systemID = $('#hid_systemid').val();
	var eventID = $('#txt_eventid').val();
	var eventName = $('#txt_eventname').val();
	var eventDesc = $('#txt_eventdesc').val();

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_event_updateKVEvent.do',
		data : '&form.id=' + id
			 + '&form.systemID=' + systemID 
		     + '&form.eventID=' + eventID
			 + '&form.eventName=' + eventName
			 + '&form.eventDesc=' + eventDesc,
		success : function(result) {
			window.location.href = "setting_event_init.do?form.systemID="
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