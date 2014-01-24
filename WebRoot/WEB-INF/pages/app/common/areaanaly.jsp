<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>地区分析</title>
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
			地区分析
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv_top10" align="center"></div>
		<s:form id="form_top10" namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="form.startTime"
							value="${formdetail.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<!-- 
						<sx:datetimepicker name="form.startTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
						-->
						<s:select list="#{'STARTIMEI':'启动IMEI','NEWIMEI':'新增IMEI'}"
							name="form.type">
						</s:select>
						<input type="button" value="查询" onclick="submitQuerytop10();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_top10">
			<s:include value="areaanaly_top10.jsp" />
		</div>

		<div id="chartDiv_detail" align="center"></div>
		<s:form namespace="/app/common" id="form_detail">
			<s:hidden name="formdetail.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="formdetail.startTime"
							value="${formdetail.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						到
						<input class="Wdate" type="text" name="formdetail.endTime"
							value="${formdetail.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<!-- 
						<sx:datetimepicker name="formdetail.startTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
						到
						<sx:datetimepicker name="formdetail.endTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
					    -->
						<s:select list="#{'STARTIMEI':'启动IMEI','NEWIMEI':'新增IMEI'}"
							name="formdetail.type">
						</s:select>
						地区
						<s:select
							list="#{'GUANGDONG':'广东','JIANGSU':'江苏','HENAN':'河南',
							'HUNAN':'湖南','BEIJING':'北京','ZHEJIANG':'浙江','SICHUAN':'四川',
							'FUJIAN':'福建'}"
							name="formdetail.place">
						</s:select>
						<input type="button" value="查询" onclick="submitQuerydetail();" />
						<s:submit name="export" value="导出"
							action="common_areaanaly_exportdetail">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_detail">
			<s:include value="areaanaly_detail.jsp" />
		</div>
		<script type="text/javascript">
showTblChart(1);
showTblChartDetail(1);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt",
			"800", "400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl", cloumn, 40);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv_top10");
}

function showTblChartDetail(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl_detail", cloumn,
			"end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv_detail");
}

function submitQuerytop10() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_areaanaly_querytop10.do',
		data : $('#form_top10').serialize(),
		success : function(result) {
			$("#div_top10").html(result);
			showTblChart(1);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

function submitQuerydetail() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_areaanaly_querydetail.do',
		data : $('#form_detail').serialize(),
		success : function(result) {
			$("#div_detail").html(result);
			showTblChartDetail(1);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>