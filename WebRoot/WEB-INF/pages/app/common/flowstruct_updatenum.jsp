<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="updatenumList == null or updatenumList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>

<s:if test="updatenumList != null and updatenumList.size>0">
	<div class="title">
		更新数量总体及分析
	</div>
	
	<table id="dataSourceTbl_total" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<td rowspan="2">
				总体数据
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载IMEI数">
				下载IMEI数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载次数">
				下载次数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="更新次数">
				更新次数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="更新imei数">
				更新imei数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="更新资源数">
				更新资源数
			</td>
		</tr>
		<s:iterator value="updatenumTotal">
			<tr>
				<td>
					${downimei }
				</td>
				<td>
					${downcnt }
				</td>
				<td>
					${updatecnt }
				</td>
				<td>
					${updateimei }
				</td>
				<td>
					${updateres }
				</td>
			</tr>
		</s:iterator>
	</table>
	
	<table id="dataSourceTbl_jobresult" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="showTblChart(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载IMEI数">
				下载IMEI数
			</th>
			<th onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载次数">
				下载次数
			</th>
			<th onclick="showTblChart(3);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="更新次数">
				更新次数
			</th>
			<th onclick="showTblChart(4);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="更新imei数">
				更新imei数
			</th>
			<th onclick="showTblChart(5);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="更新资源数">
				更新资源数
			</th>
		</tr>
		<s:iterator value="updatenumList">
			<tr>
				<td>
					<s:date name="statDate" format="MM-dd" />
				</td>
				<td>
					${downimei }
				</td>
				<td>
					${downcnt }
				</td>
				<td>
					${updatecnt }
				</td>
				<td>
					${updateimei }
				</td>
				<td>
					${updateres }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>