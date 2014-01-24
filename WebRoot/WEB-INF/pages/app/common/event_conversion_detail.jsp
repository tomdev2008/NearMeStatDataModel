<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>事件流</title>
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
		<style type="text/css">
			#menu ul {
			 font-size: 12px;
			 text-decoration: none;
			 line-height: 25px;
			 list-style:none;
			}
			#menu li {
			 float: left;
			 width: 80px;
			 padding-left: 3px;
			}
			#menu a:link {
			 color: #FF0000;
			 text-decoration: none;
			 background-color: #CCCCCC;
			}
			#menu a:visited {
			 color: #FFFFFF;
			}
			#menu a:hover {
			 color: #FFFF00;
			 text-decoration: none;
			 background-color: #666666;
			}
			#menu a {
			 text-align: center;
			 display: block;
			}
		</style>
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
			事件转化率
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<div id="tips"><font color="red">${form.tips}</font></div>
		<s:form cssClass="submit" id="form" namespace="/app/common">
			<s:hidden id="hid_systemid" name="form.systemID" />
			<s:hidden id="hid_type" name="form.type" value="COUNTS"/>
			<s:hidden id="hid_flowstep" name="form.eventFlowStep" />
			<table width="90%">
				<tr>
					<td>
					事件流
						<s:set name="tmp_flowname" value="form.eventFlowName" />
						<select id="form_flowname" name="form.eventFlowName">
							<s:iterator value="flowList">
								<option value="${eventFlowName}" 
								<s:if test="eventFlowName == #tmp_flowname">selected='selected'</s:if>>${eventFlowName}</option>
							</s:iterator>
						</select>
						
					版本
						<myTags:AppVersionSelect systemID="${form.systemID}"
							clientID="form.appVersion" clientName="form.appVersion"
							addEmptyAll="false" value="${form.appVersion}">
						</myTags:AppVersionSelect>
						
					粒度	
						<s:select list="#{'DAILY':'日','WEEKLY':'周','MONTHLY':'月'}"
							name="form.lidu">
						</s:select>
						
					日期	
						<input class="Wdate" type="text" name="form.startTime"
							value="${form.startTime}" style="width: 100px;"
							onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					</td>
					<td width="30%">
						<input type="button" value="   查询   " onclick="query();" />
						<a href="common_eventconversion_init.do?form.systemID=${form.systemID}" >返回</a>
						
					</td>
				</tr>
			</table>
		</s:form>
		<div class="data-load">
			<div><span style="bold;">转化率分析：</span></div>
			<div id="menu">
				<ul>
					<li><a href="#" onclick="showCT('COUNTS');"><span>完成次数</span></a></li>
					<li><a href="#" onclick="showICT('IMEIS');"><span>完成用户数</span></a></li>
				</ul>
				<br>
			</div>
			<div id="div_ct">
			<div id="div_ct_swf" align="center"></div>
			<div id="div_loading_ct" align="right" style="height 20px;">&nbsp;</div>
			<div id="div_ct_tbl">
			<table id="dataSourceTblCT" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>步骤序号	</th>
					<th>名称</th>
					<th onclick="showTblChartColumn('dataSourceTblCT',2,'div_ct_swf')" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">次数</th>
					<th onclick="showTblChartColumn('dataSourceTblCT',3,'div_ct_swf')" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">上一步转化率(%)</th>
					<th onclick="showTblChartColumn('dataSourceTblCT',4,'div_ct_swf')" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">总体转化率(%)</th>
					<th>趋势</th>
				</tr>
				<s:iterator value="eventFlowList" status="st">
					<tr>
						<td>
							${eventFlowStep }
						</td>
						<td>
							${finalEventName }
						</td>
						<td>
							${eventCounts }
						</td>
						<td>
							<s:if test="#st.getIndex()>0">${eventCR }</s:if>
						</td>
						<td>
							<s:if test="#st.getIndex()>0">${eventTCR }</s:if>
						</td>
						<td align="center">
							<s:if test="eventFlowStep>1">
								<a href="#" onclick="toTrend('${eventFlowStep }');" >近期趋势</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
			</div>
			<div id="div_ct_period_all" align="center"  style="display: none;">
				<div align="right"><a href="#" onclick="toBack();">返回</a></div>
				<div id="div_ct_period" align="center"></div>
				<div align="center"><span id="span_ct_desc"></span></div>
			</div>
			</div>
			
			<div id="div_ict" style="display: none;">
			<div id="div_ict_swf" align="center"></div>
			<div id="div_loading_ict" align="right" style="height 20px;">&nbsp;</div>
			<div id="div_ict_tbl">
			<table id="dataSourceTblICT" class="data-load" id="tblChannels" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>步骤序号	</th>
					<th>名称</th>
					<th onclick="showTblChartColumn('dataSourceTblICT',2,'div_ict_swf')" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">用户数</th>
					<th onclick="showTblChartColumn('dataSourceTblICT',3,'div_ict_swf')" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">上一步转化率(%)</th>
					<th onclick="showTblChartColumn('dataSourceTblICT',4,'div_ict_swf')" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="">总体转化率(%)</th>
					<th>趋势</th>
				</tr>
				<s:iterator value="eventFlowList" status="st">
					<tr>
						<td>
							${eventFlowStep }
						</td>
						<td>
							${finalEventName }
						</td>
						<td>
							${eventImeis }
						</td>
						<td>
							<s:if test="#st.getIndex()>0">${eventICR }</s:if>
						</td>
						<td>
							<s:if test="#st.getIndex()>0">${eventITCR }</s:if>
						</td>
						<td align="center">
							<s:if test="eventFlowStep>1">
								<a href="#" onclick="toTrend('${eventFlowStep }');" >近期趋势</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
			</div>
			<div id="div_ict_period_all" align="center" style="display: none;">
				<div  align="right"><a href="#" onclick="toBack();">返回</a></div>
				<div id="div_ict_period" align="center"></div>
				<div align="center"><span id="span_ict_desc"></span></div>
			</div>
			<div id="div_period_tbl" style="display: none;"></div>
		</div>

<script type="text/javascript">
showTblChartColumn('dataSourceTblCT',2,'div_ct_swf');
showTblChartColumn('dataSourceTblICT',2,'div_ict_swf');

function showTblChartColumn(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/StackedColumn3D.swf", "fcfCahrt", "1100",
			"250", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

function showTblChart(dataSourceTbl, cloumn, div) {
	var fcfCahrt = new FusionCharts("../../FCF/ScrollLine2D.swf", "fcfCahrt", "1100",
			"250", "0", "1");
	chartText = table2FCFSingleLineChart(dataSourceTbl, cloumn, "");
	fcfCahrt.setDataXML(chartText);
	fcfCahrt.render(div);
}

// 查询		
function query() {
	document.getElementById("form").action="common_eventconversion_detail.do";
	document.getElementById("form").submit();
}
		
// 近期趋势		
function toTrend(step) {
	showLoading();
	
	$('#hid_flowstep').val(step);
	$.ajax( {
				type : "POST",
				dataType : "html",
				url : 'common_eventconversion_period.do',
				data : $('#form').serialize(),
				success : function(result) {
					hideLoading();
					$('#div_period_tbl').html(result);
					var desc = "<Strong>从 ["+$('#hid_prevevent').val()+"] 到 ["+$('#hid_event').val()+"] 的转化率</Strong>";
					if($('#hid_type').val()=='COUNTS'){
						showTblChart('dataSourceTblCTPeriod',1,"div_ct_period");
						$('#span_ct_desc').html(desc);
						$('#div_ct_period_all').show(500);
						$('#div_ct_tbl').hide(500);
					}else{
						showTblChart('dataSourceTblCTPeriod',1,"div_ict_period");
						$('#span_ict_desc').html(desc);
						$('#div_ict_period_all').show(500);
						$('#div_ict_tbl').hide(500);
					}
				},
				error : function(data) {
					hideLoading();
					alert("error:" + data.responseText);
				}
			});
	
}

function showCT(type){
	$('#hid_type').val(type);
	$('#div_ct').show();
	$('#div_ict').hide();
}
function showICT(type){
	$('#hid_type').val(type);
	$('#div_ict').show();
	$('#div_ct').hide();
}

function toBack(){
	if($('#hid_type').val()=='COUNTS'){
		$('#div_ct_period_all').hide(500);
		$('#div_ct_tbl').show(500);
	}else{
		$('#div_ict_period_all').hide(500);
		$('#div_ict_tbl').show(500);
	}
}
function showLoading(){
	html = "<img src=\"../../images/loading_2.gif\"/>&nbsp;<font color=\"red\" size=\"2\">数据正在加载...</font>&nbsp;&nbsp;&nbsp;";
	if($('#hid_type').val()=='COUNTS'){
		$("#div_loading_ct").html(html);
	}else{
		$("#div_loading_ict").html(html);
	}
}

function hideLoading(){
	if($('#hid_type').val()=='COUNTS'){
		$("#div_loading_ct").html("&nbsp;");
	}else{
		$("#div_loading_ict").html("&nbsp;");
	}
}
		
</script>
	</body>
</html>