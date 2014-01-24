<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>


<html>
	<head>
		<title>联网方式系统表维护</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<script type="text/javascript" src="../../js/global.js"></script>
	</head>
	<body>
		<form id="form" method="post" action="shareTarget_execute.do">
			<div id="cardTypeList" style="width: 600px;">
				<br>
				<table id="networkTypeTbl" border="1" cellspacing="0" bordercolor="#EFEFEF" cellpadding="3" width="98%">
					<tr style="background-color:#DEEBFB">
					
						<td>分享目标ID</td>
            			<td>分享目标名称</td>
					</tr>
					<s:iterator value="list">
						<tr>
							<td style="width: 30%">
								<s:property value="id" />
							</td>
							<td style="width: 30%">
								<s:property value="shareTargetName" />
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
							分享目标ID：
						</td>
						<td>
							<input type="text" id="networkTypeID" name="networkTypeID"
								value="${pointCardType.typeName }" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td>
							分享目标名称：
						</td>
						<td>
							<input type="text" id="networkName" name="networkName"
								value="${pointCardType.typeCode }" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
                          <input type="button" id="btnAdd" value="新 增"
                            onclick="btnAddAction()"/>
                          <input type="button" id="btnUpdate" value="修 改"
                            onclick="btnUpdateAction()"/>
                          <input type="button" id="btnDelete" value="删除"
                            onclick="btnDeleteAction()"/>
                          <input type="submit" id="applyUpdate" value="应 用"
                            disabled="disabled" onclick="CheckInput()"/>
						</td>
					</tr>
				</table>
			</div>

			<div id="hiddenFileds">
				<input type="hidden" id="operation" name="operation"
					value="${operation }" />
				<input type="hidden" id="selectTypeID" name="selectTypeID"
					value="${pointCardType.typeName }" />
				<input type="hidden" id="selectTypeName" name="selectTypeName"
					value="${pointCardType.typeCode }" />
				<input type="hidden" id="selectRowNo" name="selectRowNo"
					value="${selectRowNo }" />
				<input type="hidden" id="errMsg" name="errMsg" value="${errMsg }">
				<input type="hidden" id="successMsg" name="successMsg"
					value="${successMsg }">
			</div>
		</form>
		<script type="text/javascript" src="../../js/global.js"></script>
		<script language="javascript" type="text/javascript">
	/*
		表格选中行高亮，并将选中行的值保存到文本框中
	 */
	window.onload = function() {
		//autoCutOffStrInTable('networkTypeTbl', 1, 10);
		//autoCutOffStrInTable('networkTypeTbl', 3, 20);//页面加载时截断文本

		// 取得表格对象
		var tbl = document.getElementById("networkTypeTbl");
		for ( var i = 1; i < tbl.rows.length; i++) {
			// 设置表格点击行事件
			tbl.rows[i].onclick = function() {
				if (window.cur)
					window.cur.style.background = "#FFF";
				this.style.background = "#CCC";
				window.cur = this;

				// 将选中行的值赋值给文本框和隐藏域
				// 积分卡面值
				document.getElementById("networkTypeID").value = this.cells[0].innerHTML.trim();
				document.getElementById("selectTypeID").value = this.cells[0].innerHTML.trim();
				// 积分卡代码
				document.getElementById("networkName").value = this.cells[1].innerHTML.trim();
				document.getElementById("selectTypeName").value = this.cells[1].innerHTML.trim();

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
			document.getElementById("networkTypeTbl").rows[rowno].style.background = "#CCC";
			window.cur = document.getElementById("networkTypeTbl").rows[rowno];
		}

		// 提示操作结果
		if (document.getElementById("errMsg").value != "") {
			alert(document.getElementById("errMsg").value);
			document.getElementById("errMsg").value = "";
		}
		if (document.getElementById("successMsg").value != "") {
			alert(document.getElementById("successMsg").value);
			document.getElementById("successMsg").value = "";
		}
	}

	/*
		设置各控件的状态
	 */
	function SetContorlsActive(flg) {
		// 取得各控件
		// 积分卡面值
		var networkID = document.getElementById("networkTypeID");
		// 积分卡代码
		var networkName = document.getElementById("networkName");
		// 应用按钮
		var btnApplyUpdate = document.getElementById("applyUpdate");

		switch (flg) {
		case "add": // 点击新增按钮时
			networkID.disabled = false;
			networkName.disabled = false;
			btnApplyUpdate.disabled = false;
			break;
		case "update": // 点击修改按钮时
			networkID.disabled = true;
			networkName.disabled = false;
			btnApplyUpdate.disabled = false;
			break;
        case "delete": // 点击删除按钮时
			networkID.disabled = true;
			networkName.disabled = true;
			btnApplyUpdate.disabled = false;
          break;
		default:
			networkID.disabled = true;
			networkName.disabled = true;
			btnApplyUpdate.disabled = true;
			break;
		}
	}

	/*
		新建积分卡类型时，设置控件活性及操作标识
	 */
	function btnAddAction() {
		// 设置控件活性
		SetContorlsActive('add');
		// 清空控件的值
		document.getElementById("networkTypeID").value = "";
		document.getElementById("networkName").value = "";
		// 清空隐藏域中所选择的积分卡面值
		document.getElementById("selectTypeID").value = "";
		// 清除表格选中行的状态
		var rowno = document.getElementById("selectRowNo").value;
		if (rowno > 0) {
			document.getElementById("networkTypeTbl").rows[rowno].style.background = "#FFF";
		}

		// 设置光标位置
		document.getElementById("networkTypeID").focus();
		// 设置操作标识为"add"
		document.getElementById("operation").value = "add";
	}

	/*
		更新积分卡类型时，设置控件活性及操作标识
	 */
	function btnUpdateAction() {
		// 检验是否选择了一个积分卡类型
		if (document.getElementById("selectTypeID").value.replace(
				/(^\s*)|(\s*$)/g, "") == "") {
			alert("请选择要更新的联网方式！");
			return false;
		}

		// 设置控件活性
		SetContorlsActive('update');
		// 设置光标位置
		document.getElementById("networkName").focus();
		// 设置操作标识为"add"
		document.getElementById("operation").value = "update";
	}

      function btnDeleteAction() {
        // 检验是否选择了一个积分卡类型
        if (document.getElementById("selectTypeID").value.replace(
            /(^\s*)|(\s*$)/g, "") == "") {
          alert("请选择要删除的联网方式！");
          return false;
        }

        // 设置控件活性
        SetContorlsActive('delete');
        // 设置光标位置
        document.getElementById("networkName").focus();
        // 设置操作标识为"add"
        document.getElementById("operation").value = "delete";
      }

	/*
		验证值输入的合法性
	 */
	function CheckInput() {
		// 确认是否要执行插入或更新操作
		if (document.getElementById("operation").value == "update") {
			if (confirm("您确定要修改该联网方式信息吗？") == false) {
				return false;
			}
		} else if (document.getElementById("operation").value == "add") {
			if (confirm("您确定要添加该联网方式信息吗？") == false) {
				return false;
			}
		} else {
			return false;
		}

		// 取得各控件的值，验证输入的合法性
		// 联网方式ID
		var networkID = document.getElementById("networkTypeID").value
				.replace(/(^\s*)|(\s*$)/g, "");
		// 联网方式名称
		var networkName = document.getElementById("networkName").value
				.replace(/(^\s*)|(\s*$)/g, "");

		// 联网方式ID是否输入
		if (networkID == "") {
			alert("请输入联网方式ID！");
			document.getElementById("networkTypeID").focus();
			return false;
		}
		// 联网方式名称是否输入
		if (networkName == "") {
			alert("请输入联网方式名称！");
			document.getElementById("networkName").focus();
			return false;
		}

		// 验证正整数的正则表达式
		var reg = new RegExp("^((0)|([1-9]{1}[0-9]*))$");
		// 验证联网方式ID是否为正整数
		if (reg.test(networkID) == false) {
			alert("联网方式ID输入应为正整数，请确认！");
			document.getElementById("networkTypeID").focus();
			return false;
		}

        // 验证联网方式ID的大小（限制最大为9位数）
		if (networkID.length > 9) {
			alert("联网方式ID设置过大，请重新输入！");
			document.getElementById("networkTypeID").focus();
			return false;
		}
		// 验证联网方式名称的长度
		if (networkName.length > 24) {
			alert("联网方式名设置过长，请重新输入！");
			document.getElementById("networkTypeName").focus();
			return false;
		}

		// 提交表单
		document.getElementById("form").submit	();
	}
</script>
	</body>
</html>