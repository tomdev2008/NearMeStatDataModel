<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="downsortList != null and downsortList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="分类下载">
				分类下载
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="分类直接下载">
				分类直接下载
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="分类详情">
				分类详情
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="分类详情下载">
				分类详情下载
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="Top1分类">
				Top1分类
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="Top2分类">
				Top2分类
			</th>
			<th onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="Top3分类">
				Top3分类
			</th>
		</tr>
		<s:iterator value="downsortList">
			<tr>
				<td>
					<s:if test="form.lidu == 'DAILY'">
						<s:date name="statDate" format="MM-dd" />
					</s:if>
					<s:if test="form.lidu == 'WEEKLY'">
						<s:date name="statDate" format="MM-dd" />~<s:date
							name="statEndDate" format="MM-dd" />
					</s:if>
					<s:if test="form.lidu == 'MONTHLY'">
						<s:date name="statDate" format="yyyy-MM" />
					</s:if>
				</td>
				<td>
					${down }
				</td>
				<td>
					${downdirect }
				</td>
				<td>
					${detail }
				</td>
				<td>
					${downdetail }
				</td>
				<td>
					${top1 }（${categoryname1}）
				</td>
				<td>
					${top2 }（${categoryname2}）
				</td>
				<td>
					${top3 }（${categoryname3}）
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>