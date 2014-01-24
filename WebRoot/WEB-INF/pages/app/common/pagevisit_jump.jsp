<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table id="tblDatas" class="data-load" border="1" cellspacing="0" bordercolor="#EFEFEF"
	cellpadding="3" >
	<tr>
		<td colspan="3" align="center" style="font-weight: bold;">
			页面跳转明细
		</td>
	</tr>
	<tr class="data-head">
		<th></th>
		<th>
			页面访问跳转比率
		</th>
		<th>
			页面访问IMEI跳转比率
		</th>
	</tr>
	<s:iterator value="jumpList">
		<tr>
			<td>
				${ jumppage }
			</td>
			<td>
				${ vjumpRatio }
			</td>
			<td>
				${ vimeiJumpRatio }
			</td>
		</tr>
	</s:iterator>
</table>