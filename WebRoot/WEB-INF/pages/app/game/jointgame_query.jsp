<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="jointgameList != null and jointgameList.size>0">
	<table id="dataSourceTbl2" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="截止到该日为止，该游戏的总用户">
				总用户
			</th>
			<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="当天新增激活的设备数">
				新增激活
			</th>
			<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="当天新增的账号数">
				新增用户
			</th>
			<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天有登录行为的账号数">
				登陆用户
			</th>
			<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天所有用户的总登录次数">
				启动次数
			</th>
			<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="截止到当天已有7天未登录游戏的用户">
				沉默用户
			</th>
			<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="截止到当天已有30天未登录游戏的用户">
				流失用户
			</th>
			<th onclick="showTblChart(8)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天新增用户中，在第二天继续登录的用户数，占当天新增的百分比">
				次日留存（%）
			</th>
			<th onclick="showTblChart(9)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天新增用户中，在7天继续登录的用户数，占当天新增的百分比">
				7日留存（%）
			</th>
			<th onclick="showTblChart(10)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天在游戏中有付费行为的用户">
				付费人数
			</th>
			<th onclick="showTblChart(11)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="当天付费总额">
				付费总额
			</th>
			<th onclick="showTblChart(12)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天付费人数/登录用户">
				付费率（%）
			</th>
			<th onclick="showTblChart(13)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天付费总额/当天登录用户数">
				ARPU
			</th>
			<th onclick="showTblChart(14)" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand"
				title="当天付费总额/当天付费用户数">
				ARPPU
			</th>
		</tr>
		<s:iterator value="jointgameList">
			<tr>
				<td>
					<s:date name="statDate" format="MM-dd" />
				</td>
				<td>
					${totaluser }
				</td>
				<td>
					${newimei }
				</td>
				<td>
					${newuser }
				</td>
				<td>
					${loginuser }
				</td>
				<td>
					${startcnt }
				</td>
				<td>
					${lost7 }
				</td>
				<td>
					${lost30 }
				</td>
				<td>
					${remain2 }
				</td>
				<td>
					${remain7 }
				</td>
				<td>
					${feeuser }
				</td>
				<td>
					${feetotal }
				</td>
				<td>
					${feeratio }
				</td>
				<td>
					${arpu }
				</td>
				<td>
					${arppu }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>