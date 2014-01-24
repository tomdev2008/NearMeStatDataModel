<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="errordetailList != null and errordetailList.size>0">
	<div class="title">
		错误列表
	</div>

	<table id="tblDatas" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				错误摘要
			</th>
			<th>
				应用版本
			</th>
			<th>
				错误次数
			</th>
		</tr>
		<s:iterator value="errordetailList">
			<tr>
				<td>
					<a href="#" title="${detail}" onclick="showDetail(this.title,'${version}')">${ genneral }</a>
				</td>
				<td>
					${ version }
				</td>
				<td>
					${ errorcnt }
				</td>
			</tr>
		</s:iterator>
	</table>

	<div id="div_page_devide" style="float: left" align="right">
		<a href="###" onclick="showPrePage(${form.pagecurr});" class="pagination">上一页 </a>
		<a href="###" onclick="showPage(1);" class="pagination"> 1 </a>
		<s:if test="form.pagecnt>=2">
		<a href="###" onclick="showPage(2);" class="pagination"> 2 </a>
		</s:if>
		<s:if test="form.pagecnt>=3">
		<a href="###" onclick="showPage(3);" class="pagination"> 3 </a>
		</s:if>
		<s:if test="form.pagecnt>=7">
		<span class="pagination"> ... </span>
		<a href="###" onclick="showPage(${form.pagecnt});" class="pagination"> ${form.pagecnt} </a>
		</s:if>
		<a href="###" onclick="showNextPage(${form.pagecurr},${form.pagecnt});" class="pagination"> 下一页 </a>
		<span class="pagination">当前第&nbsp;${form.pagecurr}&nbsp;页</span>
		<span class="pagination">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<a href="###" onclick="exportCurrpage();" class="pagination"> 导出当前页 </a>
	</div>
</s:if>
