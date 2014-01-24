<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>单个资源下载</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" /></script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js"></script>
		<script type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>

		<link href="../../css/jquery.msgbox.css" type="text/css" rel="stylesheet" />
		<script src="../../js/jquery.msgbox.js" type="text/javascript"></script>
		
		<link rel="stylesheet" type="text/css" href="../../assets/jquery.multiselect.css" />
		<link rel="stylesheet" type="text/css" href="../../assets/prettify.css" />
		<link rel="stylesheet" type="text/css" href="../../assets/style.css" />
		<link rel="stylesheet" type="text/css" href="../../assets/jquery-ui.css" />
		<script src="../../assets/jquery.js" type="text/javascript"></script>
		<script src="../../assets/jquery-ui.min.js" type="text/javascript"></script>
		<script src="../../assets/prettify.js" type="text/javascript"></script>
		<script src="../../assets/jquery.multiselect.js" type="text/javascript"></script>
		
		
<script type="text/javascript" language="JavaScript">
 $(function(){
	$("#mulslt_modle").multiselect({
        noneSelectedText: "--全部--",
        checkAllText: "全选",
        uncheckAllText: '全不选',
        selectedList:10
    });
});
 
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
			单个资源下载
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
		
		
		
		<!-- 渠道模块趋势分析 -->
		<div class="div_help_content" id="div_help_content">
		渠道模块趋势分析
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
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="true"
							value="${form.qudao}">
						</myTags:ChannelinfoSelect>
						模块
						<select id="mulslt_modle" multiple="multiple" size="5" name="form.groupname">
							<s:iterator value="moduleList">
								<option value="'${groupname}'">${groupname}</option>
							</s:iterator>
						</select>
						<input type="button" value="查询" onclick="queryDownsource();" />
						<input type="button" value="导出" onclick="exportDownsource();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_downsource_result">
			<s:include value="downsource_result.jsp" />
		</div>

		
		
		<!-- cpt -->
		<div class="div_help_content" id="div_help_content">
		CPT分析
		</div>
		<s:form id="form_cpt" namespace="/app/common">
			<s:hidden name="formcpt.systemID"></s:hidden>
			<table>
				<tr>
					<td>
						<input class="Wdate" type="text" name="formcpt.startTime"
							value="${formcpt.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						至
						<input class="Wdate" type="text" name="formcpt.endTime"
							value="${formcpt.endTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<input type="button" value="查询" onclick="queryCPT();" />
						<input type="button" value="导出" onclick="exportCPT();" />
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load" id="div_downsource_cpt">
			<s:include value="downsource_cpt.jsp" />
		</div>
		<script type="text/javascript">
function checkAllClick() {
	$("input[name='chkProductID']").attr("checked",
			$("#checkAll").attr("checked"));
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

// 下载资源查询
function queryDownsource() {
	//限制查询选择资源太多
	var chks = $("input[name='chkProductID']:checked");
	if (chks.length > 200) {
		return;
	}

	var ids = buildProductIDs();
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_downsource_queryDownsource.do',
		data : $('#form').serialize() + '&form.productIDs=' + ids,
		success : function(result) {
			$("#div_downsource_result").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 结果表导出(不通过ajax提交)
function exportDownsource() {
	// 指定action
	document.getElementById('form').action = "common_downsource_exportDownsource.do";
	document.getElementById('form').submit();
}


// CPT查询
function queryCPT() {
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
		url : 'common_downsource_queryCPT.do',
		data : $('#form_cpt').serialize() + '&formcpt.productIDs=' + ids,
		success : function(result) {
			$("#div_downsource_cpt").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// cpt数据导出(不通过ajax提交)
function exportCPT() {
	// 指定action
	document.getElementById('form_cpt').action = "common_downsource_exportCPT.do";
	document.getElementById('form_cpt').submit();
}
</script>
	</body>
</html>