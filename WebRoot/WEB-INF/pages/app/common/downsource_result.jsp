<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="downsourceList != null and downsourceList.size>0">
	<table id="dataSourceTbl_downsource" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				日期
			</th>
			<th onclick="showTblChartDownsource(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载IMEI数量">
				下载量
			</th>
			<th onclick="showTblChartDownsource(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="浏览次数">
				浏览量
			</th>
		</tr>
		<s:iterator value="downsourceList">
			<tr>
				<td>
					<s:date name="statDate" format="MM-dd"/>
				</td>
				<td>
					${downcnt }
				</td>
				<td>
					${browsecnt }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>