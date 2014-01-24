<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>使用时长</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" ></script>
		<script type="text/javascript" src="../../js/date.js" ></script>
		<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
		<link href="../../css/jquery.msgbox.css" type="text/css" rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.dragndrop.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.msgbox.js" type="text/javascript"></script>
		<script type="text/javascript"	src="../../js/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js"></script>
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
			使用时长
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<s:form cssClass="submit" id="form" namespace="/app/common">
			<s:hidden id="hid_systemid" name="form.systemID" />
			<s:hidden id="hid_type" name="form.type" value=""/>
			<s:hidden id="hid_days" name="form.days" value=""/>
			<s:hidden id="hid_cp_days_times" value=""/>
			<s:hidden id="hid_cp_days_imeis" value=""/>
			<table width="90%">
				<tr>
					<td>
					渠道
						<myTags:ChannelinfoSelect systemID="${form.systemID}" 
						    clientID="form.qudao" clientName="form.qudao"
							addEmptyAll="true" value="${form.qudao}" onchange="changeQudao();">
						</myTags:ChannelinfoSelect>
						
					版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="true" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						
					日期	
						<input class="Wdate" type="text" name="form.startTime" id="inp_date"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<s:submit name="query" value="查询" action="common_pvduration_query"></s:submit>
					</td>
				</tr>
			</table>
		</s:form>
		<div id="div_times" align="center"></div>
		<div id="div_times_dis" align="center" style="display: none;"></div>
		<div id="div_cp1" align="right">
		<table width="90%">
			<tr>
				<td align="right">
					<input class="Wdate" type="text" id="cp_date_times"
						value="${form.startTime}" style="width: 100px;"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<input type="button" value="对比数据" onclick="cpTimes()"/>
				</td>
			</tr>
		</table>
		</div>
		<div class="data-load">
			<table id="dataSourceTbl" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						时长
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
						启动次数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数占比">
						启动次数占比
					</th>
				</tr>
				<s:iterator value="durationTimesList">
					<tr>
						<td>
							${duration}
						</td>
						<td>
							${startTimes }
						</td>
						<td>
							${rate }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="div_imeis" align="center"></div>
		<div id="div_imeis_dis" align="center" style="display: none;"></div>
		<div id="div_cp2" align="right">
		<table width="90%">
			<tr>
				<td align="right">
					<input class="Wdate" type="text" id="cp_date_imeis"
						value="${form.startTime}" style="width: 100px;"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
						<input type="button" value="对比数据" onclick="cpImeis()"/>
				</td>
			</tr>
		</table>
		</div>
		<div class="data-load">
			<table id="dataSourceTbl1" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr style="background-color: #DEEBFB">
					<th>
						时长
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
						用户数
					</th>
					<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数占比">
						用户数比例
					</th>
				</tr>
				<s:iterator value="durationImeisList">
					<tr>
						<td>
							${duration}
						</td>
						<td>
							${startImeis }
						</td>
						<td>
							${rate }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

<script type="text/javascript">
showTblChartColumn('dataSourceTbl',1,'div_times');
showTblChartColumn('dataSourceTbl1',1,'div_imeis');

function showTblChartColumn(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/StackedColumn3D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

function showTblChart(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

function showTblChartMSColumn3D(dataSourceTbl, div) {
	var fcfCahrt = new FusionCharts("../../FCF/MSColumn3D.swf", "fcfCahrt", "1100",
			"350", "0", "1");
	chartText = table2MSColumn3DChart(dataSourceTbl,'');
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

function cpTimes(){
	$('#hid_type').val("durationT");
	
	if($('#hid_cp_days_times').val()==''){
		$('#hid_cp_days_times').val($('#cp_date_times').val());
	}else{
		$('#hid_cp_days_times').val($('#hid_cp_days_times').val()+','+$('#cp_date_times').val());
	}
	$('#hid_days').val($('#inp_date').val()+','+$('#hid_cp_days_times').val());
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_pvcp_query.do',
		data : $('#form').serialize(),
		success : function(result) {
			$('#div_times_dis').html(result);
			showTblChartMSColumn3D("dataSourceTblCp_durationT","div_times");
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

function cpImeis(){
	$('#hid_type').val("durationI");
	
	if($('#hid_cp_days_imeis').val()==''){
		$('#hid_cp_days_imeis').val($('#cp_date_imeis').val());
	}else{
		$('#hid_cp_days_imeis').val($('#hid_cp_days_imeis').val()+','+$('#cp_date_imeis').val());
	}
	$('#hid_days').val($('#inp_date').val()+','+$('#hid_cp_days_imeis').val());
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'common_pvcp_query.do',
		data : $('#form').serialize(),
		success : function(result) {
			$('#div_imeis_dis').html(result);
			showTblChartMSColumn3D("dataSourceTblCp_durationI","div_imeis");
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

		
</script>
	</body>
</html>