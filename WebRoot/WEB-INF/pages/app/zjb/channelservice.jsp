<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>分渠道服务数据</title>
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
			分渠道服务数据
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
						到
						<input id="endtime" class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<!--
						<sx:datetimepicker name="form.startTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
						到
						<sx:datetimepicker name="form.endTime" displayFormat="yyyy-MM-dd"
							toggleType="explode" toggleDuration="500" />
						-->
						粒度
						<s:select id="lidu"
							list="#{'DAILY':'日'}" name="form.lidu"
							onchange="changeLidu();">
						</s:select>
						版本
						<myTags:ZjbVersionSelect clientID="form.appVersion"
							clientName="form.appVersion" addEmptyAll="true"
							value="${form.appVersion}">
						</myTags:ZjbVersionSelect>
						渠道
						<myTags:ZjbChannelSelect
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="true"
							value="${form.qudao}">
						</myTags:ZjbChannelSelect>
						<s:submit name="query" value="查询" action="zjb_channelservice_query">
						</s:submit>
						<s:submit name="export" value="导出" action="zjb_channelservice_export">
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
					<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内通过装机宝登陆的账号数量(相同账号多次登录需去重)">
						登陆用户数
					</th>
					<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内登陆用户服务的非OPPO手机数量（连接成功即为服务，下同），同一台手机（1年内）多次服务，只记一次.">
						登陆用户服务的非OPPO手机数
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内登陆用户有效服务的非OPPO手机数（单台手机1天内累计成功安装5个软件即算有效服务）">
						分成手机数
					</th>
					<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内登陆用户服务的非OPPO手机上平均安装的软件个数（即：查询时间段内，非OPPO手机上累计安装的软件总数除以服务的非OPPO手机数），同一个软件在同一台手机上多次安装需去重。">
						登陆用户服务的非OPPO手机台均安装量
					</th>
					<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内登陆用户服务的OPPO手机数量，同一台手机（1年内）多次服务，只记一次。">
						登陆用户服务的OPPO手机数
					</th>
					<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内登陆用户有效服务的OPPO手机数（单台手机1天内累计成功安装5个软件即算有效服务）">
						分成手机数
					</th>
					<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内登陆用户服务的OPPO手机上平均安装的软件个数（即：查询时间段内，OPPO手机上累计安装的软件总数除以服务的OPPO手机数），同一个软件在同一台手机上多次安装需去重。">
						登陆用户服务的OPPO手机台均安装量
					</th>
					<th onclick="showTblChart(8)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内使用装机宝但没有登陆的电脑数">
						未登陆用户
					</th>
					<th onclick="showTblChart(9)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内未登陆用户服务的非OPPO手机数量（连接成功即为服务，下同），同一台手机（1年内）多次服务，只记一次。">
						未登陆用户服务的非OPPO手机数
					</th>
					<th onclick="showTblChart(10)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内未登陆用户有效服务的非OPPO手机数（单台手机1天内累计成功安装5个软件即算有效服务）">
						分成手机数
					</th>
					<th onclick="showTblChart(11)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内未登陆用户服务的非OPPO手机上平均安装的软件个数（即：查询时间段内，非OPPO手机上累计安装的软件总数除以服务的非OPPO手机数），同一个软件在同一台手机上多次安装需去重。">
						未登陆用户服务的非OPPO手机台均安装量
					</th>
					<th onclick="showTblChart(12)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内未登陆用户服务的OPPO手机数量，同一台手机（1年内）多次服务，只记一次。">
						未登陆用户服务的OPPO手机数
					</th>
					<th onclick="showTblChart(13)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内未登陆用户有效服务的OPPO手机数（单台手机1天内累计成功安装5个软件即算有效服务）">
						分成手机数
					</th>
					<th onclick="showTblChart(14)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="查询时间段内未登陆用户服务的OPPO手机上平均安装的软件个数（即：查询时间段内，OPPO手机上累计安装的软件总数除以服务的OPPO手机数），同一个软件在同一台手机上多次安装需去重。">
						未登陆用户服务的OPPO手机台均安装量
					</th>
				</tr>
				<s:iterator value="channelserviceList">
					<tr>
						<td>
							<s:if test="form.lidu == 'DAILY'">
								<s:date name="statDate" format="MM-dd" />
							</s:if>
						</td>
						<td>
							${in_users }
						</td>
						<td>
							${in_not_oppo }
						</td>
						<td>
							${in_not_oppo_bonus }
						</td>
						<td>
							${in_not_oppo_avg }
						</td>
						<td>
							${in_oppo }
						</td>
						<td>
							${in_oppo_bonus }
						</td>
						<td>
							${in_oppo_avg }
						</td>
						<td>
							${out_users }
						</td>
						<td>
							${out_not_oppo }
						</td>
						<td>
							${out_not_oppo_bonus }
						</td>
						<td>
							${out_not_oppo_avg }
						</td>
						<td>
							${out_oppo }
						</td>
						<td>
							${out_oppo_bonus }
						</td>
						<td>
							${out_oppo_avg }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
showTblChart(1);

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
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