<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>软件安装排行</title>
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
			软件安装排行
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<s:form namespace="/app/zjb">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input id="starttime" class="Wdate" type="text"
							name="form.startTime" value="${form.startTime}"
							style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<!-- 
						<sx:datetimepicker name="form.startTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
						-->
						粒度
						<s:select id="lidu"
							list="#{'DAILY':'日','WEEKLY':'周','MONTHLY':'月'}" name="form.lidu">
						</s:select>
						版本
						<myTags:ZjbVersionSelect clientID="form.appVersion"
							clientName="form.appVersion" addEmptyAll="true"
							value="${form.appVersion}">
						</myTags:ZjbVersionSelect>
						<s:submit name="query" value="查询" action="zjb_softinstall_query">
						</s:submit>
						<s:submit name="export" value="导出" action="zjb_softinstall_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						时间
					</th>
					<th onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="软件ID">
						软件ID
					</th>
					<th onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="软件名称">
						软件名称
					</th>
					<th onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="软件安装数量">
						软件安装数量
					</th>
				</tr>
				<s:iterator value="softinstallList">
					<tr>
						<td>
							<s:if test="form.lidu == 'DAILY'">
								<s:date name="statDate" format="MM-dd" />
							</s:if>
							<s:if test="form.lidu == 'WEEKLY'">
								<s:date name="statDate" format="MM-dd" />~<s:date
									name="statEndDate" format="MM-dd" />
							</s:if>
							<s:if test="form.lidu == 'MONTHLY'">
								<s:date name="statDate" format="yyyy-MM" />
							</s:if>
						</td>
						<td>
							${softID }
						</td>
						<td>
							${softName }
						</td>
						<td>
							${softInstall }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

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
</script>
	</body>
</html>