<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title>游戏中心信息表维护</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="../../js/global.js">
</script>
	</head>
	<body>
		<s:form namespace="/sys/systemTable" action="gcProduct_execute.do">
			<div id="cardTypeList" style="width: 600px;">
				<br>
				<table id="appTypeTbl" border="1" cellspacing="0" bordercolor="#EFEFEF" cellpadding="3" width="98%">
					<tr style="background-color: #DEEBFB">
						<td>
							产品ID
						</td>
						<td>
							产品名称
						</td>
						<td>
							系统ID
						</td>
					</tr>
					<s:iterator value="list">
						<tr>
							<td style="width: 30%">
								<s:property value="productID" />
							</td>
							<td style="width: 30%">
								<s:property value="productName" />
							</td>
							<td style="width: 30%">
								<s:property value="systemID" />
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
			<br>
			<div id="updateCardType" style="width: 600px;">
				<table border="0" width="98%">
					<tr>
						<td>
							产品ID：
						</td>
						<td>
							<input type="text" id="productID" name="productID"
								disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td>
							产品名称：
						</td>
						<td>
							<input type="text" id="productName" name="productName"
								width="128" maxlength="128" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td>
							系统ID：
						</td>
						<td>
							<input type="text" id="systemID" name="systemID"
								disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" id="btnAdd" value="新 增"
								onclick="btnAddAction()" />
							<input type="button" id="btnUpdate" value="修 改"
								onclick="btnUpdateAction()" />
							<input type="button" id="btnDelete" value="删除"
								onclick="btnDeleteAction()" />
							<input type="submit" id="applyUpdate" value="应 用"
								disabled="disabled" onclick="CheckInput()" />
						</td>
					</tr>
				</table>
			</div>

			<div id="hiddenFileds">
				<input type="hidden" id="operation" name="operation"
					value="${operation }" />
				<input type="hidden" id="selectProductID" name="selectProductID"
					value="${selectProductID }" />
				<input type="hidden" id="selectProductName" name="selectProductName"
					value="${selectProductName }" />
				<input type="hidden" id="selectSystemID" name="selectSystemID"
					value="${selectSystemID }" />
				<input type="hidden" id="selectRowNo" name="selectRowNo"
					value="${selectRowNo }" />
			</div>
		</s:form>
		<script type="text/javascript" src="../../js/global.js">
</script>
		<script language="javascript" type="text/javascript">
/*
 表格选中行高亮，并将选中行的值保存到文本框中
 */
window.onload = function() {
	//autoCutOffStrInTable('appTypeTbl', 1, 10);
	//autoCutOffStrInTable('appTypeTbl', 3, 20);//页面加载时截断文本

	// 取得表格对象
	var tbl = document.getElementById("appTypeTbl");
	for ( var i = 1; i < tbl.rows.length; i++) {
		// 设置表格点击行事件
		tbl.rows[i].onclick = function() {
			if (window.cur)
				window.cur.style.background = "#FFF";
			this.style.background = "#CCC";
			window.cur = this;

			// 将选中行的值赋值给文本框和隐藏域
			// 产品ID
			document.getElementById("productID").value = this.cells[0].innerHTML
					.trim();
			document.getElementById("selectProductID").value = this.cells[0].innerHTML
					.trim();
			// 产品名称
			document.getElementById("productName").value = this.cells[1].innerHTML
					.trim();
			document.getElementById("selectProductName").value = this.cells[1].innerHTML
					.trim();
			// 系统ID
			document.getElementById("systemID").value = this.cells[2].innerHTML
					.trim();
			document.getElementById("selectSystemID").value = this.cells[2].innerHTML
					.trim();

			//  将选择行的hanghao保存在隐藏域
			document.getElementById("selectRowNo").value = this.rowIndex;
			// 设置控件不可用
			SetContorlsActive("select");
		}
	}

	// 设置控件状态
	SetContorlsActive(document.getElementById("operation").value);
	// 保持表格选中行的状态
	var rowno = document.getElementById("selectRowNo").value;
	if (rowno > 0) {
		document.getElementById("appTypeTbl").rows[rowno].style.background = "#CCC";
		window.cur = document.getElementById("appTypeTbl").rows[rowno];
	}

}

/*
 设置各控件的状态
 */
function SetContorlsActive(flg) {
	// 取得各控件
	// 产品ID
	var productID = document.getElementById("productID");
	// 产品名称
	var productName = document.getElementById("productName");
	// 系统ID
	var systemID = document.getElementById("systemID");
	// 应用按钮
	var btnApplyUpdate = document.getElementById("applyUpdate");

	switch (flg) {
	case "add": // 点击新增按钮时
		productID.disabled = false;
		productName.disabled = false;
		systemID.disabled = false;
		btnApplyUpdate.disabled = false;
		break;
	case "update": // 点击修改按钮时
		productID.disabled = true;
		productName.disabled = false;
		systemID.disabled = false;
		btnApplyUpdate.disabled = false;
		break;
	case "delete": // 点击删除按钮时
		productID.disabled = true;
		productName.disabled = true;
		systemID.disabled = true;
		btnApplyUpdate.disabled = false;
		break;
	default:
		productID.disabled = true;
		productName.disabled = true;
		systemID.disabled = true;
		btnApplyUpdate.disabled = true;
		break;
	}
}

/*
 新建产品信息时，设置控件活性及操作标识
 */
function btnAddAction() {
	// 设置控件活性
	SetContorlsActive('add');
	// 清空控件的值
	document.getElementById("productID").value = "";
	document.getElementById("productName").value = "";
	document.getElementById("systemID").value = "";
	// 清空隐藏域中所选择的积分卡面值
	document.getElementById("selectProductID").value = "";
	// 清除表格选中行的状态
	var rowno = document.getElementById("selectRowNo").value;
	if (rowno > 0) {
		document.getElementById("appTypeTbl").rows[rowno].style.background = "#FFF";
	}

	// 设置光标位置
	document.getElementById("productID").focus();
	// 设置操作标识为"add"
	document.getElementById("operation").value = "add";
}

/*
 更新产品信息时，设置控件活性及操作标识
 */
function btnUpdateAction() {
	// 检验是否选择了一个产品信息
	if (document.getElementById("selectProductID").value.replace(
			/(^\s*)|(\s*$)/g, "") == "") {
		alert("请选择要更新的产品信息！");
		return false;
	}

	// 设置控件活性
	SetContorlsActive('update');
	// 设置光标位置
	document.getElementById("productName").focus();
	// 设置操作标识为"add"
	document.getElementById("operation").value = "update";
}

function btnDeleteAction() {
	// 检验是否选择了一个产品信息
	if (document.getElementById("selectProductID").value.replace(
			/(^\s*)|(\s*$)/g, "") == "") {
		alert("请选择要删除的产品！");
		return false;
	}

	// 设置控件活性
	SetContorlsActive('delete');
	// 设置操作标识为"delete"
	document.getElementById("operation").value = "delete";
}

/*
 验证值输入的合法性
 */
function CheckInput() {
	// 确认是否要执行插入或更新操作
	if (document.getElementById("operation").value == "update") {
		if (confirm("您确定要修改该产品信息吗？") == false) {
			return false;
		}
	} else if (document.getElementById("operation").value == "add") {
		if (confirm("您确定要添加该产品信息吗？") == false) {
			return false;
		}
	} else {
		return false;
	}

	// 取得各控件的值，验证输入的合法性
	// 联网方式ID
	var productID = document.getElementById("productID").value.replace(
			/(^\s*)|(\s*$)/g, "");
	// 应用Key
	var productName = document.getElementById("productName").value.replace(
			/(^\s*)|(\s*$)/g, "");
	// 应用ID是否输入
	if (productID == "") {
		alert("请输入产品ID！");
		document.getElementById("productID").focus();
		return false;
	}
	// 产品名称是否输入
	if (productName == "") {
		alert("请输入产品名称！");
		document.getElementById("productName").focus();
		return false;
	}

	// 验证正整数的正则表达式
	var reg = new RegExp("^((0)|([1-9]{1}[0-9]*))$");
	// 验证产品ID是否为正整数
	if (reg.test(productID) == false) {
		alert("产品ID输入应为正整数，请确认！");
		document.getElementById("productID").focus();
		return false;
	}

	// 验证联网方式ID的大小（限制最大为9位数）
	if (productID.length > 9) {
		alert("产品ID设置过大，请重新输入！");
		document.getElementById("productID").focus();
		return false;
	}
	// 验证产品名称的长度
	if (productName.length > 64) {
		alert("产品名设置过长，请重新输入！");
		document.getElementById("productName").focus();
		return false;
	}

	// 提交表单
	document.getElementById("form").submit();
}
</script>
	</body>
</html>