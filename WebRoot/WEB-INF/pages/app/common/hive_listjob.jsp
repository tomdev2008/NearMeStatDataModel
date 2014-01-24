<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="hivejobList != null and hivejobList.size>0">
	<s:if test="errorinfo != null and errorinfo != ''">
		<b><font color="red" style="font-size: 14px"> ${errorinfo} 
		</font></b>
		<br>
		<br>
	</s:if>
	
	<s:form id="form_export">
		<s:hidden name="form.systemID"></s:hidden>
		<s:hidden id="form.jobID" name="form.jobID"></s:hidden>
		<s:hidden id="form.resulttable" name="form.resulttable"></s:hidden>
		<s:hidden id="form.weidu" name="form.weidu"></s:hidden>
		<s:hidden id="form.lidu" name="form.lidu"></s:hidden>
		<table id="dataSourceTbl_listjob" class="data-load" border="1"
			cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
			<tr class="data-head">
				<th onclick="" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand" title="任务ID">
					任务ID
				</th>
				<th onclick="" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand" title="查询条件">
					查询条件
				</th>
				<th onclick="" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand" title="查询人">
					查询人
				</th>
				<th onclick="" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand" title="查询时间">
					查询时间
				</th>
				<th onclick="" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand" title="任务状态">
					任务状态
				</th>
				<th>
					操作
				</th>
			</tr>
			<s:iterator value="hivejobList">
				<tr>
					<td>
						${jobID }
					</td>
					<td class="data-fixlength">
						${condition }
					</td>
					<td>
						${username }
					</td>
					<td>
						<s:date name="starttime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<s:if test="state == 0">
							<font color="#999">未完成</font>
						</s:if>
						<s:if test="state == 1">
							<font color="#FF8400">完成</font>
						</s:if>
					</td>
					<td>
						<a href="javascript:queryresult('${jobID}','${resulttable}','${weidu}','${lidu}')">查看结果</a>
						<a href="javascript:if(confirm('      确认导出？\n请先查询结果，再导出')){exportresult('${jobID}','${resulttable}','${weidu}','${lidu}');}">导出</a>
						<s:if test="'admin' == #session.SESSION_ADMIN.userName">
						    <input type="button" value="删除" onclick="deleteresult('${jobID}','${resulttable}','${weidu}','${lidu}');" />
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:form>
</s:if>