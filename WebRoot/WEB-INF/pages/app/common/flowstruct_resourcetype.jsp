<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="resourcetypeList == null or resourcetypeList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>

<s:if test="resourcetypeList != null and resourcetypeList.size>0">
	<div class="title">
		资源类别总体及分析
	</div>

	<table id="dataSourceTbl_total" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<td rowspan="2">
				总体数据
			</td>
			<td onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载次数">
				总下载次数
			</td>
			<s:iterator value="resourcetypeList" begin="0" end="0">
				<s:if test="list != null and list.size > 1">
					<s:iterator value="list" begin="0" end="1">
						<td onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand" title="类别1下载次数">
							${typename }下载次数
						</td>
						<td onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand" title="类别1下载次数">
							${typename }下载资源数
						</td>
					</s:iterator>
				</s:if>
			</s:iterator>
		</tr>
		<tr>
			<s:iterator value="resourcetypeList" begin="0" end="0">
				<td>
					${totaldown }
				</td>

				<s:if test="list != null and list.size > 1">
					<s:iterator value="list" begin="0" end="1">
						<td>
							${downcnt }
						</td>
						<td>
							${downapp }
						</td>
					</s:iterator>
				</s:if>
			</s:iterator>
		</tr>
	</table>




	<table id="dataSourceTbl_jobresult" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="总下载次数">
				总下载次数
			</th>
			<s:iterator value="resourcetypeList" begin="0" end="0">
				<s:if test="list != null and list.size > 1">
					<s:iterator value="list" begin="0" end="1">
						<th onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand" title="类别1下载次数">
							${typename }下载次数
						</th>
						<th onclick="" onMouseOver="this.bgColor='#FF9900'"
							onMouseOut="this.bgColor=''" style="cursor: hand" title="类别1下载次数">
							${typename }下载资源数
						</th>
					</s:iterator>
				</s:if>
			</s:iterator>
		</tr>
		<s:iterator value="resourcetypeList" begin="1">
			<tr>
				<td>
					<s:date name="statDate" format="MM-dd" />
				</td>
				<td>
					${totaldown }
				</td>

				<s:if test="list != null and list.size > 1">
					<s:iterator value="list" begin="0" end="1">
						<td>
							${downcnt }
						</td>
						<td>
							${downapp }
						</td>
					</s:iterator>
				</s:if>
			</tr>
		</s:iterator>
	</table>
</s:if>