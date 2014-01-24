<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>访问页面</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" ></script>
		<script type="text/javascript" src="../../js/date.js" ></script>
		<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
		<link href="../../css/jquery.msgbox.css" type="text/css" rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.dragndrop.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.msgbox.js" type="text/javascript"></script>
		<script type="text/javascript"	src="../../js/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js"></script>
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
			访问页面
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<s:form cssClass="submit" id="form" namespace="/app/common">
			<s:hidden id="hid_systemid" name="form.systemID" />
			<table width="90%">
				<tr>
					<td>
					渠道
						<myTags:ChannelinfoSelect systemID="${form.systemID}" 
						    clientID="form.qudao" clientName="form.qudao"
							addEmptyAll="true" value="${form.qudao}" onchange="changeQudao();">
						</myTags:ChannelinfoSelect>
						
					版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						
					日期	
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						到
						<input class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<s:submit name="query" value="查询" action="common_pvvisitpage_query"></s:submit>
						
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						访问页面
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
						启动次数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数占比">
						启动次数占比
					</th>
				</tr>
				<s:iterator value="visitpageList">
					<tr>
						<td>
							${visitPages}
						</td>
						<td>
							${startTimes }
						</td>
						<td>
							${rate }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

<script type="text/javascript">
showTblChartColumn('dataSourceTbl',1,'chartDiv');

function showTblChartColumn(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/StackedColumn3D.swf", "fcfCahrt", "1100",
			"250", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

function showTblChart(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"250", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}
</script>
	</body>
</html>