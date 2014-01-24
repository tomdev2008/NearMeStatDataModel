<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>
<div align="right">
<table width="99%">
	<tr>
		<td  align="left"><strong>日期：</strong>${form.statDateInt}</td>
		<td width="50px"><a href="#" onclick="toBack();"><font color="red">返回</font></a></td>
	</tr>
</table>
</div>
<table id="dataSourceTbl" class="data-load" border="1"	cellspacing="0" bordercolor="#999999" cellpadding="3">
	<tr class="data-head">
		<th>
			下载来源
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="从该来源下载的次数">
			下载次数
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="下载次数占比">
			占比
		</th>
	</tr>
	<s:iterator value="appSourceList">
		<tr>
			<td>
				<s:if test="channel=='' || channel==null">未知</s:if>
				<s:if test="channel!='' && channel!=null">${channel }</s:if>
			</td>
			<td>
				${installTimes }
			</td>
			<td>
				${avgInstall }
			</td>
		</tr>
	</s:iterator>
</table>
