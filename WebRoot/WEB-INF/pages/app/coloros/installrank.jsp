<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>安装次数排行</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
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
			安装排行
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div class="div_help_content">
			（查询比较耗时！！！查询结果请及时导出！！！）
		</div>
		<s:form id="form" namespace="/app/coloros">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input type="button" value="返回" onclick="jumpPage();" />
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						到
						<input class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						机型
						<myTags:ModelSelect clientID="form.model" clientName="form.model"
							showOppoOnly="false" addEmptyAll="true" value="${form.model}"
							onchange="changeModel();">
						</myTags:ModelSelect>
						<input type="button" value="提交任务"
							onclick="if(confirm('                确认提交查询任务？\n（任务查询比较耗时，请务必确认好查询条件无误后再提交）')){submitQuery();}" />
						<input type="button" value="查看任务" onclick="queryJob();" />

						<s:if test="'admin' == #session.SESSION_ADMIN.userName">
							<input type="button" value="SQL查看" onclick="querySQL();" />
						</s:if>
					</td>
				</tr>
			</table>
		</s:form>

		<div class="data-load" id="div_listjob">
			<s:include value="../common/hive_listjob.jsp" />
		</div>
		<div id="chartDiv" align="center"></div>
		<div class="data-load" id="div_jobresult">
			<s:include value="installrank_jobresult.jsp" />
		</div>
		<script type="text/javascript">
function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt", "800",
			"400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl_jobresult", cloumn, 40);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

//返回周排行页面
function jumpPage() {
	var systemID = document.getElementsByName("form.systemID")[0].value;
	window.location.href = "coloros_installrank_init.do?form.systemID="+systemID;
}

// 提交任务
function submitQuery() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'coloros_installrank_query.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 查看任务
function queryJob() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'coloros_installrank_queryjob.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 结果表查看
function queryresult(jobID, resulttable, weidu, lidu) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'coloros_installrank_queryresult.do',
		data : '&form.jobID=' + jobID + '&form.resulttable=' + resulttable
				+ '&form.weidu=' + weidu + '&form.lidu=' + lidu,
		success : function(result) {
			$("#div_jobresult").html(result);
			document.getElementById('div_jobresult').scrollIntoView();
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 删除结果
function deleteresult(jobID, resulttable, weidu, lidu) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'coloros_installrank_deleteresult.do',
		data : '&form.jobID=' + jobID + '&form.resulttable=' + resulttable
				+ '&form.weidu=' + weidu + '&form.lidu=' + lidu,
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 查看SQL
function querySQL() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'coloros_installrank_querySQL.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

function changeModel() {
	var model = document.getElementById("form.model").value;
	if (model != 'all') {
		document.getElementById("form.appVersion").value = 'all';
		document.getElementById("usertype").value = 'all';
	}
}

// 结果表导出
function exportresult(jobID, resulttable, model) {
	// 指定action
	document.getElementById('form_export').action = "coloros_installrank_exportresult.do";

	document.getElementById("form.jobID").value = jobID;
	document.getElementById("form.resulttable").value = resulttable;
	document.getElementById("form.model").value = model;
	document.getElementById('form_export').submit();
}
</script>
	</body>
</html>