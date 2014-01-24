<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if
	test="downactiveList != null and downactiveList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="活动中心下载">
				活动中心下载
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="任务下载">
				任务下载
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="礼包下载">
				礼包下载
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="活动下载">
				活动下载
			</th>
		</tr>
		<s:iterator value="downactiveList">
			<tr>
				<td>
					<s:if test="form.lidu == 'DAILY'">
						<s:date name="statDate" format="MM-dd" />
					</s:if>
					<s:if test="form.lidu == 'WEEKLY'">
						<s:date name="statDate" format="MM-dd" />~<s:date
							name="statEndDate" format="MM-dd" />
					</s:if>
					<s:if test="form.lidu == 'MONTHLY'">
						<s:date name="statDate" format="yyyy-MM" />
					</s:if>
				</td>
				<td>
					${down }
				</td>
				<td>
					${downtask }
				</td>
				<td>
					${downgift }
				</td>
				<td>
					${downactive }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>