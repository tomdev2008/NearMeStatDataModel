<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>


<html>
	<head>
		<title>分类信息表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js">
</script>
	</head>
	<body class="context">
		<div class="title">
			分类信息表
		</div>
		<div class="data-load">
			<table id="tblCategory" class="data-load" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						序号
					</th>
					<th>
						应用ID
					</th>
					<th>
						分类ID
					</th>
					<th>
						分类名称
					</th>
					<th>
						分类层次
					</th>
					<th>
						分类父类ID
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
				<s:iterator value="categoryList">
					<tr>
						<td>
							${seqID }
						</td>
						<td>
							${systemID }
						</td>
						<td>
							${categoryID }
						</td>
						<td>
							${categoryName }
						</td>
						<td>
							${categoryLevel }
						</td>
						<td>
							${categoryParentID }
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
								onclick="if(confirm('确定删除  ${categoryName }  渠道?')){btnDeleteClick('${categoryID }');}"
								class="btn-style" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br />
		<form id="formCategory" action="" method="post" class="submit">
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						应用ID
					</td>
					<td>
						<input type="text" id="form.systemID" name="form.systemID" />
					</td>
				</tr>
				<tr>
					<td>
						分类ID
					</td>
					<td>
						<input type="text" id="form.categoryID" name="form.categoryID" />
					</td>
				</tr>
				<tr>
					<td>
						分类名称
					</td>
					<td>
						<input type="text" id="form.categoryName" name="form.categoryName" />
					</td>
				</tr>
				<tr>
					<td>
						分类层次
					</td>
					<td>
						<input type="text" id="form.categoryLevel"
							name="form.categoryLevel" />
					</td>
				</tr>
				<tr>
					<td>
						分类父类ID
					</td>
					<td>
						<input type="text" id="form.categoryParentID"
							name="form.categoryParentID" />
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
	tbl = document.getElementById("tblCategory");
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
	document.getElementById("form.systemID").value = row.cells[1].innerHTML
			.trim();
	document.getElementById("form.categoryID").value = row.cells[2].innerHTML
			.trim();
	document.getElementById("form.categoryName").value = row.cells[3].innerHTML
			.trim();
	document.getElementById("form.categoryLevel").value = row.cells[4].innerHTML
			.trim();
	document.getElementById("form.categoryParentID").value = row.cells[5].innerHTML
			.trim();
	document.getElementById("action").value = "update";
	document.getElementById("btnSubmit").value = "更新";
}
function btnDeleteClick(mdl) {
	document.getElementById("action").value = "delete";
	document.getElementById("form.categoryID").value = mdl;
	document.getElementById("formCategory").submit();
}
function btnCancelEdit() {
	document.getElementById("action").value = "add";
	document.getElementById("btnClear").style.display = "none";
}
</script>
	</body>
</html>