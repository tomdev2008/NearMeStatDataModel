<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"
	import="java.util.*,com.nearme.statistics.model.sys.MenuItem"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>NearMe统计与分析系统</title>
		<link rel="stylesheet" type="text/css" href="dtree.css" />
		<link rel="stylesheet" type="text/css" href="menu.css" />
		<script type="text/javascript" src="JS/dtree.js">
</script>
	</head>
	<body class='menu'>
		<div class="divbody">
			<div>
				<b>快速定位</b>
				<span style="cursor: hand;"> <a class="changestyle" href="#"
					onclick="changeMenuStyle();" onfocus="this.blur();">切换 </a> </span>
			</div>
			<div align="left">
				<select id="quickLocation" name="quickLocation"
					onchange="javascript:quickLocationSys(this.value)">
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
									if (menukey.length() == 4) {
										if (menukey.startsWith("01") && optgroup) {
											optgroup = false;
											select.append("<optgroup label='应用'>");
										} else if (menukey.startsWith("02") && !optgroup) {
											select.append("</optgroup>");
											optgroup = true;
											select.append("<optgroup label='系统维护'>");
										} else if (menukey.startsWith("03") && optgroup) {
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
			<div class="dtree">
				<script type="text/javascript">
//id, pid, name, url, title, target, icon, iconOPne, open,
d = new dTree('d');
d.config.target = "right";
d.config.folderLinks = false;
d.config.useLines = false;
d.add(0, -1, '<B>统计与分析系统</B>', '', '统计与分析系统');

<%
	//List<MenuItem> menuList = (List<MenuItem>)session.getAttribute("SESSION_MENULIST");
	//Map<String,String> sessionPerms = (Map<String,String>)session.getAttribute("SESSION_PERMS");
	if(sessionPerms != null){
		for(MenuItem menu : menuList){
			if(sessionPerms.containsKey("all") || sessionPerms.containsKey("M"+menu.getMenuKey())){
				//System.out.print(menu.getMenuKey());
				response.getWriter().write(String.format("d.add('%s','%s','%s','%s');\r\n",
					menu.getMenuKey(),
					menu.getMenuParentKey(),
					menu.getMenuText(),
					menu.getMenuUrl()));
			}
		}
	}
%>

document.write(d);

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
	window.parent.frames.left.location = "Menu-new.jsp";
}
</script>
			</div>
			<div>
				<a href="javascript: d.openAll();"><b>展开所有</b>
				</a> |
				<a href="javascript: d.closeAll();"><b>折叠所有</b>
				</a>
			</div>
		</div>
	</body>
</html>
