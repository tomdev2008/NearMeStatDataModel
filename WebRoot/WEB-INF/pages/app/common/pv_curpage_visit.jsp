<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>页面访问路径</title>
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
			页面访问路径
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<s:form cssClass="submit" id="form" namespace="/app/common">
			<s:hidden id="hid_systemid" name="form.systemID" />
			<s:hidden id="hid_name" name="form.name" />
			<table width="90%">
				<tr>
					<td>
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
						<s:submit name="query" value="查询" action="common_pvpath_visit"></s:submit>
						
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_visit">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						描述
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">
						访问次数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">
						访问次数占比(%)
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">
						平均访问时长(秒)
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">
						访问时长占比(%)
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">
						跳出率(%)
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">
						跳转情况
					</th>
				</tr>
				<s:iterator value="visitList">
					<tr>
						<td>
							${curPage}
						</td>
						<td>
							${jumpTimes }
						</td>
						<td>
							${visitRate }
						</td>
						<td>
							${avgDuration }
						</td>
						<td>
							${durationRate }
						</td>
						<td>
							${exitRate }
						</td>
						<td>
							<a href="#" onclick="toDetail('${curPage}')">展开</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="data-load" id="div_jump" style="display: none;"></div>

<script type="text/javascript">
showTblChart('dataSourceTbl',1,'chartDiv');

function showTblChartColumn(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/StackedColumn3D.swf", "fcfCahrt", "1100",
			"250", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

function showTblChart(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"450", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

function toDetail(page){
	$('#hid_name').val(page);
	
	$.ajax( {
				type : "POST",
				dataType : "html",
				url : 'common_pvpath_jump.do',
				data : $('#form').serialize(),
				success : function(result) {
					$('#div_jump').html(result);
					$('#div_jump').show(500);
					$('#div_visit').hide(500);
				},
				error : function(data) {
					alert("error:" + data.responseText);
				}
			});
}

function to_back(){
	$('#div_visit').show(500);
	$('#div_jump').hide(500);
}
</script>
	</body>
</html>