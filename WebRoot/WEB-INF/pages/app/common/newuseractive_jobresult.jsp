<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="monthList == null or monthList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>

<s:if test="monthList != null and monthList.size>0">
	<div class="title">
	第一个月
	</div>
	
	<table id="dataSourceTbl_month" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				活跃天数
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="启动IMEI">
				启动IMEI
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载次数">
				下载次数
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
				启动次数
			</th>
		</tr>
		<s:if test="monthList != null and monthList.size > 0">
			<s:iterator value="monthList" begin="0">
				<tr>
					<td>
						${activedays}
					</td>
					<td>
						${startimei }
					</td>
					<td>
						${downcnt }
					</td>
					<td>
						${startcnt }
					</td>
				</tr>
			</s:iterator>
		</s:if>
	</table>
</s:if>
<br>
<s:if test="weekList != null and weekList.size>0">
	<div class="title">
	第一个周
	</div>
	
	<table id="dataSourceTbl_week" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				活跃天数
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="启动IMEI">
				启动IMEI
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载次数">
				下载次数
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="启动次数">
				启动次数
			</th>
		</tr>
		<s:if test="weekList != null and weekList.size > 0">
			<s:iterator value="weekList" begin="0">
				<tr>
					<td>
						${activedays}
					</td>
					<td>
						${startimei }
					</td>
					<td>
						${downcnt }
					</td>
					<td>
						${startcnt }
					</td>
				</tr>
			</s:iterator>
		</s:if>
	</table>
</s:if>
