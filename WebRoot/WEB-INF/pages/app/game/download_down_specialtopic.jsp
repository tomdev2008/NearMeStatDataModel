<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if
	test="downspecialtopicList != null and downspecialtopicList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="专题下载">
				专题下载
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="专题直接下载">
				专题直接下载
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="专题详情">
				专题详情
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="专题详情下载">
				专题详情下载
			</th>
		</tr>
		<s:iterator value="downspecialtopicList">
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
					${downdirect }
				</td>
				<td>
					${detail }
				</td>
				<td>
					${downdetail }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>