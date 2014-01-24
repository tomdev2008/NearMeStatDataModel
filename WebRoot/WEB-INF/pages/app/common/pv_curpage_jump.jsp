<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>
	<form>	
	<table width="90%">
		<tr>
			<td> <strong>${form.name}</strong></td>
			<td width="50%" align="right"> <a href="#" onclick="to_back();">返回</a></td>
		</tr>
	</table>
	</form>
	<table id="dataSourceTblDetail" class="data-load" border="1"
		cellspacing="0" bordercolor="#999999" cellpadding="3">
		<tr>
			<th>
				跳转至
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
				比例
		</th>
	</tr>
	<s:iterator value="jumpList">
		<tr>
			<td>
				${nextPage }
			</td>
			<td>
				${rate }
			</td>
		</tr>
	</s:iterator>
</table>
