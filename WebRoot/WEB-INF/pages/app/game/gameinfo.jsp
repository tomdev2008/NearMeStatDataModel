<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="gameinfoList != null and gameinfoList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th title="选项请勿超过限定数200">
				选项
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="所属分类">
				所属分类
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="产品主ID">
				产品主ID
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="产品ID">
				产品ID
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="产品名称">
				产品名称
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="产品版本">
				产品版本
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="上架时间">
				上架时间
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="收费类型">
				收费类型
			</th>
		</tr>
		<s:iterator value="gameinfoList">
			<tr>
				<td align="center">
					<input type="checkbox" id="chkProductID_${productID}"
						name="chkProductID" value="${productID}"/>
				</td>
				<td>
					${fenlei }
				</td>
				<td>
					${productMID }
				</td>
				<td>
					${productID }
				</td>
				<td>
					${productName }
				</td>
				<td>
					${productVersion }
				</td>
				<td>
					${uptime }
				</td>
				<td>
					${feetype }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>