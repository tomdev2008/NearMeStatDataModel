<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table id="dataSourceTbl_detail" class="data-load" border="1" cellspacing="0"
	bordercolor="#EFEFEF" cellpadding="3">
	<tr style="background-color: #DEEBFB">
		<th>
			${detailEntity.columns.column1 }
		</th>
		<th onclick="showTblChartDetail(1)"
			onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
			style="cursor: hand" title="${detailEntity.columns.column2 }">
			${detailEntity.columns.column2 }
		</th>
		<th onclick="showTblChartDetail(2)"
			onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
			style="cursor: hand" title="${detailEntity.columns.column3 }（%）">
			${detailEntity.columns.column3 }
		</th>
	</tr>
	<s:iterator value="detailEntity.valueList">
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