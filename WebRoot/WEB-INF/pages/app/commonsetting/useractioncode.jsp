<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>用户行为编码</title>
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
		<script src="../../js/jquery.dragndrop.min.js"
			type="text/javascript">
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
			用户行为编码
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<table class="data-load">
			<tr>
				<td width="50%">
					<div class="data-load">
						<table id="dataSourceTbl" class="data-load" border="1"
							cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
							<tr class="data-head">
								<th>
									分组编码
								</th>
								<th>
									分组名称
								</th>
								<th>
									操作
								</th>
							</tr>
							<s:iterator value="usergroupList">
								<tr>
									<td>
										${groupcode }
									</td>
									<td>
										${groupname }
									</td>
									<td>
										<a href="javascript:showGroupinfo(${form.systemID},'${groupcode}','${groupname}')">更改</a>
										<a href="javascript:deleteGroupinfo(${form.systemID},'${groupcode}')">删除</a>
										<a href="javascript:queryActioncode(${form.systemID},'${groupcode}','${groupname}')">查看</a>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</td>
				<td width="50%">
					<div id="div_useractioncode_actioninfo" style="display: none;">
						<s:include value="useractioncode_detail.jsp" />
					</div>
					<div id="div_useractioncode_groupinfo" style="display: none;">
						<s:include value="useractioncode_groupinfo.jsp"></s:include>
					</div>
				</td>
			</tr>
		</table>

		<br>
		<br>
		<s:form cssClass="submit" namespace="/app/commonsetting">
			<s:hidden id="form_systemID" name="form.systemID"></s:hidden>
			<div class="title" align="center">
				添加分组编码
			</div>
			<table class="submit" align="center" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						分组编码:
					</td>
					<td>
						<s:textfield id="form_groupcode" name="form.groupcode" label="分组编码*"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						分组名称:
					</td>
					<td>
						<s:textfield id="form_groupname" name="form.groupname" label="分组名称"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="提交" class="btn-style"
							onclick="addGroupinfo();" />
					</td>
				</tr>
			</table>
		</s:form>

		<script type="text/javascript">
// 查看行为编码
function queryActioncode(systemID, groupcode, groupname) {
	document.getElementById("div_useractioncode_actioninfo").style.display = 'block';
	document.getElementById("div_useractioncode_groupinfo").style.display = 'none';
	document.getElementById("groupform_groupcode").value = groupcode;
	document.getElementById("groupform_groupname").value = groupname;
	
	document.getElementById("actionform_operation").value = "add";//设置为执行添加操作
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_queryactioncode.do',
		data : '&form.systemID=' + systemID + '&form.groupcode=' + groupcode
				+ '&form.groupname=' + groupname,
		success : function(result) {
			$("#div_useractioncode_actioninfo").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 显示分组信息
function showGroupinfo(systemID, groupcode, groupname) {
	document.getElementById("div_useractioncode_actioninfo").style.display = "none";
	document.getElementById("div_useractioncode_groupinfo").style.display = "block";
	
	//传值
	document.getElementById("groupform_groupcode").value = groupcode;
	document.getElementById("groupform_groupname").value = groupname;
}

// 添加分组信息
function addGroupinfo() {
	var systemID = document.getElementById("form_systemID").value;
	var groupcode = document.getElementById("form_groupcode").value;
	var groupname = document.getElementById("form_groupname").value;
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_addgroupinfo.do',
		data : '&form.systemID=' + systemID + '&form.groupcode=' + groupcode
				+ '&form.groupname=' + groupname,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?form.systemID="+systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 更改分组信息
function updateGroupinfo() {
	var systemID = document.getElementById("form_systemID").value;
	var groupcode = document.getElementById("groupform_groupcode").value;
	var groupname = document.getElementById("groupform_groupname").value;
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_updategroupinfo.do',
		data : '&form.systemID=' + systemID + '&groupform.groupcode=' + groupcode
				+ '&groupform.groupname=' + groupname,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?form.systemID="+systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 删除分组信息
function deleteGroupinfo(systemID, groupcode) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_deletegroupinfo.do',
		data : '&form.systemID=' + systemID 
		     + '&form.groupcode=' + groupcode,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?form.systemID="+systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}




// 操作行为信息
function operationActioninfo() {
	var systemID = document.getElementById("form_systemID").value;
	var groupcode = document.getElementById("groupform_groupcode").value;
	var groupname = document.getElementById("groupform_groupname").value;
	var actioncode = document.getElementById("actionform_actioncode").value;
	var actionname = document.getElementById("actionform_actionname").value;
	var operation = document.getElementById("actionform_operation").value;
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_operationactioninfo.do',
		data : '&form.systemID=' + systemID 
		     + '&actionform.operation=' + operation
		     + '&actionform.groupcode=' + groupcode
		     + '&actionform.actioncode=' + actioncode
			 + '&actionform.actionname=' + actionname,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?form.systemID="+systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

//查看行为编码
function showActioninfo(systemID, groupcode, groupname, actioncode, actionname) {
	document.getElementById("div_useractioncode_actioninfo").style.display = 'block';
	document.getElementById("div_useractioncode_groupinfo").style.display = 'none';
	
	//传值
	document.getElementById("groupform_groupcode").value = groupcode;
	document.getElementById("groupform_groupname").value = groupname;
	document.getElementById("actionform_actioncode").value = actioncode;
	document.getElementById("actionform_actionname").value = actionname;
	
	document.getElementById("actionform_operation").value = "update";//设置为执行更新操作
}

//删除行为编码
function deleteActioninfo(systemID, groupcode, actioncode){
	document.getElementById("actionform_operation").value = "add";//设置为执行添加操作
	
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_useractioncode_deleteactioninfo.do',
		data : '&form.systemID=' + systemID 
		     + '&actionform.groupcode=' + groupcode
		     + '&actionform.actioncode=' + actioncode,
		success : function(result) {
			window.location.href = "setting_useractioncode_init.do?form.systemID="+systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

//查看行为数据
function queryUseraction(systemID,groupcode,actioncode){
	window.location.href = "../common/common_useraction_init.do?form.systemID=" + systemID
	                     + "&form.groupcode=" + groupcode
	                     + "&form.actioncode=" + actioncode;
}
</script>
	</body>
</html>