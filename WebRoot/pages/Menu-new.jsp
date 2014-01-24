<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"
	import="java.util.*,com.nearme.statistics.model.sys.MenuItem,com.nearme.statistics.common.Constants"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>NearMe统计与分析系统</title>
		<link rel="stylesheet" type="text/css" href="dtree.css" />
		<link rel="stylesheet" type="text/css" href="menu.css" />
		<script type="text/javascript" src="JS/ndtree.js">
</script>
		<script type="text/javascript" src="../js/jquery-1.4.2.min.js">
</script>
	</head>
	<body class='menu'>
		<div class="divbody">
			<div>
				<b>快速定位</b>
				<%--
				屏蔽掉菜单风格切换功能
				<span> <a class="changestyle" onclick="changeMenuStyle();"
					onfocus="this.blur();"> 切换 </a> </span>
			    --%>
			</div>
			<div align="left">
				<select id="quickLocation" name="quickLocation"
					onchange="submitSelect(this.value);">
					<%
						// 动态产生select选项
						List<MenuItem> menuList = (List<MenuItem>) session
								.getAttribute("SESSION_MENULIST");
						Map<String, String> sessionPerms = (Map<String, String>) session
								.getAttribute("SESSION_PERMS");
						StringBuilder select = new StringBuilder();
						boolean optgroup = true;
						if (sessionPerms != null) {
							for (MenuItem menu : menuList) {
								if (sessionPerms.containsKey("all")
										|| sessionPerms
												.containsKey("M" + menu.getMenuKey())) {
									String menukey = menu.getMenuKey();
									String menutext = menu.getMenuText();
									if (menukey.length() == 6) {
										if (menukey.startsWith("01") && optgroup) {
											optgroup = false;
											select.append("<optgroup label='应用'>");
										} else if (menukey.startsWith("02") && !optgroup) {
											select.append("</optgroup>");
											optgroup = true;
											select.append("<optgroup label='ColorOS(内销)'>");
										} else if (menukey.startsWith("03") && optgroup) {
											select.append("</optgroup>");
											optgroup = false;
											select.append("<optgroup label='ColorOS(外销)'>");
										} else if (menukey.startsWith("90") && !optgroup) {
											select.append("</optgroup>");
											optgroup = true;
											select.append("<optgroup label='系统维护'>");
										} else if (menukey.startsWith("91") && optgroup) {
											select.append("</optgroup>");
											optgroup = false;
											select.append("<optgroup label='个人信息管理'>");
										}
										select.append(String.format(
												"<option value='%s'>%s</option>", menukey,
												menutext));
									}
								}
							}
						}
						if (select.length() > 0) {
							response.getWriter().write(select.toString().trim());
						}
					%>
				</select>
			</div>
			<div id="div_list">
				<s:include value="menu_list.jsp" />
			</div>
			<div>
				<a href="javascript: d.openAll();"><b>展开所有</b> </a> |
				<a href="javascript: d.closeAll();"><b>折叠所有</b> </a>
			</div>
		</div>
		<script type="text/javascript">
var seltype = document.getElementById("quickLocation").value;
submitSelect(seltype);

function submitSelect(select) {
	$.ajax( {
		type : "POST",
		dataType : "html",
		url : 'menu_select.do',
		data : "select=" + select,
		success : function(result) {
			$("#div_list").html(result);
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}

function quickLocationSys(menuid) {
	d.closeAll();
	d.openAllChildren(menuid);
	//打开第一个带链接的菜单项
	fst = d.findFirstUrl(menuid);
	if (fst >= 0) {
		d.s(fst);
		window.parent.document.getElementById("right").src = d.aNodes[fst].url
				.replace("../", "");
	}
}

//setTimeout("quickLocationSys('0')", 3000);

function changeMenuStyle() {
	window.parent.frames.left.location = "Menu.jsp";
}
</script>
	</body>
</html>
