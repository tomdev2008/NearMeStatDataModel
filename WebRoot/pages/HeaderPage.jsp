<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>NearMe统计与分析系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/header.css" />
</head>

<body>
	<div  class="header">
		<div class="logo"><img src="../images/logo.png" width="180" height="28" /></div>
		<div class="login_info">
			<p>欢迎，
				<a href="../sys/personInfo/UserInfoModify_init.do" target="right">${session.SESSION_ADMIN.userName}</a>&nbsp;|
				<a href="javascript:{window.top.location.href='../logout.do';}">退出</a>&nbsp;
				<br />
				<span id="serverTime"></span>
			</p>
		</div>
	</div>
<script type="text/javascript">
<% Date now = new Date();%>
var serverTime = new Date(<%=now.getTime()%>);
function showTime(){
	serverTime.setSeconds(serverTime.getSeconds()+1);
	document.getElementById("serverTime").innerHTML=
		serverTime.getFullYear()+"-"+(serverTime.getMonth()+1)+"-"+serverTime.getDate()+"&nbsp;"
		+serverTime.getHours()+":"+serverTime.getMinutes()+":"+serverTime.getSeconds();
	setTimeout("showTime()", 1000);
}
setTimeout("showTime()", 1000);
</script>
</body>
</html>
