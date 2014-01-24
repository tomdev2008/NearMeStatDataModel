<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>时段分析</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" /></script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js"></script>
		<script type="text/javascript"	src="../../js/My97DatePicker/WdatePicker.js"></script>

		<link href="../../css/jquery.msgbox.css" type="text/css" rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.dragndrop.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.msgbox.js" type="text/javascript"></script>
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
			时段分析
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						时段
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="昨天在该时段内的总启动次数">
						昨天启动次数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="7天前在该时段内的总启动次数">
						7天前启动次数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="30天前在该时段内的总启动次数">
						30天前启动次数
					</th>
				</tr>
				<s:iterator value="hhStartList">
					<tr>
						<td>
							${hh }
						</td>
						<td>
							${yesterday }
						</td>
						<td>
							${last7 }
						</td>
						<td>
							${last30 }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
showTblChart();

function showTblChart() {
	var fcfCahrt = new FusionCharts("../../FCF/MSLine.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFMultiLineChart("dataSourceTbl", [1,2,3], "时段分析");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
	
}
</script>
	</body>
</html>