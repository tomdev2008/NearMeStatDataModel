<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>
	<form>	
	<table width="90%">
		<tr>
			<td> <strong>EventKey：</strong>${form.eventKey}</td>
			<td> <strong>EventValue：</strong>${form.eventValue}</td>
			<td width="50%" align="right"> <a href="#" onclick="to_back();">返回</a></td>
		</tr>
	</table>
	</form>
	<div id="div_flash_eventdetail"></div>
	<table id="dataSourceTblEventDetail" class="data-load" border="1"
		cellspacing="0" bordercolor="#999999" cellpadding="3">
		<tr>
			<th>
				日期
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="事件发生次数">
			事件发生次数
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="占比">
			占比
		</th>
	</tr>
	<s:iterator value="detailList">
		<tr>
			<td>
				${statdateStr }
			</td>
			<td>
				${eventCounts }
			</td>
			<td>
				${eventCountsPercent }%
			</td>
		</tr>
	</s:iterator>
</table>
