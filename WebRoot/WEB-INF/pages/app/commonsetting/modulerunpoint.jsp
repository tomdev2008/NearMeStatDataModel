<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>模块运营点管理</title>
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
			模块运营点管理
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>

		<div class="data-load">
			<table class="data-load" id="tblChannels" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						ID
					</th>
					<th>
						类型
					</th>
					<th>
						分组名称
					</th>
					<th>
						来源(SOURCE_CODE)
					</th>
					<th>
						来源描述
					</th>
					<th>
						分类ID
					</th>
					<th>
						位置
					</th>
					<th>
						下载类型
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="modulerunpointList">
					<tr>
						<td>
							${id }
						</td>
						<td>
							${packagename }
						</td>
						<td>
							${groupname }
						</td>
						<td>
							${sourcecode }
						</td>
						<td>
							${sourcedesc }
						</td>
						<td>
							${categoryid }
						</td>
						<td>
							${clickindex }
						</td>
						<td>
							${tag }
						</td>
						<td align="center">
							<input type="button" value="编辑" class="btn-style"
								onclick="btnEditClick('${id}','${packagename}','${groupname}','${sourcecode}','${sourcedesc}','${categoryid}','${clickindex}','${tag}')" />
							<input type="button" value="删除" class="btn-style"
								onclick="if(confirm('确定删除  ${id }  对应的信息?')){btnDeleteClick('${id}');}" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br>
		<br>

		<div class="div_help_content" id="div_help_content">
			<div class="title">
				<font color="red">注意事项：</font>
			</div>
			<div class="body">
				1.分组： 例如：模块(首页推荐)、运营点(小广告位第一位)
				<br>
				2.来源： 模块、运营点对应的source_code；多个则以英文状态逗号(,)隔开，也可以分多次添加
				<br>
				3.分类ID: 必须是动态壁纸、角色扮演等最小分类；不能是音乐、游戏、应用等大的分类 ,可为空
				<br>
				4.位置： 运营点所在页面的位置 ，可为空
				<br>
				5.下载类型： 需要区分直接下载或者详情下载的时候，设置该值。
				<br>
				<font color="red">&nbsp;(必选)与sourcecode保持一致，否则会影响单个资源运营查询。</font>
				<br>
			</div>
		</div>

		<s:form cssClass="submit" namespace="/app/commonsetting">
			<s:hidden id="form.systemID" name="form.systemID"></s:hidden>
			<s:hidden id="form.id" name="form.id"></s:hidden>
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						类型
					</td>
					<td>
						<s:select id="form.packagename" name="form.packagename"
							list="#{'模块':'模块','运营点':'运营点'}">
						</s:select>
					</td>
				</tr>
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
						位置
					</td>
					<td>
						<input type="text" id="form.clickindex" name="form.clickindex" />
					</td>
				</tr>
				<tr>
					<td>
						下载类型
					</td>
					<td>
						<s:select id="form.tag" name="form.tag"
							list="#{'ZJ':'直接下载','XQ':'详情下载'}">
						</s:select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnadd" value="添加" class="btn-style"
							onclick="addInfo();" style="display: block;" />
						<input type="button" id="btnupdate" value="更新" class="btn-style"
							onclick="updateInfo();" style="display: none;" />
					</td>
				</tr>
			</table>
		</s:form>

		<script type="text/javascript">
// 添加
function addInfo() {
	var systemID = document.getElementById("form.systemID").value;
	var packagename = document.getElementById("form.packagename").value;
	var groupname = document.getElementById("form.groupname").value;
	var sourcecode = document.getElementById("form.sourcecode").value;
	var sourcedesc = document.getElementById("form.sourcedesc").value;
	var categoryid = document.getElementById("form.categoryid").value;
	var clickindex = document.getElementById("form.clickindex").value;
	var tag = document.getElementById("form.tag").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_modulerunpoint_add.do',
		data : '&form.systemID=' + systemID + '&form.packagename='
				+ packagename + '&form.groupname=' + groupname
				+ '&form.sourcecode=' + sourcecode
				+ '&form.sourcedesc=' + sourcedesc
				+ '&form.categoryid=' + categoryid
				+ '&form.clickindex=' + clickindex + '&form.tag=' + tag,
		success : function(result) {
			window.location.href = "setting_modulerunpoint_init.do?form.systemID="
				+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 编辑		
function btnEditClick(id, packagename, groupname, sourcecode, sourcedesc,
		categoryid, clickindex, tag) {
	document.getElementById("form.id").value = id;
	document.getElementById("form.packagename").value = packagename;
	document.getElementById("form.groupname").value = groupname;
	document.getElementById("form.sourcecode").value = sourcecode;
	document.getElementById("form.sourcedesc").value = sourcedesc;
	document.getElementById("form.categoryid").value = categoryid;
	document.getElementById("form.clickindex").value = clickindex;
	document.getElementById("form.tag").value = tag;

	document.getElementById("btnadd").style.display = 'none';
	document.getElementById("btnupdate").style.display = 'block';
}

// 删除
function btnDeleteClick(id) {
	var systemID = document.getElementById("form.systemID").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_modulerunpoint_delete.do',
		data : '&form.id=' + id + '&form.systemID=' + systemID,
		success : function(result) {
			window.location.href = "setting_modulerunpoint_init.do?form.systemID="
					+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

// 更新
function updateInfo() {
	var id = document.getElementById("form.id").value;
	var systemID = document.getElementById("form.systemID").value;
	var packagename = document.getElementById("form.packagename").value;
	var groupname = document.getElementById("form.groupname").value;
	var sourcecode = document.getElementById("form.sourcecode").value;
	var sourcedesc = document.getElementById("form.sourcedesc").value;
	var categoryid = document.getElementById("form.categoryid").value;
	var clickindex = document.getElementById("form.clickindex").value;
	var tag = document.getElementById("form.tag").value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'setting_modulerunpoint_update.do',
		data : '&form.id=' + id + '&form.packagename=' + packagename
				+ '&form.groupname=' + groupname + '&form.sourcecode='
				+ sourcecode + '&form.sourcedesc=' + sourcedesc
				+ '&form.categoryid=' + categoryid
				+ '&form.clickindex=' + clickindex + '&form.tag=' + tag
				+ '&form.systemID=' + systemID,
		success : function(result) {
			window.location.href = "setting_modulerunpoint_init.do?form.systemID="
					+ systemID;
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</body>
</html>