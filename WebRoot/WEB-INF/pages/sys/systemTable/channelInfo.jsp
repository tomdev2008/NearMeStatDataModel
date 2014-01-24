<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>


<html>
	<head>
		<title>渠道系统表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js">
</script>
	</head>
	<body class="context">
		<div class="title">
			渠道系统表
		</div>
		<div class="data-load">
		<table class="data-load" id="tblChannels" border="1" cellspacing="0"
			bordercolor="#EFEFEF" cellpadding="3">
			<tr class="data-head">
				<th>
					序号
				</th>
				<th>
					渠道ID
				</th>
				<th>
					渠道名称
				</th>
				<th>
					渠道描述
				</th>
				<th>
					更新时间
				</th>
				<th>
					最后操作
				</th>
				<th>
					操作
				</th>
			</tr>
			<s:iterator value="channelList">
				<tr>
					<td>
						${seqID }
					</td>
					<td>
						${channelID }
					</td>
					<td>
						${channelName }
					</td>
					<td>
						${channelDesc }
					</td>
					<td>
						<s:date name="updateDate" format="yyyy-MM-dd" />
					</td>
					<td>
						${updateBy }
					</td>
					<td>
						<input type="button" value="编辑"
							onclick="btnEditClick('${seqID }')" class="btn-style" />
						<input type="button" value="删除"
							onclick="if(confirm('确定删除  ${channelName }  渠道?')){btnDeleteClick('${channelID }');}"
							class="btn-style" />
					</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		<br>
		<br>
		<form id="formChannel" action="" method="post" class="submit">
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						渠道ID
					</td>
					<td>
						<input type="text" id="form.channelID" name="form.channelID" />
					</td>
				</tr>
				<tr>
					<td>
						渠道名称
					</td>
					<td>
						<input type="text" id="form.channelName" name="form.channelName" />
					</td>
				</tr>
				<tr>
					<td>
						渠道描述
					</td>
					<td>
						<input type="text" id="form.channelDesc" name="form.channelDesc" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" id="action" name="action" value="add"
							class="btn-style" />
						<input type="submit" id="btnSubmit" value="新增" class="btn-style" />
						<input type="button" style="display: none;"
							onclick="btnCancelEdit()" id="btnClear" value="取消编辑"
							class="btn-style" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
function btnEditClick(sid) {
	tbl = document.getElementById("tblChannels");
	rowlen = tbl.rows.length;
	row = null;
	for (i = 1; i < rowlen; i++) {
		if (tbl.rows[i].cells[0].innerHTML.trim() == sid) {
			row = tbl.rows[i];
			break;
		}
	}
	if (row == null) {
		return;
	}
	document.getElementById("form.channelID").value = row.cells[1].innerHTML
			.trim();
	document.getElementById("form.channelName").value = row.cells[2].innerHTML
			.trim();
	document.getElementById("form.channelDesc").value = row.cells[3].innerHTML
			.trim();
	document.getElementById("action").value = "update";
	document.getElementById("btnSubmit").value = "更新";
}function btnDeleteClick(mdl){
	document.getElementById("action").value="delete";
	document.getElementById("form.channelID").value=mdl;
	document.getElementById("formChannel").submit();
}
function btnCancelEdit(){
	document.getElementById("action").value="add";
	document.getElementById("btnClear").style.display="none";
}
</script>
	</body>
</html>