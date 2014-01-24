<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="topresourceList == null or topresourceList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>

<s:if test="topresourceList != null and topresourceList.size>0">
    <div class="title">
		top资源总体及分析
	</div>
    
	<table id="dataSourceTbl_total" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<td rowspan="2">
				总体数据
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载次数">
				总下载次数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top50资源下载次数">
				top50资源下载次数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top100资源下载次数">
				top100资源下载次数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top200资源下载次数">
				top200资源下载次数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top300资源下载次数">
				top300资源下载次数
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top500资源下载次数">
				top500资源下载次数
			</td>
		</tr>
		<s:iterator value="topresourceTotal">
			<tr>
				<td>
					${totaldown }
				</td>
				<td>
					${top50 }
				</td>
				<td>
					${top100 }
				</td>
				<td>
					${top200 }
				</td>
				<td>
					${top300 }
				</td>
				<td>
					${top500 }
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
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载次数">
				总下载次数
			</th>
			<th onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top50资源下载次数">
				top50资源下载次数
			</th>
			<th onclick="showTblChart(3);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top100资源下载次数">
				top100资源下载次数
			</th>
			<th onclick="showTblChart(4);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top200资源下载次数">
				top200资源下载次数
			</th>
			<th onclick="showTblChart(5);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top300资源下载次数">
				top300资源下载次数
			</th>
			<th onclick="showTblChart(6);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="top500资源下载次数">
				top500资源下载次数
			</th>
		</tr>
		<s:iterator value="topresourceList">
			<tr>
				<td>
					<s:date name="statDate" format="MM-dd" />
				</td>
				<td>
					${totaldown }
				</td>
				<td>
					${top50 }
				</td>
				<td>
					${top100 }
				</td>
				<td>
					${top200 }
				</td>
				<td>
					${top300 }
				</td>
				<td>
					${top500 }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>