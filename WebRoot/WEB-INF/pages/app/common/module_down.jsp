<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>模块下载量分析</title>
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
			模块下载量分析
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		
		<div class="div_help_content" id="div_help_content">
			<div class="title">
				<font color="red">注意事项：</font>
			</div>
			<div class="body">
				1.查询比较耗时！！！查询结果请及时导出！！！<br>
			    2.查询为一段时间、某个渠道、某个维度对应的使用情况;<br>
			    3.渠道选项可以在设置项添加，渠道id号请核对好再填写;<br>
			    4.为了控制任务的提交数量对服务器带来的压力，我们限定了任务的提交数量。<br>
			    5.如发现任务提交不了，可能有坏死的任务，没有跑出结果，请及时联系统计组。<br>
			</div>
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
						粒度
						<s:select id="lidu" list="#{'DAILY':'日','MONTHLY':'月'}"
							name="form.lidu" onchange="changeLidu();">
						</s:select>
						渠道
						<myTags:ChannelinfoSelect systemID="${form.systemID}"
							clientID="form.qudao" clientName="form.qudao" addEmptyAll="false"
							value="${form.qudao}">
						</myTags:ChannelinfoSelect>
						版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						模块
						<select id="mulslt_modle" multiple="multiple" size="5" name="form.groupname">
							<s:iterator value="moduleList">
								<option value="'${groupname}'">${groupname}</option>
							</s:iterator>
						</select>
						
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
			<s:include value="module_down_jobresult.jsp" />
		</div>
		<script type="text/javascript">

function showTblChart(cloumn) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt",
			"1100", "350", "0", "1");
	chartText = table2FCFSingleLineChart("dataSourceTbl_jobresult", cloumn,
			"end-2-front");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render("chartDiv");
}

// 提交任务
function submitQuery() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_moduledown_query.do',
		data : $('#form').serialize(),
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
		url : 'common_moduledown_queryjob.do',
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
		url : 'common_moduledown_queryresult.do',
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
		url : 'common_moduledown_deleteresult.do',
		data : '&form.jobID=' + jobID 
		     + '&form.resulttable=' + resulttable
		     + '&form.weidu=' + weidu
		     + '&form.lidu=' + lidu,
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
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_moduledown_querySQL.do',
		data : $('#form').serialize(),
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
	document.getElementById('form_export').action = "common_moduledown_exportresult.do";
	
	document.getElementById("form.jobID").value = jobID;
	document.getElementById("form.resulttable").value = resulttable;
	document.getElementById("form.weidu").value = weidu;
	document.getElementById("form.lidu").value = lidu;
	document.getElementById('form_export').submit();
}
</script>
	</body>
</html>