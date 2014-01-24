<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<input type="button" id="btnupdategroup" value="批量删除" class="btn-style"
	onclick="if(confirm('确定删除勾选信息?')){btnGroupBothDelete();}" />

<table id="dataSourceTbl_group" class="data-load" border="1"
	cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
	<tr class="data-head">
		<th title="选项请勿超过限定数200">
			<input type="checkbox" name="checkGroupAll" id="checkGroupAll"
				onclick="checkGroupAllClick()" />
		</th>
		<th>
			分组名称
		</th>
		<th>
			操作
		</th>
	</tr>
	<s:iterator value="groupList">
		<tr>
			<td align="center" width="20px">
				<input type="checkbox" id="${groupname}" name="chkGroup"
					value="${groupname}" />
			</td>
			<td>
				${groupname }
			</td>
			<td>
			    <input type="button" value="删除" onclick="if(confirm('确定删除  ${groupname }  对应的信息?')){deleteGroup('${form.systemID}','${groupname}');}" />
			    <input type="button" value="查看" onclick="queryDetail('${form.systemID}','${groupname}')" />
			</td>
		</tr>
	</s:iterator>
</table>