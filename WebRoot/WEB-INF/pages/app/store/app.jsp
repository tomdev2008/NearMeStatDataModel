<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>应用榜</title>
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
			应用榜
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="chartDiv" align="center"></div>
		<s:form namespace="/app/store">
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
						<s:select id="lidu" list="#{'DAILY':'日','MONTHLY':'月'}"
							name="form.lidu" onchange="changeLidu();">
						</s:select>
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}"
							onchange="changeAppversion();">
						</myTags:AppVersionSelect>
						机型
						<myTags:ModelSelect clientID="form.model" clientName="form.model"
							addEmptyAll="true" value="${form.model}"
							onchange="changeModel();">
						</myTags:ModelSelect>
						用户类型
						<s:select id="usertype"
							list="#{'all':'全部','1M':'1个月新用户','2-4M':'2-4个月用户','4M+':'4个月以上用户'}"
							name="form.usertype" onchange="changeUsertype();">
						</s:select>
						<s:submit name="query" value="查询" action="store_app_query">
						</s:submit>
						<s:submit name="export" value="导出" action="store_app_export">
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
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动用户数">
						启动用户数
					</th>
					<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="下载用户数">
						下载用户数
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="下载启动比（%）">
						下载启动比（%）
					</th>
					<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="模块下载量">
						模块下载量
					</th>
					<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="模块全部产品跳转浏览量">
						模块全部产品跳转浏览量
					</th>
					<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="启动用户人均下载次数">
						启动用户人均下载次数
					</th>
					<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand"
						title="下载用户人均下载次数">
						下载用户人均下载次数
					</th>
				</tr>
				<s:iterator value="appList">
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
							${startUser }
						</td>
						<td>
							${downUser }
						</td>
						<td>
							${downStartRatio }
						</td>
						<td>
							${moduleDown }
						</td>
						<td>
							${moduleAllJump }
						</td>
						<td>
							${avgStartUserDown }
						</td>
						<td>
							${avgDownUserDown }
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
 * 一个非全选，另外两个设置为全选
 */
function changeAppversion() {
	var appversion = document.getElementById("form.appVersion").value;
	if (appversion != 'all') {
		document.getElementById("form.model").value = 'all';
		document.getElementById("usertype").value = 'all';
	}
}

function changeModel() {
	var model = document.getElementById("form.model").value;
	if (model != 'all') {
		document.getElementById("form.appVersion").value = 'all';
		document.getElementById("usertype").value = 'all';
	}
}

function changeUsertype() {
	var usertype = document.getElementById("usertype").value;
	if (usertype != 'all') {
		document.getElementById("form.appVersion").value = 'all';
		document.getElementById("form.model").value = 'all';
	}
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

	//根据lidu调整选项
	if (document.getElementById("lidu").value == "DAILY") {
		var select = document.getElementById("usertype");
		removeItemFromSelect(select, "Low");
		removeItemFromSelect(select, "High");
	} else if (document.getElementById("lidu").value == "MONTHLY") {
		var select = document.getElementById("usertype");
		addItemToSelect(select, "低活跃用户", "Low");
		addItemToSelect(select, "高活跃用户", "High");
	}
}

/**
 * 判断select选项中 是否存在Value="paraValue"的Item
 */
function selectIsExitItem(objSelect, objItemValue) {
	var isExit = false;
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemValue) {
			isExit = true;
			break;
		}
	}
	return isExit;
}

/**
 * 向select选项中 加入一个Item 
 */
function addItemToSelect(objSelect, objItemText, objItemValue) {
	if (!selectIsExitItem(objSelect, objItemValue)) {
		var varItem = new Option(objItemText, objItemValue);
		objSelect.options.add(varItem);
	}
}

/**
 * 从select选项中 删除一个Item
 */
function removeItemFromSelect(objSelect, objItemValue) {
	if (selectIsExitItem(objSelect, objItemValue)) {
		for ( var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == objItemValue) {
				objSelect.options.remove(i);
				break;
			}
		}
	}
}
</script>
	</body>
</html>