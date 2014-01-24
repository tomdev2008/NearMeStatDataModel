<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>错误分析</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
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
			错误分析
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
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						<s:submit name="query" value="查询" action="common_erroranaly_query">
						</s:submit>
					</td>
				</tr>
			</table>
		</s:form>

		<div id="chartDiv" align="center"></div>
		<div class="data-load" style="display: none;">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						日期
					</th>
					<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="错误次数">
						错误数
					</th>
					<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
						错误/启动次数（‰）
					</th>
				</tr>
				<s:iterator value="errorList">
					<tr>
						<td>
							<s:date name="statDate" format="MM-dd" />
						</td>
						<td>
							${errorcnt }
						</td>
						<td>
							${errorstartratio }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<div id="div_error_general" style="display: block;">
			<s:include value="erroranaly_general.jsp"></s:include>
		</div>
		<div id="div_error_detail" style="display: none;">
			<s:include value="erroranaly_detail.jsp"></s:include>
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

//显示错误详情
function showDetail(detail, version) {
	$("#div_detail_content").text(detail);
	document.getElementById("div_error_general").style.display = "none";
	document.getElementById("div_error_detail").style.display = "block";
	
	document.getElementById("id_model_distribute").value = detail;
	document.getElementById("id_version").value = version;
	
	document.getElementById("div_detail_model_distribute").style.display = "none";
	document.getElementById("div_detail_content").style.display = "block";
}

// 显示错误摘要
function showGeneral() {
	document.getElementById("div_error_general").style.display = "block";
	document.getElementById("div_error_detail").style.display = "none";
}

function showModelDistribute(){
	var detail = document.getElementById("id_model_distribute").value;
	var version = document.getElementById("id_version").value;
	var systemID = document.getElementsByName("form.systemID")[0].value;
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_erroranaly_queryModeldistribute.do',
		data : "form.systemID=" + systemID
		       + "&form.appVersion=" + version
		       + "&form.errordetail=" + detail,
		success : function(result) {
			$("#div_detail_model_distribute").html(result);
			document.getElementById("div_detail_model_distribute").style.display = "block";
	        document.getElementById("div_detail_content").style.display = "none";
			document.getElementById('div_detail_model_distribute').scrollIntoView();
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

function showErrorDetal(){
	document.getElementById("div_detail_model_distribute").style.display = "none";
	document.getElementById("div_detail_content").style.display = "block";
}

// 显示某一页
function showPage(page) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_erroranaly_queryFixpage.do',
		data : $('#form').serialize() + "&form.pagecurr=" + page,
		success : function(result) {
			$("#div_error_general").html(result);
			document.getElementById('div_error_general').scrollIntoView();
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 显示上一页
function showPrePage(pagecurr) {
	if (pagecurr <= 1) {
		return;
	} else {
		pagecurr--;
		
		$.ajax( {
			type : "POST",
			dataType : "html",
			url : 'common_erroranaly_queryFixpage.do',
			data : $('#form').serialize() + "&form.pagecurr=" + pagecurr,
			success : function(result) {
				$("#div_error_general").html(result);
				document.getElementById('div_error_general').scrollIntoView();
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
}

// 显示下一页
function showNextPage(pagecurr, pagecnt) {
	if (pagecurr >= pagecnt) {
		return;
	} else {
		pagecurr++;
		
		$.ajax( {
			type : "POST",
			dataType : "html",
			url : 'common_erroranaly_queryFixpage.do',
			data : $('#form').serialize() + "&form.pagecurr=" + pagecurr,
			success : function(result) {
				$("#div_error_general").html(result);
				document.getElementById('div_error_general').scrollIntoView();
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
}

// 导出当前页(不通过ajax提交)
function exportCurrpage(){
	// 指定action
	document.getElementById('form').action = "common_erroranaly_exportCurrpage.do";
	document.getElementById('form').submit();
}
</script>
	</body>
</html>