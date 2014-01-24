<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="cptList != null and cptList.size>0">
	<table id="dataSourceTbl_cpt" class="data-load" border="1"
		cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
		<tr style="background-color: #DEEBFB">
			<td>
				日期
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="总下载量">
				总下载量
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="总浏览量">
				总浏览量
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="热门推荐">
				热门推荐
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="小编推荐">
				小编推荐
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="应用榜">
				应用榜
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="游戏榜">
				游戏榜
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="最近流行">
				最近流行
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="分类精选">
				分类精选
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="二级分类">
				二级分类
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="大家都在搜">
				大家都在搜
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="48小时热搜榜">
				48小时热搜榜
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="搜索排行榜">
				搜索排行榜
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="主动搜索">
				主动搜索
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="装机必备">
				装机必备
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="相关推荐">
				相关推荐
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="下载有礼">
				下载有礼
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="升级">
				升级
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="单个资源广告">
				单个资源广告
			</td>
			<td onMouseOver="this.bgColor='#FF9900'" onMouseOut="this.bgColor=''"
				style="cursor: hand" title="活动页面">
				活动页面
			</td>
		</tr>
		<s:iterator value="cptList">
			<tr>
				<td>
					<s:date name="statDate" format="yyyy-MM-dd" />
				</td>
				<td>
					${totalDown }
				</td>
				<td>
					${totalBrowse }
				</td>
				<td>
					${hottuijian }
				</td>
				<td>
					${editortujian }
				</td>
				<td>
					${app }
				</td>
				<td>
					${game }
				</td>
				<td>
					${pop }
				</td>
				<td>
					${fenlei }
				</td>
				<td>
					${secondfenlei }
				</td>
				<td>
					${allsearch }
				</td>
				<td>
					${hotsearch }
				</td>
				<td>
					${searchrank }
				</td>
				<td>
					${tosearch }
				</td>
				<td>
					${installmust }
				</td>
				<td>
					${recommondpush }
				</td>
				<td>
					${downgift }
				</td>
				<td>
					${upgrade }
				</td>
				<td>
					${resourceads }
				</td>
				<td>
					${activepage }
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>