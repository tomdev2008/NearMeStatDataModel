<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="errormodeldistributeList != null and errormodeldistributeList.size>0">
	<table id="tblDatas" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				机型
			</th>
			<th>
				错误次数
			</th>
		</tr>
		<s:iterator value="errormodeldistributeList">
			<tr>
				<td>
					${ model }
				</td>
				<td>
					${ errorcnt }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>
