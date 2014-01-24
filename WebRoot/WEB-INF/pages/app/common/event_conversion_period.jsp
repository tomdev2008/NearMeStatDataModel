<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<table id="dataSourceTblCTPeriod" class="data-load" border="1"
	cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
	<input type="hidden" id="hid_event" value="${form.eventName}"/>
	<input type="hidden" id="hid_prevevent" value="${form.prevEventName}"/>
	<tr class="data-head">
		<th>日期	</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">最近七个周期趋势</th>
	</tr>
	<s:iterator value="eventFlowList">
		<tr>
			<td>
				${statDate }
			</td>
			<td>
				<s:set id="type">${form.type}</s:set>
				<s:if test="#type=='COUNTS'">
					${eventTCR }
				</s:if>
				<s:if test="#type=='IMEIS'">
					${eventITCR }
				</s:if>
				
			</td>
		</tr>
	</s:iterator>
</table>
