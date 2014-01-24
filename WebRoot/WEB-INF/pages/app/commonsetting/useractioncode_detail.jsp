<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="title">
	具体行为编码
</div>

<table class="data-load" border="1" cellspacing="0"
	bordercolor="#EFEFEF" cellpadding="3">
	<tr style="background-color: #DEEBFB">
		<th>
			分组编码
		</th>
		<th>
			分组名称
		</th>
		<th>
			行为编码
		</th>
		<th>
			行为名称
		</th>
		<th>
			操作
		</th>
	</tr>
	<s:iterator value="useractionList">
		<tr>
			<td>
				${groupcode }
			</td>
			<td>
				${groupname }
			</td>
			<td>
				${actioncode }
			</td>
			<td>
				${actionname }
			</td>
			<td>
				<a href="javascript:showActioninfo(${form.systemID},'${groupcode}','${groupname}','${actioncode}','${actionname}')">更改</a>
				<a href="javascript:deleteActioninfo(${form.systemID},'${groupcode}','${actioncode}')">删除</a>
				<a href="javascript:queryUseraction(${form.systemID},'${groupcode}','${actioncode}')">查看数据</a>
			</td>
		</tr>
	</s:iterator>
</table>

<br>
<br>
<s:form cssClass="submit" namespace="/app/commonsetting">
	<div class="title" align="center">
		行为编码
	</div>
	<s:hidden id="actionform_groupcode" name="actionform.groupcode"></s:hidden>
	<table class="submit" align="center" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr>
			<td>
				行为编码:
			</td>
			<td>
				<s:textfield id="actionform_actioncode" name="actionform.actioncode"
					label="行为编码"></s:textfield>
			</td>
		</tr>
		<tr>
			<td>
				行为名称:
			</td>
			<td>
				<s:textfield id="actionform_actionname" name="actionform.actionname"
					label="行为名称"></s:textfield>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			    <input type="hidden" id="actionform_operation" name="actionform.operation" />
				<input type="button" value="提交" class="btn-style"
					onclick="operationActioninfo();" />
			</td>
		</tr>
	</table>
</s:form>