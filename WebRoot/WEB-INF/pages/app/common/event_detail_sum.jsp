<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>
	<table id="dataSourceTblPie" class="data-load" border="1"
		cellspacing="0" bordercolor="#999999" cellpadding="3">
		<tr class="data-head">
			<th>
				EventValue
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="事件发生次数">
			事件发生次数
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="占比">
			占比
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
						onMouseOut="this.bgColor=''" style="cursor: hand" title="操作">
						操作
		</th>
	</tr>
	<s:iterator value="sumList">
		<tr>
			<td>
				${eventValue }
			</td>
			<td>
				${eventCounts }
			</td>
			<td>
				${eventCountsPercent }%
			</td>
			<td>
				<a href="#" onclick="queryDetail('${eventValue }');">详情</a>
			</td>
		</tr>
	</s:iterator>
</table>
