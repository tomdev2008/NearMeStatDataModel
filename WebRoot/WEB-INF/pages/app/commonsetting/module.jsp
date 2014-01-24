<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>模块管理</title>
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
			模块管理
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>


		<div class="div_help_content">
			<div id="sync_tip"></div>
			<div id="sync_btn" style="display: block;">
				<input type="button" value="同步" onclick="sync();" class="btn-style"
					style="float: left;" />
				<font color="red">(操作完后点击同步按钮)</font>
			</div>
		</div>
		<br>

		<table class="data-load">
			<tr>
				<td width="28%" valign="top">
					<div id="div_group" class="data-load" style="display: block;">
						<s:include value="module_group.jsp" />
					</div>
				</td>
				<td width="72%" valign="top">
					<div id="div_detail" class="data-load">
						<s:include value="module_detail.jsp" />
					</div>
				</td>
			</tr>
		</table>



		<div class="div_help_content" id="div_help_content">
			<div class="title">
				<font color="red">注意事项：</font>
			</div>
			<div class="body">
				1.分组： 例如：首页推荐、应用榜；
				<br>
				2.来源： 模块对应的source_code；
				<br>
				3.分类ID: 必须是动态壁纸、角色扮演等最小分类；不能是音乐、游戏、应用等大的分类 ,可为空；
				<br>
				4.下载类型： 需要区分“直接下载”或者“详情下载”；
				<br>
				5.操作完后，请点击同步按钮。
				<br>
			</div>
		</div>
		<s:form id="form" cssClass="submit" namespace="/app/commonsetting">
			<s:hidden id="form.systemID" name="form.systemID"></s:hidden>
			<s:hidden id="form.id" name="form.id"></s:hidden>
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						分组名称*
					</td>
					<td>
						<input type="text" id="form.groupname" name="form.groupname" />
					</td>
				</tr>
				<tr>
					<td>
						来源(SOURCE_CODE)*
					</td>
					<td>
						<input type="text" id="form.sourcecode" name="form.sourcecode" />
					</td>
				</tr>
				<tr>
					<td>
						来源描述
					</td>
					<td>
						<input type="text" id="form.sourcedesc" name="form.sourcedesc" />
					</td>
				</tr>
				<tr>
					<td>
						分类ID
					</td>
					<td>
						<input type="text" id="form.categoryid" name="form.categoryid" />
					</td>
				</tr>
				<tr>
					<td>
						下载类型*
					</td>
					<td>
						<s:select id="form.downtype" name="form.downtype"
							list="#{'ZJ':'直接下载','XQ':'详情下载'}">
						</s:select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnadd" value="添加" class="btn-style"
							onclick="add();" style="display: block;" />
						<input type="button" id="btnupdate" value="更新" class="btn-style"
							onclick="updateDetail();" style="display: none;" />
					</td>
				</tr>
			</table>
		</s:form>

		<div id="import_excel" class="div_help_content">
			<s:form namespace="/app/commonsetting" method="post" action="setting_module_loadExcelData.do" enctype="multipart/form-data">
			    <s:hidden id="form.systemID" name="form.systemID"></s:hidden>
			    <input type="file" title="选择文件" name="excel" id="excel" />
			    <input type="submit" value="导入" />
			</s:form>
		</div>
		
		<div id="export">
			<s:form namespace="/app/commonsetting" method="post">
			    <s:hidden id="form.systemID" name="form.systemID"></s:hidden>
			    <s:submit name="export" value="导出" action="setting_module_export" />
			</s:form>
		</div>
		<script type="text/javascript">
function sync() {
	document.getElementById("sync_btn").style.display = 'none';
	$("#sync_tip").html('<font color="green">同步中...</font>');
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_sync.do',
		success : function(result) {
			$("#sync_tip").html('');
			alert("同步成功");
			document.getElementById("sync_btn").style.display = 'block';
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 分组全选 反选
function checkGroupAllClick() {
	var isChecked = document.getElementById("checkGroupAll").checked;
	var checks = document.getElementsByName("chkGroup");
	for (i = 0; i < checks.length; i++) {
		checks[i].checked = isChecked;
	}
}

// 详情全选  反选
function checkDetailAllClick() {
	var isChecked = document.getElementById("checkDetailAll").checked;
	var checks = document.getElementsByName("chkDetail");
	for (i = 0; i < checks.length; i++) {
		checks[i].checked = isChecked;
	}
}

// 添加
function add() {
	// 正则验证分组名称是否带有单引号
	var groupname = document.getElementById("form.groupname").value;
	var re = /^.*'+.*$/;
	if (re.test(groupname) == true) {
		alert("分组名称不能含英文“’”");
		return;
	}

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_add.do',
		data : $('#form').serialize(),
		success : function(result) {
			// 更新div_detail
		$("#div_detail").html(result);

		// 更新div_group
		$.ajax( {
			type : "POST",
			dataType : "html",
			url : 'setting_module_queryGroup.do',
			data : $('#form').serialize(),
			success : function(result) {
				$("#div_group").html(result);
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	},
	error : function(data) {
		alert("error:" + data.responseText);
	}
	});
}

// 更新
function updateDetail() {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_updateDetail.do',
		data : $('#form').serialize(),
		success : function(result) {
			$("#div_detail").html(result);

			document.getElementById("form.groupname").readOnly = false;
			document.getElementById("btnadd").style.display = 'block';
			document.getElementById("btnupdate").style.display = 'none';
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 生成待操作的分组
function buildGroups() {
	var chks = $("input[name='chkGroup']:checked");
	var groupnames = "";
	for (i = 0; i < chks.length; i++) {
		groupnames += "'" + chks[i].value + "'";
		if (i != chks.length - 1) {
			groupnames += ",";
		}
	}
	return groupnames;
}

// 统一删除分组
function btnGroupBothDelete() {
	var groupnames = buildGroups();
	var systemID = document.getElementById("form.systemID").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_deleteGroup.do',
		data : '&form.systemID=' + systemID + '&form.groupname=' + groupnames,
		success : function(result) {
			window.location.href = "setting_module_init.do?form.systemID="
					+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 删除单个分组
function deleteGroup(systemID, groupname) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_deleteGroup.do',
		data : "&form.systemID=" + systemID + "&form.groupname='" + groupname
				+ "'",
		success : function(result) {
			window.location.href = "setting_module_init.do?form.systemID="
					+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 查看某个分组
function queryDetail(systemID, groupname) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_queryDetail.do',
		data : '&form.systemID=' + systemID + '&form.groupname=' + groupname,
		success : function(result) {
			$("#div_detail").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 编辑详情		
function showDetailClick(id, groupname, sourcecode, sourcedesc, categoryid,
		downtype) {
	document.getElementById("form.id").value = id;
	document.getElementById("form.groupname").value = groupname;
	document.getElementById("form.sourcecode").value = sourcecode;
	document.getElementById("form.sourcedesc").value = sourcedesc;
	document.getElementById("form.categoryid").value = categoryid;
	document.getElementById("form.downtype").value = downtype;

	document.getElementById("form.groupname").readOnly = true;//编辑模式，groupname不可编辑
	document.getElementById("btnadd").style.display = 'none';
	document.getElementById("btnupdate").style.display = 'block';

	document.getElementById('form').scrollIntoView();
}

// 生成待操作的详情
function buildDetails() {
	var chks = $("input[name='chkDetail']:checked");
	var ids = "";
	for (i = 0; i < chks.length; i++) {
		ids += "'" + chks[i].value + "'";
		if (i != chks.length - 1) {
			ids += ",";
		}
	}
	return ids;
}

// 统一删除详情
function btnDetailBothDelete(groupname) {
	var ids = buildDetails();
	var systemID = document.getElementById("form.systemID").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_deleteDetail.do',
		data : '&form.id=' + ids + "&form.systemID=" + systemID
				+ '&form.groupname=' + groupname,
		success : function(result) {
			// 更新div_detail
		$("#div_detail").html(result);

		// 更新div_group
		$.ajax( {
			type : "POST",
			dataType : "html",
			url : 'setting_module_queryGroup.do',
			data : $('#form').serialize(),
			success : function(result) {
				$("#div_group").html(result);
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	},
	error : function(data) {
		alert("error:" + data.responseText);
	}
	});
}

// 删除详情
function deleteDetail(id, groupname) {
	var systemID = document.getElementById("form.systemID").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_module_deleteDetail.do',
		data : "&form.id='" + id + "'" + "&form.systemID=" + systemID
				+ "&form.groupname=" + groupname,
		success : function(result) {
			// 更新div_detail
		$("#div_detail").html(result);

		// 更新div_group
		$.ajax( {
			type : "POST",
			dataType : "html",
			url : 'setting_module_queryGroup.do',
			data : $('#form').serialize(),
			success : function(result) {
				$("#div_group").html(result);
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	},
	error : function(data) {
		alert("error:" + data.responseText);
	}
	});
}
</script>
	</body>
</html>