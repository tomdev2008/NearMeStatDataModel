<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>类目</title>
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
			类目
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<s:form namespace="/app/liren">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input id="starttime" class="Wdate" type="text"
							name="form.startTime" value="${form.startTime}"
							style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						<s:submit name="query" value="查询" action="liren_category_query">
						</s:submit>
						<s:submit name="export" value="导出" action="liren_category_export">
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
						类目
					</th>
					<th>
						时间
					</th>
					<th onclick="showTblChart2(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="应上新总量">
						应上新总量
					</th>
					<th onclick="showTblChart2(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该类目下当天上新商品的数量（此处的上新定义为用户能看到）">
						上新总量
					</th>
					<th onclick="showTblChart2(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="应上新次数">
						应上新次数
					</th>
					<th onclick="showTblChart2(5)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该类目下当天系统中进行“新商品上架”行为的次数（系统中某类目满足一定条件之后会进行新商品上架行为）">
						上新次数
					</th>
					<th onclick="showTblChart2(6)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该类目下，平均每个用户能够浏览多少个商品。用户的定义为设备数">
						每浏览商品数
					</th>
					<th onclick="showTblChart2(7)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该类目下，平均每个用户能够喜欢多少个商品。用户的定义为设备数（并且进行喜欢之前一定需要登录帐号）">
						每喜欢商品数
					</th>
					<th onclick="showTblChart2(8)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="当天访问过该类目的设备数">
						访问设备数
					</th>
					<th onclick="showTblChart2(9)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="当天访问过该类目的次数">
						访问次数
					</th>
					<th onclick="showTblChart2(10)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="最近一批商品上新时间">
						最近一批上新时间
					</th>
					<th onclick="showTblChart2(11)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="最近一批商品数量">
						最近一批数量
					</th>
					<th onclick="showTblChart2(12)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="最近一批商品平均被浏览量">
						最近一批平均被浏览量
					</th>
					<th onclick="showTblChart2(13)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="浏览“最新”设备数">
						浏览“最新”设备数
					</th>
					<th onclick="showTblChart2(14)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="浏览“最新”次数">
						浏览“最新”次数
					</th>
				</tr>
				<s:iterator value="categoryList">
					<tr>
						<td>
							${category }
						</td>
						<td>
							<s:date name="statDate" format="MM-dd" />
						</td>
						<td>
							${ysxtotal }
						</td>
						<td>
							${shangxinTotal }
						</td>
						<td>
							${ysxcnt }
						</td>
						<td>
							${shangxinCnt }
						</td>
						<td>
							${avgbrowse }
						</td>
						<td>
							${avglike }
						</td>
						<td>
							${visitImei }
						</td>
						<td>
							${visitCnt }
						</td>
						<td>
							${recentsxtime }
						</td>
						<td>
							${recentcnt }
						</td>
						<td>
							${avgrecentbrowse }
						</td>
						<td>
							${browsenewimei }
						</td>
						<td>
							${browsenewcnt }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
showTblChart2(2);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

function showTblChart2(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt",
			"800", "400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl", cloumn, 50);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

function changeLidu() {
	var starttime;
	var endtime;

	if (document.getElementById("lidu").value == "DAILY") {
		starttime = new Date().dateAdd('d', -30).format("yyyy-MM-dd");
		endtime = new Date().format("yyyy-MM-dd");
	} else if (document.getElementById("lidu").value == "WEEKLY") {
		starttime = new Date().dateAdd('w', -4).format("yyyy-MM-dd");
		endtime = new Date().format("yyyy-MM-dd");
	} else {
		starttime = new Date().dateAdd('m', -4).format("yyyy-MM-dd");
		endtime = new Date().format("yyyy-MM-dd");
	}

	document.getElementById("starttime").value = starttime;
	document.getElementById("endtime").value = endtime;
}
</script>
	</body>
</html>