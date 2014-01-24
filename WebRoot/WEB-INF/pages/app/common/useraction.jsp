<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>用户行为分析</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js">
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
			用户行为分析
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<s:form id="form" namespace="/app/common">
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
						<!-- 
						<sx:datetimepicker name="form.startTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
						到
						<sx:datetimepicker name="form.endTime"
							displayFormat="yyyy-MM-dd" toggleType="explode"
							toggleDuration="500" />
					    -->
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						机型
						<myTags:ModelSelect clientID="form.model" clientName="form.model"
							addEmptyAll="true" value="${form.model}">
						</myTags:ModelSelect>
						行为
						<s:set name="tmpgroupcode" value="" />
						<s:set name="tmpactioncode" value="form.actioncode" />
						<select id="form_actioncode" name="form.actioncode">
							<s:iterator value="selectactionList">
								<s:if test="groupcode != #tmpgroupcode">
									<s:set name="tmpgroupcode" value="groupcode" />
									<optgroup label="${groupname}" />
								</s:if>
								<option value="${actioncode}"
									<s:if test="actioncode == #tmpactioncode">selected='selected'</s:if>>
									${actionname}
								</option>
							</s:iterator>
						</select>
						<a
							href="../commonsetting/setting_useractioncode_init.do?form.systemID=${form.systemID }">用户行为编码管理</a>
						<s:submit name="query" value="查询" action="common_useraction_query">
						</s:submit>
						<s:submit name="export" value="导出"
							action="common_useraction_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div id="div_startTimes" align="center"></div>
		<div id="div_actionCount" align="center"></div>
		<div id="chartDiv" align="center"></div>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						时间
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动账号数">
						启动账号数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动手机数">
						启动手机数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
						启动次数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="事件消息账号数">
						事件消息账号数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="事件消息手机数">
						事件消息手机数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="事件消息次数">
						事件消息次数
					</th>
				</tr>
				<s:iterator value="uactionList">
					<tr>
						<td>
							<s:date name="statDate" format="MM-dd" />
						</td>
						<td>
							${startuser }
						</td>
						<td>
							${startimei }
						</td>
						<td>
							${startcnt }
						</td>
						<td>
							${eventuser }
						</td>
						<td>
							${eventimei }
						</td>
						<td>
							${eventcnt }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<script type="text/javascript">
showMultiTblChart456();

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

function showMultiTblChart123() {
	var fcfCahrt = new FusionCharts("../../FCF/MSLine.swf", "fcfCahrt", "1100",
			"300", "0", "1");
	chartText = table2FCFMultiLineChart("dataSourceTbl", [ 1, 2, 3 ],
			"启动账户、手机、次数", "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("div_startTimes");
}

function showMultiTblChart456() {
	var fcfCahrt = new FusionCharts("../../FCF/MSLine.swf", "fcfCahrt", "1100",
			"300", "0", "1");
	chartText = table2FCFMultiLineChart("dataSourceTbl", [ 4, 5, 6 ],
			"用户行为账户、手机、次数", "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("div_actionCount");
}
</script>
	</body>
</html>