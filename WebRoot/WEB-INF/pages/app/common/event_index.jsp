<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>自定义事件</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<link href="../../css/jquery.msgbox.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="../../js/global.js"></script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js"></script>
		<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>
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
			自定义事件
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<s:form id="form" namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<s:hidden name="form.eventKey" id="hid_eventkey"></s:hidden>
			<s:hidden name="form.eventValue" id="hid_eventvalue"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						到
						<input class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						事件
						<s:set name="tmp_group" value="" />
						<s:set name="tmp_eventid" value="form.eventID" />
						<select id="form_eventid" name="form.eventID">
							<s:iterator value="eventList">
								<s:if test="eventDesc != #tmp_group">
									<s:set name="tmp_group" value="eventDesc" />
									<optgroup label="${eventDesc}"/>
								</s:if>
								<option value="${eventID}" 
								<s:if test="eventID == #tmp_eventid">selected='selected'</s:if>>${eventName}</option>
							</s:iterator>
						</select>
						<a href="../commonsetting/setting_event_init.do?form.systemID=${form.systemID }">自定义事件设置</a>
						<s:submit name="query" value="查询" action="common_event_query">	</s:submit>
						<s:submit name="export" value="导出" 	action="common_event_export"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div id="div_flash_eventindex" align="center"></div>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						日期
					</th>
					<th onclick="showTblChart(1)"  onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动IMEI数">
						启动IMEI数
					</th>
					<th onclick="showTblChart(2)"  onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
						启动次数
					</th>
					<th onclick="showTblChart(3)"  onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="事件发生IMEI数">
						事件发生IMEI数
					</th>
					<th onclick="showTblChart(4)"  onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="事件发生次数">
						事件发生次数
					</th>
				</tr>
				<s:iterator value="indexList">
					<tr>
						<td>
							${statdateStr }
						</td>
						<td>
							${startImeis }
						</td>
						<td>
							${startCounts }
						</td>
						<td>
							${eventImeis }
						</td>
						<td>
							${eventCounts }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	<div id="div_loading" align="right" style="height 20px;">
		&nbsp;
	</div>
	<div id="div_page1" style="background:#EEEEEE; border-radius:15px 15px 15px 15px;">
	<s:form id="form_detail" namespace="/app/common">
	<table>
		<tr>
			<td> <strong>EventKey</strong>
			<select id="select_eventkey" onchange="queryDetailSum(this.value);">
				<s:iterator value="keyList">
					<option value="${eventKey}">${eventKey}</option>
				</s:iterator>
			</select>
			</td>
		</tr>
	</table>
	</s:form>
	<div class="data-load" id="div_sum">
			<table id="dataSourceTblPie" class="data-load" border="1"
		cellspacing="0" bordercolor="#999999" cellpadding="3">
				<tr>
					<th>
						EventValue
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="事件发生次数">
						事件发生次数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="占比">
						占比
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="操作">
						操作
					</th>
				</tr>
				<s:iterator value="sumList">
					<tr>
						<td>
							${eventValue }
						</td>
						<td>
							${eventCounts }
						</td>
						<td>
							${eventCountsPercent }%
						</td>
						<td>
							<a href="#" onclick="queryDetail('${eventValue }');">详情</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		</div>
		<div id="div_page2" style="background:#EEEEEE; border-radius:15px 15px 15px 15px;"></div>
		<script type="text/javascript">
showTblChart(3);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("div_flash_eventindex");
}

function showTblChartEventDetail(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTblEventDetail", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("div_flash_eventdetail");
}

function queryDetailSum(key){
	$('#hid_eventkey').val(key);
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_event_queryDetailSum.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_sum").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

function queryDetail(value){
	showLoading();
	$('#hid_eventvalue').val(value);
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_event_queryDetail.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_page1").hide(2000);
			$("#div_page2").html(result);
			$("#div_page2").show(2000);
			showTblChartEventDetail(1)
			hideLoading();
		},
		error : function(data) {
			hideLoading();
			alert("error:" + data.responseText);
		}
	});
}

function showLoading(){
	html = "<img src=\"../../images/loading_2.gif\"/>&nbsp;<font color=\"red\" size=\"2\">数据正在加载...</font>&nbsp;&nbsp;&nbsp;";
	$("#div_loading").html(html);
}

function hideLoading(){
	$("#div_loading").html("&nbsp;");
}

function to_back(){
	$("#div_page2").hide(2000);
	$("#div_page1").show(2000);
}
</script>
	</body>
</html>