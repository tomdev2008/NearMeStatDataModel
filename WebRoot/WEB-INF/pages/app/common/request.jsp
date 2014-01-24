<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>需求提报</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" /></script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js"></script>
		<script type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>
		<link href="../../css/jquery.msgbox.css" type="text/css" rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		<script src="../../js/jquery.dragndrop.min.js" type="text/javascript"></script>
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
        noneSelectedText: "--无--",
        checkAllText: "全选",
        uncheckAllText: '全不选',
        selectedList:10
    });
});
		
$(function() {
	$("#reflect").click(function() {
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
		<s:form namespace="/app/common">
			<s:hidden name="form.systemID"></s:hidden>
			<div class="title">
				查询设置
				<input style="float: right;" type="button" id="reflect" value="反馈" />
			</div>
			<div>
				<div>
					<input class="Wdate" type="text" name="form.startTime"
						value="${form.startTime}" style="width: 100px;"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					-
					<input class="Wdate" type="text" name="form.endTime"
						value="${form.endTime}" style="width: 100px;"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					粒度
					<s:select id="lidu"
						list="#{'DAILY':'日','WEEKLY':'周','MONTHLY':'月'}" name="form.lidu">
					</s:select>
				</div>
				<div>
					版本
					<myTags:AppVersionSelect systemID="${form.systemID}"
						clientID="form.appVersion" clientName="form.appVersion"
						addEmptyAll="true" value="${form.appVersion}">
					</myTags:AppVersionSelect>
				</div>
				<div>
					机型
					<myTags:ModelSelect clientID="form.model" clientName="form.model"
						addEmptyAll="true" value="${form.model}">
					</myTags:ModelSelect>
				</div>
				<div>
					路径
					<input class="text_input" type="text" id="form.path"
						name="form.path" placeholder="请填入路径内容" />
				</div>
				<div>
					产品名称
					<input class="text_input" type="text" id="form.productname"
						name="form.productname" placeholder="请填入产品名称" />
				</div>
			</div>

			<br>
			<br>
			<br>
			<br>
			<br>
			<div class="title">
				样本选择
			</div>
			<div>
				<div>
					类型
					<s:select id="type"
						list="#{'':'无','NEW_USER':'新增用户','ACTIVE_USER':'活跃用户'}"
						name="form.type">
					</s:select>
					周期
					<input class="Wdate" type="text" name="form.sampleStartTime"
						value="${form.sampleStartTime}" style="width: 100px;"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					-
					<input class="Wdate" type="text" name="form.sampleEndTime"
						value="${form.sampleEndTime}" style="width: 100px;"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" />
				</div>
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>

			<div class="title">
				指标设置
			</div>
			<div>
				<select id="mulslt_modle" multiple="multiple" size="5"
					name="form.zhibiao">
					<s:iterator value="measureList">
						<option value="${name}">
							${name}
						</option>
					</s:iterator>
				</select>
			</div>
			<br>
			<br>
			<s:submit name="query" value="提交需求" action="common_request_submit">
			</s:submit>
		</s:form>
	</body>
</html>