<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="feeList != null and feeList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天新出现累计付费超过3000的用户账号数">
				新增鲸鱼
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="截止到当天为止用户总数">
				总鲸鱼用户
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="截止到当天已有7天或以上不曾登录的鲸鱼数">
				鲸鱼流失7
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="截止到当天已有7天或以上不曾付费的鲸鱼数">
				鲸鱼付费流失7
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="截止到当天已有30天或以上不曾登录的鲸鱼数">
				鲸鱼付费流失30
			</th>
		</tr>
		<s:iterator value="feeList">
			<tr>
				<td>
					${statDate }
				</td>
				<td>
					<a href="#" onclick="javascript:getUser('${statDate}',0, ${newWhale});">${newWhale}</a>
				</td>
				<td>
					<a href="#" onclick="javascript:getUser('${statDate}',3,${totalWhale});">${totalWhale}</a>
				</td>
				<td></a>
					<a href="#" onclick="javascript:getUser('${statDate}',1,${whaleLost7});">${whaleLost7}</a>
				</td>
				<td>
					<a href="#" onclick="javascript:getUser('${statDate}',2,${whalePayLost7});">${whalePayLost7}</a>
				</td>
				<td>
					${whalePayLost30 }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>