<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>单个资源推广运营</title>
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
			单个资源推广运营
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div class="div_help_content">
			（查询比较耗时！！！查询结果请及时导出！！！）
		</div>
		
		<s:form id="form_resource" namespace="/app/game">
			<s:hidden name="formresource.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						游戏名
						<input type="text" name="formresource.resourcename" width="20px" 
						onkeydown='if(event.keyCode==13) return false;'/>
						<input type="button" value="查询" onclick="querySource();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_game">
			<s:include value="game.jsp" />
		</div>
		
		<s:form id="form" namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						至
						<input class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						渠道
						<myTags:ChannelinfoSelect systemID="${form.systemID}"
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="false"
							value="${form.qudao}">
						</myTags:ChannelinfoSelect>
						粒度
						<s:select id="lidu" list="#{'DAILY':'日','MONTHLY':'月'}"
							name="form.lidu" onchange="changeLidu();">
						</s:select>
						<s:submit name="query" value="查询" action="common_flowstruct_query">
						</s:submit>
						<s:submit name="export" value="导出"
							action="common_flowstruct_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div id="chartDiv" align="center"></div>
		
		<div class="data-load" id="div_updatenum">
			<s:include value="flowstruct_updatenum.jsp" />
		</div>

		<div class="data-load" id="div_resourcetype">
			<s:include value="flowstruct_resourcetype.jsp" />
		</div>
		
		<div class="data-load" id="div_topresource">
			<s:include value="flowstruct_topresource.jsp" />
		</div>
		
		<div class="data-load" id="div_naturemodlenum">
			<s:include value="flowstruct_naturemodlenum.jsp" />
		</div>
		
		<div class="data-load" id="div_operatepoint">
			<s:include value="flowstruct_operatepoint.jsp" />
		</div>
		<script type="text/javascript">
function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl_jobresult", cloumn,
			"end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

// 提交任务
function submitQuery() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_wholeuserlifecycle_query.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 结果表导出(不通过ajax提交)
function exportresult(jobID) {
	document.getElementById("form.jobID").value = jobID;
	document.getElementById('form_export').submit();
}
</script>
	</body>
</html>