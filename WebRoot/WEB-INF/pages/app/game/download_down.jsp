<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="downloadList != null and downloadList.size>0">
	<table id="dataSourceTbl" class="data-load" border="1" cellspacing="0"
		bordercolor="#EFEFEF" cellpadding="3">
		<tr class="data-head">
			<th>
				时间
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="下载">
				下载
			</th>
			<th onclick="clickHomepage();" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="首页下载">
				首页下载
			</th>
			<th onclick="clickRank();" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="排行榜下载">
				排行榜下载
			</th>
			<th onclick="clickSort();" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="分类下载">
				分类下载
			</th>
			<th onclick="clickSpecialtopic();"
				onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="专题下载">
				专题下载
			</th>
			<th onclick="clickSearch();" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="搜索下载">
				搜索下载
			</th>
			<th onclick="clickSearchRecommend();"
				onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="搜推下载">
				搜推下载
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="详情浏览">
				详情浏览
			</th>
			<th onclick="" onMouseOver="this.bgColor='#FF9900'"
				onMouseOut="this.bgColor=''" style="cursor: hand" title="详情下载">
				详情下载
			</th>
			<th onclick="clickActivecenter()"
				onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="活动中心下载">
				活动中心下载
			</th>
		</tr>
		<s:iterator value="downloadList">
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
					${downHomepage }
				</td>
				<td>
					${downRank }
				</td>
				<td>
					${downSort }
				</td>
				<td>
					${downSpecialtopic }
				</td>
				<td>
					${downSearch }
				</td>
				<td>
					${downSearchrecommend }
				</td>
				<td>
					${detailBrowse }
				</td>
				<td>
					${detailDown }
				</td>
				<td>
					${downActivecenter }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>