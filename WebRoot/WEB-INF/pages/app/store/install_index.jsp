<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>单个竞品分析</title>
		<sx:head locale="zh" parseContent="true" extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" /></script>
		<script type="text/javascript" src="../../js/date.js" /></script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js"></script>
		<script type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>

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
			单个竞品分析
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<s:form namespace="/app/store" id="form">
			<s:hidden name="form.systemID"></s:hidden>
			<s:hidden name="form.statDateInt" id="hid_statdateint"></s:hidden>
			<table>
				<tr>
					<td>
						<input id="starttime" class="Wdate" type="text"
							name="form.startTime" value="${form.startTime}"
							style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						到
						<input id="endtime" class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						粒度
						<s:select id="lidu" list="#{'DAILY':'日','WEEKLY':'周','MONTHLY':'月'}" name="form.lidu"
							onchange="changeLidu();">
						</s:select>
						渠道
						<myTags:InstallSourceSelect systemID="${form.systemID}"
							clientID="form.packageName" clientName="form.packageName"
							addEmptyAll="false" value="${form.packageName}" >
						</myTags:InstallSourceSelect>
						<s:submit name="query" value="查询" action="store_installstat_queryJP"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div id="div_loading" align="right" style="height 20px;">&nbsp;</div>
		<div class="data-load" id="div_index" >
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						日期
					</th>
					<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="该时间段内该产品在OPPO rom用户中的安装用户数">
						安装用户数
					</th>
					<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="该时间段内该产品在OPPO rom用户中的安装次数">
						安装次数
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="该时间段内该产品在OPPO rom用户中的启动用户数">
						启动用户数
					</th>
					<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="该时间段内该产品在OPPO rom用户中的启动次数">
						启动次数
					</th>
					<th>
						下载来源分析
					</th>
				</tr>
				<s:iterator value="installFromAppList">
					<tr>
						<td>
							${statDateStr}
						</td>
						<td>
							${installImei}
						</td>
						<td>
							${installTimes }
						</td>
						<td>
							${startImei }
						</td>
						<td>
							${startTimes }
						</td>
						<td>
							<a href="#" onclick="toDetail('${statDate}');">查看</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="div_detail" style="display: none;"></div>
		<script type="text/javascript">
showTblChart(2);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

function toDetail(statdate){
	showLoading();
	
	$("#hid_statdateint").val(statdate);
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'store_installstat_detailJP.do',
		data : $('#form').serialize(),
		success : function(result) {
			hideLoading();
			$("#div_index").hide(2000);
			$("#div_detail").html(result);
			$("#div_detail").show(2000);
		},
		error : function(data) {
			hideLoading();
			alert("error:" + data.responseText);
		}
	});
}

function toBack(){
	$("#div_detail").hide(2000);
	$("#div_index").show(2000);
}

function showLoading(){
	html = "<img src=\"../../images/loading_2.gif\"/>&nbsp;<font color=\"red\" size=\"2\">数据正在加载...</font>&nbsp;&nbsp;&nbsp;";
	$("#div_loading").html(html);
}

function hideLoading(){
	$("#div_loading").html("&nbsp;");
}
</script>
	</body>
</html>