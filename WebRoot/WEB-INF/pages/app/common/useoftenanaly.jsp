<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>使用频率时长分析</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js">
</script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js">
</script>
		<script type="text/javascript" src="../../js/jquery-1.4.2.min.js">
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
			日启动次数分析
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv_startcnt" align="center"></div>
		<s:form id="form_startcnt" namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<!-- 
						<sx:datetimepicker name="form.startTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
						-->
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						机型
						<myTags:ModelSelect clientID="form.model" clientName="form.model"
							addEmptyAll="true" value="${form.model}">
						</myTags:ModelSelect>
						<input type="button" value="查询" onclick="submitQuerystartcnt();" />
						<s:submit name="export" value="导出"
							action="common_useoftenanaly_exportstartcnt">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_startcnt">
			<s:include value="useoftenanaly_startcnt.jsp" />
		</div>

		<div class="title">
			日使用时长分析
		</div>
		<div id="chartDiv_usetime" align="center"></div>
		<s:form namespace="/app/common" id="form_detail">
			<s:hidden name="formusetime.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="formusetime.startTime"
							value="${formusetime.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<!-- 
						<sx:datetimepicker name="formusetime.startTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
						-->
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="formusetime.appVersion"
							clientName="formusetime.appVersion" addEmptyAll="true"
							value="${formusetime.appVersion}">
						</myTags:AppVersionSelect>
						机型
						<myTags:ModelSelect clientID="formusetime.model"
							clientName="formusetime.model" addEmptyAll="true"
							value="${formusetime.model}">
						</myTags:ModelSelect>
						<input type="button" value="查询" onclick="submitQueryusetime();" />
						<s:submit name="export" value="导出"
							action="common_useoftenanaly_exportusetime">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_usetime">
			<s:include value="useoftenanaly_usetime.jsp" />
		</div>
		<script type="text/javascript">
showTblChartStartcnt(1);
showTblChartUsetime(1);

function showTblChartStartcnt(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Bar2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFBar2DChart("dataSourceTbl", cloumn);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv_startcnt");
}

function showTblChartUsetime(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Bar2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFBar2DChart("dataSourceTbl_usetime", cloumn);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv_usetime");
}

function submitQuerystartcnt() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_useoftenanaly_querystartcnt.do',
		data : $('#form_startcnt').serialize(),
		success : function(result) {
			$("#div_startcnt").html(result);
			showTblChartStartcnt(1);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

function submitQueryusetime() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_useoftenanaly_queryusetime.do',
		data : $('#form_usetime').serialize(),
		success : function(result) {
			$("#div_usetime").html(result);
			showTblChartUsetime(1);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>
