<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>游戏中心产品信息表</title>
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
	</head>
	<body class="context">
		<div class="title">
			游戏中心产品信息表
		</div>
		<div class="data-load">
			<table id="tblAppInfos" class="data-load" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						序号
					</th>
					<th>
						产品ID
					</th>
					<th>
						系统ID
					</th>
					<th>
						产品名称
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
				<s:iterator value="gcProductList">
					<tr>
						<td>
							${seqID }
						</td>
						<td>
							${productID }
						</td>
						<td>
							${systemID }
						</td>
						<td>
							${productName }
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
								onclick="if(confirm('确定删除  ${systemName }  应用?')){btnDeleteClick('${systemID }');}"
								class="btn-style" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br />
		<br />
		<form id="formAppInfo" action="" method="post" class="submit">
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						产品ID
					</td>
					<td>
						<input type="text" id="form.productID" name="form.productID" />
					</td>
				</tr>
				<tr>
					<td>
						系统ID
					</td>
					<td>
						<input type="text" id="form.systemID" name="form.systemID" />
					</td>
				</tr>
				<tr>
					<td>
						产品名称
					</td>
					<td>
						<input type="text" id="form.productName" name="form.productName" />
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
	tbl = document.getElementById("tblAppInfos");
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
	document.getElementById("form.productID").value = row.cells[1].innerHTML
			.trim();
	document.getElementById("form.systemID").value = row.cells[2].innerHTML
			.trim();
	document.getElementById("form.productName").value = row.cells[3].innerHTML
			.trim();
	document.getElementById("action").value = "update";
	document.getElementById("btnSubmit").value = "更新";
}
function btnDeleteClick(mdl) {
	document.getElementById("action").value = "delete";
	document.getElementById("form.systemID").value = mdl;
	document.getElementById("formAppInfo").submit();
}
function btnCancelEdit() {
	document.getElementById("action").value = "add";
	document.getElementById("btnClear").style.display = "none";
}
</script>
	</body>
</html>