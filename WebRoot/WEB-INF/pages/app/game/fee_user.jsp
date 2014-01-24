<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="feeUserList != null and feeUserList.size>0">
	<table id="dataSourceTbl1" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				用户id
			</th>
			<th onclick="" 	title="">
				用户名
			</th>
			<th onclick="" 	title="">
				可可号
			</th>
			<th onclick="" title="">
				渠道id
			</th>
			<th onclick="" 	title="">
				新增日期
			</th>
			<th onclick="" title="">
				最后登录
			</th>
			<th onclick="" title="">
				最后消费
			</th>
			<th onclick="" title="">
				消费次数
			</th>
			<th onclick="" title="">
				消费金额
			</th>
			<th onclick="" title="">
				结余可币
			</th>
			<th onclick="" title="">
				充值次数
			</th>
			<th onclick="" title="">
				充值金额
			</th>
		</tr>
		<s:iterator value="feeUserList">
			<tr>
				<td>
					${ssoid }
				</td>
				<td>
					${userName }
				</td>
				<td>
					${keke }
				</td>
				<td>
					${channelId }
				</td>
				<td>
					${newWhaleDate }
				</td>
				<td>
					${lastLoginDate }
				</td>
				<td>
					${lastPayDate }
				</td>
				<td>
					${payCount }
				</td>
				<td>
					${paySum }
				</td>
				<td>
					${kekeBalance }
				</td>
				<td>
					${rechargeCount }
				</td>
				<td>
					${rechargeSum }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>