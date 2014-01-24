<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type"
		content="text/html; charset=UTF-8" />
	<title>NearMe统计与分析系统</title>
	<link rel="stylesheet" href="Style.css" type="text/css" />
</head>
<body>
	<table width="98%" border="0" cellspacing="0" cellpadding="3"
		align="center">
		<tr>
			<td>
				<h2 class="pageHeader">概述</h2>
			</td>
		</tr>
		<tr>
			<td valign="top" class="text">
				统计系统的目的：<strong>构建一个数据中心，满足ColorOS和可可系统APP数据分析、运营分析需求</strong>
			</td>
		</tr>
		<tr>
			<td valign="top" class="text">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td valign="top" class="text">
				<div style="width:98%;border:solid 1px gray;">
					<p style="font-size:16px;"><b>名词解释</b></p>
					<p>
						<b><a id="all_user">全网用户(全部用户)</a></b>:可可的使用者有两大注册来源：<u>OPPO官网</u>和<u>可可</u>,全网用户(全部用户)指的是这两部分用户的总和。
					</p>
					<p>
						<b><a id="可可_user">可可注册用户</a></b>:所有通过可可注册的用户,包括<u>可可商店开发者</u>、<u>可可邮箱注册用户</u>&nbsp;以及<u>&nbsp;各手机终端注册用户</u>。<br/>
						其中<u>可可商店开发者</u>一般注册方式为邮箱注册,和普通<u>可可个人中心邮箱注册用户</u>无法区分也无需区分。
					</p>
					<p>
						<b><a id="actived_user">激活用户(有效用户)</a></b>:所有通过可可注册的用户是需要激活的,注册超过24小时未激活则删除该注册记录,而且未激活用户不能使用可可的全部功能。<br/>
						目前通过各手机终端注册的用户在注册成功之后由系统触发激活,通过邮箱注册的用户需要点击注册邮箱中的激活链接激活账户。
					</p>
					<p>
						<b><a id="actived_rate">激活比例</a></b>:激活用户数&nbsp;/&nbsp;注册用户数。(其中通过手机终端注册的用户默认为激活用户,也计算在激活用户数之内)
					</p>
					<p>
						<b><a id="active_user">活跃用户</a></b>:启动过任意<u>客户端应用</u>的用户(暂不包括WEB登录)。
					</p>
					<p>
						<b><a id="active_user">活跃率</a></b>:活跃用户数 / 激活用户数。
					</p>
					<p>
						<b><a id="silence_user">沉默用户</a></b>:一定时间内未出现活动的用户(即:总用户-活跃用户)。
					</p>
					<p>
						<b><a id="new_start_rate">新用户启动比例</a></b>:7天内注册且启动过的用户占7天内总注册用户的比例。<br/>
						(特别注意:WEB端邮箱注册用户的新用户启动比例计算只包含启动过手机客户端的用户)
					</p>
					<p>
						<b><a id="return_user">回头用户</a></b>:一段时间前注册的用户在最近一段时间启动过客户端算作回头用户。<br/>
						例如：在2011年04月01日到2011年05月01日有5000注册用户,这5000用户在最近一周内有500用户启动过任意客户端,<br/>
						则这500用户属于回头用户,用户回头率为10%,另外的4500用户可认为是流失用户。<br/>
						(计算回头用户需要指定注册时间段、回访时间段)
					</p>
				</div>
				<div style="width:98%;border:solid 1px gray;margin-top:20px;">
					<p style="font-size:16px;"><b><a id="user_source_structure">用户来源结构图</a></b></p>
					<p>
						<table width="600px" border="1px">
							<tr align="center">
								<td colspan="6">可可产品使用者(全网用户)</td>
							</tr>
							<tr align="center">
								<td colspan="5">可可注册用户</td>
								<td rowspan="3">OPPO官网<br/>注册用户</td>
							</tr>
							<tr align="center">
								<td colspan="2">WEB端注册用户</td>
								<td colspan="3">手机客户端注册用户</td>
							</tr>
							<tr align="center">
								<td>商店开发者</td>
								<td>个人中心<br/>邮箱注册用户</td>
								<td>X903<br/>注册用户</td>
								<td>T703<br/>注册用户</td>
								<td>……</td>
							</tr>
						</table>
				</div>
				<div style="width:98%;border:solid 1px gray;margin-top:20px;">
					<p style="font-size:16px;"><b>Questions &amp; Answers</b></p>
					<p>
						<b><a id=""><img src="../images/question.png"/>:为什么在查询下载量时选定用户来源为X903或T703时,下载量明显比实际下载量少?</a></b><br/>
						<img src="../images/answer.png"/>:可可用户的注册统计从2011-12-05开始,当查询下载量时限定注册用户来源,将导致下载量偏少。<br/>
					</p>
					<p>
						<b><a id=""><img src="../images/question.png"/>:为什么查询全网注册用户时可以选择过滤条件为"终端类型",而查询可可注册用户时过滤条件却是"机型"?</a></b><br/>
						<img src="../images/answer.png"/>:统计系统最初设计时为了获得最多用户信息,将用户注册交给SSO记录,但是为了不改变已有SSO接口的正常运行,<br/>
						这些记录数据只能区分用户注册来自官网或可可某个应用、注册方式为邮箱或者手机。<br/>
						&nbsp;&nbsp;为了获取更多的统计数据用户统计分析,于2011-12-05在个人中心新开发了一个统计模块,该模块统计的数据能够区分用户注册自邮箱或手机、<br/>
						那一款手机。但是该模块只能拦截可可的注册用户,不包含官网的任何用户!<br/>
						&nbsp;&nbsp;另外,2011-12-05之前的可可注册用户使用的机型信息将无法挽回地丢失(用户并没有丢失,只是用户注册时使用那款手机无从得知)。
					</p>
					<p>
						<b><a id="why_unknown"><img src="../images/question.png"/>:在选择查询条件时经常出现各种"未知",是什么情况?</a></b><br/>
						<img src="../images/answer.png"/>:出现未知原因有以下几个:<br/>
						1.统计系统设计之初,可能因为考虑不周或者认为某些属性用处不大,客户端没有获取这些属性,新发版本的客户端搜集了这些属性,但是不能<br/>
						强制用户升级老版本客户端,所以不能判断这些属性为何物,只能统一冠以"未知"。<br/>
						(例如T703手机1.1版本的统计就没有手机联网方式属性)<br/>
						2.某些属性因为各种原因,客户端确实获取不到,例如用户通过刷ROM等方式更改了手机的某些属性,或者设置了权限导致客户端无法获取某些属性。<br/>
						3.统计系统开发过程中不断的增加一些新发现的有用属性,这些属性在原来的数据中是没有的,这些新增的属性在老数据中都只能认为"未知"。

					</p>
					<p>
						<b><a id="why_tomorrow"><img src="../images/question.png"/>:特大新闻,某居然看见来自明年的用户启动记录!?</a></b><br/>
						<img src="../images/answer.png"/>:客户端启动数据由内嵌在手机内的统计模块收集并在达到一定数量后统一上传,	每条客户端启动数据记录的时间戳均为手机时间,<br/>
						如果手机时间或日期不准确,统计模块上传到统计系统的客户端启动时间也将不正确.<br/>
						(因为统计数据的上传对用户本身是无任何直接意义的,为了不占用用户流量或影响用户使用,统计模块在收集一定数据之后,<b>仅在手机使用WIFI</b>上网时<br/>
						上传统计数据.为了获得数据产生的时间,只能采用手机时间,虽然这个时间可能不正确,但是我们要相信大部分用户的手机时间是基本正确的!)
					</p>
					<p>
						<b><a id=""><img src="../images/question.png"/>:</a></b><br/>
						<img src="../images/answer.png"/>:
					</p>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>