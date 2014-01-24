<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"
	import="java.util.*,com.nearme.statistics.model.sys.MenuItem, com.nearme.statistics.common.Constants"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
d = new dTree('d');
d.config.target = "right";
//id, pid, name, url
d.add(0, -1, '统计与分析系统', '', '统计与分析系统');

// 动态设置菜单
<%
	String menuStr = (String)session.getAttribute(Constants.SESSION_MENU_STR);
    if(menuStr != null){
    	response.getWriter().write(menuStr);
    }
%>
document.getElementById("div_list").innerHTML=d;
</script>
