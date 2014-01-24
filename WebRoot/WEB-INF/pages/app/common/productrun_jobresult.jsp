<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="moduleList == null or moduleList.size==0">
<b><font color="green" style="font-size: 14px"> ${errorinfo}</font></b>
<br>
</s:if>


<s:if test="moduleList != null and moduleList.size>0">
	<table id="dataSourceTbl_jobresult_module" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				模块名称
			</th>
			<th onclick="showTblChartModule(1);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载IMEI数量">
				下载IMEI数量
			</th>
			<th onclick="showTblChartModule(2);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="浏览次数">
				浏览次数
			</th>
			<th onclick="showTblChartModule(3);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="直接下载次数">
				直接下载次数
			</th>
			<th onclick="showTblChartModule(4);" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="详情下载次数">
				详情下载次数
			</th>
		</tr>
		<s:iterator value="moduleList">
			<tr>
				<td>
					${name }
				</td>
				<td>
					${downimei }
				</td>
				<td>
					${browseimei }
				</td>
				<td>
					${directdown }
				</td>
				<td>
					${detaildown }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>