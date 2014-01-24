<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>单个资源运营</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
</script>
		<script type="text/javascript" src="../../js/date.js" />
</script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js">
</script>
		<script type="text/javascript"
			src="../../js/My97DatePicker/WdatePicker.js">
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
			单个资源运营
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		
		<s:form id="form" namespace="/app/zjb">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input id="starttime" class="Wdate" type="text"
							name="form.startTime" value="${form.startTime}"
							style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						粒度
						<s:select id="lidu"
							list="#{'DAILY':'日','WEEKLY':'周','MONTHLY':'月'}" name="form.lidu"
							onchange="changeLidu();">
						</s:select>
						版本
						<myTags:ZjbVersionSelect clientID="form.appVersion"
							clientName="form.appVersion" addEmptyAll="true"
							value="${form.appVersion}">
						</myTags:ZjbVersionSelect>
						渠道
						<myTags:ZjbChannelSelect
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="true"
							value="${form.qudao}">
						</myTags:ZjbChannelSelect>
						软件名
						<input type="text" name="form.softName" width="20px" />
						<input type="button" value="查询" onclick="submitQuery();" />
						<s:submit name="export" value="导出" action="zjb_runpoint_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div id="chartDiv" align="center"></div>
		
		<div class="data-load" id="div_result">
			<s:include value="runpoint_result.jsp" />
		</div>
		<script type="text/javascript">
function changeLidu() {
	var starttime;
	var endtime;

	if (document.getElementById("lidu").value == "DAILY") {
		starttime = new Date().dateAdd('d', -1).format("yyyy-MM-dd");
		endtime = new Date().format("yyyy-MM-dd");
	} else if (document.getElementById("lidu").value == "WEEKLY") {
		starttime = new Date().dateAdd('w', -1).format("yyyy-MM-dd");
		endtime = new Date().format("yyyy-MM-dd");
	} else {
		starttime = new Date().dateAdd('m', -1).format("yyyy-MM-dd");
		endtime = new Date().format("yyyy-MM-dd");
	}

	document.getElementById("starttime").value = starttime;
	document.getElementById("endtime").value = endtime;
}

// 查询
function submitQuery() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'zjb_runpoint_query.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_result").html(result);
			document.getElementById('div_search').scrollIntoView();
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 添加软件名
function addSoft(softname,lidu) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'zjb_runpoint_addSoft.do',
		data : '&form.softName=' + softname
		       + '&form.lidu=' + lidu,
		success : function(result) {
			window.location.href = "zjb_runpoint_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 删除软件名
function deleteSoft(softname,lidu) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'zjb_runpoint_deleteSoft.do',
		data : '&form.softName=' + softname
		       + '&form.lidu=' + lidu,
		success : function(result) {
			window.location.href = "zjb_runpoint_init.do?";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>