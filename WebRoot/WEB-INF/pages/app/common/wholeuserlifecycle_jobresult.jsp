<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="wholeuserlifecycleList == null or wholeuserlifecycleList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>

<s:if test="wholeuserlifecycleList != null and wholeuserlifecycleList.size>0">
	<div class="title">
		整体用户生命周期
	</div>
	
	<table id="dataSourceTbl_jobresult" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="showTblChart(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总启动IMEI数">
				总启动IMEI数
			</th>
			<th onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载数量">
				总下载数量
			</th>
			<th onclick="showTblChart(3);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总启动次数">
				总启动次数
			</th>
			<th onclick="showTblChart(4);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总启动天数">
				总启动天数
			</th>
		</tr>
		<s:iterator value="wholeuserlifecycleList">
			<tr>
				<td>
					<s:date name="statDate" format="MM-dd" />~<s:date name="statEndDate" format="MM-dd" />
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
				<td>
					${startdays }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>