<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>机型管理</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
</script>
		<script type="text/javascript" src="../../js/date.js" />
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
			机型管理
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		
		<div class="div_help_content" id="div_help_content">
			<div class="body">
				<font color="red">说明：</font>如需统一操作，请先勾选选项。
			</div>
		</div>
		
		<div id="div_bothedit">
			<s:form cssClass="submit" namespace="/app/commonsetting">
				<table id="tblEdit" class="submit" align="center" border="1"
					cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
					<tr>
						<td>
							系列
						</td>
						<td>
						    <input type="text" id="typename" name="typename" placeholder="如不填则不做调整" />
						</td>
					</tr>
					<tr>
						<td>
							是否OPPO手机
						</td>
						<td>
							<input type="radio" name="isoppo" value=-1 checked="checked"/>不调整
							<input type="radio" name="isoppo" value=1 />是
							<input type="radio" name="isoppo" value=0 />否
						</td>
					</tr>
					<tr>
						<td>
							是否显示
						</td>
						<td>
							<input type="radio" name="isused" value=-1  checked="checked"/>不调整
							<input type="radio" name="isused" value=1  />显示
							<input type="radio" name="isused" value=0  />隐藏
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<input type="button" id="btnupdate" value="统一标识" class="btn-style"
								onclick="btnBothChange();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		
		<div class="data-load">
			<table class="data-load" id="tblmodels" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th title="选项请勿超过限定数200">
						选项
					</th>
					<th>
						机型
					</th>
					<th>
						系列
					</th>
					<th>
						是否OPPO手机
					</th>
					<th>
						是否隐藏
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="modelmanageList">
					<tr>
						<td align="center" width="30px">
							<input type="checkbox" id="${model}" name="chkModel"
								value="${model}" />
						</td>
						<td>
							${model }
						</td>
						<td>
							${typename }
						</td>
						<td>
							<s:if test='isoppo=="0"'>
								<font color="#999">否</font>
							</s:if>
							<s:if test='isoppo=="1"'>
								<font color="green">是</font>
							</s:if>
						</td>
						<td>
							<s:if test='isused=="0"'>
								<font color="#999">隐藏</font>
							</s:if>
							<s:if test='isused=="1"'>
								<font color="#FF8400">显示</font>
							</s:if>
						</td>
						<td align="center">
							<input type="button" value="编辑" class="btn-style"
								onclick="btnEditClick('${model}','${typename}','${isoppo}','${isused}')" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br>
		<br>

		<div id="div_edit" style="display: none;">
			<s:form cssClass="submit" namespace="/app/commonsetting">
				<table id="tblEdit" class="submit" align="center" border="1"
					cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
					<tr>
						<td>
							系列
						</td>
						<td>
							<input type="text" id="form.typename" name="form.typename" />
						</td>
					</tr>
					<tr>
						<td>
							机型
						</td>
						<td>
							<input type="text" disabled="disabled" id="form.model"
								name="form.model" />
						</td>
					</tr>
					<tr>
						<td>
							是否OPPO手机
						</td>
						<td>
							<input id="form.isoppo" type="checkbox" name="form.isoppo" />
						</td>
					</tr>
					<tr>
						<td>
							是否显示
						</td>
						<td>
							<input id="form.isused" type="checkbox" name="form.isused" />
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<input type="button" id="btnupdate" value="更新" class="btn-style"
								onclick="updateInfo();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>

		<script type="text/javascript">
/**
 * 生成查询的models
 */
function buildModels() {
	var chks = $("input[name='chkModel']:checked");
	var models = "";
	for (i = 0; i < chks.length; i++) {
		models += "'" + chks[i].value + "'";
		if (i != chks.length - 1) {
			models += ",";
		}
	}
	return models;
}

// 统一调整
function btnBothChange(){
	//限制查询选择太多
	var chks = $("input[name='chkModel']:checked");
	if(chks.length > 200){
		alert("批量操作选项超过200条，请拆分后再操作。。。");
		return;
	}
	
	var models = buildModels();
	var typename = document.getElementById("typename").value;
	var isoppo = $('input[name="isoppo"]:checked').val();
	var isused = $('input[name="isused"]:checked').val();
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_model_update.do',
		data : "&form.model=" + models + "&form.typename=" + typename
				+ "&form.isoppo=" + isoppo + "&form.isused=" + isused,
		success : function(result) {
			window.location.href = "setting_model_init.do";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
		
// 编辑		
function btnEditClick(model, typename, isoppo, isused) {
	document.getElementById("div_edit").style.display = 'block';

	document.getElementById("form.model").value = model;
	document.getElementById("form.typename").value = typename;
	if (isoppo == 1) {
		document.getElementById("form.isoppo").checked = 'checked';
	} else {
		document.getElementById("form.isoppo").checked = null;
	}
	if (isused == 1) {
		document.getElementById("form.isused").checked = 'checked';
	} else {
		document.getElementById("form.isused").checked = null;
	}

	document.getElementById('div_edit').scrollIntoView();
}

// 更新
function updateInfo() {
	var model = document.getElementById("form.model").value;
	var typename = document.getElementById("form.typename").value;
	var isoppo = 0;
	var isused = 0;
	if (true == document.getElementById("form.isoppo").checked) {
		isoppo = 1;
	} else {
		isoppo = 0;
	}
	if (true == document.getElementById("form.isused").checked) {
		isused = 1;
	} else {
		isused = 0;
	}

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_model_update.do',
		data : "&form.model='" + model + "'&form.typename=" + typename
				+ "&form.isoppo=" + isoppo + "&form.isused=" + isused,
		success : function(result) {
			window.location.href = "setting_model_init.do";
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>