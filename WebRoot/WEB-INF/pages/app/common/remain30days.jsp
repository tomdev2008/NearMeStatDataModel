<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>30天留存</title>
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
			30天留存
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
						到
						<input class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<s:select list="#{'STARTIMEI':'启动IMEI','NEWIMEI':'新增IMEI'}"
							name="form.type">
						</s:select>
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}"
							onchange="changeAppversion();">
						</myTags:AppVersionSelect>
						渠道
						<myTags:ChannelinfoSelect systemID="${form.systemID}"
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="true"
							value="${form.qudao}"
							onchange="changeQudao();">
						</myTags:ChannelinfoSelect>
						<s:submit name="query" value="查询" action="common_remain30days_query">
						</s:submit>
						<s:submit name="export" value="导出"
							action="common_remain30days_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						首次使用时间
					</th>
					<s:if test="form.type == 'STARTIMEI'">
					<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="新增用户">
						启动IMEI
					</th>
					</s:if>
					<s:if test="form.type == 'NEWIMEI'">
					<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="新增用户">
						新增IMEI
					</th>
					</s:if>
					<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="1天后留存率（%）">
						1天后（%）
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="2天后留存率（%）">
						2天后（%）
					</th>
					<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="3天后留存率（%）">
						3天后（%）
					</th>
					<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="4天后留存率（%）">
						4天后（%）
					</th>
					<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="5天后留存率（%）">
						5天后（%）
					</th>
					<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="6天后留存率（%）">
						6天后（%）
					</th>
					<th onclick="showTblChart(8)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="7天后留存率（%）">
						7天后（%）
					</th>
					<th onclick="showTblChart(9)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="14天后留存率（%）">
						14天后（%）
					</th>
					<th onclick="showTblChart(10)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="30天后留存率（%）">
						30天后（%）
					</th>
				</tr>
				<s:iterator value="remain30daysList">
					<tr>
						<td>
							<s:date name="statDate" format="MM-dd" />
						</td>
						<td>
							${remain }
						</td>
						<td>
							<s:if test="remainratio1day != 0">${remainratio1day }</s:if>
						</td>
						<td>
						    <s:if test="remainratio2day != 0">${remainratio2day }</s:if>
						</td>
						<td>
							<s:if test="remainratio3day != 0">${remainratio3day }</s:if>
						</td>
						<td>
							<s:if test="remainratio4day != 0">${remainratio4day }</s:if>
						</td>
						<td>
							<s:if test="remainratio5day != 0">${remainratio5day }</s:if>
						</td>
						<td>
							<s:if test="remainratio6day != 0">${remainratio6day }</s:if>
						</td>
						<td>
							<s:if test="remainratio7day != 0">${remainratio7day }</s:if>
						</td>
						<td>
							<s:if test="remainratio14day != 0">${remainratio14day }</s:if>
						</td>
						<td>
							<s:if test="remainratio30day != 0">${remainratio30day }</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
showTblChart(1);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

/**
 * 一个非全选，另外一个设置为全选
 */
function changeAppversion() {
	var appversion = document.getElementById("form.appVersion").value;
	if (appversion != 'all') {
		document.getElementById("form.qudao").value = 'all';
	}
}

function changeQudao() {
	var qudao = document.getElementById("form.qudao").value;
	if (qudao != 'all') {
		document.getElementById("form.appVersion").value = 'all';
	}
}
</script>
	</body>
</html>