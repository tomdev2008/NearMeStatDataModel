<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>
<table id="dataSourceTblCp_${form.type}" class="data-load" border="1"
	cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
	<tr style="background-color: #DEEBFB">
		<th>
			日期
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			1~3秒
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			4~10秒
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			11~30秒
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			31~60秒
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			1~3分
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			3~10分
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			10~30分
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			大于30分
		</th>
	</tr>
	<s:iterator value="cpList">
		<tr>
			<td>
				${statdate}
			</td>
			<td>
				${clumn1 }
			</td>
			<td>
				${clumn2 }
			</td>
			<td>
				${clumn3 }
			</td>
			<td>
				${clumn4 }
			</td>
			<td>
				${clumn5 }
			</td>
			<td>
				${clumn6 }
			</td>
			<td>
				${clumn7 }
			</td>
			<td>
				${clumn8 }
			</td>
		</tr>
	</s:iterator>
</table>
