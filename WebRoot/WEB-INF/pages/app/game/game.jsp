<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 游戏名2 -->
<s:if test="gameinfoList != null and gameinfoList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th title="选项请勿超过限定数200">
				选项
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="产品ID">
				产品ID
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="产品名称">
				产品名称
			</th>
		</tr>
		<s:iterator value="gameinfoList">
			<tr>
				<td align="center">
					<input type="radio" id="chkProductID_${productID}"
						name="chkProductID" value="${productID}"/>
				</td>
				<td>
					${productID }
				</td>
				<td>
					${productName }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>