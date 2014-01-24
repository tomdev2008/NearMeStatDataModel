<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>联网方式系统表</title>
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
	</head>
	<body class="context">
		<div class="title">
			联网方式系统表
		</div>
		<div class="data-load">
			<table id="tblNetworkTypes" class="data-load" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<th>
						序号
					</th>
					<th>
						网络类型编号
					</th>
					<th>
						网络类型组
					</th>
					<th>
						网络类型名称
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
				<s:iterator value="networkTypeList">
					<tr>
						<td>
							${seqID }
						</td>
						<td>
							${networkID }
						</td>
						<td>
							${networkGroup }
						</td>
						<td>
							${networkName }
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
								onclick="if(confirm('确定删除  ${networkName }  网络类型')){btnDeleteClick('${networkID }');}"
								class="btn-style" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<br />
		<br />
		<form class="submit" id="formNetworkType" action="" method="post">
			<table id="tblEdit" class="submit" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr>
					<td>
						网络类型编号
					</td>
					<td>
						<input type="text" id="form.networkID" name="form.networkID"
							class="btn-style" />
					</td>
				</tr>
				<tr>
					<td>
						网络类型组
					</td>
					<td>
						<input type="text" id="form.networkGroup" name="form.networkGroup"
							class="btn-style" />
						(2G/3G/WIFI...)
					</td>
				</tr>
				<tr>
					<td>
						网络类型名称
					</td>
					<td>
						<input type="text" id="form.networkName" name="form.networkName"
							class="btn-style" />
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
	tbl = document.getElementById("tblNetworkTypes");
	rowlen = tbl.rows.length;
	row = null;
	for (i = 1; i < rowlen; i++) {
		if (tbl.rows[i].cells[0].innerHTML.trim() == sid) {
			row = tbl.rows[i];
			break;
		}
	}
	if (row == null) {
		return
		
		


	}
	;
	document.getElementById("form.networkID").value = row.cells[1].innerHTML
			.trim();
	document.getElementById("form.networkGroup").value = row.cells[2].innerHTML
			.trim();
	document.getElementById("form.networkName").value = row.cells[3].innerHTML
			.trim();
	document.getElementById("action").value = "update";
	document.getElementById("btnSubmit").value = "更新";
}function btnDeleteClick(mdl){
	document.getElementById("action").value="delete";
	document.getElementById("form.networkID").value=mdl;
	document.getElementById("formNetworkType").submit();
}
function btnCancelEdit(){
	document.getElementById("action").value="add";
	document.getElementById("btnClear").style.display="none";
}
</script>
	</body>
</html>