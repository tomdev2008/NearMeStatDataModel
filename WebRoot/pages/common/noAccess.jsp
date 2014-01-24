<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.SESSION_PERMS == null or #session.SESSION_ADMIN == null">
登录超时,<a href="#" onclick="javascript:window.top.location.href='../../login.do';">点击重新登录</a>
</s:if>
<s:if test="#session.SESSION_PERMS != null and #session.SESSION_ADMIN != null">
对不起,您没有访问该功能的权限!!
</s:if>