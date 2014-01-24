<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="modulepositionList == null or modulepositionList.size==0">
	<b><font color="green" style="font-size: 14px"> ${errorinfo}</font>
	</b>
	<br>
</s:if>

<s:if test="modulepositionList != null and modulepositionList.size>0">
	<div class="title">
		模块位置分析
	</div>

	<table id="dataSourceTbl_total" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<td rowspan="2">
				总体数据
			</td>
			<td onclick="showTblChart(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总启动IMEI数">
				总下载IMEI数
			</td>
			<td onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载次数">
				总下载次数
			</td>
			<s:iterator value="modulepositionList" begin="0" end="0">
				<s:if test="list != null and list.size > 0">
					<s:iterator value="list" status="status">
						<s:if test="#status.index>=0 and #status.index<=3">
							<td onclick="" onMouseOver="this.bgColor='#FF9900'"
								onMouseOut="this.bgColor=''" style="cursor: hand" title="模块下载次数">
								${modulename }下载次数
							</td>
							<td onclick="" onMouseOver="this.bgColor='#FF9900'"
								onMouseOut="this.bgColor=''" style="cursor: hand"
								title="模块下载imei">
								${modulename }下载imei数
							</td>
						</s:if>
					</s:iterator>
				</s:if>
			</s:iterator>
		</tr>
		<s:iterator value="modulepositionList" begin="0" end="0">
			<tr>
				<td>
					${downimei }
				</td>
				<td>
					${totaldown }
				</td>
				<s:if test="list != null and list.size > 0">
					<s:iterator value="list" status="status">
						<s:if test="#status.index>=0 and #status.index<=3">
							<td>
								${downcnt }
							</td>
							<td>
								${downimei }
							</td>
						</s:if>
					</s:iterator>
				</s:if>
			</tr>
		</s:iterator>
	</table>


	<table id="dataSourceTbl_jobresult" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="showTblChart(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载IMEI数">
				总下载IMEI数
			</th>
			<th onclick="showTblChart(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载次数">
				总下载次数
			</th>
			<s:iterator value="modulepositionList" begin="0" end="0">
				<s:if test="list != null and list.size > 0">
					<s:iterator value="list" status="status">
						<s:if test="#status.index>=0 and #status.index<=3">
							<th onclick="" onMouseOver="this.bgColor='#FF9900'"
								onMouseOut="this.bgColor=''" style="cursor: hand" title="模块下载次数">
								${modulename }下载次数
							</th>
							<th onclick="" onMouseOver="this.bgColor='#FF9900'"
								onMouseOut="this.bgColor=''" style="cursor: hand"
								title="模块下载imei">
								${modulename }下载imei数
							</th>
						</s:if>
					</s:iterator>
				</s:if>
			</s:iterator>
		</tr>
		<s:iterator value="modulepositionList" begin="1">
			<tr>
				<td>
					${statDatestr }
				</td>
				<td>
					${downimei }
				</td>
				<td>
					${totaldown }
				</td>
				<s:if test="list != null and list.size > 0">
					<s:iterator value="list" status="status">
						<s:if test="#status.index>=0 and #status.index<=3">
							<td>
								${downcnt }
							</td>
							<td>
								${downimei }
							</td>
						</s:if>
					</s:iterator>
				</s:if>
			</tr>
		</s:iterator>
	</table>
</s:if>