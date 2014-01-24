<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
	bordercolor="#EFEFEF" cellpadding="3">
	<tr style="background-color: #DEEBFB">
		<th>
			${top10Entity.columns.column1 }
		</th>
		<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand"
			title="${top10Entity.columns.column2 }">
			${top10Entity.columns.column2 }
		</th>
		<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand"
			title="${top10Entity.columns.column3 }（%）">
			${top10Entity.columns.column3 }
		</th>
	</tr>
	<s:iterator value="top10Entity.valueList">
		<tr>
			<td>
				${value1 }
			</td>
			<td>
				${value2 }
			</td>
			<td>
				${value3 }%
			</td>
		</tr>
	</s:iterator>
</table>