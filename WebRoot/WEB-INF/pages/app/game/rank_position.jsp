<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="rankpositionList != null and rankpositionList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				日期
			</th>
			<th onclick="showTblChart(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="该时间段内，在该位置，用户点击直接下载产生的下载量之和">
				直接下载
			</th>
			<th onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="该时间段内，在该位置，用户点击直接浏览详情次数之和">
				详情浏览
			</th>
			<th onclick="showTblChart(3);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="该时间段内，在该位置，用户点击进入详情并下载的下载量之和">
				详情下载
			</th>
		</tr>
		<s:iterator value="rankpositionList">
			<tr>
				<td>
					<s:if test="formposition.lidu == 'DAILY'">
						<s:date name="statDate" format="MM-dd" />
					</s:if>
					<s:if test="formposition.lidu == 'WEEKLY'">
						<s:date name="statDate" format="MM-dd" />~<s:date
							name="statEndDate" format="MM-dd" />
					</s:if>
					<s:if test="formposition.lidu == 'MONTHLY'">
						<s:date name="statDate" format="yyyy-MM" />
					</s:if>
				</td>
				<td>
					${directdown }
				</td>
				<td>
					${detailbrowse }
				</td>
				<td>
					${detaildown }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>