<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>单个资源运营</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
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
			单个资源运营
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<div class="div_help_content" id="div_help_content">
			<div class="title">
				<font color="red">操作步骤:</font>
			</div>
			<div class="body">
				1.输入产品名称,点击"查询产品"按钮,获取当前系统中曾经、现在上架的商品;
				<br />
				2.从上架产品中选择需要的产品(勾选前面的
				<i>正方形</i>复选框);
				<br />
				3.输入需要查询的起、止日期;
				<br />
				4.如果需要限定下载通道,可从下拉框中选择预先编辑好的通道方案;
				<br />
				5.点击"查询下载量"按钮,即可查询需要的数据
			</div>
		</div>

		<s:form id="form_product" namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						产品名称*:
						<input type="text" name="form.productname" width="20px"
							onkeydown='if(event.keyCode==13) return false;' />
						<input type="button" value="查询" onclick="queryProduct();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_product">
			<s:include value="productinfo.jsp" />
		</div>

		<div class="div_help_content">
			（查询比较耗时！！！查询结果请及时导出！！！）
		</div>
		<s:form id="form" namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						至
						<input class="Wdate" type="text" name="form.endTime"
							value="${form.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						渠道
						<myTags:ChannelinfoSelect systemID="${form.systemID}"
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="false"
							value="${form.qudao}">
						</myTags:ChannelinfoSelect>
						粒度
						<s:select id="lidu" list="#{'DAILY':'日'}"
							name="form.lidu" onchange="changeLidu();">
						</s:select>
						<input type="button" value="提交任务" onclick="if(confirm('                确认提交查询任务？\n（任务查询比较耗时，请务必确认好查询条件无误后再提交）')){submitQuery();}" />
						<input type="button" value="查看任务" onclick="queryJob();" />
						
						<s:if test="'admin' == #session.SESSION_ADMIN.userName">
						    <input type="button" value="SQL查看" onclick="querySQL();" />
						</s:if>
					</td>
				</tr>
			</table>
		</s:form>

		<div class="data-load" id="div_listjob">
			<s:include value="hive_listjob.jsp" />
		</div>
		<div id="chartDiv" align="center"></div>
		<div class="data-load" id="div_jobresult">
		</div>
		<script type="text/javascript">
function checkAllClick() {
	$("input[name='chkProductID']").attr("checked",
			$("#checkAll").attr("checked"));
}

function showTblChartModule(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl_jobresult_module", cloumn,
			"end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

function showTblChartRunpoint(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl_jobresult_runpoint", cloumn,
			"end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

/**
 * 查询资源名
 */
function queryProduct() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_queryproduct.do',
		data : $('#form_product').serialize(),
		success : function(result) {
			$("#div_product").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

/**
 * 产品名查询结果全选/反选
 */
function checkAllClick() {
	var isChecked = document.getElementById("checkAll").checked;
	var checks = document.getElementsByName("chkProductID");
	for (i = 0; i < checks.length; i++) {
		checks[i].checked = isChecked;
	}
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

// 提交任务
function submitQuery() {
	//限制查询选择资源太多
	var chks = $("input[name='chkProductID']:checked");
	if (chks.length > 200) {
		alert("批量操作选项超过200条，请拆分后再操作。。。");
		return;
	}

	var ids = buildProductIDs();
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_productrun_query.do',
		data : $('#form').serialize() + '&form.productIDs=' + ids,
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 查看任务
function queryJob() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_productrun_queryjob.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 结果表查看
function queryresult(jobID, resulttable, weidu, lidu) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_productrun_queryresult.do',
		data : '&form.jobID=' + jobID 
		     + '&form.resulttable=' + resulttable
		     + '&form.weidu=' + weidu
		     + '&form.lidu=' + lidu,
		success : function(result) {
			$("#div_jobresult").html(result);
			document.getElementById('div_jobresult').scrollIntoView();
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 删除结果
function deleteresult(jobID, resulttable, weidu, lidu) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_productrun_deleteresult.do',
		data : '&form.jobID=' + jobID + '&form.resulttable=' + resulttable
				+ '&form.weidu=' + weidu + '&form.lidu=' + lidu,
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 查看SQL
function querySQL(){
	//限制查询选择资源太多
	var chks = $("input[name='chkProductID']:checked");
	if (chks.length > 200) {
		alert("批量操作选项超过200条，请拆分后再操作。。。");
		return;
	}

	var ids = buildProductIDs();
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_productrun_querySQL.do',
		data : $('#form').serialize() + '&form.productIDs=' + ids,
		success : function(result) {
			$("#div_listjob").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 结果表导出(不通过ajax提交)
function exportresult(jobID,resulttable,weidu,lidu) {
	// 指定action
	document.getElementById('form_export').action = "common_productrun_exportresult.do";
	
	document.getElementById("form.jobID").value = jobID;
	document.getElementById("form.resulttable").value = resulttable;
	document.getElementById("form.weidu").value = weidu;
	document.getElementById("form.lidu").value = lidu;
	document.getElementById('form_export').submit();
}
</script>
	</body>
</html>