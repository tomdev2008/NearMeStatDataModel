<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>产品月报</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
</script>
		<script type="text/javascript" src="../../js/date.js" />
</script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js">
</script>
		<script type="text/javascript" src="../../js/jquery-1.4.2.min.js">
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
			产品月报
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<div id="chartDiv" align="center"></div>
		<s:form id="form" namespace="/app/game">
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
						机型
						<myTags:ModelSelect clientID="form.model" clientName="form.model"
							addEmptyAll="true" value="${form.model}">
						</myTags:ModelSelect>
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						产品
						<s:select list="productList" name="form.productid"
							value="form.productid" listKey="productid"
							listValue="productname">
						</s:select>
						<s:submit name="query" value="查询"
							action="game_productreportmonthly_query">
						</s:submit>
						<s:submit name="export" value="导出"
							action="game_productreportmonthly_export">
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
						title="历史上所有登录过游戏中心游戏产品的帐号数">
						总用户
					</th>
					<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="历史上启动下载游戏程序的次数">
						总下载
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="对应每月登录游戏的帐号数">
						登陆用户
					</th>
					<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="登录游戏的每月新增帐号数">
						新增用户
					</th>
					<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="当月产品从游戏中心下载次数">
						下载次数
					</th>
					<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="每月启动该游戏客户端的IMEI数">
						启动IMEI
					</th>
					<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="每月启动该游戏客户端的总次数">
						启动次数
					</th>
					<th onclick="showTblChart(8)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="活跃用户">
						活跃用户
					</th>
					<th onclick="showTblChart(9)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="活跃天数">
						活跃天数
					</th>
					<th onclick="showTblChart(10)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="30天内所有无登录任何一款游戏行为的帐号数">
						沉默用户
					</th>
					<th onclick="showTblChart(11)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="90天内所有无登录任何一款游戏行为的帐号数">
						流失用户
					</th>
					<th onclick="showTblChart(12)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="统计月新增帐号在后一月有登录行为的帐号数除以统计月新增帐号数">
						NRR1（%）
					</th>
					<th onclick="showTblChart(13)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="统计月新增帐号在后二月有登录行为的帐号数除以统计月新增帐号数">
						NRR2（%）
					</th>
					<th onclick="showTblChart(14)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="统计月新增帐号在后三月有登录行为的帐号数除以统计月新增帐号数">
						NRR3（%）
					</th>
					<th onclick="showTblChart(15)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="统计月登录帐号在后一月有登录行为的帐号数除以统计月登录帐号数">
						LRR1（%）
					</th>
					<th onclick="showTblChart(16)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="统计月登录帐号在后二月有登录行为的帐号数除以统计月登录帐号数">
						LRR2（%）
					</th>
					<th onclick="showTblChart(17)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="统计月登录帐号在后三月有登录行为的帐号数除以统计月登录帐号数">
						LRR3（%）
					</th>
				</tr>
				<s:iterator value="productreportmonthlyList">
					<tr>
						<td>
							<s:date name="statDate" format="MM-dd" />
						</td>
						<td>
							${totaluser }
						</td>
						<td>
							${totaldownload }
						</td>
						<td>
							${loginuser }
						</td>
						<td>
							${newuser }
						</td>
						<td>
							${downloadtimes }
						</td>
						<td>
							${startimei }
						</td>
						<td>
							${starttimes }
						</td>
						<td>
							${activeuser }
						</td>
						<td>
							${activedays }
						</td>
						<td>
							${silentuser }
						</td>
						<td>
							${lostuser }
						</td>
						<td>
							${nrr1 }
						</td>
						<td>
							${nrr2 }
						</td>
						<td>
							${nrr3 }
						</td>
						<td>
							${lrr1 }
						</td>
						<td>
							${lrr2 }
						</td>
						<td>
							${lrr3 }
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
</script>
	</body>
</html>