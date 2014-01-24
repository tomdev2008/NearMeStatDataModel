<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>排行</title>
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
			排行
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div class="title">
			位置Top30:
		</div>
		<s:form id="form_top30" namespace="/app/game">
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
						排行类型
						<s:select id="ranktype"
							list="#{'HOT_RANK':'最热排行','NEW_RANK':'最新排行'}" name="form.ranktype">
						</s:select>
						<input type="button" value="查询" onclick="queryTop30();" />
						<input type="button" value="导出" onclick="exportTop30();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_top30">
			<s:include value="rank_top30.jsp" />
		</div>

		<br>

		<div class="title">
			位置详情:
		</div>
		<s:form id="form_position" namespace="/app/game">
			<s:hidden name="formposition.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input id="starttime" class="Wdate" type="text"
							name="formposition.startTime" value="${formposition.startTime}"
							style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						到
						<input id="endtime" class="Wdate" type="text"
							name="formposition.endTime" value="${formposition.endTime}"
							style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						粒度
						<s:select id="lidu" list="#{'DAILY':'日'}" name="formposition.lidu">
						</s:select>
						网络
						<myTags:NetworkSelect clientID="formposition.network"
							clientName="formposition.network" addEmptyAll="true"
							value="${formposition.network}">
						</myTags:NetworkSelect>
						机型
						<myTags:ModelSelect clientID="formposition.model"
							clientName="formposition.model" addEmptyAll="true"
							value="${formposition.model}">
						</myTags:ModelSelect>
						排行类型
						<s:select id="ranktype"
							list="#{'HOT_RANK':'最热排行','NEW_RANK':'最新排行'}"
							name="formposition.ranktype">
						</s:select>
						位置
						<s:select id="position"
							list="#{'-1':'总','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10',
						'11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20',
						'21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29'}"
							name="formposition.position">
						</s:select>
						<input type="button" value="查询" onclick="queryPosition();" />
						<input type="button" value="导出" onclick="exportPosition();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_position">
			<s:include value="rank_position.jsp" />
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
 * 查询top30
 */
function queryTop30() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_rank_querytop30.do',
		data : $('#form_top30').serialize(),
		success : function(result) {
			$("#div_top30").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

/**
 * 导出top30
 */
function exportTop30() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_rank_exporttop30.do',
		success : function(result) {
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

/**
 * 查询位置
 */
function queryPosition() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_rank_queryposition.do',
		data : $('#form_position').serialize(),
		success : function(result) {
			$("#div_position").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

/**
 * 导出位置
 */
function exportPosition() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'game_rank_exportposition.do',
		success : function(result) {
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>