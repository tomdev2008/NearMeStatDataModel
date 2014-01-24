<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>资源包安装</title>
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
			资源包安装
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
						粒度
						<s:select id="lidu"
							list="#{'DAILY':'日','WEEKLY':'周','MONTHLY':'月'}" name="form.lidu"
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
						<s:submit name="query" value="查询" action="zjb_pkginstall_query">
						</s:submit>
						<s:submit name="export" value="导出" action="zjb_pkginstall_export">
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
						title="该时间段内通过装机宝在线及自定义资源包所产生的软件安装总量">
						安装总量
					</th>
					<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该时间段内通过点击装机宝所有资源包一键安装按钮所产生的软件安装量">
						一键安装量
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="一键安装量/安装总量	">
						一键安装占比（%）
					</th>
					<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该时间段内通过点击装机宝自定义资源包一键安装按钮所产生的软件安装量">
						自定义资源一键安装量
					</th>
					<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="自定义资源一键安装量/安装总量">
						自定义资源一键安装占比（%）
					</th>
					<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该时间段内通过点击装机宝在线资源包一键安装按钮所产生的软件安装量">
						在线资源一键安装量
					</th>
					<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="在线资源一键安装量/安装总量">
						在线资源包一键安装占比（%）
					</th>
					<th onclick="showTblChart(8)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该时间段内通过进入所有资源包内点击单个资源安装及勾选软件批量安装所产生的软件安装量">
						包内安装量
					</th>
					<th onclick="showTblChart(9)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="包内安装量/安装总量">
						包内安装占比（%）
					</th>
					<th onclick="showTblChart(10)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该时间段内通过进入自定义资源包内点击单个资源安装及勾选软件批量安装所产生的软件安装量">
						自定义资源包内安装量
					</th>
					<th onclick="showTblChart(11)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="自定义资源包内安装量/安装总量">
						自定义资源包内安装占比（%）
					</th>
					<th onclick="showTblChart(12)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="该时间段内通过进入在线资源包内点击单个资源安装及勾选软件批量安装所产生的软件安装量">
						在线资源包内安装量
					</th>
					<th onclick="showTblChart(13)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="在线资源包内安装量/安装总量">
						在线资源包内安装占比（%）
					</th>
				</tr>
				<s:iterator value="pkginstallList">
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
							${installtotal }
						</td>
						<td>
							${keycnt }
						</td>
						<td>
							${keyratio }
						</td>
						<td>
							${selfcnt }
						</td>
						<td>
							${selfratio }
						</td>
						<td>
							${onlinecnt }
						</td>
						<td>
							${onlineratio }
						</td>
						<td>
							${innercnt }
						</td>
						<td>
							${innerratio }
						</td>
						<td>
							${selfinnercnt }
						</td>
						<td>
							${selfinnerratio }
						</td>
						<td>
							${onlineinnercnt }
						</td>
						<td>
							${onlineinnerratio }
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
		starttime = new Date().dateAdd('w', -8).format("yyyy-MM-dd");
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