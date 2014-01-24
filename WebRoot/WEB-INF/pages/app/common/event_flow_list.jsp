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
			事件转化率
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="tips"><font color="red">${form.tips}</font></div>
		<s:form cssClass="submit" id="form" namespace="/app/common">
			<s:hidden id="hid_systemid" name="form.systemID" />
			<s:hidden id="hid_flowname" name="form.eventFlowName" />
			<table>
				<tr>
					<td>
					版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="false" value="${form.appVersion}" onchange="list()">
						</myTags:AppVersionSelect>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load">
			<table class="data-load" id="tblChannels" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						事件流名称
					</th>
					<th>
						目标事件名称
					</th>
					<th>
						步骤数
					</th>
					<th>
						最近一周转化率
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="eventFlowList">
					<tr>
						<td>
							${eventFlowName }
						</td>
						<td>
							${finalEventName }
						</td>
						<td>
							${eventFlowSteps }
						</td>
						<td>
							${eventTCR }
						</td>
						<td align="center">
							<a href="#" onclick="toDetail('${eventFlowName }');" >查看</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

<script type="text/javascript">

// 查询		
function list() {
	document.getElementById("form").action="common_eventconversion_query.do";
	document.getElementById("form").submit();
}
		
// 跳转到详情页面		
function toDetail(flowname) {
	$('#hid_flowname').val(flowname);
	
	document.getElementById("form").action="common_eventconversion_detail.do";
	document.getElementById("form").submit();
}
		
</script>
	</body>
</html>