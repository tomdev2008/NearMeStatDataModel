<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="detailList != null and detailList.size > 0">
	<input type="button" id="btndeletedetail" value="批量删除" class="btn-style"
		onclick="if(confirm('确定删除勾选信息?')){btnDetailBothDelete('${detailList[0].groupname}');}" />

	<table class="data-load" id="tblModules" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th title="选项请勿超过限定数200">
				<input type="checkbox" name="checkDetailAll" id="checkDetailAll"
					onclick="checkDetailAllClick()" />
			</th>
			<th>
				ID
			</th>
			<th>
				分组名称
			</th>
			<th>
				来源(SOURCE_CODE)
			</th>
			<th>
				来源描述
			</th>
			<th>
				分类ID
			</th>
			<th>
				下载类型
			</th>
			<th>
				操作
			</th>
		</tr>
		<s:iterator value="detailList">
			<tr>
				<td align="center" width="20px">
					<input type="checkbox" id="${id}" name="chkDetail" value="${id}" />
				</td>
				<td>
					${id }
				</td>
				<td>
					${groupname }
				</td>
				<td>
					${sourcecode }
				</td>
				<td>
					${sourcedesc }
				</td>
				<td>
					${categoryid }
				</td>
				<td>
					${downtype }
				</td>
				<td>
					<input type="button" value="编辑"
						onclick="showDetailClick('${id}','${groupname}','${sourcecode}','${sourcedesc}','${categoryid}','${downtype}')" />
					<input type="button" value="删除"
						onclick="if(confirm('确定删除  ${id }  对应的信息?')){deleteDetail('${id}','${groupname}');}" />
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>