<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table id="dataSourceTbl_usetime" class="data-load" border="1" cellspacing="0"
	bordercolor="#EFEFEF" cellpadding="3">
	<tr class="data-head">
		<th>
			${usetimeEntity.columns.column1 }
		</th>
		<th onclick="showTblChartUsetime(1)"
			onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
			style="cursor: hand" title="${usetimeEntity.columns.column2 }">
			${usetimeEntity.columns.column2 }
		</th>
		<th onclick="showTblChartUsetime(2)"
			onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
			style="cursor: hand" title="${usetimeEntity.columns.column3 }（%）">
			${usetimeEntity.columns.column3 }
		</th>
	</tr>
	<s:iterator value="usetimeEntity.valueList">
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