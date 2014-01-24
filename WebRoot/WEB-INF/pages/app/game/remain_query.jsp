<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="remainList != null and remainList.size>0">
	<table id="dataSourceTbl2" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="该时间段内新增帐号在+X单位时间周期内有登录行为的帐号总数">
				账号留存
			</th>
			<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="留存率">
				账号留存率（%）
			</th>
			<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="该时间段内新增帐号在+X单位时间周期内有登录行为的IMEI总数">
				IMEI留存
			</th>
			<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="留存率">
				IMEI留存率（%）
			</th>
		</tr>
		<s:iterator value="remainList">
			<tr>
				<td>
					<s:if test="form.lidu == 'DAILY'">
						<s:date name="remainDate" format="MM-dd" />
					</s:if>
					<s:if test="form.lidu == 'WEEKLY'">
						<s:date name="remainDate" format="MM-dd" />
					</s:if>
					<s:if test="form.lidu == 'MONTHLY'">
						<s:date name="remainDate" format="yyyy-MM" />
					</s:if>
				</td>
				<td>
					${remain }
				</td>
				<td>
					${remainratio }
				</td>
				<td>
					${imeiremain }
				</td>
				<td>
					${imeiremainratio }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>