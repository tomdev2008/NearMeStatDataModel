<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>搜索关键字</title>
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
			搜索关键字
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
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
						粒度
						<s:select id="lidu" list="#{'DAILY':'日'}" name="form.lidu">
						</s:select>
						网络
						<myTags:NetworkSelect clientID="form.network"
							clientName="form.network" addEmptyAll="true"
							value="${form.network}">
						</myTags:NetworkSelect>
						机型
						<myTags:ModelSelect clientID="form.model" clientName="form.model"
							addEmptyAll="true" value="${form.model}">
						</myTags:ModelSelect>
						<s:submit name="query" value="查询" action="game_searchkey_query">
						</s:submit>
						<s:submit name="export" value="导出" action="game_searchkey_export">
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
						日期
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="总">
						总
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="0">
						0
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="1">
						1
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="2">
						2
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="3">
						3
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="4">
						4
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="5">
						5
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="6">
						6
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="7">
						7
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="8">
						8
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="9">
						9
					</th>
				</tr>
				<s:iterator value="searchkeyList">
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
							${positiontotal }
						</td>
						<td>
							${position0 }
						</td>
						<td>
							${position1 }
						</td>
						<td>
							${position2 }
						</td>
						<td>
							${position3 }
						</td>
						<td>
							${position4 }
						</td>
						<td>
							${position5 }
						</td>
						<td>
							${position6 }
						</td>
						<td>
							${position7 }
						</td>
						<td>
							${position8 }
						</td>
						<td>
							${position9 }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<div class="data-load">
			<table id="dataSourceTbl2" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						日期
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="总">
						总
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="10">
						10
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="11">
						11
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="12">
						12
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="13">
						13
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="14">
						14
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="15">
						15
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="16">
						16
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="17">
						17
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="18">
						18
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="19">
						19
					</th>
				</tr>
				<s:iterator value="searchkeyList">
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
							${positiontotal }
						</td>
						<td>
							${position10 }
						</td>
						<td>
							${position11 }
						</td>
						<td>
							${position12 }
						</td>
						<td>
							${position13 }
						</td>
						<td>
							${position14 }
						</td>
						<td>
							${position15 }
						</td>
						<td>
							${position16 }
						</td>
						<td>
							${position17 }
						</td>
						<td>
							${position18 }
						</td>
						<td>
							${position19 }
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
</script>
	</body>
</html>