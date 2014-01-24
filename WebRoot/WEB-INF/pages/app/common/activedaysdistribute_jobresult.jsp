<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="activedaysdistributeList == null or activedaysdistributeList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>

<s:if
	test="activedaysdistributeList != null and activedaysdistributeList.size>0">
	<div class="title">
		活跃天数和分布
	</div>
	
	<table id="dataSourceTbl_jobresult" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				活跃天数
			</th>
			<th onclick="showTblChart(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="IMEI数量">
				IMEI数量
			</th>
			<th onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载次数">
				下载次数
			</th>
			<th onclick="showTblChart(3);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
				启动次数
			</th>
		</tr>
		<s:iterator value="activedaysdistributeList">
			<tr>
				<td>
					${activedays }
				</td>
				<td>
					${startimei }
				</td>
				<td>
					${downcnt }
				</td>
				<td>
					${startcnt }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>