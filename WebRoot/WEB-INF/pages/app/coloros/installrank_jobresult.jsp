<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="installrankList == null or installrankList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>

<s:if
	test="installrankList != null and installrankList.size>0">
	<div class="title">
		安装排行
	</div>

	<table id="dataSourceTbl_jobresult" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				日期
			</th>
			<th>
				排名
			</th>
			<th onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="应用名称">
				应用
			</th>
			<th onclick="showTblChart(3);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="安装次数">
				安装次数
			</th>
			<th onclick="showTblChart(4);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="升级次数">
				升级次数
			</th>
		</tr>
		<s:iterator value="installrankList">
			<tr>
				<td>
					<s:date name="statDate" format="MM-dd" />~<s:date name="statEndDate" format="MM-dd" />
				</td>
				<td>
					${position }
				</td>
				<td>
					${appname }
				</td>
				<td>
					${installcnt }
				</td>
				<td>
					${upgradecnt }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>