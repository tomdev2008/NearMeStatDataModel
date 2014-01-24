<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>渠道概况</title>
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
			渠道概况
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<s:form namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						<s:submit name="query" value="查询" action="common_channelgeneral_query">
						</s:submit>
						<s:submit name="export" value="导出"
							action="common_channelgeneral_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						渠道
					</th>
					<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="日新增IMEI">
						日新增IMEI
					</th>
					<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="日新增IMEI占比（%）">
						日新增IMEI占比（%）
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="日启动IMEI">
						日启动IMEI
					</th>
					<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="日启动IMEI占比（%）">
						日启动IMEI占比（%）
					</th>
					<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="累计IMEI">
						累计IMEI
					</th>
					<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="日新增账号">
						日新增账号
					</th>
					<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="日新增账号占比（%）">
						日新增账号占比（%）
					</th>
					<th onclick="showTblChart(8)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="日启动账号">
						日启动账号
					</th>
					<th onclick="showTblChart(9)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="日启动账号占比（%）">
						日启动账号占比（%）
					</th>
					<th onclick="showTblChart(10)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="累计账号">
						累计账号
					</th>
				</tr>
				<s:iterator value="channelgeneralList">
					<tr>
						<td>
							${channelname }
						</td>
						<td>
							${newimei }
						</td>
						<td>
							${newimeiratio }
						</td>
						<td>
							${startimei }
						</td>
						<td>
							${startimeiratio }
						</td>
						<td>
							${totalimei }
						</td>
						<td>
							${newssoid }
						</td>
						<td>
							${newssoidratio }
						</td>
						<td>
							${startssoid }
						</td>
						<td>
							${startssoidratio }
						</td>
						<td>
							${totalssoid }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
showTblChart(1);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt",
			"800", "400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl", cloumn, 40);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}
</script>
	</body>
</html>