<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="com.nearme.statistics.util.DateUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>付费分析</title>
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
			付费分析
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<s:form id="form_gamename" namespace="/app/game">
			<s:hidden name="formgamename.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						游戏名
						<input type="text" name="formgamename.gamename" width="20px" 
						onkeydown='if(event.keyCode==13) return false;'/>
						<input type="button" value="查询" onclick="queryGame();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_game">
			<s:include value="game.jsp" />
		</div>

		<br>

		<s:form id="form" namespace="/app/game">
			<s:hidden name="form.productid" id="hid_productid"></s:hidden>
			<s:hidden name="form.systemID" id="hid_systemID"></s:hidden>
			<s:hidden name="form.statdate" id="hid_statdate"></s:hidden>
			<s:hidden name="form.type" id="hid_type"></s:hidden>
			<s:hidden name="form.records" id="hid_records"></s:hidden>
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
						渠道
						<myTags:ChannelinfoSelect systemID="${form.systemID}"
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="true"
							value="${form.qudao}" onchange="changeQudao();">
						</myTags:ChannelinfoSelect>
						<a href="../commonsetting/setting_channelinfo_init.do?form.systemID=${form.systemID }">渠道管理</a>
						<input type="button" value="查询" onclick="submitQuery();" />
						<s:submit name="export" value="导出" action="game_fee_export">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_fee">
			<s:include value="fee_query.jsp" />
		</div>
		<br/>
		<div class="data-load" id="div_fee_user">
		</div>

		<script type="text/javascript">
function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl", cloumn, "end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

/**
 * 一个非全选，另外两个设置为全选
 */
function changeModel() {
	var model = document.getElementById("form.model").value;
	if (model != 'all') {
		document.getElementById("form.qudao").value = 'all';
	}
}

function changeQudao() {
	var qudao = document.getElementById("form.qudao").value;
	if (qudao != 'all') {
		document.getElementById("form.model").value = 'all';
	}
}

/**
 * 查询游戏名
 */
function queryGame() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_queryGame.do',
		data : $('#form_gamename').serialize(),
		success : function(result) {
			$("#div_game").html(result);
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
		ids = chks[i].value;
		return ids;
	}
	return 0;
}

/**
 * 点击查询
 */
function submitQuery() {
	//限制查询选择太多
	var chks = $("input[name='chkProductID']:checked");
	if (chks.length > 200) {
		alert("批量操作选项超过200条，请拆分后再操作。。。");
		return;
	}
	var ids = buildProductIDs();
	$("#hid_productid").val(ids);
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_fee_query.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_fee").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

/**
 * 详情
 */
function getUser(statdate,type,records) {
	$("#div_fee_user").html("");
	
	//限制查询选择太多
	var chks = $("input[name='chkProductID']:checked");
	if (chks.length > 200) {
		alert("批量操作选项超过200条，请拆分后再操作。。。");
		return;
	}
	var ids = buildProductIDs();
	$("#hid_productid").val(ids);
	
	$("#hid_statdate").val(statdate);
	$("#hid_type").val(type);
	$("#hid_records").val(records);
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_fee_getuser.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_fee_user").html(result);
			document.getElementById("div_fee_user").scrollIntoView();
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>