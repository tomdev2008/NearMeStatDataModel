<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>游戏更新</title>
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
			游戏更新
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<s:form id="form_gamename" namespace="/app/game">
			<s:hidden name="formgamename.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						游戏名
						<input type="text" name="formgamename.gamename" width="20px"
						onkeydown='if(event.keyCode==13) return false;' />
						<input type="button" value="查询" onclick="queryGamename();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_gamename">
			<s:include value="gameinfo.jsp" />
		</div>

		<br>

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
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						机型
						<myTags:ModelSelect clientID="form.model" clientName="form.model"
							addEmptyAll="true" value="${form.model}">
						</myTags:ModelSelect>
						<input type="button" value="查询" onclick="submitQuery();" />
						<s:submit name="export" value="导出" action="game_update_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_update">
		    <s:include value="update_update.jsp" />
		</div>

		<script type="text/javascript">
function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

/**
 * 查询游戏名
 */
function queryGamename() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_queryGamename.do',
		data : $('#form_gamename').serialize(),
		success : function(result) {
			$("#div_gamename").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

/**
 * 生成查询的productID
 */
function buildProductIDs() {
	var chks = $("input[name='chkProductID']:checked");
	var ids = "";
	for (i = 0; i < chks.length; i++) {
		ids += "'" + chks[i].value + "'";
		if (i != chks.length - 1) {
			ids += ",";
		}
	}
	return ids;
}

/**
 * 点击查询
 */
function submitQuery() {
	//限制查询选择太多
	var chks = $("input[name='chkProductID']:checked");
	if(chks.length > 200){
		alert("批量操作选项超过200条，请拆分后再操作。。。");
		return;
	}
	
	var ids = buildProductIDs();
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_update_query.do',
		data : $('#form').serialize() + '&form.productIDs=' + ids,
		success : function(result) {
			$("#div_update").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>