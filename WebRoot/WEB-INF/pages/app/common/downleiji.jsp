<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>

<html>
	<head>
		<title>下载累计</title>
		<sx:head locale="zh" parseContent="true"
			extraLocales="en-us,zh-cn,de-de" />
		<link rel="stylesheet" type="text/css" href="../../css/content.css" />
		<script type="text/javascript" src="../../js/global.js" />
</script>
		<script type="text/javascript" src="../../FCF/FusionCharts.js">
</script>

		<link href="../../css/jquery.msgbox.css" type="text/css"
			rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-latest.min.js"
			type="text/javascript">
</script>
		<script src="../../js/jquery.dragndrop.min.js" type="text/javascript">
</script>
		<script src="../../js/jquery.msgbox.js" type="text/javascript">
</script>
		<script type="text/javascript" language="JavaScript">
$(function() {
	$("#reflect").click(function() {
		//alert("d");
			new $.msgbox( {
				onClose : function() {
				},
				title : '反馈提交',
				type : 'input',
				content : 'Enter your words:'
			}).show();
		});
});

//反馈点击提交
function suggestSubmit() {
	//路径信息
	var url = window.location.href;
	//反馈人
	var reflect_name = document.getElementById('reflect_name').value;
	//反馈信息
	var reflect_info = document.getElementById('reflect_info').value;

	$.ajax( {
		type : "POST",
		dataType : "html",
		url : '../../reflect_reflect.do',
		data : 'url=' + url + '&detail=' + reflect_info + '&username='
				+ reflect_name,
		success : function(result) {
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});
}
</script>
	</head>
	<body class="context">
		<div class="title">
			下载累计
			<input style="float: right;" type="button" id="reflect" value="反馈" />
		</div>
		<s:hidden name="form.systemID"></s:hidden>
		<div class="data-grandtotal">
			<table id="dataSourceTbl" class="data-grandtotal" border="1"
				cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
				<s:iterator value="downleijiList">
					<tr>
						<td class="data_title">
							累计下载量
						</td>
						<td>
							${downcnt }
						</td>
					</tr>
					<tr>
						<td class="data_title">
							累计下载IMEI数
						</td>
						<td>
							${downimei }
						</td>
					</tr>
					<tr>
						<td class="data_title">
							累计下载SSOID数
						</td>
						<td>
							${downssoid }
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</body>
</html>