<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="myTags" uri="http://com.nearme.myTag/myTags"%>
<table id="dataSourceTblCp" class="data-load" border="1"
	cellspacing="0" bordercolor="#EFEFEF" cellpadding="3">
	<tr style="background-color: #DEEBFB">
		<th>
			日期
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			1-2
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			3-5
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			6-9
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			10-29
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			30-99
		</th>
		<th onclick="" onMouseOver="this.bgColor='#FF9900'"
			onMouseOut="this.bgColor=''" style="cursor: hand" title="">
			100+
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
		</tr>
	</s:iterator>
</table>
