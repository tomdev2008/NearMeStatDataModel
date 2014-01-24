<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
	<head>
		<title>IP白名单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="js/global.js">
</script>
	</head>
	<body class="context">
		<div class="title">
			IP白名单
		</div>
		<div class="data-load">
			<table class="data-load" border="1" cellspacing="0"
				bordercolor="#EFEFEF" cellpadding="3">
				<caption>
					<b>IP限制白名单</b>
				</caption>
				<tr class="data-head">
					<td>
						序号
					</td>
					<td>
						起始IP
					</td>
					<td>
						终止IP
					</td>
					<td>
						匹配模式
					</td>
					<td>
						操作
					</td>
				</tr>
				<s:iterator value="ipList">
					<tr>
						<td>
							${id }
						</td>
						<td>
							${strIpStart }
						</td>
						<td>
							${strIpEnd }
						</td>
						<td>
							${strIpRegex }
						</td>
						<td>
							<a
								href="javascript:{if(confirm('确定删除该规则?')){window.location.href='sys/admin/ipWhite.do?deleteFilter=1&serial=${ip.id }';}}">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<br />
		<br />
		<form method="post" action="sys/admin/ipWhite.do" class="submit">
			<table class="submit" style="width: 360px;" align="center" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<tr class="data-head">
					<td id="td_ip_area" onclick="clickTab('ip_area','ip_re')"
						style="background-color: rgb(171, 171, 171);">
						IP段
					</td>
					<td id="td_ip_re" onclick="clickTab('ip_re','ip_area')">
						匹配模式
					</td>
				</tr>
				<tr>
					<td colspan="2" height="80">
						<div id="div_ip_area" style="display: block;">
							<input type="text" id="ipStart" name="ipStart" />
							-
							<input type="text" id="ipEnd" name="ipEnd" />
						</div>
						<div id="div_ip_re" style="display: none;">
							<input type="text" id="ipRegex" name="ipRegex" />
							<br />
							<ul>
								<li>
									示例：192.168.*.*
								</li>
							</ul>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" id="btnAdd" name="btnAdd" value="提交"
							class="btn-style" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
function clickTab(show, hide) {
	document.getElementById('div_' + show).style.display = "block";
	document.getElementById('div_' + hide).style.display = "none";
	document.getElementById('td_' + show).style.backgroundColor = "#ababab";
	document.getElementById('td_' + hide).style.backgroundColor = "#FFF";
}
if ('${resultMsg}') {
	alert('${resultMsg}');
}
</script>
	</body>
</html>
