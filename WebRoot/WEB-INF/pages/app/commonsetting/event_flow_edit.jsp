<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>事件流</title>
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
			事件流编辑
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<s:form id="form_add" namespace="/app/commonsetting">
			<s:hidden id="hid_systemid" name="form.systemID" />
			<s:hidden id="txt_flowname" name="form.eventFlowName" />
			<table>
				<tr>
					<td>事件流名称</td>
					<td><b>${form.eventFlowName}</b></td>
				</tr>
				<tr>
					<td valign="top">事件</td>
					<td>
						<table id="tbl_event">
							<s:iterator value="eventFlowList">
							<s:set id="finalEventID">${finalEventID}</s:set>
							<tr>
								<td>
									<s:set name="tmp_group" value="" />
									<select name="form.eventID">
										<s:iterator value="eventList">
											<s:if test="eventDesc != #tmp_group">
												<s:set name="tmp_group" value="eventDesc" />
												<optgroup label="${eventDesc}"/>
											</s:if>
											<option value="${eventID}"  
											<s:if test="finalEventID==eventID">selected</s:if>
											>${eventName}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<a href="#" onclick="addRow()">添加步骤</a>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<a href="#" onclick="delRow()">删除最后一步</a>
					</td>
					<td></td>
				</tr>
				<tr align="center">
					<td></td>
					<td>
						<input type="button" value="提交" onclick="edit()" />
						<a href="setting_eventflow_init.do?form.systemID=${form.systemID}">返回</a>
					</td>
				</tr>
			</table>
		</s:form>

<script type="text/javascript">
	function addRow(){
		var row = $("#tbl_event tr").length-1;
		$("<tr>"+$("#tbl_event tr:eq(0)").html()+"</tr>").insertAfter($("#tbl_event tr:eq("+row+")"));
	}
	function delRow(){
		var row = $("#tbl_event tr").length-1;
		if(row>1){
			$("#tbl_event tr:eq("+row+")").remove();
		}
	}
	function edit(){
		var row = $("#tbl_event tr").length;
		if(row<2){
			alert("至少2个步骤!");
			return;
		}
		
		document.getElementById("form_add").action="setting_eventflow_update.do";
		document.getElementById("form_add").submit();
	}
</script>
	</body>
</html>