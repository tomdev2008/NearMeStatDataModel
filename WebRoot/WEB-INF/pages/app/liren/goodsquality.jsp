<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>商品质量分布</title>
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
			商品质量分布
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<s:form namespace="/app/liren">
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
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						<s:submit name="query" value="查询"
							action="liren_goodsquality_query">
						</s:submit>
						<s:submit name="export" value="导出"
							action="liren_goodsquality_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>

		<s:if test="browsetotalList != null and browsetotalList.size > 0">
			<div class="title">
				表一：
			</div>
			<div id="chartDiv" align="center"></div>
			<div class="data-load">
				<table id="dataSourceTbl" class="data-load" border="1"
					cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
					<tr class="data-head">
						<th onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand"
							title="当天的新商品数，平均每个商品被多少设备浏览过（丽人系统中对浏览有两种定义，一种是浏览总量，一种是浏览详情页，这里取“浏览总量”）；">
							每商品被多少人浏览（总）
						</th>
						<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand"
							title="在每个区间内的数量">
							数量
						</th>
						<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand"
							title="该区间占到实际数量的比例">
							占比（%）
						</th>
					</tr>
					<s:iterator value="browsetotalList">
						<tr>
							<td>
								${betweentype }
							</td>
							<td>
								${numb }
							</td>
							<td>
								${zanbi }
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>

		<s:if test="browsedetailList != null and browsedetailList.size > 0">
			<div class="title">
				表二：
			</div>
			<div id="chartDiv2" align="center"></div>
			<div class="data-load">
				<table id="dataSourceTbl2" class="data-load" border="1"
					cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
					<tr class="data-head">
						<th onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand"
							title="当天的新商品数，平均每个商品被多少设备浏览过（丽人系统中对浏览有两种定义，一种是浏览总量，一种是浏览详情页，这里取“浏览详情页”）；">
							每商品被多少人浏览（详情）
						</th>
						<th onclick="showTblChart2(1)"
							onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
							style="cursor: hand" title="在每个区间内的数量">
							数量
						</th>
						<th onclick="showTblChart2(2)"
							onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
							style="cursor: hand" title="该区间占到实际数量的比例">
							占比（%）
						</th>
					</tr>
					<s:iterator value="browsedetailList">
						<tr>
							<td>
								${betweentype }
							</td>
							<td>
								${numb }
							</td>
							<td>
								${zanbi }
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>

		<s:if test="likeList != null and likeList.size > 0">
			<div class="title">
				表三：
			</div>
			<div id="chartDiv3" align="center"></div>
			<div class="data-load">
				<table id="dataSourceTbl3" class="data-load" border="1"
					cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
					<tr class="data-head">
						<th onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand"
							title="当天的新商品数，平均每个商品被多少设备喜欢">
							每商品被多少人喜欢
						</th>
						<th onclick="showTblChart3(1)"
							onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
							style="cursor: hand" title="在每个区间内的数量">
							数量
						</th>
						<th onclick="showTblChart3(2)"
							onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
							style="cursor: hand" title="该区间占到实际数量的比例">
							占比（%）
						</th>
					</tr>
					<s:iterator value="likeList">
						<tr>
							<td>
								${betweentype }
							</td>
							<td>
								${numb }
							</td>
							<td>
								${zanbi }
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>

		<s:if test="qualityList != null and qualityList.size > 0">
			<div class="title">
				表四：
			</div>
			<div id="chartDiv4" align="center"></div>
			<div class="data-load">
				<table id="dataSourceTbl4" class="data-load" border="1"
					cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
					<tr class="data-head">
						<th onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand"
							title="当天新商品的平均品质">
							商品平均品质
						</th>
						<th onclick="showTblChart4(1)"
							onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
							style="cursor: hand" title="在每个区间内的数量">
							数量
						</th>
						<th onclick="showTblChart4(2)"
							onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
							style="cursor: hand" title="该区间占到实际数量的比例">
							占比（%）
						</th>
					</tr>
					<s:iterator value="qualityList">
						<tr>
							<td>
								${betweentype }
							</td>
							<td>
								${numb }
							</td>
							<td>
								${zanbi }
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>
		<script type="text/javascript">
showTblChart(1);
showTblChart2(1);
showTblChart3(1);
showTblChart4(1);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt", "800",
			"400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl", cloumn, 40);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

function showTblChart2(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt", "800",
			"400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl2", cloumn, 40);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv2");
}

function showTblChart3(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt", "800",
			"400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl3", cloumn, 40);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv3");
}

function showTblChart4(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/Pie3D.swf", "fcfCahrt", "800",
			"400", "0", "1");
	chartText = table2FCFPieChart("dataSourceTbl4", cloumn, 40);
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv4");
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