<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="title">
	更改分组信息
</div>

<s:form cssClass="submit" namespace="/app/commonsetting">
	<div class="title" align="center">
		更改分组编码
	</div>
	<table class="submit" align="center" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr>
			<td>
				分组编码:
			</td>
			<td>
				<s:textfield id="groupform_groupcode" name="groupform.groupcode"
					label="分组编码" readonly="true"></s:textfield>
			</td>
		</tr>
		<tr>
			<td>
				分组名称:
			</td>
			<td>
				<s:textfield id="groupform_groupname" name="groupform.groupname"
					label="分组名称"></s:textfield>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="提交" class="btn-style"
					onclick="updateGroupinfo();" />
			</td>
		</tr>
	</table>
</s:form>