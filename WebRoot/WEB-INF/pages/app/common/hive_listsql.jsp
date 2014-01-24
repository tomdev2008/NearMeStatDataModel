<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="hivesqlList != null and hivesqlList.size>0">
	<s:form id="form_showsql">
		<table id="dataSourceTbl_showsql" class="data-load" border="1"
			cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
			<s:iterator value="hivesqlList">
				<tr>
					<td>
						${index }
					</td>
					<td class="data-fixlength">
						${sql }
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:form>
</s:if>