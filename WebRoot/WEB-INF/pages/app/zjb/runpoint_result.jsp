<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="div_top">
	<s:if test="runpointList != null and runpointList.size>0">
		<table id="dataSourceTbl_top" class="data-load" border="1"
			cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
			<tr class="data-head">
				<th>
					软件名
				</th>
				<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过装机宝安装的总数量">
					安装总量
				</th>
				<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过自定义资源包安装的数量">
					自定义资源包安装量
				</th>
				<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过在线资源包安装的数量">
					在线资源包安装量
				</th>
				<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过搜索结果所产生的安装数量">
					搜索安装量
				</th>
				<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过在线软件模块所产生的安装数量">
					在线软件安装量
				</th>
				<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过强制推广方式所产生的安装数量">
					强推安装量
				</th>
				<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过关联安装所产生的安装数量">
					安装器安装量
				</th>
			</tr>
			<!-- 附加的数据 -->
			<s:if test="runpointaddList != null and runpointaddList.size>0">
				<s:iterator value="runpointaddList">
					<tr>
						<td>
							${softname }
							<input type="button" id="btnadd" value="删除" class="btn-style"
								onclick="deleteSoft('${softname }','${lidu }');" />
						</td>
						<td>
							${installtotal }
						</td>
						<td>
							${selfcnt }
						</td>
						<td>
							${onlinerescnt }
						</td>
						<td>
							${searchcnt }
						</td>
						<td>
							${onlinesoftcnt }
						</td>
						<td>
							${pushinstall }
						</td>
						<td>
							${installercnt }
						</td>
					</tr>
				</s:iterator>
			</s:if>

			<s:iterator value="runpointList">
				<tr>
					<td>
						${softname }
					</td>
					<td>
						${installtotal }
					</td>
					<td>
						${selfcnt }
					</td>
					<td>
						${onlinerescnt }
					</td>
					<td>
						${searchcnt }
					</td>
					<td>
						${onlinesoftcnt }
					</td>
					<td>
						${pushinstall }
					</td>
					<td>
						${installercnt }
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
</div>

<br>


<s:if test="runpointsearchList != null and runpointsearchList.size>0">
	<div id="div_search">
		<table id="dataSourceTbl_search" class="data-load" border="1"
			cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
			<tr class="data-head">
				<th>
					软件名
				</th>
				<th onclick="showTblChart(1)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过装机宝安装的总数量">
					安装总量
				</th>
				<th onclick="showTblChart(2)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过自定义资源包安装的数量">
					自定义资源包安装量
				</th>
				<th onclick="showTblChart(3)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过在线资源包安装的数量">
					在线资源包安装量
				</th>
				<th onclick="showTblChart(4)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过搜索结果所产生的安装数量">
					搜索安装量
				</th>
				<th onclick="showTblChart(5)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过在线软件模块所产生的安装数量">
					在线软件安装量
				</th>
				<th onclick="showTblChart(6)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过强制推广方式所产生的安装数量">
					强推安装量
				</th>
				<th onclick="showTblChart(7)" onMouseOver="this.bgColor='#FF9900'"
					onMouseOut="this.bgColor=''" style="cursor: hand"
					title="该时间段内对应软件通过关联安装所产生的安装数量">
					安装器安装量
				</th>
			</tr>
			<s:iterator value="runpointsearchList">
				<tr>
					<td>
						<input type="button" id="btnadd" value="添加" class="btn-style"
							onclick="addSoft('${softname }','${lidu }');" />
						${softname }
					</td>
					<td>
						${installtotal }
					</td>
					<td>
						${selfcnt }
					</td>
					<td>
						${onlinerescnt }
					</td>
					<td>
						${searchcnt }
					</td>
					<td>
						${onlinesoftcnt }
					</td>
					<td>
						${pushinstall }
					</td>
					<td>
						${installercnt }
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</s:if>
