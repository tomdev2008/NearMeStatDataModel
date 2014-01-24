<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>


<html>
	<head>
		<title>资源分类系统表维护</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<script type="text/javascript" src="../../js/global.js"></script>
		<style type="text/css">
		.tdLabel{text-align:left;vertical-align:top;}
		</style>
	</head>
	<body>
		<form id="form" method="post" action="sourceType_execute.do">
			<div id="cardTypeList" style="width: 600px;">
				<br>
				<table id="networkTypeTbl" style="width:98%;" border="1" bordercolor="#EFEFEF" cellpadding="3">
					<tr style="background-color:#DEEBFB">
						<td>应用名称</td>
            			<td>分类ID</td>
            			<td>分类名称</td>
            			<td>分类级别</td>
            			<td>父级分类ID</td>
					</tr>
					<s:iterator value="list">
						<tr>
							<td style="width: 30%" title="systemID">
								<s:property value="systemName" />
							</td>
							<td style="width: 30%">
								<a href="system/sourceType_execute.do?URL_SYSTEM_ID=${systemID}&URL_CATEGORY_ID=${categoryID}&URL_CATEGORY_LEVEL=${categoryLevel}"></a>
								<s:property value="categoryID" />
							</td>
							<td style="width: 30%">
								<s:property value="categoryName" />
							</td>
							<td style="width: 300%">
								<s:property value="categoryLevel" />
							</td>
							<td style="width: 30%">
								<s:property value="categoryParentID" />
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
			<br>
			<div id="updateCardType" style="width: 600px;">
				<table border="0" width="98%">
					<tr >
					<td class="tdLabel" >
					<label class="label"  >应用名称:</label>
						</td><td>
						<select value="systemID" name="systemName" >
							<option value="0">
								其他
							</option>
							<option value="1">
								个人中心
							</option>
							<option value="2">
								NearMe市场
							</option>
							<option value="3">
								NearMe书城
							</option>
							<option value="7">
								Ulike市场
							</option>
							<option value="1000">
								游戏中心
							</option>
							<option value="1002">
								AnyPhoto
							</option>
							<option value="2000">
								同步
							</option>
							<option value="2001">
								纳米笔记
							</option>
							<option value="2002">
								OTA
							</option>
							<option value="2003">
								FindPhone
							</option>
							<option value="2004">
								IM
							</option>
							<option value="2101">
								纳米笔记（泰国）
							</option>
							<option value="3000">
								个人中心
							</option>
							<option value="10008">
								PC手机助手
							</option>
							<option value="10009">
								PC主题编辑器
							</option>
						</select>
					</td>
				</tr>
					<tr>
						<td>
							分类ID：
						</td>
						<td>
							<input type="text" id="categoryID" name="categoryID"
								value="${pointCardType.categoryID }" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td>
							分类名称：
						</td>
						<td>
							<input type="text" id="categoryName" name="categoryName"
								value="${pointCardType.categoryName }" disabled="disabled" />
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
        	<input type="hidden" id="selectedSystemID" name="selectedSystemID" />
        	<input type="hidden" id="selectedCategoryID" name="selectedCategoryID" />
        	<input type="hidden" id="selectRowNo" name="selectRowNo" />
        	<input type="hidden" id="operation" name="operation" />
        	<input type="hidden" id="URL_SYSTEM_ID" name="URL_SYSTEM_ID" value="${URL_SYSTEM_ID }">
        	<input type="hidden" id="URL_CATEGORY_ID" name="URL_CATEGORY_ID" value="${URL_CATEGORY_ID }">
        	<input type="hidden" id="URL_PARENT_ID" name="URL_PARENT_ID" value="${URL_PARENT_ID }">
       		 <input type="hidden" id="URL_CATEGORY_LEVEL" name="URL_CATEGORY_LEVEL" value="${URL_CATEGORY_LEVEL }">
        	<input type="hidden" id="errMsg" name="errMsg" value="${errMsg }">
       		 <input type="hidden" id="successMsg" name="successMsg" value="${successMsg }">
      </div>
			
		</form>
		<script type="text/javascript" src="../../js/global.js"></script>
		<script language="javascript" type="text/javascript">
	/*
		表格选中行高亮，并将选中行的值保存到文本框中
	 */
	
window.onload = function() {

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
				setSelectByText("systemID",this.cells[0].innerHTML.trim());
				document.getElementById("categoryID").value = this.cells[1].innerHTML.trim();
				if(this.cells[2].innerText)document.getElementById("categoryName").value = this.cells[2].innerText.trim();

				document.getElementById("selectedSystemID").value = this.cells[0].title.trim();
				document.getElementById("selectedCategoryID").value = this.cells[1].innerHTML.trim();
				document.getElementById("selectRowNo").value = this.rowIndex;
				// 设置控件不可用
				SetContorlsActive("select");
			}
		}

		// 设置控件状态
		SetContorlsActive(document.getElementById("operation").value);

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
		var systemID=document.getElementById("systemName");
		var categoryID = document.getElementById("categoryID");
		// 积分卡代码
		var categoryName = document.getElementById("categoryName");
		// 应用按钮
		var btnApplyUpdate = document.getElementById("applyUpdate");

		switch (flg) {
		case "add": // 点击新增按钮时
			systemID.disabled = false;
			categoryID.disabled = false;
			categoryName.disabled = false;
			btnApplyUpdate.disabled = false;
			break;
		case "update": // 点击修改按钮时
			systemID.disabled = true;
			categoryID.disabled = true;
			categoryName.disabled = false;
			btnApplyUpdate.disabled = false;
			break;
        case "delete": // 点击删除按钮时
        	systemID.disabled = true;
			categoryID.disabled = true;
			categoryName.disabled = true;
			btnApplyUpdate.disabled = false;
          break;
		default:
			systemID.disabled = true;
			categoryID.disabled = true;
			categoryName.disabled = true;
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
		document.getElementById("systemID").value = "";
		document.getElementById("categoryID").value = "";
        document.getElementById("categoryName").value = "";
		// 清除表格选中行的状态
		var rowno = document.getElementById("selectRowNo").value;
		if (rowno > 0) {
			document.getElementById("networkTypeTbl").rows[rowno].style.background = "#FFF";
		}
		document.getElementById("selectRowNo").value = 0;

		// 设置操作标识为"add"
		document.getElementById("operation").value = "add";
		// 设置光标位置
		document.getElementById("categoryName").focus();
	}

	/*
		更新积分卡类型时，设置控件活性及操作标识
	 */
	function btnUpdateAction() {
		// 检验是否选择了一个积分卡类型
		if (document.getElementById("selectRowNo").value<=0) {
			alert("请选择要更新的分类类型！");
			return false;
		}

		// 设置控件活性
		SetContorlsActive('update');
		// 设置光标位置
		document.getElementById("categoryName").focus();
		// 设置操作标识为"add"
		document.getElementById("operation").value = "update";
	}

    /*
      更新积分卡类型时，设置控件活性及操作标识
     */
    function btnDeleteAction() {
      // 检验是否选择了一个积分卡类型
      if (document.getElementById("selectRowNo").value<=0) {
        alert("请选择要删除的分类类型！");
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
		 if (document.getElementById("operation").value == "add") {
			if (confirm("您确定要添加该资源分类信息吗？") == false) {
				return false;
			}
		} else {
			return false;
		}

		// 取得各控件的值，验证输入的合法性
		// 联网方式ID
		var categoryID = document.getElementById("categoryID").value
				.replace(/(^\s*)|(\s*$)/g, "");
		// 联网方式名称
		var categoryName = document.getElementById("categoryName").value
				.replace(/(^\s*)|(\s*$)/g, "");

		// 联网方式ID是否输入
		if (categoryID == "") {
			alert("请输入分类ID！");
			document.getElementById("categoryID").focus();
			return false;
		}
		// 联网方式名称是否输入
		if (categoryName == "") {
			alert("请输入分类名称！");
			document.getElementById("categoryName").focus();
			return false;
		}

		// 验证正整数的正则表达式
		var reg = new RegExp("^((0)|([1-9]{1}[0-9]*))$");
		// 验证联网方式ID是否为正整数
		if (reg.test(categoryID) == false) {
			alert("联网方式ID输入应为正整数，请确认！");
			document.getElementById("categoryID").focus();
			return false;
		}

        // 验证联网方式ID的大小（限制最大为9位数）
		if (categoryID.length > 9) {
			alert("联网方式ID设置过大，请重新输入！");
			document.getElementById("categoryID").focus();
			return false;
		}
		// 验证联网方式名称的长度
		if (categoryName.length > 24) {
			alert("联网方式名设置过长，请重新输入！");
			document.getElementById("categoryName").focus();
			return false;
		}

		// 提交表单
		document.getElementById("form").submit();
	}
</script>
	</body>
</html>